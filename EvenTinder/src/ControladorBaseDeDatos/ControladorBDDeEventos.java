package ControladorBaseDeDatos;

import ModuloGestionEventos.Evento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class ControladorBDDeEventos {

    ConexionBD conexion;

    /**
     * Default constructor
     */
    public ControladorBDDeEventos() {
        this.conexion = new ConexionBD();
        iniciarlizarBD();
    }
    
    
    public void iniciarlizarBD(){
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
     */
    public int crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad, String rutOrganizador) {
        this.conexion.crearConexion();
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean nombreRepetido = validarNombreEvento(rutOrganizador, nombre);
            System.out.println("repetido:" + nombreRepetido);
            if (nombreRepetido == false) {
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into evento values(DEFAULT,'" + nombre + "','" + descripcion + "','" + fechaDeInicio + "','" + fechaDeTermino + "',\n"
                            + " " + capacidad + ", " + diasMaximoDevolucion + " ," + publicado + ",'" + rutOrganizador + "')"
                            + " RETURNING id";
                    System.out.println(sql);
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
                    System.out.println("error conexion");

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
                System.out.println(sql);
                st.executeUpdate(sql);
                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                System.out.println("error tabla celebra");

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
            System.out.println("hay eventos:" + eventos.size());
            for (int i = 0; i < eventos.size(); i++) {
                Evento evento = eventos.get(i);
                System.out.println("nombre evento:" + evento.getNombre() + " nombre a compara:" + nombreEvento);
                if (evento.getNombre().equalsIgnoreCase(nombreEvento) == true) {
                    System.out.println("entre en true");
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
                    //boolean modificarCelebra=modificarTablaCelebra(miConexion,idEvento,idPropiedad);
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
                    st.executeUpdate(sql);
                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                }finally {
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
                String sql = "";
                System.out.println(sql);
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    boolean publicado = resultado.getBoolean("publicado");
                    return publicado;
                }
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                //System.out.println("error conexion");

                return false;
            }

        }
        return false;
    }

    /**
     * elimina un evento de la base de datos, ademas de borrar la asociacion
     * de una propiedad con unn evento.
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
                    //System.out.println("ERROR DE CONEXION: eliminar cliente (desde la tabla de usuario)" + e);
                    return false;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                        //System.out.println("No se cerro satisfactoriamente la base de datos.");
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

                String sql = "select * from evento where evento.publicado=false or evento.publicado=true and evento.reforganizador='" + rutOrganizador + "'";

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

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
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
                    // obtengo la informacion del cliente.
                    int idEvento = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String descripcion = resultado.getString("descripcion");
                    Date fechaInicio = resultado.getDate("fechainicio");
                    Date fechaTermino = resultado.getDate("fechatermino");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int plazoDeVolucion = Integer.parseInt(resultado.getString("plazodevolucionentradas"));
                    boolean publicado = resultado.getBoolean("publicado");

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
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

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
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
                        + "WHERE evento.fechatermino <= '"+fecha+"'::date";

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

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
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
     * @param conexion: conexion a la base de datos.
     * @param idEvento: identificador de evento.
     * @param idPropiedad: identificador de la propiedad.
     * @return true si se elimina la asociacion, false de lo contrario.
     */
    private boolean eliminarAsociacionEventoPropiedad(Connection conexion, int idEvento) {
        Connection miConexion = conexion;

        if (miConexion != null) {
            boolean eliminar = obtenerPublicadoDeEvento(miConexion, idEvento);
            if (eliminar == false) {
                try {
                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "delete from celebra where celebra.refevento=" + idEvento + "";
                    System.out.println(sql);
                    st.executeUpdate(sql);
                    st.close();
                    return true;
                } catch (SQLException e) {
                    System.out.println("ERROR DE CONEXION: eliminarAsociacionEventoPropiedad" + e);
                    return false;
                } finally {
                    try {
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        //Logger.getLogger(ControladorBDDeEventos.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("No se cerro satisfactoriamente la base de datos.");
                    }
                }
            }

        }
        return false;

    }
    
    
    
    /**
     * Obtiene una lista de los eventos los cuales le solicitan arriendo.
     *
     * @param rutPropietario
     * @return lista de eventos, o null en caso de que no existan eventos asociados al propietario
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
                        + "where propietario.rut='" + rutPropietario + "'";

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

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
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
     * @return lista de eventos, o null en caso de que no existan eventos asociados al propietario
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

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
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
     * Obtien una lista de todos los eventos que ya finalizaron asociados a un propietario.
     * @param rutPropietario
     * @return lista de eventos, o null en caso de que no existan eventos asociados al propietario
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
                        + "where propietario.rut='"+rutPropietario+"' and evento.fechatermino <= '"+fecha+"'::date";

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

                    Evento miEvento = new Evento(idEvento, nombre, descripcion, fechaInicio, fechaTermino, capacidad, plazoDeVolucion, publicado);
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


}
