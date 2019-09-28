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
    public boolean solicitudDeAcceso(String usuario, String clave) {
        // TODO implement here
        return false;
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
    public void crearUsuario(String tipoUsuario, String nombreUsuario, 
            String rutUsuario,String contraseña, String correoElectronico,
            String telefono,String tarjeta)
            throws SQLException {
        
        this.gestorUsuarios.crearUsuario(tipoUsuario,nombreUsuario,rutUsuario,
                contraseña,correoElectronico,telefono,
                tarjeta);
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
    public void modificarUsuario(String tipoUsuario,String rutUsuarioModificar, String nombreUsuario, 
            String rutUsuario,String contraseña, String correoElectronico,
            String telefono, String tarjeta) 
            throws SQLException {
        
        this.gestorUsuarios.modificarUsuario(tipoUsuario,rutUsuarioModificar, 
                nombreUsuario, rutUsuario,contraseña, correoElectronico, 
                telefono, tarjeta);
    }

    /**
     * Metodo que elimina un Usuario en base a su RUT.
     * @param tipoUsuario El tipo de Usuario, el cual se quiere eliminar.
     * @param rutUsuario EL RUT del Usuario a eliminar.
     * @throws java.sql.SQLException
     */
    public void eliminarUsuario(String tipoUsuario, String rutUsuario) 
            throws SQLException {
        this.gestorUsuarios.eliminarUsuario(tipoUsuario,rutUsuario);
    }

    
    /**
     * @param  fecha
     */
    public void mostrarListaDeEventos(Date fecha) {
        // TODO implement here
    }

}