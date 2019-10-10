/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;

import ModuloGestionPropiedades.Propiedad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author xebae
 */
public class PanelEliminarPropiedad extends javax.swing.JPanel {

    /**
     * Creates new form PanelEliminarPropiedad
     */
    
    private ArrayList<Propiedad> propiedades;
    private VentanaPrincipalPropietario papa;
    
    public PanelEliminarPropiedad(VentanaPrincipalPropietario papa) {
        this.papa=papa;
        initComponents();
        this.actualizarMenuOpciones();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botonEliminarCuenta = new javax.swing.JButton();
        listaPropiedades = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("Menú Eliminar propiedad");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("1. Seleccione la propiedad");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VistasSistema/Imagenes/IconoEvenTinder.png"))); // NOI18N

        botonEliminarCuenta.setText("Eliminar Propiedad");
        botonEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarCuentaActionPerformed(evt);
            }
        });

        listaPropiedades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonEliminarCuenta)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(listaPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(194, Short.MAX_VALUE))
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
                .addComponent(listaPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonEliminarCuenta)
                .addContainerGap(139, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarCuentaActionPerformed
        if(this.listaPropiedades.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null, "No a seleccionado la propiedad a modificar", "Error al seleccionar propiedad", JOptionPane.WARNING_MESSAGE);    
            return;
        }
        boolean resultado = false;
        try {
            resultado = this.papa.getControladorPropietario().eliminarPropiedad(this.propiedades.get(this.listaPropiedades.getSelectedIndex()).getId());
        } catch (SQLException ex) {
            Logger.getLogger(PanelEliminarPropiedad.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(resultado){
            //agregando sectores
            JOptionPane.showMessageDialog(null, "Se a eliminado correctamente");
            this.actualizarMenuOpciones();
        }else{
            //fallo
            JOptionPane.showMessageDialog(null, "Error al registrar en la base de datos", "Error BD", JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_botonEliminarCuentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminarCuenta;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> listaPropiedades;
    // End of variables declaration//GEN-END:variables

    //Aca abajo van a estar los metodos que se tienen que hacer 
    
    /**
     * Este va a ser el formato de las consultas para ser luego testeadas en el junit
     * 0 = Correcto
     * numeros mayores que 0 son errores
     */
    
    //no existen validaciones posibles en este panel
    // no se puede hacer tdd ya que necesita otro metodo
    private void actualizarMenuOpciones(){
        this.propiedades = this.papa.getControladorPropietario().mostrarInformacionDePropiedades();
        this.listaPropiedades.removeAllItems();
        if(this.propiedades!=null){
            for(int i=0; i<this.propiedades.size(); i++){
                this.listaPropiedades.addItem("Nombre : " + this.propiedades.get(i).getNombre());
            }
            this.repaint();
            this.revalidate();
        }
    }
}
