/*package com.plasprod.JDBC;

import com.plasprod.Models.Article;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOArticle {
    public static void ajoutArticle(Article article) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.set_(1,article._);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void modificationArticle(Article article) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.set_(1,article._);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }

    public static void suppressionArticle(Article article) {
        ConnectionBDD.creerConnection();
        try {
            String requete = "DELETE\n" +
                            "FROM Article\n" +
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
            String requete = "";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                Article article = new Article(
                    
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
            String requete = "";
            PreparedStatement preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setLong(1, Id);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next())
            {
                article = new Article(
                    
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
        return article;
    }
}
*/