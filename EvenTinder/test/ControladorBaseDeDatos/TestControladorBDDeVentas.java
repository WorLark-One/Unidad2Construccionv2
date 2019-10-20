/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorBaseDeDatos;
import ControladorBaseDeDatos.ControladorBDDeVentas;
import ModuloGestionVentas.Compra;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruben
 */
public class TestControladorBDDeVentas {
    ControladorBDDeVentas c = new ControladorBDDeVentas();
    public TestControladorBDDeVentas() {
    }

    
    @Test
    public void obtenerInformacionDeHistorialDeCompraDeUnUsuario() {
        System.out.println("obtenerInformacionDeHistorialDeCompraDeUnUsuario");
        String rutUsuario = "19.006.568-5";
        ArrayList<Compra> a = c.obtenerInformacionDeHistorialDeCompraDeUnUsuario(rutUsuario);
        
        for(Compra c: a) {
            System.out.println("Id: " + c.getId() + " Nombre Evento: " + c.getNombreEvento() + " Nombre sector: " + c.getNombreSector());
        }
        
    }
    
    @Test
    public void obtenerInformacionDeHistorrialDeCompraDeUnEvento() {
        System.out.println("obtenerInformacionDeHistorrialDeCompraDeUnEvento");
        int IdEvento = 1;
        ArrayList<Compra> a = c.obtenerInformacionDeHistorrialDeCompraDeUnEvento(IdEvento);
        
        for(Compra c: a) {
            System.out.println("Id: " + c.getId() + " Nombre Evento: " + c.getNombreEvento() + " Nombre sector: " + c.getNombreSector());
        }
    }
    
    @Test
    public void registrarCompra() {
        String rutCliente = "19.006.568-5";
        int IdEvento = 1;
        String nombreSector = "Abajo";
        int cantidadDeEntradas = 1111111111;
        int IdPropiedad = 1;
        
        
        boolean r = c.registrarCompra(rutCliente, IdEvento, nombreSector, cantidadDeEntradas, IdPropiedad);
        boolean e = true;
        
        assertEquals(e, r);
        fail("No se realizó la compra correctamente");
    }
    
    @Test
    public void cancelarCompra() {
        int IdCompra = 7;
        boolean r = c.cancelarCompra(IdCompra);
        boolean e = true;
        assertEquals(e, r);
        fail("No se canceló la compra correctamente");
        
    }
}
