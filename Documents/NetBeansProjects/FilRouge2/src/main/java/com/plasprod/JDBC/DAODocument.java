package com.plasprod.JDBC;

import com.plasprod.Models.Commande;
import com.plasprod.Models.Commercial;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Antoine Demarly
 */
public class DAODocument {
    public static void creationDocumennt(Commande commande) {
        ConnectionBDD.creerConnection();
        String requete = "INSERT INTO document(dateDeCreation,idCommercial,idClient) VALUES (?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = ConnectionBDD.connection.prepareStatement(requete);
            preparedStatement.setDate(1,commande.getDateDeCreation());
            preparedStatement.setLong(2,commande.getIdCommercial());
            preparedStatement.setLong(3,commande.getIdClient());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionBDD.fermerConnection();
    }
}