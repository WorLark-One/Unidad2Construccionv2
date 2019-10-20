/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGestionVentas;

import ModuloGestionEventos.Entrada;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Brayan Escobar
 */
public class Compra implements Comparable<Compra>{

    private int id;
    private int numeroDeEntrada;
    private Date fechaDeCompra;
    private int precioTotalDeLaCompra;
    private int idEvento;
    private String nombreEvento;
    String nombreSector;
    private ArrayList<Entrada>listaEntradas;

    public Compra(int id, int numeroDeEntrada, Date fechaDeCompra, int precioTotalDeLaCompra, int idEvento, String nombreEvento, String nombreSector, ArrayList<Entrada> listaEntradas) {
        this.id = id;
        this.numeroDeEntrada = numeroDeEntrada;
        this.fechaDeCompra = fechaDeCompra;
        this.precioTotalDeLaCompra = precioTotalDeLaCompra;
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.nombreSector = nombreSector;
        this.listaEntradas = listaEntradas;
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

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    public ArrayList<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    public void setListaEntradas(ArrayList<Entrada> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }
                    
    @Override
    public int compareTo(Compra o) {
        if(this.id<o.id){
            return -1;
        }
        if( this.id > o.id ){
            return 1;
        }
        return 0;
    }
        
}
