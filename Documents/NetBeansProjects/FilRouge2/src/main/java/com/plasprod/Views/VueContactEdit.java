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
    // objet de la page
    Contact contact = null;
    
    // Models pour les composants
    final DefaultComboBoxModel modelComboBoxClient;
    final DefaultComboBoxModel modelComboBoxCommercial;
    
    /**
     * Creates new form VueContactEdit
     */
    public VueContactEdit() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        switch (Singleton.getCurrent().editModeContact) {
            case CREATION:
                contact = new Contact();
                break;
                
            case MODIFICATION:
                contact = Singleton.getCurrent().contact;
                break;
        }
        
        ArrayList<Client> clients = DAOClient.getListClients();
        ArrayList<Commercial> commerciaux = DAOCommercial.getListCommerciaux();
        
        jTextFieldReference.setText(contact.getReference());
        jTextFieldNom.setText(contact.getNom());
        jTextFieldPrenom.setText(contact.getPrenom());
        jTextFieldEmail.setText(contact.getEmail());
        jTextFieldTelephone.setText(contact.getTelephone());
        jCheckBoxActif.setSelected(contact.isActif());
        
        modelComboBoxClient = (DefaultComboBoxModel)jComboBoxClient.getModel();
        modelComboBoxCommercial = (DefaultComboBoxModel)jComboBoxCommercial.getModel();
        modelComboBoxClient.removeAllElements();
        modelComboBoxCommercial.removeAllElements();
        
        for (Client client : clients) {
            modelComboBoxClient.addElement(client);
        }
        for (Commercial commercial : commerciaux) {
            modelComboBoxCommercial.addElement(commercial);
        }
        
        if (Singleton.getCurrent().editModeContact == EditMode.MODIFICATION) {
            Client client = DAOClient.getClient(contact.getIdClient());
            Commercial commercial = DAOCommercial.getCommercial(contact.getIdCommercial());
            
            jComboBoxClient.setSelectedItem(client);
            jComboBoxCommercial.setSelectedItem(commercial);
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
    }
    
    private void DisplayErrors() {
        // nom
        if (contact.getConstraintViolations().containsKey("NomIsValid")) {
            jLabelNomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelNomIsValid.setToolTipText(contact.getConstraintViolations().get("NomIsValid"));
        } else {
            jLabelNomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelNomIsValid.setToolTipText(null);
        }
        
        // prénom
        if (contact.getConstraintViolations().containsKey("PrenomIsValid")) {
            jLabelPrenomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelPrenomIsValid.setToolTipText(contact.getConstraintViolations().get("PrenomIsValid"));
        } else {
            jLabelPrenomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelPrenomIsValid.setToolTipText(null);
        }
        
        // email
        if (contact.getConstraintViolations().containsKey("EmailIsValid")) {
            jLabelEmailIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelEmailIsValid.setToolTipText(contact.getConstraintViolations().get("EmailIsValid"));
        } else {
            jLabelEmailIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelEmailIsValid.setToolTipText(null);
        }
        
        // téléphone
        if (contact.getConstraintViolations().containsKey("TelephoneIsValid")) {
            jLabelTelephoneIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelTelephoneIsValid.setToolTipText(contact.getConstraintViolations().get("TelephoneIsValid"));
        } else {
            jLabelTelephoneIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelTelephoneIsValid.setToolTipText(null);
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
        jLabelNomIsValid = new javax.swing.JLabel();
        jLabelPrenomIsValid = new javax.swing.JLabel();
        jLabelEmailIsValid = new javax.swing.JLabel();
        jLabelTelephoneIsValid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 400));

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

        jTextFieldReference.setEnabled(false);

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
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldPrenom)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jCheckBoxActif)
                        .addGap(353, 353, 353))
                    .addComponent(jTextFieldNom)
                    .addComponent(jTextFieldEmail)
                    .addComponent(jTextFieldTelephone)
                    .addComponent(jComboBoxClient, 0, 380, Short.MAX_VALUE)
                    .addComponent(jComboBoxCommercial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldReference))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNomIsValid)
                    .addComponent(jLabelPrenomIsValid)
                    .addComponent(jLabelEmailIsValid)
                    .addComponent(jLabelTelephoneIsValid))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEnregistrer)
                .addGap(18, 18, 18)
                .addComponent(jButtonAnnuler)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelNomIsValid)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabelPrenomIsValid))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelEmailIsValid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelTelephoneIsValid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCommercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jCheckBoxActif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEnregistrer)
                    .addComponent(jButtonAnnuler))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        contact.setReference(jTextFieldReference.getText());
        contact.setNom(jTextFieldNom.getText());
        contact.setPrenom(jTextFieldPrenom.getText());
        contact.setEmail(jTextFieldEmail.getText());
        contact.setTelephone(jTextFieldTelephone.getText());
        contact.setIdClient(((Client)jComboBoxClient.getSelectedItem()).getId());
        contact.setIdCommercial(((Commercial)jComboBoxCommercial.getSelectedItem()).getId());
        contact.setActif(jCheckBoxActif.isSelected());
        
        Boolean isValid = contact.isValid();
        DisplayErrors();
        
        if (isValid) {
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
        }
    }//GEN-LAST:event_jButtonEnregistrerMousePressed

    private void jButtonAnnulerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnulerMousePressed
        this.dispose();
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
    private javax.swing.JLabel jLabelEmailIsValid;
    private javax.swing.JLabel jLabelNomIsValid;
    private javax.swing.JLabel jLabelPrenomIsValid;
    private javax.swing.JLabel jLabelTelephoneIsValid;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldReference;
    private javax.swing.JTextField jTextFieldTelephone;
    // End of variables declaration//GEN-END:variables
}
