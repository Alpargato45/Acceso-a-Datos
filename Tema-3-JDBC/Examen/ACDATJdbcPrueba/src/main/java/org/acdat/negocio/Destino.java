package org.acdat.negocio;

public class Destino {
    protected int id;
    protected String destino;
    protected String descripcion;
    protected double coste;

    public Destino(int id, String destino, String descripcion, double coste) {
        this.id = id;
        this.destino = destino;
        this.descripcion = descripcion;
        this.coste = coste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destino)) return false;

        Destino destino1 = (Destino) o;

        if (getId() != destino1.getId()) return false;
        if (Double.compare(getCoste(), destino1.getCoste()) != 0) return false;
        if (!getDestino().equals(destino1.getDestino())) return false;
        return getDescripcion().equals(destino1.getDescripcion());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getDestino().hashCode();
        result = 31 * result + getDescripcion().hashCode();
        temp = Double.doubleToLongBits(getCoste());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Destino{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", coste=" + coste +
                '}';
    }
}
