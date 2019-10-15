/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;
import org.junit.Test;
import static org.junit.Assert.*;
import VistasSistema.VistaPropietario.PanelEliminarPropietario;
import VistasSistema.VistaPropietario.VentanaPrincipalPropietario;
import java.sql.SQLException;

/**
 *
 * @author crist
 */
public class TestPanelEliminarPropietario{
    VentanaPrincipalPropietario papa;
    PanelEliminarPropietario pep;
    public TestPanelEliminarPropietario()throws SQLException {
        this.papa = new VentanaPrincipalPropietario();
        this.pep = new PanelEliminarPropietario(papa);
    }
    @Test
    public void testValidarDatosEliminarPropietario1(){
        String rut = "";
        String clave = "";
        int esperado = pep.validarEntrada(rut, clave);
        assertEquals(1,esperado); // falta el rut
    }
    @Test
    public void testValidarDatosEliminarPropietario2(){
        String rut = "17586721k";
        String clave = "";
        int esperado = pep.validarEntrada(rut, clave);
        assertEquals(2,esperado); // falta la clave
    }
    @Test
    public void testValidarDatosEliminarPropietario3(){
        String rut = "17586721k";
        String clave = "123654";
        int esperado = pep.validarEntrada(rut, clave);
        assertEquals(0,esperado); // esta bien 
    }    
    
}
