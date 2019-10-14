package ControladorBaseDeDatos;

import ModuloGestionEventos.Evento;
import ModuloGestionPropiedades.Propiedad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class ControladorBDDeEventos {

    ConexionBD conexion;
    ControladorBDDePropiedades propiedades;

    /**
     * Default constructor
     */
    public ControladorBDDeEventos() {
        this.conexion = new ConexionBD();
        this.propiedades = new ControladorBDDePropiedades();
        iniciarlizarBD();
    }

    public void iniciarlizarBD() {
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
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean nombreRepetido = validarNombreEvento(rutOrganizador, nombre);
            //System.out.println("repetido:" + nombreRepetido);
            if (nombreRepetido == false) {
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into evento values(DEFAULT,'" + nombre + "','" + descripcion + "','" + fechaDeInicio + "','" + fechaDeTermino + "',\n"
                            + " " + capacidad + ", " + diasMaximoDevolucion + " ," + publicado + ",'" + rutOrganizador + "')"
                            + " RETURNING id";
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
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);

                    return 0;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //System.out.println("error al cerrar la base de datos.");
                    }

                }
            }

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
        boolean aceptado;
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
     * @param conexion
     * @param idEvento
     * @return boolean.
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
     * elimina un evento de la base de datos, ademas de borrar la asociacion de
     * una propiedad con unn evento.
     *
     * @param idEvento
     * @return
     */
    public boolean eliminarEvento(int idEvento) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {
            boolean eliminar = obtenerPublicadoDeEvento(miConexion, idEvento);
            if (eliminar == false) {
                try {
                    eliminarAsociacionEventoPropiedad(miConexion, idEvento);
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "delete from evento where evento.id=" + idEvento + "";
                    st.executeUpdate(sql);
                    st.close();
                    return true;
                } catch (SQLException e) {
                    //      System.out.println("ERROR DE CONEXION: eliminar evento (desde la tabla de usuario)" + e);
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

                String sql = "select * from evento where evento.reforganizador='" + rutOrganizador + "'";
                // System.out.println(sql);
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

                String sql = "select * from evento where evento.publicado=true and evento.reforganizador='" + rutOrganizador + "'";
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

                String sql = "select * from evento where evento.publicado=false and evento.reforganizador='" + rutOrganizador + "'";
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

                String sql = "SELECT *\n"
                        + "FROM evento\n"
                        + "WHERE evento.fechatermino <= '" + fecha + "'::date and evento.reforganizador='" + rutOrganizador + "'";

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
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = " UPDATE evento set publicado=true where evento.id=" + idEvento + "";
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
                        + "where propietario.rut='" + rutPropietario + "' and evento.publicado=false";
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
                        + "where propietario.rut='" + rutPropietario + "' and evento.publicado=true";
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
                    // obtengo la informacion del cliente.
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

                String sql = "select * from propietario";
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

    /**
     * crea una entrada de prueba, la cual se asociara a un evento, sector y
     * propiedad.
     *
     * @param conexion
     * @return identificador de la entrada.
     */
    private int crearEntradaPrueba(Connection conexion) {
        this.conexion.crearConexion();
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            int nombreRepetido = estaCreadaEntradaPrueba(conexion);
            //System.out.println("repetido: ==============" + nombreRepetido);
            if (nombreRepetido == 0) {
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into entrada values(DEFAULT,FALSE)RETURNING id";// creamos la entrada de prueba.
                    //  System.out.println(sql);
                    ResultSet resultado = st.executeQuery(sql);
                    while (resultado.next()) {
                        int idEvento = Integer.parseInt(resultado.getString("id"));

                        return idEvento;
                    }
                    st.close();

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    //System.out.println("error conexion");

                    return 0;
                }
            }
            return nombreRepetido;

        }
        return 0;
    }

    /**
     * Verifica de la entrada de prueva esta creada.
     *
     * @param conexion: conenxion con la base de datos.
     * @return identificador de la entrada si esta creada, cero de caso
     * contrario.
     */
    private int estaCreadaEntradaPrueba(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * from entrada where  entrada.vendida=false";
               // System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    st.close();
                 //   System.out.println("el numero de la entrada es :"+idEvento);
                    return idEvento;//esta creada la entrada
                }

            } catch (SQLException e) {
                //System.out.println("error conexion");
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
    public boolean añadirPrecioEntradaPorSector(int idEvento, int precioEntrada, String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            try {
                int idEntradaPrueba = crearEntradaPrueba(miConexion);
                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into asociacion values(" + idEvento + "," + idEntradaPrueba + "," + precioEntrada + ",'" + nombreSector + "'," + idPropiedad + ")";
                //System.out.println(sql);
                st.executeQuery(sql);
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
    public boolean modificarPrecioEntradaPorSector(int idEvento, int nuevoPrecioEntrada, String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean EstadoEvento = obtenerPublicadoDeEvento(miConexion, idEvento);
            if (EstadoEvento == false) {
                try {
                    int idEntradaPrueba = crearEntradaPrueba(miConexion);
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "update  asociacion set precio="+nuevoPrecioEntrada+" where asociacion.refevento="+idEvento+" and\n"
                            + "asociacion.refsector='"+nombreSector+"' and asociacion.refpropiedad="+idPropiedad+"";
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

    public int obtenerPrecioEntradaPorSector(int idEvento, String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null)// si hay conexion.
        {
            try {
                java.sql.Statement st = miConexion.createStatement();
                int idEntrada = crearEntradaPrueba(miConexion);
                String sql = "select asociacion.precio from asociacion where asociacion.refevento=" + idEvento + " and asociacion.refentrada=" + idEntrada + " and asociacion.refsector='" + nombreSector + "' and asociacion.refpropiedad=" + idPropiedad + "";
               // System.out.println(sql);
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
                    //System.out.println("No se cerro la base de datos satisfactoriamente");
                }
            }

        }
        return 0;

    }

}
