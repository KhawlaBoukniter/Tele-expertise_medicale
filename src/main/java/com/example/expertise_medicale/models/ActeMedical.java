package com.example.expertise_medicale.models;

import com.example.expertise_medicale.models.enums.TypeActeMedical;
import jakarta.persistence.*;

@Entity
@Table(name = "actes_medicaux")
public class ActeMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cout;

    @Enumerated(EnumType.STRING)
    private TypeActeMedical acteMedical;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    public TypeActeMedical getActeMedical() {
        return acteMedical;
    }

    public void setActeMedical(TypeActeMedical acteMedical) {
        this.acteMedical = acteMedical;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCout() {
        return cout;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public String toString() {
        return "ActeMedical{" +
                "id=" + id +
                ", cout=" + cout +
                ", consultation=" + consultation +
                '}';
    }
}
