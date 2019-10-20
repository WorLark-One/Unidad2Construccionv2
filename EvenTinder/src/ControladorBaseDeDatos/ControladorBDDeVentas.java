package ControladorBaseDeDatos;

import ModuloGestionVentas.Compra;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */

public class ControladorBDDeVentas {

    ConexionBD conexion;

    public ControladorBDDeVentas() {
        this.conexion = new ConexionBD();
        inicializarBD();
    }

    /**
     * Inicializa las tablas de la base de datos.
     */
    public void inicializarBD() {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        this.conexion.crearTablas(miConexion);
    }

    /**
     * @param rutUsuario
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDeHistorialDeCompraDeUnUsuario(String rutUsuario) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Compra> compras = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select compra.id, compra.numeroentradas, compra.fechacompra, compra.preciototal\n"
                        + "from realizacompra\n"
                        + "inner join compra on realizacompra.refcompra = compra.id\n"
                        + "where realizacompra.refcliente='" + rutUsuario + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idCompra = Integer.parseInt(resultado.getString("id"));
                    int nombre = resultado.getInt("numeroentradas");
                    Date fecha = resultado.getDate("fechacompra");
                    int precioTotal = resultado.getInt("preciototal");
                    Compra compra = new Compra(idCompra, nombre, fecha, precioTotal);
                    compras.add(compra);

                }
                resultado.close();
                st.close();
                Collections.sort(compras);
                return compras;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return null;
    }

    /**
     * @param idEvento
     * @return
     */
    public ArrayList<Compra> obtenerInformacionDeHistorrialDeCompraDeUnEvento(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Compra> compras = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select DISTINCT  compra.id, compra.numeroentradas, compra.fechacompra, compra.preciototal \n"
                        + "from entrada\n"
                        + "inner join compra on entrada.refcompra=compra.id\n"
                        + "inner join evento on evento.id=entrada.refevento\n"
                        + "where evento.id="+idEvento+" ";
                // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idCompra = Integer.parseInt(resultado.getString("id"));
                    int nombre = resultado.getInt("numeroentradas");
                    Date fecha = resultado.getDate("fechacompra");
                    int precioTotal = resultado.getInt("preciototal");
                    Compra compra = new Compra(idCompra, nombre, fecha, precioTotal);
                    compras.add(compra);

                }
                resultado.close();
                st.close();
                Collections.sort(compras);
                return compras;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return null;
    }

    public boolean registrarCompra(String rutCliente, int idEvento, String nombreSector, int cantidadDeEntradas, int idPropiedad) {

        this.conexion.crearConexion();
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {
            int capacidadMaximaEvento = obtenerCapacidMaximaEvento(miConexion, idEvento);
            int numeroEntradasVendidas = obtenerNumeroDeEntradasVendidasDeEvento(miConexion, idEvento);
            int entradas = numeroEntradasVendidas + cantidadDeEntradas;
            if (entradas <= capacidadMaximaEvento) {
                try {
                    java.util.Date fecha = new Date();
                    int precioCompra = ObtenerPrecioAsociadoAlSector(conexion, idEvento, nombreSector, idPropiedad);
                    int precioTotalCompra = precioCompra * cantidadDeEntradas;
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into compra values(default," + cantidadDeEntradas + ",'" + fecha + "'," + precioTotalCompra + ")RETURNING id";
                    ResultSet resultado = st.executeQuery(sql);
                    while (resultado.next()) {
                        int idCompra = Integer.parseInt(resultado.getString("id"));
                        boolean relacionarCompraCliente = relacionCompraCliente(conexion, rutCliente, idCompra);
                        for (int i = 0; i < cantidadDeEntradas; i++) {
                            generarEntrada(miConexion, idEvento, idCompra);
                        }
                    }
                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //System.out.println("error al cerrar la base de datos.");
                    }

                }
            }

        }
        return false;

    }

    public boolean cancelarCompra(int idCompra) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {

            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from compra where compra.id=" + idCompra + "";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: eliminar evento (desde la tabla de usuario)" + e);
                return false;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    // System.out.println("No se cerro satisfactoriamente la base de datos.");
                }
            }

        }
        return false;
    }

    private int ObtenerPrecioAsociadoAlSector(ConexionBD conexion, int idEvento, String nombreSector, int idPropiedad) {
        Connection miConexion = conexion.getConexion();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from eventotienesector where refevento=" + idEvento + " and refsector='" + nombreSector + "' and refpropiedad=" + idPropiedad + "";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int precioSector = resultado.getInt("precio");
                    return precioSector;
                }
                resultado.close();
                st.close();
                return 0;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return 0;
            }

        }
        return 0;
    }

    private boolean relacionCompraCliente(ConexionBD conexion, String rutCliente, int idCompra) {
        this.conexion.crearConexion();
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into realizacompra values('" + rutCliente + "'," + idCompra + ")";
                st.executeUpdate(sql);
                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);

                return false;
            }

        }
        return false;
    }

    private boolean generarEntrada(Connection conexion, int idEvento, int idCompra) {

        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into entrada values(default," + idEvento + "," + idCompra + ")";
                st.executeUpdate(sql);
                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);

                return false;
            }

        }
        return false;

    }

    private int obtenerCapacidMaximaEvento(Connection conexion, int idEvento) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * from evento where evento.id=" + idEvento + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int capacidad = resultado.getInt("capacidad");
                    return capacidad;
                }
                st.close();

            } catch (SQLException e) {
                //System.out.println("error conexion");

                return 0;
            }

        }
        return 0;
    }

    private int obtenerNumeroDeEntradasVendidasDeEvento(Connection conexion, int idEvento) {
        Connection miConexion = conexion;
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select count(entrada.refevento)as numeroentras\n"
                        + "from entrada\n"
                        + "where entrada.refevento=1";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int numeroEntradas = resultado.getInt("numeroentras");
                    return numeroEntradas;
                }
                resultado.close();
                st.close();
                return 0;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return 0;
            }

        }
        return 0;
    }

}
