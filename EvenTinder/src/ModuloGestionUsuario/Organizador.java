package ModuloGestionUsuario;

import ModuloGestionEventos.Evento;
import java.util.*;

/**
 *
 */
public class Organizador extends Usuario {

    //Modificable
    private String tarjetaDeCredito;

    private ArrayList<Evento> listaDeEventos;

    /**
     *
     * @param nombreCompleto
     * @param rut
     * @param contraseña
     * @param correoElectronico
     * @param tarjetaDeCredito
     */
    public Organizador(String nombreCompleto, String rut, String contraseña, 
            String correoElectronico, String tarjetaDeCredito) {
        super(nombreCompleto, rut, contraseña, correoElectronico);
        this.tarjetaDeCredito = tarjetaDeCredito;
        this.listaDeEventos = new ArrayList();

    }

    public String getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

    public void setTarjetaDeCredito(String tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }

    public ArrayList<Evento> getListaDeEventos() {
        return listaDeEventos;
    }

    public void addEvento(Evento evento) {
        this.listaDeEventos.add(evento);
    }

    public void removeEvento(Evento evento) {
        this.listaDeEventos.remove(evento);
    }

    @Override
    public void ModificarUsuario() {        
    }

}
