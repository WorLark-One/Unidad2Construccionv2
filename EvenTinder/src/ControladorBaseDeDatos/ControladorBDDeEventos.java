package ControladorBaseDeDatos;

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
     * @param rut 
     * @return
     */
    public ArrayList<String> obtenerInformacionDeSolicitudesDeEventos(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param rut 
     * @param id 
     */
    public ArrayList<String> obtenerInformacionDeUnaSolicitudDeEvento(String rut,int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param rut 
     * @param id 
     */
    public void aceptarSolicitudDeUnEvento(String rut,int id) {
        // TODO implement here
    }

    /**
     * @param rut 
     * @param id 
     */
    public void rechazarSolicitud(String rut,int id) {
        // TODO implement here
    }

    /**
     * @param rut 
     * @return
     */
    public ArrayList<String> obtenerInformacionDeTodosLosEventosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param rut 
     * @return
     */
    public ArrayList<String> obtenerInformacionDeEventosPublicadosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }


    public ArrayList<String> obtenerInformacionDeEventosNoPublicados(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * 
     * @param nombre
     * @param fechaDeInicio
     * @param fechaDeTermino
     * @param capacidad
     * @param descripcion
     * @param diasMaximoDevolucion
     * @param publicado 
     */
    public void crearEvento(String nombre,Date fechaDeInicio, Date fechaDeTermino,int capacidad, String descripcion, int diasMaximoDevolucion, boolean publicado) {
        // TODO implement here

    }
    
    /**
     * 
     * @param id
     * @param nombre
     * @param fechaDeInicio
     * @param fechaDeTermino
     * @param capacidad
     * @param descripcion
     * @param diasMaximoDevolucion
     * @param publicado 
     */
    public void modificarEvento(int id,String nombre,Date fechaDeInicio,Date fechaDeTermino,int capacidad, String descripcion,int diasMaximoDevolucion, boolean publicado) {
        // TODO implement here
  
    }

    /**
     * @param id 
     */
    public void eliminarEvento(int id) {
        // TODO implement here
    }

    /**
     * @param id 
     * @return
     */
    public ArrayList<String> obtenerInformacionDeEstadisticasDeVentasPorEventos(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @param  id 
     * @return
     */
    public ArrayList<String>  obtenerInformacionDeHistorialDeEventosPorPropiedad( int id) {
        // TODO implement here
        return null;
    }

}