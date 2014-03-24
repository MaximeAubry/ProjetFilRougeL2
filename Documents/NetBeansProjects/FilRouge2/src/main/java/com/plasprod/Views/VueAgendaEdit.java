/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Views;

import com.plasprod.Models.Contact;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.Evenement;
import com.plasprod.Models.Singleton;
import com.plasprod.Models.TypeRdv;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxime
 */
public class VueAgendaEdit extends javax.swing.JFrame {
    private void disableHeuresUtilisees() {
        List<Evenement> evenements = new ArrayList<Evenement>();
        List<Time> heuresUtilisees = new ArrayList<Time>();
        
        for (Evenement evenement : evenements) {
            Calendar dateDeDebut = Calendar.getInstance();
            dateDeDebut.setTime(evenement.getDateDeDebut());
            int heureDeDebut = dateDeDebut.get(Calendar.HOUR_OF_DAY);
            
            Calendar dateDeFin = Calendar.getInstance();
            dateDeFin.setTime(evenement.getDateDeFin());
            int heureDeFin = dateDeFin.get(Calendar.HOUR_OF_DAY);
            
            for(int i = heureDeDebut; i < heureDeFin; i++) {
                Time time = new Time((i - 1) * 60 * 60 * 1000);
                
                //jComboBoxHeureDeDebut.getItemAt(i)
                //jComboBoxHeureDeFin.
            }
        }
    }
    
    /**
     * Creates new form VueAgendaEdit
     */
    public VueAgendaEdit() {
        initComponents();
        
        Evenement evenement = Singleton.getCurrent().evenement;
        List<Contact> contacts = new ArrayList<Contact>();
        
        jComboBoxType.setModel(new DefaultComboBoxModel(TypeRdv.values()));
        jTextAreaCommentaire.setText(evenement.getCommentaire());
        
//        jDateChooserDate.setDate(evenement.getDateDeDebut());
        
        // heure de début
        Time selectedTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for(int i = 8; i <= 22; i++) {
            Time time = new Time((i - 1) * 60 * 60 * 1000);
            if (Singleton.getCurrent().editModeEvenement == EditMode.MODIFICATION) {
                selectedTime = time;
            }
            jComboBoxHeureDeDebut.addItem(time);
        }
        jComboBoxHeureDeDebut.setSelectedItem(selectedTime);
        
        // heure de fin
        Time duree = new Time(evenement.getDateDeFin().getTime() - evenement.getDateDeDebut().getTime());
        Calendar dateDeFin = Calendar.getInstance();
        dateDeFin.setTime(duree);
        jSpinnerDuree.setValue(dateDeFin.get(Calendar.HOUR_OF_DAY));
        
        // contact
 /*       ComboBoxItem comboBoxContact = null;
        ComboBoxItem selectedComboBoxContact = null;
        for(Contact contact : contacts) {
            StringBuilder Nom = new StringBuilder().append(contact.getPrenom()).append(" ").append(contact.getNom());
            comboBoxContact = new ComboBoxItem(contact.getId(), Nom.toString());
            if (Singleton.getCurrent().editModeEvenement == EditMode.MODIFICATION) {
                selectedComboBoxContact = comboBoxContact;
            }
            jComboBoxContact.addItem(comboBoxContact);
        }
        if (Singleton.getCurrent().editModeEvenement == EditMode.MODIFICATION) {
            jComboBoxContact.setSelectedItem(selectedComboBoxContact);
        }*/
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
        jComboBoxType = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPaneDuree = new javax.swing.JScrollPane();
        jTextAreaCommentaire = new javax.swing.JTextArea();
        jComboBoxHeureDeDebut = new javax.swing.JComboBox();
        jComboBoxContact = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jSpinnerDuree = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 240));

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

        jLabel1.setText("Type");

        jLabel2.setText("Date");

        jLabel3.setText("Heure de début");

        jLabel4.setText("Durée");

        jLabel5.setText("Commentaire");

        jTextAreaCommentaire.setColumns(20);
        jTextAreaCommentaire.setRows(5);
        jScrollPaneDuree.setViewportView(jTextAreaCommentaire);

        jComboBoxHeureDeDebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHeureDeDebutActionPerformed(evt);
            }
        });

        jLabel6.setText("Contact");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonEnregistrer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAnnuler))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxHeureDeDebut, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jSpinnerDuree))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneDuree, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxContact, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxHeureDeDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerDuree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneDuree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAnnuler)
                            .addComponent(jButtonEnregistrer))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        Evenement evenement = Singleton.getCurrent().evenement;
        
        // Date de début
/*        Date dateDeDebut = jDateChooserDate.getDate();
        Time heureDeDebut = (Time)jComboBoxHeureDeDebut.getSelectedItem();
        dateDeDebut.setTime(dateDeDebut.getTime() + heureDeDebut.getTime());
        
        // Date de fin
        int duree = (int)jSpinnerDuree.getValue();
        Date dateDeFin = dateDeDebut;
        dateDeFin.setTime(heureDeDebut.getTime() + (duree * 60 * 60));
        
        evenement.setTypeRDV((TypeRdv)jComboBoxType.getSelectedItem());
        evenement.setDateDeDebut(dateDeDebut);
        evenement.setDateDeFin(dateDeFin);
        evenement.setCommentaire(jTextAreaCommentaire.getText());
        evenement.setIdContact(((Contact)jComboBoxContact.getSelectedItem()).getId());
        
        switch (Singleton.getCurrent().editModeEvenement) {
            case CREATION:
                
                break;
                
            case MODIFICATION:
                
                break;
        }*/
    }//GEN-LAST:event_jButtonEnregistrerMousePressed

    private void jButtonAnnulerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnulerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAnnulerMousePressed

    private void jComboBoxHeureDeDebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHeureDeDebutActionPerformed
        Time time = (Time)jComboBoxHeureDeDebut.getSelectedItem();
        
        
        JOptionPane.showMessageDialog(null, "ddd");
    }//GEN-LAST:event_jComboBoxHeureDeDebutActionPerformed

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
            java.util.logging.Logger.getLogger(VueAgendaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueAgendaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueAgendaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueAgendaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueAgendaEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonEnregistrer;
    private javax.swing.JComboBox jComboBoxContact;
    private javax.swing.JComboBox jComboBoxHeureDeDebut;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPaneDuree;
    private javax.swing.JSpinner jSpinnerDuree;
    private javax.swing.JTextArea jTextAreaCommentaire;
    // End of variables declaration//GEN-END:variables
}
