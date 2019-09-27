package ModuloGestionUsuario;

import ControladorBaseDeDatos.ControladorBDDeUsario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class GestionDeUsuario {

    private ArrayList<Usuario> ListaUsuarios;
    private ControladorBDDeUsario controlador;

    /**
     * Constructor del Gestor de Usuarios.
     */
    public GestionDeUsuario() {
        this.controlador = new ControladorBDDeUsario();
    }

    /**
     * Metodo que crea un nuevo Usuario en el sistema, de los distintos tipos de
     * Usuarios posibles.
     *
     * @param tipoUsuario El tipo del nuevo Usuario a crear. 1 para Cliente, 2
     * para Organizador y 3 para Propietario.
     * @param nombreUsuario El nombre del nuevo Usuario.
     * @param rutUsuario El RUT del nuevo Usuario.
     * @param contraseñaUsuario La contraseña del Usuario a crear.
     * @param correoElectronico El correo electronico del nuevo Usuario
     * @param telefono
     * @param cuentaCorriente
     * @param tarjetaCredito
     * @throws java.sql.SQLException
     */
    public void crearUsuario(String tipoUsuario, String nombreUsuario, String rutUsuario,
            String contraseñaUsuario, String correoElectronico, String telefono,
            String cuentaCorriente, String tarjetaCredito) throws SQLException {

        this.controlador.añadirUsuario(tipoUsuario, nombreUsuario, rutUsuario,
                contraseñaUsuario, correoElectronico, telefono, tarjetaCredito, cuentaCorriente);
    }

    /**
     * 
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
            String telefono,String cuentaCorriente, String tarjetaCredito) throws SQLException {
        this.controlador.modificarUsuario(tipoUsuario, nombreUsuario, rutUsuario,
                contraseñaUsuario, correoElectronico, telefono, 
                cuentaCorriente, tarjetaCredito);
    }

    /**
     * Metodo que elimina a un Usuario del sistema en base a su RUT.
     *
     * @param tipoUsuario
     * @param rutUsuario El RUT del Usuario a eliminar.
     * @throws java.sql.SQLException
     */
    public void eliminarUsuario(String tipoUsuario, 
            String rutUsuario) throws SQLException {
        this.controlador.eliminarUsuario(tipoUsuario,rutUsuario);
    }

}
