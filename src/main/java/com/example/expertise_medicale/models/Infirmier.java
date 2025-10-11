package com.example.expertise_medicale.models;

import jakarta.persistence.*;

@Entity
@Table(name = "infirmiers")
@PrimaryKeyJoinColumn(name = "id")
public class Infirmier extends User {

    @Column(columnDefinition = "TEXT")
    private String liste_patients;

    public Infirmier() { super(); }

    public Infirmier(String liste_patients) { this.liste_patients = liste_patients; }

    public String getListe_patients() {
        return liste_patients;
    }

    public void setListe_patients(String liste_patients) {
        this.liste_patients = liste_patients;
    }


    @Override
    public String toString() {
        return "Infirmier{" +
                ", liste_patients='" + liste_patients + '\'' +
                '}';
    }
}
