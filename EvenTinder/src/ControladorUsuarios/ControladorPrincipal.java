package ControladorUsuarios;

import ModuloGestionUsuario.GestionDeUsuario;
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
     * 
     */
    public void crearUsuario() {
        // TODO implement here
    }

    /**
     * 
     */
    public void modificarUsuario() {
        // TODO implement here
    }

    /**
     * 
     */
    public void eliminarUsuario() {
        // TODO implement here
    }

    /**
     * @param  fecha
     */
    public void mostrarListaDeEventos(Date fecha) {
        // TODO implement here
    }

}