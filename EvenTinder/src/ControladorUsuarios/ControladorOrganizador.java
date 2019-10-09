package ControladorUsuarios;

import ModuloGestionEventos.Evento;
import java.util.*;

/**
 * 
 */
public class ControladorOrganizador {

    /**
     * Default constructor
     */
    public ControladorOrganizador() {
    }



    /**
     * @param String nombre 
     * @param String descripcion 
     * @param Date fechaDeInicio 
     * @param Date fechaDeTermino 
     * @param int capacidad 
     * @param int diasMaximoDevolucion 
     * @param boolean publicado 
     * @param int idPropiedad 
     * @return
     */
    public boolean crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {
        // TODO implement here
        return false;
    }

    /**
     * @param int idEvento 
     * @param String nombre 
     * @param String descripcion 
     * @param Date fechaDeInicio 
     * @param Date fechaDeTermino 
     * @param int capacidad 
     * @param int diasMaximoDevolucion 
     * @param boolean publicado 
     * @param int idPropiedad 
     * @return
     */
    public boolean modificarEvento( int idEvento,  String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {
        // TODO implement here
        return false;
    }

    /**
     * @param int idEvento 
     * @return
     */
    public boolean eliminarEvento(int idEvento) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
     public ArrayList<Evento> obtenerInformacionDeTodosLosEventosDeUnOrganizador() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosPublicadosDeUnOrganizador() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosNoPublicadosDeUnOrganizador() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosDeUnOrganizador() {
        // TODO implement here
        return null;
    }

    /**
     * @param int id 
     * @return
     */
    public ArrayList<String> obtenerInformacionDeEstadisticasDeVentasPorEventos(int id) {
        // TODO implement here
        return null;
    }

}