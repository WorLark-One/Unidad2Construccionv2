package ModuloGestionUsuario;

/**
 * 
 */
public abstract class Usuario {

    private String nombreCompleto;
    private String rut;    
    private String contraseña;
    private String telefono;
    private String correoElectronico;
    
    /**
     * Constructor de la clase Usuario.
     * @param nombreCompleto El nombre del Usuario a crear.
     * @param rut El rut del Usuario a crear.
     * @param contraseña La contraseña del Usuario a crear.
     * @param telefono
     * @param correoElectronico El correo electronico del Usuario a crear.
     */
    public Usuario(String nombreCompleto, String rut, String contraseña, 
            String telefono, String correoElectronico) {
        this.nombreCompleto = nombreCompleto;
        this.rut = rut;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        
    }        
        
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }        

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}