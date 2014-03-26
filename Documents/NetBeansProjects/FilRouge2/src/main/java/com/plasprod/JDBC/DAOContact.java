package com.plasprod.JDBC;

import com.plasprod.Models.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOContact{

    public static void ajoutContact(Contact contact) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Contact " +
                                "(reference,nom,prenom,email,telephone,actif,IdClient,IdCommercial) " +
                            "VALUES " +
                                "(?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,contact.getReference());
            preparedStatement.setString(2,contact.getNom());
            preparedStatement.setString(3,contact.getPrenom());
            preparedStatement.setString(4,contact.getEmail());
            preparedStatement.setString(5,contact.getTelephone());
            preparedStatement.setBoolean(6,contact.isActif());
            preparedStatement.setLong(7,contact.getIdClient());
            preparedStatement.setLong(8,contact.getIdCommercial());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationContact(Contact contact) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE Contact " +
                            "SET " +
                                "reference = ? " +
                                ",nom = ? " +
                                ",prenom = ? " +
                                ",email = ? " +
                                ",telephone = ? " +
                                ",actif = ? " +
                                ",IdClient = ? " +
                                ",IdCommercial = ? " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,contact.getReference());
            preparedStatement.setString(2,contact.getNom());
            preparedStatement.setString(3,contact.getPrenom());
            preparedStatement.setString(4,contact.getEmail());
            preparedStatement.setString(5,contact.getTelephone());
            preparedStatement.setBoolean(6,contact.isActif());
            preparedStatement.setLong(7,contact.getIdClient());
            preparedStatement.setLong(8,contact.getIdCommercial());
            preparedStatement.setLong(9,contact.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionContact(Contact contact) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE " +
                            "FROM Contact " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,contact.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<Contact> getListContacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,nom,prenom,email,telephone,actif,IdClient,IdCommercial " +
                            "FROM Contact;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Contact contact = new Contact(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    resultat.getString("nom"),
                    resultat.getString("prenom"),
                    resultat.getString("email"),
                    resultat.getString("telephone"),
                    resultat.getBoolean("actif"),
                    resultat.getLong("idClient"),
                    resultat.getLong("idCommercial")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return contacts;
    }
    
    public static Contact getContact(long Id) {
        Contact contact = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,nom,prenom,email,telephone,actif,IdClient,IdCommercial " +
                            "FROM Contact " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                contact = new Contact(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    resultat.getString("nom"),
                    resultat.getString("prenom"),
                    resultat.getString("email"),
                    resultat.getString("telephone"),
                    resultat.getBoolean("actif"),
                    resultat.getLong("idClient"),
                    resultat.getLong("idCommercial")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return contact;
    }
}