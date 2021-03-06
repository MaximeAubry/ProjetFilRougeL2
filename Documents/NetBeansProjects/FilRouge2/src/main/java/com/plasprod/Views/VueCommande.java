/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Views;

import com.plasprod.JDBC.DAOClient;
import com.plasprod.JDBC.DAOCommande;
import com.plasprod.JDBC.DAOContact;
import com.plasprod.JDBC.DAOSatisfaction;
import com.plasprod.Models.Client;
import com.plasprod.Models.Commande;
import com.plasprod.Models.Contact;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.Satisfaction;
import com.plasprod.Models.Singleton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Antoine Demarly
 */
public class VueCommande extends javax.swing.JFrame {
    ArrayList<Commande> commandes = new ArrayList<Commande>();

    /**
     * Creates new form VueCommande
     */
    public VueCommande() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        LoadCommandes();
    }
    
    private void LoadCommandes() {
        commandes = DAOCommande.getListCommandes();
        
        final DefaultTableModel model = (DefaultTableModel)jTableCommandes.getModel();
        model.getDataVector().removeAllElements();
        
        for (Commande commande : commandes) {
            Contact contact = DAOContact.getContact(commande.getIdContact());
            Client client = DAOClient.getClient(contact.getIdClient());
            
            Object[] obj = new Object[] { commande,client, contact, commande.getDateDeCreation(), commande.getStatutCommande() };
            model.addRow(obj);
        }
        
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run()
            {
                jTableCommandes.setModel(model);
                jTableCommandes.revalidate();
                jTableCommandes.repaint();
            }
        });
    }
    
    public void DislayCurrentCommande(Boolean refreshJtable) {
        if (refreshJtable) {
            // raffraichissement de la grille
            LoadCommandes();
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        // raffraichissement du bloc de droite
        Commande commande = Singleton.getCurrent().commande;
        Satisfaction satisfaction = Singleton.getCurrent().satisfaction;
        jLabelReference.setText(commande.getReference());
        jLabelDateDeCreation.setText(dateFormat.format(commande.getDateDeCreation()));
        jLabelDelaiExpedition.setText(commande.getDelaiExpedition() + " jours");
        jLabelStatutCommande.setText(commande.getStatutCommande().toString());
        
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        if (satisfaction == null) {
            jLabelCommentaire.setText(satisfaction.getCommentaire());
            jLabelNiveauSatisfaction.setText(satisfaction.getNiveauSatisfaction().toString());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCommandes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelReference = new javax.swing.JLabel();
        jLabelDateDeCreation = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelStatutCommande = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelDelaiExpedition = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelCommentaire = new javax.swing.JLabel();
        jLabelNiveauSatisfaction = new javax.swing.JLabel();
        jButtonSupprimer = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonAjouter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableCommandes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Référence", "Client", "Contact", "Date de création", "Statut"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCommandes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableCommandesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCommandes);
        if (jTableCommandes.getColumnModel().getColumnCount() > 0) {
            jTableCommandes.getColumnModel().getColumn(0).setResizable(false);
            jTableCommandes.getColumnModel().getColumn(1).setResizable(false);
            jTableCommandes.getColumnModel().getColumn(2).setResizable(false);
            jTableCommandes.getColumnModel().getColumn(3).setResizable(false);
            jTableCommandes.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Satisfaction client");

        jLabel5.setText("Référence");

        jLabel6.setText("Date de création");

        jLabel7.setText("Statut");

        jLabel8.setText("Délai d'expédition");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Votre commande");

        jLabel10.setText("Niveau de satisfaction");

        jLabel11.setText("Commentaire");

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.setEnabled(false);
        jButtonSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonSupprimerMousePressed(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.setEnabled(false);
        jButtonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonModifierMousePressed(evt);
            }
        });

        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.setEnabled(false);
        jButtonAjouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAjouterMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelDateDeCreation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelStatutCommande, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelDelaiExpedition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelCommentaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNiveauSatisfaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAjouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonModifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSupprimer)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelReference, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelDateDeCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelDelaiExpedition, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabelStatutCommande, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabelCommentaire, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabelNiveauSatisfaction, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAjouter)
                    .addComponent(jButtonModifier)
                    .addComponent(jButtonSupprimer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.getAccessibleContext().setAccessibleName("Votre commande");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableCommandesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCommandesMousePressed
        int rowIndex = jTableCommandes.getSelectedRow();
        Singleton.getCurrent().commande = (Commande)jTableCommandes.getValueAt(rowIndex, 0);
        DislayCurrentCommande(false);
    }//GEN-LAST:event_jTableCommandesMousePressed

    private void jButtonAjouterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAjouterMousePressed
        if (jButtonAjouter.isEnabled()) {
            Singleton.getCurrent().editModeSatisfaction = EditMode.CREATION;

            VueSatisfactionEdit vueSatisfactionEdit = new VueSatisfactionEdit();
            vueSatisfactionEdit.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAjouterMousePressed

    private void jButtonModifierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModifierMousePressed
        if (jButtonModifier.isEnabled()) {
            if (Singleton.getCurrent().satisfaction != null) {
                Singleton.getCurrent().editModeSatisfaction = EditMode.MODIFICATION;

                VueSatisfactionEdit vueSatisfactionEdit = new VueSatisfactionEdit();
                vueSatisfactionEdit.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Aucune satisfaction existente !", "Attention !", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonModifierMousePressed

    private void jButtonSupprimerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSupprimerMousePressed
        if (jButtonSupprimer.isEnabled()) {
            if (Singleton.getCurrent().satisfaction != null) {
                int retour = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir supprimer cette satisfaction ?", "Suppression", JOptionPane.YES_NO_OPTION);
                if( retour == JOptionPane.YES_NO_OPTION) {
                    DAOSatisfaction.suppressionSatisfaction(Singleton.getCurrent().satisfaction);
                    Singleton.getCurrent().satisfaction = null;
                    DislayCurrentCommande(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucune satisfaction existente !", "Attention !", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonSupprimerMousePressed

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
            java.util.logging.Logger.getLogger(VueCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueCommande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueCommande().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCommentaire;
    private javax.swing.JLabel jLabelDateDeCreation;
    private javax.swing.JLabel jLabelDelaiExpedition;
    private javax.swing.JLabel jLabelNiveauSatisfaction;
    private javax.swing.JLabel jLabelReference;
    private javax.swing.JLabel jLabelStatutCommande;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCommandes;
    // End of variables declaration//GEN-END:variables
}
