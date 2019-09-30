package ModuloGestionUsuario;

/**
 * 
 */
public class Cliente extends Usuario {
    
    //Modificable
    private String tarjetaDeCredito;
    
    /**
     * 
     * @param nombreCompleto
     * @param rut
     * @param contraseña
     * @param telefono
     * @param correoElectronico
     * @param tarjeta 
     */
    public Cliente(String nombreCompleto, String rut, String contraseña, 
            String telefono,String correoElectronico, String tarjeta) {
        super(nombreCompleto,rut,contraseña,telefono,correoElectronico);
        this.tarjetaDeCredito = tarjeta;
    }

    public String getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    public void setTarjetaDeCredito(String tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }
        
}