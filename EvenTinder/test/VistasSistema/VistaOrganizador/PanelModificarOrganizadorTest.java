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
public class PanelModificarOrganizadorTest {
    
    public PanelModificarOrganizadorTest() {
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
     * Test of validarModificarOrganizador method, of class PanelModificarOrganizador.
     */
    @Test
    public void testValidarModificarOrganizador() {
        System.out.println("validarModificarOrganizador");
        String nombre = "";
        String clave = "";
        String numeroTelefonico = "";
        String correoElectronico = "";
        String tarjetaDeCredito = "";
        PanelModificarOrganizador instance = null;
        int expResult = 0;
        int result = instance.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
