package com.library.management.Controllers;

import com.library.management.config.DbConnection;
import com.library.management.model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivrePerduService {

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
///////////////////////////////////////LOST BOOK //////////////////////////////////
    public static List<Livre> getLostBook()   {
        String qry ="SELECT livre.id,livre.isbn,livre.titre,livre.auteur,livre.annee,livre.langage,livre.category,datePer from livreperdu inner join livre on livreperdu.livre_id=livre.id";
        return getBooks(qry);
    }
    public static List<Livre> getLostBookToday()   {
        String qry ="SELECT livre.id, livre.isbn, livre.titre, livre.auteur, livre.annee, livre.langage, livre.category, livreperdu.datePer FROM livreperdu INNER JOIN livre ON livreperdu.livre_id = livre.id WHERE datePer = CURDATE();";
        return getBooks(qry);
    }
    public static List<Livre> getLostBookByTwoDate(String startDate, String endDate)   {
        String qry ="SELECT  livre.id, livre.isbn, livre.titre, livre.auteur, livre.annee, livre.langage, livre.category, livreperdu.datePer FROM livreperdu INNER JOIN livre ON livreperdu.livre_id = livre.id WHERE datePer BETWEEN ? AND ?;";
        List<Livre> livres=new ArrayList<>();
        Connection connection=DbConnection.connect();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(qry);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
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
                livre.setStatus(resultSet.getString("datePer"));
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
    public static List<Livre> getBooks(String qry) {
        List<Livre> livres=new ArrayList<>();
        Connection connection=DbConnection.connect();
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
                livre.setStatus(resultSet.getString("datePer"));
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
