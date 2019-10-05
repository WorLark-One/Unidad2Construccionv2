package ModuloGestionPropiedades;


/**
 * 
 */
public class Sector {
    private int id;
    private String nombre;
    private int capacidadDelSector;

    public Sector(int id, String nombre, int capacidadDelSector) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadDelSector = capacidadDelSector;
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

    public int getCapacidadDelSector() {
        return capacidadDelSector;
    }

    public void setCapacidadDelSector(int capacidadDelSector) {
        this.capacidadDelSector = capacidadDelSector;
    }
 
  

}