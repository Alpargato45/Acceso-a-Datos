package org.acdat.entitiesXML;

public class AgenciasEntity {
    private int agenciaId;
    private String nombreAgencia;
    private String direccionAgencia;
    private String telefonoAgencia;

    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public String getDireccionAgencia() {
        return direccionAgencia;
    }

    public void setDireccionAgencia(String direccionAgencia) {
        this.direccionAgencia = direccionAgencia;
    }

    public String getTelefonoAgencia() {
        return telefonoAgencia;
    }

    public void setTelefonoAgencia(String telefonoAgencia) {
        this.telefonoAgencia = telefonoAgencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgenciasEntity that = (AgenciasEntity) o;

        if (agenciaId != that.agenciaId) return false;
        if (nombreAgencia != null ? !nombreAgencia.equals(that.nombreAgencia) : that.nombreAgencia != null)
            return false;
        if (direccionAgencia != null ? !direccionAgencia.equals(that.direccionAgencia) : that.direccionAgencia != null)
            return false;
        if (telefonoAgencia != null ? !telefonoAgencia.equals(that.telefonoAgencia) : that.telefonoAgencia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = agenciaId;
        result = 31 * result + (nombreAgencia != null ? nombreAgencia.hashCode() : 0);
        result = 31 * result + (direccionAgencia != null ? direccionAgencia.hashCode() : 0);
        result = 31 * result + (telefonoAgencia != null ? telefonoAgencia.hashCode() : 0);
        return result;
    }
}
