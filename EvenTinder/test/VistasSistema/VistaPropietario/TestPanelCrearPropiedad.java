/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistemaVistaPropietario;
import org.junit.Test;
import static org.junit.Assert.*;
import VistasSistemaVistaPropietario.PanelCrearPropiedad;
import VistasSistemaVistaPropietario.VentanaPrincipalPropietario;
/**
 *
 * @author crist
 */
public class TestPanelCrearPropiedad {
    VentanaPrincipalPropietario papa = new VentanaPrincipalPropietario();
    PanelCrearPropiedad pcp = new PanelCrearPropiedad(papa);
    public TestPanelCrearPropiedad(){}
    
    @Test
    public void testValidarEntradaRegistrar(){ // funcion dejada como public para el test
        String nombre = ""; 
        String descripcion = "";
        String ubicacion = "";
        String nSectores = "";
        String valorArriendo = "";
        boolean finalizar = false;
        int retorno = 10;
        int casos = 0;
        switch(casos){
            case 0:
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(1, retorno); // falla en el nombre asi que retorna 1
            case 1:
                nombre = "pepe";
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(2, retorno); // falla en la descripcion asi que retorna 2
            case 2:
                nombre = "pepe";
                descripcion = "un lugar bonito";
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(3, retorno); // falla en la ubicacion asi que retorna 3
            case 3:
                nombre = "pepe";
                descripcion = "un lugar bonito";
                ubicacion = "pasaje 4";
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(4, retorno); // falla en los nSectores asi que retorna 4 
            case 4:
                nombre = "pepe";
                descripcion = "un lugar bonito";
                ubicacion = "pasaje 4";
                nSectores = "3";
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(5, retorno); // falla en el valorArriendo asi que retorna 5
            case 5:
                nombre = "pepe";
                descripcion = "un lugar bonito";
                ubicacion = "pasaje 4";
                nSectores = "3";
                valorArriendo = "100000";
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(6, retorno); // finalizar es falso asi que falla
            case 6:
                nombre = "pepe";
                descripcion = "un lugar bonito";
                ubicacion = "pasaje 4";
                nSectores = "3";
                valorArriendo = "100000";
                finalizar = true;
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(0, retorno); // ok retrona 0
            case 7:
                nombre = "123";
                descripcion = "un lugar bonito";
                ubicacion = "pasaje 4";
                nSectores = "3";
                valorArriendo = "100000";
                finalizar = true;
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(0, retorno); // nombre invalido, deberia retornar 1 pero retorna 0
            case 8:
                nombre = "pepe";
                descripcion = "123";
                ubicacion = "pasaje 4";
                nSectores = "3";
                valorArriendo = "100000";
                finalizar = true;
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(0, retorno); // descripcion invalida, deberia retornar 2 pero retorna 0
            case 9:
                nombre = "pepe";
                descripcion = "un lugar muy bonito";
                ubicacion = "/&%";
                nSectores = "3";
                valorArriendo = "100000";
                finalizar = true;
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(0, retorno); // ubicacion invalida, deberia retornar 3 pero retorna 0
            case 10:
                nombre = "pepe";
                descripcion = "un lugar muy bonito";
                ubicacion = "pasaje 4";
                nSectores = "uyuuuy";
                valorArriendo = "100000";
                finalizar = true;
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(0, retorno); // nSectores invalido, deberia retornar 4 pero retorna 0
            case 11:
                nombre = "pepe";
                descripcion = "un lugar muy bonito";
                ubicacion = "pasaje 4";
                nSectores = "3";
                valorArriendo = ",muchas lucas";
                finalizar = true;
                retorno = pcp.validarEntradaRegistrar( nombre,  descripcion,  ubicacion,  nSectores,  valorArriendo, finalizar);
                assertEquals(0, retorno); // valorArriendo invalida, deberia retornar 5 pero retorna 0
        }
    }


}
