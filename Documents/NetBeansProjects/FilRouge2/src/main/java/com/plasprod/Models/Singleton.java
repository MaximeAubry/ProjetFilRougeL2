/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

import com.plasprod.Models.Enums.EditMode;
import com.plasprod.Views.VueEvenement;
import com.plasprod.Views.VueClient;
import com.plasprod.Views.VueCommercial;
import com.plasprod.Views.VueContact;
import com.plasprod.Views.VueDevis;
import com.plasprod.Views.VueGlobale;
import com.plasprod.Views.VueLogin;

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
    public VueCommercial vueCommercial;
    public VueContact vueContact;
    public VueDevis vueDevis;
    public VueEvenement vueEvenement;
    public VueGlobale vueGlobale;
    public VueLogin vueLogin;
    
    // mon constructeur
    private Singleton()
    {
        /*this.editModeArticle = null;
        this.editModeClient = null;
        this.editModeCommande = null;
        this.editModeCommercial = null;
        this.editModeContact = null;
        this.editModeDevis = null;
        this.editModeDocument = null;
        this.editModeEvenement = null;
        this.editModeSatisfaction = null;
        this.editModeLigneDeDocument = null;
        
        this.vueClient = new VueClient();
        this.vueCommercial = new VueCommercial();
        this.vueContact = new VueContact();
        this.vueDevis = new VueDevis();
        this.vueEvenement = new VueEvenement();
        this.vueGlobale = new VueGlobale();
        this.vueLogin = new VueLogin();*/
    }

    private static Singleton _current;
    public static Singleton getCurrent() {
        if (_current == null) {
            _current = new Singleton();
        }
                
        return _current;
    }
}
