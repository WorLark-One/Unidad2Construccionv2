package ControladorBaseDeDatos;

import java.util.Date;

/**
 * 
 */
public class ControladorBDDePropiedades {

    /**
     * Default constructor
     */
    public ControladorBDDePropiedades() {
    }

    /**
     * @param rut
     */
    public void obtenerInformacionDePropiedades( String rut) {
        // TODO implement here
    }

    /**
     * @param  rut 
     * @param  id
     */
    public void obtenerInformacionDeUnaPropiedad( String rut,  int id) {
        // TODO implement here
    }

    /**
     * @param  rut 
     * @param  nombre 
     * @param  descripcion 
     * @param  fechaDePublicacion 
     * @param  ubicacion 
     * @param  numeroDeSectores 
     * @param  valorDeArriendo
     */
    public void registrarPropiedad( String rut,  String nombre,  String descripcion,  Date fechaDePublicacion,  String ubicacion,  int numeroDeSectores,  int valorDeArriendo) {
        // TODO implement here
    }

    /**
     * @param  id 
     * @param  nombre 
     * @param  descripcion 
     * @param  fechaDePublicacion 
     * @param  ubicacion 
     * @param  numeroDeSectores 
     * @param  valorDeArriendo
     */
    public void modifcarPropiedad( int id,  String nombre,  String descripcion,  Date fechaDePublicacion,  String ubicacion,  int numeroDeSectores,  int valorDeArriendo) {
        // TODO implement here
    }

    /**
     * @param  id 
     */
    public void eliminarPropiedad( int id) {
        // TODO implement here
    }

}