package ModuloGestionPropiedades;

import ControladorBaseDeDatos.ControladorBDDePropiedades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class GestionDePropiedad {
    
    private ArrayList<Propiedad> listaPropiedades;
    private ControladorBDDePropiedades controlador;
    
    /**
     * Default constructor
     */
    public GestionDePropiedad() {
        this.listaPropiedades = new ArrayList();
        this.controlador= new ControladorBDDePropiedades();
    }

    /**
     * @return
     */
    public ArrayList<Propiedad> mostrarListaDePropiedades() { 
        
        return this.listaPropiedades;
    }

    /**
     * @param id 
     * @return
     */
    public Propiedad mostrarPropiedad( int id) {
        
        for(int i = 0; i<this.listaPropiedades.size();i++){
            if(this.listaPropiedades.get(i).getId() == id){
                return this.listaPropiedades.get(i);
            }
        }
        return null;
    }


    /**
     * @param rut 
     * @return
     */
    public void obtenerInformacionDePropiedades(String rut) throws SQLException {
        this.listaPropiedades = this.controlador.obtenerInformacionDePropiedades(rut);
    }
    

    /**
     * @param rut 
     * @param nombre 
     * @param ubicacion 
     * @param fechaDePublicacion 
     * @param capacidadTotal 
     * @param valorDeArriendo 
     * @param descripcion 
     * @return
     */
    public int registrarPropiedad( String rut,  String nombre,  String descripcion,  Date fechaDePublicacion, String ubicacion,  int capacidadTotal,  int valorDeArriendo) throws SQLException {
        // TODO implement here
        int i = this.controlador.registrarPropiedad(rut, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        if(i>=0){
            Propiedad p = new Propiedad(i, nombre, descripcion, fechaDePublicacion, ubicacion, capacidadTotal, valorDeArriendo);
            this.listaPropiedades.add(p);
            return i;
        }
        return -1;
    }

    /**
     * @param id 
     * @param nombre 
     * @param ubicacion 
     * @param fechaDePublicacion 
     * @param capacidadTotal 
     * @param valorDeArriendo 
     * @param descripcion 
     * @return
     */
    public boolean modifcarPropiedad( int id,  String nombre,  String descripcion,  Date fechaDePublicacion, String ubicacion,  int capacidadTotal,  int valorDeArriendo) throws SQLException {
        boolean result = this.controlador.modifcarPropiedad(id, nombre, ubicacion, fechaDePublicacion, capacidadTotal, valorDeArriendo, descripcion);
        if(result){
            for(Propiedad p:this.listaPropiedades){
                if(p.getId() == id){
                    p.setNombre(nombre);
                    p.setDescripcion(descripcion);
                    p.setFechaDePublicacion(fechaDePublicacion);
                    p.setUbicacion(ubicacion);
                    p.setCapacidadTotal(capacidadTotal);                                        
                    p.setValorArriendo(valorDeArriendo);
                    return true;
                }
            }
        }        
        return false;
    }

    /**
     * @param id 
     * @return
     */
    public boolean eliminarPropiedad( int id) {
       
        for(Propiedad p:this.listaPropiedades){
            if(p.getId() == id){
                this.listaPropiedades.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * @param id 
     * @param capacidad 
     * @param nombre 
     * @return
     */
    public boolean añadirSector( int id,  int capacidad,  String nombre) throws SQLException {
        this.controlador.registrarSector(nombre, capacidad,id);
        for(Propiedad p : this.listaPropiedades){
            if(p.getId() == id){
                p.añadirSector(new Sector(id,nombre,capacidad));
                return true;
            }
        }
        return false;
    }

    /**
     * @param id 
     * @param nombreActual 
     * @param capacidad 
     * @param nombre 
     * @return
     */
    public boolean modificarSector(int id, String nombreActual,int capacidad,  String nombre) throws SQLException {
        boolean bandera = this.controlador.modificarSector(nombreActual, id, nombre, capacidad);
        for(Propiedad p : this.listaPropiedades){
            if(p.getId() == id){
                for(Sector s: p.getListaSectores()){
                    if(s.getId()== id && s.getNombre().equals(nombreActual)){
                        s.setNombre(nombre);
                        s.setCapacidadDelSector(capacidad);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param id 
     * @param nombreActual 
     * @return
     */
    public boolean eliminarSector( int id, String nombreActual) throws SQLException {
        this.controlador.eliminarSector(nombreActual, id);
        for(Propiedad p:this.listaPropiedades){
            if(p.getId() == id){
                p.eliminarSector(nombreActual, id);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Propiedad> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(ArrayList<Propiedad> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }
            
}
