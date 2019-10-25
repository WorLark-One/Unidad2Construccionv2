package ControladorBaseDeDatos;

import ModuloGestionEventos.Entrada;
import ModuloGestionEventos.Evento;
import ModuloGestionPropiedades.Propiedad;
import ModuloGestionVentas.Compra;
import ModuloSeguridadExterna.Guardian;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ControladorBDDeEventos {

    ConexionBD conexion;
    ControladorBDDePropiedades propiedades;
    Guardian guardian;
    SimpleDateFormat sdf;

    /**
     * Default constructor
     */
    public ControladorBDDeEventos() {
        this.conexion = new ConexionBD();
        this.propiedades = new ControladorBDDePropiedades();
        this.guardian = new Guardian();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
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
     * Registra un Evento en la base de datos, ademas de establer un enlace
     * entre una propiedad y un evento.
     *
     * @param nombre: nombre del evento
     * @param fechaDeInicio: fecha de inicio del evento
     * @param fechaDeTermino: fecha de termino del evento.
     * @param capacidad: capacidad del evento.
     * @param descripcion: descripcion del evento.
     * @param diasMaximoDevolucion: dias maximo de devolucion del evento.
     * @param publicado: indica si el evento esta publicado o no.
     * @param idPropiedad: identificador de una propiedad
     * @param rutOrganizador: referencia al organizador al cual pertenece el
     * evento.
     * @return identificador del evento o cero en caso de que surga un error al
     * crear un evento
     *
     * PRECIO DE ENTRADAS POR SECTOR.
     */
    public int crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad, String rutOrganizador) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean nombreRepetido = validarNombreEvento(rutOrganizador, nombre);
            boolean validarFecha = validarFechaDeArriendo(miConexion, fechaDeInicio, idPropiedad);
            if (nombreRepetido == false && validarFecha == true) {
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into evento values(DEFAULT,'" + nombre + "','" + descripcion + "','" + fechaDeInicio + "','" + fechaDeTermino + "',\n"
                            + " " + capacidad + ", " + diasMaximoDevolucion + " ," + publicado + ",'" + rutOrganizador + "')"
                            + " RETURNING id";
                    //System.out.println(sql);
                    ResultSet resultado = st.executeQuery(sql);
                    while (resultado.next()) {
                        int idEvento = Integer.parseInt(resultado.getString("id"));
                        boolean Celebra = tablaCelebra(miConexion, idEvento, idPropiedad);
                        if (Celebra == false) {
                            eliminarEvento(idEvento);
                            return 0;
                        }
                        return idEvento;
                    }
                    st.close();

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirEvento" + e);

                    return 0;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //System.out.println("error al cerrar la base de datos.");
                    }

                }
            }
            return -1;
        }
        return 0;
    }

    /**
     * relaciona un evento con una propiedad, nos sirve para insicarnos en que
     * propiedad se realizaran los eventos
     *
     * @param conexion: conexion de la base de datos.
     * @param idEvento: identificador del evento.
     * @param idPropiedad: identificador de la propiedad.
     * @return true si se realza la asociacion entre evento y propiedad, false
     * de lo contrario.
     */
    private boolean tablaCelebra(Connection conexion, int idEvento, int idPropiedad) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into celebra values(" + idEvento + "," + idPropiedad + ")";
                //              System.out.println(sql);
                st.executeUpdate(sql);
                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                //            System.out.println("error tabla celebra");

                return false;
            }
        }

        return false;

    }

    /**
     * Verificate si el nombre de un evento esta registrada en la lista de
     * eventos publicados del organizador.
     *
     * @param rut
     * @param nombrePropiedad
     * @return
     */
    private boolean validarNombreEvento(String rut, String nombreEvento) {
        ArrayList<Evento> eventos = obtenerInformacionDeTodosLosEventosDeUnOrganizador(rut);
        if (eventos != null) {
            //System.out.println("hay eventos:" + eventos.size());
            for (int i = 0; i < eventos.size(); i++) {
                Evento evento = eventos.get(i);
                //System.out.println("nombre evento:" + evento.getNombre() + " nombre a compara:" + nombreEvento);
                if (evento.getNombre().equalsIgnoreCase(nombreEvento) == true) {
                    //System.out.println("entre en true");
                    return true;
                }

            }
        }

        return false;

    }

    /**
     * Puede modificar solo si publicado es false
     *
     * @param idEvento: identificador de un evento
     * @param nuevoNombre: nuevo nombre que se le asignara al evento
     * @param nuevaDescripcion: nueva descripcion que se le asignara al evento
     * @param nuevaFechaDeInicio: nueva fecha de inicio que se le asignara al
     * evento
     * @param nuevaFechaDeTermino: nueva fecha de termino que se le asignara al
     * evento.
     * @param nuevaCapacidad: nueva capacidad que se le asignara la proyecto.
     * @param nuevosDiasMaximoDevolucion: nuevo dias maximo de devolucion de
     * entrada que se le asignara al proyecto.
     * @param nuevoPublicado: nuevo estado que se le asiganra al proyecto, si
     * esta publicado o no.
     * @return true si se modifico el evento, false de lo contrario
     */
    public boolean modificarEvento(int idEvento, String nuevoNombre, String nuevaDescripcion, Date nuevaFechaDeInicio, Date nuevaFechaDeTermino, int nuevaCapacidad, int nuevosDiasMaximoDevolucion, boolean nuevoPublicado) {
        this.conexion.crearConexion();
        //       boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean EstadoEvento = obtenerPublicadoDeEvento(miConexion, idEvento);
            if (EstadoEvento == false) {
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = " UPDATE evento set nombre='" + nuevoNombre + "', descripcion='" + nuevaDescripcion + "',fechainicio='" + nuevaFechaDeInicio + "',fechatermino='" + nuevaFechaDeTermino + "',\n"
                            + "capacidad=" + nuevaCapacidad + ",plazodevolucionentradas=" + nuevosDiasMaximoDevolucion + ",publicado='" + nuevoPublicado + "' where evento.id=" + idEvento + " ";
                    //              System.out.println(sql);
                    st.executeUpdate(sql);
                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //System.out.println("Error al cerrar la conexion de la base de datos.");
                    }

                }

            }
            return false;

        }
        return false;
    }

    /**
     * modifica la asociacion entre un evento y una propiedad, en otras
     * palabras, puedes cambiar el lugar donde realizaras el evento.
     *
     * @param idEvento: identificador del evento que se desea modificar.
     * @param idPropiedad: identificador de la propiedad que se desea modificar.
     * @param nuevoIdPropiedad: nuevo identificador de la propiedad que se desea
     * modificar.
     * @return true si se modifica la tabla celebra, false de lo contrario
     */
    public boolean modificarTablaCelebra(int idEvento, int idPropiedad, int nuevoIdPropiedad) {
        this.conexion.crearConexion();
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean EstadoEvento = obtenerPublicadoDeEvento(miConexion, idEvento);
            if (EstadoEvento == false) {
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = " UPDATE celebra set  refpropiedad=" + nuevoIdPropiedad + " where  celebra.refevento=" + idEvento + "  and celebra.refpropiedad=" + idPropiedad + " ";
                    //            System.out.println(sql);
                    st.executeUpdate(sql);
                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //System.out.println("Error al cerrar la conexion de la base de datos.");
                    }

                }

            }
            return false;

        }
        return false;

    }

    /**
     * Obtiene el atributo publicado de un evento.
     *
     * @param conexion: conexion con la base de datos
     * @param idEvento: identificador de un evento
     * @return true si el evento esta publicado,false de lo contrario.
     */
    private boolean obtenerPublicadoDeEvento(Connection conexion, int idEvento) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * from evento where evento.id=" + idEvento + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    boolean publicado = resultado.getBoolean("publicado");
                    return publicado;
                }
                st.close();

            } catch (SQLException e) {
                //System.out.println("error conexion");

                return false;
            }

        }
        return false;
    }

    /**
     * Elimina un evento de la base de datos, ademas de borrar la asociacion de
     * una propiedad con unn evento.
     *
     * @param idEvento
     * @param rutOrganizador
     * @return
     */
    public boolean eliminarEvento(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {
            //verificar si un evento es finalizado.
            boolean eliminar = VerificarEliminarEvento(miConexion, idEvento);
            if (eliminar == true) {
                try {
                    ArrayList<Integer> listaCompra = obtenerIdentificadoresDeCompra(miConexion, idEvento);
                    generarRembolsoDeCompra(miConexion, listaCompra);
                    eliminarAsociacionEventoPropiedad(miConexion, idEvento);
                    borrarAsociacionEventoSectorEntrada(miConexion, idEvento);
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "delete from evento where evento.id=" + idEvento + "";
                    st.executeUpdate(sql);
                    st.close();
                    return true;
                } catch (SQLException e) {
                    System.out.println("ERROR  eliminar evento (desde la tabla de usuario)" + e);
                    return false;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //        System.out.println("No se cerro satisfactoriamente la base de datos.");
                    }
                }
            }

        }
        System.out.println("no hay conexion");
        return false;
    }

    private boolean borrarAsociacionEventoSectorEntrada(Connection miConexion, int idEvento) {
        if (miConexion != null) {

            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from asociacioneventoentradasector where asociacioneventoentradasector.refevento=" + idEvento + "";
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
     * obtiene una lista de todos los eventos publicados y no publicados de un
     * organizador.
     *
     * @param rutOrganizador: rut del organizador
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeTodosLosEventosDeUnOrganizador(String rutOrganizador) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select evento.id, evento.nombre,evento.descripcion,evento.fechainicio,evento.fechatermino,evento.capacidad,\n"
                        + "evento.plazodevolucionentradas,evento.publicado\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where evento.reforganizador='" + rutOrganizador + "' \n"
                        + "and propiedad.activa='true'";
                // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

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
     * No esta listo
     *
     * @param fechaInicioa
     * @param fechaTermino
     * @return
     */
    public ArrayList<Evento> obtenerEventoPublicados(Date fechaInicio, Date fechaTermino) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = " select evento.id, evento.nombre,evento.descripcion,evento.fechainicio,evento.fechatermino,evento.capacidad,\n"
                        + "evento.plazodevolucionentradas,evento.publicado\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where evento.fechainicio >= '" + fechaInicio + "' and \n"
                        + "evento.fechainicio <= '" + fechaTermino + "' and  propiedad.activa='true' and evento.publicado='true'";
                // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaIni = resultado.getDate("fechainicio");
                    Date fechaTer = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaIni, fechaTer, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

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
     * Obtienen el identificador de una propiedad.
     *
     * @param conexion: conexion con la base de datos
     * @param idEvento: identificador de un evento.
     * @return identificador de la propiedad.
     */
    public int obtenerIdDePropiedadDondeSeRealizaEvento(Connection conexion, int idEvento) {
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from  celebra where celebra.refevento=" + idEvento + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int id = Integer.parseInt(resultado.getString("refpropiedad"));
                    resultado.close();
                    st.close();
                    return id;

                }
            } catch (SQLException e) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * obtiene una lista de todos los eventos publicados de un organizador
     *
     * @param rutOrganizador: rut del organizador
     * @return lista de eventos.
     */
    public ArrayList<Evento> obtenerInformacionDeEventosPublicadosDeUnOrganizador(String rutOrganizador) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();
                java.util.Date fecha = new Date();
                String sql = "select evento.id, evento.nombre,evento.descripcion,evento.fechainicio,evento.fechatermino,evento.capacidad,\n"
                        + "evento.plazodevolucionentradas,evento.publicado\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where evento.reforganizador='" + rutOrganizador + "' and evento.fechatermino >= '" + fecha + "'::date\n"
                        + "and propiedad.activa='true' and evento.publicado='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return null;
    }

    /**
     * obtiene una lista con todos los eventos no publicados de un organizador
     *
     * @param rutOrganizador: rut del organizador
     * @return lista de sectores
     */
    public ArrayList<Evento> obtenerInformacionDeEventosNoPublicadosDeUnOrganizador(String rutOrganizador) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();
                java.util.Date fecha = new Date();

                String sql = "select evento.id, evento.nombre,evento.descripcion,evento.fechainicio,evento.fechatermino,evento.capacidad,\n"
                        + "evento.plazodevolucionentradas,evento.publicado\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where evento.reforganizador='" + rutOrganizador + "' and evento.fechatermino >= '" + fecha + "'::date\n"
                        + "and propiedad.activa='true' and evento.publicado='false'";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    //  System.out.println("nombre evento:" + nombre + " rut del organizador:" + rutOrganizador);
                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);
                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return null;
    }

    /**
     * obtiene todos los eventos finalizados de un organizador.
     *
     * @param rutOrganizador: rut del organizador.
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosDeUnOrganizador(String rutOrganizador) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        java.util.Date fecha = new Date();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select evento.id, evento.nombre,evento.descripcion,evento.fechainicio,evento.fechatermino,evento.capacidad,\n"
                        + "evento.plazodevolucionentradas,evento.publicado\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where evento.reforganizador='" + rutOrganizador + "' and evento.fechatermino <= '" + fecha + "'::date\n"
                        + "and propiedad.activa='true'";

                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");

                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return null;
    }

    /**
     * modifico a true el parametro publicado
     *
     * @param idEvento
     * @return true si modifica el parametro publicado, false de lo contrario
     */
    public boolean aceptarSolicitudPropietario(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = " UPDATE evento set publicado=true where evento.id=" + idEvento + "";
                System.out.println(sql);
                //se envia correo
                CorreoArriendoPropiedad(miConexion, idEvento);
                //System.out.println(sql);
                st.executeUpdate(sql);
                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                return false;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //System.out.println("Error al cerrar la conexion de la base de datos.");
                }
            }
        }
        return false;
    }

    /**
     * rechaza la solicitud de un organizador para celebrar un evento en sus
     * propiedad.
     *
     * @param idEvento: identificador de un evento.
     * @return
     */
    public boolean rechazarSolicitudPropietario(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select DISTINCT  propietario.correo as correopropietario, evento.id as idevento,\n"
                        + "evento.nombre as nombreevento, evento.fechainicio,evento.fechatermino,\n"
                        + "organizador.nombrecompleto as nombreorganizador,organizador.correo as correoorganizador, organizador.tarjetacredito,\n"
                        + "propiedad.id as identificadorpropiedad, propiedad.nombre as nombrepropiedad\n"
                        + "from evento\n"
                        + "inner join asociacioneventoentradasector  on evento.id=asociacioneventoentradasector.refevento\n"
                        + "inner join propiedad on asociacioneventoentradasector.refpropiedad=propiedad.id\n"
                        + "inner join propietario on propiedad.refpropietario=propietario.rut\n"
                        + "inner join organizador on evento.reforganizador = organizador.rut\n"
                        + "where evento.id="+idEvento+" and propiedad.activa='true'";
                System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String correoPropietario = resultado.getString("correopropietario");
                    String correoOrganizador = resultado.getString("correoorganizador");
                    String idEven = resultado.getString("idevento");
                    String nombreEvento = resultado.getString("nombreevento");
                    Date fechaIni = resultado.getDate("fechainicio");
                    Date fechaTer = resultado.getDate("fechatermino");
                    String nombreOrganizador = resultado.getString("nombreorganizador");
                    String tarjetaOrganizador = resultado.getString("tarjetacredito");
                    int idPropiedad = Integer.parseInt(resultado.getString("identificadorpropiedad"));
                    String nombrePropiedad = resultado.getString("nombrepropiedad");

                    String fechaInic = this.sdf.format(fechaIni);
                    String fechaTerm = this.sdf.format(fechaTer);
                    // correo de rechazo de evento para el propietario
                    this.guardian.correoPropietarioCancelacionEvento(correoPropietario, idEvento, nombreEvento, fechaInic, fechaTerm);
                    //correo de rechazo de evento para organizador.
                    this.guardian.correoOrganizadorEventoRechazado(correoOrganizador, idEvento, nombreEvento, fechaInic, fechaTerm, tarjetaOrganizador, idPropiedad, nombrePropiedad);
                    eliminarEvento(idEvento);
                    return true;
                }
                

            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION: rechazarSolicitudPropietario()");
                return false;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return false;
    }

    private ArrayList<Entrada> obtenerListaEntradasDeUnaCompra(Connection miConexion, int idCompra) {

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
     * borra la relacion entre un evento y una propiedad.
     *
     * @param conexion: conexion a la base de datos.
     * @param idEvento: identificador de evento.
     * @param idPropiedad: identificador de la propiedad.
     * @return true si se elimina la asociacion, false de lo contrario.
     */
    private boolean eliminarAsociacionEventoPropiedad(Connection conexion, int idEvento) {
        Connection miConexion = conexion;

        if (miConexion != null) {

            try {
                ArrayList<Integer> entradas = obtenerListaEntradas(miConexion, idEvento);
                eliminarEntrada(conexion, entradas);
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from celebra where celebra.refevento=" + idEvento + "";
                //System.out.println(sql);
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: eliminarAsociacionEventoPropiedad" + e);
                return false;
            }

        }
        return false;

    }

    private boolean eliminarEntrada(Connection conexion, ArrayList<Integer> entradas) {
        Connection miConexion = conexion;

        if (miConexion != null) {
            java.sql.Statement st = null;
            if (entradas != null) {
                for (int i = 0; i < entradas.size(); i++) {
                    Integer idEntrada = entradas.get(i);
                    try {
                        st = miConexion.createStatement();
                        String sql = "delete from entrada where entrada.id=" + idEntrada + "";
                        //System.out.println(sql);
                        st.executeUpdate(sql);
                        st.close();
                    } catch (SQLException e) {
                        //System.out.println("ERROR DE CONEXION: eliminarAsociacionEventoPropiedad" + e);
                        return false;
                    }
                }
            }
            return true;

        }
        return false;

    }

    private ArrayList<Integer> obtenerListaEntradas(Connection miConexion, int idEvento) {
        ArrayList<Integer> entradas = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select *from asociacioneventoentradasector as t1 where t1.refevento=" + idEvento + "";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEntrada = Integer.parseInt(resultado.getString("refentrada"));
                    entradas.add(idEntrada);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return entradas;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }

    /**
     * Obtiene una lista de los eventos los cuales le solicitan arriendo.
     *
     * @param rutPropietario
     * @return lista de eventos, o null en caso de que no existan eventos
     * asociados al propietario
     */
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventosPropietario(String rutPropietario) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select evento.id,evento.nombre, evento.descripcion, evento.fechainicio, evento.fechatermino,evento.capacidad, evento.plazodevolucionentradas, evento.publicado,evento.reforganizador \n"
                        + "from propietario\n"
                        + "inner join propiedad on propietario.rut = propiedad.refpropietario\n"
                        + "inner join celebra on propiedad.id= celebra.refpropiedad\n"
                        + "inner join evento on evento.id= celebra.refevento\n"
                        + "where propietario.rut='" + rutPropietario + "' and evento.publicado='false'"
                        + "and propiedad.activa='true'";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    //System.out.println("ide evento:" + idEvento);
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");

                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);
                    // System.out.println("nombre del evento:" + nombre + " id propiedad: " + idPropiedad);
                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return null;
    }

    /**
     * Obtiene una lista de los eventos activos asociados a su propiedad.
     *
     * @param rutPropietario: rut del propietario
     * @return lista de eventos, o null en caso de que no existan eventos
     * asociados al propietario
     */
    public ArrayList<Evento> obtenerInformacionDeEventosActualesPropietario(String rutPropietario) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select evento.id,evento.nombre, evento.descripcion, evento.fechainicio, evento.fechatermino,evento.capacidad, evento.plazodevolucionentradas, evento.publicado,evento.reforganizador \n"
                        + "from propietario\n"
                        + "inner join propiedad on propietario.rut = propiedad.refpropietario\n"
                        + "inner join celebra on propiedad.id= celebra.refpropiedad\n"
                        + "inner join evento on evento.id= celebra.refevento\n"
                        + "where propietario.rut='" + rutPropietario + "' and evento.publicado=true"
                        + "and propiedad.activa='true'";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);
                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return null;
    }

    /**
     * Obtien una lista de todos los eventos que ya finalizaron asociados a un
     * propietario.
     *
     * @param rutPropietario
     * @return lista de eventos, o null en caso de que no existan eventos
     * asociados al propietario
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosPropietario(String rutPropietario) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        java.util.Date fecha = new Date();
        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select evento.id,evento.nombre, evento.descripcion, evento.fechainicio, evento.fechatermino,evento.capacidad, evento.plazodevolucionentradas, evento.publicado,evento.reforganizador \n"
                        + "from propietario\n"
                        + "inner join propiedad on propietario.rut = propiedad.refpropietario\n"
                        + "inner join celebra on propiedad.id= celebra.refpropiedad\n"
                        + "inner join evento on evento.id= celebra.refevento\n"
                        + "where propietario.rut='" + rutPropietario + "' and evento.fechatermino <= '" + fecha + "'::date";
                // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    int ipPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);
                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(ipPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }
        }
        return null;
    }

    /**
     * Obtiene una lista de todos los propietarios registrados en la base de
     * datos con sus respectivas propiedades asiciadas..
     *
     * @return lista de propietario, o null en caso de que no existan.
     */
    public ArrayList<Propiedad> obtenerListaDePropiedades() {

        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        ArrayList<Propiedad> listaPropiedades = new ArrayList<>();
        ArrayList<Propiedad> aux = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from propietario ";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String rutCliente = resultado.getString("rut");
                    // System.out.println("rut propietario: " + rutCliente);

                    aux = this.propiedades.obtenerInformacionDePropiedades(rutCliente);
                    if (aux != null) {
                        for (int i = 0; i < aux.size(); i++) {
                            Propiedad propiedad = aux.get(i);
                            listaPropiedades.add(propiedad);
                        }
                    }
                }
                resultado.close();
                st.close();
                Collections.sort(listaPropiedades);
                return listaPropiedades;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;

    }

    private int crearEntrada(Connection conexion) {
        this.conexion.crearConexion();
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into entrada values(DEFAULT)RETURNING id";// creamos la entrada de prueba.
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));

                    return idEvento;
                }
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                System.out.println("error conexion");

                return 0;
            }

        }
        return 0;
    }

    /**
     *
     * @param idEvento: identificador del evento.
     * @param precioEntrada: precio de la entrada.
     * @param nombreSector:nombre del sector al cual esta asociada la entrada.
     * @param idPropiedad: identificador de la propiedad.
     * @return
     */
    public boolean añadirPrecioEventoPorSector(int idEvento, int precio, String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                int entrada = crearEntrada(miConexion);
                String sql = "insert into asociacioneventoentradasector values(" + entrada + "," + idEvento + ",'" + nombreSector + "'," + idPropiedad + "," + precio + ")";
                //System.out.println(sql);
                st.executeQuery(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("error conexion");
                return false;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //System.out.println("error al cerrar la base de datos.");
                }
            }
        }
        return false;

    }

    /**
     * modifica el precio de una entrada
     *
     * @param idEvento: identificador de evento.
     * @param nuevoPrecioEntrada : nuevo precio que se le asignara a la entrada.
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de la propiedad
     * @return true si modifica el precio, false de lo contrario.
     */
    public boolean modificarPrecioEventoPorSector(int idEvento, int nuevoPrecioEntrada, String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean EstadoEvento = obtenerPublicadoDeEvento(miConexion, idEvento);
            if (EstadoEvento == false) {
                try {
                    //int idEntradaPrueba = crearEntradaPrueba(miConexion);
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "update asociacioneventoentradasector set precio=" + nuevoPrecioEntrada + "\n"
                            + "where asociacioneventoentradasector.refevento=" + idEvento + " and asociacioneventoentradasector.refsector='" + nombreSector + "'\n"
                            + "and asociacioneventoentradasector.refpropiedad=" + idPropiedad + "";
                    //System.out.println(sql);
                    st.executeUpdate(sql);
                    st.close();
                } catch (SQLException e) {
                    //System.out.println("error conexion");
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

    public int obtenerPrecioEventoPorSector(int idEvento, String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                //int idEntrada = crearEntradaPrueba(miConexion);
                String sql = "select asociacioneventoentradasector.precio from asociacioneventoentradasector  \n"
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
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return 0;

    }

    public ArrayList<Evento> obtenerTodosLosEventos() {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select evento.id, evento.nombre,evento.descripcion,evento.fechainicio,evento.fechatermino,evento.capacidad,\n"
                        + "evento.plazodevolucionentradas,evento.publicado\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where \n"
                        + " propiedad.activa='true'";
                // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    int ipPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);
                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(ipPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }
        }
        return null;
    }

    private void CorreoArriendoPropiedad(Connection miConexion, int idEvento) {
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select propietario.nombrecompleto, propietario.correo as correopropietario ,evento.id as idevento,evento.nombre as nombreevento,evento.fechainicio,evento.fechatermino,propietario.cuentacorriente,\n"
                        + "propiedad.id as idPropiedad, propiedad.nombre as nombrePropiedad, organizador.correo as correoorganizador,\n"
                        + "organizador.tarjetacredito\n"
                        + "from evento\n"
                        + "inner join organizador on evento.reforganizador = organizador.rut\n"
                        + "inner join celebra on celebra.refevento = evento.id\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "inner join propietario on propiedad.refpropietario = propietario.rut\n"
                        + "where evento.id=" + idEvento + ""
                        + " and propiedad.activa='true'";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {

                    //String nombrePropietario = resultado.getString("nombre");
                    int idEven = Integer.parseInt(resultado.getString("idevento"));
                    String correo = resultado.getString("correopropietario");
                    String nombreEvento = resultado.getString("nombreevento");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    String cuentaCorriente = resultado.getString("cuentacorriente");
                    int idPropiedad = Integer.parseInt(resultado.getString("idPropiedad"));
                    String nombrePropiedad = resultado.getString("nombrePropiedad");
                    String fechaIni = this.sdf.format(fechaInicio);
                    String fechaTer = this.sdf.format(fechaTermino);
                    //System.out.println("correo de propietario");
                    System.out.println("correo propierario:" + correo);

                    this.guardian.CorreoPropietarioAceptarEvento(correo, idEven, nombreEvento, fechaIni, fechaTer, cuentaCorriente, idPropiedad, nombrePropiedad);
                    // organizador.
                    String correoOrganzador = resultado.getString("correoorganizador");
                    String tarjetaOrg = resultado.getString("tarjetacredito");
                    System.out.println("correo organizador:" + correoOrganzador);
                    this.guardian.correoOrganizadorEventoAceptado(correoOrganzador, idEven, nombreEvento, fechaIni, fechaTer, tarjetaOrg, idPropiedad, nombrePropiedad);
                }
                resultado.close();
                //st.close();
                //Collections.sort(eventos);
                //return eventos;

            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION: CorreoArriendoPropiedad()" + e);
                //return null;
            }
        }
        //return null;
    }

    private boolean VerificarEliminarEvento(Connection miConexion, int idEvento) {
        ArrayList<Evento> eventos = listaEventosFinalizados();
        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            if (idEvento == evento.getIdEvento()) {
                return false;
            }

        }
        return true;

    }

    private ArrayList<Evento> listaEventosFinalizados() {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        java.util.Date fecha = new Date();

        ArrayList<Evento> eventos = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "SELECT *\n"
                        + "FROM evento\n"
                        + "WHERE evento.fechatermino <= '" + fecha + "'::date ";

                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");

                    int idPropiedad = obtenerIdDePropiedadDondeSeRealizaEvento(miConexion, idEvento);

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    miEvento.setIdPropiedad(idPropiedad);
                    eventos.add(miEvento);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return eventos;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }

    private boolean validarFechaDeArriendo(Connection miConexion, Date fechaDeInicio, int idPropiedad) {
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select evento.fechainicio,evento.fechatermino\n"
                        + "from celebra\n"
                        + "inner join evento on celebra.refevento = evento.id\n"
                        + "where celebra.refpropiedad=" + idPropiedad + " and evento.publicado='true' and\n"
                        + "evento.fechatermino>= '" + fechaDeInicio + "' ::date";
                //System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    resultado.close();
                    st.close();
                    return false;
                }
                resultado.close();
                st.close();
                return true;
                //Collections.sort(eventos);
                //return eventos;

            } catch (SQLException e) {
                System.out.println("ERROR DE CONEXION: CorreoArriendoPropiedad()" + e);
                //return null;
            }
        }
        //return null;
        return false;
    }

    private ArrayList<Integer> obtenerIdentificadoresDeCompra(Connection miConexion, int idEvento) {
        ArrayList<Integer> identificadorCompras = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select DISTINCT compra.id \n"
                        + "from asociacioneventoentradasector\n"
                        + "inner join entrada on asociacioneventoentradasector.refentrada=entrada.id\n"
                        + "inner join instanciaentrada on entrada.id = instanciaentrada.refentrada\n"
                        + "inner join compra on instanciaentrada.refcompra = compra.id\n"
                        + "where  asociacioneventoentradasector.refevento=" + idEvento + " ";

                System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {

                    int idCompra = Integer.parseInt(resultado.getString("id"));
                    identificadorCompras.add(idCompra);
                }
                resultado.close();
                st.close();
                //Collections.sort(eventos);
                return identificadorCompras;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }

    private void generarRembolsoDeCompra(Connection miConexion, ArrayList<Integer> listaCompra) {
        if (miConexion != null)// si hay conexion.
        {

            try {
                if (listaCompra != null) {
                    for (int i = 0; i < listaCompra.size(); i++) {
                        Integer idCompra = listaCompra.get(i);
                        java.sql.Statement st = miConexion.createStatement();

                        String sql = "select DISTINCT cliente.correo, compra.id as idcompra, compra.numeroentradas,compra.fechacompra,\n"
                                + "compra.preciototal,cliente.tarjetacredito,evento.nombre\n"
                                + "from compra\n"
                                + "inner join realizacompra ON realizacompra.refcompra = compra.id\n"
                                + "inner join cliente on realizacompra.refcliente = cliente.rut\n"
                                + "inner join instanciaentrada on instanciaentrada.refcompra = realizacompra.refcompra\n"
                                + "inner join entrada on instanciaentrada.refentrada=entrada.id\n"
                                + "inner join asociacioneventoentradasector ON asociacioneventoentradasector.refentrada = entrada.id\n"
                                + "inner join evento on asociacioneventoentradasector.refevento = evento.id"
                                + "where compra.id=" + idCompra + " ";

                        //System.out.println(sql);
                        ResultSet resultado = st.executeQuery(sql);
                        while (resultado.next()) {
                            String correoCliente = resultado.getString("correo");
                            int idComp = resultado.getInt("idcompra");
                            int numeroEntradas = resultado.getInt("numeroentradas");
                            Date fechaCompra = resultado.getDate("fechacompra");
                            int precioCompra = resultado.getInt("preciototal");
                            String tarjeta = resultado.getString("tarjetacredito");
                            String nombreEvento = resultado.getString("nombre");
                            String fechaComoCadena = this.sdf.format(fechaCompra);
                            this.guardian.correoClienteReembolsoDeCompra(correoCliente, idComp, numeroEntradas, fechaComoCadena, precioCompra, tarjeta, nombreEvento);
                        }
                        resultado.close();
                        st.close();
                        //Collections.sort(eventos);
                    }
                }

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
            }

        }
    }

}
