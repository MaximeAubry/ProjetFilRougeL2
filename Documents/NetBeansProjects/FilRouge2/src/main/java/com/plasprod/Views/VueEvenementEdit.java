package com.plasprod.Views;

import com.plasprod.CustomLibraries.HeureEvenementListCellRenderer;
import com.plasprod.JDBC.DAOContact;
import com.plasprod.JDBC.DAOEvenement;
import com.plasprod.Models.Contact;
import com.plasprod.Models.CustomObjects.HeureEvenement;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.Evenement;
import com.plasprod.Models.Singleton;
import com.plasprod.Models.Enums.TypeRdv;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

public class VueEvenementEdit extends javax.swing.JFrame {
    // objet de la page
    Evenement evenement = null;
    
    // Models pour les composants
    final DefaultComboBoxModel modelComboBoxContact;
    final DefaultComboBoxModel modelComboBoxTypeRdv;
    final DefaultListModel modelListHeureDeDebut;
    
    /**
     * Creates new form VueAgendaEdit
     */
    public VueEvenementEdit() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        switch (Singleton.getCurrent().editModeEvenement) {
            case CREATION:
                evenement = new Evenement();
                break;
                
            case MODIFICATION:
                evenement = Singleton.getCurrent().evenement;
                break;
        }
        
        ArrayList<Contact> contacts = DAOContact.getListContacts();
        jTextAreaCommentaire.setText(evenement.getCommentaire());
        jDateChooserDateEvenement.setDate(evenement.getDateDeDebut(false));
        jListHeureDeDebut.setCellRenderer(new HeureEvenementListCellRenderer());
        jListHeureDeDebut.setModel(new DefaultListModel());
        
        modelComboBoxContact = (DefaultComboBoxModel)jComboBoxContact.getModel();
        modelComboBoxTypeRdv = (DefaultComboBoxModel)jComboBoxType.getModel();
        modelListHeureDeDebut = (DefaultListModel)jListHeureDeDebut.getModel();
        modelComboBoxContact.removeAllElements();
        modelComboBoxTypeRdv.removeAllElements();
        modelListHeureDeDebut.removeAllElements();
        
        for (Contact contact : contacts) {
            modelComboBoxContact.addElement(contact);
        }
        for (TypeRdv typeRdv : TypeRdv.values()) {
            modelComboBoxTypeRdv.addElement(typeRdv);
        }
        popullerHeures();
        
        if (Singleton.getCurrent().editModeEvenement == EditMode.MODIFICATION) {
            Contact contact = DAOContact.getContact(evenement.getIdContact());
            TypeRdv typeRdv = TypeRdv.valueOf(evenement.getTypeRDV().name());
            
            jComboBoxContact.setSelectedItem(contact);
            jComboBoxType.setSelectedItem(typeRdv);
            jListHeureDeDebut.setSelectedValue(evenement.getHeureDeDebut(), true);
        }
        
        SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run() {
                jComboBoxContact.setModel(modelComboBoxContact);
                jComboBoxContact.revalidate();
                jComboBoxContact.repaint();
                
                jComboBoxType.setModel(modelComboBoxTypeRdv);
                jComboBoxType.revalidate();
                jComboBoxType.repaint();
                
                jListHeureDeDebut.setModel(modelListHeureDeDebut);
                jListHeureDeDebut.revalidate();
                jListHeureDeDebut.repaint();
                
                desactiverHeuresUtilisees();
            }
        });
    }
    
    private void popullerHeures() {
        modelListHeureDeDebut.removeAllElements();
        
        for(int i = 8; i < 22; i++) {
            Time time = new Time((i - 1) * 60 * 60 * 1000);
            HeureEvenement HeureEvenement = new HeureEvenement(time, true, null);
            modelListHeureDeDebut.addElement(HeureEvenement);
        }
    }
    
    private void desactiverHeuresUtilisees() {
        ArrayList<Evenement> evenements = DAOEvenement.getListEvenements(new Date(jDateChooserDateEvenement.getDate().getTime()));
        ArrayList<HeureEvenement> heuresUtilisees = new ArrayList<HeureEvenement>();
        
        for (Evenement evenementDuJour : evenements) {
            Calendar _heureDeDebut = Calendar.getInstance();
            _heureDeDebut.setTime(evenementDuJour.getHeureDeDebut());
            int heureDeDebut = _heureDeDebut.get(Calendar.HOUR_OF_DAY);
            
            Calendar _heureDeFin = Calendar.getInstance();
            _heureDeFin.setTime(evenementDuJour.getHeureDeFin());
            int heureDeFin = _heureDeFin.get(Calendar.HOUR_OF_DAY);
            
            for(int i = heureDeDebut; i < heureDeFin; i++) {
                Time heure = new Time((i - 1) * 60 * 60 * 1000);
                HeureEvenement heureEvenement = new HeureEvenement(heure, true, evenementDuJour);
                heuresUtilisees.add(heureEvenement);
            }
        }
        
        for (int i = 0; i < modelListHeureDeDebut.getSize(); i++) {
            HeureEvenement heureEvenement = (HeureEvenement)modelListHeureDeDebut.getElementAt(i);
            
            // par défaut, cette heure est utilisable
            heureEvenement.setEnabled(true);
            
            for (HeureEvenement heureUtilisee : heuresUtilisees) {
                
                if (heureEvenement.getHeure().getTime() == heureUtilisee.getHeure().getTime()) {
                    heureEvenement.setEnabled(false);
                    heureEvenement.setEvenementActuel(heureUtilisee.getEvenementActuel());
                    Boolean isSelected = (jListHeureDeDebut.getSelectedIndex() == i);
                    jListHeureDeDebut.getCellRenderer().getListCellRendererComponent(jListHeureDeDebut, heureEvenement, i, isSelected, true);
                }
            }
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
        jComboBoxType = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPaneDuree = new javax.swing.JScrollPane();
        jTextAreaCommentaire = new javax.swing.JTextArea();
        jComboBoxContact = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jDateChooserDateEvenement = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListHeureDeDebut = new javax.swing.JList();

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

        jLabel1.setText("Type");

        jLabel2.setText("Date");

        jLabel3.setText("Heure de début");

        jLabel5.setText("Commentaire");

        jTextAreaCommentaire.setColumns(20);
        jTextAreaCommentaire.setRows(5);
        jScrollPaneDuree.setViewportView(jTextAreaCommentaire);

        jLabel6.setText("Contact");

        jDateChooserDateEvenement.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserDateEvenementPropertyChange(evt);
            }
        });

        jListHeureDeDebut.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jListHeureDeDebut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jListHeureDeDebutFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jListHeureDeDebut);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserDateEvenement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                            .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxContact, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPaneDuree)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonEnregistrer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAnnuler)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserDateEvenement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneDuree, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonEnregistrer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        Contact contact = (Contact)jComboBoxContact.getSelectedItem();
        ArrayList<HeureEvenement> heuresEvenement = (ArrayList<HeureEvenement>)jListHeureDeDebut.getSelectedValuesList();
        
        Time heureDeDebut = heuresEvenement.get(0).getHeure();
        
        Calendar _heureDeFin = Calendar.getInstance();
        _heureDeFin.setTime(heuresEvenement.get(heuresEvenement.size() - 1).getHeure());
        _heureDeFin.set(Calendar.HOUR_OF_DAY, (_heureDeFin.get(Calendar.HOUR_OF_DAY) + 1));
        Time heureDeFin = new Time(_heureDeFin.getTime().getTime());
        
        evenement.setTypeRDV((TypeRdv)jComboBoxType.getSelectedItem());
        evenement.setCommentaire(jTextAreaCommentaire.getText());
        evenement.setDateDeDebut(new Date(jDateChooserDateEvenement.getDate().getTime()));
        evenement.setHeureDeDebut(heureDeDebut);
        evenement.setDateDeFin(evenement.getDateDeDebut(rootPaneCheckingEnabled));
        evenement.setHeureDeFin(heureDeFin);
        evenement.setIdCommercial(Singleton.getCurrent().me.getId());
        evenement.setIdContact(contact.getId());
        
        switch (Singleton.getCurrent().editModeEvenement) {
            case CREATION:
                DAOEvenement.ajoutEvenement(evenement);
                Singleton.getCurrent().evenement = evenement;
                break;
                
            case MODIFICATION:
                DAOEvenement.modificationEvenement(evenement);
                break;
        }
        
        this.dispose();
        Singleton.getCurrent().vueEvenement.DislayCurrentEvenement(true);
    }//GEN-LAST:event_jButtonEnregistrerMousePressed

    private void jButtonAnnulerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnulerMousePressed
        this.dispose();
    }//GEN-LAST:event_jButtonAnnulerMousePressed

    private void jDateChooserDateEvenementPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserDateEvenementPropertyChange
        if (this.isActive()) {
            desactiverHeuresUtilisees();
        }
    }//GEN-LAST:event_jDateChooserDateEvenementPropertyChange

    private void jListHeureDeDebutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jListHeureDeDebutFocusGained
        for (int i = 0; i < modelListHeureDeDebut.getSize(); i++) {
            HeureEvenement heureEvenement = (HeureEvenement)modelListHeureDeDebut.getElementAt(i);
            
            if (heureEvenement.getEvenementActuel() != null) {
                if (heureEvenement.getEvenementActuel().getId() == evenement.getId()) {
                    heureEvenement.setEnabled(true);
                    jListHeureDeDebut.getCellRenderer().getListCellRendererComponent(jListHeureDeDebut, heureEvenement, i, false, true);
                }
            }
        }
        
        jListHeureDeDebut.repaint();
    }//GEN-LAST:event_jListHeureDeDebutFocusGained

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
            java.util.logging.Logger.getLogger(VueEvenementEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueEvenementEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueEvenementEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueEvenementEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueEvenementEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonEnregistrer;
    private javax.swing.JComboBox jComboBoxContact;
    private javax.swing.JComboBox jComboBoxType;
    private com.toedter.calendar.JDateChooser jDateChooserDateEvenement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jListHeureDeDebut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDuree;
    private javax.swing.JTextArea jTextAreaCommentaire;
    // End of variables declaration//GEN-END:variables
}
