package com.example.expertise_medicale.models;

import com.example.expertise_medicale.models.enums.Specialite;
import jakarta.persistence.*;

@Entity
@Table(name = "specialistes")
public class Specialiste extends  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    private Double tarif;
    private Integer duree_consultation;

    @Column(columnDefinition = "TEXT")
    private String liste_demandes;

    @Column(columnDefinition = "TEXT")
    private String crenaux;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public Integer getDuree_consultation() {
        return duree_consultation;
    }

    public void setDuree_consultation(Integer duree_consultation) {
        this.duree_consultation = duree_consultation;
    }

    public String getListe_demandes() {
        return liste_demandes;
    }

    public void setListe_demandes(String liste_demandes) {
        this.liste_demandes = liste_demandes;
    }

    public String getCrenaux() {
        return crenaux;
    }

    public void setCrenaux(String crenaux) {
        this.crenaux = crenaux;
    }

    @Override
    public String toString() {
        return "Specialiste{" +
                "id=" + id +
                ", specialite=" + specialite +
                ", tarif=" + tarif +
                ", duree_consultation=" + duree_consultation +
                ", liste_demandes='" + liste_demandes + '\'' +
                ", crenaux='" + crenaux + '\'' +
                '}';
    }
}
