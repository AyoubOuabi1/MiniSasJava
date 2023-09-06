package com.library.management;

import com.library.management.model.Livre;
import com.library.management.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivreEmprunte {
    private  int id,livreId,userId;
    private Date dateEmp;

    public LivreEmprunte() {
    }

    public LivreEmprunte(int livreId, int userId) {
        this.livreId = livreId;
        this.userId = userId;
    }

    public LivreEmprunte(int id, int livreId, int userId, Date dateEmp) {
        this.id = id;
        this.livreId = livreId;
        this.userId = userId;
        this.dateEmp = dateEmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateEmp() {
        return dateEmp;
    }

    public void setDateEmp(Date dateEmp) {
        this.dateEmp = dateEmp;
    }
    public static boolean emprunteLivre(User user, Livre livre){
        Connection connection=DbConnection.connect();
        String qry="insert into empruntelivre values (null,?,?,SYSDATE())";
        try {
             PreparedStatement preparedStatement=connection.prepareStatement(qry);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2,livre.getId());
            if(!preparedStatement.execute()){
                return true;
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public  int returnLivre(Livre livre,int id) throws SQLException {
        Connection connection=DbConnection.connect();

        String qry="delete from empruntelivre where id = ? ";
        PreparedStatement preparedStatement1 = connection.prepareStatement(qry);
        preparedStatement1.setInt(1,id);
        preparedStatement1.close();
        connection.close();
        return preparedStatement1.executeUpdate();
    }

    public static List<Livre> getLivresEmpByMe(User user)   {
        List<Livre> livres=new ArrayList<>();
        Connection connection=DbConnection.connect();
        String qry ="SELECT livre.id,livre.isbn,livre.titre,livre.auteur,livre.annee,livre.langage,livre.category,livre.status from empruntelivre inner join livre on empruntelivre.livre_id=livre.id where empruntelivre.id = ?";
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
}
