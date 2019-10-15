/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;
import org.junit.Test;
import static org.junit.Assert.*;
import VistasSistema.VistaPropietario.PanelModificarSector;
import VistasSistema.VistaPropietario.VentanaPrincipalPropietario;
/**
 *
 * @author crist
 */
// en PanelModificarSector se debe hacer static validarEntrada
public class TestPanelModificarSector {
    PanelModificarSector msector = null;// para las pruebas
    
    public TestPanelModificarSector(){
    }
    @Test
    public void testValidarEntrada(){ // se dejo el metodo como pubico para las pruebas
        System.out.println("Empeso el test");
        String nombre = "";
        String capacidad = "";
        int retorno = 10;
        int casos = 0;
        switch(casos){
            case 0:
                retorno = msector.validarEntrada(nombre, capacidad);
                System.out.println("Retorno = "+retorno);
                assertEquals(1, retorno); // falla en el nombre asi que retorna 1
            case 1:
                nombre = "Sector A";
                retorno = msector.validarEntrada(nombre, capacidad);
                assertEquals(2, retorno); // falla en la capacidad asi que retorna 2
            case 2:
                nombre = "Sector A";
                capacidad = "100";
                retorno = msector.validarEntrada(nombre, capacidad);
                assertEquals(0, retorno); // no falla retorna 0
            case 3:
                nombre = "Sector A";
                capacidad = "caca";
                retorno = msector.validarEntrada(nombre, capacidad);
                assertEquals(2, retorno); // falla en la capacidad asi que deberia retornar 2
        } 
    }
}
