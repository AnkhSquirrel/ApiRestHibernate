package fr.kp.apiresthibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "Famille", schema = "dbo", catalog = "CRKF")
@NamedQuery(name = "famille.getAll", query = "SELECT f FROM FamilleEntity f ORDER BY f.idFamille")
public class FamilleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_famille")
    private int idFamille;

    @Basic
    @Column(name = "Famille")
    private String famille;

    @Basic
    @Column(name = "id_classification")
    private int idClassification;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamilleEntity that = (FamilleEntity) o;

        if (idFamille != that.idFamille) return false;
        return Objects.equals(famille, that.famille);
    }

    @Override
    public int hashCode() {
        return 31 * idFamille + (famille != null ? famille.hashCode() : 0);
    }
}
