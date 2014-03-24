package com.plasprod.JDBC;

import com.plasprod.Models.Enums.TypeRdv;
import com.plasprod.Models.Evenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEvenement {
    public static void ajoutEvenement(Evenement evenement) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Evenement\n" +
                                "(typeRDV,commentaire,dateDeDebut,dateDeFin,IdCommercial,IdContact)\n" +
                            "VALUES\n" +
                                "(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,evenement.getTypeRDV().toString());
            preparedStatement.setString(2,evenement.getCommentaire());
            preparedStatement.setDate(3,evenement.getDateDeDebut());
            preparedStatement.setDate(4,evenement.getDateDeFin());
            preparedStatement.setLong(5,evenement.getIdCommercial());
            preparedStatement.setLong(6,evenement.getIdContact());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static void modificationEvenement(Evenement evenement) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE Evenement\n" +
                            "SET\n" +
                                "typeRDV = ?\n" +
                                ",commentaire = ?\n" +
                                ",dateDeDebut = ?\n" +
                                ",dateDeFin = ?\n" +
                                ",IdCommercial = ?\n" +
                                ",IdContact = ?\n" +
                            "WHERE id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,evenement.getTypeRDV().toString());
            preparedStatement.setString(2,evenement.getCommentaire());
            preparedStatement.setDate(3,evenement.getDateDeDebut());
            preparedStatement.setDate(4,evenement.getDateDeFin());
            preparedStatement.setLong(5,evenement.getIdCommercial());
            preparedStatement.setLong(6,evenement.getIdContact());
            preparedStatement.setLong(7,evenement.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static void suppressionEvenement(Evenement evenement) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE\n" +
                            "FROM Evenement\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,evenement.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<Evenement> getListEvenements() {
        ArrayList<Evenement> evenements = new ArrayList<Evenement>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,typeRDV,commentaire,dateDeDebut,dateDeFin,IdCommercial,IdContact\n" +
                            "FROM Evenement;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Evenement evenement = new Evenement(
                    resultat.getLong("Id"),
                    TypeRdv.valueOf(resultat.getString("typeRDV")),
                    resultat.getString("commentaire"),
                    resultat.getDate("dateDeDebut"),
                    resultat.getDate("dateDeFin"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact")
                );
                evenements.add(evenement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return evenements;
    }
    
    public static Evenement getEvenement(long Id) {
        Evenement evenement = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,typeRDV,commentaire,dateDeDebut,dateDeFin,IdCommercial,IdContact\n" +
                            "FROM Evenement\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                evenement = new Evenement(
                    resultat.getLong("Id"),
                    TypeRdv.valueOf(resultat.getString("typeRDV")),
                    resultat.getString("commentaire"),
                    resultat.getDate("dateDeDebut"),
                    resultat.getDate("dateDeFin"),
                    resultat.getLong("IdCommercial"),
                    resultat.getLong("IdContact")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return evenement;
    }
}
