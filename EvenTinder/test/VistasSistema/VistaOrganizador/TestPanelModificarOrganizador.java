/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaOrganizador;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author crist
 */
public class TestPanelModificarOrganizador {
    VentanaPrincipalOrganizador papa = new VentanaPrincipalOrganizador();
    PanelModificarOrganizador pmo = new PanelModificarOrganizador(papa);
    
    public TestPanelModificarOrganizador(){}
    
    @Test
    public void testValidarModificarOrganizador(){

    String nombre = "";
    String clave = "";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(1,esperado); // faltaa el nombre
    }
    @Test
    public void testValidarModificarOrganizador1(){
    String nombre = "Cristobal";
    String clave = "";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(2,esperado); // faltaa la clave
    }
     @Test
    public void testValidarModificarOrganizador2(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(3,esperado); // faltaa el numeroTelefonico
    }
    @Test
    public void testValidarModificarOrganizador3(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(4,esperado); // faltaa el correoElectronico
    }
    @Test
    public void testValidarModificarOrganizador4(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(5,esperado); // faltaa la tarjetaDeCredito
    }
     @Test
    public void testValidarModificarOrganizador5(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "17586721";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(0,esperado); // esta todo bien 
    }
     @Test
    public void testValidarModificarOrganizador6(){
    String nombre = "/&%$";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "17586721";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(1,esperado); // nombre invalido, deveria dar 1
    }
     @Test
    public void testValidarModificarOrganizador7(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "CELULAR";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "17586721";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(3,esperado); // celular invalido, deveria dar 3 
    }
     @Test
    public void testValidarModificarOrganizador8(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo";
    String tarjetaDeCredito = "17586721";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(4,esperado); // correo invalido deberia dar 4
    }
     @Test
    public void testValidarModificarOrganizador9(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String tarjetaDeCredito = "Targeta";
    int esperado = pmo.validarModificarOrganizador(nombre, clave, numeroTelefonico, correoElectronico, tarjetaDeCredito);
    assertEquals(5,esperado); // tarjetaDeCredito invalida deveria dar 5
    }
    
}
