package ModuloSeguridadExterna;

import ModuloGestionEventos.Entrada;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 */
public class Guardian {
    private String correo = "EvenTinderUtalca";
    private String pass = "holamundo12345";
    
    /**
     * Default constructor
     */
    public Guardian() {
//        String c = "rukratos@gmail.com";
//        
//        //compra
//        int idcompra= 1;
//        int ndeentradas= 2;
//        String fecha = "19-10-2019";
//        int preciototal = 50000;
//
//        ArrayList<Entrada> entradas = new ArrayList<>();
//        for (int i = 0; i < ndeentradas; i++) {
//            Entrada e;
//            if (i==0) {
//                e = new Entrada(i,15000);
//            }
//            else{
//                e = new Entrada(i,35000);
//            }
//            entradas.add(e);
//        }
//        String tc = "1234123412341234";
//        String nombreEvento = "Party en la casa del Ruben";
//        //compraEntradas(c,idcompra,ndeentradas,fecha,preciototal,entradas,tc,nombreEvento);
//        
//        //arriendo
//        String propietario = c;
//        int idEvento = 1; 
//        nombreEvento= "Party en la casa del Ruben";
//        String fechaDeInicio= "20-10-2019";
//        String fechaDeTermino= "21-10-2019";
//        String cuentaCorriente= "12345678901234567890";
//        int idPropiedad= 1;
//        String nombrePropiedad= "Casa del ruben";
//        //arriendoPropiedad(propietario,idEvento,nombreEvento,fechaDeInicio,fechaDeTermino,cuentaCorriente,idPropiedad,nombrePropiedad);
//        
//        //aceptacion
//        String organizador = c;
//        idEvento= 1 ;
//        nombreEvento= "Party en la casa del Ruben";
//        fechaDeInicio= "20-10-2019";
//        fechaDeTermino= "21-10-2019";
//        tc = "1234123412341234"; 
//        idPropiedad= 1;
//        nombrePropiedad= "Casa del ruben";
//        
//        eventoAceptado(organizador,idEvento,nombreEvento,fechaDeInicio,fechaDeTermino,tc,idPropiedad,nombrePropiedad);
        
        
    }


    /**
     * 
     */
    public void enviarCorreo() {
        // TODO implement here
    }
    
