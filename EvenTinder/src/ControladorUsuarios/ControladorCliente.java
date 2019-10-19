package ControladorUsuarios;

import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionEventos.Evento;
import ModuloGestionVentas.Compra;
import ModuloGestionVentas.GestionDeVenta;
import java.util.ArrayList;

/**
 * 
 */
public class ControladorCliente {
    
    private GestionDeVenta gestorVentas;
    private ControlDeAcceso controlAcceso;
    
    /**
     * Constructor del Controlador de Clientes.
     */
    public ControladorCliente() {
        this.gestorVentas = new GestionDeVenta();
        this.controlAcceso = ControlDeAcceso.getIntancia();
    }
    
    /**
     * Metodo que permite realizar una Compra en el sistema.
     * 
     * @param idEvento El id del Evento al cual esta asociada la compra.
     * @param nombreSector El nombre del sector al cual esta asociada la compra.
     * @param cantidadDeEntradas La cantidad de entradas que se compraron.
     * @return True si se pudo efectuar la compra. False si NO se realizo la compra con exito.
     */
    public boolean registrarCompra(int idEvento, String nombreSector, int cantidadDeEntradas){
        return this.gestorVentas.registrarCompra(this.controlAcceso.getRut(), idEvento, nombreSector, cantidadDeEntradas);
    }
    
    /**
     * Metodo que permite eliminar o cancelar una compra realizada por el usuario.
     * 
     * @param idCompra El id de la Compra que se desea cancelar/eliminar.
     * @return True si se pudo realizar la cancelacion. False si no se pudo cancelar.
     */
    public boolean eliminarCompra(int idCompra){
        return this.gestorVentas.eliminarCompra(idCompra);
    }
    
    /**
     * Metodo que retorna todos los eventos publicados en el sistema.
     * 
     * @return Un arreglo con todos los eventos existentes.
     */
    public ArrayList<Evento> obtenerTodosLosEventos(){
        return this.gestorVentas.obtenerTodosLosEventos();
    }
    
    /**
     * Metodo que permite obtener el precio de un Sector especifico asociado a una Propiedad y Evento especificos.
     * 
     * @param idEvento El id del evento al que esta asociado el sector.
     * @param nombreSector El nombre del sector del cual se desea saber el precio.
     * @param idPropiedad El id de la Propiedad a la cual esta asociada el sector.
     * @return El precio del sector consultado.
     */
    public int obtenerInformacionDePrecioDeUnSector(int idEvento, String nombreSector, int idPropiedad){
        return this.gestorVentas.obtenerInformacionDePrecioDeUnSector(idEvento, nombreSector, idPropiedad);
    }
    
    /**
     * Metodo que retorna la informacion de todas las Compras realizadas por un Usuario.
     * 
     * @return Un arreglo con la compras realizadas por el Usuario.
     */
    public ArrayList<Compra> obtenerInformacionDelHistorialDeCompraDeUnUsuario(){
        return this.gestorVentas.obtenerInformacionDelHistorialDeCompraDeUnUsuario(this.controlAcceso.getRut());
    }
            
}