package com.plasprod.Views;

import com.plasprod.JDBC.DAOArticle;
import com.plasprod.JDBC.DAOClient;
import com.plasprod.JDBC.DAODevis;
import com.plasprod.JDBC.DAOLigneDeDocument;
import com.plasprod.Models.Article;
import com.plasprod.Models.Client;
import com.plasprod.Models.Commercial;
import com.plasprod.Models.Devis;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.LigneDeDocument;
import com.plasprod.Models.Singleton;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class VueDevisEdit extends javax.swing.JFrame {
    ArrayList<LigneDeDocument> lignesDeDocument = new ArrayList<LigneDeDocument>();
    final DefaultComboBoxModel modelComboBoxClient;
    final DefaultTableModel modelComboBoxLignesDeDocument;

    /**
     * Creates new form VueDevisEdit
     */
    public VueDevisEdit() {
        initComponents();
        
        ArrayList<Client> clients = DAOClient.getListClients();
        modelComboBoxClient = (DefaultComboBoxModel)jComboBoxClient.getModel();
        modelComboBoxLignesDeDocument = (DefaultTableModel)jTableLigneDeDocument.getModel();
        modelComboBoxClient.removeAllElements();
        modelComboBoxLignesDeDocument.getDataVector().removeAllElements();
        
        for (Client client : clients) {
            modelComboBoxClient.addElement(client);
        }
        
        Devis devis = Singleton.getCurrent().devis;
        jTextFieldReference.setText(devis.getReference());
        jDateChooserDateDeCreation.setDate(devis.getDateDeCreation());
        jComboBoxClient.setSelectedItem(devis);
        jDateChooserDateDeValidite.setDate(devis.getDateDeFinDeValidite());
        jCheckBoxSigne.setSelected(devis.isSigne());
        jSpinnerMontantTotalHT.setValue(0);
        jSpinnerRemise.setValue(devis.getRemise());
        jSpinnerFraisDeTransport.setValue(devis.getFraisDeTransport());
        jSpinnerMontantTotalTTC.setValue(0);
        
        if (Singleton.getCurrent().editModeDevis == EditMode.MODIFICATION) {
            lignesDeDocument = DAOLigneDeDocument.getListLigneDeDocuments(devis.getId());
            for (LigneDeDocument ligneDeDocument : lignesDeDocument) {
                Article article = DAOArticle.getArticle(ligneDeDocument.getIdArticle());
                Double Remise = (1 - (ligneDeDocument.getRemise() / 100));
                Double prixTotal = ((article.getPrixUnitaire() * ligneDeDocument.getQte()) * Remise);
                Object[] obj = new Object[] { ligneDeDocument, article.getReference(), article.getNom(), article.getQuantiteStock(), ligneDeDocument.getQte(), ligneDeDocument.getRemise(), article.getPrixUnitaire(), prixTotal };
                modelComboBoxLignesDeDocument.addRow(obj);
            }
        }
        
        LigneDeDocument ligneDeDocument = new LigneDeDocument();
        ligneDeDocument.setIdDocument(devis.getId());
        ligneDeDocument.setNumeroDeLigne(1);
        ligneDeDocument.setQte(0);
        ligneDeDocument.setRemise(0);
        Object[] obj = new Object[] { ligneDeDocument, null, null, 0, 0, 0, 0, 0 };
        modelComboBoxLignesDeDocument.addRow(obj);
        
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run()
            {
                jComboBoxClient.setModel(modelComboBoxClient);
                jComboBoxClient.revalidate();
                jComboBoxClient.repaint();
                
                jTableLigneDeDocument.setModel(modelComboBoxLignesDeDocument);
                jTableLigneDeDocument.revalidate();
                jTableLigneDeDocument.repaint();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTextFieldReference = new javax.swing.JTextField();
        jButtonEnregistrer = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLigneDeDocument = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxTVA = new javax.swing.JComboBox();
        jCheckBoxSigne = new javax.swing.JCheckBox();
        jSpinnerRemise = new javax.swing.JSpinner();
        jSpinnerFraisDeTransport = new javax.swing.JSpinner();
        jSpinnerMontantTotalHT = new javax.swing.JSpinner();
        jComboBoxClient = new javax.swing.JComboBox();
        jDateChooserDateDeCreation = new com.toedter.calendar.JDateChooser();
        jDateChooserDateDeValidite = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jSpinnerMontantTotalTTC = new javax.swing.JSpinner();
        jButtonAjouterArticle = new javax.swing.JButton();
        jButtonNouvelleLigne = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setText("Référence");

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

        jLabel7.setText("Date de création");

        jLabel8.setText("Client");

        jLabel9.setText("Date de validité");

        jLabel10.setText("Signé");

        jTableLigneDeDocument.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Numéro de ligne", "Référence", "Nom", "Quantité en stock", "Quantité", "Remise", "Prix unitaire", "Prix total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableLigneDeDocument);
        if (jTableLigneDeDocument.getColumnModel().getColumnCount() > 0) {
            jTableLigneDeDocument.getColumnModel().getColumn(0).setResizable(false);
            jTableLigneDeDocument.getColumnModel().getColumn(1).setResizable(false);
            jTableLigneDeDocument.getColumnModel().getColumn(2).setResizable(false);
            jTableLigneDeDocument.getColumnModel().getColumn(3).setResizable(false);
            jTableLigneDeDocument.getColumnModel().getColumn(4).setResizable(false);
            jTableLigneDeDocument.getColumnModel().getColumn(5).setResizable(false);
            jTableLigneDeDocument.getColumnModel().getColumn(6).setResizable(false);
            jTableLigneDeDocument.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel11.setText("Montant total HT");

        jLabel12.setText("Remise");

        jLabel13.setText("Frais de transport");

        jLabel14.setText("Taux de TVA");

        jSpinnerMontantTotalHT.setEnabled(false);

        jLabel15.setText("Montant total TTC");

        jSpinnerMontantTotalTTC.setEnabled(false);

        jButtonAjouterArticle.setText("Nouvel article");
        jButtonAjouterArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterArticleActionPerformed(evt);
            }
        });

        jButtonNouvelleLigne.setText("Nouvelle ligne");
        jButtonNouvelleLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNouvelleLigneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonEnregistrer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAnnuler))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxClient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxSigne)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jDateChooserDateDeCreation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserDateDeValidite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldReference)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxTVA, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerFraisDeTransport, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSpinnerMontantTotalHT)
                            .addComponent(jSpinnerRemise)
                            .addComponent(jSpinnerMontantTotalTTC)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAjouterArticle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNouvelleLigne)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooserDateDeCreation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jDateChooserDateDeValidite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jCheckBoxSigne))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAjouterArticle)
                    .addComponent(jButtonNouvelleLigne))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jSpinnerMontantTotalHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jSpinnerRemise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jSpinnerFraisDeTransport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBoxTVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jSpinnerMontantTotalTTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonEnregistrer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        Devis devis = Singleton.getCurrent().devis;
        Client client = (Client)jComboBoxClient.getSelectedItem();
        Commercial commercial = Singleton.getCurrent().me;
        
        Calendar dateDeCreation = Calendar.getInstance();
        Calendar dateDeFinDeValidite = Calendar.getInstance();
        dateDeCreation.setTime(jDateChooserDateDeCreation.getDate());
        dateDeFinDeValidite.setTime(jDateChooserDateDeValidite.getDate());
        
        devis.setReference(jTextFieldReference.getText());
        devis.setDateDeCreation(new Date(dateDeCreation.getTime().getTime()));
        devis.setIdClient(client.getId());
        devis.setIdCommercial(commercial.getId());
        devis.setDateDeFinDeValidite(new Date(dateDeFinDeValidite.getTime().getTime()));
        devis.setSigne(jCheckBoxSigne.isSelected());
        devis.setRemise((Double)jSpinnerRemise.getValue());
        devis.setFraisDeTransport((Double)jSpinnerFraisDeTransport.getValue());
        devis.setTauxDeTva((Double)jComboBoxTVA.getSelectedItem());
        
        switch (Singleton.getCurrent().editModeDevis) {
            case CREATION:
                //DAODevis.ajoutDevis(devis);
                break;
                
            case MODIFICATION:
                //DAODevis.modificationDevis(devis);
                break;
        }
        
        this.dispose();
        Singleton.getCurrent().vueClient.DislayCurrentClient(true);
    }//GEN-LAST:event_jButtonEnregistrerMousePressed

    private void jButtonAnnulerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnulerMousePressed
        
    }//GEN-LAST:event_jButtonAnnulerMousePressed

    private void jButtonNouvelleLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNouvelleLigneActionPerformed
        int nbLignes = jTableLigneDeDocument.getRowCount();
        
        Devis devis = Singleton.getCurrent().devis;
        LigneDeDocument ligneDeDocument = new LigneDeDocument();
        ligneDeDocument.setIdDocument(devis.getId());
        ligneDeDocument.setNumeroDeLigne(nbLignes + 1);
        ligneDeDocument.setQte(0);
        ligneDeDocument.setRemise(0);
        lignesDeDocument.add(ligneDeDocument);
        
        Object[] obj = new Object[] { ligneDeDocument, null, null, 0, 0, 0, 0, 0 };
        modelComboBoxLignesDeDocument.addRow(obj);
    }//GEN-LAST:event_jButtonNouvelleLigneActionPerformed

    private void jButtonAjouterArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterArticleActionPerformed
        int rowIndex = jTableLigneDeDocument.getSelectedRow();
        int columnIndex = jTableLigneDeDocument.getSelectedColumn();
        String Reference = (String)jTableLigneDeDocument.getCellEditor(rowIndex,columnIndex).getCellEditorValue();
        
        Devis devis = Singleton.getCurrent().devis;
        LigneDeDocument ligneDeDocument = (LigneDeDocument)jTableLigneDeDocument.getValueAt(rowIndex, 0);
        
        Article article = DAOArticle.getArticle(Reference);
        if (article != null) {
            ligneDeDocument.setIdArticle(article.getId());
            Double Remise = (1 - (ligneDeDocument.getRemise() / 100));
            Double prixTotal = ((article.getPrixUnitaire() * ligneDeDocument.getQte()) * Remise);
            Object[] obj = new Object[] { ligneDeDocument, article.getReference(), article.getNom(), article.getQuantiteStock(), ligneDeDocument.getQte(), ligneDeDocument.getRemise(), article.getPrixUnitaire(), prixTotal };
            modelComboBoxLignesDeDocument.setValueAt(obj, rowIndex, columnIndex);
        }
    }//GEN-LAST:event_jButtonAjouterArticleActionPerformed

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
            java.util.logging.Logger.getLogger(VueDevisEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueDevisEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueDevisEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueDevisEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueDevisEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouterArticle;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonEnregistrer;
    private javax.swing.JButton jButtonNouvelleLigne;
    private javax.swing.JCheckBox jCheckBoxSigne;
    private javax.swing.JComboBox jComboBoxClient;
    private javax.swing.JComboBox jComboBoxTVA;
    private com.toedter.calendar.JDateChooser jDateChooserDateDeCreation;
    private com.toedter.calendar.JDateChooser jDateChooserDateDeValidite;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerFraisDeTransport;
    private javax.swing.JSpinner jSpinnerMontantTotalHT;
    private javax.swing.JSpinner jSpinnerMontantTotalTTC;
    private javax.swing.JSpinner jSpinnerRemise;
    private javax.swing.JTable jTableLigneDeDocument;
    private javax.swing.JTextField jTextFieldReference;
    // End of variables declaration//GEN-END:variables
}