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
        PanelModificarEvento3 instance = new PanelModificarEvento3(null);
        //fallo con nombre
        int expResult = 1;
        int result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        //fallo con fechaDeInicio
        nombre = "Primer evento de EvenTinder";
        expResult = 2;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        //fallo con fechaDeTermino
        fechaDeInicio = "1/9/2019";
        expResult = 3;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        //fallo con capacidad
        fechaDeTermino = "11/10/2019";
        expResult = 4;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        //fallo con descripcion
        capacidad = "13";
        expResult = 5;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        //fallo con diasMaximos
        descripcion = "test descripcion";
        expResult = 6;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        //devuelve correcto
        diasMaximos = "13";
        expResult = 0;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
    }
    
}
