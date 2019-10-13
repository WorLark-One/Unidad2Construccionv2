/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaUsuario;
import VistasSistema.VistaUsuario.VentanaPrincipalUsuario;
import VistasSistema.VistaUsuario.PanelModificarUsuario;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 *
 * @author crist
 */
public class TestPanelModificarUsuario {
    VentanaPrincipalUsuario papa = new VentanaPrincipalUsuario();
    PanelModificarUsuario pmu = new PanelModificarUsuario(papa);
    public TestPanelModificarUsuario(){}
    @Test
    public void testValidarModificarUsuario(){
    String nombre = "";
    String clave = "";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(1,esperado); // faltaa el nombre
    }
    @Test
    public void testValidarModificarUsuario1(){
    String nombre = "Cristobal";
    String clave = "";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(2,esperado); // faltaa la clave
    }
     @Test
    public void testValidarModificarUsuario2(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(3,esperado); // faltaa el numeroTelefonico
    }
    @Test
    public void testValidarModificarUsuario3(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(4,esperado); // faltaa el correoElectronico
    }
    @Test
    public void testValidarModificarUsuario4(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(5,esperado); // faltaa la tarjetaDeCredito
    }
     @Test
    public void testValidarModificarUsuario5(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "17586721";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(0,esperado); // esta todo bien 
    }
     @Test
    public void testValidarModificarUsuario6(){
    String nombre = "/&%$";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "17586721";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(1,esperado); // nombre invalido, deveria dar 1
    }
     @Test
    public void testValidarModificarUsuario7(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "CELULAR";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "17586721";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(3,esperado); // celular invalido, deveria dar 3 
    }
     @Test
    public void testValidarModificarUsuario8(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo";
    String tarjetaDeCredito = "17586721";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(4,esperado); // correo invalido deberia dar 4
    }
     @Test
    public void testValidarModificarUsuario9(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "Targeta";
    int esperado = pmu.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(5,esperado); // tarjetaDeCredito invalida deveria dar 5
    }
    
}

