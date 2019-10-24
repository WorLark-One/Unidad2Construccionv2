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
    
    
    @Test
    public void obtenerInformaciónDePropiedades() throws SQLException {
        String rut = "22.222.222-2";
        ArrayList<Propiedad> propiedades = c.obtenerInformacionDePropiedades(rut);
        
        for (Propiedad p: propiedades) {
            System.out.println(p.getId() + " " + p.getNombre() + " " + p.getDescripcion() + " " + p.getCapacidadTotal() + " "+p.getValorArriendo() +" " + p.getUbicacion() + " " + p.getFechaDePublicacion());
        }
    }
    
    
    
    @Test
    public void registrarPropiedad() throws SQLException, ParseException {
        int esperado = 0;
        String rut="22.222.222-2"; 
        String nombre="Campo Santo";
        String ubicacion="Sagrada Familia"; 
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDePublicacion = format.parse ( "06-10-2019" );  
        int capacidadTotal=600; 
        int valorDeArriendo = 2000; 
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
    
    
    
    
    @Test
    public void modifcarPropiedad() throws SQLException, ParseException {
        boolean esperado = false;
        int id=7; 
        String nombre="Utal";
        String ubicacion="Talcahuano"; 
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDePublicacion = format.parse ( "20-10-2019" );  
        int capacidadTotal=1000; 
        int valorDeArriendo = 1000; 
        String descripcion = "Donde está?";
        boolean resultado = c.modifcarPropiedad(id, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        
        assertTrue(resultado);
        
        if (resultado==esperado) {
            
            fail("No se modificó la propiedad");
        }
        else {
            System.out.println("Se modificó correctamente la propiedad en la base de datos");
        }
    }
    
    
    @Test
    public void eliminarPropiedad() throws SQLException {
        int id=11; 
        boolean resultado = c.eliminarPropiedad(id);
        
        assertTrue(resultado);
        fail("No se eliminó la propiedad");
        
        
    }
    
    
    @Test
    public void registrarSector() throws SQLException {
        boolean esperado = true;
        String nombreSector = "Piscina";
        int capacidad = 10;
        int idPropiedad=10; 
        boolean resultado = c.registrarSector(nombreSector, capacidad, idPropiedad);
        
        assertEquals(esperado, resultado);
        fail("No se registró el sector");

    }
    
    @Test
    public void modificarSector() throws SQLException {
        boolean esperado = true;
        String nombreSector = "Piscina";
        String nuevoSector = "Jacuzzi";
        int capacidad = 60;
        int idPropiedad=2; 
        boolean resultado = c.modificarSector(nombreSector, idPropiedad, nuevoSector, capacidad);
        
        assertEquals(esperado, resultado);
         fail("No se modificó el sector");
        
    }
    
    
    @Test
    public void eliminarSector() throws SQLException {
        boolean esperado = true;
        String nombreSector = "Centro";
        int idPropiedad=1; 
        boolean resultado = c.eliminarSector(nombreSector, idPropiedad);
        
        assertEquals(esperado, resultado);
        
          fail("No se modificó el sector");  
  
    }

}
