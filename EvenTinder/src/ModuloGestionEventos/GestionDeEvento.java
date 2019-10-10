package ModuloGestionEventos;

import ControladorBaseDeDatos.ControladorBDDeEventos;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 */
public class GestionDeEvento {
    
    private ControladorBDDeEventos controlador;
    private ArrayList<Evento> listaEventos;

    /**
     * Default constructor
     */
    public GestionDeEvento() {
        this.controlador = new ControladorBDDeEventos();
    }

    /**
     * @param nombre 
     * @param descripcion 
     * @param fechaDeInicio 
     * @param fechaDeTermino 
     * @param capacidad 
     * @param diasMaximoDevolucion 
     * @param publicado 
     * @param idPropiedad 
     * @return
     */
    public boolean crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {
        boolean result = this.controlador.crearEvento(nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad, descripcion);
        if(result){
            
            //crear evento cuando este implementada la clase evento.
            //Evento e = new Evento();
            //this.listaEventos.add(e);            
        }        
        return result;
    }

    /**
     * @param idEvento 
     * @param nombre 
     * @param descripcion 
     * @param fechaDeInicio 
     * @param fechaDeTermino 
     * @param capacidad 
     * @param diasMaximoDevolucion 
     * @param publicado 
     * @param idPropiedad 
     * @return
     */
    public boolean modificarEvento(int idEvento, String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {
        boolean result = this.controlador.modificarEvento(idEvento, nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad);
        if(result){
            //modificar evento cuando este implementada la clase evento
        }                
        return result;
    }

    /**
     * Metodo que eliminar 
     * @param idEvento 
     * @return
     */
    public boolean eliminarEvento(int idEvento) {
        boolean result = this.controlador.eliminarEvento(idEvento);
        if(result){
            for(Evento e:this.listaEventos){
                //buscar el id, despues eliminar evento.
            }
        }
        return false;
    }

    /**
     * @param idEvento 
     * @return
     */
    public boolean aceptarSolicitud(int idEvento) {
        this.controlador.aceptarSolicitudPropietario(idEvento);
        
        
        return false;
    }

    /**
     * @param idEvento 
     * @return
     */
    public boolean rechazarSolicitud(int idEvento) {
        return this.controlador.eliminarEvento(idEvento);                                
    }

    /**
     * 
     * 
     * @param rut 
     * @param opcion 
     * @return
     */
    public ArrayList<Evento> obtenerInformacion(String rut, String opcion) {
        // TODO implement here
        return null;
    }
}