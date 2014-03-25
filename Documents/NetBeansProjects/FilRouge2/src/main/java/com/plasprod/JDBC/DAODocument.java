package com.plasprod.JDBC;

import com.plasprod.Models.Document;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAODocument {
    public static long ajoutDocument(Document document) {
        long Id = 0;
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Document\n" +
                                "(dateDeCreation,reference,idCommercial,idClient)\n" +
                            "VALUES\n" +
                                "(?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setDate(1,document.getDateDeCreation());
            preparedStatement.setString(2,document.getReference());
            preparedStatement.setLong(3,document.getIdCommercial());
            preparedStatement.setLong(4,document.getIdClient());
            preparedStatement.executeUpdate();
            
            // retour du dernier Id
            requete = "SELECT MAX(Id) AS Id\n" +
                    "FROM Document;";
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.executeUpdate();
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
            String requete = "UPDATE Document\n" +
                            "SET\n" +
                                "dateDeCreation = ?\n" +
                                ",reference = ?\n" +
                                ",idCommercial = ?\n" +
                                ",idClient = ?\n" +
                            "WHERE IdDocument = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setDate(1,document.getDateDeCreation());
            preparedStatement.setString(2,document.getReference());
            preparedStatement.setLong(3,document.getIdCommercial());
            preparedStatement.setLong(4,document.getIdClient());
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
            String requete = "DELETE\n" +
                            "FROM Document\n" +
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