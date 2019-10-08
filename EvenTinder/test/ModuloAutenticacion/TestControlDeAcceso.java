/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloAutenticacion;
import static org.junit.Assert.assertEquals;
import ModuloAutenticacion.ControlDeAcceso;
import org.junit.Test;
/**
 *
 * @author crist
 */
public class TestControlDeAcceso {
    ControlDeAcceso c = new ControlDeAcceso();
    int contador = 0;
    public TestControlDeAcceso() {
    }
    @Test 
    public void probarSingleton(){
        int contador = c.getContador();
        for (int i = 0;i<4; i++){
            contador++;
            ControlDeAcceso aux  = c.getIntancia();
            contador = c.getContador();
            System.out.println(contador);
        }
        assertEquals(1, contador);
    }
  //  @Test
    /*
    public void probarObtencioDeCredencial(){
        String tipoUsuario = "organizador";
        String rut = "17586721k";
        String clave = "123";
        System.out.println("probarObtencioDeCredencial");
        boolean seteado = false;
        boolean aux = c.obtencioDeCredencial(tipoUsuario, rut, clave, seteado);
        
        String usser = c.getTipoUsuario();
        String tur = c.getRut();
        String vecla = c.getContraseña(); 
        
        if(tipoUsuario.equals(usser) && rut.equals(tur) && clave.equals(vecla)){
            seteado = true;
        }
        assertEquals(false, seteado);
    }*/
}
