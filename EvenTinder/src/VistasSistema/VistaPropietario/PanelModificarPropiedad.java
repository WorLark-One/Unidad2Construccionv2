/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;

import javax.swing.JOptionPane;

/**
 *
 * @author xebae
 */
public class PanelModificarPropiedad extends javax.swing.JPanel {

    /**
     * Creates new form PanelModificarPropiedad
     */
    
    private VentanaPrincipalPropietario papa;
    private int id;
    
    public PanelModificarPropiedad(VentanaPrincipalPropietario papa) {
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

        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        numeroDeSectores = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ubicacion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        botonRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        valorArriendo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        listaPropiedades = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        agregarSector = new javax.swing.JButton();
        modificarSector = new javax.swing.JButton();
        eliminarSector = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VistasSistema/Imagenes/IconoEvenTinder.png"))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("Menú Modificar una propiedad");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("2. Ingrese los datos que desee modificar de la propiedad");

        jLabel12.setText("Descripción");

        jLabel15.setText("Valor de arriendo");

        jLabel13.setText("Ubicación");

        ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubicacionActionPerformed(evt);
            }
        });

        jLabel14.setText("N° de sectores");

        botonRegistrar.setText("Guardar Cambios");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("1. Seleccione la propiedad a modificar");

        listaPropiedades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("3. Agregar, modificar y/o eliminar sector");

        agregarSector.setText("Agregar");
        agregarSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarSectorActionPerformed(evt);
            }
        });

        modificarSector.setText("Modificar");
        modificarSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarSectorActionPerformed(evt);
            }
        });

        eliminarSector.setText("Eliminar");
        eliminarSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarSectorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel12)
                        .addComponent(jLabel19)
                        .addComponent(jLabel18)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ubicacion)
                                .addComponent(numeroDeSectores)
                                .addComponent(valorArriendo)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                        .addComponent(listaPropiedades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonRegistrar, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregarSector)
                        .addGap(18, 18, 18)
                        .addComponent(modificarSector)
                        .addGap(18, 18, 18)
                        .addComponent(eliminarSector)))
                .addContainerGap(61, Short.MAX_VALUE))
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
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(listaPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroDeSectores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valorArriendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarSector)
                    .addComponent(modificarSector)
                    .addComponent(eliminarSector))
                .addGap(60, 60, 60)
                .addComponent(botonRegistrar)
                .addContainerGap(118, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubicacionActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        // TODO add your handling code here:
        int resp = validarEntradaRegistrar();
        if(resp==0){
            //realizar operacion
            
        }
        //nombre
        if(resp==1){
            JOptionPane.showMessageDialog(null, "Le falto rellenar el campo: nombre", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);    
            return;
        }
        //descripcion
        if(resp==2){
            JOptionPane.showMessageDialog(null, "Le falto rellenar el campo: descripcion", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
        //ubicacion
        if(resp==3){
            JOptionPane.showMessageDialog(null, "Le falto rellenar el campo: ubicacion", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
        //numero de sectores
        if(resp==4){
            JOptionPane.showMessageDialog(null, "Le falto rellenar el campo: numero de sectores", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
        //valor de arriendo
        if(resp==5){
            JOptionPane.showMessageDialog(null, "Le falto rellenar el campo: valor de arriendo", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void eliminarSectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarSectorActionPerformed
        if(this.listaPropiedades.getUI().equals("")){
            JOptionPane.showMessageDialog(null, "No a seleccionado una propiedad", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE); 
            return;
        }
        int id=0;
        PanelEliminarSector sector = new PanelEliminarSector(this.papa, id);
        this.papa.eliminarSector(sector);
        
        
    }//GEN-LAST:event_eliminarSectorActionPerformed

    private void agregarSectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarSectorActionPerformed
        if(this.listaPropiedades.getUI().equals("")){
            JOptionPane.showMessageDialog(null, "No a seleccionado una propiedad", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE); 
            return;
        }
        int id=0;
        PanelAgregarSector sector = new PanelAgregarSector(this.papa, id);
        this.papa.añadirSector(sector);
        
        
    }//GEN-LAST:event_agregarSectorActionPerformed

    private void modificarSectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarSectorActionPerformed
        if(this.listaPropiedades.getUI().equals("")){
            JOptionPane.showMessageDialog(null, "No a seleccionado una propiedad", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE); 
            return;
        }
        int id=0;
        PanelModificarSector sector = new PanelModificarSector(this.papa, id);
        this.papa.modificarSector(sector);
        
    }//GEN-LAST:event_modificarSectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarSector;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JButton eliminarSector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listaPropiedades;
    private javax.swing.JButton modificarSector;
    private javax.swing.JTextField numeroDeSectores;
    private javax.swing.JTextField ubicacion;
    private javax.swing.JTextField valorArriendo;
    // End of variables declaration//GEN-END:variables

    //Aca abajo van a estar los metodos que se tienen que hacer 
    
    /**
     * Este va a ser el formato de las consultas para ser luego testeadas en el junit
     * 0 = Correcto
     * numeros mayores que 0 son errores
     */

    public int validarEntradaRegistrar() {
        
        return 0;
    }

}