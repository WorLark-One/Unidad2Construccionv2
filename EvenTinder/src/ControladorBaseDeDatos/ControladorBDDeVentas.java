package ControladorBaseDeDatos;

import ModuloGestionVentas.Compra;
import java.util.*;

/**
 * 
 */
public class ControladorBDDeVentas {

    /**
     * Default constructor
     */
    public ControladorBDDeVentas() {
    }

    /**
     * @param String rutUsuario 
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDeHistorialDeCompraDeUnUsuario( String rutUsuario) {
        // TODO implement here
        return null;
    }

    /**
     * @param int idEvento 
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDeHistorrialDeCompraDeUnEvento( int idEvento) {
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
    public boolean registrarCompra( String rutCliente,  int idEvento,  String nombreSector,  int cantidadDeEntradas) {
        // TODO implement here
        return false;
    }

    /**
     * @param int idCompra 
     * @return
     */
    public boolean cancelarCompra( int idCompra) {
        // TODO implement here
        return false;
    }


}