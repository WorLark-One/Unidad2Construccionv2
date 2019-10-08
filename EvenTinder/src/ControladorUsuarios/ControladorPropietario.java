package ControladorUsuarios;

import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionPropiedades.GestionDePropiedad;
import ModuloGestionPropiedades.Propiedad;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 */
public class ControladorPropietario {
    private ControlDeAcceso  controlAcceso;
    private GestionDePropiedad gestorPropiedades;

    public ControladorPropietario() {
        this.controlAcceso = ControlDeAcceso.getIntancia();
        this.gestorPropiedades = new GestionDePropiedad();
        //this.gestorPropiedades.obtenerInformacionDePropiedades(controlAcceso.getRut());
    }

    /**
     * Implementacion en proximo incremento
     */
    public void aceptarSolicitud() {
        // TODO implement here
    }

    /**
     * Implementacion en proximo incremento
     */
    public void rechazarSolicitud() {
        // TODO implement here
    }

    /**
     * @return
     */
    public ArrayList<Propiedad> mostrarInformacionDePropiedades() {
        return this.gestorPropiedades.mostrarListaDePropiedades();
    }

    /**
     * @param id 
     * @return
     */
    public Propiedad mostrarInformaciónPropiedad( int id) {
        return this.gestorPropiedades.mostrarPropiedad(id);        
    }

    /**
     * @param nombre 
     * @param ubicacion 
     * @param fechaDePublicacion 
     * @param capacidadTotal 
     * @param valorDeArriendo 
     * @param descripcion 
     * @return
     */
    public int registrarPropiedad( String nombre,  String ubicacion,  Date fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) {
        return this.gestorPropiedades.registrarPropiedad(this.controlAcceso.getRut(), nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
    }

    /**
     * @param id 
     * @param nombre 
     * @param ubicacion 
     * @param fechaDePublicacion 
     * @param capacidadTotal 
     * @param valorDeArriendo 
     * @param descripcion 
     * @return
     */
    public boolean modifcarPropiedad( int id,  String nombre,  String ubicacion,  Date fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) {
        return this.gestorPropiedades.modifcarPropiedad(id, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
    }

    /**
     * @param id 
     * @return
     */
    public boolean eliminarPropiedad( int id) {
        return this.gestorPropiedades.eliminarPropiedad(id);
    }

    /**
     * @param id 
     * @param capacidad 
     * @param nombre 
     * @return
     */
    public boolean añadirSector( int id,  int capacidad,  String nombre) {
        return this.gestorPropiedades.añadirSector(id, capacidad, nombre);
    }

    /**
     * @param id 
     * @param nombreActual 
     * @param capacidad 
     * @param nombre 
     * @return
     */
    public boolean modificarSector( int id,String nombreActual,int capacidad,  String nombre) {
        return this.gestorPropiedades.modificarSector(id, nombreActual, capacidad, nombre);
    }

    /**
     * @param id 
     * @param nombreActual 
     * @return
     */
    public boolean eliminarSector( int id, String nombreActual) {
        return this.gestorPropiedades.eliminarSector(id, nombreActual);
    }

}