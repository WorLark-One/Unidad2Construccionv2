/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author xebae
 */
public class PanelEliminarPropietario extends javax.swing.JPanel {

    /**
     * Creates new form PanelEliminarUsuario
     */
    
    private VentanaPrincipalPropietario papa;
    
    public PanelEliminarPropietario(VentanaPrincipalPropietario papa) {
        this.papa=papa;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        clave = new javax.swing.JTextField();
        botonEliminarCuenta = new javax.swing.JButton();
        rut = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel12.setText("Rut");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VistasSistema/Imagenes/IconoEvenTinder.png"))); // NOI18N

        clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveActionPerformed(evt);
            }
        });

        botonEliminarCuenta.setText("Eliminar cuenta");
        botonEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarCuentaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("Menú Eliminar cuenta de propietario");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Ingrese los siguientes datos");

        jLabel13.setText("Clave");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rut, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonEliminarCuenta)))
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(botonEliminarCuenta)
                .addContainerGap(101, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_claveActionPerformed

    private void botonEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarCuentaActionPerformed
        int resp =  validarEntrada(this.rut.getText(),  this.clave.getText());
        if(resp==0){
            boolean respuesta = false;
            try {
                respuesta = this.papa.getControladorPrincipal().eliminarUsuario(this.rut.getText(),this.clave.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PanelEliminarPropietario.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(respuesta){
                JOptionPane.showMessageDialog(null, "Se a eliminado correctamente");
                this.papa.cerrarSesion();
            }else{
                JOptionPane.showMessageDialog(null, "No se a podido eliminar su cuenta de usuario");
            }
        }
        if(resp==1){
            JOptionPane.showMessageDialog(null, "Error al rellenar el campo: Rut", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(resp==2){
            JOptionPane.showMessageDialog(null, "Error al rellenar el campo: Clave", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_botonEliminarCuentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminarCuenta;
    private javax.swing.JTextField clave;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField rut;
    // End of variables declaration//GEN-END:variables

    //Aca abajo van a estar los metodos que se tienen que hacer 
    
    /**
     * Este va a ser el formato de las consultas para ser luego testeadas en el junit
     * 0 = Correcto
     * numeros mayores que 0 son errores
     */

    /**
     * 
     * @param tipoUsuario
     * @param rut
     * @param clave
     * @return 
     */
    public int validarEntrada(String rut, String clave){
        if("".equals(rut)){
            return 1;
        }
        if("".equals(clave)){
            return 2;
        }
        return 0;
    }
}
