package ModuloGestionPropiedades;



/**
 * 
 */
public class Sector implements Comparable<Sector>{
    private int idPropiedad;
    private String nombre;
    private int capacidadDelSector;

    public Sector(int idPropiedad, String nombre, int capacidadDelSector) {
        this.idPropiedad = idPropiedad;
        this.nombre = nombre;
        this.capacidadDelSector = capacidadDelSector;
    }

    
    
    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
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

    @Override
    public int compareTo(Sector o) {
        if(this.idPropiedad<o.idPropiedad){
            return -1;
        }
        if( this.idPropiedad > o.idPropiedad ){
            return 1;
        }
        return 0;
    }

  

}