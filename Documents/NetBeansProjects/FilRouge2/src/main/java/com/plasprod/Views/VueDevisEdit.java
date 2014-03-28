package com.plasprod.Views;

import com.plasprod.CustomLibraries.JSpinnerChangeCustom;
import com.plasprod.CustomLibraries.JTextFieldFocusCustom;
import com.plasprod.CustomLibraries.SpinnerEditor;
import com.plasprod.JDBC.DAOArticle;
import com.plasprod.JDBC.DAOCommande;
import com.plasprod.JDBC.DAOContact;
import com.plasprod.JDBC.DAODevis;
import com.plasprod.JDBC.DAOLigneDeDocument;
import com.plasprod.Models.Article;
import com.plasprod.Models.Commande;
import com.plasprod.Models.Contact;
import com.plasprod.Models.Commercial;
import com.plasprod.Models.Contact;
import com.plasprod.Models.Devis;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.LigneDeDocument;
import com.plasprod.Models.Singleton;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class VueDevisEdit extends javax.swing.JFrame {
    ArrayList<LigneDeDocument> lignesDeDocument = new ArrayList<LigneDeDocument>();
    final DefaultComboBoxModel modelComboBoxContact;
    final DefaultTableModel modelTableLignesDeDocument;
    final SpinnerModel modelSpinnerRemise;
    final SpinnerModel modelSpinnerFraisDeTransport;
    
    private void jTableSettings() {
        TableColumn referenceColumn = jTableLigneDeDocument.getColumnModel().getColumn(1);
        TableColumn qteColumn = jTableLigneDeDocument.getColumnModel().getColumn(4);
        TableColumn remiseColumn = jTableLigneDeDocument.getColumnModel().getColumn(5);
        
        // création des composants
        JTextField jTextFieldArticleReference = new JTextField();
        SpinnerEditor jSpinnerLigneDeDocumentQte = new SpinnerEditor(0.0, 0.0, 100.0, 1.0);
        SpinnerEditor jSpinnerLigneDeDocumentRemise = new SpinnerEditor(0.0, 0.0, 100.0, 0.5);
        
        // attribution des composants
        referenceColumn.setCellEditor(new DefaultCellEditor(jTextFieldArticleReference));
        qteColumn.setCellEditor(jSpinnerLigneDeDocumentQte);
        remiseColumn.setCellEditor(jSpinnerLigneDeDocumentRemise);
        
        // events
        jTextFieldArticleReference.addFocusListener(new JTextFieldFocusCustom(jTextFieldArticleReference) {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                super.focusLost(evt);
                jTextFieldArticleReferenceFocusLost(evt, this.value);
            }
        });
        
        jSpinnerLigneDeDocumentQte.spinner.addChangeListener(new JSpinnerChangeCustom(jSpinnerLigneDeDocumentQte.spinner) {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                super.stateChanged(evt);
                jSpinnerLigneDeDocumentQteStateChanged(evt, this.value.intValue());
            }
        });
        
        jSpinnerLigneDeDocumentRemise.spinner.addChangeListener(new JSpinnerChangeCustom(jSpinnerLigneDeDocumentRemise.spinner) {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                super.stateChanged(evt);
                jSpinnerLigneDeDocumentRemiseStateChanged(evt, this.value.doubleValue());
            }
        });
    }
    
    private Boolean InsertionArticle(String reference, int rowIndex) {
        Boolean dejaPresent = false;
        
        for (int i = 0; i< modelTableLignesDeDocument.getRowCount(); i++) {
            // cherchons uniquement si la référence est présent dans une autre ligne que celle sélectionnée
            if (i != rowIndex) {
                LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(i, 0);
                if (ligneDeDocument.getIdArticle() != 0) {
                    Article article = DAOArticle.getArticle(ligneDeDocument.getIdArticle());
                    if (article.getReference().equals(reference)) {
                        dejaPresent = true;
                    }
                }
            }
        }
        
        if (dejaPresent) {
            JOptionPane.showMessageDialog(this, "L'article est déjà présent dans ce document", "Attention !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void CalculMontants() {
        Double MontantTotalHT = 0.0;
        Double TauxRemise = (1 - (((Number)jSpinnerRemise.getValue()).doubleValue() / 100));
        Double FraisDeTransport = ((Number)jSpinnerFraisDeTransport.getValue()).doubleValue();
        Double TauxDeTVA = (1 + (Double.parseDouble((String)jComboBoxTVA.getSelectedItem()) / 100));
        Double MontantTotalTTC = 0.0;
        
        for (int i = 0; i< modelTableLignesDeDocument.getRowCount(); i++) {
            LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(i, 0);
            if (ligneDeDocument.getIdArticle() != 0) {
                Article article = DAOArticle.getArticle(ligneDeDocument.getIdArticle());
                MontantTotalHT = (MontantTotalHT + (Double)modelTableLignesDeDocument.getValueAt(i, 7));
            }
        }
        
        MontantTotalTTC = (((MontantTotalHT * TauxRemise) + FraisDeTransport) * TauxDeTVA);
        jLabelMontantTotalHT.setText(MontantTotalHT.toString());
        jLabelMontantTotalTTC.setText(MontantTotalTTC.toString());
    }
    
    private void EpurationLignesDeDocument() {
        int nbLignesEpurees = 0;
        for (int i = 0; i< modelTableLignesDeDocument.getRowCount(); i++) {
            LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(i, 0);
            if (ligneDeDocument.getIdArticle() == 0) {
                modelTableLignesDeDocument.removeRow(i);
                nbLignesEpurees++;
            }
        }
        
        if (nbLignesEpurees > 0) {
            JOptionPane.showMessageDialog(this, "Une ou plusieurs lignes ont été supprimées car aucun article n'a été sélectionné.", "Attention !", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Creates new form VueDevisEdit
     */
    public VueDevisEdit() {
        initComponents();
        
        ArrayList<Contact> clients = DAOContact.getListContacts();
        modelComboBoxContact = (DefaultComboBoxModel)jComboBoxContact.getModel();
        modelTableLignesDeDocument = (DefaultTableModel)jTableLigneDeDocument.getModel();
        modelSpinnerRemise = new SpinnerNumberModel(0, 0, 100, 0.5);
        modelSpinnerFraisDeTransport = new SpinnerNumberModel(0, 0, 100, 0.5);
        
        modelComboBoxContact.removeAllElements();
        modelTableLignesDeDocument.getDataVector().removeAllElements();
           
        jTableSettings();
        
        for (Contact client : clients) {
            modelComboBoxContact.addElement(client);
        }
        
        Devis devis = Singleton.getCurrent().devis;
        jLabelReference.setText(devis.getReference());
        jDateChooserDateDeCreation.setDate(devis.getDateDeCreation());
        jComboBoxContact.setSelectedItem(devis);
        jDateChooserDateDeValidite.setDate(devis.getDateDeFinDeValidite());
        jCheckBoxSigne.setSelected(devis.isSigne());
        jLabelMontantTotalHT.setText(Double.toString(devis.getMontantTotalHT()));
        modelSpinnerRemise.setValue(devis.getRemise());
        modelSpinnerFraisDeTransport.setValue(devis.getFraisDeTransport());
        jComboBoxTVA.setSelectedItem(Double.toString(devis.getTauxDeTva()));
        jLabelMontantTotalTTC.setText(Double.toString(devis.getMontantTotalTTC()));
        
        if (Singleton.getCurrent().editModeDevis == EditMode.MODIFICATION) {
            lignesDeDocument = DAOLigneDeDocument.getListLignesDeDocument(devis.getId());
            for (LigneDeDocument ligneDeDocument : lignesDeDocument) {
                Article article = DAOArticle.getArticle(ligneDeDocument.getIdArticle());
                Double Remise = (1 - (ligneDeDocument.getRemise() / 100));
                Double prixTotal = ((article.getPrixUnitaire() * ligneDeDocument.getQte()) * Remise);
                Object[] obj = new Object[] { ligneDeDocument, article.getReference(), article.getNom(), article.getQuantiteStock(), ligneDeDocument.getQte(), ligneDeDocument.getRemise(), article.getPrixUnitaire(), prixTotal };
                modelTableLignesDeDocument.addRow(obj);
            }
        }
        
        // gèlement du formulaire
        GelerFormulaire(false);
        
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run()
            {
                jComboBoxContact.setModel(modelComboBoxContact);
                jComboBoxContact.revalidate();
                jComboBoxContact.repaint();
                
                jTableLigneDeDocument.setModel(modelTableLignesDeDocument);
                jTableLigneDeDocument.revalidate();
                jTableLigneDeDocument.repaint();
        
                jSpinnerRemise.setModel(modelSpinnerRemise);
                jSpinnerRemise.revalidate();
                jSpinnerRemise.repaint();
        
                jSpinnerFraisDeTransport.setModel(modelSpinnerFraisDeTransport);
                jSpinnerFraisDeTransport.revalidate();
                jSpinnerFraisDeTransport.repaint();
            }
        });
    }
    
    private void GelerFormulaire(Boolean signature) {
        Devis devis = Singleton.getCurrent().devis;
        
        jDateChooserDateDeCreation.setEnabled(!devis.isSigne());
        jComboBoxContact.setEnabled(!devis.isSigne());
        jDateChooserDateDeValidite.setEnabled(!devis.isSigne());
        jCheckBoxSigne.setEnabled(!devis.isSigne());
        jTableLigneDeDocument.setEnabled(!devis.isSigne());
        jButtonNouvelleLigne.setEnabled(!devis.isSigne());
        jButtonSupprimerLigne.setEnabled(!devis.isSigne());
        jSpinnerRemise.setEnabled(!devis.isSigne());
        jSpinnerFraisDeTransport.setEnabled(!devis.isSigne());
        jComboBoxTVA.setEnabled(!devis.isSigne());
        
        if (!signature) {
            jButtonEnregistrer.setEnabled(!devis.isSigne());
        }
        
        Boolean commandeDejaGeneree = (devis.getIdCommande() != null);
        jButtonGenererCommande.setEnabled(!commandeDejaGeneree);
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
        jComboBoxContact = new javax.swing.JComboBox();
        jDateChooserDateDeCreation = new com.toedter.calendar.JDateChooser();
        jDateChooserDateDeValidite = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jButtonNouvelleLigne = new javax.swing.JButton();
        jLabelMontantTotalHT = new javax.swing.JLabel();
        jLabelMontantTotalTTC = new javax.swing.JLabel();
        jButtonSupprimerLigne = new javax.swing.JButton();
        jButtonGenererCommande = new javax.swing.JButton();
        jLabelReference = new javax.swing.JLabel();

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

        jLabel8.setText("Contact");

        jLabel9.setText("Date de validité");

        jLabel10.setText("Signé");

        jTableLigneDeDocument.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Numéro de ligne", "Référence", "Nom", "Quantité en stock", "Quantité", "Remise (%)", "Prix unitaire", "Prix total"
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
        jTableLigneDeDocument.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableLigneDeDocumentFocusGained(evt);
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

        jComboBoxTVA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5.5", "10.0", "20.0" }));
        jComboBoxTVA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTVAItemStateChanged(evt);
            }
        });

        jCheckBoxSigne.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxSigneStateChanged(evt);
            }
        });

        jSpinnerRemise.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerRemiseStateChanged(evt);
            }
        });

        jSpinnerFraisDeTransport.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerFraisDeTransportStateChanged(evt);
            }
        });

        jLabel15.setText("Montant total TTC");

        jButtonNouvelleLigne.setText("Nouvelle ligne");
        jButtonNouvelleLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNouvelleLigneActionPerformed(evt);
            }
        });

        jLabelMontantTotalHT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMontantTotalHT.setText("0");

        jLabelMontantTotalTTC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMontantTotalTTC.setText("0");

        jButtonSupprimerLigne.setText("Supprimer ligne");
        jButtonSupprimerLigne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonSupprimerLigneMousePressed(evt);
            }
        });

        jButtonGenererCommande.setText("Générer une commande");
        jButtonGenererCommande.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonGenererCommandeMousePressed(evt);
            }
        });

        jLabelReference.setText("jLabel1");

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
                        .addComponent(jButtonGenererCommande)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jComboBoxContact, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserDateDeCreation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserDateDeValidite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxSigne)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabelReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                            .addComponent(jSpinnerRemise)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMontantTotalHT, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelMontantTotalTTC, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNouvelleLigne)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSupprimerLigne)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelReference))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooserDateDeCreation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jButtonNouvelleLigne)
                    .addComponent(jButtonSupprimerLigne))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabelMontantTotalHT))
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
                    .addComponent(jLabelMontantTotalTTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonEnregistrer)
                    .addComponent(jButtonGenererCommande))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxSigneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxSigneStateChanged
        Devis devis = Singleton.getCurrent().devis;
        devis.setSigne(jCheckBoxSigne.isSelected());

        // gèlement du formulaire
        GelerFormulaire(true);
    }//GEN-LAST:event_jCheckBoxSigneStateChanged

    private void jTableLigneDeDocumentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableLigneDeDocumentFocusGained
        // récupération de la ligne
        int rowIndex = jTableLigneDeDocument.getSelectedRow();
        Singleton.getCurrent().ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(rowIndex, 0);
        Singleton.getCurrent().editModeLigneDeDocument = EditMode.MODIFICATION;
    }//GEN-LAST:event_jTableLigneDeDocumentFocusGained
	
    private void jTextFieldArticleReferenceFocusLost(java.awt.event.FocusEvent evt, String reference) {
        // récupération de la ligne
        int rowIndex = jTableLigneDeDocument.getSelectedRow();
        LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(rowIndex, 0);
        
        if (reference != null) {
            if (InsertionArticle(reference, rowIndex)) {
                Article article = DAOArticle.getArticle(reference);
                if (article != null) {

                    // attribution de nouvelles valeurs
                    ligneDeDocument.setIdArticle(article.getId());
                    Double TauxRemise = (1 - (ligneDeDocument.getRemise() / 100));
                    Double prixTotal = ((article.getPrixUnitaire() * ligneDeDocument.getQte()) * TauxRemise);
                    Object[] obj = new Object[] { ligneDeDocument, article.getReference(), article.getNom(), article.getQuantiteStock(), ligneDeDocument.getQte(), article.getPrixUnitaire(), ligneDeDocument.getRemise(), prixTotal };
                    
                    // affichage
                    modelTableLignesDeDocument.setValueAt(ligneDeDocument, rowIndex, 0);
                    modelTableLignesDeDocument.setValueAt(article.getReference(), rowIndex, 1);
                    modelTableLignesDeDocument.setValueAt(article.getNom(), rowIndex, 2);
                    modelTableLignesDeDocument.setValueAt(article.getQuantiteStock(), rowIndex, 3);
                    modelTableLignesDeDocument.setValueAt(ligneDeDocument.getQte(), rowIndex, 4);
                    modelTableLignesDeDocument.setValueAt(ligneDeDocument.getRemise(), rowIndex, 5);
                    modelTableLignesDeDocument.setValueAt(article.getPrixUnitaire(), rowIndex, 6);
                    modelTableLignesDeDocument.setValueAt(prixTotal, rowIndex, 7);
                    
                    // calcul des montants
                    CalculMontants();
                }
            } else {
                ligneDeDocument.setIdArticle(0);
                ligneDeDocument.setPrixUnitaire(0);
                ligneDeDocument.setQte(0);
                ligneDeDocument.setRemise(0);
                modelTableLignesDeDocument.setValueAt(ligneDeDocument, rowIndex, 0);
                modelTableLignesDeDocument.setValueAt(null, rowIndex, 1);
            }
        } else {
            ligneDeDocument.setIdArticle(0);
            ligneDeDocument.setPrixUnitaire(0);
            ligneDeDocument.setQte(0);
            ligneDeDocument.setRemise(0);
            modelTableLignesDeDocument.setValueAt(ligneDeDocument, rowIndex, 0);
            modelTableLignesDeDocument.setValueAt(null, rowIndex, 1);
        }
    }
    
    private void jSpinnerLigneDeDocumentQteStateChanged(javax.swing.event.ChangeEvent evt, int qte) {
        // récupération de la ligne et de l'article
        int rowIndex = jTableLigneDeDocument.getSelectedRow();
        LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(rowIndex, 0);
        
        if (ligneDeDocument.getIdArticle() != 0) {
            Article article = DAOArticle.getArticle(ligneDeDocument.getIdArticle());
                
            // attribution de nouvelles valeurs
            ligneDeDocument.setQte(qte);
            Double TauxRemise = (1 - (ligneDeDocument.getRemise() / 100));
            Double prixTotal = ((article.getPrixUnitaire() * qte) * TauxRemise);
            Object[] obj = new Object[] { ligneDeDocument, article.getReference(), article.getNom(), article.getQuantiteStock(), ligneDeDocument.getQte(), ligneDeDocument.getRemise(), article.getPrixUnitaire(), prixTotal };
            
            // affichage
            modelTableLignesDeDocument.setValueAt(ligneDeDocument, rowIndex, 0);
            modelTableLignesDeDocument.setValueAt(article.getReference(), rowIndex, 1);
            modelTableLignesDeDocument.setValueAt(article.getNom(), rowIndex, 2);
            modelTableLignesDeDocument.setValueAt(article.getQuantiteStock(), rowIndex, 3);
            modelTableLignesDeDocument.setValueAt(ligneDeDocument.getQte(), rowIndex, 4);
            modelTableLignesDeDocument.setValueAt(ligneDeDocument.getRemise(), rowIndex, 5);
            modelTableLignesDeDocument.setValueAt(article.getPrixUnitaire(), rowIndex, 6);
            modelTableLignesDeDocument.setValueAt(prixTotal, rowIndex, 7);
                    
            // calcul des montants
            CalculMontants();
        }
    }
    
    private void jSpinnerLigneDeDocumentRemiseStateChanged(javax.swing.event.ChangeEvent evt, Double remise) {
        // récupération de la ligne et de l'article
        int rowIndex = jTableLigneDeDocument.getSelectedRow();
        LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(rowIndex, 0);
        
        if (ligneDeDocument.getIdArticle() != 0) {
            Article article = DAOArticle.getArticle(ligneDeDocument.getIdArticle());
                
            // attribution de nouvelles valeurs
            ligneDeDocument.setRemise(remise);
            Double TauxRemise = (1 - (remise / 100));
            Double prixTotal = ((article.getPrixUnitaire() * ligneDeDocument.getQte()) * TauxRemise);
            Object[] obj = new Object[] { ligneDeDocument, article.getReference(), article.getNom(), article.getQuantiteStock(), ligneDeDocument.getQte(), ligneDeDocument.getRemise(), article.getPrixUnitaire(), prixTotal };
            
            // affichage
            modelTableLignesDeDocument.setValueAt(ligneDeDocument, rowIndex, 0);
            modelTableLignesDeDocument.setValueAt(article.getReference(), rowIndex, 1);
            modelTableLignesDeDocument.setValueAt(article.getNom(), rowIndex, 2);
            modelTableLignesDeDocument.setValueAt(article.getQuantiteStock(), rowIndex, 3);
            modelTableLignesDeDocument.setValueAt(ligneDeDocument.getQte(), rowIndex, 4);
            modelTableLignesDeDocument.setValueAt(ligneDeDocument.getRemise(), rowIndex, 5);
            modelTableLignesDeDocument.setValueAt(article.getPrixUnitaire(), rowIndex, 6);
            modelTableLignesDeDocument.setValueAt(prixTotal, rowIndex, 7);
            
            // calcul des montants
            CalculMontants();
        }
    }
	
	private void jButtonNouvelleLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNouvelleLigneActionPerformed
        int nbLignes = modelTableLignesDeDocument.getRowCount();
        
        Devis devis = Singleton.getCurrent().devis;
        LigneDeDocument ligneDeDocument = new LigneDeDocument();
        ligneDeDocument.setIdDocument(devis.getId());
        ligneDeDocument.setNumeroDeLigne(nbLignes + 1);
        ligneDeDocument.setQte(0);
        ligneDeDocument.setRemise(0);
        lignesDeDocument.add(ligneDeDocument);
        
        Object[] obj = new Object[] { ligneDeDocument, null, null, 0, 0, 0, 0, 0 };
        modelTableLignesDeDocument.addRow(obj);
    }//GEN-LAST:event_jButtonNouvelleLigneActionPerformed

    private void jButtonSupprimerLigneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSupprimerLigneMousePressed
        // récupération de la ligne et de l'article
        int rowIndex = jTableLigneDeDocument.getSelectedRow();
        
        if (rowIndex >= 0) {
            LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(rowIndex, 0);
            
            // la ligne existe dansd la BDD
            if (ligneDeDocument.getId() != 0) {
                DAOLigneDeDocument.suppressionLigneDeDocument(ligneDeDocument);
            }
            
            Singleton.getCurrent().ligneDeDocument = null;
            modelTableLignesDeDocument.removeRow(rowIndex);
            
            // calcul des montants
            CalculMontants();
        }
    }//GEN-LAST:event_jButtonSupprimerLigneMousePressed

    private void jButtonGenererCommandeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGenererCommandeMousePressed
        Devis devis = Singleton.getCurrent().devis;
        Commande commande = new Commande();
        DAOCommande.genererCommande(devis.getId(), commande.getReference());
        Singleton.getCurrent().devis = DAODevis.getDevis(devis.getId());
        
        // gèlement du formulaire
        GelerFormulaire(false);
    }//GEN-LAST:event_jButtonGenererCommandeMousePressed
    
    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        EpurationLignesDeDocument();
        
        Devis devis = Singleton.getCurrent().devis;
        Contact contact = (Contact)jComboBoxContact.getSelectedItem();
        Commercial commercial = Singleton.getCurrent().me;
        
        Calendar dateDeCreation = Calendar.getInstance();
        Calendar dateDeFinDeValidite = Calendar.getInstance();
        dateDeCreation.setTime(jDateChooserDateDeCreation.getDate());
        dateDeFinDeValidite.setTime(jDateChooserDateDeValidite.getDate());
        
        devis.setDateDeCreation(new Date(dateDeCreation.getTime().getTime()));
        devis.setIdContact(contact.getId());
        devis.setIdCommercial(commercial.getId());
        devis.setDateDeFinDeValidite(new Date(dateDeFinDeValidite.getTime().getTime()));
        devis.setSigne(jCheckBoxSigne.isSelected());
        devis.setMontantTotalHT(Double.parseDouble(jLabelMontantTotalHT.getText()));
        devis.setRemise((Double)jSpinnerRemise.getValue());
        devis.setFraisDeTransport((Double)jSpinnerFraisDeTransport.getValue());
        devis.setTauxDeTva(Double.parseDouble((String)jComboBoxTVA.getSelectedItem()));
        devis.setMontantTotalTTC(Double.parseDouble(jLabelMontantTotalTTC.getText()));
        
        switch (Singleton.getCurrent().editModeDevis) {
            case CREATION:
                long Id = DAODevis.ajoutDevis(devis);
                devis.setId(Id);
                break;
                
            case MODIFICATION:
                DAODevis.modificationDevis(devis);
                break;
        }
        
        // Enregistrement des lignes
        for (int i = 0; i< modelTableLignesDeDocument.getRowCount(); i++) {
            LigneDeDocument ligneDeDocument = (LigneDeDocument)modelTableLignesDeDocument.getValueAt(i, 0);
            ligneDeDocument.setIdDocument(devis.getId());
            
            // un article a été saisi, on ajoute cette ligne
            if (ligneDeDocument.getIdArticle() != 0) {
                // CREATION
                if (ligneDeDocument.getId() == 0) {
                    DAOLigneDeDocument.ajoutLigneDeDocument(ligneDeDocument);
                }
                // MODIFICATION
                else {
                    DAOLigneDeDocument.modificationLigneDeDocument(ligneDeDocument);
                }
            }
        }
        
        this.dispose();
        Singleton.getCurrent().vueDevis.DislayCurrentDevis(true);
    }//GEN-LAST:event_jButtonEnregistrerMousePressed

    private void jButtonAnnulerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnulerMousePressed
        this.dispose();
    }//GEN-LAST:event_jButtonAnnulerMousePressed

    private void jSpinnerRemiseStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerRemiseStateChanged
        CalculMontants();
    }//GEN-LAST:event_jSpinnerRemiseStateChanged

    private void jSpinnerFraisDeTransportStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerFraisDeTransportStateChanged
        CalculMontants();
    }//GEN-LAST:event_jSpinnerFraisDeTransportStateChanged

    private void jComboBoxTVAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTVAItemStateChanged
        CalculMontants();
    }//GEN-LAST:event_jComboBoxTVAItemStateChanged

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
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonEnregistrer;
    private javax.swing.JButton jButtonGenererCommande;
    private javax.swing.JButton jButtonNouvelleLigne;
    private javax.swing.JButton jButtonSupprimerLigne;
    private javax.swing.JCheckBox jCheckBoxSigne;
    private javax.swing.JComboBox jComboBoxContact;
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
    private javax.swing.JLabel jLabelMontantTotalHT;
    private javax.swing.JLabel jLabelMontantTotalTTC;
    private javax.swing.JLabel jLabelReference;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerFraisDeTransport;
    private javax.swing.JSpinner jSpinnerRemise;
    private javax.swing.JTable jTableLigneDeDocument;
    // End of variables declaration//GEN-END:variables
}
