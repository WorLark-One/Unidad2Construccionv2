/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaSistema.VistaPrincipal;
import VistasSistema.VistaPrincipal.PanelInicioSesion;
import VistasSistema.VistaPrincipal.VentanaPrincipal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author crist
 */
public class TestPanelInicioSesion {
    PanelInicioSesion p = new PanelInicioSesion();
    
    public TestPanelInicioSesion(){
    }
    
    String tipoUsuario = "";
    String rut = "";
    String clave = "";
    
    @Test
    public void validarDatos(){
    int casos = 0;
    int aux = 0;
    switch (casos){
        case 0: //1 falta tipiUsuario
            this.tipoUsuario = "";
            this.rut = "";
            this.clave = "";
            break;
        case 1:  //2 falta rut
            this.tipoUsuario = "propietario";
            this.rut = "";
            this.clave = "";
            break;
        case 2: //3 falta clave
            this.tipoUsuario = "propietario";
            this.rut = "propietario";
            this.clave = "";
            break;
        case 3: //0 es ok
            this.tipoUsuario = "propietario";
            this.rut = "17586721k";
            this.clave = "clave";
            break;
        case 4:  //0 es ok
            this.tipoUsuario = "caca";
            this.rut = "caca";
            this.clave = "clave";
            break;
    }
    aux = this.p.validarDatosIniciarSesion(this.tipoUsuario, this.rut, this.clave);
    switch (casos){
        case 0: //1 falta tipiUsuario
            assertEquals(1, aux);
            break;
        case 1:  //2 falta rut
            assertEquals(2, aux);
            break;
        case 2: //3 falta clave
            assertEquals(3, aux);
            break;
        case 3: //0 es ok
            assertEquals(0, aux);
            break;
        case 4:  //1 el tipo de usuauio es invalido
            assertEquals(1, aux);
            break;
    }
    
}
    
    
    
    
    
}
