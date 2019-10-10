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
    String nombreBD;
    String contraseñaUsuarioBD;

    /**
     * Default constructor
     */
    public ControladorBDDeEventos() {
        this.conexion = new ConexionBD();
        this.nombreBD = "EventTinder";
        this.contraseñaUsuarioBD = "1";
    }


    /**
     * Registra un Evento en la base de datos, ademas de establer un enlace entre
     * una propiedad y un evento.
     * @param nombre: nombre del evento
     * @param fechaDeInicio: fecha de inicio del evento
     * @param fechaDeTermino: fecha de termino del evento.
     * @param capacidad: capacidad del evento.
     * @param descripcion: descripcion del evento.
     * @param diasMaximoDevolucion: dias maximo de devolucion del evento.
     * @param publicado: indica si el evento esta publicado o no.
     * @param idPropiedad: identificador de una propiedad
     * @param rutOrganizador: referencia al organizador al cual pertenece el evento.
     * @return identificador del evento o cero en caso de que surga un error al
     * crear un evento
     */
    public int crearEvento(String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad, String rutOrganizador) {
        this.conexion.crearConexion(this.nombreBD, this.contraseñaUsuarioBD);
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean nombreRepetido = validarNombreEvento(rutOrganizador, nombre);
            System.out.println("repetido:"+nombreRepetido);
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
                        boolean Celebra=tablaCelebra(miConexion, idEvento, idPropiedad);
                        if(Celebra==false){
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
    
     public boolean tablaCelebra(Connection conexion, int idEvento, int idPropiedad) {
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
            System.out.println("hay eventos:"+eventos.size());
            for (int i = 0; i < eventos.size(); i++) {
                Evento evento = eventos.get(i);
                System.out.println("nombre evento:"+evento.getNombre()+" nombre a compara:"+nombreEvento);
                if (evento.getNombre().equalsIgnoreCase(nombreEvento) == true) {
                    System.out.println("entre en true");
                    return true;
                }

            }
        }

        return false;

    }
    
    
    

    /*
     * @return
     */
    public boolean modificarEvento(int idEvento, String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int diasMaximoDevolucion, boolean publicado, int idPropiedad) {
        // TODO implement here
        return false;
    }

    /**
     * @param idEvento
     * @return
     */
    public boolean eliminarEvento(int idEvento) {
        // TODO implement here
        return false;
    }

    /**
     * @param rut
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeTodosLosEventosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param rut
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosPublicadosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param rut
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosNoPublicadosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param rut
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosDeUnOrganizador(String rut) {
        // TODO implement here
        return null;
    }

    /**
     * @param idEvento
     * @return
     */
    public boolean aceptarSolicitudPropietario(int idEvento) {
        // TODO implement here
        return false;
    }

    /**
     * @param rutPropietario
     * @return
     */
    public ArrayList<Evento> obtenerInformacionSolicitudesDeEventosPropietario(String rutPropietario) {
        // TODO implement here
        return null;
    }

    /**
     * @param rutPropietario
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosActualesPropietario(String rutPropietario) {
        // TODO implement here
        return null;
    }

    /**
     * @param rutPropietario
     * @return
     */
    public ArrayList<Evento> obtenerInformacionDeEventosFinalizadosPropietario(String rutPropietario) {
        // TODO implement here
        return null;
    }

}