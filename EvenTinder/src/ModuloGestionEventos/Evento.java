package ModuloGestionEventos;

import java.util.*;

/**
 * 
 */
public class Evento {

    /**
     * Default constructor
     */
    public Evento() {
    }

    /**
     * 
     */
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaDeInicio;
    private Date fechaDeTermino;
    private int capacidadMaximaDelEvento;
    private int plazoDevolucionDeEntrada;
    private boolean publicado;
    private ArrayList<Entrada> listaDeEntrada;




    /**
     * @return
     */
    public boolean añadorEntrada() {
        // TODO implement here
        return false;
    }

    /**
     * @param int idEntrada 
     * @return
     */
    public boolean eliminarEntrada(int idEntrada) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean verificarDisponibilidadDeEntrada() {
        // TODO implement here
        return false;
    }

}