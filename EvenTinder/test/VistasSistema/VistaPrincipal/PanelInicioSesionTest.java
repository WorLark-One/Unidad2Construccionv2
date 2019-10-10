/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPrincipal;

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
public class PanelInicioSesionTest {
    
    public PanelInicioSesionTest() {
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
     * Test of validarDatosIniciarSesion method, of class PanelInicioSesion.
     */
    @Test
    public void testValidarDatosIniciarSesion() {
        System.out.println("validarDatosIniciarSesion");
        String tipoUsuario = "";
        String rut = "";
        String clave = "";
        PanelInicioSesion instance = new PanelInicioSesion();
        int expResult = 0;
        int result = instance.validarDatosIniciarSesion(tipoUsuario, rut, clave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
