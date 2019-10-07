package ModuloGestionPropiedades;

import java.util.ArrayList;
import java.util.Date;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 */
public class Propiedad {

    private int id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Date fechaDePublicacion;
    private  int numeroSectores;
    private int capacidadTotal;
    private int valorArriendo;
    private ArrayList<Sector> listaSectores;

    public Propiedad(int id, String nombre, String descripcion, String ubicacion, Date fechaDePublicacion, int numeroSectores, int capacidadTotal, int valorArriendo, ArrayList<Sector> listaSectores) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fechaDePublicacion = fechaDePublicacion;
        this.numeroSectores = numeroSectores;
        this.capacidadTotal = capacidadTotal;
        this.valorArriendo = valorArriendo;
        this.listaSectores = listaSectores;
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
                String nombre = sector.getNombre();
                int id = sector.getIdPropiedad();
                if (nombreSector.equalsIgnoreCase(nombre) == true && idPropiedad == id) {
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
                if (id == miSector.getIdPropiedad()) {
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

    public int getNumeroSectores() {
        return numeroSectores;
    }

    public void setNumeroSectores(int numeroSectores) {
        this.numeroSectores = numeroSectores;
    }

    
}
