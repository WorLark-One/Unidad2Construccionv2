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
    public boolean preguntarPorUsuario(String usuario, String clave) {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.ValidarInicioSecion(conexionAux, clave, clave);
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

        if (tipoUsuario.equalsIgnoreCase("Cliente") == true) {
            informacion = this.conexion.mostrarIndormacionCliente(conexionAux, rut);
            return informacion;
        } else if (tipoUsuario.equalsIgnoreCase("Organizadir") == true) {
            informacion = this.conexion.mostrarInformacionOrganizador(conexionAux, rut);
            return informacion;
        } else {
            informacion = this.conexion.mostrarInformacionPropietario(conexionAux, rut);
            return informacion;
        }

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
    public void añadirUsuario(String tipoUsuario, String nombre, String rut, String clave, String correo, String telefono, String tarjetaDeCredito, String cuentaCorriente) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        if (tipoUsuario.equalsIgnoreCase("Cliente") == true) {
            aceptado=this.conexion.añadirCliente(conexionAux, nombre, rut, correo, correo, telefono, tarjetaDeCredito);
            System.out.println("se añadio el cliente: "+aceptado);
        } else if (tipoUsuario.equalsIgnoreCase("Organizador") == true) {
            aceptado=this.conexion.añadirOrganizador(conexionAux, nombre, rut, correo, correo, telefono, tarjetaDeCredito);
            System.out.println("se añadio el organizador: "+aceptado);
        } else {
            aceptado=this.conexion.añadirPropietario(conexionAux, nombre, rut, correo, correo, telefono, cuentaCorriente);
            System.out.println("se añadio el propietario: "+aceptado);
            
        }

    }
  

    /**
     * modifica un tipo de usuario.
     * @param tipoUsuario
     * @param nombre
     * @param rut
     * @param clave
     * @param correo
     * @param telefono
     * @param tarjetaDeCredito
     * @param cuentaCorriente
     * @return true si el usuario fue modificado, false de lo contrario.
     * @throws java.sql.SQLException 
     */
    public boolean modificarUsuario(String tipoUsuario,String nombre, String rut, String clave, String correo, String telefono, String tarjetaDeCredito, String cuentaCorriente) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        aceptado = this.conexion.modificarTiposDeUsuarios(conexionAux, tipoUsuario, nombre, rut, correo, correo, telefono, tarjetaDeCredito, cuentaCorriente);
        return aceptado;
    }

    /**
     * elimina un usuario de tipo cliente, organizador o propietario de la base de 
     * datos.
     * @param tipoUsuario:cliente,organizador u propietario
     * @param rut: rut del usuario.
     * @throws java.sql.SQLException
     */
    public void eliminarUsuario(String tipoUsuario,String rut) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        if (tipoUsuario.equalsIgnoreCase("Cliente") == true) {
            aceptado=this.conexion.eliminarCliente(conexionAux, rut);
            System.out.println("se elimino el cliente: "+aceptado);
        } else if (tipoUsuario.equalsIgnoreCase("Organizadir") == true) {
            aceptado=this.conexion.eliminarOrganizador(conexionAux, rut);
            System.out.println("se elimino el organizador: "+aceptado);
        } else {
            aceptado=this.conexion.eliminarPropietario(conexionAux, rut);
            System.out.println("se elimino el propietario: "+aceptado);
            
        }
    }

}
