package ModuloGestionPropiedades;

import java.util.ArrayList;
import java.util.Date;


/**
 * 
 */
public class Propiedad {

    /**
     * Default constructor
     */
    public Propiedad() {
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    public String nombre;

    /**
     * 
     */
    private String descripcion;

    /**
     * 
     */
    private Date fechaDePublicacion;

    /**
     * 
     */
    private String ubicacion;

    /**
     * 
     */
    private int capacidadTotal;

    /**
     * 
     */
    private int valorArriendo;

    /**
     * 
     */
    private ArrayList<Sector> listaSectores;



    /**
     * @param int id 
     * @param String nombre 
     * @param String ubicacion 
     * @param Date fechaDePublicacion 
     * @param int capacidadTotal 
     * @param int valorDeArriendo 
     * @param String descripcion 
     * @return
     */
    public boolean Propiedad( int id,  String nombre,  String ubicacion,  Date fechaDePublicacion,  int capacidadTotal,  int valorDeArriendo,  String descripcion) {
        // TODO implement here
        return false;
    }

    /**
     * @param Sector sector 
     * @return
     */
    public boolean añadirSector( Sector sector) {
        // TODO implement here
        return false;
    }

    /**
     * @param int id 
     * @param String nombre 
     * @param int capSector 
     * @return
     */
    public boolean modificarSector( int id,  String nombre,  int capSector) {
        // TODO implement here
        return false;
    }

    /**
     * @param Sector sector 
     * @return
     */
    public boolean eliminarSector( Sector sector) {
        // TODO implement here
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(Date fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public int getValorArriendo() {
        return valorArriendo;
    }

    public void setValorArriendo(int valorArriendo) {
        this.valorArriendo = valorArriendo;
    }

    public ArrayList<Sector> getListaSectores() {
        return listaSectores;
    }

    public void setListaSectores(ArrayList<Sector> listaSectores) {
        this.listaSectores = listaSectores;
    }



}