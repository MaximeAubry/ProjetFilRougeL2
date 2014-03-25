package com.plasprod.Views;

import com.plasprod.JDBC.DAOContact;
import com.plasprod.JDBC.DAOEvenement;
import com.plasprod.Models.Contact;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.Evenement;
import com.plasprod.Models.Singleton;
import com.plasprod.Models.Enums.TypeRdv;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class VueEvenementEdit extends javax.swing.JFrame {
    /**
     * Creates new form VueAgendaEdit
     */
    public VueEvenementEdit() {
        initComponents();
        
        ArrayList<Contact> contacts = DAOContact.getListContacts();
        Evenement evenement = Singleton.getCurrent().evenement;
        jTextAreaCommentaire.setText(evenement.getCommentaire());
        jDateChooserDateEvenement.setDate(evenement.getDateDeDebut());
        
        final DefaultComboBoxModel modelComboBoxContact = (DefaultComboBoxModel)jComboBoxContact.getModel();
        final DefaultComboBoxModel modelComboBoxTypeRdv = (DefaultComboBoxModel)jComboBoxType.getModel();
        final DefaultComboBoxModel modelComboBoxHeureDeDebut = (DefaultComboBoxModel)jComboBoxHeureDeDebut.getModel();
        
        modelComboBoxContact.removeAllElements();
        modelComboBoxHeureDeDebut.removeAllElements();
        
        for (Contact contact : contacts) {
            modelComboBoxContact.addElement(contact);
        }
        for (TypeRdv typeRdv : TypeRdv.values()) {
            modelComboBoxTypeRdv.addElement(typeRdv);
        }
        for(int i = 8; i <= 22; i++) {
            Time time = new Time((i - 1) * 60 * 60 * 1000);
            modelComboBoxHeureDeDebut.addElement(time);
        }
        
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run()
            {
                jComboBoxContact.setModel(modelComboBoxContact);
                jComboBoxContact.revalidate();
                jComboBoxContact.repaint();
                
                jComboBoxType.setModel(modelComboBoxTypeRdv);
                jComboBoxType.revalidate();
                jComboBoxType.repaint();
                
                jComboBoxHeureDeDebut.setModel(modelComboBoxHeureDeDebut);
                jComboBoxHeureDeDebut.revalidate();
                jComboBoxHeureDeDebut.repaint();
            }
        });
        
        if (Singleton.getCurrent().editModeEvenement == EditMode.MODIFICATION) {
            Contact contact = DAOContact.getContact(evenement.getIdContact());
            TypeRdv typeRdv = TypeRdv.valueOf(evenement.getTypeRDV().name());
            
            Calendar dateDeDebut = Calendar.getInstance();
            dateDeDebut.setTime(evenement.getDateDeDebut());
            Calendar heureDeDebut = null;
            for(int i = 0; i < modelComboBoxHeureDeDebut.getSize(); i++) {
                Calendar heureDeDebutTEMP = Calendar.getInstance();
                heureDeDebutTEMP.setTime((Time)modelComboBoxHeureDeDebut.getElementAt(i));
                
                if (dateDeDebut.get(Calendar.HOUR_OF_DAY) == heureDeDebutTEMP.get(Calendar.HOUR_OF_DAY)) {
                    heureDeDebut = heureDeDebutTEMP;
                    break;
                }
            }
            
            //Time heureDeDebut = new Time((dateDeDebut.get(Calendar.HOUR_OF_DAY) - 1) * 60 * 60 * 1000);
            
            Calendar dateDeFin = Calendar.getInstance();
            dateDeFin.setTime(evenement.getDateDeFin());
            int dureeMax = (dateDeFin.get(Calendar.HOUR_OF_DAY) - dateDeDebut.get(Calendar.HOUR_OF_DAY));
            SpinnerModel modelSpinnerDuree = new SpinnerNumberModel(dureeMax, 0, dureeMax, 1);
            
            jComboBoxContact.setSelectedItem(contact);
            jComboBoxType.setSelectedItem(typeRdv);
            jComboBoxHeureDeDebut.setSelectedItem(new Time(heureDeDebut.getTimeInMillis()));
            jSpinnerDuree.setModel(modelSpinnerDuree);
        }
        
        supprimerHeuresUtilisees();
        setMaxDelay();
    }
    
    private void supprimerHeuresUtilisees() {
        ArrayList<Evenement> evenements = DAOEvenement.getListEvenements(Singleton.getCurrent().evenement.getDateDeDebut());
        ArrayList<Time> heuresUtilisees = new ArrayList<Time>();
        final DefaultComboBoxModel modelComboBoxHeureDeDebut = (DefaultComboBoxModel)jComboBoxHeureDeDebut.getModel();
        
        for (Evenement evenement : evenements) {
            Calendar dateDeDebut = Calendar.getInstance();
            dateDeDebut.setTime(evenement.getDateDeDebut());
            int heureDeDebut = dateDeDebut.get(Calendar.HOUR_OF_DAY);
            
            Calendar dateDeFin = Calendar.getInstance();
            dateDeFin.setTime(evenement.getDateDeFin());
            int heureDeFin = dateDeFin.get(Calendar.HOUR_OF_DAY);
            
            for(int i = heureDeDebut; i < heureDeFin; i++) {
                Time heure = new Time((i - 1) * 60 * 60 * 1000);
                modelComboBoxHeureDeDebut.removeElement(heure);
            }
        }
    }
    
    private void setMaxDelay() {
        final DefaultComboBoxModel modelComboBoxHeureDeDebut = (DefaultComboBoxModel)jComboBoxHeureDeDebut.getModel();
        int selectedIndex = jComboBoxHeureDeDebut.getSelectedIndex();
        int dureeMax = 0;
        
        if (selectedIndex > -1) {
            Calendar heureSelectionnee = Calendar.getInstance();
            heureSelectionnee.setTime(new Date(((Time)jComboBoxHeureDeDebut.getItemAt(selectedIndex)).getTime()));
            
            for(int i = selectedIndex; i < modelComboBoxHeureDeDebut.getSize(); i++) {
                Calendar heureActuelle = Calendar.getInstance();
                Calendar heureSuivante = Calendar.getInstance();
                
                if (i < (modelComboBoxHeureDeDebut.getSize() - 1)) {
                    Time x = (Time)jComboBoxHeureDeDebut.getItemAt(i);
                    Time y = (Time)jComboBoxHeureDeDebut.getItemAt(i + 1);
                    
                    heureActuelle.setTime(new Date(((Time)jComboBoxHeureDeDebut.getItemAt(i)).getTime()));
                    heureSuivante.setTime(new Date(((Time)jComboBoxHeureDeDebut.getItemAt(i + 1)).getTime()));
                    
                    int diff = (heureSuivante.get(Calendar.HOUR_OF_DAY) - heureActuelle.get(Calendar.HOUR_OF_DAY));
                    if (diff > 1) {
                        dureeMax = (heureActuelle.get(Calendar.HOUR_OF_DAY) - heureSelectionnee.get(Calendar.HOUR_OF_DAY));
                        break;
                    }
                } else {
                    heureActuelle.setTime(new Date(((Time)jComboBoxHeureDeDebut.getItemAt(i)).getTime()));
                    dureeMax = (heureActuelle.get(Calendar.HOUR_OF_DAY) - heureSelectionnee.get(Calendar.HOUR_OF_DAY));
                }
            }
        }
        
        SpinnerModel modelSpinnerDuree = new SpinnerNumberModel(dureeMax, 0, dureeMax, 1);
        jSpinnerDuree.setModel(modelSpinnerDuree);
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
        jDateChooserDateEvenement = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jComboBoxHeureDeDebut.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxHeureDeDebutItemStateChanged(evt);
            }
        });

        jLabel6.setText("Contact");

        jDateChooserDateEvenement.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserDateEvenementPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxHeureDeDebut, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerDuree, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooserDateEvenement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                    .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxContact, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPaneDuree, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))))
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
                    .addComponent(jScrollPaneDuree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonEnregistrer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        Contact contact = (Contact)jComboBoxContact.getSelectedItem();

        // heure de début ET durée
        Calendar heureDeDebut = Calendar.getInstance();
        heureDeDebut.setTime(((Time)jComboBoxHeureDeDebut.getSelectedItem()));
        int duree = (int)jSpinnerDuree.getValue();
        
        // date de début
        Calendar dateDeDebut = Calendar.getInstance();
        dateDeDebut.setTime(jDateChooserDateEvenement.getDate());
        dateDeDebut.set(Calendar.HOUR_OF_DAY, 0);
        dateDeDebut.set(Calendar.MINUTE, 0);
        dateDeDebut.set(Calendar.SECOND, 0);
        dateDeDebut.set(Calendar.MILLISECOND, 0);
        dateDeDebut.add(Calendar.HOUR_OF_DAY, heureDeDebut.get(Calendar.HOUR_OF_DAY));
        
        // date de fin
        Calendar dateDeFin = Calendar.getInstance();
        dateDeFin.setTime(dateDeDebut.getTime());
        dateDeFin.add(Calendar.HOUR_OF_DAY, duree);
        
        Evenement evenement = Singleton.getCurrent().evenement;
        evenement.setTypeRDV((TypeRdv)jComboBoxType.getSelectedItem());
        evenement.setCommentaire(jTextAreaCommentaire.getText());
        evenement.setDateDeDebut(new Date(dateDeDebut.getTime().getTime()));
        evenement.setDateDeFin(new Date(dateDeFin.getTime().getTime()));
        evenement.setIdCommercial(Singleton.getCurrent().me.getId());
        evenement.setIdContact(contact.getId());
        
        switch (Singleton.getCurrent().editModeEvenement) {
            case CREATION:
                DAOEvenement.ajoutEvenement(evenement);
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

    private void jComboBoxHeureDeDebutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxHeureDeDebutItemStateChanged
        setMaxDelay();
    }//GEN-LAST:event_jComboBoxHeureDeDebutItemStateChanged

    private void jDateChooserDateEvenementPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserDateEvenementPropertyChange
        setMaxDelay();
    }//GEN-LAST:event_jDateChooserDateEvenementPropertyChange

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
    private javax.swing.JComboBox jComboBoxHeureDeDebut;
    private javax.swing.JComboBox jComboBoxType;
    private com.toedter.calendar.JDateChooser jDateChooserDateEvenement;
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