    public void compraEntradas(String correoCliente, int idCompra,int nDeEntradas,String fecha,int precioTotal,ArrayList<String> entradas,String tc,String nombreEvento){
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port","587");
        props.setProperty("mail.smtp.user", this.correo);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.correo));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(correoCliente));
            message.setSubject("Recibimos tu compra ID: "+idCompra+ " en EvenTinder");
            String s = "Gracias por preferir EvenTinder \n"
                    + "Recibimos tu compra que realizaste por EvenTinder para el evento ' "+nombreEvento+" ' y te informamos vía e–mail que la compra se ha procesado satisfactoriamente.\n"
                    + "Los detalles la compra son los siguientes:\n"
                    + "     ID de la compra: "+idCompra+"\n"
                    + "     N° de entradas compradas: "+nDeEntradas+"\n"
                    + "     Fecha de compra: "+fecha+"\n"
                    + "         Entradas compradas \n";
            
            for (int i = 0; i < entradas.size(); i++) {
                String cadena=entradas.get(i);
                String[] a = cadena.split(";");
                String id=a[0];
                String precio=a[1];
                String sector=a[2];
                s = s +"            ID entrada: "+id+"  Valor: $"+precio+"  Sector: "+sector+ "\n";
            }
            String[] tcDijitos =tc.split("");
            String tcNueva = "*";
            for (int i = 1; i < tcDijitos.length; i++) {
                if (i<tcDijitos.length-4) {
                    tcNueva+="*";
                }
                else{
                    tcNueva+=tcDijitos[i];
                }
            }
            s = s +"     Precio total: $"+precioTotal+"  cargado en tu tarjeta de credito: "+tcNueva+"\n";
            message.setText(s);
            Transport t = session.getTransport("smtp");
            t.connect(this.correo,this.pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void eventoCancelado(String correoCliente, int idCompra,int nDeEntradas,String fecha,int precioTotal,String tc,String nombreEvento){
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port","587");
        props.setProperty("mail.smtp.user", this.correo);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.correo));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(correoCliente));
            message.setSubject("El evento  ' "+nombreEvento+" ' se ha cancelado");
            String s = "Tenemos una mala noticia para ti. \n"
                    + "Lamentamos informarte que el evento ' "+nombreEvento+" ' se ha cancelado y te informamos vía e–mail que tu comprar fue reembolsada satisfactoriamente.\n"
                    + "Los detalles del reembolso de tu compra realizada para este evento son los siguientes:\n"
                    + "     ID de la compra: "+idCompra+"\n"
                    + "     N° de entradas compradas: "+nDeEntradas+"\n"
                    + "     Fecha de compra: "+fecha+"\n";
            String[] tcDijitos =tc.split("");
            String tcNueva = "*";
            for (int i = 1; i < tcDijitos.length; i++) {
                if (i<tcDijitos.length-4) {
                    tcNueva+="*";
                }
                else{
                    tcNueva+=tcDijitos[i];
                }

            }
            s = s +"     Precio total de compra: $"+precioTotal+"  se ha reembolso a tu tarjeta de credito: "+tcNueva+"\n";
            message.setText(s);
            Transport t = session.getTransport("smtp");
            t.connect(this.correo,this.pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
  
    
    
    public void reembolsoCompra(String cliente, int idCompra,int nDeEntradas,String fecha,int precioTotal,String tc,String nombreEvento){
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port","587");
        props.setProperty("mail.smtp.user", this.correo);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.correo));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(cliente));
            message.setSubject("Solicitud de reembolso de la compra: "+ idCompra+" en EvenTinder");
            String s = "Tenemos una mala noticia para ti. \n"
                    + "Lamentamos informarte que el evento ' "+nombreEvento+" ' se ha cancelado y te informamos vía e–mail que tu comprar fue reembolsada satisfactoriamente.\n"
                    + "Los detalles del reembolso de tu compra realizada para este evento son los siguientes:\n"
                    + "     ID de la compra: "+idCompra+"\n"
                    + "     N° de entradas compradas: "+nDeEntradas+"\n"
                    + "     Fecha de compra: "+fecha+"\n";
            String[] tcDijitos =tc.split("");
            String tcNueva = "*";
            for (int i = 1; i < tcDijitos.length; i++) {
                if (i<tcDijitos.length-4) {
                    tcNueva+="*";
                }
                else{
                    tcNueva+=tcDijitos[i];
                }

            }
            s = s +"     Precio total de compra: $"+precioTotal+"  se ha reembolso a tu tarjeta de credito: "+tcNueva+"\n";
            message.setText(s);
            Transport t = session.getTransport("smtp");
            t.connect(this.correo,this.pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void arriendoPropiedad(String correoPropietario,int idEvento, String nombreEvento, String fechaDeInicio, String fechaDeTermino, String cuentaCorriente, int idPropiedad, String nombrePropiedad){
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port","587");
        props.setProperty("mail.smtp.user", this.correo);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);     
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.correo));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(correoPropietario));
            message.setSubject("Solicitud de evento aceptada para el evento: ' "+ nombreEvento+" ' con ID: "+idEvento+ " en EvenTinder");
            String s = "Gracias por preferir EvenTinder \n"
                   + "Haz aceptado exitosamente la solicitud del evento ' "+nombreEvento+" ' para tu propiedad "+nombrePropiedad+"y te informamos vía e–mail que los detalles del evento son los siguientes: \n"
                   + "     ID del evento: "+idEvento+"\n"
                   + "     Nombre del evento: "+nombreEvento+"\n"
                   + "     A realizar en tú propieda: "+nombrePropiedad+"\n"
                   + "     Inicio: "+fechaDeInicio+"\n"
                   + "     Termino: "+fechaDeTermino+"\n";
            String[] ccDijitos =cuentaCorriente.split("");
            String ccNueva = "*";
            for (int i = 1; i < ccDijitos.length; i++) {
                if (i<ccDijitos.length-4) {
                    ccNueva+="*";
                }
                else{
                    ccNueva+=ccDijitos[i];
                }
            }
            s = s +"     El dinero que corresponde del arriendo de la propiedad ha sido trasferido a la cuenta corriente: "+ccNueva+"\n";
            message.setText(s);
 
            Transport t = session.getTransport("smtp");
            t.connect(this.correo,this.pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
    * 
    * @param organizador
    * @param idEvento
    * @param nombreEvento
    * @param fechaDeInicio
    * @param fechaDeTermino
    * @param tarjetaCredito
    * @param idPropiedad
    * @param nombrePropiedad 
    */
    
    public void eventoAceptado(String organizador,int idEvento, String nombreEvento, String fechaDeInicio, String fechaDeTermino, String tarjetaCredito, int idPropiedad, String nombrePropiedad){
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port","587");
        props.setProperty("mail.smtp.user", this.correo);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true); 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.correo));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(organizador));
            message.setSubject("Su evento ' "+ nombreEvento+" ' con ID: "+idEvento+ " fue aceptado en EvenTinder");
            String s = "Gracias por preferir EvenTinder \n"
                    + "Le informamos vía e–mail que su evento ' "+nombreEvento+" ' con ID: "+idEvento+"  fue aceptado satisfactoriamente.\n"
                    + "Los detalles del evento aceptado son los siguientes:\n"
                    + "     ID del evento: "+idEvento+"\n"
                    + "     Nombre del evento: "+nombreEvento+"\n"
                    + "     A realizarce en la propiedad: "+nombrePropiedad+" con id:"+idPropiedad+"\n"
                    + "     Inicio: "+fechaDeInicio+"\n"
                    + "     Termino: "+fechaDeTermino+"\n";
            String[] tcDijitos =tarjetaCredito.split("");
            String tcNueva = "*";
            for (int i = 1; i < tcDijitos.length; i++) {
                if (i<tcDijitos.length-4) {
                    tcNueva+="*";
                }
                else{
                    tcNueva+=tcDijitos[i];
                }
            }
            s = s +"     El dinero que corresponde del arriendo de la propiedad se ha cargado a su tarjeta de credito: "+tcNueva+"\n";
            message.setText(s);
            Transport t = session.getTransport("smtp");
            t.connect(this.correo,this.pass);
            t.sendMessage(message,message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    

    public static void main(String args[]) {
        Guardian g = new Guardian();
    }
}