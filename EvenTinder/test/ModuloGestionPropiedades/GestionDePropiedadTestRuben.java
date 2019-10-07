/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionPropiedades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GestionDePropiedadTest {
    public GestionDePropiedadTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void mostrarListaDePropiedades() { 
        GestionDePropiedad instance = new GestionDePropiedad();
        
       ArrayList<Propiedad> p= instance.mostrarListaDePropiedades();
       
       for (Propiedad pro: p) {
           System.out.println(pro.getNombre() + " " + pro.getId() + " " + pro.getDescripcion());
       }

    }
/*
    @Test
    public void mostrarPropiedad() {
        GestionDePropiedad instance = new GestionDePropiedad();
        
        Propiedad p= instance.mostrarPropiedad(11);
       
        System.out.println(p.getId() + " " + p.getNombre());
    }
*/

    @Test
    public void obtenerInformacionDePropiedades() throws SQLException {
        GestionDePropiedad instance = new GestionDePropiedad();
        
        instance.obtenerInformacionDePropiedades("19");
    }

    /**
     * Test of registrarPropiedad method, of class GestionDePropiedad.
     */
    
    /*
    @Test
    public void testRegistrarPropiedad() throws SQLException {
        System.out.println("registrarPropiedad");
        
        String rut = "1";
        String nombre = "Disco";
        String ubicacion = "Santiago";
        Date fechaDePublicacion = new Date(2019,10,10);
        int capacidadTotal = -1;
        int valorDeArriendo = 3000;
        String descripcion = "desc";
        
        GestionDePropiedad instance = new GestionDePropiedad();
        int expResult = 0;
        
        int result = instance.registrarPropiedad(rut, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of modifcarPropiedad method, of class GestionDePropiedad.
     
   
    
    @Test
    public void testModifcarPropiedad() throws SQLException {
        System.out.println("modifcarPropiedad");
        int id = 11;
        String nombre = "Gran Piscina";
        String ubicacion = "La Serena";
        Date fechaDePublicacion = new Date(2020, 05, 12);
        int capacidadTotal = 600;
        int valorDeArriendo = 1000;
        String descripcion = "Piscinazo";
        
        GestionDePropiedad instance = new GestionDePropiedad();
        
        boolean expResult = true;
        boolean result = instance.modifcarPropiedad(id, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminarPropiedad method, of class GestionDePropiedad.
     */
    
    /*
    @Test
    public void testEliminarPropiedad() throws SQLException {
        System.out.println("eliminarPropiedad");
        
        int id = 0;
        GestionDePropiedad instance = new GestionDePropiedad();
       
      
        boolean expResult = true;
        boolean result = instance.eliminarPropiedad(id);        
        assertEquals(expResult, result);
        assertNull(instance.mostrarPropiedad(id));
        
    }
*/
    /**
     * Test of añadirSector method, of class GestionDePropiedad.
     */
    /*
    @Test
    public void testAñadirSector() throws SQLException {
        System.out.println("añadirSector");               

        GestionDePropiedad instance = new GestionDePropiedad();
        
        int id = 12;
        int capacidad = 200;
        String nombreSector = "Centro";
        boolean expResult = true;
        boolean result = instance.añadirSector(id, capacidad, nombreSector);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of modificarSector method, of class GestionDePropiedad.
     */

    /*
    @Test
    public void testModificarSector() throws SQLException {
        System.out.println("modificarSector");
        //Se crea una propiedad de prueba

        GestionDePropiedad instance = new GestionDePropiedad();
 
        

        int id= 11;
        int capacidad2 = 50;      
        String nombreActual = "Piscina pequeña";
        String nombreSector = "Puerta";
        boolean expResult = true;
        boolean result = instance.modificarSector(id, nombreActual,capacidad2, nombreSector);       
        assertEquals(expResult, result);
    }

    
     //* Test of eliminarSector method, of class GestionDePropiedad.
     
    /*
    @Test
    public void testEliminarSector() throws SQLException {
        System.out.println("eliminarSector");

        GestionDePropiedad instance = new GestionDePropiedad();
        
        
        int id = 11;
        String nombreActual = "Piscina pequeña";                     
        boolean expResult = true;
        boolean result = instance.eliminarSector(id,nombreActual);
        assertEquals(expResult, result);
    }
    */
    

}
