package com.library.management.model;

import com.library.management.DbConnection;

import java.sql.*;

public class User {
    private int id;
    private String nom,prenom,email,password,role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public User checkLogin() {
        Connection connection = DbConnection.connect();
        PreparedStatement statementSimpleUser;
        PreparedStatement statementAdmin;
        try {
            String querySimpleUser = "SELECT * FROM simpleUser WHERE password = ? AND email = ?";
            String queryAdmin = "SELECT * FROM admin WHERE password = ? AND email = ?";
            statementSimpleUser = connection.prepareStatement(querySimpleUser);
            statementAdmin = connection.prepareStatement(queryAdmin);
            statementSimpleUser.setString(1, getPassword());
            statementSimpleUser.setString(2, getEmail());

            statementAdmin.setString(1, getPassword());
            statementAdmin.setString(2, getEmail());

            ResultSet resultSetSimpleUser = statementSimpleUser.executeQuery();
            ResultSet resultSetAdmin = statementAdmin.executeQuery();

            if (resultSetSimpleUser.next()) {
                User user = new User();
                user.setId(Integer.parseInt(resultSetSimpleUser.getString("id")));
                user.setNom(resultSetSimpleUser.getString("nom"));
                user.setPrenom(resultSetSimpleUser.getString("prenom"));
                user.setEmail(resultSetSimpleUser.getString("email"));
                user.setRole("simpleUser");
                statementSimpleUser.close();
                connection.close();
                return user;
            } else if (resultSetAdmin.next()) {
                User user = new User();
                user.setId(Integer.parseInt(resultSetAdmin.getString("id")));
                user.setNom(resultSetAdmin.getString("nom"));
                user.setPrenom(resultSetAdmin.getString("prenom"));
                user.setEmail(resultSetAdmin.getString("email"));
                user.setRole("admin");
                statementAdmin.close();
                connection.close();
                return user;
            } else {
                return null;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }



}
