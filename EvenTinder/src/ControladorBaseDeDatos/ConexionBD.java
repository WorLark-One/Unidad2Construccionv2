package ControladorBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para conectar la base de datos y java. modo de uso. 
 * 1.se crea una nueva instancia, solo una vez. 
 * 2.se crea la conexion con la base de datos.
 * 3.se obtienen la clase de datos.
 * 4.cada metodo que esta en esta clase, nececita una instaciona de conexion "activa"
 * por lo cual es necesario que se le pase como parametro.
 */
public class ConexionBD {

    private Connection conexion = null;

    public Connection getConexion() {
        return conexion;
    }

    /**
     * Crea la conexion con la base de datos, recibe como parametro el nombre de
     * la base de datos y contraseña que tienes como usuario de postgres.
     *
     * @param nombreBD: nombre de la base de datos.
     * @param contraseña: contraseña de la base de datos que tiene en postgres.
     * @return true si se realiza la conexion, false de lo contrario
     */
    public boolean crearConexion(String nombreBD, String contraseña) {
        String url = "jdbc:postgresql://localhost:5432/" + nombreBD + "";// la url incluye el puerto y nombre del proyecto
        String password = "" + contraseña + "";// contraseña de postgres
        String usuario = "postgres";
        try {
            Class.forName("org.postgresql.Driver");

            conexion = DriverManager.getConnection(url, usuario, password);

            if (conexion != null) {
                System.out.println("CONEXION EXITOSA: crearConexion().");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA CONEXION: crearConexion() : " + ex);
        } catch (ClassNotFoundException ex) {
            //System.out.println(ex);
        }
        return false;
    }

    
    /**
     * cierra la conexion con la base de datos
     * @param conexionAux
     * @throws SQLException 
     */
    public void cerrarBaseDeDatos(Connection conexionAux) throws SQLException {

        if (conexionAux != null) {
            conexionAux.close();

        }
    }

}
