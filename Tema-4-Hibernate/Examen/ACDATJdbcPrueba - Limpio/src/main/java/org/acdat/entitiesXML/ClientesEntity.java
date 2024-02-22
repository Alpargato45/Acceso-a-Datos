package org.acdat.entitiesXML;

public class ClientesEntity {
    private int clienteId;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientesEntity that = (ClientesEntity) o;

        if (clienteId != that.clienteId) return false;
        if (nombreCliente != null ? !nombreCliente.equals(that.nombreCliente) : that.nombreCliente != null)
            return false;
        if (correoCliente != null ? !correoCliente.equals(that.correoCliente) : that.correoCliente != null)
            return false;
        if (telefonoCliente != null ? !telefonoCliente.equals(that.telefonoCliente) : that.telefonoCliente != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clienteId;
        result = 31 * result + (nombreCliente != null ? nombreCliente.hashCode() : 0);
        result = 31 * result + (correoCliente != null ? correoCliente.hashCode() : 0);
        result = 31 * result + (telefonoCliente != null ? telefonoCliente.hashCode() : 0);
        return result;
    }
}
