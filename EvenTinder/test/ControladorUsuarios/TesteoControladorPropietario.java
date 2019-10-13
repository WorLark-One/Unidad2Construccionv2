/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorUsuarios;

import ControladorUsuarios.ControladorPropietario;
import ModuloGestionPropiedades.Propiedad;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruben
 */
public class TesteoControladorPropietario  {
    
    public TesteoControladorPropietario() throws SQLException {
    }

    
   @Test
    public void mostrarInformacionDePropiedades() throws SQLException {
        ControladorPropietario p = new ControladorPropietario();
        ArrayList<Propiedad> propiedades = p.mostrarInformacionDePropiedadesDeUnPropietario();
        
        for (Propiedad po: propiedades) {
            System.out.println(po.getId() + " " + po.getNombre());
        }
    }

    
    @Test
    public void mostrarInformaciónPropiedad() throws SQLException {
        ControladorPropietario p = new ControladorPropietario();
        Propiedad po = p.mostrarInformaciónPropiedad(10);
        System.out.println(po.getId() + " " + po.getNombre() + " " + po.getDescripcion());
    }

    
    @Test
    public void registrarPropiedad() throws SQLException {
        ControladorPropietario p = new ControladorPropietario();
        String nombre="Casino Monti";
        String ubicacion = "Santiago";
        Date fechaDePublicacion = new Date(2019, 5, 12) ;
        int capacidadTotal= 1000;
        int valorArriendo = 200000;
        String descripcion = "Pa gastar platita";
        int esperado = 0;
        int resultado= p.registrarPropiedad(nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorArriendo, descripcion);
        
        assertNotEquals(resultado, esperado);
    }

    
    @Test
    public void modifcarPropiedad() throws SQLException, ParseException {
        ControladorPropietario p = new ControladorPropietario();
        int id = 2;
        String nombre="Villa Salada";
        String ubicacion = "Talca";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDePublicacion = format.parse ( "11-05-2019" );  
        int capacidadTotal= 1000;
        int valorArriendo = 100;
        String descripcion = "Pa los ka";
        boolean esperado = true;
        boolean resultado= p.modifcarPropiedad(id, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorArriendo, descripcion);
        
        assertEquals(esperado, resultado);
    }
    
    

    @Test
    public void eliminarPropiedad() throws SQLException {
         ControladorPropietario p = new ControladorPropietario();
         int id= 3;
         boolean esperado = true;
         boolean resultado= p.eliminarPropiedad(id);
         
         assertEquals(esperado, resultado);
    }

    
    @Test
    public void añadirSector() throws SQLException {
        ControladorPropietario p = new ControladorPropietario();
        int id=1;
        int capacidad=2;
        String nombre="Habitacion";
        boolean esperado = true;
        boolean resultado= p.añadirSector(id, capacidad, nombre);
        
        assertEquals(esperado, resultado);
    }
    
    
    @Test
    public void modificarSector() throws SQLException {
        ControladorPropietario p = new ControladorPropietario();
        int id=7;
        int capacidad=10;
        String nombre="Piscina";
        String nombreNuevo = "Terraza";
        boolean esperado = true;
        boolean resultado= p.modificarSector(id, nombre, capacidad, nombreNuevo);
        
        assertEquals(esperado, resultado);
    }

    @Test
    public void eliminarSector() throws SQLException {
            ControladorPropietario p = new ControladorPropietario();
        int id=8;
        String nombre="Casino";
        boolean esperado = true;
        boolean resultado= p.eliminarSector(id, nombre);
        
        assertEquals(esperado, resultado);
    }
}
