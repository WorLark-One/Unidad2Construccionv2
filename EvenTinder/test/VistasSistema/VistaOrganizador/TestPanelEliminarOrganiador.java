/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaOrganizador;;
import org.junit.Test;
import static org.junit.Assert.*;
import VistasSistema.VistaOrganizador.VentanaPrincipalOrganizador;
import VistasSistema.VistaOrganizador.PanelEliminarOrganizador;
/**
 *
 * @author crist
 */
public class TestPanelEliminarOrganiador {
    VentanaPrincipalOrganizador papa = new VentanaPrincipalOrganizador();
    PanelEliminarOrganizador peo = new PanelEliminarOrganizador(papa);
    
    public TestPanelEliminarOrganiador(){}
    
    @Test
    public void testValidarDatosEliminarOrganizador1(){
        String rut = "";
        String clave = "";
        int esperado = peo.validarDatosEliminarOrganizador(rut, clave);
        assertEquals(1,esperado); // falta el rut
    }
    @Test
    public void testValidarDatosEliminarOrganizador2(){
        String rut = "17586721k";
        String clave = "";
        int esperado = peo.validarDatosEliminarOrganizador(rut, clave);
        assertEquals(2,esperado); // falta la clave
    }
    @Test
    public void testValidarDatosEliminarOrganizador3(){
        String rut = "17586721k";
        String clave = "123654";
        int esperado = peo.validarDatosEliminarOrganizador(rut, clave);
        assertEquals(0,esperado); // esta bien 
    }    
    
}
