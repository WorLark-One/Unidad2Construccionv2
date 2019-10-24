/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorBaseDeDatos;

import ControladorBaseDeDatos.ControladorBDDeEventos;
import ModuloGestionEventos.Evento;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruben
 */
public class TestControladorBDDeEventos {
    ControladorBDDeEventos c = new ControladorBDDeEventos();
    
    public TestControladorBDDeEventos() {
    }

    
    @Test
    public void  crearEvento() {
        System.out.println("");
        System.out.println("crearEvento");
        System.out.println("");
        String nombre = "Torneo de LoL";
        String descripcion = "asdasd";
        Date fechaDeInicio = new Date(2019, 1, 20);
        Date fechaDeTermino = new Date(2020, 1, 22);
        int capacidad= 100;
        int diasMaximoDevolucion = 2;
        boolean publicado = false;
        int idPropiedad = 1;
        String rutOrganizador = "11.111.111-1";
        int i= c.crearEvento(nombre, descripcion, fechaDeInicio, fechaDeTermino, capacidad, diasMaximoDevolucion, publicado, idPropiedad, rutOrganizador);
        assertNotEquals(0, i);
        fail("No se creó el evento. ERROR");
    }
    
    @Test
    public void   modificarEvento() {
        System.out.println("");
        System.out.println("modificarEvento");
        System.out.println("");
        int idEvento = 12;
        String nuevoNombre = "Evento modificado";
        String nuevaDescripcion = "xd";
        Date nuevaFechaDeInicio = new Date(2019, 11, 5);
        Date nuevaFechaDeTermino = new Date(2019, 11, 7);
        int nuevaCapacidad= 30;
        int nuevosDiasMaximoDevolucion = 3;
        boolean nuevoPublicado = true;  
        
        boolean i = c.modificarEvento(idEvento, nuevoNombre, nuevaDescripcion, nuevaFechaDeInicio, nuevaFechaDeTermino, nuevaCapacidad, nuevosDiasMaximoDevolucion, nuevoPublicado);
        assertTrue(i);
        fail("No se modificó el evento");
    }
    
    @Test
    public void  eliminarEvento() {
        System.out.println("");
        System.out.println("eliminarEvento");
        System.out.println("");
        int idEvento = 12;
        boolean i = c.eliminarEvento(idEvento);
        
        assertTrue(i);
        fail("No se eliminó el evento");
    }
    
    @Test
    public void  obtenerInformacionDeTodosLosEventosDeUnOrganizador() {
        System.out.println("");
        System.out.println("obtenerInformacionDeTodosLosEventosDeUnOrganizador");
        System.out.println("");
        String rutOrg = "11.111.111-1";
        ArrayList<Evento> a = c.obtenerInformacionDeTodosLosEventosDeUnOrganizador(rutOrg);
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
        ArrayList<Evento> a = c.obtenerInformacionDeEventosPublicadosDeUnOrganizador(rutOrg);
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
        ArrayList<Evento> a = c.obtenerInformacionDeEventosNoPublicadosDeUnOrganizador(rutOrg);
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
        ArrayList<Evento> a = c.obtenerInformacionDeEventosFinalizadosDeUnOrganizador(rutOrg);
         for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
    }
    @Test
    public void  obtenerInformacionSolicitudesDeEventosPropietario() {
        System.out.println("");
        System.out.println("obtenerInformacionSolicitudesDeEventosPropietario");
        System.out.println("");
        String rutPro = "22222222-2";
        ArrayList<Evento> a = c.obtenerInformacionSolicitudesDeEventosPropietario(rutPro);
         for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
    }
    
    @Test
    public void  obtenerInformacionDeEventosActualesPropietario() {
        System.out.println("");
        System.out.println("obtenerInformacionDeEventosActualesPropietario");
        System.out.println("");
        String rutPro = "22222222-2";
        ArrayList<Evento> a = c.obtenerInformacionDeEventosActualesPropietario(rutPro);
         for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
    
    }
    
    @Test
    public void  obtenerInformacionDeEventosFinalizadosPropietario() {
        System.out.println("");
        System.out.println("obtenerInformacionDeEventosFinalizadosPropietario");
        System.out.println("");
        
        String rutPro = "22222222-2";
        ArrayList<Evento> a = c.obtenerInformacionDeEventosFinalizadosPropietario(rutPro);
         for (Evento  e: a) {
            System.out.println(e.getIdEvento() + " " + e.getNombre() + " " + e.getIdPropiedad());
        }
    }
    
    @Test
    public void  aceptarSolicitudPropietario() {
        System.out.println("");
        System.out.println("aceptarSolicitudPropietario");
        System.out.println("");
        
        int idEvento = 5;
        c.aceptarSolicitudPropietario(idEvento);
    
    }
    
    
    
}
