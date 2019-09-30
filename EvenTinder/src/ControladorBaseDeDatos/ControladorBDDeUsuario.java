package ControladorBaseDeDatos;

import ModuloGestionUsuario.Cliente;
import ModuloGestionUsuario.Organizador;
import ModuloGestionUsuario.Propietario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase que se encarga de interactuar con la base de datos. 1.
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
     *
     * @param tipoUsuario: cliente, organizador u propietario
     * @param rut rut del usuario a validar
     * @param clave: contraseña del usuario a logear.
     * @return true si el usuario esta logeado, false de lo contrario.
     * @throws java.sql.SQLException
     */
    public boolean preguntarPorUsuario(String tipoUsuario, String rut, String clave) throws SQLException {
        // se establece la conexion.
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        //se realiza la consulta.
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = " select  count(" + tipoUsuario + ".rut) as repetido "
                        + "from " + tipoUsuario + " where " + tipoUsuario + ".rut='" + rut + "' and " + tipoUsuario + ".contraseña='" + clave + "'"
                        + "group by " + tipoUsuario + ".rut\n"
                        + "having count(" + tipoUsuario + ".rut)>=0 ";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String cadena = resultado.getString("repetido");
                    int valor = Integer.parseInt(cadena);
                    if (valor == 1) {
                        return true;
                    }
                }

                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: ValidarInicioSecion" + e);
                return false;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }
        }
        return false;
    }
    /**
     * obtiene la informacion de un cliente a traves de su rut
     * @param rut
     * @return cliente.
     * @throws SQLException 
     */
    public Cliente obtenerInformacionCliente(String rut) throws SQLException {
        Cliente cliente = null;
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from cliente where rut='" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String tarjetaCredito = resultado.getString("tarjetacredito");
                    cliente = new Cliente(nombreCompleto, rutCliente,contraseña,telefono, correo, tarjetaCredito);
                    return cliente;

                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return cliente;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
        return null;
    }
    /**
     * obtiene la informacion de un organizador a traves de su rut
     * @param rut
     * @return
     * @throws SQLException 
     */
    public Organizador obtenerInformacionOrganizador(String rut) throws SQLException {
        Organizador organizador = null;
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from organizador where rut='" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String tarjetaCredito = resultado.getString("tarjetacredito");
                    organizador = new Organizador(nombreCompleto, rutCliente,contraseña,telefono,  correo,  tarjetaCredito);
                    return organizador;

                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return organizador;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
        return null;
    }
    /**
     * obtiene la informacion de un Propietario a traves de su rut
     * @param rut
     * @return
     * @throws SQLException 
     */
    public Propietario obtenerInformacionPropietario(String rut) throws SQLException {
        Propietario propietario = null;
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from propietario where rut='" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String cuentaCorriente = resultado.getString("cuentacorriente");
                    propietario = new Propietario(nombreCompleto, rutCliente,contraseña,telefono,  correo,  cuentaCorriente);
                    return propietario;

                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return propietario;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
        return null;
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
     * @return
     * @throws java.sql.SQLException
     */
    public boolean añadirUsuario(String tipoUsuario, String nombre, String rut, String correo, String clave, String telefono, String tarjeta) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(miConexion, "cliente", rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla cliente.
            if (numero == -1) {// el cliente no esta registrado, por lo tanto hay que registrarlo.
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into " + tipoUsuario + " values('" + nombre + "','" + rut + "','" + correo + "','" + clave + "','" + telefono + "','" + tarjeta + "')";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                } finally {
                    this.conexion.cerrarBaseDeDatos(miConexion);

                }
                return true;

            }
            // retornamos false debido a que el cliente ya esta registrado.
            return false;

        }
        return false;
    }

    /**
     * modifica la informacion de un usuario.
     *
     * @param tipoUsuario: cliente, organizador u propietario
     * @param rutUsuarioModificar: rut del usuario al que se le modificara la
     * informacion.
     * @param nombre: nombre del usuario que se que se desea modificar.
     * @param clave: clave del usuario que se que se desea modificar.
     * @param correo: correo del usuario que se que se desea modificar.
     * @param telefono: telefono del usuario que se que se desea modificar.
     * @param tarjeta: tarjeta de credito o cuenta corriete del usuario que se
     * que se desea modificar.
     * @return true si el usuario fue modificado, false de lo contrario.
     * @throws java.sql.SQLException
     */
    public boolean modificarUsuario(String tipoUsuario, String rutUsuarioModificar, String nombre, String correo, String clave, String telefono, String tarjeta) throws SQLException {

        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {
            if (tipoUsuario.equalsIgnoreCase("cliente") == true) {

                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "UPDATE cliente SET nombrecompleto='"+nombre+"', correo='"+correo+"', contraseña='"+clave+"', telefono='"+telefono+"', tarjetacredito='"+tarjeta+"' where rut='"+rutUsuarioModificar+"'";

                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: ValidarInicioSecion" + e);
                    return false;
                } finally {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                }
            } else if (tipoUsuario.equalsIgnoreCase("organizador") == true) {
                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "UPDATE organizador SET nombrecompleto='"+nombre+"', correo='"+correo+"', contraseña='"+clave+"', telefono='"+telefono+"', tarjetacredito='"+tarjeta+"' where rut='"+rutUsuarioModificar+"'";
                    st.executeUpdate(sql);

                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: ValidarInicioSecion" + e); 
                    return false;
                } finally {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                }
            } else if(tipoUsuario.equalsIgnoreCase("propietario") == true){

                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "UPDATE propietario SET nombrecompleto='"+nombre+"', correo='"+correo+"', contraseña='"+clave+"', telefono='"+telefono+"', cuentacorriente='"+tarjeta+"' where rut='"+rutUsuarioModificar+"'";
                    st.executeUpdate(sql);

                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: ValidarInicioSecion" + e);
                    return false;
                } finally {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                }
            }
        }
        return false;

    }

    /**
     * elimina un usuario de tipo cliente, organizador o propietario de la base
     * de datos.
     *
     * @param tipoUsuario:cliente,organizador u propietario
     * @param rut: rut del usuario que se desea eliminar.
     * @return true si se elimino el usuario, false de lo contrario.
     * @throws java.sql.SQLException
     */
    public boolean eliminarUsuario(String tipoUsuario, String rut) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(miConexion, tipoUsuario, rut);

            // si el rut del usuario esta en la tabla cliente, y el rut no esta en las
            // tablas de organizador y propietario, esto implica que puedo borrar 
            // desde la tabla usuario, lo que permite la eliminacion en cascada.
            if (numero == 1) {
                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "delete from " + tipoUsuario + " where " + tipoUsuario + ".rut='" + rut + "'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: eliminar cliente (desde la tabla de usuario)" + e);
                    return false;
                } finally {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * verifica a traves del rut de un tipo de usuario " cliente, organizador o
     * propietario", si este ya esta registrado en la base de datos, retorna 1,
     * -1 de lo contrario. metodo de uso interno
     *
     * @param conexionAux
     * @param tipoUsuario
     * @param rut
     * @return
     */
    private int contarTiposDeUsuario(Connection conexionAux, String tipoUsuario, String rut) {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select  count(" + tipoUsuario + ".rut) as repetido "
                        + "from " + tipoUsuario + " where " + tipoUsuario + ".rut='" + rut + "'"
                        + "group by " + tipoUsuario + ".rut"
                        + " having count(" + tipoUsuario + ".rut)>=0";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String cadena = resultado.getString("repetido");
                    int valor = Integer.parseInt(cadena);
                    return valor;
                }

                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                System.out.println("sssss");
                return -1;
            }
        }
        return -1;
    }

}
