package com.example.expertise_medicale;

import com.example.expertise_medicale.models.Patient;
import com.example.expertise_medicale.models.SigneVital;
import com.example.expertise_medicale.models.enums.PatientStatus;
import com.example.expertise_medicale.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {

    private PatientService service;

    @BeforeEach
    public void setup() {
        service = new PatientService();
    }

    private Patient createUniquePatient() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        Patient p = new Patient();
        p.setId("CIN" + unique);
        p.setNom("Nom_" + unique);
        p.setPrenom("Prenom_" + unique);
        p.setCoordonnees("Adresse_" + unique);
        p.setMutuelle(true);
        p.setNumero_securite_sociale("NS" + unique);
        p.setDateArrivee(LocalDateTime.now());
        p.setStatus(PatientStatus.EN_ATTENTE);
        return p;
    }

    private SigneVital createSigneVital() {
        SigneVital s = new SigneVital();
        s.setDate_mesure(LocalDateTime.now());
        s.setTension_arterielle("12/8");
        s.setTemperature_corporelle(37.0);
        s.setFrequence_cardiaque(70);
        s.setFrequence_respiratoire(16.0);
        s.setPoids(70.0);
        s.setTaille(1.75);
        return s;
    }

    @Test
    public void testAddAndFindById() {
        Patient p = createUniquePatient();
        SigneVital s = createSigneVital();
        service.add(p, s);

        Patient found = service.findById(p.getId());
        assertNotNull(found);
        assertEquals(p.getNom(), found.getNom());
    }

    @Test
    public void testFindByTodayIncludesAdded() {
        Patient p = createUniquePatient();
        SigneVital s = createSigneVital();
        service.add(p, s);

        List<Patient> patientsToday = service.findByToday();
        boolean found = patientsToday.stream().anyMatch(pt -> pt.getId().equals(p.getId()));
        assertTrue(found);
    }

    @Test
    public void testUpdatePatient() {
        Patient p = createUniquePatient();
        SigneVital s = createSigneVital();
        service.add(p, s);

        p.setNom("Nom_Modifié");
        service.update(p);

        Patient updated = service.findById(p.getId());
        assertEquals("Nom_Modifié", updated.getNom());
    }
}
