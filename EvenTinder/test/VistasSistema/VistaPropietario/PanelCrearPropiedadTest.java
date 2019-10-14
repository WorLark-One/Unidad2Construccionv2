/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xebae
 */
public class PanelCrearPropiedadTest {
    
    public PanelCrearPropiedadTest() {
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
     * Test of validarEntradaSector method, of class PanelCrearPropiedad.
     */
    @Test
    public void testValidarEntradaSector() throws SQLException {
        System.out.println("validarEntradaSector");
        String nombre = "222";
        String capacidad = "1";
        VentanaPrincipalPropietario papa= new VentanaPrincipalPropietario();
        PanelCrearPropiedad instance = new PanelCrearPropiedad(papa);
        int expResult = 0;
        int result = instance.validarEntradaSector(nombre, capacidad);
        assertEquals(expResult, result);

    }

    /**
     * Test of validarEntradaPropiedad method, of class PanelCrearPropiedad.
     * 
     */
   
    @Test
    public void testValidarEntradaPropiedad() throws SQLException {
        System.out.println("validarEntradaPropiedad");
        String nombre = "evento";
        String descripcion = "un grato lugar";
        String ubicacion = "en mi casa";
        String nSectores = "1";
        String valorArriendo = "10000";
        boolean finalizar = true;
        VentanaPrincipalPropietario papa = new VentanaPrincipalPropietario();
        PanelCrearPropiedad instance = new PanelCrearPropiedad(papa);
        int expResult = 0;
        int result = instance.validarEntradaPropiedad(nombre, descripcion, ubicacion, nSectores, valorArriendo, finalizar);
        System.out.println("resultado esperado:"+result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
