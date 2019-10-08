/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorBaseDeDatos;
import ControladorBaseDeDatos.ControladorBDDePropiedades;
import ModuloGestionPropiedades.Propiedad;
import ControladorBaseDeDatos.ConexionBD;
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
public class TestControladorBDDePropiedades {
    ControladorBDDePropiedades c = new ControladorBDDePropiedades();
    
    public TestControladorBDDePropiedades() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    /*
    @Test
    public void obtenerInformaciónDePropiedades() throws SQLException {
        String rut = "0101";
        ArrayList<Propiedad> propiedades = c.obtenerInformacionDePropiedades(rut);
        
        for (Propiedad p: propiedades) {
            System.out.println(p.getId() + " " + p.getNombre() + " " + p.getDescripcion() + " " + p.getCapacidadTotal() + " "+p.getValorArriendo() +" " + p.getUbicacion() + " " + p.getFechaDePublicacion());
        }
    }
    
    */
    /*
    @Test
    public void registrarPropiedad() throws SQLException, ParseException {
        int esperado = 0;
        String rut="1"; 
        String nombre="Campo";
        String ubicacion="Sagrada Familia"; 
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDePublicacion = format.parse ( "06-10-2019" );  
        int capacidadTotal=600; 
        int valorDeArriendo = 20; 
        String descripcion = "Lo mejor";
        int resultado = c.registrarPropiedad(rut, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        
        assertNotEquals(esperado, resultado);
        
        if (resultado==esperado) {
            
            fail("No se registró la propiedad");
        }
        else {
            System.out.println("Se añadió correctamente la propiedad a la base de datos");
        }
        
    }
    
    
    
    /*
    @Test
    public void modifcarPropiedad() throws SQLException, ParseException {
        boolean esperado = false;
        int id=6; 
        String nombre="Utal";
        String ubicacion="Talca"; 
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDePublicacion = format.parse ( "" );  
        int capacidadTotal=0; 
        int valorDeArriendo = 20; 
        String descripcion = "Donde está?";
        boolean resultado = c.modifcarPropiedad(id, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        
        assertNotEquals(esperado, resultado);
        
        if (resultado==esperado) {
            
            fail("No se modificó la propiedad");
        }
        else {
            System.out.println("Se modificó correctamente la propiedad en la base de datos");
        }
    }*/
    
    /*
    @Test
    public void eliminarPropiedad() throws SQLException {
        boolean esperado = false;
        int id=7; 
        boolean resultado = c.eliminarPropiedad(id);
        
        assertNotEquals(esperado, resultado);
        
        if (resultado==esperado) {
            
            fail("No se eliminó la propiedad");
        }
        else {
            System.out.println("Se eliminó correctamente la propiedad de la base de datos");
        }
        
    }*/
    
    /*
    @Test
    public void registrarSector() throws SQLException {
        boolean esperado = true;
        String nombreSector = "Piscina";
        int capacidad = 10;
        int idPropiedad=1; 
        boolean resultado = c.registrarSector(nombreSector, capacidad, idPropiedad);
        
        assertEquals(esperado, resultado);
        
        if (resultado!=esperado) {
            
            fail("No se registró el sector");
        }
        else {
            System.out.println("Se añadió correctaemente el sector a la Base de datos");
        }
    }*/
    /*
    @Test
    public void modificarSector() throws SQLException {
        boolean esperado = true;
        String nombreSector = "Piscina";
        String nuevoSector = "Jacuzzi";
        int capacidad = 60;
        int idPropiedad=2; 
        boolean resultado = c.modificarSector(nombreSector, idPropiedad, nuevoSector, capacidad);
        
        assertEquals(esperado, resultado);
        
        if (resultado!=esperado) {
            
            fail("No se modificó el sector");
        }
        else {
            System.out.println("Se modificó correctaemente el sector en la Base de datos");
        }
        
    }
    */
    
    @Test
    public void eliminarSector() throws SQLException {
        boolean esperado = true;
        String nombreSector = "Terraza";
        int idPropiedad=6; 
        boolean resultado = c.eliminarSector(nombreSector, idPropiedad);
        
        assertEquals(esperado, resultado);
        
        if (resultado!=esperado) {
            
            fail("No se modificó el sector");
        }
        else {
            System.out.println("Se modificó correctaemente el sector en la Base de datos");
        }
    }

}
