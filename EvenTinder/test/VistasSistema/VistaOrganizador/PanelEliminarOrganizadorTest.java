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
        PanelEliminarOrganizador instance = new PanelEliminarOrganizador(null);
        //error rut
        int expResult = 1;
        int result = instance.validarDatosEliminarOrganizador(rut, clave);
        assertEquals(expResult, result);
        //error clave
        rut = "19392599-5";
        expResult = 2;
        result = instance.validarDatosEliminarOrganizador(rut, clave);
        assertEquals(expResult, result);
        //exito
        clave = "1234567890";
        expResult = 0;
        result = instance.validarDatosEliminarOrganizador(rut, clave);
        assertEquals(expResult, result);
    }
    
}
