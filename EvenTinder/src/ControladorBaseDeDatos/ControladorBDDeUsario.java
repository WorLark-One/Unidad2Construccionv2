package ControladorBaseDeDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class ControladorBDDeUsario {

    ConexionBD conexion;

    /**
     * Default constructor
     */
    public ControladorBDDeUsario() {
        this.conexion = new ConexionBD();
    }

    /**
     * metodo para login.
     * @param usuario
     * @param clave
     * @return
     */
    public boolean preguntarPorUsuario(String tipoUsuario,String rut, String clave) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.ValidarInicioSecion(conexionAux,tipoUsuario, rut, clave);
    }

    /**
     * devuelve una lista con la informacion de un Usuarios
     *
     * @param tipoUsuario
     * @param rut
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<String> obtenerInformacioDeUsurio(String tipoUsuario, String rut) throws SQLException {
        ArrayList<String> informacion = null;

        this.conexion.crearConexion("EventTinder", "1");
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.mostrarInformacionUsuario(conexionAux, tipoUsuario, rut);


    }

    /**
     * añade a un usuario a la base de datos
     *
     * @param tipoUsuario
     * @param nombre
     * @param rut
     * @param clave
     * @param correo
     * @param telefono
     * @param tarjetaDeCredito
     * @param cuentaCorriente
     */
    public void añadirUsuario(String tipoUsuario, String nombre, String rut, String correo,String clave, String telefono, String tarjeta) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        aceptado=this.conexion.añadirUsuario(conexionAux, tipoUsuario, nombre, rut, correo, clave, telefono, tarjeta);
        System.out.println("se añadio el usuario correctamente? :"+aceptado);

    }
  

    /**
     * modifica un tipo de usuario.
     * @param tipoUsuario
     * @param rutUsuarioModificar
     * @param nombre
     * @param rut
     * @param clave
     * @param correo
     * @param telefono
     * @param tarjeta
     * @return true si el usuario fue modificado, false de lo contrario.
     * @throws java.sql.SQLException 
     */
    public boolean modificarUsuario(String tipoUsuario,String rutUsuarioModificar,String nombre, String rut, String clave, String correo, String telefono, String tarjeta) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.modificarDatosUsuario(conexionAux, tipoUsuario, rutUsuarioModificar, nombre, rut, correo, correo, telefono, tarjeta);
    }

    /**
     * elimina un usuario de tipo cliente, organizador o propietario de la base de 
     * datos.
     * @param tipoUsuario:cliente,organizador u propietario
     * @param rut: rut del usuario.
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean eliminarUsuario(String tipoUsuario,String rut) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.eliminarUsuario(conexionAux, tipoUsuario, rut);
    }

}
