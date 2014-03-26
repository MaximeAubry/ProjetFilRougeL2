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
            String requete = "INSERT INTO Satisfaction " +
                                "(note,commentaire,dateSatisfaction,IdDocument,IdEvenement) " +
                            "VALUES " +
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
            String requete = "UPDATE Satisfaction " +
                            "SET " +
                                "note = ? " +
                                ",commentaire = ? " +
                                ",dateSatisfaction = ? " +
                                ",IdDocument = ? " +
                                ",IdEvenement = ? " +
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
            String requete = "DELETE " +
                            "FROM Satisfaction " +
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
            String requete = "SELECT Id,note,commentaire,dateSatisfaction,IdDocument,IdEvenement " +
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
            String requete = "SELECT Id,note,commentaire,dateSatisfaction,IdDocument,IdEvenement " +
                            "FROM Satisfaction " +
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
