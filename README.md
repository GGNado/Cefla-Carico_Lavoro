# Carico Lavoro — Backend Spring Boot

Sistema di gestione carichi di lavoro. Sostituisce il file Excel "TEST per carichi lavoro CCS" con un backend REST + database relazionale.

---

## Stack tecnico

| Layer | Tecnologia |
|---|---|
| Backend | Java 21 + Spring Boot 3 |
| Database | PostgreSQL |
| Autenticazione | JWT (jjwt 0.12.x) |
| ORM | Spring Data JPA + Hibernate |
| Sicurezza | Spring Security |
| Build | Maven |

---

## Entità principali

```
Role (enum)
  └── ADMIN, MANAGER, COLLABORATOR, VIEWER

ActivityType
  └── nome attività + tempo medio di riferimento (nullable)
  └── esempi: ORDINE (0.41h), OFFERTA (0.47h), ASSENZA (null)

Collaborator
  └── persona a cui viene imputato il carico di lavoro
  └── corrisponde a "Nome customer" nel file Excel originale

UserAccount
  └── utente che accede al sistema
  └── collegato opzionalmente a un Collaborator
  └── ha un Role che determina i permessi

WorkloadEntry  ← entità centrale
  └── una riga del foglio "Database" dell'Excel
  └── inputDate + activityType + collaborator + quantity + estimatedTime + notes
  └── estimatedTime è uno snapshot editabile (NON ricalcolato automaticamente)
  └── soft delete con deleted + deletedAt
```

---

## Ordine di implementazione

### Fase 1 — Entity
1. `Role` (enum)
2. `ActivityType`
3. `Collaborator`
4. `UserAccount` → dipende da Role, Collaborator
5. `WorkloadEntry` → dipende da tutte

Abilitare auditing nel main:
```java
@SpringBootApplication
@EnableJpaAuditing
public class CcsApplication { ... }
```

### Fase 2 — Repository
1. `ActivityTypeRepository`
2. `CollaboratorRepository`
3. `UserAccountRepository`
4. `WorkloadEntryRepository`

### Fase 3 — Autenticazione JWT
1. `JwtService` — genera e valida token
2. `JwtFilter` — intercetta ogni request
3. `UserDetailsServiceImpl` — carica utente dal DB
4. `SecurityConfig` — regole di accesso per endpoint

### Fase 4 — DTO + Validation
Request e response separati dalle entity per ogni risorsa.

### Fase 5 — Service + Controller
Logica di business e endpoint REST.

### Fase 6 — Report ed Export
Query aggregate e export Excel con Apache POI.

---

## Flow applicativo

### Setup iniziale (ADMIN)

```
Admin si logga
    │
    ├── POST /api/activity-types     → crea le attività (ORDINE, OFFERTA, ...)
    ├── POST /api/collaborators      → crea i collaboratori (Rossi Mario, ...)
    └── POST /api/users              → crea gli UserAccount e li collega ai collaboratori
```

### Inserimento carico di lavoro

```
Utente si logga  →  POST /api/auth/login  →  riceve JWT
    │
    └── POST /api/workload-entries
            │
            ├── body: { date, activityTypeId, collaboratorId, quantity, estimatedTime, notes }
            │
            ├── [COLLABORATOR] collaboratorId ignorato → forzato al proprio Collaborator
            └── [MANAGER/ADMIN] collaboratorId libero → qualsiasi Collaborator
```

Il backend:
1. Risolve il collaboratore in base al ruolo
2. Pre-calcola `estimatedTime = quantity * activityType.averageTime` (solo come suggerimento)
3. Se l'utente ha passato un `estimatedTime` manuale → usa quello
4. Se l'attività non ha `averageTime` (es. ASSENZA) → `estimatedTime` obbligatorio nel body
5. Salva la `WorkloadEntry`

### Modifica di una entry

```
Utente cerca entry  →  GET /api/workload-entries?from=&to=&collaboratorId=
    │
    └── PUT /api/workload-entries/{id}
            │
            ├── [COLLABORATOR] può modificare solo entry dove collaborator == se stesso
            └── [MANAGER/ADMIN] può modificare qualsiasi entry
```

### Eliminazione

```
DELETE /api/workload-entries/{id}
    └── soft delete: deleted=true, deletedAt=now(), updatedBy=currentUser
    └── le entry eliminate non compaiono nelle query normali
    └── [COLLABORATOR] solo le proprie
    └── [MANAGER/ADMIN] qualsiasi
```

### Report e analisi

```
GET /api/reports/by-collaborator?from=&to=
GET /api/reports/by-activity?from=&to=
GET /api/reports/summary?from=&to=&collaboratorId=
    │
    └── [COLLABORATOR] vede solo i propri dati
    └── [MANAGER/ADMIN/VIEWER] vedono tutto
```

### Export Excel

```
GET /api/workload-entries/export?from=&to=&collaboratorId=&activityTypeId=
    └── restituisce file .xlsx con i dati filtrati
    └── stessa logica di visibilità dei report
```

---

## Regole di autorizzazione

| Azione | VIEWER | COLLABORATOR | MANAGER | ADMIN |
|---|:---:|:---:|:---:|:---:|
| Legge le proprie entry | ✓ | ✓ | ✓ | ✓ |
| Legge entry altrui | ✓ | ✗ | ✓ | ✓ |
| Crea entry (proprie) | ✗ | ✓ | ✓ | ✓ |
| Crea entry (per altri) | ✗ | ✗ | ✓ | ✓ |
| Modifica entry proprie | ✗ | ✓ | ✓ | ✓ |
| Modifica entry altrui | ✗ | ✗ | ✓ | ✓ |
| Elimina entry | ✗ | ✓ | ✓ | ✓ |
| Gestisce ActivityType | ✗ | ✗ | ✗ | ✓ |
| Gestisce Collaborator | ✗ | ✗ | ✗ | ✓ |
| Gestisce UserAccount | ✗ | ✗ | ✗ | ✓ |
| Vede report | ✓ | ✗ | ✓ | ✓ |
| Export Excel | ✓ | ✗ | ✓ | ✓ |

---

## Logica estimatedTime

```
activityType.averageTime presente?
    │
    ├── SÌ → backend suggerisce quantity * averageTime
    │         l'utente può sovrascrivere con valore manuale
    │
    └── NO (ASSENZA, FIERA, RIUNIONI) → estimatedTime obbligatorio nel body
```

`estimatedTime` è uno **snapshot**: viene salvato al momento dell'inserimento e non cambia anche se `averageTime` viene aggiornato in futuro. Questo garantisce la correttezza dei report storici.

---

## Configurazione

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ccs_workload
    username: ccs_user
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

app:
  jwt:
    secret: ${JWT_SECRET}   # openssl rand -base64 32
    expiration-ms: 86400000 # 24 ore
```

---

## Corrispondenza Excel → Backend

| Foglio Excel | Backend |
|---|---|
| Input (tabella attività) | `ActivityType` entity + `GET /api/activity-types` |
| Input (form inserimento) | `POST /api/workload-entries` |
| Database | `WorkloadEntry` entity |
| Analisi carichi | `GET /api/reports/*` |
| Modifica / CERCA | `GET /api/workload-entries?filters` |
| Modifica / SOVRASCRIVI | `PUT /api/workload-entries/{id}` |
| Pulsante SUBMIT | `POST /api/workload-entries` |