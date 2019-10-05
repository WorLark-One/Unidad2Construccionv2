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
    private ArrayList<Propiedad> lista;
    
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
        this.lista = new ArrayList();
        lista.add(new Propiedad());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of mostrarListaDePropiedades method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testMostrarListaDePropiedades() {
        System.out.println("mostrarListaDePropiedades");
        GestionDePropiedad instance = new GestionDePropiedad();
        ArrayList<Propiedad> expResult = null;
        ArrayList<Propiedad> result = instance.mostrarListaDePropiedades();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarPropiedad method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testMostrarPropiedad() {
        System.out.println("mostrarPropiedad");
        
        int id = 1;
        
        GestionDePropiedad instance = new GestionDePropiedad();
        Propiedad expResult = null;
        
        Propiedad result = instance.mostrarPropiedad(id);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerInformacionDePropiedades method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testObtenerInformacionDePropiedades() {
        System.out.println("obtenerInformacionDePropiedades");
        String rut = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        boolean expResult = false;
        boolean result = instance.obtenerInformacionDePropiedades(rut);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarPropiedad method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testRegistrarPropiedad() {
        System.out.println("registrarPropiedad");
        String rut = "";
        String nombre = "";
        String ubicacion = "";
        Date fechaDePublicacion = null;
        int capacidadTotal = 0;
        int valorDeArriendo = 0;
        String descripcion = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        boolean expResult = false;
        boolean result = instance.registrarPropiedad(rut, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifcarPropiedad method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testModifcarPropiedad() {
        System.out.println("modifcarPropiedad");
        int id = 0;
        String nombre = "";
        String ubicacion = "";
        Date fechaDePublicacion = null;
        int capacidadTotal = 0;
        int valorDeArriendo = 0;
        String descripcion = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        boolean expResult = false;
        boolean result = instance.modifcarPropiedad(id, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarPropiedad method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testEliminarPropiedad() {
        System.out.println("eliminarPropiedad");
        int id = 0;
        GestionDePropiedad instance = new GestionDePropiedad();
        boolean expResult = false;
        boolean result = instance.eliminarPropiedad(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of añadirSector method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testAñadirSector() {
        System.out.println("a\u00f1adirSector");
        int id = 0;
        int capacidad = 0;
        String nombre = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        boolean expResult = false;
        boolean result = instance.añadirSector(id, capacidad, nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarSector method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testModificarSector() {
        System.out.println("modificarSector");
        int id = 0;
        int capacidad = 0;
        String nombre = "";
        GestionDePropiedad instance = new GestionDePropiedad();
        boolean expResult = false;
        boolean result = instance.modificarSector(id, capacidad, nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarSector method, of class GestionDePropiedad.
     */
    @org.junit.Test
    public void testEliminarSector() {
        System.out.println("eliminarSector");
        int id = 0;
        GestionDePropiedad instance = new GestionDePropiedad();
        boolean expResult = false;
        boolean result = instance.eliminarSector(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
