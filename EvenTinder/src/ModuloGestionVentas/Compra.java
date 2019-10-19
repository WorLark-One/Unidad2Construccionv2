/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionVentas;

import java.util.Date;

/**
 *
 * @author Brayan Escobar
 */
public class Compra {

    private int id;
    private int numeroDeEntrada;
    private Date fechaDeCompra;
    private int precioTotalDeLaCompra;

    public Compra(int id, int numeroDeEntrada, Date fechaDeCompra, int precioTotalDeLaCompra) {
        this.id = id;
        this.numeroDeEntrada = numeroDeEntrada;
        this.fechaDeCompra = fechaDeCompra;
        this.precioTotalDeLaCompra = precioTotalDeLaCompra;
    }

    public int getId() {
        return id;
    }


    public int getNumeroDeEntrada() {
        return numeroDeEntrada;
    }

    public void setNumeroDeEntrada(int numeroDeEntrada) {
        this.numeroDeEntrada = numeroDeEntrada;
    }

    public Date getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(Date fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    public int getPrecioTotalDeLaCompra() {
        return precioTotalDeLaCompra;
    }

    public void setPrecioTotalDeLaCompra(int precioTotalDeLaCompra) {
        this.precioTotalDeLaCompra = precioTotalDeLaCompra;
    }
    
    



}
