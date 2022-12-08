package fr.kp.apiresthibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "Classification", schema = "dbo", catalog = "CRKF")
@NamedQuery(name = "classification.getAll", query = "SELECT c FROM ClassificationEntity c ORDER BY c.idClassification")
public class ClassificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_classification")
    private int idClassification;
    @Basic
    @Column(name = "Classification")
    private String classification;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "idClassification")
    private List<FamilleEntity> familles;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassificationEntity that = (ClassificationEntity) o;

        if (idClassification != that.idClassification) return false;
        return Objects.equals(classification, that.classification);
    }

    @Override
    public int hashCode() {
        return 31 * idClassification + (classification != null ? classification.hashCode() : 0);
    }
}
