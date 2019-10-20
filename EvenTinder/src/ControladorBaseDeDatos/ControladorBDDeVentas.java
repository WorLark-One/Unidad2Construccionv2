package ControladorBaseDeDatos;

import ModuloGestionEventos.Entrada;
import ModuloGestionVentas.Compra;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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

                String sql = "select DISTINCT compra.id,compra.numeroentradas,compra.fechacompra,compra.preciototal,\n"
                        + "asociacioneventoentradasector.refevento,evento.nombre,asociacioneventoentradasector.refsector\n"
                        + "from compra\n"
                        + "inner join instanciaentrada ON instanciaentrada.refcompra = compra.id\n"
                        + "inner join entrada ON entrada.id = instanciaentrada.refentrada\n"
                        + "inner join asociacioneventoentradasector ON asociacioneventoentradasector.refentrada = entrada.id\n"
                        + "inner join evento ON evento.id = asociacioneventoentradasector.refevento\n"
                        + "inner join realizacompra ON realizacompra.refcompra = compra.id\n"
                        + "where realizacompra.refcliente='" + rutUsuario + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idCompra = Integer.parseInt(resultado.getString("id"));
                    int numeroEntradas = resultado.getInt("numeroentradas");
                    Date fecha = resultado.getDate("fechacompra");
                    int precioTotal = resultado.getInt("preciototal");
                    int refEvento = resultado.getInt("refevento");
                    String nombreEvento = resultado.getString("nombre");
                    String nombreSector = resultado.getString("refsector");
                    ArrayList<Entrada> lista = obtenerListaEntradas(miConexion, idCompra);
                    Compra compra = new Compra(idCompra, numeroEntradas, fecha, precioTotal, refEvento, nombreEvento, nombreSector, idCompra, lista);
                    compras.add(compra);

                }
                resultado.close();
                st.close();
                //Collections.sort(compras);
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

                String sql = "select DISTINCT compra.id,compra.numeroentradas,compra.fechacompra,compra.preciototal,\n"
                        + "asociacioneventoentradasector.refevento,evento.nombre,asociacioneventoentradasector.refsector\n"
                        + "from compra\n"
                        + "inner join instanciaentrada ON instanciaentrada.refcompra = compra.id\n"
                        + "inner join entrada ON entrada.id = instanciaentrada.refentrada\n"
                        + "inner join asociacioneventoentradasector ON asociacioneventoentradasector.refentrada = entrada.id\n"
                        + "inner join evento ON evento.id = asociacioneventoentradasector.refevento\n"
                        + "where evento.id="+idEvento+"";
                // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idCompra = Integer.parseInt(resultado.getString("id"));
                    int numeroEntradas = resultado.getInt("numeroentradas");
                    Date fecha = resultado.getDate("fechacompra");
                    int precioTotal = resultado.getInt("preciototal");
                    int refEvento = resultado.getInt("refevento");
                    String nombreEvento = resultado.getString("nombre");
                    String nombreSector = resultado.getString("refsector");
                    ArrayList<Entrada> lista = obtenerListaEntradas(miConexion, idCompra);
                    Compra compra = new Compra(idCompra, numeroEntradas, fecha, precioTotal, refEvento, nombreEvento, nombreSector, idCompra, lista);
                    compras.add(compra);

                }
                resultado.close();
                st.close();
                //Collections.sort(compras);
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
            //System.out.println("capacidad maxima del evento:"+capacidadMaximaEvento);
            //System.out.println("numero de entradas vendidas:"+numeroEntradasVendidas);
            int entradas = numeroEntradasVendidas + cantidadDeEntradas;
            if (entradas <= capacidadMaximaEvento) {
                try {
                    java.util.Date fecha = new Date();
                    int precioCompra = obtenerPrecioEventoPorSector(miConexion, idEvento, nombreSector, idPropiedad);
                    //System.out.println("precio compra:"+precioCompra);
                    int precioTotalCompra = precioCompra * cantidadDeEntradas;
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into compra values(default," + cantidadDeEntradas + ",'" + fecha + "'," + precioTotalCompra + ")RETURNING id";
                    //      System.out.println(sql);
                    ResultSet resultado = st.executeQuery(sql);
                    while (resultado.next()) {
                        int idCompra = Integer.parseInt(resultado.getString("id"));
                        // obtengo el identificador de la entrada asociada a un evento,sector y propiedad.
                        int idEntrada = obtenerIdentificarEntrada(miConexion, idEvento, nombreSector, idPropiedad);
                        //relaciono la compra con el cliente
                        boolean relacionarCompraCliente = relacionCompraCliente(miConexion, rutCliente, idCompra);
                        // genero la instancia de entrada
                        generarInstanciaEntrada(miConexion, idCompra, idEntrada, cantidadDeEntradas);
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
                borrarInstanciasEntradas(miConexion, idCompra);
                borrarRealizaCompra(miConexion, idCompra);
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from compra where compra.id=" + idCompra + "";
                //System.out.println(sql);
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                return false;
            }

        }
        return false;
    }

    private int obtenerPrecioEventoPorSector(Connection conexion, int idEvento, String nombreSector, int idPropiedad) {

        Connection miConexion = conexion;
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select asociacioneventoentradasector.precio from asociacioneventoentradasector \n"
                        + "where asociacioneventoentradasector.refevento=" + idEvento + " and asociacioneventoentradasector.refsector='" + nombreSector + "'\n"
                        + "and asociacioneventoentradasector.refpropiedad=" + idPropiedad + "";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {

                    int precio = resultado.getInt("precio");
                    //System.out.println("precio:" + precio);
                    resultado.close();
                    st.close();
                    return precio;
                }

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return 0;
            }

        }
        return 0;

    }

    private boolean relacionCompraCliente(Connection conexion, String rutCliente, int idCompra) {

        Connection miConexion = conexion;
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

                String sql = "select count(instanciaentrada.id)as numeroentrada\n"
                        + "from instanciaentrada\n"
                        + "inner join entrada on instanciaentrada.refentrada = entrada.id\n"
                        + "inner join asociacioneventoentradasector on entrada.id = asociacioneventoentradasector.refentrada\n"
                        + "inner join evento on asociacioneventoentradasector.refevento = evento.id\n"
                        + "where evento.id=" + idEvento + "";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int numeroEntradas = resultado.getInt("numeroentrada");
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

    private int obtenerIdentificarEntrada(Connection conexion, int idEvento, String nombreSector, int idPropiedad) {
        Connection miConexion = conexion;
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select asociacioneventoentradasector.refentrada from asociacioneventoentradasector \n"
                        + "where asociacioneventoentradasector.refevento=" + idEvento + " and asociacioneventoentradasector.refsector='" + nombreSector + "'\n"
                        + "and asociacioneventoentradasector.refpropiedad=" + idPropiedad + "";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int numeroEntrada = resultado.getInt("refentrada");
                    return numeroEntrada;
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

    private boolean generarInstanciaEntrada(Connection miConexion, int idCompra, int idEntrada, int cantidad) {

        if (miConexion != null) {
            for (int i = 0; i < cantidad; i++) {
                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into instanciaentrada values(default," + idCompra + "," + idEntrada + ")";
                    st.executeUpdate(sql);
                    st.close();

                } catch (SQLException e) {
                    return false;
                }
            }
            return true;

        }
        return false;
    }

    private boolean borrarInstanciasEntradas(Connection miConexion, int idCompra) {
        if (miConexion != null) {

            try {
                borrarRealizaCompra(miConexion, idCompra);
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from instanciaentrada where instanciaentrada.refcompra=" + idCompra + "";
                //System.out.println(sql);
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                return false;
            }

        }
        return false;
    }

    private boolean borrarRealizaCompra(Connection miConexion, int idCompra) {
        if (miConexion != null) {

            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from realizacompra where realizacompra.refcompra=" + idCompra + "";
                //System.out.println(sql);
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                return false;
            }

        }
        return false;
    }

    private ArrayList<Entrada> obtenerListaEntradas(Connection miConexion, int idCompra) {

        ArrayList<Entrada> entradas = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select entrada.id, asociacioneventoentradasector.precio\n"
                        + "from instanciaentrada\n"
                        + "inner join entrada on instanciaentrada.refentrada=entrada.id\n"
                        + "inner join asociacioneventoentradasector on entrada.id=asociacioneventoentradasector.refentrada";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idEntrada = Integer.parseInt(resultado.getString("id"));
                    int precio = resultado.getInt("precio");
                    Entrada entrada = new Entrada(idEntrada, precio);
                    entradas.add(entrada);
                }
                resultado.close();
                st.close();
                //Collections.sort(compras);
                return entradas;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }
        }
        return null;
    }
}
