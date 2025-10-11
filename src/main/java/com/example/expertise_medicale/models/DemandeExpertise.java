package com.example.expertise_medicale.models;

import com.example.expertise_medicale.models.enums.Priorite;
import com.example.expertise_medicale.models.enums.StatutDemande;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demandes_expertise")
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date_demande;
    private LocalDateTime date_reponse;
    private String question;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Column(columnDefinition = "TEXT")
    private String avis;

    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    @OneToOne
    @JoinColumn(name = "consultation_id", nullable = false, unique = true)
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    @ManyToOne
    @JoinColumn(name = "creneau_id")
    private Creneau creneau;

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate_demande() {
        return date_demande;
    }

    public void setDate_demande(LocalDateTime date_demande) {
        this.date_demande = date_demande;
    }

    public LocalDateTime getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(LocalDateTime date_reponse) {
        this.date_reponse = date_reponse;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public StatutDemande getStatut() {
        return statut;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public void setSpecialiste(Specialiste specialiste) {
        this.specialiste = specialiste;
    }

    @Override
    public String toString() {
        return "DemandeExpertise{" +
                "id=" + id +
                ", date_demande=" + date_demande +
                ", date_reponse=" + date_reponse +
                ", question='" + question + '\'' +
                ", priorite=" + priorite +
                ", creneau=" + creneau +
                ", avis='" + avis + '\'' +
                ", statut=" + statut +
                ", consultation=" + consultation +
                ", specialiste=" + specialiste +
                '}';
    }

}
