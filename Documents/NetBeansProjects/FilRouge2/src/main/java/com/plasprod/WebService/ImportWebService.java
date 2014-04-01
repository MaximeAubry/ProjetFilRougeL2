/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.WebService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plasprod.JDBC.DAOArticle;
import com.plasprod.Models.Article;
import com.plasprod.ModelsL1.Produit;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Antoine Demarly
 */
public class ImportWebService {
    
    public void connectionWebService(){
        WebResource resource = Client.create().resource("http://192.168.1.11:9000/filrouge/");
        ClientResponse response;
        response =
                resource.path("export/").accept(MediaType.APPLICATION_JSON)
                        .get(ClientResponse.class);
        String produitJson = response.getEntity(String.class);

        Gson gson = new Gson();
        Article article = null;

       //Produit produit = gson.fromJson(produitJson, Produit.class);

       List<Produit> produits =  
               gson.fromJson(produitJson, new TypeToken<ArrayList<Produit>>() {}.getType());

       for(Produit produit:produits)
       {
           article = DAOArticle.getArticle(produit.getReference());
           if (article == null)
            {
                DAOArticle.ajoutArticle(new Article(0, produit.getReference(), produit.getNom(), produit.getCouleur(), Double.parseDouble(Float.toString( produit.getQuantiteStock())), produit.getUnite(), Double.parseDouble(Float.toString(produit.getPrixUnitaire())), produit.isActif()));
            }
           else
           {
               DAOArticle.modificationArticle(new Article(0, produit.getReference(), produit.getNom(), produit.getCouleur(), Double.parseDouble(Float.toString( produit.getQuantiteStock())), produit.getUnite(), Double.parseDouble(Float.toString(produit.getPrixUnitaire())), produit.isActif()));
           }
       }
    }
}