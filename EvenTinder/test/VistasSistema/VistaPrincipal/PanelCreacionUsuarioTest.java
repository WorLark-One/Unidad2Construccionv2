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
public class PanelCreacionUsuarioTest {
    
    public PanelCreacionUsuarioTest() {
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
     * Test of validarResgistro method, of class PanelCreacionUsuario.
     */
    @Test
    public void testValidarResgistro() {
        System.out.println("validarResgistro");
        String tipoUsuario = "";
        String nombre = "";
        String rut = "";
        String clave = "";
        String numeroTelefonico = "";
        String correoElectronico = "";
        String tarjetaDeCredito = "";
        String CuentaBancaria = "";
        PanelCreacionUsuario instance = null;
        int expResult = 0;
        int result = instance.validarResgistro(tipoUsuario, nombre, rut, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito, CuentaBancaria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
