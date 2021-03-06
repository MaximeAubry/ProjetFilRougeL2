package com.plasprod.JDBC;

import com.plasprod.Models.Document;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DAODocument {
    public static long ajoutDocument(Document document) {
        long Id = 0;
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Document " +
                                "(reference,dateDeCreation,IdCommercial,IdContact) " +
                            "VALUES " +
                                "(?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,document.getReference());
            preparedStatement.setTimestamp(2,new Timestamp(document.getDateDeCreation().getTime()));
            preparedStatement.setLong(3,document.getIdCommercial());
            preparedStatement.setLong(4,document.getIdContact());
            preparedStatement.executeUpdate();
            
            // retour du dernier Id
            requete = "SELECT MAX(Id) AS Id " +
                    "FROM Document;";
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Id = resultat.getLong("Id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return Id;
    }
    
    public static void modificationDocument(Document document) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE Document " +
                            "SET " +
                                "reference = ? " +
                                ",dateDeCreation = ? " +
                                ",IdCommercial = ? " +
                                ",IdContact = ? " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,document.getReference());
            preparedStatement.setTimestamp(2,new Timestamp(document.getDateDeCreation().getTime()));
            preparedStatement.setLong(3,document.getIdCommercial());
            preparedStatement.setLong(4,document.getIdContact());
            preparedStatement.setLong(5,document.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionDocument(Document document) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE " +
                            "FROM Document " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,document.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
}