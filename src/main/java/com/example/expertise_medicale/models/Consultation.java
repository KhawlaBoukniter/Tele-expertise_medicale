package com.example.expertise_medicale.models;

import com.example.expertise_medicale.models.enums.StatutConsultation;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date_consultation;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    private DossierMedical dossier;

    @ManyToOne
    @JoinColumn(name = "generaliste_id", nullable = false)
    private Generaliste generaliste;

    @Column(columnDefinition = "TEXT")
    private String symptomes;

    @Column(columnDefinition = "TEXT")
    private String diagnostic;

    @Column(columnDefinition = "TEXT")
    private String traitement_prescrit;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Column(columnDefinition = "TEXT")
    private Double cout;

    @Enumerated(EnumType.STRING)
    private StatutConsultation status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(LocalDateTime date_consultation) {
        this.date_consultation = date_consultation;
    }

    public DossierMedical getDossier() {
        return dossier;
    }

    public void setDossier(DossierMedical dossier) {
        this.dossier = dossier;
    }

    public Generaliste getGeneraliste() {
        return generaliste;
    }

    public void setGeneraliste(Generaliste generaliste) {
        this.generaliste = generaliste;
    }

    public String getSymptomes() {
        return symptomes;
    }

    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTraitement_prescrit() {
        return traitement_prescrit;
    }

    public void setTraitement_prescrit(String traitement_prescrit) {
        this.traitement_prescrit = traitement_prescrit;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Double getCout() {
        return cout;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }

    public StatutConsultation getStatus() {
        return status;
    }

    public void setStatus(StatutConsultation status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", date_consultation=" + date_consultation +
                ", dossier=" + dossier +
                ", generaliste=" + generaliste +
                ", symptomes='" + symptomes + '\'' +
                ", diagnostic='" + diagnostic + '\'' +
                ", traitement_prescrit='" + traitement_prescrit + '\'' +
                ", observations='" + observations + '\'' +
                ", cout=" + cout +
                ", status=" + status +
                '}';
    }
}
