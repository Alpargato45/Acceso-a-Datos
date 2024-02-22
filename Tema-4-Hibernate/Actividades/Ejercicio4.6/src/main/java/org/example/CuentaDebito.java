package org.example;

public class CuentaDebito extends Cuenta {

    private double cargoPorDescubierto;

    public double getCargoPorDescubierto() {
        return cargoPorDescubierto;
    }

    public void setCargoPorDescubierto(double cargoPorDescubierto) {
        this.cargoPorDescubierto = cargoPorDescubierto;
    }
}
