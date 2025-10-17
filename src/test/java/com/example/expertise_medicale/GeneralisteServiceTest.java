package com.example.expertise_medicale;

import com.example.expertise_medicale.models.Generaliste;
import com.example.expertise_medicale.models.enums.Role;
import com.example.expertise_medicale.services.GeneralisteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralisteServiceTest {

    private GeneralisteService service;

    @BeforeEach
    public void setup() {
        service = new GeneralisteService();
    }

    private Generaliste createUniqueGeneraliste() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        Generaliste g = new Generaliste();
        g.setNom("Nom_" + unique);
        g.setPrenom("Prenom_" + unique);
        g.setEmail("gen_" + unique + "@gmail.com");
        g.setPassword("password123");
        g.setRole(Role.GENERALISTE);
        return g;
    }

    @Test
    public void testAddAndFindById() {
        Generaliste g = createUniqueGeneraliste();
        service.add(g);

        Generaliste found = service.findById(g.getId());
        assertNotNull(found);
        assertEquals(g.getEmail(), found.getEmail());
    }

    @Test
    public void testExistsByEmail() {
        Generaliste g = createUniqueGeneraliste();
        service.add(g);

        boolean exists = service.existsByEmail(g.getEmail());
        assertTrue(exists);
    }

    @Test
    public void testGetAllContainsAdded() {
        Generaliste g = createUniqueGeneraliste();
        service.add(g);

        List<Generaliste> all = service.findAll();
        boolean found = all.stream().anyMatch(gen -> gen.getId().equals(g.getId()));
        assertTrue(found);
    }

}
