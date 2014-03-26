package com.plasprod.JDBC;

import com.plasprod.Models.Article;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOArticle {
    public static void ajoutArticle(Article article) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "INSERT INTO Article " +
                                "(reference,nom,couleur,quantiteStock,unite,prixUnitaire,actif) " +
                            "VALUES " +
                                "(?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,article.getReference());
            preparedStatement.setString(2,article.getNom());
            preparedStatement.setString(3,article.getCouleur());
            preparedStatement.setDouble(4,article.getQuantiteStock());
            preparedStatement.setString(5,article.getUnite());
            preparedStatement.setDouble(6,article.getPrixUnitaire());
            preparedStatement.setBoolean(7,article.isActif());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationArticle(Article article) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "UPDATE Article " +
                            "SET " +
                                "reference = ? " +
                                ",nom = ? " +
                                ",couleur = ? " +
                                ",quantiteStock = ? " +
                                ",unite = ? " +
                                ",prixUnitaire = ? " +
                                ",actif = ? " +
                            "WHERE id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1,article.getReference());
            preparedStatement.setString(2,article.getNom());
            preparedStatement.setString(3,article.getCouleur());
            preparedStatement.setDouble(4,article.getQuantiteStock());
            preparedStatement.setString(5,article.getUnite());
            preparedStatement.setDouble(6,article.getPrixUnitaire());
            preparedStatement.setBoolean(7,article.isActif());
            preparedStatement.setLong(8,article.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionArticle(Article article) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE " +
                            "FROM Article " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1,article.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
    
    public static ArrayList<Article> getListArticles() {
        ArrayList<Article> articles = new ArrayList<Article>();
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,nom,couleur,quantiteStock,unite,prixUnitaire,actif " +
                            "FROM Article;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Article article = new Article(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    resultat.getString("nom"),
                    resultat.getString("couleur"),
                    resultat.getDouble("quantiteStock"),
                    resultat.getString("unite"),
                    resultat.getDouble("prixUnitaire"),
                    resultat.getBoolean("actif")
                );
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return articles;
    }
    
    public static Article getArticle(long Id) {
        Article article = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,nom,couleur,quantiteStock,unite,prixUnitaire,actif " +
                            "FROM Article " +
                            "WHERE Id = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                article = new Article(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    resultat.getString("nom"),
                    resultat.getString("couleur"),
                    resultat.getDouble("quantiteStock"),
                    resultat.getString("unite"),
                    resultat.getDouble("prixUnitaire"),
                    resultat.getBoolean("actif")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return article;
    }
    
    public static Article getArticle(String reference) {
        Article article = null;
        ConnectionBDD.creerConnection();
        try {
            String requete = "SELECT Id,reference,nom,couleur,quantiteStock,unite,prixUnitaire,actif " +
                            "FROM Article " +
                            "WHERE reference = ?;";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setString(1, reference);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                article = new Article(
                    resultat.getLong("Id"),
                    resultat.getString("reference"),
                    resultat.getString("nom"),
                    resultat.getString("couleur"),
                    resultat.getDouble("quantiteStock"),
                    resultat.getString("unite"),
                    resultat.getDouble("prixUnitaire"),
                    resultat.getBoolean("actif")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return article;
    }
}