package ModuloGestionVentas;

import ModuloGestionEventos.Evento;
import java.util.ArrayList;

/**
 * 
 */
public class GestionDeVenta {

    /**
     * Default constructor
     */
    public GestionDeVenta() {
    }



    /**
     * @return
     */
    public ArrayList<Evento> obtenerTodosLosEventos() {
        // TODO implement here
        return null;
    }

    /**
     * @param String rutCliente 
     * @param int idEvento 
     * @param String nombreSector 
     * @param int cantidadDeEntradas 
     * @return
     */
    public boolean registrarCompra(String rutCliente, int idEvento, String nombreSector, int cantidadDeEntradas) {
        // TODO implement here
        return false;
    }

    /**
     * @param int idCompra 
     * @return
     */
    public boolean eliminarCompra(int idCompra) {
        // TODO implement here
        return false;
    }

    /**
     * @param int idEvento 
     * @param String nombreSector 
     * @param int idPropiedad 
     * @return
     */
    public int obtenerInformacionDePrecioDeUnSector(int idEvento, String nombreSector, int idPropiedad) {
        // TODO implement here
        return 0;
    }

    /**
     * @param String rutUsuario 
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDelHistorialDeCompraDeUnUsuario(String rutUsuario) {
        // TODO implement here
        return null;
    }

    /**
     * @param int idEvento 
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDelHistorialDeCompraDeUnEvento(int idEvento) {
        // TODO implement here
        return null;
    }

}