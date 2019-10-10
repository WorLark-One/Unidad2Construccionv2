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
        PanelModificarOrganizador instance = new PanelModificarOrganizador(null);
        //error nombre
        int expResult = 1;
        int result = instance.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
        assertEquals(expResult, result);
        //error clave
        nombre = "Sebastian Ibarra";
        expResult = 2;
        result = instance.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
        assertEquals(expResult, result);
        //error numeroTelefonico
        clave = "1234567890";
        expResult = 3;
        result = instance.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
        assertEquals(expResult, result);
        //error correoElectronico
        numeroTelefonico = "994049789";
        expResult = 4;
        result = instance.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
        assertEquals(expResult, result);
        //error tarjetaDeCredito
        correoElectronico = "xebaelvemgador@gmail.com";
        expResult = 5;
        result = instance.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
        assertEquals(expResult, result);
        //Exito
        tarjetaDeCredito = "5573457894455";
        expResult = 0;
        result = instance.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
        assertEquals(expResult, result);
    }
    
}
