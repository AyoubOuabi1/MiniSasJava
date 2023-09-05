package com.library.management.controllers;

import com.library.management.DbConnection;
import com.library.management.model.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreController{
    Livre livre;
    public LivreController(Livre livre) {
        this.livre=livre;
    }

    public LivreController() {
    }

    public boolean addLivre(){
        try{
            Connection con= DbConnection.connect();
            PreparedStatement statement=con.prepareStatement("insert into livre  values (null,?,?,?,?,?,?,?)");
            statement.setString(1,livre.getIsbn());
            statement.setString(2,livre.getTitre());
            statement.setString(3,livre.getAuteur());
            statement.setInt(4,livre.getAnnee());
            statement.setString(5,livre.getLangage());
            statement.setString(6,livre.getCategory());
            statement.setString(7,livre.getStatus());
            if(!statement.execute()){
                return true;
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());

        }
        return  false;
    }
    public List<Livre> getLivreDisponible(){
        Connection connection=DbConnection.connect();
        String qry="select * from livre where status = 'disponible'";
        List<Livre> livres=new ArrayList<>();

        try {
            Statement preparedStatement=  connection.createStatement();
            ResultSet resultSet=preparedStatement.executeQuery(qry);
            while (resultSet.next()){
                Livre livre=new Livre();
                livre.setId(Integer.parseInt(resultSet.getString("id")));
                livre.setIsbn(resultSet.getString("isbn"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livre.setAnnee(Integer.parseInt(resultSet.getString("annee")));
                livre.setCategory(resultSet.getString("category"));
                livre.setLangage(resultSet.getString("langage"));
                livre.setStatus(resultSet.getString("status"));
                livres.add(livre);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return  livres;
    }
    public static List<Livre> getLivreBySearch(String searchBy) {
        Connection connection = DbConnection.connect();
        String query = "SELECT DISTINCT * FROM livre WHERE (titre LIKE ? OR auteur LIKE ?)";
        List<Livre> livres = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchBy + "%"); // Use % for partial matching
            preparedStatement.setString(2, "%" + searchBy + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Livre livre = new Livre();
                livre.setId(resultSet.getInt("id"));
                livre.setIsbn(resultSet.getString("isbn"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livre.setAnnee(resultSet.getInt("annee"));
                livre.setCategory(resultSet.getString("category"));
                livre.setLangage(resultSet.getString("langage"));
                livre.setStatus(resultSet.getString("status"));
                livres.add(livre);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println("Error searching Books: " + exception.getMessage());
        }

        return livres;
    }
    public String updateLivre() {
        Connection connection = DbConnection.connect();
        try {
            String updateQuery = "UPDATE livre SET isbn=?, titre=?, auteur=?, annee=?, category=?, langage=?, status=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, livre.getIsbn());
            preparedStatement.setString(2,livre.getTitre());
            preparedStatement.setString(3,livre.getAuteur());
            preparedStatement.setInt(4,livre.getAnnee());
            preparedStatement.setString(5,livre.getLangage());
            preparedStatement.setString(6,livre.getCategory());
            preparedStatement.setString(7,livre.getStatus());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                preparedStatement.close();
                connection.close();
                return  "book updated successfully.";
            } else {
                connection.close();
                preparedStatement.close();
                return "book update failed. No records were updated.";
            }


        } catch (SQLException exception) {
            return  "Error updating Livre: " + exception.getMessage();
        }
    }
    public String deleteLivre() {
        Connection connection = DbConnection.connect();
        try {
            String deleteQuery = "DELETE FROM livre WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, livre.getId());

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                preparedStatement.close();
                connection.close();
                return "Livre deleted successfully.";
            } else {
                preparedStatement.close();
                connection.close();
                return  "No Livre records were deleted.";
            }

        } catch (SQLException exception) {
            return "Error deleting Livre: " + exception.getMessage();
        }
    }
    public static int countLivresDispo(Connection connection) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM livre WHERE status = 'disponible'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1); // Get the count from the first column
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println("Error counting available Livres: " + exception.getMessage());
        }

        return count;
    }
}
