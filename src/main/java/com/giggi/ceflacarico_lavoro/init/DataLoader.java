package com.giggi.ceflacarico_lavoro.init;

import com.giggi.ceflacarico_lavoro.entity.Role;
import com.giggi.ceflacarico_lavoro.entity.RoleName;
import com.giggi.ceflacarico_lavoro.entity.Utente;
import com.giggi.ceflacarico_lavoro.repository.RoleRepository;
import com.giggi.ceflacarico_lavoro.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
        log.info("Check se aggiungere credenziali base al DB...");

        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            loadRuoli();
            log.info("Ruoli aggiunti al DB");
        }

        List<Utente> utenti = utenteRepository.findAll();
        if (utenti.isEmpty()) {
            loadUtente();
            log.info("Utente aggiunto al DB");
        }

        log.info("Dati iniziali caricati con successo!");
    }

    private void loadRuoli() {
        List<Role> ruoli = new ArrayList<>();

        for (RoleName roleName : RoleName.values()) {
            Role role = new Role();
            role.setName(roleName.getCode());
            role.setDescription(roleName.getDescription());
            ruoli.add(role);
        }

        roleRepository.saveAll(ruoli);
    }

    private void loadUtente() {
        Role role = roleRepository.findByName(RoleName.ROLE_USER.getCode());

        if (role == null) {
            throw new IllegalStateException("Ruolo base non trovato: " + RoleName.ROLE_USER.getCode());
        }

        log.info("Ruolo trovato per utente iniziale: {}", role.getName());

        Utente admin = new Utente();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin1234"));
        admin.setEmail("admin@cefla.it");
        admin.setEnabled(true);
        admin.setRoles(Set.of(role));
        admin.setFirstName("Admin");
        admin.setLastName("Admin");

        utenteRepository.save(admin);
    }
}