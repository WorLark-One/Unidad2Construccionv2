package ModuloGestionPropiedades;

import ModuloGestionEventos.Evento;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 */
public class Propiedad implements Comparable<Propiedad>{

    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaDePublicacion;
    private String ubicacion;
    private int capacidadTotal;
    private int valorArriendo;
    private ArrayList<Sector> listaSectores;
    private boolean activa;

    public Propiedad(int id, String nombre, String descripcion, Date fechaDePublicacion, String ubicacion, int capacidadTotal, int valorArriendo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaDePublicacion = fechaDePublicacion;
        this.ubicacion = ubicacion;
        this.capacidadTotal = capacidadTotal;
        this.valorArriendo = valorArriendo;
        this.listaSectores = new ArrayList<>();
        this.activa=true;//inicialmente la propiedad 
    }

    /**
     * Añade un sector a la lista de sectores.
     * @param sector
     * @return true si se añade el sector, false de lo contrario.
     */
    public boolean añadirSector(Sector sector) {

        if (this.listaSectores != null) {
            this.listaSectores.add(sector);
            return true;
        }
        return false;
    }
    /**
     * modifica la informacion de un sector.
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de la propiedad.
     * @param nuevoNombre: nuevo nombre del sector
     * @param nuevaCapacidad: nueva capacidad del sector.
     * @return 
     */
    public boolean modificarSector(String nombreSector, int idPropiedad, String nuevoNombre, int nuevaCapacidad) {

        if (this.listaSectores != null) {
            for (int i = 0; i < listaSectores.size(); i++) {
                Sector sector = listaSectores.get(i);
                String miNombre = sector.getNombre();
                int miId = sector.getIdPropiedad();
                if (nombreSector.equalsIgnoreCase(miNombre) == true && idPropiedad == miId) {
                    sector.setNombre(nombre);
                    sector.setCapacidadDelSector(capacidadTotal);
                    return true;
                }

            }
        }
        return false;
    }
    /**
     * Borra un sector de la lista de sectores.
     * @param nombreSector: nombre del sector.
     * @param idPropiedad: identificador de la propiedad a la cual pertenece la propiedad.
     * @return true si elimina el sector, false de lo contrario.
     */
    public boolean eliminarSector(String nombreSector, int idPropiedad) {
        if (this.listaSectores != null) {
            for (int i = 0; i < listaSectores.size(); i++) {
                Sector miSector = listaSectores.get(i);
                if (miSector.getNombre().equalsIgnoreCase(nombreSector) == true && miSector.getIdPropiedad()== idPropiedad) {
                    this.listaSectores.remove(i);
                    return true;
                }

            }
        }
        return false;
    }

    public int numeroSectore() {
        return this.listaSectores.size();
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    

    @Override
    public int compareTo(Propiedad o) {
        if(this.id<o.id){
            return -1;
        }
        if( this.id > o.id ){
            return 1;
        }
        return 0;
    }

    
    
  
}
