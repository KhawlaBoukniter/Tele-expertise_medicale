package com.example.expertise_medicale.models;

import jakarta.persistence.*;

@Entity
@Table(name = "infirmiers")
public class Infirmier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String liste_patients;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListe_patients() {
        return liste_patients;
    }

    public void setListe_patients(String liste_patients) {
        this.liste_patients = liste_patients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Infirmier{" +
                "id=" + id +
                ", liste_patients='" + liste_patients + '\'' +
                ", user=" + user +
                '}';
    }
}
