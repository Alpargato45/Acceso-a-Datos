package org.acdat;

import org.acdat.jdbc.MiJDBC;
import org.acdat.vista.VistaCliente;
import org.acdat.vista.VistaVuelo;

import java.nio.charset.MalformedInputException;
import java.sql.SQLException;

/**
 * @author Jorge del Cid Moreno
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
        //miJDBC.IniciarTransaccion();
        //miJDBC.añadirTablas();

        //miJDBC.IniciarTransaccion();
        //miJDBC.añadirInsert();
        //Si falla es posible que sea la contraseña de la BBDD

        VistaVuelo vistaVuelo = new VistaVuelo();
        vistaVuelo.crudVuelo();
    }
}
