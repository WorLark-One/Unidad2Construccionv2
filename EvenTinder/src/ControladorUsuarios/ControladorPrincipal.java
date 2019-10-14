package ControladorUsuarios;

import ModuloAutenticacion.ControlDeAcceso;
import ModuloGestionUsuario.GestionDeUsuario;
import ModuloGestionUsuario.Usuario;
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
    //posible error capa 8
    public boolean crearUsuario(String tipoUsuario, String nombreUsuario, 
            String rutUsuario,String contraseña, String correoElectronico,
            String telefono,String tarjeta)
            throws SQLException {
        
        
        return this.gestorUsuarios.crearUsuario(tipoUsuario,nombreUsuario,rutUsuario,
                contraseña,correoElectronico,telefono,
                tarjeta);
    }

    /**
     * Metodo que modifica los datos del Usuario que esta ingresado en el sistema actualmente.
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
        
        
        return this.gestorUsuarios.modificarUsuario(this.controlAcceso.getTipoUsuario(),this.controlAcceso.getRut(),
                nombreUsuario,contraseña,correoElectronico,telefono,tarjeta);
    }

    /**
     * Metodo que elimina un Usuario en base a su RUT.
     * @param clave La clase del Usuario, que esta ingresado en el sistema, que desea eliminar su cuenta.
     * @param rutUsuario EL RUT del Usuario, que esta ingresado en el sistema, que desea eliminar su cuenta.
     * @return True si el usuario se elimino con exito.
     *          False si el usuario no se pudo eliminar o los datos son erroneos.
     * @throws java.sql.SQLException
     */
    public boolean eliminarUsuario(String rutUsuario, String clave) 
            throws SQLException {
        if(this.controlAcceso.getRut().equals(rutUsuario) && 
                this.controlAcceso.getContraseña().equals(clave)){
            this.gestorUsuarios.eliminarUsuario(this.controlAcceso.getTipoUsuario(),rutUsuario);
            return true;
        }        
        else{
            System.out.println("Los datos ingresados no corresponden al Usuario "
                    + "actualmente ingresado en el sistema ");
            return false;
        }
    }
    
    public Usuario obtenerInformacionUsuario() throws SQLException{
        if(this.controlAcceso.getTipoUsuario().equals("cliente")){
            return this.gestorUsuarios.obtenerInformacionCliente(this.controlAcceso.getRut());
        }
        if(this.controlAcceso.getTipoUsuario().equals("propietario")){
            return this.gestorUsuarios.obtenerInformacionPropietario(this.controlAcceso.getRut());
        }
        if(this.controlAcceso.getTipoUsuario().equals("organizador")){
            return this.gestorUsuarios.obtenerInformacionOrganizador(this.controlAcceso.getRut());
        }
        return null;
    }

    
    /**
     * @param  fecha
     */
    public void mostrarListaDeEventos(Date fecha) {
        // TODO implement here
    }

}