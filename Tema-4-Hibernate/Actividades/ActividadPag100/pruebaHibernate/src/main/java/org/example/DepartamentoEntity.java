package org.example;

import java.util.Objects;

public class DepartamentoEntity {
    private int id;
    private String nombre;
    private double presupuesto;
    private double gastos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartamentoEntity that = (DepartamentoEntity) o;
        return id == that.id && Double.compare(presupuesto, that.presupuesto) == 0 && Double.compare(gastos, that.gastos) == 0 && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, presupuesto, gastos);
    }
}
