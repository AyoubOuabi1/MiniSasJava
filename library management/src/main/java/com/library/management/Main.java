package com.library.management;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
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

    }
}