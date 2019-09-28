package ModuloAutenticacion;

import ControladorBaseDeDatos.ControladorBDDeUsuario;
import java.sql.SQLException;


/**
 * 
 */
public class ControlDeAcceso {
    ControladorBaseDeDatos.ControladorBDDeUsuario controladorUsuario;

    /**
     * Default constructor
     */
    public ControlDeAcceso() {
        this.controladorUsuario=new ControladorBDDeUsuario();
        
    }


    /**
     * 
     */
    public ControlDeAcceso Singleton;

    /**
     * verifica si un usuario esta registrado en la base de datos.
     * @param tipoUsuario: puede ser cliete, organizador u propietario
     * @param rut : rut del cliente del cual se quiere verificar su credencial
     * @param  clave : clave del cliente del cuela se quiere verificar su credencial
     * @return true o false, dependiendo de si esta registrado el usuario o no.
     * @throws java.sql.SQLException
     */
    public boolean obtencioDeCredencial(String tipoUsuario, String rut,  String clave) throws SQLException {
        return this.controladorUsuario.preguntarPorUsuario(tipoUsuario, rut, clave);
    }

}