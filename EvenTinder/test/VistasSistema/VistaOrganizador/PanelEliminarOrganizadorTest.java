/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaOrganizador;

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
public class PanelEliminarOrganizadorTest {
    
    public PanelEliminarOrganizadorTest() {
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
     * Test of validarDatosEliminarOrganizador method, of class PanelEliminarOrganizador.
     */
    @Test
    public void testValidarDatosEliminarOrganizador() {
        System.out.println("validarDatosEliminarOrganizador");
        String rut = "";
        String clave = "";
        PanelEliminarOrganizador instance = null;
        int expResult = 0;
        int result = instance.validarDatosEliminarOrganizador(rut, clave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
