package ModuloGestionPropiedades;

import ControladorBaseDeDatos.ControladorBDDePropiedades;
import ModuloGestionEventos.Evento;
import ModuloGestionEventos.GestionDeEvento;
import ModuloSeguridadExterna.Guardian;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class GestionDePropiedad {
    
    private ArrayList<Propiedad> listaPropiedades;
    private ControladorBDDePropiedades controlador;
    private GestionDeEvento gestorEventos;
    
    
    /**
     * Constructor de un Gestor de Propiedades.
     */
    public GestionDePropiedad() {
        this.listaPropiedades = new ArrayList();
        this.controlador= new ControladorBDDePropiedades();
        this.gestorEventos = new GestionDeEvento();
    }

    /**
     * Metodo que retorna la lista de todas las Propiedades que posee el Propietario hasta el momento.
     * 
     * @param rut El rut del propietario del cual se necesitan las propiedades.
     * @return La lista de las propiedades del Propietario que esta conectado al sistema.
     * @throws java.sql.SQLException
     */
    public ArrayList<Propiedad> mostrarListaDePropiedades(String rut) throws SQLException {         
        return this.controlador.obtenerInformacionDePropiedades(rut);
    }
    
    /**
     * Metodo que permite obtener la informacion de todas las propiedades registradas en el sistema.
     * @return Un arreglo con todas las propiedades que existan en el sistema.
     */
    public ArrayList<Propiedad> obtenerInformacionDeTodasLasPropiedades(){
        return this.gestorEventos.obtenerInformacionPropiedades();
    }

    /**
     * Metodo que retorna la informacion de una Propiedad especifica del Propietario que esta ingresado en el sistema.
     * @param id El id de la Propiedad que se desea obtener.
     * @return La Propiedad que se desea retornar.
     */
    public Propiedad mostrarPropiedad( int id) {
        
        for(int i = 0; i<this.listaPropiedades.size();i++){
            if(this.listaPropiedades.get(i).getId() == id){
                return this.listaPropiedades.get(i);
            }
        }
        return null;
    }


    /**
     * Metodo para pedirle a la base de datos todos los datos asociados al Propietario que ingresa en el sistema.
     *  De esta forma, se tendra toda la informacion del propietario en memoria.
     * @param rut El rut del Propietario que ingresa al sistema.
     * @throws java.sql.SQLException
     */
    public void obtenerInformacionDePropiedades(String rut) throws SQLException {
        this.listaPropiedades = this.controlador.obtenerInformacionDePropiedades(rut);
    }
    

    /**
     * Metodo que, mediante la conexion a la base de datos, registra una Propiedad.
     * @param rut El rut del Propietario ingresado en el sistema.
     * @param nombre El nombre de la nueva Propiedad.
     * @param ubicacion La ubicacion de la nueva Propiedad.
     * @param fechaDePublicacion La fecha de publicacion de la Propiedad.
     * @param capacidadTotal La capacidad total de la Propiedad a crear.
     * @param valorDeArriendo El valor del arriendo asociado a la Propiedad.
     * @param descripcion La descripcion asociada a la Propiedad.
     * @return EL id de la Propiedad creada. -1 si la propiedad no se registro con exito.
     * @throws java.sql.SQLException
     */
    public int registrarPropiedad( String rut,  String nombre,  String descripcion,  Date fechaDePublicacion, String ubicacion,  int capacidadTotal,  int valorDeArriendo) throws SQLException {
        // TODO implement here
        int i = this.controlador.registrarPropiedad(rut, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        if(i>0){
            Propiedad p = new Propiedad(i, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
            this.listaPropiedades.add(p);
            return i;
        }
        return -1;
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
    public boolean modifcarPropiedad( int id,  String nombre,  String descripcion,  Date fechaDePublicacion, String ubicacion,  int capacidadTotal,  int valorDeArriendo,String rutPropietario) throws SQLException {
        boolean result = this.controlador.modifcarPropiedad(id, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion,rutPropietario);
        if(result){
            for(Propiedad p:this.listaPropiedades){
                if(p.getId() == id){
                    p.setNombre(nombre);
                    p.setDescripcion(descripcion);
                    p.setFechaDePublicacion(fechaDePublicacion);
                    p.setUbicacion(ubicacion);
                    p.setCapacidadTotal(capacidadTotal);                                        
                    p.setValorArriendo(valorDeArriendo);
                    return true;
                }
            }
        }        
        return false;
    }

    /**
     * Metodo que elimina una propiedad existente.
     * @param id El id de la Propiedad que se desea borrar.
     * @return True si se elimino la Propiedad con exito. False si no se pudo borrar la Propiedad.
     * @throws java.sql.SQLException
     */
    public boolean eliminarPropiedad( int id) throws SQLException {
        boolean result = this.controlador.eliminarPropiedad(id);
        if(result){
            for(Propiedad p:this.listaPropiedades){
                if(p.getId() == id){
                    this.listaPropiedades.remove(p);
                    return true;
                }
            }
        }
        return false;
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
        boolean result = this.controlador.registrarSector(nombre, capacidad,id);
        if(result){
            for(Propiedad p : this.listaPropiedades){
                if(p.getId() == id){
                    p.añadirSector(new Sector(id,nombre,capacidad));
                    return true;
                }
            }
        }
        return false;
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
    public boolean modificarSector(int id, String nombreActual,int capacidad,  String nombre) throws SQLException {
        boolean result = this.controlador.modificarSector(nombreActual, id, nombre, capacidad);
        if(result){
            for(Propiedad p : this.listaPropiedades){
                if(p.getId() == id){
                    for(Sector s: p.getListaSectores()){
                        if(s.getIdPropiedad()== id && s.getNombre().equals(nombreActual)){
                            s.setNombre(nombre);
                            s.setCapacidadDelSector(capacidad);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Metodo que elimina un Sector ya existente asociado a una Propiedad.
     * @param id El id de la Propiedad a la cual esta asociada el Sector.
     * @param nombreActual El nombre actual del Sector.
     * @return True si se elimino el Sector con exito. False si no se pudo eliminar el Sector.
     * @throws java.sql.SQLException
     */
    public boolean eliminarSector( int id, String nombreActual) throws SQLException {
        boolean result = this.controlador.eliminarSector(nombreActual, id);
        if(result){
            for(Propiedad p:this.listaPropiedades){
                if(p.getId() == id){
                    p.eliminarSector(nombreActual, id);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Propiedad> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(ArrayList<Propiedad> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }
    
    /**
     * Metodo que permite aceptar una solicitud de propiedad de un evento.
     * @param idEvento El id del evento que se desea aceptar.
     * @return True si se acepto la solicitud con exito. False si no se pudo aceptar la solicitud.
     */
    public boolean aceptarSolicitud(int idEvento, String rutPropietario) {
        return this.gestorEventos.aceptarSolicitud(idEvento,rutPropietario);

        
    }

    /** 
     * Metodo que permite rechazar una solicitud, y por lo tanto, eliminarla del sistema
     * @param idEvento El id del evento al cual se desea rechazar la solicitud.
     * @return True si se rechazo la solicitud con exito. False si no se pudo rechazar la solicitud.
     */
    public boolean rechazarSolicitud(int idEvento) {
        return this.gestorEventos.rechazarSolicitud(idEvento);
    }

    /**
     * Metodo que retorna todas las solicitudes de eventos asociadas a un propietario especifico.
     * 
     * @param rut El rut del propietario.
     * @return Un arreglo con los eventos que tienen solicitudes hacia el propietario.
     */
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventos(String rut) {
        return this.gestorEventos.obtenerInformacionSolicitudesDeEventos(rut);
    }
     
    /**
     * Metodo que retorna la informacion de los eventos actuales asociados a un propietario.
     * @param rutPropietario El rut del propietario consultado.
     * @return Un arreglo con los eventos actuales asociados al propietario.
     */
    public ArrayList<Evento> obtenerInformacionDeEventosActuales(String rutPropietario) {
        return this.gestorEventos.obtenerInformacionDeEventosActualesPropietario(rutPropietario);
    }
    
    /**
     * Metodo que retorna la informacion de los eventos finalizados asociados a un propietario.
     * @param rutPropietario El rut del propietario consultado.
     * @return Un arreglo con los eventos finalizados asociados a un propietario.
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizados(String rutPropietario) {        
        return this.gestorEventos.obtenerInformacionDeEventosFinalizadosPropietario(rutPropietario);
    }
    
    /**
     * @return
     */
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventos() {
        // TODO implement here
        return null;
    }
}
