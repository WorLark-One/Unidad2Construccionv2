package ModuloGestionUsuario;

import ControladorBaseDeDatos.ControladorBDDeUsuario;
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
     * @param nombreUsuario El nombre del nuevo Usuario.
     * @param rutUsuario El RUT del nuevo Usuario.
     * @param contraseñaUsuario La contraseña del Usuario a crear.
     * @param correoElectronico El correo electronico del nuevo Usuario
     * @param cuentaAsociada La cuenta del nuevo Usuario. Puede ser una tarjeta
     * de credito o una cuenta corriente.
     * @param tipoUsuario El tipo del nuevo Usuario a crear. 1 para Cliente,
     *  2 para Organizador y 3 para Propietario.
     */
    public void crearUsuario(String nombreUsuario, String rutUsuario,
            String contraseñaUsuario, String correoElectronico,
            String cuentaAsociada, int tipoUsuario) {

        this.controlador.añadirUsuario(nombreUsuario, rutUsuario,
                contraseñaUsuario, correoElectronico, cuentaAsociada);
    }

    /**
     *
     */
    public void modificarUsuario() {
        // TODO implement here
    }

    /**
     * Metodo que elimina a un Usuario del sistema en base a su RUT.
     *
     * @param rutUsuario El RUT del Usuario a eliminar.
     */
    public void eliminarUsuario(String rutUsuario) {
        this.controlador.eliminarUsuario(rutUsuario);
    }

}
