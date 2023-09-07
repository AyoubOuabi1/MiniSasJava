package com.library.management.services;

import com.library.management.config.DbConnection;
import com.library.management.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivrePerdu {

    public static boolean markBookAsLost( Livre livre){
        Connection connection= DbConnection.connect();
        String qry="insert into livreperdu values (null,?,SYSDATE())";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(qry);
             preparedStatement.setInt(1,livre.getId());
            if(!preparedStatement.execute()){
                return true;
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static int returnBookIntoLibrarry(int id)  {
        Connection connection=DbConnection.connect();
        try{
            String qry="delete from livreperdu where id = ? ";
            PreparedStatement preparedStatement1 = connection.prepareStatement(qry);
            preparedStatement1.setInt(1,id);
            int ret=preparedStatement1.executeUpdate();
            preparedStatement1.close();
            connection.close();
            return ret;
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return  -1;

    }

    public static List<Livre> getLostBook()   {
        List<Livre> livres=new ArrayList<>();
        Connection connection=DbConnection.connect();
        String qry ="SELECT livre.id,livre.isbn,livre.titre,livre.auteur,livre.annee,livre.langage,livre.category,livre.status from livreperdu inner join livre on livreperdu.livre_id=livre.id";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(qry);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Livre livre= new Livre();
                livre.setId(resultSet.getInt("id"));
                livre.setIsbn(resultSet.getString("isbn"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livre.setAnnee(resultSet.getInt("annee"));
                livre.setCategory(resultSet.getString("category"));
                livre.setLangage(resultSet.getString("langage"));
                livres.add(livre);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return  livres;
    }
}
