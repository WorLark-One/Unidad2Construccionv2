package ModuloGestionEventos;

import java.util.*;

/**
 * 
 */
public class GestionDeEvento {

    /**
     * Default constructor
     */
    public GestionDeEvento() {
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
     * @param int idEvento 
     * @return
     */
    public boolean aceptarSolicitud(int idEvento) {
        // TODO implement here
        return false;
    }

    /**
     * @param int idEvento 
     * @return
     */
    public boolean rechazarSolicitud(int idEvento) {
        // TODO implement here
        return false;
    }

    /**
     * @param String rut 
     * @param String opcion 
     * @return
     */
    public ArrayList<Evento> obtenerInformacion(String rut, String opcion) {
        // TODO implement here
        return null;
    }
}