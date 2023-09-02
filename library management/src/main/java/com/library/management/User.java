package com.library.management;

import java.sql.*;

public class User {
    int id;
    String nom,prenom,email,password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int checkLogin() {
        Connection connection = DbConnection.connect();
        try {
            String querySimpleUser = "SELECT * FROM simpleUser WHERE password = ? AND email = ?";
            String queryAdmin = "SELECT * FROM admin WHERE password = ? AND email = ?";
            PreparedStatement statementSimpleUser = connection.prepareStatement(querySimpleUser);
            PreparedStatement statementAdmin = connection.prepareStatement(queryAdmin);
            statementSimpleUser.setString(1, getPassword());
            statementSimpleUser.setString(2, getEmail());

            statementAdmin.setString(1, getPassword());
            statementAdmin.setString(2, getEmail());

            ResultSet resultSetSimpleUser = statementSimpleUser.executeQuery();
            ResultSet resultSetAdmin = statementAdmin.executeQuery();

            if (resultSetSimpleUser.next()) {
                return 1;
            } else if (resultSetAdmin.next()) {
                return 2;
            } else {
                return 0;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return -1; // Handle error condition
    }

}
