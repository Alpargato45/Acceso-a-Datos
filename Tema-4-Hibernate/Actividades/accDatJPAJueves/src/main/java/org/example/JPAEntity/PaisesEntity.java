package org.example.JPAEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "Paises", schema = "public", catalog = "ACDATJPA")
public class PaisesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPais", nullable = false)
    private int idPais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaisesEntity that = (PaisesEntity) o;

        if (idPais != that.idPais) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPais;
        return result;
    }
}
