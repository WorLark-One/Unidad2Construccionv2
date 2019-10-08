/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;

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
public class PanelModificarPropietarioTest {
    
    public PanelModificarPropietarioTest() {
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
     * Test of validarEntrada method, of class PanelModificarPropietario.
     */
    @Test
    public void testValidarEntrada() {
        System.out.println("validarEntrada");
        String nombre = "";
        String clave = "";
        String numeroTelefonico = "";
        String correoElectronico = "";
        String cuentaBancaria = "";
        PanelModificarPropietario instance = null;
        int expResult = 0;
        int result = instance.validarEntrada(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
