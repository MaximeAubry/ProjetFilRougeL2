/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Views;

import com.plasprod.JDBC.DAOClient;
import com.plasprod.JDBC.DAOCommercial;
import com.plasprod.JDBC.DAOContact;
import com.plasprod.Models.Client;
import com.plasprod.Models.Commercial;
import com.plasprod.Models.Contact;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.Singleton;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;


/**
 *
 * @author Maxime
 */
public class VueContactEdit extends javax.swing.JFrame {
    ArrayList<Client> clients = new ArrayList<Client>();
    ArrayList<Commercial> commerciaux = new ArrayList<Commercial>();
    
    /**
     * Creates new form VueContactEdit
     */
    public VueContactEdit() {
        initComponents();
        
        clients = DAOClient.getListClients();
        commerciaux = DAOCommercial.getListCommerciaux();
        
        Contact contact = Singleton.getCurrent().contact;
        jTextFieldReference.setText(contact.getReference());
        jTextFieldNom.setText(contact.getNom());
        jTextFieldPrenom.setText(contact.getPrenom());
        jTextFieldEmail.setText(contact.getEmail());
        jTextFieldTelephone.setText(contact.getTelephone());
        jCheckBoxActif.setSelected(contact.isActif());
        
        final DefaultComboBoxModel modelComboBoxClient = (DefaultComboBoxModel)jComboBoxClient.getModel();
        final DefaultComboBoxModel modelComboBoxCommercial = (DefaultComboBoxModel)jComboBoxCommercial.getModel();
        
        modelComboBoxClient.removeAllElements();
        modelComboBoxCommercial.removeAllElements();
        
        for (Client client : clients) {
            modelComboBoxClient.addElement(client);
        }
        for (Commercial commercial : commerciaux) {
            modelComboBoxCommercial.addElement(commercial);
        }
        
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run()
            {
                jComboBoxClient.setModel(modelComboBoxClient);
                jComboBoxClient.revalidate();
                jComboBoxClient.repaint();
                
                jComboBoxCommercial.setModel(modelComboBoxCommercial);
                jComboBoxCommercial.revalidate();
                jComboBoxCommercial.repaint();
            }
        });
        
        if (Singleton.getCurrent().editModeContact == EditMode.MODIFICATION) {
            Client client = DAOClient.getClient(contact.getIdClient());
            Commercial commercial = DAOCommercial.getCommercial(contact.getIdCommercial());
            
            jComboBoxClient.setSelectedItem(client);
            jComboBoxCommercial.setSelectedItem(commercial);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonEnregistrer = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldReference = new javax.swing.JTextField();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPrenom = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTelephone = new javax.swing.JTextField();
        jCheckBoxActif = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxClient = new javax.swing.JComboBox();
        jComboBoxCommercial = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonEnregistrer.setText("Enregistrer");
        jButtonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonEnregistrerMousePressed(evt);
            }
        });

        jButtonAnnuler.setText("Fermer");
        jButtonAnnuler.setToolTipText("");
        jButtonAnnuler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAnnulerMousePressed(evt);
            }
        });

        jLabel1.setText("Nom");

        jLabel2.setText("Prénom");

        jLabel3.setText("Email");

        jLabel4.setText("Téléphone");

        jLabel6.setText("Référence");

        jLabel7.setText("Actif");

        jLabel8.setText("Client");

        jLabel9.setText("Commercial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 222, Short.MAX_VALUE)
                        .addComponent(jButtonEnregistrer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAnnuler))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldReference)
                            .addComponent(jTextFieldNom)
                            .addComponent(jTextFieldPrenom)
                            .addComponent(jTextFieldEmail)
                            .addComponent(jTextFieldTelephone)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxActif)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBoxCommercial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxClient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCommercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jCheckBoxActif))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonEnregistrer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        Contact contact = Singleton.getCurrent().contact;
        
        contact.setReference(jTextFieldReference.getText());
        contact.setNom(jTextFieldNom.getText());
        contact.setPrenom(jTextFieldPrenom.getText());
        contact.setEmail(jTextFieldEmail.getText());
        contact.setTelephone(jTextFieldTelephone.getText());
        contact.setIdClient(((Client)jComboBoxClient.getSelectedItem()).getId());
        contact.setIdCommercial(((Commercial)jComboBoxCommercial.getSelectedItem()).getId());
        contact.setActif(jCheckBoxActif.isSelected());
        
        switch (Singleton.getCurrent().editModeContact) {
            case CREATION:
                DAOContact.ajoutContact(contact);
                break;
                
            case MODIFICATION:
                DAOContact.modificationContact(contact);
                break;
        }
        
        this.dispose();
        Singleton.getCurrent().vueContact.DislayCurrentContact(true);
    }//GEN-LAST:event_jButtonEnregistrerMousePressed

    private void jButtonAnnulerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnulerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAnnulerMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VueContactEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueContactEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueContactEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueContactEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueContactEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonEnregistrer;
    private javax.swing.JCheckBox jCheckBoxActif;
    private javax.swing.JComboBox jComboBoxClient;
    private javax.swing.JComboBox jComboBoxCommercial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldReference;
    private javax.swing.JTextField jTextFieldTelephone;
    // End of variables declaration//GEN-END:variables
}
