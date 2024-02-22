package org.acdat.entitiesXML;

import java.io.Serializable;

public class DestinosAgenciasEntityPK implements Serializable {
    private int destinoId;
    private int agenciaId;

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DestinosAgenciasEntityPK that = (DestinosAgenciasEntityPK) o;

        if (destinoId != that.destinoId) return false;
        if (agenciaId != that.agenciaId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = destinoId;
        result = 31 * result + agenciaId;
        return result;
    }
}
