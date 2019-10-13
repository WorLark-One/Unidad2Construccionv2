package ControladorBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase para conectar la base de datos y java. modo de uso. 1.se crea una nueva
 * instancia, solo una vez. 2.se crea la conexion con la base de datos. 3.se
 * obtienen la clase de datos. 4.cada metodo que esta en esta clase, nececita
 * una instaciona de conexion "activa" por lo cual es necesario que se le pase
 * como parametro.
 */
public class ConexionBD {

    private Connection conexion;
    String contraseñaBD;
    String nombreBD;

    public ConexionBD() {
        conexion = null;
        this.nombreBD = "EvenTinder";
        this.contraseñaBD = "1";
    }

    public Connection getConexion() {
        return conexion;
    }

    /**
     * Crea la conexion con la base de datos, recibe como parametro el nombre de
     * la base de datos y contraseña que tienes como usuario de postgres.
     *
     * @return true si se realiza la conexion, false de lo contrario
     */
    public boolean crearConexion() {
        String url = "jdbc:postgresql://localhost:5432/" + this.nombreBD + "";// la url incluye el puerto y nombre del proyecto
        String password = "" + this.contraseñaBD + "";// contraseña de postgres
        String usuario = "postgres";
        try {
            Class.forName("org.postgresql.Driver");

            conexion = DriverManager.getConnection(url, usuario, password);

            if (conexion != null) {
                //System.out.println("CONEXION EXITOSA: crearConexion().");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA CONEXION: crearConexion() : " + ex);
        } catch (ClassNotFoundException ex) {
            //System.out.println(ex);
        }
        return false;
    }

    /**
     * cierra la conexion con la base de datos
     *
     * @param conexionAux
     * @throws SQLException
     */
    public void cerrarBaseDeDatos(Connection conexionAux) throws SQLException {

        if (conexionAux != null) {
            conexionAux.close();

        }
    }
    /**
     * verifica si la base de datos fue creada.
     * @param conexion
     * @return 
     */
    public boolean verificarBD(Connection conexion) {
        ArrayList<String> listaNombresBD = new ArrayList<>();
        Connection miConexion = conexion;
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "select * from pg_database where datname='" + this.nombreBD + "'";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombre = resultado.getString("datname");
                    if (nombre.equalsIgnoreCase(this.nombreBD) == true) {
                        return true;
                    }

                }
                resultado.close();
                st.close();

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return false;
            }

        }
        return false;

    }

    public ArrayList<String> obtenerListaTablas(Connection conexion) {
        Connection miConexion = conexion;

        ArrayList<String> listaTablas = new ArrayList<>();
        if (miConexion != null)// si hay conexion.
        {

            try {
                java.sql.Statement st = miConexion.createStatement();

                String sql = "SELECT t.table_name FROM information_schema.tables t WHERE t.table_schema = 'public' ORDER BY t.table_name";

                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    String nombre = resultado.getString("table_name");
                    listaTablas.add(nombre);

                }
                resultado.close();
                st.close();
                return listaTablas;

            } catch (SQLException e) {
                //System.out.println("ERROR DE CONEXION: mostrarIndormacionCliente()");
                return null;
            }

        }
        return null;
    }
    /**
     * crea las tablas de la base de datos, siempre que no esten previamente
     * creadas.
     * @param conexion 
     */
    public void crearTablas(Connection conexion) {
        crearTablaCliente(conexion);
        crearTablaOrganizador(conexion);
        crearTablaPropietario(conexion);
        crearTablaEvento(conexion);
        crearTablaPropiedad(conexion);
        crearTablaSector(conexion);
        crearTablaEntrada(conexion);
        crearTablaAsociacion(conexion);
        crearTablaCompra(conexion);
        crearTablaCelebra(conexion);
    }

    // creacion de tablas
    public boolean crearTablaCliente(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = " CREATE TABLE IF NOT EXISTS Cliente(\n"
                        + "	nombreCompleto varchar(100),\n"
                        + "	rut  varchar(10) not null,\n"
                        + "	correo varchar(50),\n"
                        + "	contraseña varchar(25),\n"
                        + "	telefono varchar(20),\n"
                        + "	tarjetaCredito varchar(16) not null,\n"
                        + "	PRIMARY KEY(rut)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla cliente");
                return false;
            }
        }
        return false;

    }

    public boolean crearTablaOrganizador(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Organizador(\n"
                        + "nombreCompleto varchar(100),\n"
                        + "rut  varchar(10) not null,\n"
                        + "correo varchar(50),\n"
                        + "contraseña varchar(25),\n"
                        + "telefono varchar(20),\n"
                        + "tarjetaCredito varchar(16) not null,\n"
                        + "PRIMARY KEY(rut)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla organizador");
                return false;
            }
        }
        return false;

    }

    public boolean crearTablaPropietario(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Propietario(\n"
                        + "	nombreCompleto varchar(100),\n"
                        + "	rut  varchar(10) not null,\n"
                        + "	correo varchar(50),\n"
                        + "	contraseña varchar(25),\n"
                        + "	telefono varchar(20),\n"
                        + "	cuentaCorriente varchar(20) not null,\n"
                        + "	PRIMARY KEY(rut)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla propietario");
                return false;
            }
        }
        return false;

    }

    public boolean crearTablaEvento(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Evento(\n"
                        + "	id SERIAL NOT NULL,\n"
                        + "	nombre varchar(100),\n"
                        + "	descripcion text,\n"
                        + "	fechaInicio date,\n"
                        + "	fechaTermino date,\n"
                        + "	capacidad integer,\n"
                        + "	PlazoDevolucionEntradas integer,\n"
                        + "	publicado boolean,\n"
                        + "	refOrganizador varchar(10),\n"
                        + "	PRIMARY KEY(id) \n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla evento");
                return false;
            }
        }
        return false;

    }

    public boolean crearTablaPropiedad(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Propiedad(\n"
                        + "	id SERIAL NOT NULL,\n"
                        + "	nombre varchar(100),\n"
                        + "	ubicacion varchar(100),\n"
                        + "	fechaPublicacion date,\n"
                        + "	capacidadTotal integer,\n"
                        + "	valorArriendo integer,\n"
                        + "	descripcion varchar(100),\n"
                        + "	numeroDeSectores integer,\n"
                        + "	refPropietario varchar(10),\n"
                        + "	PRIMARY KEY(id),\n"
                        + "	FOREIGN KEY (refPropietario) references Propietario(rut) ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla propiedad");
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaSector(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Sector(\n"
                        + "	nombre varchar(100) not null,\n"
                        + "	capacidad integer,\n"
                        + "	refPropiedad integer not null,\n"
                        + "	PRIMARY KEY(nombre,refPropiedad),\n"
                        + "	FOREIGN KEY (refPropiedad) references Propiedad(id)ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla sector");
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaEntrada(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Entrada(\n"
                        + "	id SERIAL,\n"
                        + "	vendida boolean,\n"
                        + "	PRIMARY KEY(id)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla entrada");
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaAsociacion(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Asociacion(\n"
                        + "	refEvento integer,\n"
                        + "	refEntrada integer,\n"
                        + "	precio integer,\n"
                        + "	refSector varchar(100),\n"
                        + "	refPropiedad integer,\n"
                        + "	PRIMARY KEY(refEvento,refEntrada,refSector),\n"
                        + "	FOREIGN KEY (refEvento) references Evento(id)ON DELETE CASCADE,\n"
                        + "	FOREIGN KEY (refEntrada) references Entrada(id)ON DELETE CASCADE,\n"
                        + "	FOREIGN KEY (refSector,refPropiedad) references Sector(nombre,refPropiedad)ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla asociacion");
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaCompra(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Compra(\n"
                        + "	refCliente varchar(10) not null,\n"
                        + "	refEntrada integer not null,\n"
                        + "	PRIMARY KEY(refCliente,refEntrada),\n"
                        + "	FOREIGN KEY (refEntrada) references Entrada(id)ON DELETE CASCADE,\n"
                        + "	FOREIGN KEY (refCliente) references Cliente(rut)ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla compra");
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaCelebra(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS Celebra(\n"
                        + "	refEvento integer,\n"
                        + "	refPropiedad integer,\n"
                        + "	PRIMARY KEY(refEvento,refPropiedad),\n"
                        + "	FOREIGN KEY (refEvento) references Evento(id)ON DELETE CASCADE,\n"
                        + "	FOREIGN KEY (refPropiedad) references Propiedad(id)ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                System.out.println("ERROR DE crear tabla celebra");
                return false;
            }
        }
        return false;
    }

}
