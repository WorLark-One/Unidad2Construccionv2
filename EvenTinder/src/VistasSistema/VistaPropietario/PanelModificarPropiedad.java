/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasSistema.VistaPropietario;

import ModuloGestionPropiedades.Propiedad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ArrayList<Propiedad> propiedades;
    
    public PanelModificarPropiedad(VentanaPrincipalPropietario papa) {
        this.papa=papa;
        initComponents();
        this.actualizarMenuOpciones();
        this.actualizarMenuOpcionesModificar();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ubicacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        valorArriendo = new javax.swing.JTextField();
        botonRegistrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        listaOpciones = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listaPropiedades = new javax.swing.JComboBox<>();

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

        jLabel3.setText("Nombre");

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        botonRegistrar.setText("Guardar Cambios");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel19)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonRegistrar)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ubicacion)
                                .addComponent(valorArriendo)
                                .addComponent(jScrollPane1)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valorArriendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(botonRegistrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("3. Agregar, modificar y/o eliminar sector");

        listaOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listaOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaOpcionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(listaOpciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(listaOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("1. Seleccione la propiedad");

        listaPropiedades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listaPropiedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaPropiedadesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(listaPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(listaPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(719, Short.MAX_VALUE))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubicacionActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        int resp = validarEntrada(this.nombre.getText(),this.descripcion.getText(), this.ubicacion.getText(), this.valorArriendo.getText());
        if(resp==0){
            //realizar operacion
            java.util.Date fechaDePublicacion = new Date();
            //falta id
            if(this.listaPropiedades.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(null, "No a seleccionado la propiedad a modificar", "Error al seleccionar propiedad", JOptionPane.WARNING_MESSAGE);    
                return;
            }
            for (int i = 0; i < this.propiedades.size(); i++) {
                if(this.listaPropiedades.getSelectedIndex()!=i){
                    if(this.propiedades.get(i).getNombre().equals(this.nombre.getText())){
                        JOptionPane.showMessageDialog(null, "Este nombre ya se encuentra en la base de datos", "Error ingreso de datos", JOptionPane.WARNING_MESSAGE); 
                        return;
                    }
                }
            }
            
            boolean resultado = false;
            try {
                System.out.println("Entre a modificar");
                resultado = this.papa.getControladorPropietario().modifcarPropiedad(this.propiedades.get(this.listaPropiedades.getSelectedIndex()).getId() ,this.nombre.getText(), this.ubicacion.getText(),fechaDePublicacion, this.propiedades.get(this.listaPropiedades.getSelectedIndex()).getCapacidadTotal(), Integer.parseInt(this.valorArriendo.getText()), this.descripcion.getText());
                System.out.println(resultado);
            } catch (SQLException ex) {
                System.out.println("Cayo");
                Logger.getLogger(PanelModificarPropiedad.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(resultado){
                //agregando sectores
                JOptionPane.showMessageDialog(null, "Se a modificado correctamente");
                this.nombre.setText("");
                this.descripcion.setText("");
                this.ubicacion.setText("");
                this.valorArriendo.setText("");
                this.actualizarMenuOpciones();
            }else{
                //fallo
                JOptionPane.showMessageDialog(null, "Error al registrar en la base de datos", "Error BD", JOptionPane.WARNING_MESSAGE);  
            }
        }
        
        //nombre
        if(resp==1){
            JOptionPane.showMessageDialog(null, "Error al rellenar el campo: nombre", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);    
            return;
        }
        //descripcion
        if(resp==2){
            JOptionPane.showMessageDialog(null, "Error al rellenar el campo: descripcion", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
        //ubicacion
        if(resp==3){
            JOptionPane.showMessageDialog(null, "Error al rellenar el campo: ubicacion", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
        //numero de sectores
        if(resp==4){
            JOptionPane.showMessageDialog(null, "Error al rellenar el campo: numero de sectores", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
        //valor de arriendo
        if(resp==5){
            JOptionPane.showMessageDialog(null, "Error al rellenar el campo: valor de arriendo", "Error al llenado de datos", JOptionPane.WARNING_MESSAGE);            
            return;
        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void listaOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaOpcionesActionPerformed
        // TODO add your handling code here:
        if(this.listaOpciones.getSelectedIndex()==-1){
            return;
        }
        if(this.listaOpciones.getSelectedIndex()==0){
            return;
        }
        if(this.listaOpciones.getSelectedIndex()==1){
            PanelAgregarSector sector = new PanelAgregarSector(this.papa, this.listaPropiedades.getSelectedIndex());
            this.papa.añadirSector(sector);
            this.actualizarMenuOpcionesModificar();
            return;
        }
        if(this.listaOpciones.getSelectedIndex()==2){
            PanelModificarSector sector = new PanelModificarSector(this.papa, this.listaPropiedades.getSelectedIndex());
            this.papa.modificarSector(sector);
            this.actualizarMenuOpcionesModificar();
            return;
        }
        if(this.listaOpciones.getSelectedIndex()==3){
            PanelEliminarSector sector = new PanelEliminarSector(this.papa, this.listaPropiedades.getSelectedIndex());
            this.papa.eliminarSector(sector);
            this.actualizarMenuOpcionesModificar();
            return;
        }
    }//GEN-LAST:event_listaOpcionesActionPerformed

    private void listaPropiedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaPropiedadesActionPerformed
        // TODO add your handling code here:
        if(this.listaPropiedades.getSelectedIndex()==-1){
            return;
        }
        this.nombre.setText(this.propiedades.get(this.listaPropiedades.getSelectedIndex()).getNombre());
        this.descripcion.setText(this.propiedades.get(this.listaPropiedades.getSelectedIndex()).getDescripcion());
        this.ubicacion.setText(this.propiedades.get(this.listaPropiedades.getSelectedIndex()).getUbicacion());
        this.valorArriendo.setText(Integer.toString(this.propiedades.get(this.listaPropiedades.getSelectedIndex()).getValorArriendo()));
    }//GEN-LAST:event_listaPropiedadesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listaOpciones;
    private javax.swing.JComboBox<String> listaPropiedades;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField ubicacion;
    private javax.swing.JTextField valorArriendo;
    // End of variables declaration//GEN-END:variables

    //Aca abajo van a estar los metodos que se tienen que hacer 
    
    /**
     * Este va a ser el formato de las consultas para ser luego testeadas en el junit
     * 0 = Correcto
     * numeros mayores que 0 son errores
     */

    //
    // no se puede hacer tdd ya que necesita otro metodo
    public void actualizarMenuOpciones(){
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
    
    public void actualizarMenuOpcionesModificar(){
        this.listaOpciones.removeAllItems();
        this.listaOpciones.addItem("");
        this.listaOpciones.addItem("Agregar sector");
        this.listaOpciones.addItem("Modificar sector");
        this.listaOpciones.addItem("Eliminar Sector");
    }
    
    /**
     * 
     * @param nombre
     * @param descripcion
     * @param ubicacion
     * @param nSectores
     * @param valorArriendo
     * @param finalizar
     * @return 
     */
    public int validarEntrada(String nombre, String descripcion, String ubicacion, String valorArriendo) {
        if(nombre.equals("")){
            return 1;
        }
        if(descripcion.equals("")){
            return 2;
        }
        if(ubicacion.equals("")){
            return 3;
        }
        if(valorArriendo.equals("") || !isNumero(valorArriendo)){
            return 5;
        }
        return 0;
    }

    
            /**
     * Método que se encarga de verificar que los numeros ingresados son numeros validos
     */
    private boolean isNumero(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        if (resultado==true) {
            int a = Integer.parseInt(cadena);
            if (a>0) {
                resultado = true;
            }
            else{
                resultado = false;
            }
        }
        return resultado;
    }
}
