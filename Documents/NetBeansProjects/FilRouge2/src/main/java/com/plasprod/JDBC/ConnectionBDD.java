package com.plasprod.JDBC;

import java.sql.Connection;   
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBDD {

    static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=FilRougeL2";
    static final String DB_LOGIN = "sa";
    static final String DB_PASSWORD = "hellsing";
    public static Connection connection = null;

    public static void creerConnection() {
        try {
            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("Le chargement du driver fonctionne");
            } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Impossible de charger le pilote jdbc:odbc");
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        }
        catch (SQLException e) {
            System.out.println(e);
            System.out.println("Connection BDD impossible");
        }
    }

    public static void fermerConnection() {
        try {
            connection.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
