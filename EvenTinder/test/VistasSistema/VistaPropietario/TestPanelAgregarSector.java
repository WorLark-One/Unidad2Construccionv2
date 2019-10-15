/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
/**
 *
 * @author crist
 */
// en PanelAgregarSector se debe hacer static validarEntrada y isNumero (esta se debe pasar a public)
public class TestPanelAgregarSector {
    PanelAgregarSector pas;
    String cadena = "";
    public TestPanelAgregarSector () throws SQLException{
        this.pas = null;
    }
    @Test
    public void testValidarDatosAgregarSector1(){
        String rut = "";
        String clave = "";
        int esperado = this.pas.validarEntrada(rut, clave);
        assertEquals(1,esperado); // falta el rut
    }
    @Test
    public void testValidarDatosAgregarSector2(){
        String rut = "17586721k";
        String clave = "";
        int esperado = pas.validarEntrada(rut, clave);
        assertEquals(2,esperado); // falta la clave
    }
    @Test
    public void testValidarDatosAgregarSector3(){
        String rut = "17586721k";
        String clave = "123654";
        int esperado = this.pas.validarEntrada(rut, clave);
        assertEquals(0,esperado); // esta bien 
    }    
    @Test
    public void testValidarNumeros1(){
        this.cadena = "1";
        boolean esperado = this.pas.isNumero(cadena);
         assertEquals(true,esperado);
    }
    @Test
    public void testValidarNumeros2(){
        this.cadena = "uno";
        boolean esperado = this.pas.isNumero(cadena);
         assertEquals(false,esperado);
    }
    @Test
    public void testValidarNumeros3(){
        this.cadena = "&%$";
        boolean esperado = this.pas.isNumero(cadena);
         assertEquals(false,esperado);
    }
        @Test
    public void testValidarNumeros(){
        this.cadena = "-10";
        boolean esperado = this.pas.isNumero(cadena);
         assertEquals(false,esperado);
    }
}
