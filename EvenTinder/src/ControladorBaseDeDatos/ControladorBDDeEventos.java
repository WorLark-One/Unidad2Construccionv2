package ControladorBaseDeDatos;

import ModuloGestionEventos.Evento;
import java.util.*;

/**
 * 
 */
public class ControladorBDDeEventos {

    /**
     * Default constructor
     */
    public ControladorBDDeEventos() {
    }


    /**

     * @return
     */
    public boolean crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad, String rutOrganizador) {
        // TODO implement here
        return false;
    }

    /*
     * @return
     */
    public boolean modificarEvento(int idEvento, String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {
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
     * @param String rut 
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeTodosLosEventosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param String rut 
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosPublicadosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param String rut 
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosNoPublicadosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param String rut 
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param int idEvento 
     * @return
     */
    public boolean aceptarSolicitudPropietario(int idEvento) {
        // TODO implement here
        return false;
    }

    /**
     * @param String rutPropietario 
     * @return
     */
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventosPropietario(String rutPropietario) {
        // TODO implement here
        return null;
    }

    /**
     * @param String rutPropietario 
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosActualesPropietario(String rutPropietario) {
        // TODO implement here
        return null;
    }

    /**
     * @param String rutPropietario 
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosPropietario(String rutPropietario) {
        // TODO implement here
        return null;
    }

}