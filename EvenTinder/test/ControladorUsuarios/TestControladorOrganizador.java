/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorUsuarios;

import ControladorUsuarios.ControladorOrganizador;
import ModuloGestionEventos.Evento;
import ModuloGestionUsuario.Organizador;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruben
 */
public class TestControladorOrganizador {
    ControladorOrganizador c = new ControladorOrganizador();
    
    public TestControladorOrganizador() {
    }
    
    @Test
    public void  crearEvento() {
        System.out.println("");
        System.out.println("crearEvento");
        System.out.println("");
        String nombre = "Eventazo";
        String descripcion = "asdasd";
        Date fechaDeInicio = new Date(2019, 1, 20);
        Date fechaDeTermino = new Date(2020, 1, 20);
        int capacidad= -300;
        int diasMaximoDevolucion = -3;
        boolean publicado = false;
        int idPropiedad = 3;
        int i= c.crearEvento(nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad);
        assertNotEquals(0, i);
        fail("No se creó el evento. ERROR");
    }
    
    @Test
    public void   modificarEvento() {
        System.out.println("");
        System.out.println("modificarEvento");
        System.out.println("");
        int idEvento = 8;
        String nuevoNombre = "Evento modificado dos";
        String nuevaDescripcion = "lol";
        Date nuevaFechaDeInicio = new Date(2010, 1, 1);
        Date nuevaFechaDeTermino = new Date(2012, 1, 1);
        int nuevaCapacidad= 200;
        int nuevosDiasMaximoDevolucion = -3;
        boolean nuevoPublicado = false;  
        
        boolean i = c.modificarEvento(idEvento, nuevoNombre, nuevaDescripcion, nuevaFechaDeInicio, nuevaFechaDeTermino, nuevaCapacidad, nuevosDiasMaximoDevolucion, nuevoPublicado);
        assertEquals(true, i);
        fail("No se modificó el evento");
    }
    
    @Test
    public void  eliminarEvento() {
        System.out.println("");
        System.out.println("eliminarEvento");
        System.out.println("");
        int idEvento = 7;
        boolean i = c.eliminarEvento(idEvento);
        
        assertEquals(i, true);
        fail("No se eliminó el evento");
    }
    
    @Test
    public void  obtenerInformacionDeTodosLosEventosDeUnOrganizador() {
        System.out.println("");
        System.out.println("obtenerInformacionDeTodosLosEventosDeUnOrganizador");
        System.out.println("");
        String rutOrg = "19";
        ArrayList<Evento> a = c.obtenerInformacionDeEventosFinalizadosDeUnOrganizador();
        for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
        
    
    }
    
    
    @Test
    public void  obtenerInformacionDeEventosPublicadosDeUnOrganizador() {
        System.out.println("");
        System.out.println("obtenerInformacionDeEventosPublicadosDeUnOrganizador");
        System.out.println("");
        String rutOrg = "19";
        ArrayList<Evento> a = c.obtenerInformacionDeEventosPublicadosDeUnOrganizador();
         for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
    
    }
    @Test
    public void  obtenerInformacionDeEventosNoPublicadosDeUnOrganizador() {
        System.out.println("");
        System.out.println("obtenerInformacionDeEventosNoPublicadosDeUnOrganizador");
        System.out.println("");
        String rutOrg = "19";
        ArrayList<Evento> a = c.obtenerInformacionDeEventosNoPublicadosDeUnOrganizador();
         for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
    }
    
    @Test
    public void  obtenerInformacionDeEventosFinalizadosDeUnOrganizador() {
        System.out.println("");
        System.out.println("obtenerInformacionDeEventosFinalizadosDeUnOrganizador");
        System.out.println("");
        String rutOrg = "19";
        ArrayList<Evento> a = c.obtenerInformacionDeEventosFinalizadosDeUnOrganizador();
         for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
    }
}
