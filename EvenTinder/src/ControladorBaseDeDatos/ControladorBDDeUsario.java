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
     * @param usuario
     * @param clave
     * @return
     */
    public boolean preguntarPorUsuario(String usuario, String clave) {
        // TODO implement here
        return false;
    }

    /**
     * devuelve una lista con la informacion de un Usuarios
     *
     * @param tipoUsuario
     * @param rut
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<String> obtenerInformacioDelCliente(String tipoUsuario, String rut) throws SQLException {
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
        Connection conexionAux = this.conexion.getConexion();
        if (tipoUsuario.equalsIgnoreCase("Cliente") == true) {
            boolean aceptado=this.conexion.añadirCliente(conexionAux, nombre, rut, correo, correo, telefono, tarjetaDeCredito);
            System.out.println("se añadio el cliente: "+aceptado);
        } else if (tipoUsuario.equalsIgnoreCase("Organizadir") == true) {
            
        } else {
            
        }

    }
  

    /**
     * @param nombre
     * @param rut
     * @param clave
     * @param correo
     * @param tarjetaDeCredito
     */
    public void modificarUsuario(String nombre, String rut, String clave, String correo, String tarjetaDeCredito) {
        // TODO implement here

    }

    /**
     * @param rut
     */
    public void eliminarUsuario(String rut) {
        // TODO implement here
    }

}
