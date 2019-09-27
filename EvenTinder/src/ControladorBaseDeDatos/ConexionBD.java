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
     * @return
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
     *
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
     * añade a un cliente a la base de datos, retorna true si lo añadio
     * correctamente, false de lo contrario.
     *
     * @param conexionAux
     * @param nombreCompleto
     * @param rut
     * @param correo
     * @param contraseña
     * @param telefono
     * @param tarjetaCredito
     * @return 
     * @throws SQLException
     */
    public boolean añadirCliente(Connection conexionAux, String nombreCompleto, String rut, String correo, String contraseña, String telefono, String tarjetaCredito) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, "usuario", rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla cliente.
            if (numero == 1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "insert into cliente values('" + tarjetaCredito + "','" + rut + "')";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                } finally {
                    cerrarBaseDeDatos(miConexion);
                    
                }
                return true;
            } else if (numero == -1) {
                boolean añadirUsuario = añadirUsuario(miConexion, nombreCompleto, rut, contraseña, correo, telefono);
                if (añadirUsuario == true) {
                    try {
                        java.sql.Statement st = conexion.createStatement();
                        String sql = "insert into cliente values('" + tarjetaCredito + "','" + rut + "')";
                        st.executeUpdate(sql);

                        st.close();

                    } catch (SQLException e) {
                        //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                        return false;
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
     * añade a un usuario a la base de datos, retorna true si lo añadio
     * correctamente, false de lo contrario. Metodo de uso interno
     *
     * @param conexionAux
     * @param nombreCompleto
     * @param rut
     * @param contraseña
     * @param correo
     * @param telefono
     * @return
     * @throws SQLException
     */
    private boolean añadirUsuario(Connection conexionAux, String nombreCompleto, String rut, String contraseña, String correo, String telefono) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, "usuario", rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla cliente.

            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = "insert into usuario values('" + nombreCompleto + "','" + rut + "','" + correo + "','" + contraseña + "','" + telefono + "')";
                st.executeUpdate(sql);

                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadir usuario" + e);
                return false;
            }

        }
        return false;
    }

    /**
     * agrega un organizador a la base de datos, retornado true si lo realiza
     * con exito, false de caso contrario
     *
     * @param conexionAux
     * @param nombreCompleto
     * @param rut
     * @param correo
     * @param contraseña
     * @param telefono
     * @param tarjetaCredito
     * @return
     * @throws SQLException
     */
    public boolean añadirOrganizador(Connection conexionAux, String nombreCompleto, String rut, String correo, String contraseña, String telefono, String tarjetaCredito) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, "usuario", rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla organizador.
            if (numero == 1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "insert into organizador values('" + tarjetaCredito + "','" + rut + "')";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: añadirOrganizador" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);
                    return true;
                }
            } else if (numero == -1) {
                boolean añadirUsuario = añadirUsuario(miConexion, nombreCompleto, rut, contraseña, correo, telefono);
                if (añadirUsuario == true) {
                    try {
                        java.sql.Statement st = conexion.createStatement();
                        String sql = "insert into organizador values('" + tarjetaCredito + "','" + rut + "')";
                        st.executeUpdate(sql);

                        st.close();

                    } catch (SQLException e) {
                        System.out.println("ERROR DE CONEXION: añadirOrganizador" + e);
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
     * se añade a la base de datos el usuario propietario, retorna true si se
     * añade exitosamente, false de manera contraria
     *
     * @param conexionAux
     * @param nombreCompleto
     * @param rut
     * @param correo
     * @param contraseña
     * @param telefono
     * @param cuentaCorriente
     * @return
     * @throws SQLException
     */
    public boolean añadirPropietario(Connection conexionAux, String nombreCompleto, String rut, String correo, String contraseña, String telefono, String cuentaCorriente) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, "usuario", rut);
            //significa que esta creado el usuario, y solo debemos añadir la referencia y la tarjeta de credito
            // a la tabla cliente.
            if (numero == 1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "insert into organizador values('" + cuentaCorriente + "','" + rut + "')";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: añadirPropietario" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);

                }
                return true;
            } else if (numero == -1) {
                boolean añadirUsuario = añadirUsuario(miConexion, nombreCompleto, rut, contraseña, correo, telefono);
                if (añadirUsuario == true) {
                    try {
                        java.sql.Statement st = conexion.createStatement();
                        String sql = "insert into organizador values('" + cuentaCorriente + "','" + rut + "')";
                        st.executeUpdate(sql);

                        st.close();

                    } catch (SQLException e) {
                        System.out.println("ERROR DE CONEXION: añadirPropietario" + e);
                    } finally {
                        cerrarBaseDeDatos(miConexion);

                    }
                }
                return true;
            }

        }
        return false;
    }

    /**
     * elimina un cliente de la base de dato, atraves del rut.
     *
     * @param conexionAux
     * @param rut
     * @return
     * @throws SQLException
     */
    public boolean eliminarCliente(Connection conexionAux, String rut) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numeroCliente = contarTiposDeUsuario(conexionAux, "cliente", rut);
            int numeroOrganizador = contarTiposDeUsuario(conexionAux, "organizador", rut);
            int numeroPropietario = contarTiposDeUsuario(conexionAux, "propietario", rut);
            // si el rut del usuario esta en la tabla cliente, y el rut no esta en las
            // tablas de organizador y propietario, esto implica que puedo borrar 
            // desde la tabla usuario, lo que permite la eliminacion en cascada.
            if (numeroCliente == 1 && numeroOrganizador == -1 && numeroPropietario == -1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "delete from usuario where usuario.rut='" + rut + "'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: eliminar cliente (desde la tabla de usuario)" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);
                }
                return true;
            } else {
                // si el tipo de usuario esta en las tablas de organizador o propietario, solo podemos eliminar al
                //tipo de usuario desde la tabla cliente.
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "delete from cliente where cliente.rut='" + rut + "'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: eliminar cliente(desde la tabla cliente.)" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);

                }
                return true;
            }
        }
        return false;
    }

    /**
     * se elimina a un organizador de la base de datos.
     *
     * @param conexionAux
     * @param rut
     * @return
     * @throws SQLException
     */
    public boolean eliminarOrganizador(Connection conexionAux, String rut) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numeroCliente = contarTiposDeUsuario(conexionAux, "cliente", rut);
            int numeroOrganizador = contarTiposDeUsuario(conexionAux, "organizador", rut);
            int numeroPropietario = contarTiposDeUsuario(conexionAux, "propietario", rut);
            // si el rut del usuario esta en la tabla organizador, y el rut no esta en las
            // tablas de cliente y propietario, esto implica que puedo borrar 
            // desde la tabla usuario, lo que permite la eliminacion en cascada.
            if (numeroOrganizador == 1 && numeroCliente == -1 && numeroPropietario == -1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "delete from usuario where usuario.rut='" + rut + "'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: eliminar organizador (desde la tabla de usuario)" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);
                }
                return true;
            } else {
                // si el tipo de usuario esta en las tablas de cliente o propietario, solo podemos eliminar al
                //tipo de usuario desde la tabla organizador.
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "delete from organizador where organizador.rut='" + rut + "'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: eliminar organizador(desde la tabla organizador.)" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);

                }
                return true;
            }
        }
        return false;
    }

    /**
     * elimina a un propietario de la base de datos.
     *
     * @param conexionAux
     * @param rut
     * @return
     * @throws SQLException
     */
    public boolean eliminarPropietario(Connection conexionAux, String rut) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numeroCliente = contarTiposDeUsuario(conexionAux, "cliente", rut);
            int numeroOrganizador = contarTiposDeUsuario(conexionAux, "organizador", rut);
            int numeroPropietario = contarTiposDeUsuario(conexionAux, "propietario", rut);
            // si el rut del usuario esta en la tabla propietario, y el rut no esta en las
            // tablas de cliente y organizador, esto implica que puedo borrar 
            // desde la tabla usuario, lo que permite la eliminacion en cascada.
            if (numeroPropietario == 1 && numeroCliente == -1 && numeroOrganizador == -1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "delete from usuario where usuario.rut='" + rut + "'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: eliminar propietario (desde la tabla de propietario)" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);
                }
                return true;
            } else {
                // si el tipo de usuario esta en las tablas de cliente o organizador, solo podemos eliminar al
                //tipo de usuario desde la tabla propietario.
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "delete from propietario where propietario.rut='" + rut + "'";
                    st.executeUpdate(sql);

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: eliminar propietario(desde la tabla propietario.)" + e);
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
     * propietario", si este ya esta registrado en la base de datos, si esta
     * registrado retorna 1, -1 de lo contrario. metodo de uso interno
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
                String sql = "select  count("+tipoUsuario +".refusuario) as repetido "
                        + "from " + tipoUsuario + " where " + tipoUsuario+".refusuario='" + rut + "'"
                        + "group by " + tipoUsuario +".refusuario"
                        + "having count(" + tipoUsuario +".refusuario)>=0";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String cadena = resultado.getString("repetido");
                    int valor = Integer.parseInt(cadena);
                    return valor;
                }

                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                return-1;
            }
        }
        return -1;
    }

    public void cerrarBaseDeDatos(Connection conexionAux) throws SQLException {

        if (conexionAux != null) {
            conexionAux.close();

        }
    }

    // modificar usuarios.
    /**
     * modifica la informacion de un usuario, siempre y cuando este previamente
     * creado.
     * metodo privado.
     *
     * @param conexionAux
     * @param nombreCompleto
     * @param rut
     * @param correo
     * @param contraseña
     * @param telefono
     * @return
     */
    private boolean modificarUsuario(Connection conexionAux, String nombreCompleto, String rut, String correo, String contraseña, String telefono) throws SQLException {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, "usuario", rut);
            //significa que esta creado el usuario, y debemos de actualizar sus registros.
            if (numero == 1) {
                try {
                    java.sql.Statement st = conexion.createStatement();
                    String sql = "update usuario set nombrecompleto='" + nombreCompleto + "', rut='" + rut + "', correo='" + correo + "', contraseña='" + contraseña + "', telefono= '" + telefono + "')";
                    st.executeUpdate(sql);

                    st.close();
                    return true;

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: modificar usuario" + e);
                }

            }
            // significa que al usuario al que se le quiere actualizar los datos no existe
            return false;
        }
        //no hay conexion con la base de datos
        return false;
    }
    /**
     * se hace generico el metodo modificar usuario, retorna true si se modifico
     * correctamente el usuario, false de lo contrario
     * @param conexionAux
     * @param tipoUsuario
     * @param nombreCompleto
     * @param rut
     * @param correo
     * @param contraseña
     * @param telefono
     * @param tarjetaCredito
     * @param cuentaCorriente
     * @return
     * @throws SQLException 
     */
    public boolean modificarTiposDeUsuarios(Connection conexionAux,String tipoUsuario, String nombreCompleto, String rut, String correo, String contraseña, String telefono, String tarjetaCredito,String cuentaCorriente) throws SQLException{
         Connection miConexion = conexionAux;
        if (miConexion != null) {
            // verificamos si el usuario existe
            int numero = contarTiposDeUsuario(conexionAux, "usuario", rut);
            int numeroTipoUsuario = contarTiposDeUsuario(conexionAux, tipoUsuario, rut);
            //significa que esta creado el usuario y el cliente, y  debemos modificar primero la informacion del usuario,
            // para luego modificar la informacion que se encuentra en la tabla cliente
            if (numero == 1 && numeroTipoUsuario==1) {
                try {
                    //primero modificamos los datos en la tabla de usuario.
                    modificarUsuario(conexionAux, nombreCompleto, rut, correo, contraseña, telefono);
                    //2. modificamos los datos en la tabla cliente.
                    java.sql.Statement st = conexion.createStatement();
                    if(tipoUsuario.equalsIgnoreCase("cliente")== true)
                    {
                        String sql = "update cliente set tarjetacredito='"+tarjetaCredito+"',refusuario='"+rut+"' where cliente.refusuario='"+rut+"'";
                        st.executeUpdate(sql);
                    }
                    else if(tipoUsuario.equalsIgnoreCase("organizador")== true)
                    {
                        String sql = "update organizador set tarjetacredito='"+tarjetaCredito+"',refusuario='"+rut+"' where cliente.refusuario='"+rut+"'";
                        st.executeUpdate(sql);
                    }
                    else if(tipoUsuario.equalsIgnoreCase("propietario") == true)
                    {
                        String sql = "update propietario set cuentacorriente='"+cuentaCorriente+"',refusuario='"+rut+"' where cliente.refusuario='"+rut+"'";
                        st.executeUpdate(sql);
                    }

                    st.close();

                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: modificar Usuario" + e);
                } finally {
                    cerrarBaseDeDatos(miConexion);
                }
                return true;
            } else  {
                //el cliente no esta creado previamente.
                return false;
                }
            

        }
        // no hay conexion
        return false;
    }



    /**
     *  busca si un usuario esta en la base de datos.
     * @param conexionAux
     * @param rut
     * @return true si el usuario se encuentra, false de lo contrario.
     */
    public boolean ValidarInicioSecion(Connection conexionAux, String rut,String clave)
    {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            try {
                java.sql.Statement st = conexion.createStatement();
                String sql = "select  count(usuario.rut) as repetido " +
                "from usuario where usuario.rut='"+rut+"' and usuario.contraseña='"+clave+"' " +
                "group by usuario.rut " +
                "having count(usuario.rut)>=0";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String cadena = resultado.getString("repetido");
                    int valor = Integer.parseInt(cadena);
                    if( valor == 1)
                    {
                        return true;
                    }
                }

                st.close();

            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION: ValidarInicioSecion" + e);
            }
        }
        return false;
    }
}


