/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionUsuario;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brayan Escobar
 */
public class GestionDeUsuarioTest {

    @Before
    public void before() throws SQLException {
        String tipoUsuario = "cliente";
        String nombreUsuario = "brayan Escobar";
        String rutUsuario = "19363170-3";
        String contraseña = "123456789";
        String correoElectronico = "brayan.escobar@live.com";
        String telefono = "123456789";
        String tarjeta = "1111111111111111";
        Cliente cliente = new Cliente(nombreUsuario, tarjeta, contraseña, telefono, correoElectronico, tarjeta);
        GestionDeUsuario instance = new GestionDeUsuario();
        //instance.crearUsuario(tipoUsuario, nombreUsuario, rutUsuario, contraseña, correoElectronico, telefono, tarjeta);

    }

    /**
     * Test of crearUsuario method, of class GestionDeUsuario.
     */
    @Test
    public void testCrearUsuario() throws Exception {
        System.out.println("crearUsuario");
        String tipoUsuario = "cliente";
        String rutUsuarioAModificar = "19363170-3";
        String nombreUsuario = "jhoany escobar";
        String contraseña = "123";
        String correoElectronico = "brayan.escobar@live.com";
        String telefono = "123456789";
        String tarjeta = "111111111111111";
        GestionDeUsuario instance = new GestionDeUsuario();
        boolean expResult = false;
        boolean result = instance.crearUsuario(tipoUsuario, nombreUsuario, rutUsuarioAModificar, contraseña, correoElectronico, telefono, tarjeta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarUsuario method, of class GestionDeUsuario.
     */
    @Test
    public void testModificarUsuario() throws Exception {
        System.out.println("modificarUsuario");
        String tipoUsuario = "";
        String rutUsuarioAModificar = "";
        String nombreUsuario = "";
        String contraseña = "";
        String correoElectronico = "";
        String telefono = "";
        String tarjeta = "";
        GestionDeUsuario instance = new GestionDeUsuario();
        boolean expResult = false;
        boolean result = instance.modificarUsuario(tipoUsuario, rutUsuarioAModificar, nombreUsuario, contraseña, correoElectronico, telefono, tarjeta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarUsuario method, of class GestionDeUsuario.
     */
    @Test
    public void testEliminarUsuario() throws Exception {
        System.out.println("eliminarUsuario");
        String tipoUsuario = "";
        String rutUsuario = "";
        GestionDeUsuario instance = new GestionDeUsuario();
        boolean expResult = false;
        boolean result = instance.eliminarUsuario(tipoUsuario, rutUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

}
