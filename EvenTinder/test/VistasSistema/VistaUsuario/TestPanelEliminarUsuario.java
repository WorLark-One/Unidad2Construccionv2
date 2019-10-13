/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaUsuario;
import VistasSistema.VistaUsuario.VentanaPrincipalUsuario;
import VistasSistema.VistaUsuario.PanelEliminarUsuario;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 *
 * @author crist
 */
public class TestPanelEliminarUsuario {
    VentanaPrincipalUsuario papa = new VentanaPrincipalUsuario();
    PanelEliminarUsuario peu = new PanelEliminarUsuario(papa);
    public TestPanelEliminarUsuario(){}
    
    @Test
    public void testValidarDatosEliminarUsuario1(){
        String rut = "";
        String clave = "";
        int esperado = peu.validarDatosEliminarUsuario(rut, clave);
        assertEquals(1,esperado); // falta el rut
    }
    @Test
    public void testValidarDatosEliminarUsuario2(){
        String rut = "17586721k";
        String clave = "";
        int esperado = peu.validarDatosEliminarUsuario(rut, clave);
        assertEquals(2,esperado); // falta la clave
    }
    @Test
    public void testValidarDatosEliminarUsuario3(){
        String rut = "17586721k";
        String clave = "123654";
        int esperado = peu.validarDatosEliminarUsuario(rut, clave);
        assertEquals(0,esperado); // esta bien 
    }    
}
