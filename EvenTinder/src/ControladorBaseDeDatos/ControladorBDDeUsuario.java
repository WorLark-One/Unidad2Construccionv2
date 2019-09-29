package ControladorBaseDeDatos;

import ModuloGestionUsuario.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 *clase que se encarga de interactuar con la base de datos.
 * 1.
 */
public class ControladorBDDeUsuario {

    ConexionBD conexion;

    /**
     * Default constructor
     */
    public ControladorBDDeUsuario() {
        this.conexion = new ConexionBD();
    }

    /**
     * Sirve para saber si un usuario esta logeado.
     * @param tipoUsuario: cliente, organizador u propietario
     * @param rut rut del usuario a validar
     * @param clave: contraseña del usuario a logear.
     * @return true si el usuario esta logeado, false de lo contrario.
     * @throws java.sql.SQLException
     */
    public boolean preguntarPorUsuario(String tipoUsuario,String rut, String clave) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.ValidarInicioSecion(conexionAux,tipoUsuario, rut, clave);
    }

    /**
     * devuelve una lista con la informacion de un Usuarios, en este orden.
     * 1.nombre completo.
     * 2.rut.
     * 3.correo.
     * 4.contraseña.
     * 5.telefono.
     * 6.cuenta corriente/ tarjeta de credito.
     *
     * @param tipoUsuario:cliente, organizador u propietario
     * @param rut: rut del usuario al cual se le quiere obtener la informacion.
     * @return un usuario.
     * @throws java.sql.SQLException
     */
    public Usuario obtenerInformacioDeUsurio(String tipoUsuario, String rut) throws SQLException {
        ArrayList<String> informacion = null;
        Usuario usuario= null;
        this.conexion.crearConexion("EventTinder", "1");
        Connection conexionAux = this.conexion.getConexion();
        informacion=this.conexion.mostrarInformacionUsuario(conexionAux, tipoUsuario, rut);
        if(informacion!=null)
        {
            String nombreCompleto=informacion.get(0);
            String rutAux = informacion.get(1);
            String correo=informacion.get(2);
            String contraseña=informacion.get(3);
            String telefono = informacion.get(4);
            String tarjeta=informacion.get(5);
            
            usuario = new Usuario(rutAux,correo,contraseña,telefono,tarjeta);
            
            return usuario;
        }
  
        return usuario;


    }

    /**
     * añade a un usuario a la base de datos
     *
     * @param tipoUsuario:cliente, organizador u propietario
     * @param nombre:nombre del usuario
     * @param rut: rut del usuario
     * @param clave: clave del usuario
     * @param correo: correo electronico del usuario
     * @param telefono: telefono del usuario
     * @param tarjeta: esta puede ser cuenta corriente o tarjeta de credito.
     * @throws java.sql.SQLException
     */
    public void añadirUsuario(String tipoUsuario, String nombre, String rut, String correo,String clave, String telefono, String tarjeta) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        aceptado=this.conexion.añadirUsuario(conexionAux, tipoUsuario, nombre, rut, correo, clave, telefono, tarjeta);
        System.out.println("se añadio el usuario correctamente? :"+aceptado);

    }
  

    /**
     * modifica la informacion de un usuario.
     * @param tipoUsuario: cliente, organizador u propietario
     * @param rutUsuarioModificar: rut del usuario al que se le modificara la informacion.
     * @param nombre: nombre del usuario que se que se desea modificar.
     * @param rut: rut del usuario que se que se desea modificar.
     * @param clave: clave del usuario que se que se desea modificar.
     * @param correo: correo del usuario que se que se desea modificar.
     * @param telefono: telefono del usuario que se que se desea modificar.
     * @param tarjeta: tarjeta de credito o cuenta corriete del usuario que se que se desea modificar.
     * @return true si el usuario fue modificado, false de lo contrario.
     * @throws java.sql.SQLException 
     */
    public boolean modificarUsuario(String tipoUsuario,String rutUsuarioModificar,String nombre, String clave, String correo, String telefono, String tarjeta) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.modificarDatosUsuario(conexionAux, tipoUsuario, rutUsuarioModificar, nombre, correo, correo, telefono, tarjeta);
    }

    /**
     * elimina un usuario de tipo cliente, organizador o propietario de la base de 
     * datos.
     * @param tipoUsuario:cliente,organizador u propietario
     * @param rut: rut del usuario que se desea eliminar.
     * @return  true si se elimino el usuario, false de lo contrario.
     * @throws java.sql.SQLException
     */
    public boolean eliminarUsuario(String tipoUsuario,String rut) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection conexionAux = this.conexion.getConexion();
        return this.conexion.eliminarUsuario(conexionAux, tipoUsuario, rut);
    }

}
