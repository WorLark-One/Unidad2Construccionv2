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
     * @param rutUsuario 
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDeHistorialDeCompraDeUnUsuario( String rutUsuario) {
        // TODO implement here
        return null;
    }

    /** 
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDeHistorrialDeCompraDeUnEvento( int idEvento) {
        // TODO implement here
        return null;
    }

 
    public boolean registrarCompra( String rutCliente,  int idEvento,  String nombreSector,  int cantidadDeEntradas) {
        // TODO implement here
        return false;
    }


    public boolean cancelarCompra( int idCompra) {
        // TODO implement here
        return false;
    }


}