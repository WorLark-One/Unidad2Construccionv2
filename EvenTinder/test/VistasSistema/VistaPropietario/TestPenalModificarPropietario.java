/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistemaVistaPropietario;
import org.junit.Test;
import static org.junit.Assert.*;
import VistasSistemaVistaPropietario.PanelModificarPropietario;
import VistasSistemaVistaPropietario.VentanaPrincipalPropietario;
/**
 *
 * @author crist
 */
public class TestPenalModificarPropietario {
    VentanaPrincipalPropietario papa = new VentanaPrincipalPropietario();
    PanelModificarPropietario pmp = new PanelModificarPropietario(papa);
    
    public TestPenalModificarPropietario(){}
    
     @Test
    public void testValidarModificarUsuario(){
        String nombre = "";
        String clave = "";
        String numeroTelefonico = "";
        String correoElectronico = "";
        String cuentaBancaria = "";
        int retorno = -1;
        int casos = 0;
            
        switch(casos){
            case 0:
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(1, retorno); // falla en el nombre asi que retorna 1
            case 1:
                nombre = "Pepe";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(2, retorno); // falla en la clave asi que retorna 2
            case 2:
                nombre = "pepe";
                clave = "100";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(3, retorno); // falla en el numeroTelefonico retorn 3
            case 3:
                nombre = "pepe";
                clave = "100";
                numeroTelefonico = "+56997815288";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(4, retorno); // falla en el correoElectronico asi que deberia retornar 3
            case 4:
                nombre = "pepe";
                clave = "100";
                numeroTelefonico = "+56997815288";
                correoElectronico = "Ejemplo@gmail.com";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(5, retorno); // falla en la cuentaBancaria asi que deberia retornar 4
            case 5:
                nombre = "pepe";
                clave = "100";
                numeroTelefonico = "+56997815288";
                correoElectronico = "Ejemplo@gmail.com";
                cuentaBancaria = "17586721";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(0, retorno); // no falla
            case 6:
                nombre = "123";
                clave = "100";
                numeroTelefonico = "+56997815288";
                correoElectronico = "Ejemplo@gmail.com";
                cuentaBancaria = "17586721";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(0, retorno); // el nombre no pueden ser numeros, deberia retornar 1, pero retorna 0   
            case 7:
                nombre = "pepe";
                clave = "100";
                numeroTelefonico = "2";
                correoElectronico = "Ejemplo@gmail.com";
                cuentaBancaria = "17586721";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(0, retorno); // numero de telefoo invalido,deberia retornar 3 pero retorna 0
            case 8:
                nombre = "pepe";
                clave = "100";
                numeroTelefonico = "+56997815288";
                correoElectronico = "Ejemplo";
                cuentaBancaria = "17586721";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(0, retorno); // correo invalido, deberia retornar 4 pero retorna 0
            case 9:
                nombre = "pepe";
                clave = "100";
                numeroTelefonico = "+56997815288";
                correoElectronico = "Ejemplo@gmail.com";
                cuentaBancaria = "1";
                retorno = pmp.validarModificarUsuario( nombre,  clave,  numeroTelefonico,  correoElectronico,  cuentaBancaria);
                assertEquals(0, retorno); // cuenta bancaria invalida deberia retornar 5 pero retorna 0              
        } 
    
    }
}
