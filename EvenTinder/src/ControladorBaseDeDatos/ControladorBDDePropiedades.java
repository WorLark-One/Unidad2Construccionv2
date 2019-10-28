package ControladorBaseDeDatos;

import ModuloGestionPropiedades.Propiedad;
import ModuloGestionPropiedades.Sector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ControladorBDDePropiedades {

    ConexionBD conexion;

    public ControladorBDDePropiedades() {
        this.conexion = new ConexionBD();

        iniciarlizarBD();
    }

    public void iniciarlizarBD() {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        this.conexion.crearTablas(miConexion);
    }

    /**
     * Obtiene una lista con todas las propiedades activas de un propietario.
     *
     * @param rut: rut del propietario
     * @return lista de propiedades
     */
    public ArrayList<Propiedad> obtenerInformacionDePropiedades(String rut) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Propiedad> propiedades = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from propiedad where propiedad.refpropietario='" + rut + "' and propiedad.activa='true'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int idPropiedad = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String ubicacion = resultado.getString("ubicacion");
                    int capacidadtotal = Integer.parseInt(resultado.getString("capacidadtotal"));
                    int valorArriendo = Integer.parseInt(resultado.getString("valorarriendo"));
                    String descripcion = resultado.getString("descripcion");
                    //int numeroSectores = Integer.parseInt(resultado.getString("numerodesectores"));
                    //String refPropietario = resultado.getString("refpropietario");
                    Date fechaPublicacion = resultado.getDate("fechapublicacion");
                    ArrayList<Sector> sectores = obtenerInformacionDeSectores(idPropiedad);
                    boolean activo = resultado.getBoolean("activa");
                    Propiedad propiedad = new Propiedad(idPropiedad, nombre, descripcion, fechaPublicacion, ubicacion, capacidadtotal, valorArriendo);
                    propiedad.setListaSectores(sectores);
                    propiedad.setActiva(activo);
                    propiedades.add(propiedad);
                }
                resultado.close();
                st.close();
                Collections.sort(propiedades);
                return propiedades;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;
    }

    /**
     * Entrega una propiedad, a traves del identificador de la propiedad y rut
     * del propietario.
     *
     * @param rut; rut del dueño de la propiedad.
     * @param id: identificador de la propiedad.
     * @return propiedad si encuentra la propiedad de la que se quiere obtener
     * informacion, null de lo contrario.
     */
    public Propiedad obtenerInformacionDeUnaPropiedad(String rut, int id) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<String> informacion = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from propiedad where propiedad.id=" + id + " and propiedad.refpropietario='" + rut + "'"
                        + "and propiedad.activa='true'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // obtengo la informacion del cliente.
                    int idPropiedad = Integer.parseInt(resultado.getString("id"));
                    String nombre = resultado.getString("nombre");
                    String ubicacion = resultado.getString("ubicacion");
                    int capacidadtotal = Integer.parseInt(resultado.getString("capacidadtotal"));
                    int valorArriendo = Integer.parseInt(resultado.getString("valorarriendo"));
                    String descripcion = resultado.getString("descripcion");
                    boolean activo = resultado.getBoolean("activa");
                    //int numeroSectores = Integer.parseInt(resultado.getString("numerodesectores"));
                    //String refPropietario = resultado.getString("refpropietario");
                    Date fechaPublicacion = resultado.getDate("fechapublicacion");
                    ArrayList<Sector> sectores = obtenerInformacionDeSectores(idPropiedad);
                    Propiedad propiedad = new Propiedad(idPropiedad, nombre, descripcion, fechaPublicacion, ubicacion, capacidadtotal, valorArriendo);
                    propiedad.setListaSectores(sectores);
                    propiedad.setActiva(activo);
                    return propiedad;
                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;

    }

    /**
     * Registra una propiedad en la base de datos, para que el registro sea
     * exitoso se debe haber creado anteriormente un propietario con el rut al
     * cual se le hara la referencia.
     *
     * @param rut: rut del propietario
     * @param nombre :nombre de la propiedad
     * @param descripcion :descripcion de la propiedad
     * @param fechaDePublicacion : fecha de publicacion de la propiedad.
     * @param capacidadTotal: numero de personas que puede contener la
     * propiedad.
     * @param ubicacion :ubicacion de la propiedad
     * @param valorDeArriendo: valor del arriendo de la propiedad
     * @return id de la propiedad creada, 0 si no crea la propiedad
     */
    public int registrarPropiedad(String rut, String nombre, String ubicacion, Date fechaDePublicacion, int capacidadTotal, int valorDeArriendo, String descripcion) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "insert into propiedad values(DEFAULT,'" + nombre + "','" + ubicacion + "','" + fechaDePublicacion + "','" + capacidadTotal + "','" + valorDeArriendo + "','" + descripcion + "','" + rut + "')"
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
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    //Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        //System.out.println("wololoooo");
        return 0;
    }

    /**
     * Modifica la informacion de una propiedad. // si se modifica el valor del
     * arriendo, la capacidad total o el numero de sectores se debe crear una
     * nueva instancia.
     *
     * @param id: identificador de la propiedad.
     * @param nuevoNombre: nuevo nombre de la propiedad.
     * @param NuevaUbicacion: nueva ubicacion de la propiedad.
     * @param fechaDePublicacion: nueva fecha publicacion de la propiedad.
     * @param nuevaCapacidadTotal: nueva capacidad de la propiedad.
     * @param nuevoValorDeArriendo: nuevo valor de arriendo de la propiedad.
     * @param nuevaDescripcion: nueva descripcion de la propiedad.
     * @param rutPropietario: rut del propietario.
     * @return true si modifica la informacion de la propiedad, false de lo
     * contrario.
     */
    public boolean modifcarPropiedad(int id, String nuevoNombre, String NuevaUbicacion, Date fechaDePublicacion, int nuevaCapacidadTotal, int nuevoValorDeArriendo, String nuevaDescripcion, String rutPropietario) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean propiedadActiva = verificarSiLaPropiedadEstaActiva(miConexion, id);
            boolean verificarEventosPublicadosAsociados = verificarEventosPublicadosAsociadoPropiedad(miConexion, id);
            boolean verificaEventosFinalizadosAsociados = verificarEventosFinalizadossAsociadoPropiedad(miConexion, id);
            boolean crearNuevaInstancia = VerificarCambiosPropiedad(miConexion, id, nuevaCapacidadTotal, nuevoValorDeArriendo);
            System.out.println("propiedad activa:" + propiedadActiva);
            System.out.println("eventos publicados :" + verificarEventosPublicadosAsociados);
            System.out.println("eventos finalizados:" + verificaEventosFinalizadosAsociados);
            System.out.println("crear una nueva instancia:" + crearNuevaInstancia);
            if (propiedadActiva == true) {
                //si hay eventos publicados activos, No se puede modificar la propiedad.
                if (verificarEventosPublicadosAsociados == false) {
                    try {
                        //si hay eventos finalizados y se modifican los valores de capacidad y valor de arriendo
                        // necesito crear una nueva instancia de propiedad.
                        if (verificaEventosFinalizadosAsociados == true && crearNuevaInstancia == true) {
                            System.out.println("CREAR UNA NUEVA INSTANCIA DE LA PROPIEDAD");
                            int nuevoIdPropiedad = crearUnaNuevaInstanciaDePropiedad(nuevoNombre, NuevaUbicacion, fechaDePublicacion, nuevaCapacidadTotal, nuevoValorDeArriendo, nuevaDescripcion, rutPropietario);
                            crearNuevosSectoresAnexosPropiedad(id, nuevoIdPropiedad);
                            desabilitarPropiedad(miConexion, id);
                            this.conexion.cerrarBaseDeDatos(miConexion);
                            return true;
                        } else if (verificaEventosFinalizadosAsociados == true && crearNuevaInstancia == false) {// no es necesario crear una nueva instancia, solo necesitamos modificar los valores.
                            System.out.println("ACTUALIZAR LOS REGISTROS DE LA PROPIEDAD.");
                            boolean act = actualizarRegistroPropiedad(miConexion, id, nuevoNombre, NuevaUbicacion, fechaDePublicacion, nuevaCapacidadTotal, nuevoValorDeArriendo, nuevaDescripcion);
                            this.conexion.cerrarBaseDeDatos(miConexion);
                            return act;
                        } else {
                            System.out.println("NO HAY EVENTOS FINALAZADOS");
                            System.out.println("ACTUALIZAR LOS REGISTROS DE LA PROPIEDAD.");
                            boolean act = actualizarRegistroPropiedad(miConexion, id, nuevoNombre, NuevaUbicacion, fechaDePublicacion, nuevaCapacidadTotal, nuevoValorDeArriendo, nuevaDescripcion);
                            this.conexion.cerrarBaseDeDatos(miConexion);
                            return act;
                        }
                    } catch (SQLException ex) {
                        //error al cerrar la base de datos.
                    }

                }
                System.out.println("No se puede modificar una propiedad con eventos publicados.");
                return false;
                //no se pueden modificar las propiedades que tienen eventos publicados.

            }
            return false;

        }
        try {
            this.conexion.cerrarBaseDeDatos(miConexion);
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Elimina una propiedad de la base de datos.
     *
     * @param idPropiedad: identificador de la propiedad.
     * @return true si se elimina la propiedad, false de lo contrario.
     */
    public boolean eliminarPropiedad(int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean propiedadActiva = verificarSiLaPropiedadEstaActiva(miConexion, idPropiedad);
            boolean verificarEventosPublicadosAsociados = verificarEventosPublicadosAsociadoPropiedad(miConexion, idPropiedad);
            boolean verificaEventosFinalizadosAsociados = verificarEventosFinalizadossAsociadoPropiedad(miConexion, idPropiedad);
            System.out.println("propiedad activa:"+propiedadActiva);
            System.out.println("verificar eventos publicados:"+verificarEventosPublicadosAsociados);
            System.out.println("verificar eventos finalizados:"+verificaEventosFinalizadosAsociados);
            if (propiedadActiva == true) {
                try {
                    if (verificarEventosPublicadosAsociados == false) {
                        if (verificaEventosFinalizadosAsociados == true) {
                            System.out.println("desabilitar propiedad");
                            desabilitarSectoresAnexosPropiedad(miConexion, idPropiedad);
                            desabilitarPropiedad(miConexion, idPropiedad);
                            this.conexion.cerrarBaseDeDatos(miConexion);
                            return true;
                        } else {// se puede eliminar directamente la propiedad
                            System.out.println("Eliminar propiedad");
                            eliminarRegistroDePropiedad(miConexion, idPropiedad);
                            this.conexion.cerrarBaseDeDatos(miConexion);
                            return true;
                        }
                    }
                    System.out.println("HAY EVENTOS PUBLICADOS EN LA PROPIEDAD.");
                    return false;

                } catch (Exception e) {
                }

                //no se puede eliminar las propiedades que tienen eventos publicados.
            }
        }
        try {
            this.conexion.cerrarBaseDeDatos(miConexion);
        } catch (SQLException ex) {
            //Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Registra un sector en la base de datos, siempre y cuando la propiedad
     * este activa.
     *
     * @param nombreSector:nombre del sector.
     * @param capacidad: capacidad del sector.
     * @param idPropiedad: identificador de la propiedad a la cual pertenece el
     * sector.
     * @return true si registra el sector satisfactoriamente ,false de lo
     * contrario
     */
    public boolean registrarSector(String nombreSector, int capacidad, int idPropiedad) {

        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        if (miConexion != null) {
            boolean propiedadActiva = verificarSiLaPropiedadEstaActiva(miConexion, idPropiedad);
            if (propiedadActiva == true) {
                try {

                    java.sql.Statement st = miConexion.createStatement();
                    String sql = "insert into sector values('" + nombreSector + "'," + capacidad + "," + idPropiedad + ")";
                    st.executeUpdate(sql);
                    st.close();
                    this.conexion.cerrarBaseDeDatos(miConexion);
                    return true;

                } catch (SQLException e) {
                    try {
                        //System.out.println("Error al añadir un sector.");
                        this.conexion.cerrarBaseDeDatos(miConexion);
                    } catch (SQLException ex) {
                        // Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return false;

                }
            }

        }
        return false;
    }

    /**
     * Elimina un sector de la base de datos, a traves de nombre y el
     * identificador de propiedad del sector
     *
     * @param nombreSector: nombre del sector
     * @param idPropiedad: identificador de la propiedad a la cual pertenece el
     * sector
     * @return
     * @return: true si borra el sector, false de lo contrario.
     */
    public boolean eliminarSector(String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null) {
            boolean propiedadActiva = verificarSiLaPropiedadEstaActiva(miConexion, idPropiedad);
            //entradas son aquellas compradas por algun cliente, no las que se generar
            //para ponerle precio a un sector.
            boolean verificarEntradasAsociado = verificarEntradasAsociadoSector(miConexion, nombreSector, idPropiedad);
            System.out.println("propiedad activa:" + propiedadActiva);
            System.out.println("tiene entradas asociadas al sector:" + verificarEntradasAsociado);
            try {
                if (propiedadActiva == true) {
                    //no hay entradas asociadas al sector, por lo tanto se puede eliminar. .
                    if (verificarEntradasAsociado == false) {
                        System.out.println("ELIMINAR REGISTRO DEL SECTOR.");
                        EliminarRegistroSector(miConexion, idPropiedad, nombreSector);
                        this.conexion.cerrarBaseDeDatos(miConexion);
                        return true;
                    } else {// hay entradas asociadas al sector, se desactiva el sector..
                        System.out.println("DESABILITAR SECTOR");
                        boolean des = desabilitarSector(miConexion, idPropiedad, nombreSector);
                        this.conexion.cerrarBaseDeDatos(miConexion);
                        return des;
                    }
                    //no se pueden modificar las propiedades que tienen eventos publicados.
                }
                this.conexion.cerrarBaseDeDatos(miConexion);
                return false;
            } catch (SQLException ex) {
                //error al cerrar la base de datos.
            }
        }
        return false;
    }

    /**
     * Modifca la informacion de un sector.
     *
     * @param nombreSector
     * @param idPropiedad
     * @param nuevoNombre
     * @param nuevaCapacidad
     * @return
     */
    public boolean modificarSector(String nombreSector, int idPropiedad, String nuevoNombre, int nuevaCapacidad) {
        try {
            this.conexion.crearConexion();
            Connection miConexion = this.conexion.getConexion();
            if (miConexion != null) {
                boolean propiedadActiva = verificarSiLaPropiedadEstaActiva(miConexion, idPropiedad);
                boolean verificarEntradasAsociadosAlSector = verificarEntradasAsociadoSector(miConexion, nombreSector, idPropiedad);
                boolean crearNuevaInstancia = verificarDatosDeSector(miConexion, idPropiedad, nombreSector, nuevaCapacidad);
                System.out.println("propiedad activa:" + propiedadActiva);
                System.out.println("entradas asociadas al sector:" + verificarEntradasAsociadosAlSector);
                System.out.println("crear nueva instancia:" + crearNuevaInstancia);
                if (propiedadActiva == true) {
                    //no hay entras asociadas al sector, por lo tanto puedo modificar.
                    if (verificarEntradasAsociadosAlSector == true) {
                        if (crearNuevaInstancia == true) {
                            System.out.println("CREAR UNA NUEVA INSTANCIA DE SECTOR");
                            desabilitarSector(miConexion, idPropiedad, nombreSector);
                            crearNuevaInstanciaSector(nombreSector, idPropiedad, nuevoNombre, nuevaCapacidad);
                            this.conexion.cerrarBaseDeDatos(miConexion);
                            return true;
                        } else {
                            //solo modificamos los datos del sector
                            System.out.println("MODIFICAR SECTOR");
                            modificarDatosSector(miConexion, nombreSector, idPropiedad, nuevoNombre, nuevaCapacidad);
                            this.conexion.cerrarBaseDeDatos(miConexion);
                            return true;
                        }
                    }//no se pueden modificar las propiedades que tienen eventos publicados.
                    return false;
                }
                return false;
            }

        } catch (SQLException ex) {
            //error en la sentencia sql.
            return false;
        }
        return false;

    }

    /**
     * Obtiene un sector a traves del nombre del sector y el identificador de
     * propidad.
     *
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de una propidad.
     * @return
     * @throws SQLException
     */
    public Sector obtenerInformacionDeUnSector(String nombreSector, int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select sector.nombre,sector.capacidad,sector.refpropiedad\n"
                        + "from sector\n"
                        + "inner join propiedad on sector.refpropiedad = propiedad.id\n"
                        + "where sector.nombre='" + nombreSector + "' and sector.refpropiedad=" + idPropiedad + " and propiedad.activa='true'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int refPropiedad = Integer.parseInt(resultado.getString("refpropiedad"));

                    Sector sector = new Sector(refPropiedad, nombre, capacidad);
                    resultado.close();
                    st.close();
                    this.conexion.cerrarBaseDeDatos(miConexion);
                    return sector;
                }

            } catch (SQLException e) {
                System.out.println("ERROR AL OBTENER INFORMACION DE UN SECTOR");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return null;

    }

    /**
     * Obtiene todos los sectores anexos a una propiedad.
     *
     * @param idPropiedad: identificador de una propiedad.
     * @return
     */
    public ArrayList<Sector> obtenerInformacionDeSectores(int idPropiedad) {
        this.conexion.crearConexion();
        Connection miConexion = this.conexion.getConexion();

        ArrayList<Sector> sectores = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select sector.nombre,sector.capacidad,sector.refpropiedad\n"
                        + "from sector\n"
                        + "inner join propiedad on sector.refpropiedad = propiedad.id\n"
                        + "where  sector.refpropiedad=" + idPropiedad + " and propiedad.activa='true'"
                        + "and sector.activa='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    int capacidad = Integer.parseInt(resultado.getString("capacidad"));
                    int refPropiedad = Integer.parseInt(resultado.getString("refpropiedad"));

                    Sector sector = new Sector(refPropiedad, nombre, capacidad);
                    sectores.add(sector);
                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                System.out.println("ERROR AL OBTENER INFORMACION DE SECTORES.");
                return null;
            } finally {
                try {
                    this.conexion.cerrarBaseDeDatos(miConexion);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorBDDePropiedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        Collections.sort(sectores);
        return sectores;
    }

    /**
     * verifica si hay eventos asociados a una propiedad.
     *
     * @param miConexion:conexion con la base de datos.
     * @param idPropiedad : identificador de una propiedad..
     * @return
     */
    private boolean verificarEventosPublicadosAsociadoPropiedad(Connection miConexion, int idPropiedad) {
        if (miConexion != null) {
            try {
                java.util.Date fecha = new Date();
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select propiedad.nombre\n"
                        + "from celebra\n"
                        + "inner join evento ON celebra.refevento = evento.id\n"
                        + "inner join propiedad on celebra.refpropiedad=propiedad.id\n"
                        + "where evento.activo='true' and evento.publicado='true' and propiedad.activa='true'\n"
                        + "and propiedad.id=" + idPropiedad + ""
                        + "and evento.fechatermino>='" + fecha + "'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // si entra en el ciclo significa que hay datos.
                    return true;
                }
                st.close();
                return false;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                return false;
            }
        }
        return false;
    }

    /**
     * Metodo privado.
     * Verifica si una propiedad esta activa.
     * @param miConexion: conexión con la BD
     * @param idPropiedad: identificador de la propeidad.
     * @return 
     */
    private boolean verificarSiLaPropiedadEstaActiva(Connection miConexion, int idPropiedad) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select propiedad.nombre from propiedad where propiedad.activa='true' and propiedad.id=" + idPropiedad + "";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // si entra en el ciclo significa que hay datos.
                    return true;
                }
                st.close();
                return false;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                return false;
            }
        }
        return false;
    }

    /**
     * Verifica si hay eventos finalizados anexos a una propiedad.
     *
     * @param miConexion:conexion con la base de datos.
     * @param idPropiedad:indentificador de una propiedad.
     * @return
     */
    private boolean verificarEventosFinalizadossAsociadoPropiedad(Connection miConexion, int idPropiedad) {
        if (miConexion != null) {
            try {
                java.util.Date fecha = new Date();
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select DISTINCT evento.nombre\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where propiedad.id=" + idPropiedad + " and evento.fechatermino <= '" + fecha + "'::date\n"
                        + "and propiedad.activa='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // si entra en el ciclo significa que hay datos.
                    return true;
                }
                st.close();
                return false;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                return false;
            }
        }
        return false;
    }

    /**
     * Verifica si los datos de capacidad total y valor de arriendo son los
     * mismos que estan registrados en la base de datos.
     *
     * @param miConexion: conexion BD
     * @param id:identificador de una propiedad
     * @param nuevaCapacidadTotal: capacidad total de la propiedad.
     * @param nuevoValorDeArriendo: valor de arriendo de la propiedad.
     * @return
     */
    private boolean VerificarCambiosPropiedad(Connection miConexion, int id, int nuevaCapacidadTotal, int nuevoValorDeArriendo) {
        if (miConexion != null) {
            try {
                java.util.Date fecha = new Date();
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * from propiedad where propiedad.id=1 and propiedad.activa='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    int capacidadtotal = Integer.parseInt(resultado.getString("capacidadtotal"));
                    int valorArriendo = Integer.parseInt(resultado.getString("valorarriendo"));

                    if (capacidadtotal != nuevaCapacidadTotal || valorArriendo != nuevoValorDeArriendo) {
                        return true;
                    }

                }
                st.close();
                return false;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                return false;
            }
        }
        return false;
    }

    private int crearUnaNuevaInstanciaDePropiedad(String nuevoNombre, String NuevaUbicacion, Date fechaDePublicacion, int nuevaCapacidadTotal, int nuevoValorDeArriendo, String nuevaDescripcion, String rutPropietario) {
        return registrarPropiedad(rutPropietario, nuevoNombre, NuevaUbicacion, fechaDePublicacion, nuevaCapacidadTotal, nuevoValorDeArriendo, nuevaDescripcion);
    }

    /**
     * Metodo privado
     * Desabilita una propiedad.
     *
     * @param miConexion:conexion BD
     * @param id : identificador de una propiedad.
     */
    private void desabilitarPropiedad(Connection miConexion, int id) {
        if (miConexion != null) {

            try {
                desabilitarSectoresAnexosPropiedad(miConexion, id);
                java.sql.Statement st = miConexion.createStatement();
                String sql = " update propiedad set activa='false' where propiedad.id=" + id + " and propiedad.activa='true'";
                System.out.println(sql);
                st.executeUpdate(sql);
                System.out.println("desabilito la propiedad");
                //System.out.println(sql);
                st.close();
                //return true;

            } catch (SQLException e) {
                //System.out.println("ERROR AL MODIFICAR LA INFORMACION DE UNA PROPIEDAD" + e);
                //return false;
            }

        }
        //return false;
    }
    /**
     * Actualiza los registros de uns propiedad.
     * @param miConexion: conexión con la BD
     * @param id: identificador de la propiedad.
     * @param nuevoNombre: nombre de la propiedad.
     * @param NuevaUbicacion: ubicación de la propiedad.
     * @param fechaDePublicacion :fecha de publicación de la propiedad.
     * @param nuevaCapacidadTotal: capacidad total de la propiedad.
     * @param nuevoValorDeArriendo: valor de arriendo de la propiedad.
     * @param nuevaDescripcion: descripcion de la propiedad.
     * @return 
     */
    private boolean actualizarRegistroPropiedad(Connection miConexion, int id, String nuevoNombre, String NuevaUbicacion, Date fechaDePublicacion, int nuevaCapacidadTotal, int nuevoValorDeArriendo, String nuevaDescripcion) {
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = " UPDATE propiedad SET nombre='" + nuevoNombre + "', ubicacion='" + NuevaUbicacion + "',fechapublicacion='" + fechaDePublicacion + "',capacidadtotal='" + nuevaCapacidadTotal + "',\n"
                        + "valorarriendo='" + nuevoValorDeArriendo + "',descripcion='" + nuevaDescripcion + "'"
                        + "where propiedad.id=" + id + " ";
                st.executeUpdate(sql);
                st.close();
                return true;

            } catch (SQLException e) {
                System.out.println("ERROR AL MODIFICAR LA INFORMACION DE UNA PROPIEDAD" + e);
                return false;
            }

        }
        return false;
    }

    /**
     * Metodo privado.
     * Elimina Los datos de una propiedad de la BD
     * @param miConexion
     * @param idPropiedad 
     */
    private void eliminarRegistroDePropiedad(Connection miConexion, int idPropiedad) {
        if (miConexion != null) {
            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from propiedad where propiedad.id=" + idPropiedad + " ";
                st.executeUpdate(sql);
                //System.out.println(sql);
                st.close();
                //return true;

            } catch (SQLException e) {
                //System.out.println("ERROR AL MODIFICAR LA INFORMACION DE UNA PROPIEDAD" + e);
                //return false;
            }

        }
    }
    /**
     * Desabilita un Sector.
     * @param miConexion: conexión con la BD
     * @param idPropiedad: identificador de la propiedad.
     * @param nombreSector: nombre del sector.
     * @return true si realiza la accion con exito, false de lo contrario.
     */
    private boolean desabilitarSector(Connection miConexion, int idPropiedad, String nombreSector) {
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "update sector set activa='false' where sector.nombre='" + nombreSector + "' and sector.refpropiedad=" + idPropiedad + "";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR AL ELIMINAR UN SECTOR: )" + e);
                return false;
            }

        }
        return false;
    }
    /**
     * Metodo privado.
     * Verifica si un sector tiene entradas asociadas.
     * @param miConexion: conexión con la BD
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de la propiedad.
     * @return true si tiene entradas asociadas, false de lo contrario.
     */
    private boolean verificarEntradasAsociadoSector(Connection miConexion, String nombreSector, int idPropiedad) {
        if (miConexion != null) {
            try {
                java.util.Date fecha = new Date();
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select DISTINCT evento.nombre\n"
                        + "from evento\n"
                        + "inner join celebra on evento.id = celebra.refevento\n"
                        + "inner join propiedad on celebra.refpropiedad = propiedad.id\n"
                        + "where propiedad.id=" + idPropiedad + " and evento.fechatermino <= '" + fecha + "'::date\n"
                        + "and propiedad.activa='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // si entra en el ciclo significa que hay datos.
                    return true;
                }
                st.close();
                return false;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                return false;
            }
        }
        return false;
    }
    /**
     * Elimina los registros de un sector de la BD
     * @param miConexion: conexion con la BD
     * @param idPropiedad: identificador de la propiedad.
     * @param nombreSector:nombre del sector.
     * @return true si realiza la operacion, false de lo contrario.
     */
    private boolean EliminarRegistroSector(Connection miConexion, int idPropiedad, String nombreSector) {
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "delete from sector where sector.nombre='" + nombreSector + "' and sector.refpropiedad=" + idPropiedad + "  ";
                st.executeUpdate(sql);
                //System.out.println(sql);
                st.close();
                return true;

            } catch (SQLException e) {
                //System.out.println("ERROR AL MODIFICAR LA INFORMACION DE UNA PROPIEDAD" + e);
                return false;
            }

        }
        return false;
    }
    /**
     * Verifica si los datos de un sector, cambian respecto a los
     * ya registrados en la base de datos.
     * @param miConexion: conexión con la BD.
     * @param idPropiedad:identificador de la propiedad.
     * @param nombreSector: nombre del sector.
     * @param nuevaCapacidad: capacidad del sector.
     * @return true si los datos no son iguales a los ya registrados,false de lo contrario.
     */
    private boolean verificarDatosDeSector(Connection miConexion, int idPropiedad, String nombreSector, int nuevaCapacidad) {
        if (miConexion != null) {
            try {
                java.util.Date fecha = new Date();
                java.sql.Statement st = miConexion.createStatement();
                String sql = "select * \n"
                        + "from sector\n"
                        + "where sector.nombre='" + nombreSector + "' and sector.refpropiedad=" + idPropiedad + " and sector.activa='true'";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    // si entra en el ciclo significa que hay datos.
                    int capacidad = resultado.getInt("capacidad");
                    if (capacidad == nuevaCapacidad) {
                        st.close();
                        return true;
                    }

                }
                st.close();
                return false;
            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: contar usuario :" + e);
                return false;
            }
        }
        return false;
    }
    /**
     * Metodo privado.
     * Crea un nuevo sector, con los mismos datos, aunque cambia el identificador de este.
     * @param nombreSector:: nombre del sector.
     * @param idPropiedad:identificador de la propiedad a la que esta asociado el sector..
     * @param nuevoNombre: nuevo nombre del sector..
     * @param nuevaCapacidad : nueva capacidad del sector.
     */
    private void crearNuevaInstanciaSector(String nombreSector, int idPropiedad, String nuevoNombre, int nuevaCapacidad) {
        registrarSector(nombreSector, nuevaCapacidad, idPropiedad);
    }
    /**
     * Metodo privado.
     * Modifica los datos de un sector.
     * @param miConexion: conexión con la BD.
     * @param nombreSector: nombre del sector.
     * @param idPropiedad:identificador de la propiedad a la cual esta asociado.
     * @param nuevoNombre: nuevo nombre del sector.
     * @param nuevaCapacidad: nueva capacidad del sector.
     */
    private void modificarDatosSector(Connection miConexion, String nombreSector, int idPropiedad, String nuevoNombre, int nuevaCapacidad) {
        if (miConexion != null) {

            try {

                java.sql.Statement st = miConexion.createStatement();
                String sql = "update  sector set capacidad=" + nuevaCapacidad + " , nombre='" + nuevoNombre + "' where sector.nombre='" + nombreSector + "'\n"
                        + "and sector.refpropiedad=" + idPropiedad + "\n"
                        + "and sector.activa='true'";
                st.executeUpdate(sql);
                //System.out.println(sql);
                st.close();
                //return true;

            } catch (SQLException e) {
                System.out.println("ERROR AL MODIFICAR LA INFORMACION DE UNA PROPIEDAD" + e);
                //return false;
            }

        }
        //return false;
    }
    /**
     * Metodo privado.
     * Desabilita todos los sectores anexos a una propiedad.
     * @param miConexion: conexion con la BD.
     * @param idPropiedad :identificador de una propiedad.
     */
    private void desabilitarSectoresAnexosPropiedad(Connection miConexion, int idPropiedad) {
        if (miConexion != null) {
            ArrayList<Sector> lista = obtenerInformacionDeSectores(idPropiedad);
            if (lista != null) {
                for (int i = 0; i < lista.size(); i++) {
                    Sector sector = lista.get(i);
                    String nombre = sector.getNombre();
                    int idPro = sector.getIdPropiedad();
                    try {

                        java.sql.Statement st = miConexion.createStatement();
                        String sql = "update  sector set activa='false' where sector.nombre='" + nombre + "' \n"
                                + "and sector.refpropiedad=" + idPro + "";
                        st.executeUpdate(sql);
                        st.close();
                        //return true;

                    } catch (SQLException e) {
                        //System.out.println("ERROR AL MODIFICAR LA INFORMACION DE UNA PROPIEDAD" + e);
                        //return false;
                    }
                }
                //System.out.println("no hay sectores anexos a la propiedad:" + idPropiedad);
            }

        }
        //return false;
    }
    /**
     * Crea una copiea de los sectores anexos de una propiedad a otra propiedad.
     * @param idPropiedad: identificador de una propiedad.
     * @param nuevoIdPropiedad : nuevo identificador de la propiedad.
     */
    private void crearNuevosSectoresAnexosPropiedad(int idPropiedad, int nuevoIdPropiedad) {
        ArrayList<Sector> listaSectores = obtenerInformacionDeSectores(idPropiedad);
        if (listaSectores != null) {
            for (int i = 0; i < listaSectores.size(); i++) {
                Sector sector = listaSectores.get(i);
                String nombre = sector.getNombre();
                int capacidad = sector.getCapacidadDelSector();
                registrarSector(nombre, capacidad, nuevoIdPropiedad);

            }
        }
    }

}
