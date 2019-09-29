package ModuloGestionUsuario;

import ControladorBaseDeDatos.ControladorBDDeUsuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class GestionDeUsuario {

    private ArrayList<Usuario> ListaUsuarios;
    private ControladorBDDeUsuario controlador;

    /**
     * Constructor del Gestor de Usuarios.
     */
    public GestionDeUsuario() {
        this.controlador = new ControladorBDDeUsuario();
    }

    /**
     * Metodo que crea un nuevo Usuario en el sistema, de los distintos tipos de
     * Usuarios posibles.
     *
     * @param tipoUsuario El tipo del nuevo Usuario a crear. Puede ser Cliente, 
     *  Organizador o Propietario.
     * @param nombreUsuario El nombre del nuevo Usuario.
     * @param rutUsuario El RUT del nuevo Usuario.
     * @param contraseña La contraseña del Usuario a crear.
     * @param correoElectronico El correo electronico del nuevo Usuario
     * @param telefono El telefono asociado al nuevo Usuario.
     * @param tarjeta La tarjeta o cuenta bancaria del Usuario a crear.
     * @throws java.sql.SQLException
     */
    public void crearUsuario(String tipoUsuario, String nombreUsuario, 
            String rutUsuario,String contraseña, String correoElectronico,
            String telefono,String tarjeta) throws SQLException {

        this.controlador.añadirUsuario(tipoUsuario, nombreUsuario, rutUsuario,
                correoElectronico,contraseña,  telefono, tarjeta);
        
    }

    
    /**
     * Metodo que modifica los datos relacionados a un Usuario especifico.
     * 
     * @param tipoUsuario El tipo de Usuario a modificar.
     * @param rutUsuarioAModificar El RUT actual del Usuario que se desea 
     *  modificar.
     * @param nombreUsuario El nombre del Usuario a modificar.
     * @param rut El RUT del usuario a modificar.
     * @param contraseña La contraseña del Usuario a modificar.
     * @param correoElectronico El correo electronico asociado al Usuario a 
     *  modificar.
     * @param telefono El telefono asociado al Usuario a modificar.
     * @param tarjeta El numero de tarjeta o cuenta bancaria del Usuario a 
     *  modificar.
     * @throws SQLException 
     */
    public void modificarUsuario(String tipoUsuario,String rutUsuarioAModificar, 
            String nombreUsuario,String rut, String contraseña, 
            String correoElectronico,String telefono, String tarjeta) 
            throws SQLException {
        
        this.controlador.modificarUsuario(tipoUsuario, rutUsuarioAModificar,
                nombreUsuario,rut,contraseña, correoElectronico, telefono,
                tarjeta);
    }

    /**
     * Metodo que elimina a un Usuario del sistema en base a su RUT.
     *
     * @param tipoUsuario El tipo de Usuario que se busca eliminar.
     * @param rutUsuario El RUT del Usuario a eliminar.
     * @throws java.sql.SQLException
     */
    public void eliminarUsuario(String rutUsuario, 
            String clave) throws SQLException {
        
        this.controlador.eliminarUsuario(rutUsuario,clave);
    }

}
