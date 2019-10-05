package ControladorUsuarios;

import ModuloGestionPropiedades.Propiedad;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 */
public class ControladorPropietario {

    public ControladorPropietario() {
    }



    /**
     * 
     */
    public void aceptarSolicitud() {
        // TODO implement here
    }

    /**
     * 
     */
    public void rechazarSolicitud() {
        // TODO implement here
    }

    /**
     * @return
     */
    public ArrayList<Propiedad> mostrarInformacionDePropiedades() {
        // TODO implement here
        return null;
    }

    /**
     * @param int id 
     * @return
     */
    public Propiedad mostrarInformaciónPropiedad( int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param String nombre 
     * @param String ubicacion 
     * @param String fechaDePublicacion 
     * @param int capacidadTotal 
     * @param int valorDeArriendo 
     * @param String descripcion 
     * @return
     */
    public boolean registrarPropiedad( String nombre,  String ubicacion,  Date fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) {
        // TODO implement here
        return false;
    }

    /**
     * @param int id 
     * @param String nombre 
     * @param String ubicacion 
     * @param String fechaDePublicacion 
     * @param int capacidadTotal 
     * @param int valorDeArriendo 
     * @param String descripcion 
     * @return
     */
    public boolean modifcarPropiedad( int id,  String nombre,  String ubicacion,  Date fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) {
        // TODO implement here
        return false;
    }

    /**
     * @param int id 
     * @return
     */
    public boolean eliminarPropiedad( int id) {
        // TODO implement here
        return false;
    }

    /**
     * @param int id 
     * @param int capacidad 
     * @param String nombre 
     * @return
     */
    public boolean añadirSector( int id,  int capacidad,  String nombre) {
        // TODO implement here
        return false;
    }

    /**
     * @param int id 
     * @param int capacidad 
     * @param String nombre 
     * @return
     */
    public boolean modificarSector( int id,  int capacidad,  String nombre) {
        // TODO implement here
        return false;
    }

    /**
     * @param int id 
     * @return
     */
    public boolean eliminarSector( int id) {
        // TODO implement here
        return false;
    }

}