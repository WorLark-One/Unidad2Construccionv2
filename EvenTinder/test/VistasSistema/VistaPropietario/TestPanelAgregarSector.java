/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;
import org.junit.Test;
import static org.junit.Assert.*;
import VistasSistema.VistaPropietario.PanelAgregarSector;
import VistasSistema.VistaPropietario.VentanaPrincipalPropietario;
/**
 *
 * @author crist
 */
public class TestPanelAgregarSector {
    VentanaPrincipalPropietario papa = new VentanaPrincipalPropietario();
    PanelAgregarSector pas = new PanelAgregarSector(papa, 1);
    
    public TestPanelAgregarSector (){}
    
    @Test
    public void testValidarEntrada(){
        int casos = -1;
    }
}
