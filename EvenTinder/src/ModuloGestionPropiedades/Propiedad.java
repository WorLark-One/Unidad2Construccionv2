package ModuloGestionPropiedades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 */
public class Propiedad {

    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaDePublicacion;
    private String ubicacion;
    private int capacidadTotal;
    private int valorArriendo;
    private ArrayList<Sector> listaSectores;

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
    public boolean añadirSector(Sector sector) {

        if (this.listaSectores != null) {
            this.listaSectores.add(sector);
            return true;
        }
        return false;
    }

    public boolean modificarSector(String nombreSector, int idPropiedad, String nuevoNombre, int nuevaCapacidad) {

        if (this.listaSectores != null) {
            for (int i = 0; i < listaSectores.size(); i++) {
                Sector sector = listaSectores.get(i);
                String nombre = sector.getNombre();
                int id = sector.getId();
                if (nombreSector.equalsIgnoreCase(nombre) == true && idPropiedad == id) {
                    sector.setNombre(nombre);
                    sector.setCapacidadDelSector(capacidadTotal);
                    return true;
                }

            }
        }
        return false;
    }

    public boolean eliminarSector(String nombreSector, int idPropiedad) {
        if (this.listaSectores != null) {
            for (int i = 0; i < listaSectores.size(); i++) {
                Sector miSector = listaSectores.get(i);
                if (id == miSector.getId()) {
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

}
