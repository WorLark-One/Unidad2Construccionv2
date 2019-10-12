package ModuloGestionPropiedades;

import ControladorBaseDeDatos.ControladorBDDePropiedades;
import ModuloGestionEventos.Evento;
import ModuloGestionEventos.GestionDeEvento;
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
     * @return La lista de las propiedades del Propietario que esta conectado al sistema.
     */
    public ArrayList<Propiedad> mostrarListaDePropiedades() { 
        
        return this.listaPropiedades;
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
    public boolean modifcarPropiedad( int id,  String nombre,  String descripcion,  Date fechaDePublicacion, String ubicacion,  int capacidadTotal,  int valorDeArriendo) throws SQLException {
        boolean result = this.controlador.modifcarPropiedad(id, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
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
     * @param idEvento 
     * @return
     */
    public boolean aceptarSolicitud(int idEvento) {
        return this.gestorEventos.aceptarSolicitud(idEvento);
    }

    /**
     * @param idEvento 
     * @return
     */
    public boolean rechazarSolicitud(int idEvento) {
        return this.gestorEventos.eliminarEvento(idEvento);
    }

    /**
     * @param rut
     * @return
     */
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventos(String rut) {
        return this.gestorEventos.obtenerInformacionSolicitudesDeEventos(rut);
    }
            
}
