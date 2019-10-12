package ControladorUsuarios;

import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionEventos.Evento;
import ModuloGestionEventos.GestionDeEvento;
import ModuloGestionPropiedades.Propiedad;
import ModuloGestionUsuario.Propietario;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class ControladorOrganizador {
    
    private ControlDeAcceso controlAcceso;
    private GestionDeEvento gestorEventos;

    /**
     * Default constructor
     */
    public ControladorOrganizador() {
        this.controlAcceso = ControlDeAcceso.getIntancia();
        this.gestorEventos = new GestionDeEvento();        
    }

    /**
     * Metodo que crea un nuevo Evento asociado al Organizador correspondiente.
     * 
     * @param nombre El nombre del evento a crear.
     * @param descripcion La descripcion asociada al nuevo evento.
     * @param fechaDeInicio La fecha de Inicio del Evento.
     * @param fechaDeTermino La fecha de Termino del Evento
     * @param capacidad La capacidad maxima de asistentes del Evento.
     * @param diasMaximoDevolucion Los dias maximos que tiene un Cliente despues 
     * @param publicado 
     * @param idPropiedad El ID de la propiedad a la cual estara asociado el evento.
     * @return
     */
    public boolean crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {       
        return this.gestorEventos.crearEvento(nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad);
    }

    /**
     * Metodo que permite modificar los parametros de un Evento especifico, que pertenezca al Organizador 
     * que esta ingresado en el sistema actualmente.
     * 
     * @param idEvento El ID del evento que se desea modificar.
     * @param nombre El nuevo nombre del Evento a modificar.
     * @param descripcion La nueva descripcion del Evento a modificar.
     * @param fechaDeInicio La nueva fecha de Inicio del Evento a modificar.
     * @param fechaDeTermino La neuva fecha de Termino del Evento a modificar.
     * @param capacidad La nueva capacidad maxima de asistentes del Evento a modificar.
     * @param diasMaximoDevolucion 
     * @param publicado 
     * @return
     */
    public boolean modificarEvento(int idEvento,  String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado) {
        return this.gestorEventos.modificarEvento(idEvento, nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado);
    }

    /**
     * Metodo que permite Eliminar un Evento del sistema, que este asociado al Organizador que esta actualmente
     *   ingresado en el sistema.
     * @param idEvento EL ID del evento que se desea eliminar.
     * @return True si el Evento fue eliminado con exito. False si el Evento no pudo eliminarse.
     */
    public boolean eliminarEvento(int idEvento) {        
        return this.gestorEventos.eliminarEvento(idEvento);
    }

    /**
     * Metodo que retorna todos los Eventos que estan asociados al Organizador actualmente ingresado en el sistema.
     * 
     * @return El arreglo con todos los Eventos que estan asociados al Organizador actualmente ingresado en el sistema.
     */
     public ArrayList<Evento> obtenerInformacionDeTodosLosEventosDeUnOrganizador() {
        return this.gestorEventos.obtenerInformacion(this.controlAcceso.getRut(), "Todos");       
    }

    /**
     * Metodo que retorna todos los Eventos Publicados que estan asociados al Organizador actualmente ingresado en el sistema.
     * 
     * @return El arreglo con todos los Eventos Publicados que estan asociados al Organizador actualmente ingresado en el sistema.
     */
    public ArrayList<Evento> obtenerInformacionDeEventosPublicadosDeUnOrganizador() {
        return this.gestorEventos.obtenerInformacion(this.controlAcceso.getRut(), "Publicados");
    }

    /**
     * Metodo que retorna todos los Eventos No Publicados que estan asociados al Organizador actualmente ingresado en el sistema.
     * 
     * @return El arreglo con todos los Eventos No Publicados que estan asociados al Organizador actualmente ingresado en el sistema.
     */
    public ArrayList<Evento> obtenerInformacionDeEventosNoPublicadosDeUnOrganizador() {
        return this.gestorEventos.obtenerInformacion(this.controlAcceso.getRut(),"No Publicados");
    }

    /**
     * Metodo que retorna todos los Eventos Finalizados que estan asociados al Organizador actualmente ingresado en el sistema.
     * 
     * @return El arreglo con todos los Eventos Finalizados que estan asociados al Organizador actualmente ingresado en el sistema.
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosDeUnOrganizador() {
        return this.gestorEventos.obtenerInformacion(this.controlAcceso.getRut(),"Finalizados");
    }
    
    public ArrayList<Propiedad> obtenerInformacionPropiedades(){
        return this.gestorEventos.obtenerInformacionPropiedades();
    }

    /**
     * Iteracion 4 
     * Proximamente
     * @param id 
     * @return
     */
    public ArrayList<String> obtenerInformacionDeEstadisticasDeVentasPorEventos(int id) {
        // TODO implement here
        return null;
    }

}