package ModuloGestionEventos;

import ControladorBaseDeDatos.ControladorBDDeEventos;
import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionPropiedades.Propiedad;
import ModuloGestionUsuario.Propietario;
import java.util.ArrayList;
import java.util.Date;


/**
 * 
 */
public class GestionDeEvento {
    
    private ControladorBDDeEventos controlador;
    private ArrayList<Evento> listaEventos;
   //ControlDeAcceso controlAcceso;

    /**
     * Default constructor
     */
    public GestionDeEvento() {
        this.controlador = new ControladorBDDeEventos();
        this.listaEventos = new ArrayList();
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
     * @param rutOrganizador 
     * @return
     */
    public boolean crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {
        String rutOrganizador="19255330k";
        int idEvento = this.controlador.crearEvento(nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad,rutOrganizador );
        if(idEvento != 0){                        
            Evento e = new Evento(idEvento, nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado);
            this.listaEventos.add(e);  
            return true;
        }        
        return false;
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
     * @return
     */
    public boolean modificarEvento(int idEvento, String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado) {
        boolean result = this.controlador.modificarEvento(idEvento, nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado);        
        if(result){                                  
            for(Evento e : this.listaEventos){
                if(e.getIdEvento() == idEvento){
                    e.setNombre(nombre);
                    e.setDescripcion(descripcion);
                    e.setCapacidadMaximaDelEvento(diasMaximoDevolucion);
                    e.setFechaDeInicio(fechaDeInicio);
                    e.setFechaDeTermino(fechaDeTermino);
                    e.setPlazoDevolucionEntrada(diasMaximoDevolucion);                    
                    return true;
                }
            }
        }                
        return result;
    }
    
    public ArrayList<Propiedad> obtenerInformacionPropiedades(){
        return this.controlador.obtenerListaDePropietarios();
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
                if(e.getIdEvento() == idEvento){
                    this.listaEventos.remove(e);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param idEvento 
     * @return
     */
    public boolean aceptarSolicitud(int idEvento) {
        return this.controlador.aceptarSolicitudPropietario(idEvento);
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
        switch(opcion){
            case "Todos":
                return this.controlador.obtenerInformacionDeTodosLosEventosDeUnOrganizador(rut);
            case "Publicados":
                return this.controlador.obtenerInformacionDeEventosPublicadosDeUnOrganizador(rut);
            case "No Publicados":
                return this.controlador.obtenerInformacionDeEventosNoPublicadosDeUnOrganizador(rut);
            case "Finalizados":
                return this.controlador.obtenerInformacionDeEventosFinalizadosDeUnOrganizador(rut);
            default:
                return null;
        }        
    }
    
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventos(String rutPropietario){
        return this.controlador.obtenerInformacionSolicitudesDeEventosPropietario(rutPropietario);
    }
    
}