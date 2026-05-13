package com.giggi.ceflacarico_lavoro.init;

import com.giggi.ceflacarico_lavoro.entity.*;
import com.giggi.ceflacarico_lavoro.repository.AttivitaRepository;
import com.giggi.ceflacarico_lavoro.repository.CollaboratoreRepository;
import com.giggi.ceflacarico_lavoro.repository.RoleRepository;
import com.giggi.ceflacarico_lavoro.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UtenteRepository utenteRepository;
    private final CollaboratoreRepository collaboratoreRepository;
    private final AttivitaRepository attivitaRepository;
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

        List<Attivita> attivita = attivitaRepository.findAll();
        if (attivita.isEmpty()) {
            loadAttivita();
            log.info("Attività aggiunte al DB");
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
        Role role = roleRepository.findByName(RoleName.ROLE_ADMIN.getCode());
        Role user = roleRepository.findByName(RoleName.ROLE_USER.getCode());

        if (role == null || user == null) {
            throw new IllegalStateException("Ruolo base non trovato: " + RoleName.ROLE_USER.getCode());
        }

        log.info("Ruolo trovato per utente iniziale: {}", role.getName());

        Collaboratore collaboratore = new Collaboratore();
        collaboratore.setFullName("Admin collaboratore");

        Collaboratore collaboratore1 = new Collaboratore();
        collaboratore1.setFullName("User collaboratore");


        Utente admin = new Utente();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin1234"));
        admin.setEmail("admin@cefla.it");
        admin.setEnabled(true);
        admin.setRoles(Set.of(role));
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        collaboratore.setUserAccount(admin);
        admin.setCollaborator(collaboratore);

        Utente user1 = new Utente();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user1234"));
        user1.setEmail("user@cefla.it");
        user1.setEnabled(true);
        user1.setRoles(Set.of(user));
        user1.setFirstName("User");
        user1.setLastName("User");
        collaboratore1.setUserAccount(user1);
        user1.setCollaborator(collaboratore1);

        Set<Utente> utenti = Set.of(admin, user1);
        Set<Collaboratore> collaboratori = Set.of(collaboratore, collaboratore1);

        collaboratoreRepository.saveAll(collaboratori);

        utenteRepository.saveAll(utenti);
    }

    private void loadAttivita() {
        List<Attivita> attivita = new ArrayList<>();

        attivita.add(createAttivita("ORDINE", new BigDecimal("0.41")));
        attivita.add(createAttivita("GESTIONE PRATICHE (ORDINE, STAMPE, CONTROLLO , ETC..)", new BigDecimal("0.41")));
        attivita.add(createAttivita("MODIFICA ORDINE", new BigDecimal("0.41")));
        attivita.add(createAttivita("OFFERTA", new BigDecimal("0.47")));
        attivita.add(createAttivita("SPEDIZIONE", new BigDecimal("0.46")));
        attivita.add(createAttivita("PIANO SPEDIZIONE", new BigDecimal("0.46")));
        attivita.add(createAttivita("TOUCH POINT COL CLIENTE", new BigDecimal("1.6")));
        attivita.add(createAttivita("GESTIONE CREDITO", new BigDecimal("0.23")));
        attivita.add(createAttivita("ATTIVITA' KU/ SPECIALIST", new BigDecimal("0.81")));
        attivita.add(createAttivita("ALTRO", new BigDecimal("0.81")));

        attivitaRepository.saveAll(attivita);
    }

    private Attivita createAttivita(String name, BigDecimal averageTime) {
        Attivita activity = new Attivita();
        activity.setName(name);
        activity.setAverageTime(averageTime);
        activity.setActive(true);
        return activity;
    }
}