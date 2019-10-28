package ControladorBaseDeDatos;

import ModuloGestionUsuario.Cliente;
import ModuloGestionUsuario.Organizador;
import ModuloGestionUsuario.Propietario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase que se encarga de interactuar con la base de datos. 1.
 */
public class ControladorBDDeUsuario {

    private ConexionBD conexion;

    /**
     * Default constructor
     */
    public ControladorBDDeUsuario() {
        this.conexion = new ConexionBD();
        inicializarBD();
    }

    /**
     * Inicializa las tablas de la base de datos.
     */
    private void inicializarBD() {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        this.conexion.crearTablas(miConexion);
    }

    /**
     * Activa la cuenta de un usuario.
     *
     * @param tipoUsuario:puede ser cliente,organizador.
     * @param rut: rut del usuario.
     * @return
     */
    public boolean activarCuentaUsuario(String tipoUsuario, String rut) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "update " + tipoUsuario + " set activo='true' where " + tipoUsuario + ".rut='" + rut + "'";
                st.executeUpdate(sql);
                st.close();
                cerrarConexionBD(miConexion);
                return true;
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return false;
            }
        }
        return false;
    }

    /**
     * Indica si un usuario se encuentra registrado en el sistema..
     *
     * @param tipoUsuario: cliente, organizador u propietario
     * @param rut: rut del usuario a validar
     * @param clave: contraseña del usuario a logear.
     * @return 1 si el usuario esta registrado y su cuenta esta activa. -1 si
     * esta registrado pero su cuenta esta inactiva. 0 si no esta registrado en
     * el sistema.
     */
    public int preguntarPorUsuario(String tipoUsuario, String rut, String clave) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select " + tipoUsuario + ".activo, count(" + tipoUsuario + ".rut) as repetido \n"
                        + "from  " + tipoUsuario + " where " + tipoUsuario + ".rut='" + rut + "' and  " + tipoUsuario + ".contraseña='" + clave + "'\n"
                        + "group by " + tipoUsuario + ".rut\n"
                        + "having count(" + tipoUsuario + ".rut)>=0 ";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String existeUsuario = resultado.getString("repetido");
                    int valor = Integer.parseInt(existeUsuario);
                    boolean activo = resultado.getBoolean("activo");
                    if (valor == 1 && activo == true) {
                        st.close();
                        cerrarConexionBD(miConexion);
                        return 1;
                    } else if (valor == 1 && activo == false) {
                        st.close();
                        cerrarConexionBD(miConexion);
                        return -1;
                    }
                }
                st.close();
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return 0;
            }
        }
        return 0;
    }

    /**
     * Obtiene la informacion de un cliente a traves de su rut
     *
     * @param rut
     * @return cliente.
     */
    public Cliente obtenerInformacionCliente(String rut) {
        Cliente cliente;
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * from cliente where rut='" + rut + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String tarjetaCredito = resultado.getString("tarjetacredito");
                    boolean activo = resultado.getBoolean("activo");
                    cliente = new Cliente(nombreCompleto, rutCliente, contraseña, telefono, correo, tarjetaCredito);
                    cliente.setActivo(activo);
                    return cliente;
                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }
        }
        return null;
    }

    /**
     * Obtiene la informacion de un organizador a traves de su rutOrganizador
     *
     * @param rutOrganizador
     * @return
     */
    public Organizador obtenerInformacionOrganizador(String rutOrganizador) {
        Organizador organizador;
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * from organizador where rut='" + rutOrganizador + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String tarjetaCredito = resultado.getString("tarjetacredito");
                    boolean activo = resultado.getBoolean("activo");
                    organizador = new Organizador(nombreCompleto, rutCliente, contraseña, telefono, correo, tarjetaCredito);
                    organizador.setActivo(activo);
                    resultado.close();
                    st.close();
                    cerrarConexionBD(miConexion);
                    return organizador;
                }
                resultado.close();
                st.close();
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }
        }
        return null;
    }

    /**
     * Obtiene la informacion de un Propietario a traves de su rutPropietario
     *
     * @param rutPropietario
     * @return
     */
    public Propietario obtenerInformacionPropietario(String rutPropietario) {
        Propietario propietario;
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * from propietario where rut='" + rutPropietario + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombreCompleto = resultado.getString("nombrecompleto");
                    String rutCliente = resultado.getString("rut");
                    String correo = resultado.getString("correo");
                    String contraseña = resultado.getString("contraseña");
                    String telefono = resultado.getString("telefono");
                    String cuentaCorriente = resultado.getString("cuentacorriente");
                    boolean activo = resultado.getBoolean("activo");
                    propietario = new Propietario(nombreCompleto, rutCliente, contraseña, telefono, correo, cuentaCorriente);
                    propietario.setActivo(activo);
                    cerrarConexionBD(miConexion);
                    return propietario;
                }
                resultado.close();
                st.close();
                cerrarConexionBD(miConexion);

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }
        }
        return null;
    }

    /**
     * Registra un usuario en la base de datos.
     *
     * @param tipoUsuario:cliente, organizador u propietario
     * @param nombre:nombre del usuario
     * @param rut: rut del usuario
     * @param clave: clave del usuario
     * @param correo: correo electronico del usuario
     * @param telefono: telefono del usuario
     * @param tarjeta: esta puede ser cuenta corriente o tarjeta de credito.
     * @return true si realizo la operacion con exito, false de lo contrario.
     */
    public boolean añadirUsuario(String tipoUsuario, String nombre, String rut, String correo, String clave, String telefono, String tarjeta) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            int numero = verificarUsuarioRegistradoEnBD(miConexion, tipoUsuario, rut);
            if (numero == -1) {
                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into " + tipoUsuario + " values('" + nombre + "','" + rut + "','" + clave + "','" + correo + "','" + telefono + "','" + tarjeta + "')";
                    st.executeUpdate(sql);
                    st.close();
                    cerrarConexionBD(miConexion);
                    return true;
                } catch (SQLException e) {
                    cerrarConexionBD(miConexion);
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Modifica la informacion de un usuario. Solo puede modificar la
     * informacion de usuarios activos.
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
     */
    public boolean modificarUsuario(String tipoUsuario, String rutUsuarioModificar, String nombre, String correo, String clave, String telefono, String tarjeta) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean clienteActivo = VerificarSiLaCuentaDeUnUsuarioEstaActiva(miConexion, tipoUsuario, rutUsuarioModificar);
            if (tipoUsuario.equalsIgnoreCase("cliente") == true) {
                if (clienteActivo == true) {
                    try {
                        java.sql.Statement st = miConexion.createStatement();
                        String sql = "UPDATE cliente SET nombrecompleto='" + nombre + "', correo='" + correo + "', contraseña='" + clave + "', telefono='" + telefono + "', tarjetacredito='" + tarjeta + "' where rut='" + rutUsuarioModificar + "'";
                        st.executeUpdate(sql);
                        st.close();
                        cerrarConexionBD(miConexion);
                        return true;
                    } catch (SQLException e) {
                        cerrarConexionBD(miConexion);
                        return false;
                    }
                }
                return false;
            } else if (tipoUsuario.equalsIgnoreCase("organizador") == true) {
                if (clienteActivo == true) {
                    try {
                        java.sql.Statement st = miConexion.createStatement();
                        String sql = "UPDATE organizador SET nombrecompleto='" + nombre + "', correo='" + correo + "', contraseña='" + clave + "', telefono='" + telefono + "', tarjetacredito='" + tarjeta + "' where rut='" + rutUsuarioModificar + "'";
                        st.executeUpdate(sql);
                        st.close();
                        cerrarConexionBD(miConexion);
                        return true;
                    } catch (SQLException e) {
                        cerrarConexionBD(miConexion);
                        return false;
                    }
                }
                return false;
            } else if (tipoUsuario.equalsIgnoreCase("propietario") == true) {
                if (clienteActivo == true) {
                    try {
                        java.sql.Statement st = miConexion.createStatement();
                        String sql = "UPDATE propietario SET nombrecompleto='" + nombre + "', correo='" + correo + "', contraseña='" + clave + "', telefono='" + telefono + "', cuentacorriente='" + tarjeta + "' where rut='" + rutUsuarioModificar + "'";
                        st.executeUpdate(sql);
                        st.close();
                        cerrarConexionBD(miConexion);
                        return true;
                    } catch (SQLException e) {
                        cerrarConexionBD(miConexion);
                        return false;
                    }
                }
                return false;
            }
        }
        return false;

    }

    /**
     * Este metodo deja inactiva una cuenta de un usuario.
     *
     * @param tipoUsuario:cliente,organizador u propietario
     * @param rutUsuario: rutUsuario del usuario que se desea eliminar.
     * @return true si se elimino el usuario, false de lo contrario.
     */
    public boolean BorrarRegistroDeUsuarioEnBD(String tipoUsuario, String rutUsuario){
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            int numero = verificarUsuarioRegistradoEnBD(miConexion, tipoUsuario, rutUsuario);
            if (numero == 1) {
                if (tipoUsuario.equalsIgnoreCase("cliente") == true) {
                    boolean tieneCompras = tieneComprasAsociadas(miConexion, rutUsuario);
                    if (tieneCompras == true) {
                        cambiarEstadoCuentaUsuario(miConexion, tipoUsuario, rutUsuario, "false");
                        cerrarConexionBD(miConexion);
                        return true;
                    } else {
                        borrarRegistroUsuario(miConexion, tipoUsuario, rutUsuario);
                        cerrarConexionBD(miConexion);
                        return true;
                    }
                } else if (tipoUsuario.equalsIgnoreCase("organizador") == true) {
                    boolean eventosActivos = VerificarEventosActivos(miConexion, rutUsuario);
                    if (eventosActivos == false) {
                        cambiarEstadoCuentaUsuario(miConexion, tipoUsuario, rutUsuario, "false");
                        cerrarConexionBD(miConexion);
                        return true;
                    } else {
                        borrarRegistroUsuario(miConexion, tipoUsuario, rutUsuario);
                        cerrarConexionBD(miConexion);
                        return true;
                    }
                } else if (tipoUsuario.equalsIgnoreCase("propietario") == true) {
                    boolean eventosAsociadosPropiedad = VerificarEventosAsociadosPropiedad(miConexion, rutUsuario);
                    if (eventosAsociadosPropiedad == false) {
                        cambiarEstadoCuentaUsuario(miConexion, tipoUsuario, rutUsuario, "false");
                        cerrarConexionBD(miConexion);
                        return true;
                    } else {
                        borrarRegistroUsuario(miConexion, tipoUsuario, rutUsuario);
                        cerrarConexionBD(miConexion);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * verifica a traves del rutUsuario de un tipo de usuario " cliente,
     * organizador o propietario", si este ya esta registrado en la base de
     * datos, retorna 1, -1 de lo contrario. metodo de uso interno
     *
     * @param conexionAux: conexion con la base de datos.
     * @param tipoUsuario: tipo de usuario, el
     * @param rutUsuario
     * @return
     */
    private int verificarUsuarioRegistradoEnBD(Connection conexionAux, String tipoUsuario, String rutUsuario) {
        Connection miConexion = conexionAux;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select  count(" + tipoUsuario + ".rut) as repetido "
                        + "from " + tipoUsuario + " where " + tipoUsuario + ".rut='" + rutUsuario + "'"
                        + "group by " + tipoUsuario + ".rut"
                        + " having count(" + tipoUsuario + ".rut)>=0";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String cadena = resultado.getString("repetido");
                    int valor = Integer.parseInt(cadena);
                    st.close();
                    cerrarConexionBD(miConexion);
                    return valor;
                }
                st.close();

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return -1;
            }
        }
        return -1;
    }

    /**
     * Metodo privado. Verifica si un usuario tiene su cuenta activa.
     *
     * @param tipoUsuario: tipo de usuario.
     * @param rutUsuario:rutUsuario del organizador
     * @return
     */
    private boolean VerificarSiLaCuentaDeUnUsuarioEstaActiva(Connection miConexion, String tipoUsuario, String rutUsuario) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select " + tipoUsuario + ".activo from " + tipoUsuario + " where " + tipoUsuario + ".rut='" + rutUsuario + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    boolean activo = resultado.getBoolean("activo");
                    st.close();
                    return activo;
                }
                st.close();
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Verifica si un organizador tiene eventos activos.
     *
     * @param miConexion:conexion con la base de datos.
     * @param rutOrganizador:rutOrganizador del organizador.
     * @return true si el organizador tiene eventos activos, false de lo 
     * contrario
     */
    private boolean VerificarEventosActivos(Connection miConexion, String rutOrganizador) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select DISTINCT organizador.nombrecompleto,evento.nombre \n"
                        + "from organizador\n"
                        + "inner join evento on organizador.rut=evento.reforganizador\n"
                        + "where evento.publicado='true' and organizador.rut='" + rutOrganizador + "'"
                        + "and evento.activo='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    return true;
                }
                st.close();
                return false;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * verifica si hay eventos asociados a una propiedad.
     *
     * @param miConexion:conexion con la base de datos.
     * @param rut: rut de un propietario.
     * @return
     */
    private boolean VerificarEventosAsociadosPropiedad(Connection miConexion, String rut) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select DISTINCT propietario.rut\n"
                        + "from propietario\n"
                        + "inner join propiedad on propietario.rut = propiedad.refpropietario\n"
                        + "inner join celebra ON celebra.refpropiedad = propiedad.id\n"
                        + "where propietario.rut='" + rut + "'"
                        + "and propiedad.activa='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    st.close();
                    return true;
                }
                st.close();
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * verifica si hay compras asociadas a un cliente.
     *
     * @param miConexion:conexion con la base de datos.
     * @param rutCliente: rutCliente del cliente.
     * @return true si hay compras asociadas, false de lo contrario
     */
    private boolean tieneComprasAsociadas(Connection miConexion, String rutCliente) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select DISTINCT cliente.rut\n"
                        + "from compra\n"
                        + "inner join realizacompra on compra.id=realizacompra.refcompra\n"
                        + "inner join cliente on realizacompra.refcliente=cliente.rut\n"
                        + "where cliente.rut='" + rutCliente + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    return true;
                }
                st.close();
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Cambia el estado de una cuenta,la puede habilitar o desabilirar
     *
     * @param miConexion: conexion con la base de datos
     * @param tipoUsuario:tipo de usuario.
     * @param rut:rut del usuario.
     * @return true sicambia el estado de la cuenta de un usuario, false de lo
     * contrario.
     */
    private boolean cambiarEstadoCuentaUsuario(Connection miConexion, String tipoUsuario, String rut, String estado) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "update  " + tipoUsuario + " set activo='" + estado + "' where " + tipoUsuario + ".rut='" + rut + "'";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * borra los registros de un usuario.
     *
     * @param miConexion: conexion con la base de datos.
     * @param tipoUsuario:tipo de usuario.
     * @param rut:rut del usuario.
     */
    private void borrarRegistroUsuario(Connection miConexion, String tipoUsuario, String rut) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from " + tipoUsuario + " where " + tipoUsuario + ".rut='" + rut + "'";
                st.executeUpdate(sql);
                st.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * cierra la conexion con la base de datos.
     *
     * @param conexion: conexion con la base de datos.
     */
    private void cerrarConexionBD(Connection conexion) {
        try {
            this.conexion.cerrarBaseDeDatos(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
