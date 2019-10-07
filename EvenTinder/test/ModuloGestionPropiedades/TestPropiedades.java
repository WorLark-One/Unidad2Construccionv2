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
 * @author crist
 */
public class TestPropiedades {
    Propiedad propiedad;
    

    
    @Test    
    public void testAñadirSector() throws ParseException {
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba1", 1000);
        boolean esperado = propiedad.añadirSector(sector);
        assertEquals(true,esperado);
    }    
    @Test
    public void testModificarSectorListavacia()throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        boolean esperado = propiedad.modificarSector("prueba1", 1, "nuevo nombre", 1000);
        assertEquals(false,esperado);
    }
    @Test
    public void testModificarSector()throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba1", 1000);
         boolean esperado = propiedad.añadirSector(sector);
         esperado = propiedad.modificarSector("prueba1", 1, "nuevo nombre", 1000);
        assertEquals(true,esperado);
    }
    @Test
    public void testModificarSectorIdErroneo()throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba1", 1000);
         boolean esperado = propiedad.añadirSector(sector);
         esperado = propiedad.modificarSector("prueba1", 2, "nuevo nombre", 1000);
        assertEquals(false,esperado);
    }
    @Test
    public void testModificarSectorNombreErroneo()throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba1", 1000);
         boolean esperado = propiedad.añadirSector(sector);
         esperado = propiedad.modificarSector("prueba2", 1, "nuevo nombre", 1000);
        assertEquals(false,esperado);
    }
    
    @Test
    public void testEliminarSectorListaVacia() throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        boolean esperado = propiedad.eliminarSector("prueba",1);
         assertEquals(false,esperado);
    }
    @Test
    public void testEliminarSector() throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba", 1000);
        boolean esperado = propiedad.añadirSector(sector);
        esperado = propiedad.eliminarSector("prueba",1);
         assertEquals(true, esperado);
    }
    @Test
    public void testEliminarSectorConIdErroneo() throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba", 1000);
        boolean esperado = propiedad.añadirSector(sector);
        esperado = propiedad.eliminarSector("prueba",2);
         assertEquals(false, esperado); // los id no coinciden
    }
    @Test
    public void testEliminarSectorConNombreErroneo() throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba", 1000);
        boolean esperado = propiedad.añadirSector(sector);
        esperado = propiedad.eliminarSector("prueba2",1);
         assertEquals(false, esperado);//los nombres no coinsiden
    }
    
    /* se verifican con las entradas
    @Test
    public void testAñadirSectorNull() throws ParseException {
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = null;
        boolean esperado = propiedad.añadirSector(sector);
        assertEquals(esperado, true); // deberia fallar por que no agrega nada
    }
    @Test
    public void testAñadirSectorID() throws ParseException {
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        Sector sector = new Sector(1, "prueba1", 1000);
        Sector sector1 = new Sector(1, "prueba1", 1000);
        boolean esperado = propiedad.añadirSector(sector);
        esperado = propiedad.añadirSector(sector1);
        assertEquals(esperado, true); // permite agregar sectores con el mismo ID
    }
    @Test
    public void testAñadirSectorPersonaNegativo() throws ParseException {
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", -50, 50000);
        Sector sector = new Sector(1, "prueba1", 1000);
        boolean esperado = propiedad.añadirSector(sector);
        assertEquals(esperado, true); // permite agregar sectores con el mismo ID
    }
    @Test
    public void testAñadirSectorValorNegativo() throws ParseException {
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, -50000);
        Sector sector = new Sector(1, "prueba1", 1000);
        boolean esperado = propiedad.añadirSector(sector);
        assertEquals(esperado, true); // permite agregar sectores con el mismo ID
    }
    @Test
    public void testModificarSectorVacio()throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        propiedad = new Propiedad(1, "nombre", "lugar", dt_1, "pasaje 4", 50, 50000);
        boolean esperado = propiedad.modificarSector("prueba", 1, "prueba1", 1000);
        assertEquals(esperado, false);
    }
    */
}
