package ModuloGestionVentas;

import ControladorBaseDeDatos.ControladorBDDeEventos;
import ControladorBaseDeDatos.ControladorBDDeVentas;
import ModuloGestionEventos.Evento;
import ModuloGestionPropiedades.Propiedad;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class GestionDeVenta {
    
    private ControladorBDDeVentas controladorVentas;
    private ControladorBDDeEventos controladorEventos;

    /**
     * Constructor del Gestor de Ventas.
     */
    public GestionDeVenta() {
        this.controladorVentas = new ControladorBDDeVentas();
        this.controladorEventos = new ControladorBDDeEventos();
    }

    /**
     * Metodo que retorna todos los eventos publicados en el sistema, de un intervalo de tiempo especifico.
     * 
     * @param fechaInicio La fecha de inicio del intervalo de los Eventos.
     * @param fechaTermino La fecha de termino del intervalo de los Eventos.
     * @return Un arreglo con todos los eventos existentes en ese intervalo de tiempo.
     */
    public ArrayList<Evento> obtenerTodosLosEventos(Date fechaInicio, Date fechaTermino) {        
        return this.controladorEventos.obtenerEventoPublicados(fechaInicio, fechaTermino);
    }
    
    /**
     * Metodo que retorna el numero de entradas restantes de un evento.
     * @param idEvento El ID del evento que se desea consultar.
     * @return EL numero de entradas restantes que tiene un Evento especifico.
     */
    public int obtenerEntradasRestantes(int idEvento){
        return this.controladorVentas.obtenerEntradasQueQuedanPorEvento(idEvento);
    }

    /**
     * Metodo que permite realizar una Compra en el sistema.
     * 
     * @param rutCliente El rut del Cliente que realiza la compra.
     * @param idEvento El id del Evento al cual esta asociada la compra.
     * @param nombreSector El nombre del sector al cual esta asociada la compra.
     * @param cantidadDeEntradas La cantidad de entradas que se compraron.
     * @return True si se pudo efectuar la compra. False si NO se realizo la compra con exito.
     */
    public boolean registrarCompra(String rutCliente, int idEvento, String nombreSector, int cantidadDeEntradas,int idPropiedad) {
        return this.controladorVentas.registrarCompra(rutCliente, idEvento, nombreSector, cantidadDeEntradas, idPropiedad);
    }

    /**
     * Metodo que permite eliminar o cancelar una compra realizada por el usuario.
     * 
     * @param idCompra El id de la Compra que se desea cancelar/eliminar.
     * @return True si se pudo realizar la cancelacion. False si no se pudo cancelar.
     */
    public boolean eliminarCompra(int idCompra) {
        return this.controladorVentas.cancelarCompra(idCompra);
    }

    /**
     * Metodo que permite obtener el precio de un Sector especifico asociado a una Propiedad y Evento especificos.
     * 
     * @param idEvento El id del evento al que esta asociado el sector.
     * @param nombreSector El nombre del sector del cual se desea saber el precio.
     * @param idPropiedad El id de la Propiedad a la cual esta asociada el sector.
     * @return El precio del sector consultado.
     */
    public int obtenerInformacionDePrecioDeUnSector(int idEvento, String nombreSector, int idPropiedad) {
        return this.controladorEventos.obtenerPrecioEventoPorSector(idEvento, nombreSector, idPropiedad);
    }

     /**
     * Metodo que retorna la informacion de todas las Compras realizadas por un Usuario.
     * 
     * @param rutUsuario El rut del usuario actualmente ingresado en el sistema.
     * @return Un arreglo con la compras realizadas por el Usuario.
     */
    public ArrayList<Compra> obtenerInformacionDelHistorialDeCompraDeUnUsuario(String rutUsuario) {        
        return this.controladorVentas.obtenerInformacionDeHistorialDeCompraDeUnUsuario(rutUsuario);
    }

    /**
     * Metodo que retorna todas las compras realizadas asociadas a un eventos.
     * 
     * @param idEvento El id del Eventos del cual se desean obtener los datos.
     * @return Un arreglo con todas las compras asociadas al Evento.
     */
    public ArrayList<Compra> obtenerInformacionDelHistorialDeCompraDeUnEvento(int idEvento) {
        return this.controladorVentas.obtenerInformacionDeHistorrialDeCompraDeUnEvento(idEvento);
    }
    
    public ArrayList<Propiedad> obtenerListaDePropiedades(){
        return this.controladorEventos.obtenerListaDePropiedades();
    }
}