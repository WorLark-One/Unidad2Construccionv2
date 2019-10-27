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
       int conectado=this.controladorUsuario.preguntarPorUsuario(tipoUsuario, rut, clave);
       if(conectado==1)// el usuario esta registrado
       {
           setTipoUsuario(tipoUsuario);
           setRut(rut);
           setContraseña(clave);
           return true;
       }
       else if(conectado==-1){// el usuario esta registrado, pero tiene su cuenta inactiva.
           return false;
       }
        
        return false;
    }
    /**
     * retorna el rut del usuario logeado
     * @return 
     */
    public String getRut() {
        return rut;
    }
   
    /**
     * retorna la contraseña del usuario logeado
     * @return 
     */
    public String getContraseña() {
        return contraseña;
    }

 
    /**
     * retorn el tipo de usuario: cliente, organizador u propietario
     * @return 
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    /**
     * remplaza el rut del usuario logeado
     * @param rut 
     */
    private void setRut(String rut) {
        this.rut = rut;
    }
    /**
     * remplaza la contrasela del usuario logeado.
     * @param contraseña 
     */
    private void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    

    
               
}