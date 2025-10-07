package com.example.expertise_medicale.models;

import com.example.expertise_medicale.models.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "generalistes")
public class Generaliste extends User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(columnDefinition = "TEXT")
    private String liste_patients;

    public Generaliste() {
        super();
    }

    public Generaliste(String liste_patients) {
//        this.id = id;
        this.liste_patients = liste_patients;
    }

    public Generaliste(Long id, String email, String password, String nom, String prenom, Role role, LocalDateTime created_at, LocalDateTime updated_at, Long id1, String liste_patients) {
        super(id, email, password, nom, prenom, role, created_at, updated_at);
//        this.id = id1;
        this.liste_patients = liste_patients;
    }

    public String getListe_patients() {
        return liste_patients;
    }

    public void setListe_patients(String liste_patients) {
        this.liste_patients = liste_patients;
    }

    @Override
    public String toString() {
        return "Generaliste{" +
//                "id='" + id + '\'' +
                ", liste_patients='" + liste_patients + '\'' +
                '}';
    }
}
