package ModuloGestionPropiedades;

import java.util.ArrayList;
import java.util.Date;


/**
 * 
 */
public class Propiedad {
     int id;
     String nombre;
     String descripcion;
     Date fechaDePublicacion;
     String ubicacion;
     int capacidadTotal;
     int valorArriendo;
     ArrayList<Sector> listaSectores;

    public Propiedad(int id, String nombre, String descripcion, Date fechaDePublicacion, String ubicacion, int capacidadTotal, int valorArriendo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaDePublicacion = fechaDePublicacion;
        this.ubicacion = ubicacion;
        this.capacidadTotal = capacidadTotal;
        this.valorArriendo = valorArriendo;
        this.listaSectores = new ArrayList<>();
    }
  
    

    /**
     * 
     * @param sector
     * @return 
     */
    public boolean añadirSector( Sector sector) {
        
        return this.añadirSector(sector);
    }

    public boolean modificarSector( int id,  String nombre,  int capSector) {
        
        return false;
    }

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