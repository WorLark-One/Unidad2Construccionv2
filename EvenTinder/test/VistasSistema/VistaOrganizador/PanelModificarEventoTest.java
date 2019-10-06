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
public class PanelModificarEventoTest {
    
    public PanelModificarEventoTest() {
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
     * Test of validarDatos method, of class PanelModificarEvento.
     */
    @Test
    public void testValidarDatos() {
        System.out.println("validarDatos");
        String nombre = "";
        String fechaDeInicio = "";
        String fechaDeTermino = "";
        String capacidad = "";
        String descripcion = "";
        String diasMaximos = "";
        PanelModificarEvento instance = new PanelModificarEvento();
        int expResult = 0;
        int result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
