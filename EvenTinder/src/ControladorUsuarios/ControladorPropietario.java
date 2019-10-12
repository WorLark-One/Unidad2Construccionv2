package ControladorUsuarios;

import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionEventos.Evento;
import ModuloGestionPropiedades.GestionDePropiedad;
import ModuloGestionPropiedades.Propiedad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 */
public class ControladorPropietario {
    private ControlDeAcceso  controlAcceso;
    private GestionDePropiedad gestorPropiedades;

    /**
     * Constructor del Controlador de Propietarios.
     * Al crearse, actualiza los datos en memoria, consultando a la base de datos si existen Propiedades asociadas al Propietario que acaba de ingresar al sistema.
     * @throws SQLException 
     */
    public ControladorPropietario() throws SQLException {
        this.controlAcceso = ControlDeAcceso.getIntancia();
        this.gestorPropiedades = new GestionDePropiedad();
        this.gestorPropiedades.obtenerInformacionDePropiedades(controlAcceso.getRut());
    }

    /**
     * Metodo que pide y retorna la lista de las Propiedades existentes.
     * @return El arreglo de Propiedades obtenidas.
     */
    public ArrayList<Propiedad> mostrarInformacionDePropiedades() {
        return this.gestorPropiedades.mostrarListaDePropiedades();
    }

    /**
     * Metodo que retorna la informacion de una Propiedad especifica.
     * @param id El id de la Propiedad de la cual se desea obtener su informacion.
     * @return La Propiedad solicitada.
     */
    public Propiedad mostrarInformaciónPropiedad( int id) {
        return this.gestorPropiedades.mostrarPropiedad(id);        
    }

   /**
     * Metodo que registra una Propiedad.
     * @param nombre El nombre de la nueva Propiedad.
     * @param ubicacion La ubicacion de la nueva Propiedad.
     * @param fechaDePublicacion La fecha de publicacion de la Propiedad.
     * @param capacidadTotal La capacidad total de la Propiedad a crear.
     * @param valorDeArriendo El valor del arriendo asociado a la Propiedad.
     * @param descripcion La descripcion asociada a la Propiedad.
     * @return EL id de la Propiedad creada. -1 si la propiedad no se registro con exito.
     * @throws java.sql.SQLException
     */
    public int registrarPropiedad( String nombre,  String ubicacion,  Date fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) throws SQLException {
        return this.gestorPropiedades.registrarPropiedad(this.controlAcceso.getRut(), nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
    }

    /**
     * Metodo que modifica los datos de una Propiedad existente.
     * @param id El id de la Propiedad a modificar.
     * @param nombre El nuevo nombre de la Propiedad.
     * @param ubicacion La nueva ubicacion de la Propiedad.
     * @param fechaDePublicacion La nueva fecha de publicacion de la Propiedad.
     * @param capacidadTotal La nueva capacidad total de la Propiedad.
     * @param valorDeArriendo El nuevo valor de arriendo asociado a la Propiedad.
     * @param descripcion La nueva descripcion de la Propiedad a modificar.
     * @return True si se modifico la propiedad con exito. False si la modificacion fallo.
     * @throws java.sql.SQLException
     */
    public boolean modifcarPropiedad( int id,  String nombre,  String ubicacion,  Date fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) throws SQLException {
        return this.gestorPropiedades.modifcarPropiedad(id, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
    }

    /**
     * Metodo que elimina una propiedad existente.
     * @param id El id de la Propiedad que se desea borrar.
     * @return True si se elimino la Propiedad con exito. False si no se pudo borrar la Propiedad.
     * @throws java.sql.SQLException
     */
    public boolean eliminarPropiedad( int id) throws SQLException {
        return this.gestorPropiedades.eliminarPropiedad(id);
    }

    /**
     * Metodo que añade un Sector a una Propiedad ya existente.
     * @param id El id de la Propiedad a la cual se desea añadir un Sector.
     * @param capacidad La capacidad del sector a crear.
     * @param nombre El nombre del nuevo sector a crear.
     * @return True si el Sector se creo con exito. False si no se pudo crear el Sector.
     * @throws java.sql.SQLException
     */
    public boolean añadirSector( int id,  int capacidad,  String nombre) throws SQLException {
        return this.gestorPropiedades.añadirSector(id, capacidad, nombre);
    }

    /**
     * Metodo que modifica los datos de un Sector existente asociado a una Propiedad especifica.
     * @param id El id de la Propiedad a la cual pertenece el Sector.
     * @param nombreActual El nombre actual del Sector que se desea modificar.
     * @param capacidad La nueva capacidad del Sector.
     * @param nombre El nuevo nombre del Sector.
     * @return True si el Sector se modifico con exito. False si no se pudo modificar el sector.
     * @throws java.sql.SQLException
     */
    public boolean modificarSector( int id,String nombreActual,int capacidad,  String nombre) throws SQLException {
        return this.gestorPropiedades.modificarSector(id, nombreActual, capacidad, nombre);
    }

    /**
     * Metodo que elimina un Sector ya existente asociado a una Propiedad.
     * @param id El id de la Propiedad a la cual esta asociada el Sector.
     * @param nombreActual El nombre actual del Sector.
     * @return True si se elimino el Sector con exito. False si no se pudo eliminar el Sector.
     * @throws java.sql.SQLException
     */
    public boolean eliminarSector( int id, String nombreActual) throws SQLException {
        return this.gestorPropiedades.eliminarSector(id, nombreActual);
    }

    /**
     * @param idEvento 
     * @return
     */
    public boolean aceptarSolicitud(int idEvento) {
        return this.gestorPropiedades.aceptarSolicitud(idEvento);
    }

    /**
     * @param idEvento 
     * @return
     */
    public boolean rechazarSolicitud( int idEvento) {
        return this.gestorPropiedades.rechazarSolicitud(idEvento);
    }

    /**
     * @return
     */
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventos() {        
        return this.gestorPropiedades.obtenerInformacionSolicitudesDeEventos(this.controlAcceso.getRut());
    }
    
    
    /**
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosActuales() {
        return this.gestorPropiedades.obtenerInformacionDeEventosActuales(this.controlAcceso.getRut());
    }

    /**
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizados() {
        return this.gestorPropiedades.obtenerInformacionDeEventosFinalizados(this.controlAcceso.getRut());
    }
}