package com.example.expertise_medicale.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "signes_vitaux")
public class SigneVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date_mesure;
    private String tension_arterielle;
    private Double temperature_corporelle;
    private Integer frequence_cardiaque;
    private Double frequence_respiratoire;
    private Double poids;
    private Double taille;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = true)
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate_mesure() {
        return date_mesure;
    }

    public void setDate_mesure(LocalDateTime date_mesure) {
        this.date_mesure = date_mesure;
    }

    public String getTension_arterielle() {
        return tension_arterielle;
    }

    public void setTension_arterielle(String tension_arterielle) {
        this.tension_arterielle = tension_arterielle;
    }

    public Double getTemperature_corporelle() {
        return temperature_corporelle;
    }

    public void setTemperature_corporelle(Double temperature_corporelle) {
        this.temperature_corporelle = temperature_corporelle;
    }

    public Integer getFrequence_cardiaque() {
        return frequence_cardiaque;
    }

    public void setFrequence_cardiaque(Integer frequence_cardiaque) {
        this.frequence_cardiaque = frequence_cardiaque;
    }

    public Double getFrequence_respiratoire() {
        return frequence_respiratoire;
    }

    public void setFrequence_respiratoire(Double frequence_respiratoire) {
        this.frequence_respiratoire = frequence_respiratoire;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public String toString() {
        return "SigneVital{" +
                "id=" + id +
                ", date_mesure=" + date_mesure +
                ", tension_arterielle='" + tension_arterielle + '\'' +
                ", temperature_corporelle=" + temperature_corporelle +
                ", frequence_cardiaque=" + frequence_cardiaque +
                ", frequence_respiratoire=" + frequence_respiratoire +
                ", poids=" + poids +
                ", taille=" + taille +
                ", consultation=" + consultation +
                '}';
    }
}
