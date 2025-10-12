package com.example.expertise_medicale.models;

import com.example.expertise_medicale.models.enums.PatientStatus;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    private String id;

    private String nom;
    private String prenom;

    @Column(columnDefinition = "TEXT")
    private String coordonnees;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'EN_ATTENTE'")
    private PatientStatus status;

    private Boolean mutuelle;
    private String numero_securite_sociale;
    private LocalDateTime dateArrivee;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<SigneVital> signesVitaux;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Boolean getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Boolean mutuelle) {
        this.mutuelle = mutuelle;
    }

    public String getNumero_securite_sociale() {
        return numero_securite_sociale;
    }

    public void setNumero_securite_sociale(String numero_securite_sociale) {
        this.numero_securite_sociale = numero_securite_sociale;
    }

    public LocalDateTime getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDateTime dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public List<SigneVital> getSignesVitaux() { return signesVitaux; }

    public void setSignesVitaux(List<SigneVital> signesVitaux) { this.signesVitaux = signesVitaux; }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", statut='" + status + '\'' +
                ", coordonnees='" + coordonnees + '\'' +
                ", mutuelle=" + mutuelle +
                ", numero_securite_sociale='" + numero_securite_sociale + '\'' +
                ", dateArrivee=" + dateArrivee +
                '}';
    }
}
