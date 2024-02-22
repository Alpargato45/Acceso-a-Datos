package org.example.JPAEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "Persona", schema = "public", catalog = "ACDATJPA")
public class PersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPersona", nullable = false)
    private int idPersona;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return idPersona == that.idPersona;
    }

    @Override
    public int hashCode() {
        int result = idPersona;
        return result;
    }
}
