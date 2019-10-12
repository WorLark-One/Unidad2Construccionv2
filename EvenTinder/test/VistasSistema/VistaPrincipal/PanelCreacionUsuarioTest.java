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
        String tipoUsuario = "cliente";
        String nombre = "";
        String rut = "";
        String clave = "";
        String numeroTelefonico = "";
        String correoElectronico = "";
        String tarjetaDeCredito = "";
        String CuentaBancaria = "";
        PanelCreacionUsuario instance = new PanelCreacionUsuario(null);
        int expResult = 1;        
        
        System.out.println("nombre (solo con letras)");
        nombre = "123&&/";
        int result = instance.validarResgistro(tipoUsuario, nombre, rut, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito, CuentaBancaria);        
        expResult = 2;
        assertEquals(expResult, result);
        
        //PENDIENTE VALIDAR PUNTOS Y GUIONES.
        System.out.println("rut");
        nombre = "aaaaaaa";
        rut = "asdasdasdasda";
        result = instance.validarResgistro(tipoUsuario, nombre, rut, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito, CuentaBancaria);
        expResult = 3;
        assertEquals(expResult, result);
        
        System.out.println("clave (solo letras y numeros)");
        nombre = "matias";
        rut = "11.111.111-1";
        clave = "!#$%&/()=";
        result =instance.validarResgistro(tipoUsuario, nombre, rut, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito, CuentaBancaria);
        expResult = 4;
        assertEquals(expResult, result);
        
        
        System.out.println("numero telefonico (solo 9 numeros)");
        nombre = "matias";
        rut = "11.111.111-1";
        clave = "12345678";
        numeroTelefonico = "asd12312";
        result =instance.validarResgistro(tipoUsuario, nombre, rut, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito, CuentaBancaria);
        expResult =5;
        assertEquals(expResult, result);
        
        System.out.println("correo (con arroba, minimo 1 dominio, maximo 2");
        nombre = "matias";
        rut = "11.111.111-1";
        clave = "12345678";
        numeroTelefonico = "123456789";
        correoElectronico = "mpizarro16@12.ut%%alca.com";
        result =instance.validarResgistro(tipoUsuario, nombre, rut, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito, CuentaBancaria);
        expResult = 6;
        assertEquals(expResult, result);
        
        System.out.println("tarjeta de credito (16 numeros)");
        nombre = "matias";
        rut = "11.111.111-1";
        clave = "12345678";
        numeroTelefonico = "123456789";
        correoElectronico = "mpizarro16@alumnos.utalca.cl";
        tarjetaDeCredito = "1234 5678 3412";
        result =instance.validarResgistro(tipoUsuario, nombre, rut, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito, CuentaBancaria);
        expResult = 7;
        assertEquals(expResult, result);      
    }

    /**
     * Test of actualizarMenuOpciones method, of class PanelCreacionUsuario.
     */
    @Test
    public void testActualizarMenuOpciones() {
        System.out.println("actualizarMenuOpciones");
        PanelCreacionUsuario instance = null;
        instance.actualizarMenuOpciones();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
