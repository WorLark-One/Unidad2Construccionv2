/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorBaseDeDatos;
import ControladorBaseDeDatos.ConexionBD;
import ControladorBaseDeDatos.TestControladorBDDeUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruben
 */
public class TestControladorBDDeUsuario {
    ControladorBDDeUsuario b = new ControladorBDDeUsuario();
    
    public TestControladorBDDeUsuario() {
        
    }
    
    @Test
    public void preguntarPorUsuario() throws SQLException {
        ConexionBD c = new ConexionBD();
        c.crearConexion("EvenTinder", "12345");
        
        String tipoUsuario = "";
        String rut = "18666568-5";
        String clave = "12345";
                
        boolean r = b.preguntarPorUsuario(tipoUsuario, rut, clave);
        boolean esp = true;
        
        if (r==false) {
            fail("No se validó correctamente el usuario");
        }
        assertEquals(esp, r);
        
    }
    
    @Test
    public void obtenerInformacioDeUsurio() throws SQLException {
         ConexionBD c = new ConexionBD();
        c.crearConexion("EvenTinder", "12345");

        String tipoUsuario = "propietario";
        String rut = "18666568-5";
        ArrayList<String> l = new ArrayList<>();
        //l = b.obtenerInformacionCliente(tipoUsuario, rut);
        int esp = 1;
        if (esp>l.size()) {
            assertEquals(esp, l.size());
            fail("ERROR. No se ha obtenido la información del usuario");
        }
        
        else {
            for (String s : l) {
                System.out.println(s);
            }
        }      
    }
    
    
    
    @Test
    public void añadirUsuario() throws SQLException {
        String tipoUsuario = "cliente";
        String nombre = "Rubén Ramírez";
        String rut = "19006568-5";
        String correo = "asd@gmail.com";
        String clave = "123";
        String telefono = "95568444";
        String tarjeta = "252668884515";

        ConexionBD c = new ConexionBD();
        c.crearConexion("EvenTinder", "12345");
        boolean aceptado;
        Connection conexionAux = c.getConexion();
        //=c.añadirUsuario(conexionAux, tipoUsuario, nombre, rut, correo, clave, telefono, tarjeta);
        /*
        if (aceptado==true) {
            assertTrue(true);
            b.añadirUsuario(tipoUsuario, nombre, rut, correo, clave, telefono, tarjeta);
            System.out.println("Se ha añadido el usuario a la base de datos con éxito");
        }
        else {
            fail("Fallo al añadir el dato a la base de datos");
        }*/

    }
    
    
    @Test
    public void modificarUsuario() throws SQLException {
        String tipoUsuario = "";
        String rutModificar = "18666568-5";
        String nombre = "Sebastian Ibarra";
        String rut = "18666568-5";
        String correo = "Delorean@gmail.com";
        String clave = "123";
        String telefono = "123";
        String tarjeta = "123";
        ConexionBD c = new ConexionBD();
        c.crearConexion("EvenTinder", "12345");
        boolean aceptado;
        Connection conexionAux = c.getConexion();
        
        //aceptado=c.modificarDatosUsuario(conexionAux, tipoUsuario, rutModificar, nombre, rut, correo, clave, telefono, tarjeta);
        
        /*
        if (aceptado==true) {
            assertTrue(true);
            //b.modificarUsuario(tipoUsuario, rutModificar, nombre, rut, correo, clave, telefono, tarjeta);
            System.out.println("Se ha modificado el usuario a la base de datos con éxito");
        }
        else {
            fail("Fallo al modificar el dato de la base de datos");
        }
*/
    }
    
    
    @Test
    public void eliminarUsuario() throws SQLException {
        String tipoUsuario = "";
        String rut = "1616";
        
        ConexionBD c = new ConexionBD();
        c.crearConexion("EvenTinder", "12345");
        boolean aceptado;
        Connection conexionAux = c.getConexion();
        //aceptado=c.eliminarUsuario(conexionAux, tipoUsuario, rut);
        /*
        if (aceptado==true) {
            assertTrue(true);
            b.eliminarUsuario(tipoUsuario, rut);
            System.out.println("Se ha eliminado el usuario a la base de datos con éxito");
        }
        else {
            fail("Fallo al eliminar el dato de la base de datos");
        }*/

    }

    
}
