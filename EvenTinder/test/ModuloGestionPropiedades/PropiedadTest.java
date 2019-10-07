/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionPropiedades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author braya
 */
public class PropiedadTest {
    Propiedad propiedad;
    

    
    @Before
    public void before() throws ParseException
    {
        System.out.println("a\u00f1adirSector");
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "brayan", "un grato lugar para pasar en familia", "talca", dt_1, 0, 10, 2220);
        Sector sector = new Sector(1, "prueba2", 1000);
        propiedad.añadirSector(sector);
    }

    
    @Test
    public void testAñadirSector() throws ParseException {
        
        Sector sector = new Sector(1, "prueba1", 10);
        int esperado = 2;//propiedad.numeroSectore();
        propiedad.añadirSector(sector);// añado el sector
        int result = propiedad.numeroSectore();
        assertEquals(esperado, result);
        
    }
    @Test
    public void testAñadirSectorTipoDato() throws ParseException {
        
        Sector sector = new Sector(1, "hola", 0);
        boolean esperado = true;//propiedad.numeroSectore();
        
        boolean result =propiedad.añadirSector(sector);
        assertEquals(esperado, result);
        
    }
    
  /**
     * Test of modificarSector method, of class Propiedad.
     */
    @Test
    public void testModificarSector() {
        System.out.println("modificarSector");
        int id = 0;
        String nombre = "prueba test";
        int capSector = 100000;
        boolean expResult = true;
        boolean result = propiedad.modificarSector("prueba2", 1, "nuevo nombre", 1000);
        assertEquals(expResult, result);
        
    }
    
     /**
     * Test of eliminarSector method, of class Propiedad.
     */
    @Test
    public void testEliminarSector() {
        System.out.println("eliminarSector");
        
        boolean expResult = true;
        boolean result = propiedad.eliminarSector("prueba2",1);
        assertEquals(expResult, result);
        
    }

    
}
