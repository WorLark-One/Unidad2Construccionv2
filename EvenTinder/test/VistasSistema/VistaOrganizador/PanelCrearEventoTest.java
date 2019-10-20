/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaOrganizador;

import VistasSistema.VistaPrincipal.VentanaPrincipal;
import java.sql.SQLException;
import java.text.ParseException;
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
public class PanelCrearEventoTest {
    
    public PanelCrearEventoTest() {
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
     * Test of validarDatos method, of class PanelCrearEvento.
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
        PanelCrearEvento instance = new PanelCrearEvento(null);
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
        descripcion = "teste descripcion";
        expResult = 6;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
        //devuelve correcto
        diasMaximos = "13";
        expResult = 0;
        result = instance.validarDatos(nombre, fechaDeInicio, fechaDeTermino, capacidad, descripcion, diasMaximos);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testValidarNombre() throws SQLException{       
        VentanaPrincipalOrganizador v  = new VentanaPrincipalOrganizador();
        PanelCrearEvento instance = new PanelCrearEvento(v);
        String nombre = "";
        int expResult = 1;
        
        //validar nombre vacio
        int result = instance.validarNombre(nombre);        
        assertEquals(expResult, result);
        
        nombre = "nombre";
        expResult = 0;
        result = instance.validarNombre(nombre);        
        assertEquals(expResult, result);
        
        nombre = "#$%&/()";
        
        expResult = 1;
        result = instance.validarNombre(nombre);        
        assertEquals(expResult, result);
        
        nombre = "Evento 1";
        
        expResult = 0;
        result = instance.validarNombre(nombre);        
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void validarDescripcion() throws SQLException{
        VentanaPrincipalOrganizador v  = new VentanaPrincipalOrganizador();
        PanelCrearEvento instance = new PanelCrearEvento(v);
        String descripcion = "";
        int expResult = 2;
        
        //validar descripcion vacia
        int result = instance.validarDescripcion(descripcion);        
        assertEquals(expResult, result);
        
        //validar tamaño maximo, pero acepta cualquier caracter
        expResult = 2;
        descripcion = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        result = instance.validarDescripcion(descripcion);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void validarFechas() throws SQLException, ParseException{
        VentanaPrincipalOrganizador v  = new VentanaPrincipalOrganizador();
        PanelCrearEvento instance = new PanelCrearEvento(v);
        String inicio = "";
        String termino = "";
        String dias = "";
        int expResult = 3;
        
        //validar fecha vacia
        int result = instance.validarFechas(inicio,termino,dias);        
        assertEquals(expResult, result);
    
        //validar formato valido
        expResult = 4;
        inicio = "asd";
        termino = "123";
        dias = "xd";
        result = instance.validarFechas(inicio,termino,dias);
        assertEquals(expResult, result);
        
        //validar fecha valida
        expResult = 5;
        inicio = "19/10/2019";
        termino = "18/10/1928";
        dias = "-1";
        result = instance.validarFechas(inicio,termino,dias);
        assertEquals(expResult, result);
        
        expResult = 0;
        inicio = "22/12/2019";
        termino = "23/01/2020";
        dias ="20";
        result = instance.validarFechas(inicio,termino,dias);
        assertEquals(expResult, result);
    }   
    
    
    @Test
    public void validarCapacidad() throws SQLException{
        VentanaPrincipalOrganizador v  = new VentanaPrincipalOrganizador();
        PanelCrearEvento instance = new PanelCrearEvento(v);
        String capacidad = "";
        int expResult = 7;
        
        //validar capacidad vacia
        int result = instance.validarCapacidad(capacidad);        
        assertEquals(expResult, result);
        
        
        //validar que es un numero valido
        expResult = 7;
        capacidad = "asd";
        result = instance.validarCapacidad(capacidad);
        assertEquals(expResult, result);
        
        //capacidad valida
        expResult = 0;
        capacidad = "123";
        result = instance.validarCapacidad(capacidad);
        assertEquals(expResult, result);

    }
    

}
