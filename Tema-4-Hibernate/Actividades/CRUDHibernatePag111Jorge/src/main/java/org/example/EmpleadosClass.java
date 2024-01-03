package org.example;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "empleados", schema = "public", catalog = "EmpleadosInstituto")
public class EmpleadosClass {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "empno")
    private int empno;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "puesto")
    private String puesto;
    @Basic
    @Column(name = "depno")
    private Integer depno;

    @ManyToOne
    @JoinColumn(name = "depno", insertable = false, updatable = false)
    private DepartamentosClass departamentosByDepno;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Integer getDepno() {
        return depno;
    }

    public void setDepno(Integer depno) {
        this.depno = depno;
    }

    public DepartamentosClass getDepartamentosByDepno() {
        return departamentosByDepno;
    }

    public void setDepartamentosByDepno(DepartamentosClass departamentosByDepno) {
        this.departamentosByDepno = departamentosByDepno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadosClass that = (EmpleadosClass) o;
        return empno == that.empno && Objects.equals(nombre, that.nombre) && Objects.equals(puesto, that.puesto) && Objects.equals(depno, that.depno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, nombre, puesto, depno);
    }
}

