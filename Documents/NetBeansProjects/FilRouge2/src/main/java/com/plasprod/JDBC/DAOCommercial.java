package com.plasprod.JDBC;

import com.plasprod.Models.Commercial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCommercial{

    public static void ajoutCommercial(Commercial commercial) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO commercial(reference,nom,prenom,email,telephone,identifiant,motDePasse,actif) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,commercial.getReference());
            preparedStatement.setString(2,commercial.getNom());
            preparedStatement.setString(3,commercial.getPrenom());
            preparedStatement.setString(4,commercial.getEmail());
            preparedStatement.setString(5,commercial.getTelephone());
            preparedStatement.setString(6,commercial.getIdentifiant());
            preparedStatement.setString(7,commercial.getMotDePasse());
            preparedStatement.setBoolean(8,commercial.isActif());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationCommercial(Commercial commercial) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE commercial SET reference = ?,nom = ?,prenom = ?,email = ?,telephone = ?,identifiant = ?,motDePasse = ?,actif = ? WHERE id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,commercial.getReference());
            preparedStatement.setString(2,commercial.getNom());
            preparedStatement.setString(3,commercial.getPrenom());
            preparedStatement.setString(4,commercial.getEmail());
            preparedStatement.setString(5,commercial.getTelephone());
            preparedStatement.setString(6,commercial.getIdentifiant());
            preparedStatement.setString(7,commercial.getMotDePasse());
            preparedStatement.setBoolean(8,commercial.isActif());
            preparedStatement.setLong(8,commercial.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionCommercial(Commercial commercial) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE FROM commercial WHERE id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,commercial.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<Commercial> getListCommerciaux() {
        ArrayList<Commercial> commerciaux = new ArrayList<Commercial>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,nom,prenom,email,telephone,identifiant,motDePasse,actif FROM Commercial;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Commercial commercial = new Commercial(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    resultat.getString("nom"),
                    resultat.getString("prenom"),
                    resultat.getString("email"),
                    resultat.getString("telephone"),
                    resultat.getString("identifiant"),
                    resultat.getString("motDePasse"),
                    resultat.getBoolean("actif")
                );
                commerciaux.add(commercial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return commerciaux;
    }
    
    public static Commercial getCommercial(long Id) {
        Commercial commercial = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,nom,prenom,email,telephone,identifiant,motDePasse,actif FROM Commercial WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            commercial = (Commercial)preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return commercial;
    }
    
    /*public static List<Commande> getCommandeParCommercial(Commercial commercial) {

        List<Commande> listCommande = null;
        ConnectionBDD.creerConnection();
        String requete = "SELECT * FROM commande WHERE IdCommercial = ?";
        PreparedStatement preparedStatement;
        try {
                preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
                preparedStatement.setLong(1,commercial.getId());
                listCommande = (List<Commande>) preparedStatement.executeQuery();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return ((List<Commande>) listCommande);
    }
    
    public static Commercial getCommercialParId(long id) {

        List<Commercial> listCommercial = null;
        ConnectionBDD.creerConnection();
        String requete = "SELECT * FROM commercial WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
                preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
                preparedStatement.setLong(1,id);
                listCommercial = (List<Commercial>) preparedStatement.executeQuery();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return (Commercial) listCommercial.get(0);
    }*/
}