package com.library.management.services;

import com.library.management.config.DbConnection;
import com.library.management.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService  {
    User user;

    public UserService(User user) {
        this.user = user;
    }

    public User checkLogin() {
        Connection connection = DbConnection.connect();
        PreparedStatement statementAdmin;
        try {
            String queryAdmin = "SELECT * FROM user WHERE password = ? AND email = ?";
            statementAdmin = connection.prepareStatement(queryAdmin);

            statementAdmin.setString(1, user.getPassword());
            statementAdmin.setString(2, user.getEmail());

            ResultSet resultSetAdmin = statementAdmin.executeQuery();

            if (resultSetAdmin.next()) {
                User user = new User();
                user.setId(Integer.parseInt(resultSetAdmin.getString("id")));
                user.setNom(resultSetAdmin.getString("nom"));
                user.setPrenom(resultSetAdmin.getString("prenom"));
                user.setEmail(resultSetAdmin.getString("email"));
                resultSetAdmin.close();
                connection.close();
                return user;
            }   else {
                return null;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
