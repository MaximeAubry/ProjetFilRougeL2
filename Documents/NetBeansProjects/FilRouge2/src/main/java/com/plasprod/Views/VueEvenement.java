package com.plasprod.Views;

import com.plasprod.JDBC.DAOContact;
import com.plasprod.JDBC.DAOEvenement;
import com.plasprod.Models.Contact;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.Evenement;
import com.plasprod.Models.Singleton;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class VueEvenement extends javax.swing.JFrame {
    // objet de la page
    public List<Evenement> evenements = new ArrayList<Evenement>();
    
    // Models pour les composants
    final DefaultTableModel modelTableEvenement;
    
    /**
     * Creates new form VueAgenda
     */
    public VueEvenement() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        modelTableEvenement = (DefaultTableModel)jTableEvenements.getModel();
        jDateChooserDateEvenement.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        
        LoadEvenements();
    }
    
    private void LoadEvenements() {
        Evenement evenementTemp = new Evenement();
        evenementTemp.setDateDeDebut(new Date(jDateChooserDateEvenement.getDate().getTime()));
        ArrayList<Evenement> evenementsTemp = new ArrayList<Evenement>();
        
        switch (Singleton.getCurrent().me.getTypeCommercial()) {
            case DIRECTEURCOMMERCIAL:
                evenementsTemp = DAOEvenement.getListEvenements(evenementTemp.getDateDeDebut(false));
                break;
                
            case COMMERCIAL:
                evenementsTemp = DAOEvenement.getListEvenements(evenementTemp.getDateDeDebut(false), Singleton.getCurrent().me.getId());
                break;
        }
        
        PopullerTableEvenements(evenementsTemp);
        
        modelTableEvenement.getDataVector().removeAllElements();
        
        for (Evenement evenement : evenements) {
            if (evenement != null) {
                Contact contact = DAOContact.getContact(evenement.getIdContact());
                String nomContact = contact.getPrenom() + " " + contact.getNom();
                Object[] obj = new Object[] { evenement, nomContact };
                modelTableEvenement.addRow(obj);
            } else {
                Object[] obj = new Object[] { null, null };
                modelTableEvenement.addRow(obj);
            }
        }
        
        SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run() {
                jTableEvenements.setModel(modelTableEvenement);
                jTableEvenements.revalidate();
                jTableEvenements.repaint();
                
                // réagrandissement des lignes
                for (int i = 0; i < modelTableEvenement.getRowCount(); i++) {
                    Evenement evenement = (Evenement)modelTableEvenement.getValueAt(i, 0);
                    int duree = (evenement == null) ? 1 : evenement.getDuree();
                    int height = (20 * duree);
                    jTableEvenements.setRowHeight(i, height);
                }
            }
        });
    }
    
    private void PopullerTableEvenements(List<Evenement> evenementsTemp) {
        evenements = new ArrayList<Evenement>();
        
        Time debutDeJournee = new Time((8 - 1) * 60 * 60 * 1000);
        Time finDeJournee = new Time((22 - 1) * 60 * 60 * 1000);
        
        if (evenementsTemp.size() > 0) {
            for (Evenement evenement : evenementsTemp) {
                // le premier de la liste. S'il ne commence pas à 8h, on rempli avant

                // le reste, on détecte l'heure de debut et de fin de l'événement précédent,
                // l'heure de debut et de fin de l'événement actuel, et on rempli avant et après l'événement actuel

                // le dernier de la liste, s'il ne finit pas à 21h, on rempli la fin du tableau

                Boolean premierDeLaListe = (evenementsTemp.indexOf(evenement) == 0);
                Boolean dernierDeLaListe = (evenementsTemp.indexOf(evenement) == (evenementsTemp.size() - 1));
                Boolean evenementAjoute = false;

                if (premierDeLaListe) {
                    Boolean commenceEnDebutDeJournee = (evenement.getHeureDeDebut().getTime() == debutDeJournee.getTime());

                    if (!commenceEnDebutDeJournee) {
                        Calendar heureDeDebut = Calendar.getInstance();
                        heureDeDebut.setTime(evenement.getHeureDeDebut());

                        for (int i = 8; i < heureDeDebut.get(Calendar.HOUR_OF_DAY); i++) {
                            evenements.add(null);
                        }
                    }

                    if (!evenementAjoute) {
                        evenements.add(evenement);
                        evenementAjoute = true;
                    }
                }

                if (!premierDeLaListe && !dernierDeLaListe) {
                    int indexEvenementActuel = evenementsTemp.indexOf(evenement);
                    Evenement evenementPrecedent = evenementsTemp.get(indexEvenementActuel - 1);
                    Evenement evenementSuivant = evenementsTemp.get(indexEvenementActuel - 1);

                    Calendar heureDeFinEvenementPrecedent = Calendar.getInstance();
                    Calendar heureDeDebutEvenementActuel = Calendar.getInstance();
                    Calendar heureDeFinEvenementActuel = Calendar.getInstance();
                    Calendar heureDeDebutEvenementSuivant = Calendar.getInstance();

                    heureDeFinEvenementPrecedent.setTime(evenementPrecedent.getHeureDeFin());
                    heureDeDebutEvenementActuel.setTime(evenement.getHeureDeDebut());
                    heureDeFinEvenementActuel.setTime(evenement.getHeureDeFin());
                    heureDeDebutEvenementSuivant.setTime(evenementSuivant.getHeureDeDebut());

                    for (int i = heureDeFinEvenementPrecedent.get(Calendar.HOUR_OF_DAY); i < heureDeDebutEvenementActuel.get(Calendar.HOUR_OF_DAY); i++) {
                        evenements.add(null);
                    }

                    if (!evenementAjoute) {
                        evenements.add(evenement);
                        evenementAjoute = true;
                    }

                    for (int j = heureDeFinEvenementActuel.get(Calendar.HOUR_OF_DAY); j < heureDeDebutEvenementSuivant.get(Calendar.HOUR_OF_DAY); j++) {
                        evenements.add(null);
                    }
                }

                if (dernierDeLaListe) {
                    Boolean finitEnDebutDeJournee = (evenement.getHeureDeFin().getTime() == finDeJournee.getTime());

                    if (!evenementAjoute) {
                        evenements.add(evenement);
                        evenementAjoute = true;
                    }

                    if (!finitEnDebutDeJournee) {
                        Calendar heureDeFin = Calendar.getInstance();
                        heureDeFin.setTime(evenement.getHeureDeFin());

                        for (int i = heureDeFin.get(Calendar.HOUR_OF_DAY); i < 22; i++) {
                            evenements.add(null);
                        }
                    }
                }
            }
        } else {
            for (int i = 8; i < 22; i++) {
                evenements.add(null);
            }
        }
    }
    
    public void DislayCurrentEvenement(Boolean refreshJtable) {
        if (refreshJtable) {
            // raffraichissement de la grille
            LoadEvenements();
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        
        // raffraichissement du bloc de droite
        Evenement evenement = Singleton.getCurrent().evenement;
        
        if (evenement == null) {
            jLabelType.setText(null);
            jLabelDate.setText(null);
            jLabelHeureDeDebut.setText(null);
            jLabelHeureDeFin.setText(null);
            jLabelCommentaire.setText(null);
        } else {
            jLabelType.setText(evenement.getTypeRDV().toString());
            jLabelDate.setText(dateFormat.format(evenement.getDateDeDebut(false)));
            jLabelHeureDeDebut.setText(timeFormat.format(evenement.getHeureDeDebut()));
            jLabelHeureDeFin.setText(timeFormat.format(evenement.getHeureDeFin()));
            jLabelCommentaire.setText(evenement.getCommentaire());
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
        jTableEvenements = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonRechercher = new javax.swing.JButton();
        jDateChooserDateEvenement = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelDate = new javax.swing.JLabel();
        jLabelHeureDeDebut = new javax.swing.JLabel();
        jLabelHeureDeFin = new javax.swing.JLabel();
        jLabelCommentaire = new javax.swing.JLabel();
        jButtonAjouter = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableEvenements.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Contact"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEvenements.setRowHeight(20);
        jTableEvenements.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableEvenementsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEvenements);
        if (jTableEvenements.getColumnModel().getColumnCount() > 0) {
            jTableEvenements.getColumnModel().getColumn(0).setResizable(false);
            jTableEvenements.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel2.setText("Date");

        jLabel3.setText("Recherche");

        jButtonRechercher.setText("Rechercher");
        jButtonRechercher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonRechercherMousePressed(evt);
            }
        });

        jDateChooserDateEvenement.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserDateEvenementPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserDateEvenement, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jButtonRechercher))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserDateEvenement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRechercher)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel4.setText("Votre rendez-vous");

        jLabel5.setText("Type");

        jLabel7.setText("Commentaire");

        jLabel8.setText("Date");

        jLabel9.setText("Heure de début");
        jLabel9.setToolTipText("");

        jLabel10.setText("Heure de fin");

        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAjouterMousePressed(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonModifierMousePressed(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonSupprimerMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonAjouter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonModifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSupprimer)
                        .addGap(0, 208, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                .addComponent(jLabelType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelHeureDeDebut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelCommentaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelHeureDeFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelType, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelHeureDeDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabelHeureDeFin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabelCommentaire, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAjouter)
                    .addComponent(jButtonModifier)
                    .addComponent(jButtonSupprimer))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"8h - 9h"},
                {"9h - 10h"},
                {"10h - 11h"},
                {"11h - 12h"},
                {"12h - 13h"},
                {"13h - 14h"},
                {"14h - 15h"},
                {"15h - 16h"},
                {"16h - 17h"},
                {"17h - 18h"},
                {"18h - 19h"},
                {"19h - 20h"},
                {"20h - 21h"},
                {"21h - 22h"}
            },
            new String [] {
                "Horaires"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableEvenementsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEvenementsMousePressed
        int rowIndex = jTableEvenements.getSelectedRow();
        Singleton.getCurrent().evenement = (Evenement)jTableEvenements.getValueAt(rowIndex, 0);
        DislayCurrentEvenement(false);
    }//GEN-LAST:event_jTableEvenementsMousePressed

    private void jButtonRechercherMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRechercherMousePressed
        LoadEvenements();
    }//GEN-LAST:event_jButtonRechercherMousePressed

    private void jButtonAjouterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAjouterMousePressed
        Singleton.getCurrent().editModeEvenement = EditMode.CREATION;
        
        VueEvenementEdit vueEvenementEdit = new VueEvenementEdit();
        vueEvenementEdit.setVisible(true);
    }//GEN-LAST:event_jButtonAjouterMousePressed

    private void jButtonModifierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModifierMousePressed
        if (Singleton.getCurrent().evenement != null) {
            Singleton.getCurrent().editModeEvenement = EditMode.MODIFICATION;
            
            VueEvenementEdit vueEvenementEdit = new VueEvenementEdit();
            vueEvenementEdit.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Aucun événement n'a été sélectionné !", "Attention !", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonModifierMousePressed

    private void jButtonSupprimerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSupprimerMousePressed
        if (Singleton.getCurrent().evenement != null) {
            int retour = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir supprimer cet événement ?", "Suppression", JOptionPane.YES_NO_OPTION);
            if( retour == JOptionPane.YES_NO_OPTION) {
                DAOEvenement.suppressionEvenement(Singleton.getCurrent().evenement);
                Singleton.getCurrent().evenement = null;
                DislayCurrentEvenement(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Aucun événement n'a été sélectionné !", "Attention !", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSupprimerMousePressed

    private void jDateChooserDateEvenementPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserDateEvenementPropertyChange
        if (this.isActive()) {
            LoadEvenements();
            
            if (jTableEvenements.getRowCount() > 0) {
                Singleton.getCurrent().evenement = (Evenement)jTableEvenements.getValueAt(0, 0);
            } else {
                Singleton.getCurrent().evenement = null;
            }
            
            DislayCurrentEvenement(true);
        }
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
            java.util.logging.Logger.getLogger(VueEvenement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueEvenement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueEvenement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueEvenement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueEvenement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonRechercher;
    private javax.swing.JButton jButtonSupprimer;
    private com.toedter.calendar.JDateChooser jDateChooserDateEvenement;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCommentaire;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelHeureDeDebut;
    private javax.swing.JLabel jLabelHeureDeFin;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableEvenements;
    // End of variables declaration//GEN-END:variables
}
