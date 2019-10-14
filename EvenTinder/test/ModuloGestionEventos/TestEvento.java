/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionEventos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author crist
 */
public class TestEvento {
    // en la vista se verifica que sean numeros enteros > a 0 y que los campos no esten vacios
   Evento evento;
    public TestEvento()throws ParseException{
        SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy"); 
        Date dt_1 = objSDF.parse("20-08-1981"); 
        evento = new Evento(1, "mi evento", "un evento con grato ambiente", dt_1, dt_1, 100, 10, false);
        Entrada entrada = new Entrada(2, 500);
        evento.AñadirEntrada(entrada);    
    }
    @Test
    public void testAñadirEntrada(){
        int id = 1;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean esperado = evento.AñadirEntrada(entrada);
        assertEquals(true, esperado);
    }
    @Test
    public void testAñadirEntrada1(){
        int id = 1;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean esperado = evento.AñadirEntrada(entrada);
        id = 2;
        precio = 1000;
        esperado = evento.AñadirEntrada(entrada);
        assertEquals(true, esperado);
    }
    @Test
    public void testAñadirEntrada2(){
        int id = 1;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean esperado = evento.AñadirEntrada(entrada);// true 
        esperado = evento.AñadirEntrada(entrada);// false por que se agrega dos veces la misma entrada
        assertEquals(false, esperado);
    }
    @Test
    public void testAñadirEntrada3(){
        int x = 99;
        int precio = 1000;
        Entrada entrada;
        boolean aux;
        for(int i = 0; i < x; i++){// desde el 0
            entrada = new Entrada(i, precio);
            aux = evento.AñadirEntrada(entrada);
        }
        entrada = new Entrada(100, precio);
        boolean esperado = evento.AñadirEntrada(entrada);
        assertEquals(false, esperado);
    }
     @Test
    public void testVerificarDisponibilidadDeEntrada(){
    // evento con capacidad de  100 peronas
        boolean esperado = evento.verificarDisponibilidadDeEntrada();// true 
        assertEquals(true, esperado);
    }
    @Test
    public void testVerificarDisponibilidadDeEntrada1(){
    // evento con capacidad de  100 peronas
        int x = 98; //creando 99 entradas, desde el 0 al 98
        int precio = 1000;
        Entrada entrada;
        boolean aux;
        for(int i = 0; i<x; i++){
            entrada = new Entrada(i, precio);
            aux = evento.AñadirEntrada(entrada);
        }
        boolean esperado = evento.verificarDisponibilidadDeEntrada();// true 
        assertEquals(true, esperado);
    }
    @Test
    public void testVerificarDisponibilidadDeEntrada2(){
    // evento con capacidad de  100 peronas
        int x = 99; //creando 100 entradas, desde el 0 al 98
        int precio = 1000;
        Entrada entrada;
        boolean aux;
        for(int i = 0; i<x; i++){ // agrego las 100 entradas 
            entrada = new Entrada(i, precio);
            aux = evento.AñadirEntrada(entrada);
        }
        boolean esperado = evento.verificarDisponibilidadDeEntrada();// false 
        assertEquals(false, esperado);
    }
    @Test
    public void testEliminarEntrada() {
        int id = 0;
        boolean esperado = evento.eliminarEntrada(id);
        assertEquals(false , esperado); // lista vacia
    }
    @Test
    public void testEliminarEntrada1() {
        int id = 1;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean esperado = evento.AñadirEntrada(entrada); // es true
        esperado = false;
        
        esperado = evento.eliminarEntrada(id);
        assertEquals(true, esperado);// se elimina la entrada ingresada
    }
    @Test
    public void testEliminarEntrada2() {
        int id = 1;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean aux = evento.AñadirEntrada(entrada);
        id = 3;
        boolean esperado = evento.eliminarEntrada(id);
        assertEquals(false, esperado);// los id no coinciden por lo que no elimina
    }
    @Test
    public void testEliminarEntrada3() {
        int id = 1;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean aux = evento.AñadirEntrada(entrada);
        id = 2;
        boolean esperado = evento.eliminarEntrada(id);// devuelve true
        assertEquals(false, esperado);// los id no coinciden por lo que no elimina
    }
    @Test
    public void testEliminarEntrada4() {
        int id = 2;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean aux = evento.AñadirEntrada(entrada);
        id = 3;
        boolean esperado = evento.eliminarEntrada(id);
        assertEquals(false, esperado);// los id no coinciden por lo que no elimina
    }
    @Test
    public void testEliminarEntrada5() {
        int id = 1;
        int precio = 1000;
        Entrada entrada = new Entrada(id, precio);
        boolean aux = evento.AñadirEntrada(entrada);
        id = 2;
        entrada = new Entrada(id, precio);
        aux = evento.AñadirEntrada(entrada);
        id = 3;
        entrada = new Entrada(id, precio);
        aux = evento.AñadirEntrada(entrada);
        id = 4;
        boolean esperado = evento.eliminarEntrada(id);
        assertEquals(false, esperado);// los id no coinciden por lo que no elimina
    }
 
}
