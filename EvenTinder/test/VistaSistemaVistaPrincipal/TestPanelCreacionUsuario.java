/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaSistemaVistaPrincipal;

import VistasSistemaVistaPrincipal.VentanaPrincipal;
import VistasSistemaVistaPrincipal.VentanaPrincipal;
import VistasSistemaVistaPrincipal.PanelCreacionUsuario;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author crist
 */
public class TestPanelCreacionUsuario {
    VentanaPrincipal papa = new VentanaPrincipal();
    PanelCreacionUsuario p = new PanelCreacionUsuario(papa);
    public TestPanelCreacionUsuario(){
    }
    String tipoUsuario = "";
    String nombre = "";
    String rut = "";
    String clave = "";
    String numeroTelefonico = "";
    String correoElectronico = "";
    String tarjetaDeCredito = "";
    String CuentaBancaria = "";
    
    
    
    @Test    
    public void validarDatos(){
    int casos = 10;
    int aux = 0;
        switch (casos){
            case 0:  //1 falta tipiUsuario
                this.tipoUsuario = "";
                this.nombre="";
                this.rut = "";
                this.clave = "";
                this.numeroTelefonico = "";
                this.correoElectronico = "";
                this.tarjetaDeCredito = "";
                this.CuentaBancaria = "";
                break;
            case 1:  //2 falta nombre
                this.tipoUsuario = "Propietario";
                this.nombre="";
                this.rut = "";
                this.clave = "";
                this.numeroTelefonico = "";
                this.correoElectronico = "";
                this.tarjetaDeCredito = "";
                this.CuentaBancaria = "";
                break;
            case 2: //3 falta rut
                this.tipoUsuario = "Propietario";
                this.nombre="critobal";
                this.rut = "";
                this.clave = "";
                this.numeroTelefonico = "";
                this.correoElectronico = "";
                this.tarjetaDeCredito = "";
                this.CuentaBancaria = "";
                break;
            case 3: //3 falta clave
                this.tipoUsuario = "Propietario";
                this.nombre="critobal";
                this.rut = "17586721k";
                this.clave = "";
                this.numeroTelefonico = "";
                this.correoElectronico = "";
                this.tarjetaDeCredito = "";
                this.CuentaBancaria = "";
                break;
            case 4: //4 falta numeroTelefonico
                this.tipoUsuario = "Propietario";
                this.rut = "17586721k";
                this.clave = "clave";
                this.numeroTelefonico = "";
                this.correoElectronico = "";
                this.tarjetaDeCredito = "";
                this.CuentaBancaria = "";
                break;
            case 5: // 5 falta numeroTelefonico
                this.tipoUsuario = "Propietario";
                this.rut = "17586721k";
                this.clave = "clave";
                this.numeroTelefonico = "";
                this.correoElectronico = "";
                this.tarjetaDeCredito = "";
                this.CuentaBancaria = "";
                break;
            case 6:  //6 falta correoElectronico
                this.tipoUsuario = "Propietario";
                this.rut = "17586721k";
                this.clave = "clave";
                this.numeroTelefonico = "97815288";
                this.correoElectronico = "";
                this.tarjetaDeCredito = "";
                this.CuentaBancaria = "";
                break;
             case 7:  //7 falta tarjetaDeCredito
                this.tipoUsuario = "organizador";
                if("organizador".equals(this.tipoUsuario) || "cliente".equals(this.tipoUsuario)){
                    this.rut = "17586721k";
                    this.clave = "clave";
                    this.numeroTelefonico = "97815288";
                    this.correoElectronico = "cristobal@gmail.com";
                    this.tarjetaDeCredito = "";
                }
                break;
            case 8:  //0 ok
                this.tipoUsuario = "organizador";
                if("organizador".equals(this.tipoUsuario) || "cliente".equals(this.tipoUsuario)){
                    this.rut = "17586721k";
                    this.clave = "clave";
                    this.numeroTelefonico = "97815288";
                    this.correoElectronico = "cristobal@gmail.com";
                    this.tarjetaDeCredito = "5896";
                }
                break;
            case 9:  //8 falta CuentaBancaria
                this.tipoUsuario = "organizador";
                if("organizador".equals(this.tipoUsuario) || "cliente".equals(this.tipoUsuario)){
                    this.rut = "17586721k";
                    this.clave = "clave";
                    this.numeroTelefonico = "97815288";
                    this.correoElectronico = "cristobal@gmail.com";
                    this.CuentaBancaria = "";
                }
                break;
             case 10:  //0 ok
                this.tipoUsuario = "organizador";
                if("propietario".equals(this.tipoUsuario)){
                    this.rut = "17586721k";
                    this.clave = "clave";
                    this.numeroTelefonico = "97815288";
                    this.correoElectronico = "cristobal@gmail.com";
                    this.CuentaBancaria = "6589";
                }
                break;
        }
        aux = p.validarResgistro(this.tipoUsuario, this.rut, this.clave, this.numeroTelefonico, this.correoElectronico, this.tarjetaDeCredito,this.CuentaBancaria);
        switch (casos){
            case 0:  //1 falta tipiUsuario
                System.out.println("caso 1.test");
                assertEquals(1, aux);
                break;
             case 1:  //2 falta nombre
                System.out.println(aux);
                assertEquals(1, aux);
                break;
            case 2:  //3 falta rut
                System.out.println(aux);
                assertEquals(2, aux);
                break;
            case 3: //4 falta clave
                assertEquals(3, aux);
                break;
            case 4: //5 falta numeroTelefonico
                assertEquals(4, aux);
                break;
            case 5: // 6 falta numeroTelefonico
                assertEquals(5, aux);
                break;
            case 6:  //7 falta correoElectronico
                assertEquals(6, aux);
                break;
             case 7:  //8 falta tarjetaDeCredito
                assertEquals(7, aux);
                break;
            case 8:  //0 ok
                assertEquals(0, aux);
                break;
            case 9:  //8 falta CuentaBancaria
                assertEquals(8, aux);
                    break;
             case 10: //0 ok
                assertEquals(0, aux);
                    break;
        }
    }
    
    
}
