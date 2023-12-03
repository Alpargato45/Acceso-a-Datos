package org.acdat.jdbc;

import java.util.ArrayList;
import java.util.List;

public class PostgreDao {
    private MiJDBC miJDBC = new MiJDBC();
    private List<String> empleados = new ArrayList<>();

    public void crearTabla() {
        empleados.add("id SERIAL PRIMARY KEY,"
                + "nif VARCHAR(9) NOT NULL UNIQUE,"
                + "nombre VARCHAR(100) NOT NULL,"
                + "apellido1 VARCHAR(100) NOT NULL,"
                + "apellido2 VARCHAR(100),"
                + "id_departamento INT REFERENCES departamento(id)");
    }

}
