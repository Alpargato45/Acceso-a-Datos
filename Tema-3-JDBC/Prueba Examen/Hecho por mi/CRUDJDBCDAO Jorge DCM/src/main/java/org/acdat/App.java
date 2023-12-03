package org.acdat;

import org.acdat.jdbc.PostgreDao;
import org.acdat.vista.VistaCliente;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Jorge del Cid Moreno
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        PostgreDao postgreDao = new PostgreDao();

        postgreDao.crearTabla();

        VistaCliente vistaCliente = new VistaCliente();
        vistaCliente.crudCliente();
    }
}
