package ControladorBaseDeDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 */
public class ControladorBDDePropiedades {

    ConexionBD conexion;

    /**
     * Default constructor
     */
    public ControladorBDDePropiedades() {
        this.conexion = new ConexionBD();
    }

    /**
     * @param rut
     * @throws java.sql.SQLException
     */
    public boolean obtenerInformacionDePropiedades(String rut) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from propiedad where propiedad.refpropietario='" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idPropiedad = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String ubicacion = resultado.getString("ubicacion");
                    String capacidadtotal = resultado.getString("capacidadtotal");
                    int valorArriendo = Integer.parseInt(resultado.getString("valorarriendo"));
                    String descripcion = resultado.getString("descripcion");
                    int numeroSectores = Integer.parseInt(resultado.getString("numerodesectores"));
                    String refPropietario = resultado.getString("refpropietario");

                    System.out.println("id: " + idPropiedad + " nombre: " + nombre + " ubicacion:" + ubicacion + " capacidad total:" + capacidadtotal + ""
                            + " valor arriendo:" + valorArriendo + " descripcion:" + descripcion + " numero de sectores:" + numeroSectores + ""
                            + " ref a propietario:" + refPropietario);

                    System.out.println("===================");
                }
                resultado.close();
                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                //return cliente;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
        return false;
    }

    /**
     * Entrega una propiedad, atravez del identificador y rut del propietario.
     *
     * @param rut; rut del dueño de la propiedad.
     * @param id: identificador de la propiedad.
     * @throws java.sql.SQLException
     */
    public void obtenerInformacionDeUnaPropiedad(String rut, int id) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from propiedad where propiedad.id=" + id + " and propiedad.refpropietario='" + rut + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idPropiedad = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String ubicacion = resultado.getString("ubicacion");
                    String capacidadtotal = resultado.getString("capacidadtotal");
                    int valorArriendo = Integer.parseInt(resultado.getString("valorarriendo"));
                    String descripcion = resultado.getString("descripcion");
                    int numeroSectores = Integer.parseInt(resultado.getString("numerodesectores"));
                    String refPropietario = resultado.getString("refpropietario");

                    System.out.println("id: " + idPropiedad + " nombre: " + nombre + " ubicacion:" + ubicacion + " capacidad total:" + capacidadtotal + ""
                            + " valor arriendo:" + valorArriendo + " descripcion:" + descripcion + " numero de sectores:" + numeroSectores + ""
                            + " ref a propietario:" + refPropietario);

                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                //return cliente;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }

    }

    /**
     * registra una propiedad en la base de datos, para que el registro sea
     * exitoso se debe haber creado anteriormente un propietario con el rut al
     * cual se le hara la referencia.
     *
     * @param rut: rut del propietario
     * @param nombre :nombre de la propiedad
     * @param descripcion :descripcion de la propiedad
     * @param fechaDePublicacion : fecha de publicacion de la propiedad.
     * @param ubicacion :ubicacion de la propiedad
     * @param valorDeArriendo: valor del arriendo de la propiedad
     * @return id de la propiedad creada, 0 si no crea la propiedad
     * @throws java.sql.SQLException
     */
    public int registrarPropiedad(String rut, String nombre, String descripcion, String fechaDePublicacion, String ubicacion, int valorDeArriendo) throws SQLException {

        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into propiedad values(DEFAULT,'" + nombre + "','" + descripcion + "','" + fechaDePublicacion + "','0','" + valorDeArriendo + "','" + ubicacion + "','50','" + rut + "')"
                        + " RETURNING id";
                
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idPropiedad = Integer.parseInt(resultado.getString("id"));
                    return idPropiedad;
                }
                st.close();
                
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                return 0;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);

            }

        }
        return 0;
    }

    /**
     * modifica la informacion de una propiedad
     *
     * @param id: identificador de una propiedad
     * @param nombre: nombre por el cual se modificara la propieda
     * @param descripcion: descripcion por la cual se modificara la propieda
     * @param fechaDePublicacion: fecha de publicacion por la cual se modificara
     * la propieda
     * @param ubicacion: ubicacion por la cual se modificara la propieda
     * @param valorDeArriendo: valor del arriendo por el cual se modificara la
     * propieda
     * @return
     * @throws java.sql.SQLException
     */
    public boolean modifcarPropiedad(int id, String nombre, String descripcion, String fechaDePublicacion, String ubicacion, int valorDeArriendo) throws SQLException {

        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = " UPDATE propiedad SET nombre='" + nombre + "', ubicacion='" + ubicacion + "',fechapublicacion='" + fechaDePublicacion + "',\n"
                        + "valorarriendo='" + valorDeArriendo + "',descripcion='" + descripcion + "'\n"
                        + "where  propiedad.id=" + id + " ";
                st.executeUpdate(sql);

                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                return false;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);

            }

        }
        return false;

    }

    /**
     * elimina una propiedad a traves de su id
     *
     * @param id: identificador de una propiedad.
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean eliminarPropiedad(int id) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {

            // si el rut del usuario esta en la tabla cliente, y el rut no esta en las
            // tablas de organizador y propietario, esto implica que puedo borrar 
            // desde la tabla usuario, lo que permite la eliminacion en cascada.
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from propiedad where propiedad.id=" + id + "";
                st.executeUpdate(sql);

                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: eliminar cliente (desde la tabla de usuario)" + e);
                return false;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
        return false;

    }

    //====================Sectores.
    /**
     * registra un sector en la base de datos, anexo a una propiedad.
     * @param nombreSector:nombre del sector.
     * @param capacidad: capacidad del sector.
     * @param idPropiedad: identificador de la propiedad a la cual pertenece el sector.
     * @return true si registra el sector satisfactoriamente ,false de lo contrario
     * @throws SQLException 
     */
    public boolean registrarSector(String nombreSector,int capacidad, int idPropiedad) throws SQLException {

        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();
        
        if (miConexion != null) {

                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into sector values('"+nombreSector+"',"+capacidad+","+idPropiedad+")";
                    System.out.println(sql);
                    st.executeUpdate(sql);
                    st.close();
                    return true;

                } catch (SQLException e) {
                    //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                    return false;
                } finally {
                    this.conexion.cerrarBaseDeDatos(miConexion);

                }
        }
        return false;
    }

    /**
     * borra un sector de la base de datos, a traves de nombre y el
     * identificador de propiedad del sector
     *
     * @param nombreSector: nombre del sector
     * @param idPropiedad: identificador de la propiedad a la cual pertenece el
     * sector
     * @return 
     * @return: true si borra el sector, false de lo contrario.
     * @throws SQLException
     */
    public boolean eliminarSector(String nombreSector, String idPropiedad) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {

            // si el rut del usuario esta en la tabla cliente, y el rut no esta en las
            // tablas de organizador y propietario, esto implica que puedo borrar 
            // desde la tabla usuario, lo que permite la eliminacion en cascada.
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from sector where nombre='" + nombreSector + "' and sector.refpropiedad=" + idPropiedad + "";
                st.executeUpdate(sql);

                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: eliminar cliente (desde la tabla de usuario)" + e);
                return false;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
        return false;
    }

    public boolean modificarSector(String nombreSector, int idPropiedad, String nuevoNombre, int nuevaCapacidad) throws SQLException {

        this.conexion.crearConexion("EventTinder", "1");
        boolean aceptado;
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = " UPDATE sector SET nombre='"+nuevoNombre+"',capacidad="+nuevaCapacidad+" \n" +
                "where nombre ='"+nombreSector+"' and sector.refpropiedad="+idPropiedad+" ";
                st.executeUpdate(sql);

                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: añadirCliente" + e);
                return false;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);

            }

        }
        return false;
    }
