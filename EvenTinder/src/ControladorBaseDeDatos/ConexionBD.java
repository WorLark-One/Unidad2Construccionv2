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
     *
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
    /**
     * Obtiene todas las tablas en la BD.
     * @param conexion
     * @return 
     */
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
     *
     * @param conexion
     */
    public void crearTablas(Connection conexion) {
        crearTablaCliente(conexion);
        crearTablaOrganizador(conexion);
        crearTablaPropietario(conexion);
        crearTablaEvento(conexion);
        crearTablaPropiedad(conexion);
        crearTablaSector(conexion);
        crearTablaCelebra(conexion);
        crearTablaCompra(conexion);
        crearTablaEntrada(conexion);
        crearTablaIntanciaEntrada(conexion);
        crearTablaAsociarEventoEntradaSector(conexion);
        crearTablaRealizaCompra(conexion);

    }

    // creacion de tablas
    public boolean crearTablaCliente(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE Cliente(\n"
                        + "	nombreCompleto varchar(100)not null,\n"
                        + "	rut  varchar(12) not null,\n"
                        + "	correo varchar(50)not NULL,\n"
                        + "	contraseña varchar(25)not null,\n"
                        + "	telefono varchar(10)not NULL,\n"
                        + "	tarjetaCredito varchar(20) not null,\n"
                        + "	activo boolean NOT null default 'true',\n"
                        + "	PRIMARY KEY(rut)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla cliente");
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
                String sql = "CREATE TABLE Organizador(\n"
                        + "	nombreCompleto varchar(100)not null,\n"
                        + "	rut  varchar(12) not null,\n"
                        + "	correo varchar(50)not null,\n"
                        + "	contraseña varchar(25)not null,\n"
                        + "	telefono varchar(10)not null,\n"
                        + "	tarjetaCredito varchar(20) not null,\n"
                        + "	activo boolean NOT null default 'true',\n"
                        + "	PRIMARY KEY(rut)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla organizador" + e);
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
                String sql = "CREATE TABLE Propietario(\n"
                        + "	nombreCompleto varchar(100) not null,\n"
                        + "	rut  varchar(12) not null,\n"
                        + "	correo varchar(50)not null,\n"
                        + "	contraseña varchar(25)not null,\n"
                        + "	telefono varchar(10)not null,\n"
                        + "	cuentaCorriente varchar(25) not null,\n"
                        + "	activo boolean NOT NULL default 'true',\n"
                        + "	PRIMARY KEY(rut)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla propietario" + e);
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
                String sql = "CREATE TABLE Evento(\n"
                        + "	id SERIAL NOT NULL,\n"
                        + "	nombre varchar(100)not null,\n"
                        + "	descripcion text not null,\n"
                        + "	fechaInicio date not null,\n"
                        + "	fechaTermino date not null,\n"
                        + "	capacidad integer not null,\n"
                        + "	PlazoDevolucionEntradas integer not null,\n"
                        + "	publicado boolean not null,\n"
                        + "	refOrganizador varchar(12) not null,\n"
                        + "	activo boolean NOT null default 'true',\n"
                        + "	PRIMARY KEY(id),\n"
                        + "	FOREIGN KEY (refOrganizador) references Organizador(rut) ON DELETE CASCADE \n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla evento" + e);
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
                String sql = "CREATE TABLE Propiedad(\n"
                        + "	id SERIAL NOT NULL,\n"
                        + "	nombre varchar(100)not null,\n"
                        + "	ubicacion varchar(100) not null,\n"
                        + "	fechaPublicacion date not null,\n"
                        + "	capacidadTotal integer not null,\n"
                        + "	valorArriendo integer not null,\n"
                        + "	descripcion varchar(500) not null,\n"
                        + "	refPropietario varchar(12) not null,\n"
                        + "	activa boolean not NULL default 'true',\n"
                        + "	PRIMARY KEY(id),\n"
                        + "	FOREIGN KEY (refPropietario) references Propietario(rut) ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla propiedad" + e);
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
                String sql = "CREATE TABLE Sector(\n"
                        + "	nombre varchar(100) not null,\n"
                        + "	capacidad integer not NULL,\n"
                        + "	refPropiedad integer not null,\n"
                        + "	activa boolean not NULL default 'true',\n"
                        + "	PRIMARY KEY(nombre,refPropiedad),\n"
                        + "	FOREIGN KEY (refPropiedad) references Propiedad(id)ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla sector" + e);
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
                String sql = "CREATE TABLE entrada(\n"
                        + "	id serial NOT NULL,\n"
                        + "	PRIMARY KEY(id)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla entrada" + e);
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
                String sql = "CREATE TABLE Celebra(\n"
                        + "	refEvento integer not NULL,\n"
                        + "	refPropiedad integer NOT NULL,\n"
                        + "	activa boolean not NULL default 'true',\n"
                        + "	PRIMARY KEY(refEvento,refPropiedad),\n"
                        + "	FOREIGN KEY (refEvento) references Evento(id)ON DELETE CASCADE,\n"
                        + "	FOREIGN KEY (refPropiedad) references Propiedad(id)ON DELETE CASCADE\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla celebra" + e);
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaIntanciaEntrada(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE instanciaEntrada(\n"
                        + "	id serial NOT NULL,\n"
                        + "	refcompra integer NOT NULL,\n"
                        + "	refentrada integer NOT NULL,\n"
                        + "	PRIMARY KEY(id),\n"
                        + "	FOREIGN KEY(refEntrada)references entrada(id),\n"
                        + "	FOREIGN KEY(refcompra)references compra(id)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla instancia entrada" + e);
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
                String sql = "CREATE TABLE compra(\n"
                        + "	id serial NOT NULL,\n"
                        + "	numeroentradas integer not null,\n"
                        + "	fechacompra date not null,\n"
                        + "	preciototal integer not NULL,\n"
                        + "	PRIMARY KEY(id)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear tabla compra" + e);
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaAsociarEventoEntradaSector(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE asociacionEventoEntradaSector(\n"
                        + "	refEntrada integer not NULL,\n"
                        + "	refEvento integer not NULL,\n"
                        + "	refSector varchar(100) not NULL,\n"
                        + "	refPropiedad integer not NULL,\n"
                        + "	precio integer not NULL,\n"
                        + "	activa boolean not NULL default 'true',\n"
                        + "	PRIMARY KEY(refEntrada,refEvento,refSector),\n"
                        + "	FOREIGN KEY(refEntrada)references entrada(id),\n"
                        + "	FOREIGN KEY(refEvento)references evento(id),\n"
                        + "	FOREIGN KEY(refSector,refPropiedad)references sector(nombre,refPropiedad)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear crearTablaAsociarEventoEntradaSector " + e);
                return false;
            }
        }
        return false;
    }

    public boolean crearTablaRealizaCompra(Connection conexion) {
        Connection miConexion = conexion;
        if (miConexion != null) {
            try {
                java.sql.Statement st = miConexion.createStatement();
                String sql = "CREATE TABLE realizaCompra(\n"
                        + "	refcliente varchar(12)not NULL,\n"
                        + "	refcompra integer not NULL,\n"
                        + "	PRIMARY KEY(refcliente,refcompra)\n"
                        + ");";
                st.executeUpdate(sql);
                st.close();
                return true;
            } catch (SQLException e) {
                //System.out.println("ERROR DE crear crearTablaRealizaCompra " + e);
                return false;
            }
        }
        return false;
    }
}
