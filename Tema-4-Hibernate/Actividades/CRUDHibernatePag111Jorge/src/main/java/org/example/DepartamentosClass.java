package org.example;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "EmpleadosInstituto")
public class DepartamentosClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "depno")
    private int depno;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "ubicacion")
    private String ubicacion;
    @OneToMany(mappedBy = "departamentosByDepno")
    private Collection<EmpleadosClass> empleadosByDepno;

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
        DepartamentosClass that = (DepartamentosClass) o;
        return depno == that.depno && Objects.equals(nombre, that.nombre) && Objects.equals(ubicacion, that.ubicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depno, nombre, ubicacion);
    }

    public Collection<EmpleadosClass> getEmpleadosByDepno() {
        return empleadosByDepno;
    }

    public void setEmpleadosByDepno(Collection<EmpleadosClass> empleadosByDepno) {
        this.empleadosByDepno = empleadosByDepno;
    }
}
