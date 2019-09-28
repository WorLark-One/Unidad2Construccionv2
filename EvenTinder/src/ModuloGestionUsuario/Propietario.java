package ModuloGestionUsuario;

import ModuloGestionPropiedades.Propiedad;
import java.util.ArrayList;

/**
 * 
 */
public class Propietario extends Usuario {
    //Modificable
    private String cuentaCorriente;
    
    private ArrayList<Propiedad> listaDePropiedades;
    
    /**
     * 
     * @param nombreCompleto
     * @param rut
     * @param contraseña
     * @param correoElectronico
     * @param cuentaCorriente 
     */
    public Propietario(String nombreCompleto, String rut, String contraseña, 
            String correoElectronico, String cuentaCorriente) {
        super(nombreCompleto,rut,contraseña,correoElectronico);
        this.cuentaCorriente = cuentaCorriente;
        this.listaDePropiedades = new ArrayList();
    }

    public String getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(String cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public ArrayList<Propiedad> getListaDePropiedades() {
        return listaDePropiedades;
    }

    public void addPropiedad(Propiedad propiedad) {
        this.listaDePropiedades.add(propiedad);
    }
    
    public void removePropiedad(Propiedad propiedad){
        this.listaDePropiedades.remove(propiedad);
    }
        
}