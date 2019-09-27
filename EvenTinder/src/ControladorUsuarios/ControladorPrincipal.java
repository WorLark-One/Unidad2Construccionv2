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
     * Metodo que crea un nuevo usuario en el sistema.
     * @param nombreUsuario El nombre del nuevo Usuario.
     * @param rutUsuario El RUT del nuevo Usuario.
     * @param contraseñaUsuario La contraseña asociada al nuevo Usuario.
     * @param correoElectronico El correo Electronico asociado al nuevo Usuario.
     * @param tipoUsuario El tipo del nuevo Usuario. 
     *      Puede ser Cliente, Organizador o Propietario.
     * @param telefono El telefono asociado al nuevo Usuario.
     * @param cuentaCorriente 
     * @param tarjetaCredito 
     * @throws java.sql.SQLException 
     */
    public void crearUsuario(String tipoUsuario, String nombreUsuario, 
            String rutUsuario,String contraseñaUsuario, String correoElectronico, 
            String telefono,String cuentaCorriente, String tarjetaCredito)
            throws SQLException {
        
        this.gestorUsuarios.crearUsuario(tipoUsuario,nombreUsuario,rutUsuario,
                contraseñaUsuario,correoElectronico,telefono,cuentaCorriente,
                tarjetaCredito);
    }

    /**
     * Metodo que modifica los datos de un Usuario.
     * @param tipoUsuario
     * @param nombreUsuario
     * @param rutUsuario
     * @param contraseñaUsuario
     * @param correoElectronico
     * @param telefono
     * @param cuentaCorriente
     * @param tarjetaCredito
     * @throws SQLException 
     */
    public void modificarUsuario(String tipoUsuario, String nombreUsuario, 
            String rutUsuario,String contraseñaUsuario, String correoElectronico,
            String telefono,String cuentaCorriente, String tarjetaCredito) 
            throws SQLException {
        
        this.gestorUsuarios.modificarUsuario(tipoUsuario, nombreUsuario, rutUsuario,
                contraseñaUsuario, correoElectronico, telefono, 
                cuentaCorriente, tarjetaCredito);
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