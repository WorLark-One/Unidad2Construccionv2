package ControladorUsuarios;

import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionEventos.Evento;
import ModuloGestionEventos.GestionDeEvento;
import ModuloGestionPropiedades.Propiedad;
import ModuloGestionVentas.Compra;
import ModuloGestionVentas.GestionDeVenta;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class ControladorOrganizador {
    
    private ControlDeAcceso controlAcceso;
    private GestionDeEvento gestorEventos;
    private GestionDeVenta gestorVentas;

    /**
     * Default constructor
     */
    public ControladorOrganizador() {
        this.controlAcceso = ControlDeAcceso.getIntancia();
        this.gestorEventos = new GestionDeEvento();      
        this.gestorVentas = new GestionDeVenta();
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
     * @param publicado Indica si el evento esta publicado o no. Siempre comienza en False.
     * @param idPropiedad El ID de la propiedad a la cual estara asociado el evento.
     * @return El id del evento creado.
     */
    public int crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {       
        return this.gestorEventos.crearEvento(nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad,this.controlAcceso.getRut());
    }
    
    /**
     * Metodo que asigna un valor a un sector de una Propiedad, el cual sera utilizado para la compra de entradas.
     * 
     * @param idEvento El id del evento al cual esta asociado el sector seleccionado para darle un valor.
     * @param nuevoPrecio El precio que se le asignara al sector.
     * @param nombreSector El nombre del sector seleccionado.
     * @param idPropiedad EL id de la Propiedad a la cual esta asociada el sector.
     * @return True si se agrego el precio del sector correctamente. False si no se pudo agregar.
     */
    public boolean agregarPrecioSector(int idEvento, int nuevoPrecio, String nombreSector,int idPropiedad){
        return this.gestorEventos.agregarPrecioSector(idEvento,nuevoPrecio,nombreSector,idPropiedad);
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
     * @param diasMaximoDevolucion Los dias maximos que un Cliente tiene para devolver una entrada.
     * @param publicado Si el evento esta publicado o no.
     * @return True si el evento fue modificado con exito. False si no se pudo modificar.
     */
    public boolean modificarEvento(int idEvento,  String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado) {
        return this.gestorEventos.modificarEvento(idEvento, nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado);
    }
    
    /**
     * Metodo que modifica el precio asociado a un sector.
     * 
     * @param idEvento El id del evento al cual esta asociada la propiedad, y por lo tanto, el sector.
     * @param nuevoPrecio El nuevo precio que se asociara al sector.
     * @param nombreSector El nombre del sector.
     * @param idPropiedad El id de la propiedad a la cual esta asociada el sector.
     * @return True si se pudo modificar el precio del sector. False si no se pudo modificar.
     */
    public boolean modificarPrecioSector(int idEvento, int nuevoPrecio, String nombreSector,int idPropiedad){
        return this.gestorEventos.modificarPrecioSector(idEvento,nuevoPrecio,nombreSector,idPropiedad);
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
    
    /**
     * Metodo que permite obtener la informacion de todas las propiedades.
     * @return El arreglo de todas las propiedades en el sistema.
     */
    public ArrayList<Propiedad> obtenerInformacionPropiedades(){
        return this.gestorEventos.obtenerInformacionPropiedades();
    }
    
    /**
     * Metodo que permite obtener el precio de un sector especifico de una propiedad.
     * @param idEvento El id del evento al cual esta asociada la propiedad.
     * @param nombreSector El nombre del sector asociado.
     * @param idPropiedad El id de la propiedad a la cual esta asociado el sector.
     * @return el valor del sector solicitado.
     */
    public int obtenerPrecioEntradaPorSector(int idEvento, String nombreSector, int idPropiedad){
        return this.gestorEventos.obtenerPrecioEntradaPorSector(idEvento, nombreSector, idPropiedad);
    }
    
    /**
     * Metodo que retorna todas las compras realizadas asociadas a un eventos.
     * 
     * @param idEvento El id del Eventos del cual se desean obtener los datos.
     * @return Un arreglo con todas las compras asociadas al Evento.
     */
    public ArrayList<Compra> obtenerInformacionDelHistorialDeCompraDeUnEvento(int idEvento){
        return this.gestorVentas.obtenerInformacionDelHistorialDeCompraDeUnEvento(idEvento);
    }

    /**
     * Metodo que se encarga en devolver todas las compras realizadas a ese eventos
     * @param idEvento 
     */
    public void devolverTodasLasEntradasDelEvento(int idEvento) {
        ArrayList<Compra> aux = this.gestorVentas.obtenerInformacionDelHistorialDeCompraDeUnEvento(idEvento);
        for(Compra c:aux){
            this.gestorVentas.eliminarCompra(c.getId());
        }                
    }
}