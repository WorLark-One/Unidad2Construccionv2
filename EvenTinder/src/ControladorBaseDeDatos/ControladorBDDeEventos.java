package ControladorBaseDeDatos;

import ModuloGestionEventos.Entrada;
import ModuloGestionEventos.Evento;
import ModuloGestionPropiedades.Propiedad;
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
    public int crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino,
            int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad, String rutOrganizador) {
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
                    ResultSet resultado = st.executeQuery(sql);
                    while (resultado.next()) {
                        int idEvento = Integer.parseInt(resultado.getString("id"));
                        boolean Celebra = relacionaEventoConPropiedad(miConexion, idEvento, idPropiedad);
                        if (Celebra == false) {
                            eliminarEvento(idEvento);
                            return 0;
                        }
                        st.close();
                        cerrarConexionBD(miConexion);
                        return idEvento;
                    }

                } catch (SQLException e) {
                    cerrarConexionBD(miConexion);
                    return 0;
                }
            }
            return -1;
        }
        return 0;
    }

    /**
     * Relaciona un evento con una propiedad.
     *
     * @param conexion: conexion de la base de datos.
     * @param idEvento: identificador del evento.
     * @param idPropiedad: identificador de la propiedad.
     * @return true si se realza la asociacion entre evento y propiedad, false
     * de lo contrario.
     */
    private boolean relacionaEventoConPropiedad(Connection conexion, int idEvento, int idPropiedad) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into celebra values(" + idEvento + "," + idPropiedad + ")";
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
     * Verifica si el nombre de un evento ya fue creado anteriormente.
     *
     * @param rut
     * @param nombreEvento
     * @return
     */
    private boolean validarNombreEvento(String rut, String nombreEvento) {
        ArrayList<Evento> eventos = obtenerInformacionDeTodosLosEventosDeUnOrganizador(rut);
        if (eventos != null) {
            for (int i = 0; i < eventos.size(); i++) {
                Evento evento = eventos.get(i);
                if (evento.getNombre().equalsIgnoreCase(nombreEvento) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Modifica la informacion registrada de un evento.
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
    public boolean modificarEvento(int idEvento, String nuevoNombre, String nuevaDescripcion, Date nuevaFechaDeInicio, Date nuevaFechaDeTermino,
            int nuevaCapacidad, int nuevosDiasMaximoDevolucion, boolean nuevoPublicado) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean EstadoEvento = obtenerEstadoDeUnEvento(miConexion, idEvento);
            if (EstadoEvento == false) {
                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = " UPDATE evento set nombre='" + nuevoNombre + "', descripcion='" + nuevaDescripcion + ""
                            + "',fechainicio='" + nuevaFechaDeInicio + "',fechatermino='" + nuevaFechaDeTermino + "',\n"
                            + "capacidad=" + nuevaCapacidad + ",plazodevolucionentradas=" + nuevosDiasMaximoDevolucion + ","
                            + "publicado='" + nuevoPublicado + "' where evento.id=" + idEvento + " ";
                    st.executeUpdate(sql);
                    st.close();
                    cerrarConexionBD(miConexion);
                    return true;
                } catch (SQLException e) {
                    cerrarConexionBD(miConexion);
                    return false;
                }
            } else {
                //el evento esta activo por lo cual no se puede modificar ni la ubicacion
                // ni el valor de la entrada.
            }
            return false;
        }
        return false;
    }

    /**
     * Verifica si un evento esta publicado.
     *
     * @param conexion: conexion con la base de datos
     * @param idEvento: identificador de un evento
     * @return true si el evento esta publicado,false de lo contrario.
     */
    private boolean obtenerEstadoDeUnEvento(Connection conexion, int idEvento) {
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
                return false;
            }
        }
        return false;
    }

    /**
     * Elimina el registro de un evento en la base de datos.
     * @param idEvento:identificador de un evento.
     * @return
     */
    public boolean eliminarEvento(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean eventosFinalizados = existenEventosFinalizados(idEvento);
            boolean eventoPublicado = eventoPublicado(miConexion, idEvento);
            System.out.println("HAY EVENTOS FINALIZADOS:" + eventosFinalizados);
            System.out.println("HAY EVENTOS PUBLICADOS:" + eventoPublicado);
            if (eventosFinalizados == false) {
                if (eventoPublicado == false) {
                    try {
                        ArrayList<Integer> listaCompra = obtenerIdentificadoresDeCompra(miConexion, idEvento);
                        generarRembolsoDeCompra(miConexion, listaCompra);
                        eliminarAsociacionEventoPropiedad(miConexion, idEvento);
                        borrarAsociacionEventoSectorEntrada(miConexion, idEvento);
                        java.sql.Statement st = miConexion.createStatement();
                        String sql = "delete from evento where evento.id=" + idEvento + "";
                        st.executeUpdate(sql);
                        st.close();
                        cerrarConexionBD(miConexion);
                        return true;
                    } catch (SQLException e) {
                        cerrarConexionBD(miConexion);
                        return false;
                    }
                } else {
                    cerrarConexionBD(miConexion);
                    return false;
                }
            } else {

                // si tiene eventos finalizados no se puede
                //borrar el evento, en cambio se deja desabilitado.
                desabilitarCelebra(miConexion, idEvento);
                desabilitarAsociacionEventoEntradaSector(miConexion, idEvento);
                desabilitarEvento(miConexion, idEvento);
                cerrarConexionBD(miConexion);
                return true;

            }
        }
        return false;
    }

    /**
     * Borra el registro de la asociacion de una entrada, sector y evento.
     *
     * @param miConexion
     * @param idEvento
     * @return true si borra el registro, false de lo contrario.
     */
    private boolean borrarAsociacionEventoSectorEntrada(Connection miConexion, int idEvento) {
        if (miConexion != null) {

            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from asociacioneventoentradasector where asociacioneventoentradasector.refevento=" + idEvento + "";
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
     * Obtiene una lista de todos los eventos publicados y no publicados de un
     * organizador. no estoy filtrando por fecha ni por propiedad activa.
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
                String sql = "select * from evento where evento.reforganizador='" + rutOrganizador + "'";
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }

        }
        return null;
    }

    /**
     * Obtiene los eventos publicados en un intervalo de fechas
     *
     * @param fechaInicio: fecha de inicio del evento.
     * @param fechaTermino: fecha de termino del evento.
     * @return lista de eventos.
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
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
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
     * Obtiene una lista de todos los eventos publicados de un organizador
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }
        }
        return null;
    }

    /**
     * Obtiene una lista con todos los eventos no publicados de un organizador
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
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
                        + "and propiedad.activa='true'"
                        + "and evento.publicado='true'";
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }

        }
        return null;
    }

    /**
     * Metodo privado Modifica a true el parametro publicado
     *
     * @param idEvento: identificador de un evento.
     * @param rutPropietario : rut de un propietario.
     * @return true si modifica el parametro publicado, false de lo contrario
     */
    public boolean aceptarSolicitudPropietario(int idEvento, String rutPropietario) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = " UPDATE evento set publicado=true where evento.id=" + idEvento + "";
                CorreoArriendoPropiedad(miConexion, idEvento);
                st.executeUpdate(sql);
                st.close();
                Evento evento = obtenerEvento(miConexion, idEvento);
                eliminarSolicitudesDeEvento(miConexion, evento.getFechaDeInicio(), evento.getFechaDeTermino(), rutPropietario);
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
     * rechaza la solicitud de un organizador para celebrar un evento en sus
     * propiedad.
     *
     * @param idEvento: identificador de un evento.
     * @return
     */
    public boolean rechazarSolicitudPropietario(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {// si hay conexion.
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
                        + "where evento.id=" + idEvento + " and propiedad.activa='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String correoPropietario = resultado.getString("correopropietario");
                    String correoOrganizador = resultado.getString("correoorganizador");
                    String nombreEvento = resultado.getString("nombreevento");
                    Date fechaIni = resultado.getDate("fechainicio");
                    Date fechaTer = resultado.getDate("fechatermino");
                    String tarjetaOrganizador = resultado.getString("tarjetacredito");
                    int idPropiedad = Integer.parseInt(resultado.getString("identificadorpropiedad"));
                    String nombrePropiedad = resultado.getString("nombrepropiedad");
                    String fechaInic = this.sdf.format(fechaIni);
                    String fechaTerm = this.sdf.format(fechaTer);
                    this.guardian.correoPropietarioCancelacionEvento(correoPropietario, idEvento, nombreEvento, fechaInic, fechaTerm);
                    this.guardian.correoOrganizadorEventoRechazado(correoOrganizador, idEvento, nombreEvento, fechaInic, fechaTerm, tarjetaOrganizador, idPropiedad, nombrePropiedad);
                    eliminarEvento(idEvento);
                    cerrarConexionBD(miConexion);
                    return true;
                }

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return false;
            }
        }
        return false;
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
     * Elimina los registros de entradas.
     *
     * @param conexion: conexión con la BD
     * @param entradas: lista de entradas.
     * @return true si logra borrar los registros de las entradas, false de lo
     * contrario.
     */
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
                        st.executeUpdate(sql);
                        st.close();
                    } catch (SQLException e) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Obtiene una lista de entradas asociadas a un evento.
     *
     * @param miConexion: conexion con la BD.
     * @param idEvento: identificador del evento.
     * @return lista de entradas o null en caso que el evento no tenga entradas
     * asociadas.
     */
    private ArrayList<Integer> obtenerListaEntradas(Connection miConexion, int idEvento) {
        ArrayList<Integer> entradas = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select *from asociacioneventoentradasector as t1 where t1.refevento=" + idEvento + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEntrada = Integer.parseInt(resultado.getString("refentrada"));
                    entradas.add(idEntrada);
                }
                resultado.close();
                st.close();
                return entradas;
            } catch (SQLException e) {
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
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
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String rutCliente = resultado.getString("rut");
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
                cerrarConexionBD(miConexion);
                return listaPropiedades;
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }

        }
        return null;
    }

    /**
     * Metodo privado Crea una entrada, para asignarle un precia a un sector
     * Crea una entrada.
     *
     * @param conexion:conexion con la base de datos.
     * @return
     */
    private int crearEntrada(Connection conexion) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into entrada values(DEFAULT)RETURNING id";// creamos la entrada de prueba.
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    return idEvento;
                }
                st.close();
            } catch (SQLException e) {
                return 0;
            }

        }
        return 0;
    }

    /**
     * Metodo privado Añade el valor que tendra las entradas por sector.
     *
     * @param idEvento: identificador del evento.
     * @param precio: precio de la entrada.
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
                st.executeQuery(sql);
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
     * modifica el precio de una entrada
     *
     * @param idEvento: identificador de evento.
     * @param nuevoPrecioEntrada : nuevo precio que se le asignara a la entrada.
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de la propiedad
     * @return true si modifica el precio, false de lo contrario.
     */
    public boolean modificarPrecioEventoPorSector(int idEvento, int nuevoPrecioEntrada, String nombreSector,
            int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean EstadoEvento = obtenerEstadoDeUnEvento(miConexion, idEvento);
            if (EstadoEvento == false) {
                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "update asociacioneventoentradasector set precio=" + nuevoPrecioEntrada + "\n"
                            + "where asociacioneventoentradasector.refevento=" + idEvento + " and asociacioneventoentradasector.refsector='" + nombreSector + "'\n"
                            + "and asociacioneventoentradasector.refpropiedad=" + idPropiedad + "";
                    st.executeUpdate(sql);
                    st.close();
                    cerrarConexionBD(miConexion);
                    return true;
                } catch (SQLException e) {
                    cerrarConexionBD(miConexion);
                    return false;
                }
            }
        }
        return false;

    }

    /**
     * Obtiene el precio de entrada de un evento por sector.
     *
     * @param idEvento:identificador de un evento.
     * @param nombreSector:nombre del sector.
     * @param idPropiedad:identificador de una propiedad.
     * @return
     */
    public int obtenerPrecioEventoPorSector(int idEvento, String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select asociacioneventoentradasector.precio from asociacioneventoentradasector  \n"
                        + "where asociacioneventoentradasector.refevento=" + idEvento + " and asociacioneventoentradasector.refsector='" + nombreSector + "'\n"
                        + "and asociacioneventoentradasector.refpropiedad=" + idPropiedad + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int precio = resultado.getInt("precio");
                    resultado.close();
                    st.close();
                    cerrarConexionBD(miConexion);
                    return precio;
                }
            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return 0;
            }
        }
        return 0;

    }

    /**
     * Obtiene una lista con todos los eventos activos registrados en la base de
     * datos.
     *
     * @return lista de eventos o null en caso de que no existan eventos
     * registrados
     */
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }
        }
        return null;
    }

    /**
     * Metodo privado. Se envia un correo a un propietario y organizador de la
     * aceptacion de un evento.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idEvento:identificador de un evento.
     */
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
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
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
                    this.guardian.CorreoPropietarioAceptarEvento(correo, idEven, nombreEvento, fechaIni, fechaTer, cuentaCorriente, idPropiedad, nombrePropiedad);
                    String correoOrganzador = resultado.getString("correoorganizador");
                    String tarjetaOrg = resultado.getString("tarjetacredito");
                    this.guardian.correoOrganizadorEventoAceptado(correoOrganzador, idEven, nombreEvento, fechaIni, fechaTer, tarjetaOrg, idPropiedad, nombrePropiedad);
                }
                resultado.close();

            } catch (SQLException e) {
            }
        }
    }

    /**
     * verifica si el evento es finalizados
     *
     * @param miConexion
     * @param idEvento
     * @return true si el id del evento pertence a uno finzalizado false de lo
     * contrario
     */
    private boolean existenEventosFinalizados(int idEvento) {
        ArrayList<Evento> eventos = listaEventosFinalizados();
        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            if (idEvento == evento.getIdEvento()) {
                return true;
            }

        }
        return false;

    }

    /**
     * Metodo privado Obtiene una lista de los eventos finalizados registrados
     * en la BD.
     *
     * @return lista de eventos finalizados o null en caso de que no existan
     * eventos finalizados.
     */
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
                        + "WHERE evento.fechatermino <= '" + fecha + "'::date and evento.publicado='true'";
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
                Collections.sort(eventos);
                cerrarConexionBD(miConexion);
                return eventos;

            } catch (SQLException e) {
                cerrarConexionBD(miConexion);
                return null;
            }

        }
        return null;
    }

    /**
     * Valida las fecha de inicio de un evento, para la solicitud de arriendo.
     *
     * @param miConexion:conexión con la BD.
     * @param fechaDeInicio: fecha de inicio del evento.
     * @param idPropiedad:identificador de la propiedad.
     * @return
     */
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
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    resultado.close();
                    st.close();
                    return false;
                }
                resultado.close();
                st.close();
                return true;
            } catch (SQLException e) {
            }
        }
        return false;
    }

    /**
     * Obtiene los identidicadores de compra relizacionados a un evento.
     *
     * @param miConexion:conexión con la BD.
     * @param idEvento:identificador del evento.
     * @return
     */
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
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idCompra = Integer.parseInt(resultado.getString("id"));
                    identificadorCompras.add(idCompra);
                }
                resultado.close();
                st.close();
                return identificadorCompras;

            } catch (SQLException e) {
                return null;
            }

        }
        return null;
    }

    /**
     * Genera el reembolso de una compra.
     *
     * @param miConexion:conexión con la BD.
     * @param listaCompra:lista de compras a la que se le realizara reembolso.
     */
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
                    }
                }

            } catch (SQLException e) {
            }

        }
    }

    /**
     * Metodo privado. Desabila la relacion entre un evento y una propiedad.
     *
     * @param miConexion:conexión con la BD.
     * @param idEvento :identificador de un evento.
     */
    private void desabilitarCelebra(Connection miConexion, int idEvento) {
        if (miConexion != null) {

            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "update celebra set activa='false' where celebra.refevento=" + idEvento + "";
                st.executeUpdate(sql);
                st.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Metodo privado. Desabilita la relacion entre entrada, evento, sector y
     * propiedad.
     *
     * @param miConexion:conexióon con la base de datos.
     * @param idEvento :identificador de un evento.
     */
    private void desabilitarAsociacionEventoEntradaSector(Connection miConexion, int idEvento) {
        if (miConexion != null) {

            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "update asociacioneventoentradasector set activa='false'\n"
                        + "where asociacioneventoentradasector.refevento=" + idEvento + "";
                st.executeUpdate(sql);
                st.close();

            } catch (SQLException e) {
            }
        }
    }

    /**
     * Metodo privado. Desabilita un evento.
     *
     * @param miConexion: conexión con la BD.
     * @param idEvento
     */
    private void desabilitarEvento(Connection miConexion, int idEvento) {
        if (miConexion != null) {

            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "update evento set activo='false'\n"
                        + "where evento.id=" + idEvento + "";
                st.executeUpdate(sql);
                st.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Verifica si que la fecha de termino de un evento publicado sea menos a la
     * fecha actual.
     *
     * @param miConexion: conexion con la base de datos.
     * @param idEvento:identificador de un evento.
     * @return
     */
    private boolean eventoPublicado(Connection miConexion, int idEvento) {
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                java.util.Date fechaActual = new Date();
                String sql = "select evento.nombre,evento.fechainicio,evento.fechatermino \n"
                        + "from celebra\n"
                        + "inner join evento on celebra.refevento = evento.id\n"
                        + "where evento.id=" + idEvento + " and evento.activo='true'\n"
                        + "and evento.fechatermino >='" + fechaActual + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    resultado.close();
                    st.close();
                    return true;
                }
            } catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Obtiene la instancia de un evento.
     *
     * @param miConexion:conexion con la base de datos.
     * @param idEvento: identificador de un evento.
     * @return instancia de evento.
     */
    private Evento obtenerEvento(Connection miConexion, int idEvento) {
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from evento where evento.id=" + idEvento + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {                    //int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");
                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
                    resultado.close();
                    st.close();
                    return miEvento;
                }
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Borra los registros de solicitudes de eventos que esten en un intervalo
     * de fechas.
     *
     * @param miConexion: conexion con la base de datos.
     * @param fechaDeInicio: fecha de inicio.
     * @param fechaDeTermino: fecha de termino
     * @param rutPropietario : rut del propietario.
     */
    private void eliminarSolicitudesDeEvento(Connection miConexion, Date fechaDeInicio, Date fechaDeTermino, String rutPropietario) {
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select evento.id\n"
                        + "from propietario\n"
                        + "inner join propiedad on propietario.rut = propiedad.refpropietario\n"
                        + "inner join celebra on propiedad.id= celebra.refpropiedad\n"
                        + "inner join evento on evento.id= celebra.refevento\n"
                        + "where propietario.rut='" + rutPropietario + "' and evento.publicado='false'\n"
                        + "and propiedad.activa='true'\n"
                        + "and evento.fechainicio>='" + fechaDeInicio + "' and evento.fechainicio<='" + fechaDeTermino + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    rechazarSolicitudPropietario(idEvento);
                }
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
