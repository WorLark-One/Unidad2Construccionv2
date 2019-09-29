package ControladorUsuarios;

import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionUsuario.GestionDeUsuario;
import java.sql.SQLException;
import java.util.Date;

/**
 * 
 */
public class ControladorPrincipal {
    
    private GestionDeUsuario gestorUsuarios;
    private ControlDeAcceso controlAcceso;
    
    /**
     *  Constructor del Controlador Principal
     */
    public ControladorPrincipal() {
        this.gestorUsuarios = new GestionDeUsuario();
        this.controlAcceso = ControlDeAcceso.getIntancia();
    }
    
    /**
     * Metodo que permite verificar si el usuario existe dentro del sistema, para poder iniciar sesion dentro de este.
     * @param tipoUsuario El tipo de Usuario que desea ingresar al sistema.
     * @param rut El rut del Usuario que desea ingresar.
     * @param clave La clave del Usuario que desea ingresar.
     * @return True si el usuario existe y se permite el acceso.
     *          False si el Usuario no existe o los datos son erroneos.
     * @throws java.sql.SQLException
     */
    public boolean solicitudDeAcceso(String tipoUsuario,String rut, String clave) throws SQLException {
        return this.controlAcceso.obtencioDeCredencial(tipoUsuario, rut, clave);
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
     * @return True si el usuario se creo con exito.
     *          False si el usuario no pudo ser creado.
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
     * @param nombreUsuario El nombre del Usuario a modificar.
     * @param contraseña La contraseña del Usuario a modificar.
     * @param correoElectronico El correo electronico asociado al Usuario a 
     *  modificar.
     * @param telefono El telefono asociado al Usuario que quiere ser modificado.
     * @param tarjeta El numero de tarjeta o cuenta bancaria del usuario 
     *  a modificar.
     * @return True si el Usuario se modifico exitosamente.
     *          False si no se pudo modificar el Usuario.
     * @throws SQLException 
     */
    public boolean modificarUsuario(String nombreUsuario
             ,String contraseña, String correoElectronico,
            String telefono, String tarjeta) 
            throws SQLException {
        //obtener tipo, rut (no cambia). jiji
        this.gestorUsuarios.modificarUsuario(this.controlAcceso.getTipoUsuario(),this.controlAcceso.getRut(),
                nombreUsuario,contraseña,correoElectronico,telefono,tarjeta);
        return true;
    }

    /**
     * Metodo que elimina un Usuario en base a su RUT.
     * @param tipoUsuario El tipo de Usuario, el cual se quiere eliminar.
     * @param clave La clase del usuario que se desea eliminar.
     * @param rutUsuario EL RUT del Usuario a eliminar.
     * @return True si el usuario se elimino con exito.
     *          False si el usuario no se pudo eliminar o los datos son erroneos.
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