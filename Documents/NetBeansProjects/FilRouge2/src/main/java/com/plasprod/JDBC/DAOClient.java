package com.plasprod.JDBC;

import com.plasprod.Models.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOClient{
    public static void ajoutClient(Client client) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Client (reference,raisonSociale,adresse,codePostal,ville,pays,actif) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,client.getReference());
            preparedStatement.setString(2,client.getRaisonSociale());
            preparedStatement.setString(3,client.getAdresse());
            preparedStatement.setString(4,client.getCodePostal());
            preparedStatement.setString(5,client.getVille());
            preparedStatement.setString(6,client.getPays());
            preparedStatement.setBoolean(7,client.isActif());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationClient(Client client) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE Client SET reference = ?,raisonSociale = ?,adresse = ?,codePostal = ?,ville = ?,pays = ?,actif = ? WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,client.getReference());
            preparedStatement.setString(2,client.getRaisonSociale());
            preparedStatement.setString(3,client.getAdresse());
            preparedStatement.setString(4,client.getCodePostal());
            preparedStatement.setString(5,client.getVille());
            preparedStatement.setString(6,client.getPays());
            preparedStatement.setBoolean(7,client.isActif());
            preparedStatement.setLong(8,client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionClient(Client client) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE FROM Client WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<Client> getListClients() {
        ArrayList<Client> clients = new ArrayList<Client>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,raisonSociale,adresse,codePostal,ville,pays,actif FROM Client;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Client client = new Client(
                    resultat.getLong("Id"), 
                    resultat.getString("reference"), 
                    resultat.getString("raisonSociale"), 
                    resultat.getString("adresse"),
                    resultat.getString("codePostal"),
                    resultat.getString("ville"),
                    resultat.getString("pays"),
                    resultat.getBoolean("actif")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return clients;
    }
    
    public static Client getClient(long Id) {
        Client client = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,raisonSociale,adresse,codePostal,ville,pays,actif FROM Client WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                client = new Client(resultat.getLong("Id"), 
                            resultat.getString("reference"), 
                            resultat.getString("raisonSociale"), 
                            resultat.getString("adresse"),
                            resultat.getString("codePostal"),
                            resultat.getString("ville"),
                            resultat.getString("pays"),
                            resultat.getBoolean("actif"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return client;
    }
}