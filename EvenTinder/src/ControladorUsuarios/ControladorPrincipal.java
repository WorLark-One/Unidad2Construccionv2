package ControladorUsuarios;

import ModuloGestionUsuario.GestionDeUsuario;
import java.sql.SQLException;
import java.util.Date;

/**
 * 
 */
public class ControladorPrincipal {
    
    private GestionDeUsuario gestorUsuarios;
    
    /**
     * 
     */
    public ControladorPrincipal() {
        this.gestorUsuarios = new GestionDeUsuario();
    }
    
    /**
     * @param usuario 
     * @param clave 
     * @return
     */
    public boolean solicitudDeAcceso(String tipoUsuario,String usuario, String clave) {
        // TODO implement here
        return true;
    }

    /**
     * Metodo que crea un nuevo usuario en el sistema con los datos ingresados.
     * 
     * @param tipoUsuario El tipo del nuevo Usuario. 
     *      Puede ser Cliente, Organizador o Propietario.
     * @param nombreUsuario El nombre del nuevo Usuario.
     * @param rutUsuario El RUT del nuevo Usuario.
     * @param contraseña La contraseña asociada al nuevo Usuario.
     * @param correoElectronico El correo Electronico asociado al nuevo Usuario.
     * @param telefono El telefono asociado al nuevo Usuario.
     * @param tarjeta EL numero de tarjeta o cuenta bancaria del Usuario a 
     *  crear.
     * @throws java.sql.SQLException 
     */
    public boolean crearUsuario(String tipoUsuario, String nombreUsuario, 
            String rutUsuario,String contraseña, String correoElectronico,
            String telefono,String tarjeta)
            throws SQLException {
        
        this.gestorUsuarios.crearUsuario(tipoUsuario,nombreUsuario,rutUsuario,
                contraseña,correoElectronico,telefono,
                tarjeta);
        return true;
    }

    /**
     * Metodo que modifica los datos de un Usuario.
     * 
     * @param tipoUsuario EL tipo del Usuario a modificar.
     * @param rutUsuarioModificar El rut actual del Usuario a modificar.
     * @param nombreUsuario El nombre del Usuario a modificar.
     * @param rutUsuario EL posible nuevo RUT del Usuario a modificar.
     * @param contraseña La contraseña del Usuario a modificar.
     * @param correoElectronico El correo electronico asociado al Usuario a 
     *  modificar.
     * @param telefono El telefono asociado al Usuario que quiere ser modificado.
     * @param tarjeta El numero de tarjeta o cuenta bancaria del usuario 
     *  a modificar.
     * @throws SQLException 
     */
    public boolean modificarUsuario(String nombreUsuario
             ,String contraseña, String correoElectronico,
            String telefono, String tarjeta) 
            throws SQLException {
        //obtener tipo, rut (no cambia). jiji
        this.gestorUsuarios.modificarUsuario(nombreUsuario,nombreUsuario, 
                nombreUsuario, nombreUsuario,nombreUsuario, correoElectronico, 
                telefono, tarjeta);
        return true;
    }

    /**
     * Metodo que elimina un Usuario en base a su RUT.
     * @param tipoUsuario El tipo de Usuario, el cual se quiere eliminar.
     * @param rutUsuario EL RUT del Usuario a eliminar.
     * @throws java.sql.SQLException
     */
    public boolean eliminarUsuario(String rutUsuario, String clave) 
            throws SQLException {
        //obtener tipo del usuario para poder cambiarlo, y agregar la clave para confirmar que es el usuario a borrar
        this.gestorUsuarios.eliminarUsuario(rutUsuario,rutUsuario);
        return true;
    }

    
    /**
     * @param  fecha
     */
    public void mostrarListaDeEventos(Date fecha) {
        // TODO implement here
    }

}