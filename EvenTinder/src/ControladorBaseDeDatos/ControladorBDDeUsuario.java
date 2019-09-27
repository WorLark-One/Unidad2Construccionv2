package ControladorBaseDeDatos;

import java.util.*;

/**
 * 
 */
public class ControladorBDDeUsuario {

    /**
     * Default constructor
     */
    public ControladorBDDeUsuario() {
    }

    /**
     * @param  usuario 
     * @param  clave 
     * @return
     */
    public boolean preguntarPorUsuario( String usuario,  String clave) {
        // TODO implement here
        return false;
    }

    /**
     * @param  rut 
     * @return
     */
    public ArrayList<String>  obtenerInformacioDelUsuario( String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param  nombre 
     * @param  rut 
     * @param  clave 
     * @param  correo 
     * @param  tarjetaDeCredito 
     */
    public void añadirUsuario( String nombre,  String rut,  String clave,  String correo,  String tarjetaDeCredito) {
        // TODO implement here

    }

    /**
     * @param  nombre 
     * @param  rut 
     * @param  clave 
     * @param  correo 
     * @param  tarjetaDeCredito 
     */
    public void modificarUsuario( String nombre,  String rut,  String clave,  String correo,  String tarjetaDeCredito) {
        // TODO implement here

    }

    /**
     * @param  rut 
     */
    public void eliminarUsuario( String rut) {
        // TODO implement here
    }

}