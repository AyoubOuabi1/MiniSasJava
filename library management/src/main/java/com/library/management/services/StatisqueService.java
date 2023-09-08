package com.library.management.services;

import com.library.management.config.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisqueService {

    public static int countLivresDispo() {
        int count=0;
        Connection connection= DbConnection.connect();
        String query = "SELECT COUNT(*) as totalLivre  FROM livre";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1); // Get the count from the first column
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println("Error counting available Livres: " + exception.getMessage());
        }

        return count;
    }
    public static int countLivresPerdu() {
        int count=0;
        Connection connection= DbConnection.connect();
        String query = "SELECT COUNT(*) as totalLivre  FROM livreperdu";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1); // Get the count from the first column
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println("Error counting available Livres: " + exception.getMessage());
        }

        return count;
    }
    public static int countLivresEmp() {
        int count=0;
        Connection connection= DbConnection.connect();
        String query = "SELECT COUNT(*) as totalLivre  FROM empruntelivre";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1); // Get the count from the first column
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println("Error counting available Livres: " + exception.getMessage());
        }

        return count;
    }
}
