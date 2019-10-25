package ControladorBaseDeDatos;

import ModuloGestionEventos.Entrada;
import ModuloGestionVentas.Compra;
import ModuloSeguridadExterna.Guardian;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControladorBDDeVentas {

    ConexionBD conexion;
    SimpleDateFormat sdf;
    Guardian guardian;

    public ControladorBDDeVentas() {
        this.conexion = new ConexionBD();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.guardian = new Guardian();
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
     * Obtiene una lista con todas las compras realizadas por un usuario.
     *
     * @param rutUsuario: rut del usuario.
     * @return arryaList
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
     * Obtiene una lista de todas las compras realizadas por clientes de un
     * evento.
     *
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
                        + "where evento.id=" + idEvento + "";
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

    /**
     * registra una compra en la base de datos.
     *
     * @param rutCliente: rut del cliente
     * @param idEvento: identificador del evento donde se realiza el evento
     * @param nombreSector: nombre del sector
     * @param cantidadDeEntradas: numero de entradas respecto a un evento y
     * sector especifico.
     * @param idPropiedad: identificador de la propiedad donde se realizara el
     * evento.
     * @return
     */
    public boolean registrarCompra(String rutCliente, int idEvento, String nombreSector, int cantidadDeEntradas, int idPropiedad) {

        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {
            int capacidadMaximaEvento = obtenerCapacidMaximaEvento(miConexion, idEvento);
            int numeroEntradasVendidas = obtenerNumeroDeEntradasVendidasDeEvento(miConexion, idEvento);
            int entradas = numeroEntradasVendidas + cantidadDeEntradas;
            if (entradas <= capacidadMaximaEvento) {
                try {
                    java.util.Date fecha = new Date();
                    int precioCompra = obtenerPrecioEventoPorSector(miConexion, idEvento, nombreSector, idPropiedad);
                    int precioTotalCompra = precioCompra * cantidadDeEntradas;
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into compra values(default," + cantidadDeEntradas + ",'" + fecha + "'," + precioTotalCompra + ")RETURNING id";
                    System.out.println(sql);
                    ResultSet resultado = st.executeQuery(sql);
                    while (resultado.next()) {
                        int idCompra = Integer.parseInt(resultado.getString("id"));
                        int idEntrada = obtenerIdentificadorDeEntrada(miConexion, idEvento, nombreSector, idPropiedad);
                        boolean relacionarCompraCliente = relacionCompraCliente(miConexion, rutCliente, idCompra);
                        // genero la instancia de entrada
                        generarInstanciaEntrada(miConexion, idCompra, idEntrada, cantidadDeEntradas);
                        
                        ArrayList<String> listaEntrada = obtenerListaEntradasCorreo(miConexion, idCompra);
                        
                        String fechaComoCadena = this.sdf.format(fecha);
                        String tarjeta = obtenerTarjetaCreditoCliente(miConexion, rutCliente);
                        String nombreEvento = obtenerNombreEvento(miConexion, idEvento);
                        String correo = obtenerCorreoCliente(miConexion, rutCliente);
                        this.guardian.correoClienteCompraDeEntradas(correo, idCompra, idEntrada, fechaComoCadena, precioCompra, listaEntrada, tarjeta, nombreEvento);
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

    private ArrayList<String> obtenerListaEntradasCorreo(Connection miConexion, int idCompra) {

        ArrayList<String> entradas = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select instanciaentrada.id,asociacioneventoentradasector.precio,\n"
                        + "asociacioneventoentradasector.refSector\n"
                        + "from compra\n"
                        + "inner join instanciaentrada instanciaentrada ON instanciaentrada.refcompra = compra.id \n"
                        + "inner join entrada on instanciaentrada.refentrada=entrada.id\n"
                        + "inner join asociacioneventoentradasector on entrada.id = asociacioneventoentradasector.refentrada\n"
                        + "where compra.id = "+idCompra+"";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idEntrada = Integer.parseInt(resultado.getString("id"));
                    int precio = resultado.getInt("precio");
                    String nombreSector=resultado.getString("refsector");
                    String cadena=idEntrada+";"+precio+";"+nombreSector;
                    entradas.add(cadena);

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

    /**
     * Cancela la compra realizada por un cliente
     *
     * @param idCompra: identificador de una compra.
     * @return true si se logra eliminar el registro de compra, false de lo
     * contrario.
     */
    public boolean cancelarCompra(int idCompra) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {

            try {
                //generar reembolso de la compra.
                generarReembolsoCompra(miConexion, idCompra);

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

    /**
     * Obtiene el precio que tiene un evento por sector.
     *
     * @param conexion: conexion con la base de datos.
     * @param idEvento: identificador de un evento.
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de la propiedad.
     * @return
     */
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

    /**
     * Metodo privado. Relaciona las tablas de compra y cliente.
     *
     * @param conexion: conexion con la base de datos.
     * @param rutCliente: rut del cliente.
     * @param idCompra: identificador de una compra.
     * @return true si realiza la relacion correctamente, false de lo contrario.
     */
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

    /**
     * Metodo privado. Obtiene la capcidad maxima de un evento.
     *
     * @param conexion: conexion con la base de datos.
     * @param idEvento: identificador del evento.
     * @return capacidad maxima del evento de lo contrario -1.
     */
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

                return -1;
            }

        }
        return -1;
    }

    /**
     * Metodo privado. obtiene el numero de entradas vendidas de un evento.
     *
     * @param conexion: conexion con la base de datos.
     * @param idEvento: identidicador del evento.
     * @return numero de entras vendidas.
     */
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
                return -1;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return -1;
            }

        }
        return -1;
    }

    /**
     * Metodo privado. Obtiene el identidicador de una entrada.
     *
     * @param conexion
     * @param idEvento: identificador del evento.
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de una propiedad.
     * @return
     */
    private int obtenerIdentificadorDeEntrada(Connection conexion, int idEvento, String nombreSector, int idPropiedad) {
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

    /**
     * Metodo privado. Genera instancias de entras.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idCompra: identificador de una compra.
     * @param idEntrada: identificado de una entrada,
     * @param cantidad: numero de instancias de entradas que se desean generar.
     * @return true si se generan las instancias, false de lo contrario.
     */
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

    /**
     * Borra el registro de instancias de entradas de la base de datos.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idCompra: identificador de una compra.
     * @return true si se borran los registros de instancias, false de lo
     * contrario.
     */
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

    /**
     * Meotodo privado. borra el registro que relaciona una compra con un
     * cliente.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idCompra: identificador de una compra.
     * @return true si borra el registro, false de lo contrario.
     */
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

    /**
     * Metodo privado. Obtiene una lista de entradas referente a una compra.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idCompra: identificador de una compra.
     * @return lista de entradas o null en caso de que no existan entradas
     * referentes a esa compra.
     */
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

    /**
     * Obtiene el numero de entradas que quedan por vender de un evento.
     *
     * @param idEvento: identificador de un evento.
     * @return numero de entradas que quedan por vender.
     */
    public int obtenerEntradasQueQuedanPorEvento(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        int entradas = 0;
        if (miConexion != null)// si hay conexion.
        {
            int capacidaEvento = obtenerCapacidMaximaEvento(miConexion, idEvento);
            int entradasVendidas = obtenerNumeroDeEntradasVendidasDeEvento(miConexion, idEvento);
            entradas = capacidaEvento - entradasVendidas;
            try {
                this.conexion.cerrarBaseDeDatos(miConexion);
            } catch (SQLException ex) {
                //Logger.getLogger(ControladorBDDeVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
            return entradas;

        }
        return entradas;

    }

    /**
     * Metodo privado. Obtiene la tarjeta de credito de un cliente.
     *
     * @param miConexion: conexion con la base de datos.
     * @param rutCliente: rut del cliente.
     * @return tarjeta de credito del cliente, o null si no encuentra registrado
     * en la base de datos al cliente.
     */
    private String obtenerTarjetaCreditoCliente(Connection miConexion, String rutCliente) {
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select* from cliente where cliente.rut='" + rutCliente + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String tarjeta = resultado.getString("tarjetacredito");
                    resultado.close();
                    st.close();
                    return tarjeta;

                }
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }

    /**
     * Obtiene el nombre de un evento.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idEvento: identificador de un evento
     * @return nombre del evento.
     */
    private String obtenerNombreEvento(Connection miConexion, int idEvento) {
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select* from evento where evento.id=" + idEvento + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    resultado.close();
                    st.close();
                    return nombre;

                }
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }

    /**
     * Metodo privado Generar Reembolso de una compra.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idCompra : identificador de una compra.
     */
    private void generarReembolsoCompra(Connection miConexion, int idCompra) {

        if (miConexion != null) {

            try {
                java.util.Date fechaActual = new Date();
                int plazoEvento = obtenerPlazoMaximoDevolucionEntradaEvento(miConexion, idCompra);
                Date fechaCompra = obtenerFechaCompra(miConexion, idCompra);

                Date fechaproyectada = proyectarFecha(miConexion, fechaCompra, plazoEvento);
                boolean comparacion = fechaActual.before(fechaproyectada);
                if (comparacion == true) {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "select DISTINCT\n"
                            + "cliente.nombrecompleto,cliente.correo,compra.id,compra.numeroentradas,compra.fechacompra,compra.preciototal,cliente.tarjetacredito,\n"
                            + "evento.nombre as nombreevento\n"
                            + "from cliente\n"
                            + "inner join realizacompra ON realizacompra.refcliente = cliente.rut\n"
                            + "inner join compra on compra.id = realizacompra.refcompra\n"
                            + "inner join instanciaentrada ON instanciaentrada.refcompra = compra.id\n"
                            + "inner join entrada on instanciaentrada.refentrada=entrada.id\n"
                            + "inner join asociacioneventoentradasector on entrada.id=asociacioneventoentradasector.refentrada\n"
                            + "inner join evento on asociacioneventoentradasector.refevento = evento.id\n"
                            + "where compra.id=" + idCompra + " ";
                    ResultSet resultado = st.executeQuery(sql);
                    while (resultado.next()) {
                        // obtengo la informacion del cliente.
                        String nombreCliente = resultado.getString("nombrecompleto");
                        int idComp = resultado.getInt("idcompra");
                        String correo = resultado.getString("correo");
                        int numeroEntradas = resultado.getInt("numeroentradas");
                        Date fechaCom = resultado.getDate("fechacompra");
                        int precioT = resultado.getInt("preciototal");
                        String tarjeta = resultado.getString("tarjetacredito");
                        String nombreEvento = resultado.getString("nombreevento");
                        String fechaComoCadena = this.sdf.format(fechaCom);

                        this.guardian.correoClienteReembolsoDeCompra(correo, idComp, numeroEntradas, fechaComoCadena, precioT, tarjeta, nombreEvento);
                    }
                    resultado.close();
                    st.close();
                }

                //return true;
            } catch (SQLException e) {
                //return false;
            }

        }
        //return false;
    }

    /**
     * Obtiene el plazo de devolucion de entradas de un evento.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idCompra: identificador de una compra.
     * @return numero de dias de plazo de devolucion.
     */
    public int obtenerPlazoMaximoDevolucionEntradaEvento(Connection miConexion, int idCompra) {
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select DISTINCT evento.plazodevolucionentradas\n"
                        + "from compra\n"
                        + "inner join instanciaentrada ON instanciaentrada.refcompra = compra.id\n"
                        + "inner join entrada on entrada.id = instanciaentrada.refentrada\n"
                        + "inner join asociacioneventoentradasector on entrada.id=asociacioneventoentradasector.refentrada\n"
                        + "inner join evento on asociacioneventoentradasector.refevento = evento.id\n"
                        + "where compra.id=" + idCompra + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int plazo = resultado.getInt("plazodevolucionentradas");
                    resultado.close();
                    st.close();
                    return plazo;

                }
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return -1;
            }

        }
        return -1;
    }

    /**
     * Metodo privado. apartir de una fecha, le suma una cantidad de dias.
     *
     * @param miConexion: conexion con la base de datos.
     * @param fechaActual: fecha.
     * @param dias: numero de dias los cuales quiere que se le sume a la fecha.
     * @return nueva fecha con los dias sumados.
     */
    private Date proyectarFecha(Connection miConexion, Date fechaActual, int dias) {
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "SELECT CAST('" + fechaActual + "' AS DATE) + CAST('" + dias + " days' AS INTERVAL) as fecha;";
                // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Date fecha = resultado.getDate("fecha");
                    return fecha;
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                //return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                //return null;
            }
        }
        return null;
    }

    /**
     * Metodo privado Obtiene la fecha de compra de una compra en especifica.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idCompra: identificador de una compra.
     * @return fecha de compra.
     */
    private Date obtenerFechaCompra(Connection miConexion, int idCompra) {
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select compra.fechacompra from compra where compra.id=" + idCompra + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Date fecha = resultado.getDate("fechacompra");
                    resultado.close();
                    st.close();
                    return fecha;

                }
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }

    private String obtenerCorreoCliente(Connection miConexion, String rutCliente) {
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select cliente.correo\n"
                        + "from cliente\n"
                        + "where cliente.rut='" + rutCliente + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String correo = resultado.getString("correo");
                    resultado.close();
                    st.close();
                    return correo;

                }
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }

}
