package com.acdat.SpringbootLunes.demo.jpa;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "EmpleadosInstituto")
public class DepartamentosEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "depno")
    private int depno;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "ubicacion")
    private String ubicacion;

    public int getDepno() {
        return depno;
    }

    public void setDepno(int depno) {
        this.depno = depno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartamentosEntity that = (DepartamentosEntity) o;
        return depno == that.depno && Objects.equals(nombre, that.nombre) && Objects.equals(ubicacion, that.ubicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depno, nombre, ubicacion);
    }
}
