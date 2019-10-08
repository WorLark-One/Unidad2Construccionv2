/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistemaVistaPropietario;
import org.junit.Test;
import static org.junit.Assert.*;
import VistasSistema.VistaPropietario.VentanaPrincipalPropietario;
import VistasSistema.VistaPropietario.PanelModificarPropietario;
import java.sql.SQLException;
/**
 *
 * @author crist
 */
public class TestPanelModificarPropietario {
    VentanaPrincipalPropietario papa;
    PanelModificarPropietario pmp;
    
    public TestPanelModificarPropietario()throws SQLException {
    this.papa = new VentanaPrincipalPropietario();
    this.pmp = new PanelModificarPropietario(papa);
    }
    @Test
    public void testValidarModificarPropietario(){
    String nombre = "";
    String clave = "";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String cuentaBancaria = "";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(1,esperado); // faltaa el nombre
    }
    @Test
    public void testValidarModificarOrganizador1(){
    String nombre = "Cristobal";
    String clave = "";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String cuentaBancaria = "";
     int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(2,esperado); // faltaa la clave
    }
     @Test
    public void testValidarModificarOrganizador2(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String cuentaBancaria = "";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(3,esperado); // faltaa el numeroTelefonico
    }
    @Test
    public void testValidarModificarPropietario3(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "";
    String cuentaBancaria = "";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(4,esperado); // faltaa el correoElectronico
    }
    @Test
    public void testValidarModificarPropietario4(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String cuentaBancaria = "";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(5,esperado); // faltaa la cuentaBancaria
    }
     @Test
    public void testValidarModificarPropietario5(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String cuentaBancaria = "17586721";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(0,esperado); // esta todo bien 
    }
     @Test
    public void testValidarModificarPropietario6(){
    String nombre = "/&%$";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String cuentaBancaria = "17586721";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(1,esperado); // nombre invalido, deveria dar 1
    }
     @Test
    public void testValidarModificarPropietario7(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "CELULAR";
    String correoElectronico = "ejemplo@gmail.com";
    String cuentaBancaria = "17586721";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(3,esperado); // celular invalido, deveria dar 3 
    }
     @Test
    public void testValidarModificarPropietario8(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo";
    String cuentaBancaria = "17586721";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(4,esperado); // correo invalido deberia dar 4
    }
     @Test
    public void testValidarModificarPropietario9(){
    String nombre = "Cristobal";
    String clave = "123654";
    String numeroTelefonico = "+56997815288";
    String correoElectronico = "ejemplo@gmail.com";
    String cuentaBancaria = "Targeta";
    int esperado = pmp.validarModificarUsuario(nombre, clave, numeroTelefonico, correoElectronico, cuentaBancaria);
    assertEquals(5,esperado); // cuentaBancaria invalida deveria dar 5
    }
    
    
    
}
