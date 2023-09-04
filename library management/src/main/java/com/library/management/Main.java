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

        /*SimpleUser user1= new SimpleUser("ayoub","ouabi");
        User currentUser= user1.checkLogin();

        Livre livre=new Livre().getLivreDisponible().get(4);
        //System.out.println(livre.getId());
        user1.setId(currentUser.getId());
        System.out.println(LivreEmprunte.emprunteLivre(user1,livre));*/

        afficher();
    }
    static void afficher(){
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("//                                                                                               //");
        System.out.println("//                                             Welcome                                           //");
        System.out.println("//                                                                                               //");
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////");
    }
}