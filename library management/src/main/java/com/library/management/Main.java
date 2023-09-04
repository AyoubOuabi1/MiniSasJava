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
        User user=new User("ayoub","ayoub");
        User user1=user.checkLogin();
         System.out.println(user1.getNom());
         Livre livre=new Livre();
         livre.setAnnee(2023);
         System.out.println(livre.getAnnee());*/




        Livre livre=new Livre();
        for (int i =0 ;i<livre.getLivreDisponible().size();i++){
            System.out.println(livre.getLivreDisponible().get(i).getId());

        }
        //System.out.println(livre.addLivre());
    }
}