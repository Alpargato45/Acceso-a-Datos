package org.example.JPAEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "Continentes", schema = "public", catalog = "ACDATJPA")
public class ContinenteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idContinente", nullable = false)
    private int idContinente;

    public int getIdContinente() {
        return idContinente;
    }

    public void setIdContinente(int idPais) {
        this.idContinente = idContinente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContinenteEntity that = (ContinenteEntity) o;

        if (idContinente != that.idContinente) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContinente;
        return result;
    }
}
