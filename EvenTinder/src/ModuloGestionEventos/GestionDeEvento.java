package ModuloGestionEventos;

import ControladorBaseDeDatos.ControladorBDDeEventos;
import ModuloGestionPropiedades.Propiedad;
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
    public int crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad, String rutOrganizador) {        
        return this.controlador.crearEvento(nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad,rutOrganizador );
    }
    
    public boolean agregarPrecioSector(int idEvento, int nuevoPrecio, String nombreSector,int idPropiedad){
        return this.controlador.añadirPrecioEntradaPorSector(idEvento, nuevoPrecio, nombreSector, idPropiedad);
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
        return this.controlador.modificarEvento(idEvento, nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado);        
    }
    
    public boolean modificarPrecioSector(int idEvento, int nuevoPrecio, String nombreSector,int idPropiedad){
        return this.controlador.modificarPrecioEntradaPorSector(idEvento,nuevoPrecio,nombreSector,idPropiedad);
    }
    
    public ArrayList<Propiedad> obtenerInformacionPropiedades(){
        return this.controlador.obtenerListaDePropiedades();
    }

    /**
     * Metodo que eliminar 
     * @param idEvento 
     * @return
     */
    public boolean eliminarEvento(int idEvento) {
        return this.controlador.eliminarEvento(idEvento);
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
    
    public ArrayList<Evento> obtenerInformacionDeEventosActualesPropietario(String rutPropietario) {
        return this.controlador.obtenerInformacionDeEventosActualesPropietario(rutPropietario);
    }
    
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosPropietario(String rutPropietario) {        
        return this.controlador.obtenerInformacionDeEventosFinalizadosPropietario(rutPropietario);
    }
    
    public int obtenerPrecioEntradaPorSector(int idEvento, String nombreSector, int idPropiedad){
        return this.controlador.obtenerPrecioEntradaPorSector(idEvento, nombreSector, idPropiedad);
    }
}