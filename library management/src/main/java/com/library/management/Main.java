package com.library.management;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        /*try{
            Statement statement=DbConnection.connect().createStatement();
            String str="select * from admin";
            ResultSet resultSet=statement.executeQuery(str);
            while (resultSet.next()){
                System.out.println(resultSet.getString("id")+" testt ");
            }
            statement.close();
            resultSet.close();
            DbConnection.connect().close();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        User user=new User("ayoub","ouabi");
        int i=user.checkLogin();*/
        //System.out.println(i);
        Livre livre=new Livre(6,2023,"TZU8797d1","test book","test auteur","drama","arab","disponible");
        System.out.println(livre.addLivre());
    }
}