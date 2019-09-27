package ControladorBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase para conectar la base de datos y java. modo de uso. 1.se crea una nueva
 * instancia, solo una vez. 2.se crea la conexion atravez del metodo
 * crearConexion. 3.
 */
public class ConexionBD {

    private Connection conexion = null;

    public Connection getConexion() {
        return conexion;
    }

    /**
     * Crea la conexion con la base de datos, recibe como parametro el nombre de
     * la base de datos y contraseña que tienes como usuario de postgres.
     *
     * @param nombreBD
     * @param contraseña
     * @return true si se realiza la conexion, false de lo contrario
     */
    public boolean crearConexion(String nombreBD, String contraseña) {
        String url = "jdbc:postgresql://localhost:5432/" + nombreBD + "";// la url incluye el puerto y nombre del proyecto
        String password = "" + contraseña + "";// contraseña de postgres
        String usuario = "postgres";
        try {
            Class.forName("org.postgresql.Driver");

            conexion = DriverManager.getConnection(url, usuario, password);

            if (conexion != null) {
                System.out.println("CONEXION EXITOSA: crearConexion().");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA CONEXION: crearConexion() : " + ex);
        } catch (ClassNotFoundException ex) {
            //System.out.println(ex);
        }
        return false;
    }

    /**
     * muestra la informacion de un cliente resive como parametro la conexion
     * con la base de datos, para luego despues de hacer la consulta cerrar la
     * connexion.
     *
     * @param conexionAux
     * @param rut
     * @throws SQLException
     */
    public ArrayList<String> mostrarIndormacionCliente(Connection conexionAux, String rut) throws SQLException {
        ArrayList<String> informacion = new ArrayList<>();
        Connection miConexion = conexionAux;
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = "select usuario.nombrecompleto,usuario.rut,usuario.correo,usuario.contraseña, cliente.tarjetacredito "
                        + "from usuario join cliente on usuario.rut = '" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String tarjetaCredito = resultado.getString("tarjetacredito");

                    //añado la informacion a la lista.
                    informacion.add(nombreCompleto);
                    informacion.add(rutCliente);
                    informacion.add(correo);
                    informacion.add(contraseña);
                    informacion.add(tarjetaCredito);
                }
                resultado.close();
                st.close();
                // retorno la lista
                return informacion;
            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
            } finally {
                cerrarBaseDeDatos(miConexion);
            }
        }
        return null;
    }

    /**
     * devuelve la informacion de un organizador a traves de un arrayList
     *
     * @param conexionAux
     * @param rut
     * @return
     */
    public ArrayList<String> mostrarInformacionOrganizador(Connection conexionAux, String rut) throws SQLException {
        ArrayList<String> informacion = new ArrayList<>();
        Connection miConexion = conexionAux;
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = "select usuario.nombrecompleto,usuario.rut,usuario.correo,usuario.contraseña, organizador.tarjetacredito, organizador.telefono "
                        + "from usuario join organizador on usuario.rut = '" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String tarjetaCredito = resultado.getString("tarjetacredito");
                    String telefono = resultado.getString("telefono");

                    //añado la informacion a la lista.
                    informacion.add(nombreCompleto);
                    informacion.add(rutCliente);
                    informacion.add(correo);
                    informacion.add(contraseña);
                    informacion.add(tarjetaCredito);
                    informacion.add(telefono);
                }
                resultado.close();
                st.close();
                // retorno la lista
                return informacion;
            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
            } finally {
                cerrarBaseDeDatos(miConexion);
            }
        }
        return null;
    }
    /**
     * devuelve la informacion de un propietario a travez de un arrayList
     * @param conexionAux
     * @param rut
     * @return
     * @throws SQLException 
     */
    public ArrayList<String> mostrarInformacionPropietario(Connection conexionAux, String rut) throws SQLException {
        ArrayList<String> informacion = new ArrayList<>();
        Connection miConexion = conexionAux;
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = "select usuario.nombrecompleto,usuario.rut,usuario.correo,usuario.contraseña, propietario.cuentacorriente "
                        + "from usuario join propietario on usuario.rut = '" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String cuentaCorriente = resultado.getString("cuentacorriente");

                    //añado la informacion a la lista.
                    informacion.add(nombreCompleto);
                    informacion.add(rutCliente);
                    informacion.add(correo);
                    informacion.add(contraseña);
                    informacion.add(cuentaCorriente);
                }
                resultado.close();
                st.close();
                // retorno la lista
                return informacion;
            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
            } finally {
                cerrarBaseDeDatos(miConexion);
            }
        }
        return null;
    }

    /**
     * añade a un cliente a la base de datos, retorna true si lo añadio correctamente, false de lo contrario.
     *
     * @param conexionAux
     * @throws SQLException
     */
    public boolean añadirCliente(Connection conexionAux, String nombreCompleto, String rut, String correo,String contraseña, String telefono, String tarjetaCredito) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarUsuario(conexionAux, rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla cliente.
            if (numero == 1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "insert into cliente values('" + tarjetaCredito + "','" + rut + "')";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);
                    return true;
                }
            } else if (numero == -1) {
                boolean añadirUsuario = añadirUsuario(miConexion, nombreCompleto, rut, contraseña, correo,telefono);
                if (añadirUsuario == true) {
                    try {
                        java.sql.Statement st = conexion.createStatement();
                        String sql = "insert into cliente values('" + tarjetaCredito + "','" + rut + "')";
                        st.executeUpdate(sql);

                        st.close();

                    } catch (SQLException e) {
                        System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    } finally {
                        cerrarBaseDeDatos(miConexion);
                        return true;
                    }
                }
            }

        }
        return false;

    }
    /**
     * añade a un usuario a la base de datos, retorna true si lo añadio correctamente, false de lo contrario
     * @param conexionAux
     * @param nombreCompleto
     * @param rut
     * @param contraseña
     * @param correo
     * @param telefono
     * @return
     * @throws SQLException 
     */
    public boolean añadirUsuario(Connection conexionAux, String nombreCompleto, String rut, String contraseña, String correo,String telefono) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarUsuario(conexionAux, rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla cliente.

            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = "insert into usuario values('" + nombreCompleto + "','" + rut + "','" + contraseña + "','" + correo + "','" + telefono + "')";
                st.executeUpdate(sql);

                st.close();
                return true;

            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION" + e);
            }

        }
        return false;
    }
/**
 * cuenta si un rut esta en la tabla usuario, como rut es una primary key 
 * solo puede estar una vez en la tabla por ende, si esta en la tabla retorna 1,
 * 
 * @param conexionAux
 * @param rut
 * @return
 * @throws SQLException 
 */
    public int contarUsuario(Connection conexionAux, String rut) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = "select  count(usuario.rut) as repetido "
                        + "from usuario where usuario.rut='" + rut + "' "
                        + "group by usuario.rut\n"
                        + "having count(usuario.rut)>=0";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String cadena = resultado.getString("repetido");
                    int valor = Integer.parseInt(cadena);
                    return valor;
                }

                st.close();

            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION" + e);
            }
        }
        return -1;
    }

    public void añadirOrganizador() {
    }

    public void añadirPropietario() {

    }

    public void eliminarCliente() {
    }

    public void eliminarOrganizador() {
    }

    public void eliminarPropietario() {
    }

    public void cerrarBaseDeDatos(Connection conexionAux) throws SQLException {

        if (conexionAux != null) {
            conexionAux.close();

        }
    }


}
