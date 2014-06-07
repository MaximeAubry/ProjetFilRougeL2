package com.plasprod.JDBC;

import com.plasprod.Models.Enums.NiveauSatisfaction;
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
                                "(niveauSatisfaction,commentaire,dateSatisfaction,IdCommande) " +
                            "VALUES " +
                                "(?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,satisfaction.getNiveauSatisfaction().name());
            preparedStatement.setString(2,satisfaction.getCommentaire());
            preparedStatement.setDate(3,satisfaction.getDateSatisfaction());
            preparedStatement.setLong(4,satisfaction.getIdCommande());
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
                                "niveauSatisfaction = ? " +
                                ",commentaire = ? " +
                                ",dateSatisfaction = ? " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,satisfaction.getNiveauSatisfaction().name());
            preparedStatement.setString(2,satisfaction.getCommentaire());
            preparedStatement.setDate(3,satisfaction.getDateSatisfaction());
            preparedStatement.setLong(4,satisfaction.getId());
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
            String requete = "SELECT Id,niveauSatisfaction,commentaire,dateSatisfaction,IdCommande " +
                            "FROM Satisfaction;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Satisfaction satisfaction = new Satisfaction(
                    resultat.getLong("Id"),
                    NiveauSatisfaction.valueOf(resultat.getString("niveauSatisfaction")),
                    resultat.getString("commentaire"),
                    resultat.getDate("dateSatisfaction"),
                    resultat.getLong("IdCommande")
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
            String requete = "SELECT Id,niveauSatisfaction,commentaire,dateSatisfaction,IdDocument " +
                            "FROM Satisfaction " +
                            "WHERE IdEvenement = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                satisfaction = new Satisfaction(
                    resultat.getLong("Id"),
                    NiveauSatisfaction.valueOf(resultat.getString("niveauSatisfaction")),
                    resultat.getString("commentaire"),
                    resultat.getDate("dateSatisfaction"),
                    resultat.getLong("IdCommande")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return satisfaction;
    }
}
