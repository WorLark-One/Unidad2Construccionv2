/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionPropiedades;

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


    /**
     * Test of registrarPropiedad method, of class GestionDePropiedad.
     */
    @Test
    public void testRegistrarPropiedad() {
        System.out.println("registrarPropiedad");
        
        String rut = "1";
        String nombre = "nombre";
        String ubicacion = "ubicacion";
        Date fechaDePublicacion = new Date(2019,10,05);
        int capacidadTotal = 10;
        int valorDeArriendo = 10000;
        String descripcion = "descripcion";
        
        GestionDePropiedad instance = new GestionDePropiedad();
        int expResult = 1;
        
        int result = instance.registrarPropiedad(rut, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of modifcarPropiedad method, of class GestionDePropiedad.
     */
    @Test
    public void testModifcarPropiedad() {
        System.out.println("modifcarPropiedad");
        int id = 1;
        String nombre = "";
        String ubicacion = "";
        Date fechaDePublicacion = null;
        int capacidadTotal = 0;
        int valorDeArriendo = 0;
        String descripcion = "";
        
        GestionDePropiedad instance = new GestionDePropiedad();
        
        String rut ="";
        instance.registrarPropiedad(rut, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        
        boolean expResult = true;
        boolean result = instance.modifcarPropiedad(id, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarPropiedad method, of class GestionDePropiedad.
     */
    @Test
    public void testEliminarPropiedad() {
        System.out.println("eliminarPropiedad");
        
        int id = 1;
        GestionDePropiedad instance = new GestionDePropiedad();
        String rut = "";
        String nombre = "";
        String ubicacion = "";
        Date fechaDePublicacion = null;
        int capacidadTotal = 0;
        int valorDeArriendo = 0;
        String descripcion = "";        
        instance.registrarPropiedad(rut, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
       
        assertNotNull(instance.mostrarPropiedad(id));
        
        boolean expResult = true;
        boolean result = instance.eliminarPropiedad(id);        
        assertEquals(expResult, result);
        assertNull(instance.mostrarPropiedad(id));
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of añadirSector method, of class GestionDePropiedad.
     */
    @Test
    public void testAñadirSector() {
        System.out.println("añadirSector");               
        String rut = "";
        String nombre = "";
        String ubicacion = "";
        Date fechaDePublicacion = null;
        int capacidadTotal = 0;
        int valorDeArriendo = 0;
        String descripcion = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        instance.registrarPropiedad(rut, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        
        int id = 1;
        int capacidad = 0;
        String nombreSector = "";
        boolean expResult = true;
        boolean result = instance.añadirSector(id, capacidad, nombreSector);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of modificarSector method, of class GestionDePropiedad.
     */
    @Test
    public void testModificarSector() {
        System.out.println("modificarSector");
        //Se crea una propiedad de prueba
        String rut = "";
        String nombre = "";
        String ubicacion = "";
        Date fechaDePublicacion = null;
        int capacidadTotal = 0;
        int valorDeArriendo = 0;
        String descripcion = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        instance.registrarPropiedad(rut, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        
        
        int id = 1;
        int capacidad = 1;
        String nombreActual = "nombre";        
        instance.añadirSector(id, capacidad, nombreActual);
        
        int capacidad2 = 0;        
        String nombreSector = "";
        boolean expResult = true;
        boolean result = instance.modificarSector(id, nombreActual,capacidad2, nombreSector);       
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarSector method, of class GestionDePropiedad.
     */
    @Test
    public void testEliminarSector() {
        System.out.println("eliminarSector");
        //Se crea una propiedad de prueba
        String rut = "";
        String nombre = "";
        String ubicacion = "";
        Date fechaDePublicacion = null;
        int capacidadTotal = 0;
        int valorDeArriendo = 0;
        String descripcion = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        instance.registrarPropiedad(rut, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
        
        
        int id = 1;
        int capacidad = 1;
        String nombreActual = "nombre";        
        instance.añadirSector(id, capacidad, nombreActual);
                
        boolean expResult = true;
        boolean result = instance.eliminarSector(id,nombreActual);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
