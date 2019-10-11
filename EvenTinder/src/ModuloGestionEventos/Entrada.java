package ModuloGestionEventos;





/**
 * 
 */
public class Entrada {
    private int idEntrada;
    private int precio;

    public Entrada(int idEntrada, int precio) {
        this.idEntrada = idEntrada;
        this.precio = precio;
    }
    /**
     * Obtiene el identificador de una entrada.
     * @return identificador de la entrada.
     */
    public int getIdEntrada() {
        return idEntrada;
    }
    /**
     * Modifica el identeficador de una entrada
     * @param idEntrada 
     */
    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }
    /**
     * Obtiene el precio de la entrada.
     * @return 
     */
    public int getPrecio() {
        return precio;
    }
    /**
     * Modifica el precio de la entrada.
     * @param precio 
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    
    
    

}