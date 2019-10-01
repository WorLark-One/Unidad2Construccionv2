/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorUsuarios;

import org.junit.Test;
import ControladorUsuarios.ControladorPrincipal;
import java.sql.SQLException;
import static org.junit.Assert.*;

/**
 *
 * @author ruben
 */
public class TesteoControladorPrincipal {
    
    ControladorPrincipal c = new ControladorPrincipal();
    
    public TesteoControladorPrincipal() {
    }

    
    @Test
    public void crearUsuario() throws SQLException {
        String tipoUsuario = "propietario";
        String nombreUsuario = "Roberto Bolaño";
        String rutUsuario = "17400400-8";
        String correoElectronico = "rob@hotmail.com";
        String contraseña = "12345";
        String telefono = "98568444";
        String tarjeta = "252230";
        
        c.crearUsuario(tipoUsuario, nombreUsuario, rutUsuario, contraseña, correoElectronico, telefono, tarjeta);
    }
    
    @Test
    public void modificarUsuario() throws SQLException {
        String tipoUsuario = "organizador";
        String nombreUsuario = "Benjamin";
        String rutUsuario = "0303";
        String correoElectronico = "Benjamin@gmail.com";
        String contraseña = "111";
        String telefono = "075";
        String tarjeta = "5";
        
        //c.modificarUsuario(tipoUsuario, rutUsuario, nombreUsuario, rutUsuario, contraseña, correoElectronico, telefono, tarjeta);
    }
    
    @Test
    public void eliminarUsuario() throws SQLException {
        String tipoUsuario = "propietario";
        String rutUsuario = "";
        
        c.eliminarUsuario(tipoUsuario, rutUsuario);
    }
}
