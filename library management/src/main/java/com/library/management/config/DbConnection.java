package com.library.management.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection connect(){
        String url = "jdbc:mysql://localhost:3306/minisasjava";
        String user = "root";
        String password = "root";
        Connection connection;
        try {
            // Create a connection to the database
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
