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
    public boolean addUser(){
        Connection connection=DbConnection.connect();
        PreparedStatement preparedStatement;
        try{
            String qry="insert into user values (null,?,?,?,?,?,?)";
            preparedStatement= connection.prepareStatement(qry);
            preparedStatement.setString(1,user.getCin());
            preparedStatement.setString(2,user.getNom());
            preparedStatement.setString(3,user.getPrenom());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setString(6,"simpleuser");
            if(!preparedStatement.execute()){
                return true;
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return  false;
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
                user.setRole(resultSetAdmin.getString("rolle"));
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
