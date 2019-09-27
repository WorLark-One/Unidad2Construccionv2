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
     * @param correoElectronico
     * @param tarjeta 
     */
    public Cliente(String nombreCompleto, String rut, String contraseña, 
            String correoElectronico, String tarjeta) {
        super(nombreCompleto,rut,contraseña,correoElectronico);
        this.tarjetaDeCredito = tarjeta;
    }

    public String getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    public void setTarjetaDeCredito(String tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }
        
    @Override
    public void ModificarUsuario() {        
    }
}