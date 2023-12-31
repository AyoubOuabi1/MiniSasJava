package com.library.management.Controllers;

import com.library.management.config.DbConnection;
import com.library.management.model.Livre;
import com.library.management.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreEmprunteService {
    public static boolean checkBookBorrowed(User user, Livre livre){
        Connection connection= DbConnection.connect();
        String qry="select * from empruntelivre where   empruntelivre.user_id = ? and empruntelivre.livre_id=? ";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(qry);
            preparedStatement.setInt(1,user.getId());

            preparedStatement.setInt(2,livre.getId());
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return false;
            }

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }
    public static boolean emprunteLivre(User user, Livre livre){
        Connection connection= DbConnection.connect();
        String qry="insert into empruntelivre values (null,?,?,SYSDATE())";
        try {
            if (checkBookBorrowed(user, livre)){
                PreparedStatement preparedStatement=connection.prepareStatement(qry);
                preparedStatement.setInt(1,user.getId());
                preparedStatement.setInt(2,livre.getId());
                 if(preparedStatement.executeUpdate()>0){
                    return true;
                }
            }

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static int returnLivre(int id,User user)  {
        Connection connection=DbConnection.connect();
        try{
            String qry="delete from empruntelivre where livre_id = ? and user_id = ? ";
            PreparedStatement preparedStatement1 = connection.prepareStatement(qry);
            preparedStatement1.setInt(1,id);
            preparedStatement1.setInt(2,user.getId());
            int ret=preparedStatement1.executeUpdate();
            preparedStatement1.close();
            connection.close();
            return ret;
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return  -1;

    }

    public static List<Livre> getLivresEmpByMe(User user)
    {
        List<Livre> livres=new ArrayList<>();
        Connection connection=DbConnection.connect();
        String qry ="SELECT livre.id,livre.isbn,livre.titre,livre.auteur,livre.annee,livre.langage,livre.category,livre.status from empruntelivre inner join livre on empruntelivre.livre_id=livre.id where empruntelivre.user_id = ?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(qry);
            preparedStatement.setInt(1,user.getId());
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
    public static List<Livre> getAllBookEmp()   {
        List<Livre> livres=new ArrayList<>();
        Connection connection=DbConnection.connect();
        String qry ="SELECT livre.id,livre.isbn,livre.titre,livre.auteur,livre.annee,livre.langage,livre.category,CONCAT(user.nom ,' ',user.prenom) As borrowedBy from empruntelivre inner join livre on empruntelivre.livre_id=livre.id  inner join user on empruntelivre.user_id=user.id";
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
                livre.setStatus(resultSet.getString("borrowedBy"));
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
    public static List<Livre> getBorrowedBookToday()   {
        String qry ="SELECT livre.id, livre.isbn, livre.titre, livre.auteur, livre.annee, livre.langage, livre.category, empruntelivre.dateEmp FROM empruntelivre INNER JOIN livre ON empruntelivre.livre_id = livre.id WHERE dateEmp = CURDATE();";
        return getBooks(qry);
    }
    public static List<Livre> getBorrowedBookByTwoDate(String startDate, String endDate)   {
        String qry ="SELECT  livre.id, livre.isbn, livre.titre, livre.auteur, livre.annee, livre.langage, livre.category, empruntelivre.dateEmp FROM empruntelivre INNER JOIN livre ON empruntelivre.livre_id = livre.id WHERE dateEmp BETWEEN ? AND ?;";
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
                livre.setStatus(resultSet.getString("dateEmp"));
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
                livre.setStatus(resultSet.getString("dateEmp"));
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
