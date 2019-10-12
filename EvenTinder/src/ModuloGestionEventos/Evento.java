package ModuloGestionEventos;

import java.util.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class Evento {

    

    private int idEvento;
    private String nombre;
    private String descripcion;
    private Date fechaDeInicio;
    private Date fechaDeTermino;
    private int capacidadMaximaDelEvento;
    private int plazoDevolucionEntrada;
    private boolean publicado;
    int idPropiedad;
    private ArrayList<Entrada>listaEntradas;

    public Evento(int idEvento, String nombre, String descripcion, Date fechaDeInicio, Date fechaDeTermino, int capacidad, int plazoDevolucionEntrada, boolean publicado) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeTermino = fechaDeTermino;
        this.capacidadMaximaDelEvento = capacidad;
        this.plazoDevolucionEntrada = plazoDevolucionEntrada;
        this.publicado = publicado;
        this.listaEntradas= new ArrayList<>();
        this.idPropiedad=-1;
    }




    /**
     * Añade una entrada a la lista de entradas del evento.
     * @param entrada
     * @return true si la añadio correctamente, false de lo contrario.
     */
    public boolean AñadirEntrada(Entrada entrada) {

        return this.listaEntradas.add(entrada);
    }

    /**
     * Elimina una entrada de la lista de entradas del evento.
     * @param idEntrada
     * @return true si se elimina, false de lo contrario.
     */
    public boolean eliminarEntrada(int idEntrada) {
        if (this.listaEntradas != null) {
            for (int i = 0; i < listaEntradas.size(); i++) {
                Entrada entrada = listaEntradas.get(i);
                if (entrada.getIdEntrada() == idEntrada) {
                    this.listaEntradas.remove(i);
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * verifica si hay entradas disponibles para un evento.
     * @return true si hay entradas disponibles, false de lo contrario.
     */
    public boolean verificarDisponibilidadDeEntrada() {
        System.out.println("disponivilidad de entrada:"+this.capacidadMaximaDelEvento+" lista entradas:"+this.listaEntradas.size());
        if (this.listaEntradas != null) {
            if (getCapacidadMaximaDelEvento() > this.listaEntradas.size()) {
                return true;
            }

        }
        return false;
    }
    
     /**
     * Se obtienen el identificador del eveto.
     * @return identificador del evento
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * se obtiene el nombre del evento.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * modifica el nombre del evento por un nuevo nombre.
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Se obtiene la descripcion del evento.
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Modifica la descripción del evento
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * se obtienen la fecha de inicio del evento.
     * @return Date
     */
    public Date getFechaDeInicio() {
        return fechaDeInicio;
    }
    /**
     * Modifica la fecha de inicio del evento.
     * @param fechaDeInicio 
     */
    public void setFechaDeInicio(Date fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }
    /**
     * obtiene la fecha de termino del proyecto.
     * @return Date
     */
    public Date getFechaDeTermino() {
        return fechaDeTermino;
    }

    public void setFechaDeTermino(Date fechaDeTermino) {
        this.fechaDeTermino = fechaDeTermino;
    }
    /**
     * Obtiene el plazo de devolucion de las entradas.
     * @return 
     */
    public int getPlazoDevolucionEntrada() {
        return plazoDevolucionEntrada;
    }
    /**
     * Modifica el plazo de devolución de las entradas.
     * @param plazoDevolucionEntrada 
     */
    public void setPlazoDevolucionEntrada(int plazoDevolucionEntrada) {
        this.plazoDevolucionEntrada = plazoDevolucionEntrada;
    }
    /**
     * Se obtiene la capacidadMaximaDelEvento del evento " numero de personas"
     * @return 
     */
    public int getCapacidadMaximaDelEvento() {
        return capacidadMaximaDelEvento;
    }
    /**
     * Modifica la capacidadMaximaDelEvento del propyecto.
     * @param capacidadMaximaDelEvento 
     */
    public void setCapacidadMaximaDelEvento(int capacidadMaximaDelEvento) {
        this.capacidadMaximaDelEvento = capacidadMaximaDelEvento;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public ArrayList<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    public void setListaEntradas(ArrayList<Entrada> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }
    
    
}