/**
 * obtiene un sector a traves del nombre del sector  y el identificador de propidad.
 * @param nombreSector: nombre del sector.
 * @param idPropiedad: identificador de una propidad.
 * @throws SQLException 
 */
    public void obtenerInformacionDeUnSector(String nombreSector, int idPropiedad) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from sector where nombre='"+nombreSector+"' and sector.refpropiedad="+idPropiedad+"";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombre = resultado.getString("nombre");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    String refPropietario = resultado.getString("refpropiedad");

                    System.out.println("id: " + idPropiedad + " nombre: " + nombre +  " ref a propietario:" + refPropietario);

                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                //return cliente;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
    }
/**
 * obtiene todos los sectores anexos a una propiedad.
 * @param idPropiedad: identificador de una propiedad.
 * @throws SQLException 
 */
    public void obtenerInformacionDeSectores(int idPropiedad) throws SQLException {
        this.conexion.crearConexion("EventTinder", "1");
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from sector where sector.refpropiedad="+idPropiedad+"";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    String nombre = resultado.getString("nombre");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    String refPropietario = resultado.getString("refpropiedad");

                    System.out.println("id: " + idPropiedad + " nombre: " + nombre +  " ref a propietario:" + refPropietario);

                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                //return cliente;
            } finally {
                this.conexion.cerrarBaseDeDatos(miConexion);
            }

        }
    }

}