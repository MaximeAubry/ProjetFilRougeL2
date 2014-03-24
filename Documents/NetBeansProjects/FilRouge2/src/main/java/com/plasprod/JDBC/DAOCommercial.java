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
            String requete = "INSERT INTO commercial\n" +
                                "(reference,nom,prenom,email,telephone,identifiant,motDePasse,actif)\n" +
                            "VALUES\n" +
                                "(?,?,?,?,?,?,?,?);";
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
            String requete = "UPDATE commercial\n" +
                            "SET\n" +
                                "reference = ?\n" +
                                ",nom = ?\n" +
                                ",prenom = ?\n" +
                                ",email = ?\n" +
                                ",telephone = ?\n" +
                                ",identifiant = ?\n" +
                                ",motDePasse = ?\n" +
                                ",actif = ?\n" +
                            "WHERE id = ?;";
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
            String requete = "DELETE\n" +
                            "FROM commercial\n" +
                            "WHERE id = ?;";
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
            String requete = "SELECT Id,reference,nom,prenom,email,telephone,identifiant,motDePasse,actif\n" +
                            "FROM Commercial;";
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
            String requete = "SELECT Id,reference,nom,prenom,email,telephone,identifiant,motDePasse,actif\n" +
                            "FROM Commercial\n" +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                commercial = new Commercial(
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return commercial;
    }
}