/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionUsuario;

import org.junit.Test;
import static org.junit.Assert.*;
import ModuloGestionUsuario.GestionDeUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ruben
 */
public class TestGestionDeUsuario {
    GestionDeUsuario g = new GestionDeUsuario();
    
    public TestGestionDeUsuario() {
    }

    
    @Test
    public void crearUsuario() throws SQLException {
        String tipoUsuario = "propietario";
        String nombreUsuario = "Federico Meza";
        String rutUsuario = "";
        String correoElectronico = "";
        String contraseña = "";
        String telefono = "5695568444";
        String tarjeta = "060606";
        
        
        
        
        g.crearUsuario(tipoUsuario, nombreUsuario, rutUsuario, contraseña, correoElectronico, telefono, tarjeta);
        
    
    }
    
    
    @Test
    public void modificarUsuario() throws SQLException {
        String tipoUsuario = "cliente";
        String nombreUsuario = "Manuel Palma";
        String rutUsuario = "1616";
        String correoElectronico = "wololo_nuevo@gmail.com";
        String contraseña = "852456";
        String telefono = "020203";
        String tarjeta = "6363";
             
     //g.modificarUsuario(tipoUsuario, rutUsuario, nombreUsuario, tarjeta, contraseña, correoElectronico, telefono, tarjeta);
        
    
    }
    
    @Test
    public void eliminarUsuario() throws SQLException {
        
        String tipoUsuario = "cliente";
        String rutUsuario = "";
           
        g.eliminarUsuario(tipoUsuario, rutUsuario);
        
    
    }
    
}
