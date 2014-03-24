package com.plasprod.JDBC;

import com.plasprod.Models.Satisfaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOSatisfaction {
    public static void ajoutSatisfaction(Satisfaction satisfaction) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Satisfaction\n" +
                                "(note,commentaire,dateSatisfaction,IdDocument,IdEvenement)\n" +
                            "VALUES\n" +
                                "(?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,satisfaction.getNote());
            preparedStatement.setString(2,satisfaction.getCommentaire());
            preparedStatement.setDate(3,satisfaction.getDateSatisfaction());
            preparedStatement.setLong(4,satisfaction.getIdDocument());
            preparedStatement.setLong(5,satisfaction.getIdEvenement());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static void modificationSatisfaction(Satisfaction satisfaction) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE Satisfaction\n" +
                            "SET\n" +
                                "note = ?\n" +
                                ",commentaire = ?\n" +
                                ",dateSatisfaction = ?\n" +
                                ",IdDocument = ?\n" +
                                ",IdEvenement = ?\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setInt(1,satisfaction.getNote());
            preparedStatement.setString(2,satisfaction.getCommentaire());
            preparedStatement.setDate(3,satisfaction.getDateSatisfaction());
            preparedStatement.setLong(4,satisfaction.getIdDocument());
            preparedStatement.setLong(5,satisfaction.getIdEvenement());
            preparedStatement.setLong(6,satisfaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static void suppressionSatisfaction(Satisfaction satisfaction) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE\n" +
                            "FROM Satisfaction\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,satisfaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<Satisfaction> getListSatisfactions() {
        ArrayList<Satisfaction> satisfactions = new ArrayList<Satisfaction>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,note,commentaire,dateSatisfaction,IdDocument,IdEvenement\n" +
                            "FROM Satisfaction;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Satisfaction satisfaction = new Satisfaction(
                    resultat.getLong("Id"),
                    resultat.getInt("Note"),
                    resultat.getString("commentaire"),
                    resultat.getDate("dateSatisfaction"),
                    resultat.getLong("IdDocument"),
                    resultat.getLong("IdEvenement")
                );
                satisfactions.add(satisfaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return satisfactions;
    }
    
    public static Satisfaction getSatisfaction(long Id) {
        Satisfaction satisfaction = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,note,commentaire,dateSatisfaction,IdDocument,IdEvenement\n" +
                            "FROM Satisfaction\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                satisfaction = new Satisfaction(
                    resultat.getLong("Id"),
                    resultat.getInt("Note"),
                    resultat.getString("commentaire"),
                    resultat.getDate("dateSatisfaction"),
                    resultat.getLong("IdDocument"),
                    resultat.getLong("IdEvenement")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return satisfaction;
    }
}
