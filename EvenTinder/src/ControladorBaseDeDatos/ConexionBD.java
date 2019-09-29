package ControladorBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase para conectar la base de datos y java. modo de uso. 
 * 1.se crea una nueva instancia, solo una vez. 
 * 2.se crea la conexion con la base de datos.
 * 3.se obtienen la clase de datos.
 * 4.cada metodo que esta en esta clase, nececita una instaciona de conexion "activa"
 * por lo cual es necesario que se le pase como parametro.
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
     * @param nombreBD: nombre de la base de datos.
     * @param contraseña: contraseña de la base de datos que tiene en postgres.
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
     * devuelve la informacion de un usuario, en este orden.
     * 1.nombre completo.
     * 2.rut del cliente.
     * 3.correo del cliente,
     * 4.contraseña del cliente.
     * 5.telefono del cliete.
     * 6.tarjeta de credito / cuenta corriente.
     *
     * @param conexionAux: conexion activa de la base de datos.
     * @param tipoUsuario: cliente, organizador u propietario.
     * @param rut: rut del usuario del cual se quiere obtener informacion.
     * @return ArrayList con la informacion del usuario.
     * @throws SQLException
     */
    public ArrayList<String> mostrarInformacionUsuario(Connection conexionAux, String tipoUsuario, String rut) throws SQLException {
        ArrayList<String> informacion = new ArrayList<>();
        Connection miConexion = conexionAux;
        if (miConexion != null)// si hay conexion.
        {
            if(tipoUsuario.equalsIgnoreCase("cliente")== true || tipoUsuario.equalsIgnoreCase("organizador")== true)
            {
                try {
                java.sql.Statement st = conexion.createStatement();

                String sql ="select * from "+tipoUsuario+" where rut='"+rut+"'" ;

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String tarjetaCredito = resultado.getString("tarjetacredito");

                    //añado la informacion a la lista.
                    informacion.add(nombreCompleto);
                    informacion.add(rutCliente);
                    informacion.add(correo);
                    informacion.add(contraseña);
                    informacion.add(telefono);
                    informacion.add(tarjetaCredito);
                }
                resultado.close();
                st.close();
                // retorno la lista
                return informacion;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                cerrarBaseDeDatos(miConexion);
            }
            }
        }
        else{
            try {
                java.sql.Statement st = conexion.createStatement();

                String sql ="select * from "+tipoUsuario+" where rut='"+rut+"'" ;

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String cuentaCorriente = resultado.getString("cuentacorriente");

                    //añado la informacion a la lista.
                    informacion.add(nombreCompleto);
                    informacion.add(rutCliente);
                    informacion.add(correo);
                    informacion.add(contraseña);
                    informacion.add(telefono);
                    informacion.add(cuentaCorriente);
                }
                resultado.close();
                st.close();
                // retorno la lista
                return informacion;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                cerrarBaseDeDatos(miConexion);
            }
        
        }
        return null;
    }

    /**
     * añade a un usuario a la base de datos, retorna true si lo añadio
     * correctamente, false de lo contrario.
     *
     * @param conexionAux
     * @param tipoUsuario
     * @param nombreCompleto
     * @param rut
     * @param correo
     * @param contraseña
     * @param telefono
     * @param tarjeta
     * @return
     * @throws SQLException
     */
    public boolean añadirUsuario(Connection conexionAux, String tipoUsuario, String nombreCompleto, String rut, String correo, String contraseña, String telefono, String tarjeta) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, "cliente", rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla cliente.
            if (numero == -1) {// el cliente no esta registrado, por lo tanto hay que registrarlo.
                try {

                    java.sql.Statement st = conexion.createStatement();
                    String sql = "insert into " + tipoUsuario + " values('" + nombreCompleto + "','" + rut + "','" + correo + "','" + contraseña + "','" + telefono + "','" + tarjeta + "')";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                } finally {
                    cerrarBaseDeDatos(miConexion);

                }
                return true;

            }
            // retornamos false debido a que el cliente ya esta registrado.
            return false;

        }
        return false;

    }

    /**
     * elimina un cliente de la base de dato, atraves del rut.
     *
     * @param conexionAux:
     * @param rut
     * @return
     * @throws SQLException
     */
    public boolean eliminarUsuario(Connection conexionAux,String tipoUsuario, String rut) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, tipoUsuario, rut);

            // si el rut del usuario esta en la tabla cliente, y el rut no esta en las
            // tablas de organizador y propietario, esto implica que puedo borrar 
            // desde la tabla usuario, lo que permite la eliminacion en cascada.
            if (numero == 1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "delete from "+tipoUsuario+" where "+tipoUsuario+".rut='"+rut+"'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: eliminar cliente (desde la tabla de usuario)" + e);
                    return false;
                } finally {
                    cerrarBaseDeDatos(miConexion);
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
                java.sql.Statement st = conexion.createStatement();
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
    /**
     * cierra la conexion con la base de datos
     * @param conexionAux
     * @throws SQLException 
     */
    public void cerrarBaseDeDatos(Connection conexionAux) throws SQLException {

        if (conexionAux != null) {
            conexionAux.close();

        }
    }

    /**
     * busca si un usuario esta en la base de datos.
     *
     * @param conexionAux
     * @param rut
     * @return true si el usuario se encuentra, false de lo contrario.
     */
    public boolean ValidarInicioSecion(Connection conexionAux,String tipoUsuario, String rut, String clave) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = " select  count("+tipoUsuario+".rut) as repetido \n" +
                "from "+tipoUsuario+" where "+tipoUsuario+".rut='"+rut+"' and "+tipoUsuario+".contraseña='"+clave+"'\n" +
                "group by "+tipoUsuario+".rut\n" +
                "having count("+tipoUsuario+".rut)>=0 ";
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
            }finally{
                cerrarBaseDeDatos(conexionAux);
            }
        }
        return false;
    }
    /**
     * modifica los datos de un usuario.
     * @param conexionAux
     * @param tipoUsuario
     * @param rutUsuario
     * @param nombreCompleto
     * @param rut
     * @param correo
     * @param contraseña
     * @param telefono
     * @param tarjeta
     * @return
     * @throws SQLException 
     */
    public boolean modificarDatosUsuario(Connection conexionAux, String tipoUsuario,String rutUsuario, String nombreCompleto, String rut, String correo, String contraseña, String telefono, String tarjeta) throws SQLException
    {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            if(tipoUsuario.equalsIgnoreCase("cliente")== true || tipoUsuario.equalsIgnoreCase("organizador")==true)
            {
                
                try {
                java.sql.Statement st = conexion.createStatement();
                String sql = " update "+tipoUsuario+" set nombrecompleto='"+nombreCompleto+"',rut='"+rut+"',correo='"+correo+"',contraseña='"+contraseña+"',telefono='"+telefono+"',tarjetacredito='"+tarjeta+"' where "+tipoUsuario+".rut='"+rutUsuario+"' ";
                 st.executeUpdate(sql);

                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: ValidarInicioSecion" + e);
                return false;
            }finally{
                    cerrarBaseDeDatos(conexionAux);
                }
            }else{
                
            
            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = " update "+tipoUsuario+" set nombrecompleto='"+nombreCompleto+"',rut='"+rut+"',correo='"+correo+"',contraseña='"+contraseña+"',telefono='"+telefono+"',cuentacorriente='"+tarjeta+"' where "+tipoUsuario+".rut='"+rutUsuario+"' ";
                System.out.println(sql);
                 st.executeUpdate(sql);

                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: ValidarInicioSecion" + e);
                return false;
            }finally{
                    cerrarBaseDeDatos(conexionAux);
                }
            }
        }
        return false;
    
    }

   
}
