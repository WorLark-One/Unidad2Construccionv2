/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionEventos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author braya
 */
public class EventoTest {
    Evento evento;
    
    @Before
    public void before() throws ParseException
    {
        System.out.println("a\u00f1adirSector");
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        evento = new Evento(1, "mi evento", "un evento con grato ambiente", dt_1, dt_1, 100, 10, false);
        Entrada entrada = new Entrada(2, 500);
        evento.AñadirEntrada(entrada);
        
    }
    
    /**
     * Test of AñadirEntrada method, of class Evento.
     */
    @Test
    public void testAñadirEntrada() {
        System.out.println("A\u00f1adirEntrada");
        Entrada entrada = new Entrada(1, 500);
        
        boolean expResult = true;
        boolean result = evento.AñadirEntrada(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarEntrada method, of class Evento.
     */
    @Test
    public void testEliminarEntrada() {
        System.out.println("eliminarEntrada");
        int idEntrada = 2;
        boolean expResult = true;
        boolean result = evento.eliminarEntrada(idEntrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of verificarDisponibilidadDeEntrada method, of class Evento.
     */
    @Test
    public void testVerificarDisponibilidadDeEntrada() {
        System.out.println("verificarDisponibilidadDeEntrada");
        boolean expResult = true;
        boolean result = evento.verificarDisponibilidadDeEntrada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    
}
