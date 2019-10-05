package ControladorBaseDeDatos;

import ModuloGestionPropiedades.Propiedad;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class ControladorBDDePropiedades {

    /**
     * Default constructor
     */
    public ControladorBDDePropiedades() {
    }


    /**
     * @param String rut 
     * @return
     */
    public ArrayList<Propiedad> obtenerInformacionDePropiedades(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param String rut 
     * @param String nombre 
     * @param String ubicacion 
     * @param String fechaDePublicacion 
     * @param int capacidadTotal 
     * @param int valorDeArriendo 
     * @param String descripcion 
     * @return
     */
    public boolean registrarPropiedad(String rut, String nombre,  String ubicacion,  String fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) {
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
    public boolean modifcarPropiedad( int id,  String nombre,  String ubicacion,  String fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) {
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