/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import com.plasprod.JDBC.DAOCommercial;
import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Views.VueEvenement;
import com.plasprod.Views.VueClient;
import com.plasprod.Views.VueCommande;
import com.plasprod.Views.VueCommercial;
import com.plasprod.Views.VueContact;

/**
 *
 * @author Maxime
 */
public class Singleton {
    // mes variables
    public EditMode editModeArticle;
    public EditMode editModeClient;
    public EditMode editModeCommande;
    public EditMode editModeCommercial;
    public EditMode editModeContact;
    public EditMode editModeDevis;
    public EditMode editModeDocument;
    public EditMode editModeEvenement;
    public EditMode editModeSatisfaction;
    public EditMode editModeLigneDeDocument;
    
    public Commercial me;
    
    public Article article;
    public Client client;
    public Commande commande;
    public Commercial commercial;
    public Contact contact;
    public Devis devis;
    public Document document;
    public Evenement evenement;
    public Satisfaction satisfaction;
    public LigneDeDocument ligneDeDocument;
    
    public VueClient vueClient;
    public VueCommande vueCommande;
    public VueCommercial vueCommercial;
    public VueContact vueContact;
    public VueEvenement vueEvenement;
    
    // mon constructeur
    private Singleton()
    {
        this.editModeArticle = EditMode.CONSULTATION;
        this.editModeClient = EditMode.CONSULTATION;
        this.editModeCommande = EditMode.CONSULTATION;
        this.editModeCommercial = EditMode.CONSULTATION;
        this.editModeContact = EditMode.CONSULTATION;
        this.editModeDevis = EditMode.CONSULTATION;
        this.editModeDocument = EditMode.CONSULTATION;
        this.editModeEvenement = EditMode.CONSULTATION;
        this.editModeSatisfaction = EditMode.CONSULTATION;
        this.editModeLigneDeDocument = EditMode.CONSULTATION;
        
        this.vueClient = new VueClient();
        this.vueCommande = new VueCommande();
        this.vueCommercial = new VueCommercial();
        this.vueContact = new VueContact();
        this.vueEvenement = new VueEvenement();
        
        this.me = DAOCommercial.getCommercial(2);
    }

    private static Singleton _current;
    public static Singleton getCurrent() {
        if (_current == null)
                _current = new Singleton();
            return _current;
    }
}
