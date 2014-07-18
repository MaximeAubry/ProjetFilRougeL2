package com.plasprod.Views;

import com.plasprod.CustomLibraries.MD5Generator;
import com.plasprod.JDBC.DAOCommercial;
import com.plasprod.Models.Commercial;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Models.Enums.TypeCommercial;
import com.plasprod.Models.Singleton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

public class VueCommercialEdit extends javax.swing.JFrame {
    // objet de la page
    Commercial commercial = null;
    
    // Models pour les composants
    final DefaultComboBoxModel modelComboBoxTypeCommercial;
    
    /**
     * Creates new form VueCommercialEdit
     */
    public VueCommercialEdit() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        switch (Singleton.getCurrent().editModeCommercial) {
            case CREATION:
                commercial = new Commercial();
                break;
                
            case MODIFICATION:
                commercial = Singleton.getCurrent().commercial;
                break;
        }
        
        modelComboBoxTypeCommercial = (DefaultComboBoxModel)jComboBoxTypeCommercial.getModel();
        modelComboBoxTypeCommercial.removeAllElements();
        for (TypeCommercial typeCommercial : TypeCommercial.values()) {
            modelComboBoxTypeCommercial.addElement(typeCommercial);
        }
        
        jTextFieldReference.setText(commercial.getReference());
        jTextFieldNom.setText(commercial.getNom());
        jTextFieldPrenom.setText(commercial.getPrenom());
        jTextFieldEmail.setText(commercial.getEmail());
        jTextFieldTelephone.setText(commercial.getTelephone());
        jTextFieldIdentifiant.setText(commercial.getIdentifiant());
        jCheckBoxActif.setSelected(commercial.isActif());
        
        if (Singleton.getCurrent().editModeCommercial == EditMode.MODIFICATION) {
            TypeCommercial typeCommercial = TypeCommercial.valueOf(commercial.getTypeCommercial().name());
            jComboBoxTypeCommercial.setSelectedItem(typeCommercial);
        }
        
        SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run() {
                jComboBoxTypeCommercial.setModel(modelComboBoxTypeCommercial);
                jComboBoxTypeCommercial.revalidate();
                jComboBoxTypeCommercial.repaint();
            }
        });
    }
    
    private void DisplayErrors() {
        // nom
        if (commercial.getConstraintViolations().containsKey("NomIsValid")) {
            jLabelNomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelNomIsValid.setToolTipText(commercial.getConstraintViolations().get("NomIsValid"));
        } else {
            jLabelNomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelNomIsValid.setToolTipText(null);
        }
        
        // prénom
        if (commercial.getConstraintViolations().containsKey("PrenomIsValid")) {
            jLabelPrenomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelPrenomIsValid.setToolTipText(commercial.getConstraintViolations().get("PrenomIsValid"));
        } else {
            jLabelPrenomIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelPrenomIsValid.setToolTipText(null);
        }
        
        // email
        if (commercial.getConstraintViolations().containsKey("EmailIsValid")) {
            jLabelEmailIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelEmailIsValid.setToolTipText(commercial.getConstraintViolations().get("EmailIsValid"));
        } else {
            jLabelEmailIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelEmailIsValid.setToolTipText(null);
        }
        
        // téléphone
        if (commercial.getConstraintViolations().containsKey("TelephoneIsValid")) {
            jLabelTelephoneIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelTelephoneIsValid.setToolTipText(commercial.getConstraintViolations().get("TelephoneIsValid"));
        } else {
            jLabelTelephoneIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelTelephoneIsValid.setToolTipText(null);
        }
        
        // identifiant
        if (commercial.getConstraintViolations().containsKey("IdentifiantIsValid")) {
            jLabelIdentifiantIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelIdentifiantIsValid.setToolTipText(commercial.getConstraintViolations().get("IdentifiantIsValid"));
        } else {
            jLabelIdentifiantIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelIdentifiantIsValid.setToolTipText(null);
        }
        
        // mot de passe
        if (commercial.getConstraintViolations().containsKey("MotDePasseIsValid")) {
            jLabelMotDePasseIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\deny-icon.png"));
            jLabelMotDePasseIsValid.setToolTipText(commercial.getConstraintViolations().get("MotDePasseIsValid"));
        } else {
            jLabelMotDePasseIsValid.setIcon(new javax.swing.ImageIcon("C:\\Users\\Maxime\\Desktop\\ProjetFilRougeL2\\Documents\\NetBeansProjects\\FilRouge2\\src\\main\\java\\com\\plasprod\\Images\\Accept-icon.png"));
            jLabelMotDePasseIsValid.setToolTipText(null);
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

        jLabelReferenceIsValid6 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldReference = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPrenom = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldTelephone = new javax.swing.JTextField();
        jTextFieldIdentifiant = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBoxActif = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jButtonEnregistrer = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jPasswordFieldMotDePasse = new javax.swing.JPasswordField();
        jComboBoxTypeCommercial = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabelNomIsValid = new javax.swing.JLabel();
        jLabelPrenomIsValid = new javax.swing.JLabel();
        jLabelEmailIsValid = new javax.swing.JLabel();
        jLabelTelephoneIsValid = new javax.swing.JLabel();
        jLabelIdentifiantIsValid = new javax.swing.JLabel();
        jLabelMotDePasseIsValid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setText("Référence");

        jTextFieldReference.setEnabled(false);

        jLabel7.setText("Nom");

        jLabel8.setText("Prénom");

        jLabel9.setText("Email");

        jLabel10.setText("Téléphone");

        jLabel11.setText("Identifiant");

        jLabel12.setText("Mot de passe");

        jLabel13.setText("Actif");

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

        jLabel14.setText("Type");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBoxActif)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 226, Short.MAX_VALUE)
                                .addComponent(jButtonEnregistrer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAnnuler))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldReference)
                                    .addComponent(jComboBoxTypeCommercial, javax.swing.GroupLayout.Alignment.LEADING, 0, 380, Short.MAX_VALUE)
                                    .addComponent(jPasswordFieldMotDePasse, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldIdentifiant, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldTelephone, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNomIsValid)
                                    .addComponent(jLabelPrenomIsValid)
                                    .addComponent(jLabelEmailIsValid)
                                    .addComponent(jLabelTelephoneIsValid)
                                    .addComponent(jLabelIdentifiantIsValid)
                                    .addComponent(jLabelMotDePasseIsValid))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8))
                                                    .addComponent(jLabelPrenomIsValid, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9)))
                                            .addComponent(jLabelEmailIsValid, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabelNomIsValid)
                                                .addGap(52, 52, 52)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10)))
                                    .addComponent(jLabelTelephoneIsValid, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextFieldIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelIdentifiantIsValid, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jPasswordFieldMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelMotDePasseIsValid, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxTypeCommercial)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jCheckBoxActif))
                .addGap(156, 156, 156)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnnuler)
                    .addComponent(jButtonEnregistrer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnregistrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEnregistrerMousePressed
        commercial.setReference(jTextFieldReference.getText());
        commercial.setNom(jTextFieldNom.getText());
        commercial.setPrenom(jTextFieldPrenom.getText());
        commercial.setEmail(jTextFieldEmail.getText());
        commercial.setTelephone(jTextFieldTelephone.getText());
        commercial.setIdentifiant(jTextFieldIdentifiant.getText());
        
        String motDePasse = new String(jPasswordFieldMotDePasse.getPassword());
        if (!motDePasse.isEmpty()) {
            commercial.setMotDePasse(MD5Generator.Generate(motDePasse));
        } else {
            commercial.setMotDePasse("");
        }
        
        commercial.setTypeCommercial((TypeCommercial)jComboBoxTypeCommercial.getSelectedItem());
        commercial.setActif(jCheckBoxActif.isSelected());

        Boolean isValid = commercial.isValid();
        DisplayErrors();
        
        if (isValid) {
            switch (Singleton.getCurrent().editModeCommercial) {
                case CREATION:
                    DAOCommercial.ajoutCommercial(commercial);
                    break;

                case MODIFICATION:
                    DAOCommercial.modificationCommercial(commercial);
                    break;
            }

            this.dispose();
            Singleton.getCurrent().vueCommercial.DislayCurrentCommercial(true);
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
            java.util.logging.Logger.getLogger(VueCommercialEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueCommercialEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueCommercialEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueCommercialEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueCommercialEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonEnregistrer;
    private javax.swing.JCheckBox jCheckBoxActif;
    private javax.swing.JComboBox jComboBoxTypeCommercial;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEmailIsValid;
    private javax.swing.JLabel jLabelIdentifiantIsValid;
    private javax.swing.JLabel jLabelMotDePasseIsValid;
    private javax.swing.JLabel jLabelNomIsValid;
    private javax.swing.JLabel jLabelPrenomIsValid;
    private javax.swing.JLabel jLabelReferenceIsValid6;
    private javax.swing.JLabel jLabelTelephoneIsValid;
    private javax.swing.JPasswordField jPasswordFieldMotDePasse;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldIdentifiant;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldReference;
    private javax.swing.JTextField jTextFieldTelephone;
    // End of variables declaration//GEN-END:variables
}
