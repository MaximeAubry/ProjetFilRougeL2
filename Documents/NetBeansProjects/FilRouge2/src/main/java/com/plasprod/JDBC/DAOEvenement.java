package com.plasprod.JDBC;

import com.plasprod.Models.Enums.TypeRdv;
import com.plasprod.Models.Evenement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class DAOEvenement {
    public static void ajoutEvenement(Evenement evenement) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Evenement " +
                                "(typeRDV,commentaire,dateDeDebut,dateDeFin,IdCommercial,IdContact) " +
                            "VALUES " +
                                "(?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,evenement.getTypeRDV().name());
            preparedStatement.setString(2,evenement.getCommentaire());
            preparedStatement.setTimestamp(3,new Timestamp(evenement.getDateDeDebut().getTime()));
            preparedStatement.setTimestamp(4,new Timestamp(evenement.getDateDeFin().getTime()));
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
            String requete = "UPDATE Evenement " +
                            "SET " +
                                "typeRDV = ? " +
                                ",commentaire = ? " +
                                ",dateDeDebut = ? " +
                                ",dateDeFin = ? " +
                                ",IdCommercial = ? " +
                                ",IdContact = ? " +
                            "WHERE id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,evenement.getTypeRDV().name());
            preparedStatement.setString(2,evenement.getCommentaire());
            preparedStatement.setTimestamp(3,new Timestamp(evenement.getDateDeDebut().getTime()));
            preparedStatement.setTimestamp(4,new Timestamp(evenement.getDateDeFin().getTime()));
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
            String requete = "DELETE " +
                            "FROM Evenement " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,evenement.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<Evenement> getListEvenements(Date dateEvenement) {
        ArrayList<Evenement> evenements = new ArrayList<Evenement>();
        ConnectionBDD.creerConnection();
        try {
            Calendar dateDeDebut = Calendar.getInstance();
            dateDeDebut.setTime(dateEvenement);
            dateDeDebut.set(Calendar.HOUR_OF_DAY, 0);
            dateDeDebut.set(Calendar.MINUTE, 0);
            dateDeDebut.set(Calendar.SECOND, 0);
            dateDeDebut.set(Calendar.MILLISECOND, 0);
            
            Calendar dateDeFin = Calendar.getInstance();
            dateDeFin.setTime(dateDeDebut.getTime());
            dateDeFin.add(Calendar.DATE, 1);
            
            String requete = "SELECT Id,typeRDV,commentaire,dateDeDebut,dateDeFin,IdCommercial,IdContact " +
                            "FROM Evenement " +
                            "WHERE dateDeDebut >= ? AND dateDeFin <= ? " +
                            "ORDER BY dateDeDebut ASC;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setDate(1, new Date(dateDeDebut.getTimeInMillis()));
            preparedStatement.setDate(2, new Date(dateDeFin.getTimeInMillis()));
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Evenement evenement = new Evenement(
                    resultat.getLong("Id"),
                    TypeRdv.valueOf(resultat.getString("typeRDV")),
                    resultat.getString("commentaire"),
                    new Date(resultat.getTimestamp("dateDeDebut").getTime()),
                    new Date(resultat.getTimestamp("dateDeFin").getTime()),
                    resultat.getLong("IdContact"),
                    resultat.getLong("IdCommercial")
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
            String requete = "SELECT Id,typeRDV,commentaire,dateDeDebut,dateDeFin,IdCommercial,IdContact " +
                            "FROM Evenement " +
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
                    new Date(resultat.getTimestamp("dateDeDebut").getTime()),
                    new Date(resultat.getTimestamp("dateDeFin").getTime()),
                    resultat.getLong("IdContact"),
                    resultat.getLong("IdCommercial")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return evenement;
    }
}
