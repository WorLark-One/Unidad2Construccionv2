package ModuloAutenticacion;

import ControladorBaseDeDatos.ControladorBDDeUsuario;
import java.sql.SQLException;


/**
 * 
 */
public class ControlDeAcceso {
    String rut;
    String contraseña;
    String tipoUsuario;
    ControladorBaseDeDatos.ControladorBDDeUsuario controladorUsuario;
    private static ControlDeAcceso controDeAcceso;
    

    /**
     * Default constructor
     */
    private ControlDeAcceso() {
        this.controladorUsuario=new ControladorBDDeUsuario();
        this.rut="";
        this.contraseña="";
        
    }
    /**
     * retorna una instancia de control de acceso.
     * @return si la instancia no a sido creada, crea una nueva, 
     * si ya fue creada anteriormente, retorna la misma.
     */
    public static ControlDeAcceso getIntancia()
    {
        if(controDeAcceso == null)
        {
            controDeAcceso = new ControlDeAcceso();
            return controDeAcceso;
        }
        else{
            return controDeAcceso;
        }
    }  

    /**
     * verifica si un usuario esta registrado en la base de datos.
     * @param tipoUsuario: puede ser cliete, organizador u propietario
     * @param rut : rut del cliente del cual se quiere verificar su credencial
     * @param  clave : clave del cliente del cuela se quiere verificar su credencial
     * @return true o false, dependiendo de si esta registrado el usuario o no.
     * @throws java.sql.SQLException
     */
    public boolean obtencioDeCredencial(String tipoUsuario, String rut,  String clave) throws SQLException {
       boolean conectado=this.controladorUsuario.preguntarPorUsuario(tipoUsuario, rut, clave);
       if(conectado==true)
       {
           setRut(rut);
           setContraseña(contraseña);
       }
        
        return this.controladorUsuario.preguntarPorUsuario(tipoUsuario, rut, clave);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }                
}