package com.library.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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
    public static boolean emprunteLivre(SimpleUser user,Livre livre){
        Connection connection=DbConnection.connect();
        String qry1 = "update livre set status = 'emprunte', quantity = quantity - 1  where id = ?";
        String qry="insert into empruntelivre values (null,?,?,SYSDATE())";
        try {
            PreparedStatement livreStatment=connection.prepareStatement(qry1);
            livreStatment.setInt(1,livre.getId());
            livreStatment.executeUpdate();
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

    public  void returnLivre(Livre livre,int id){
        Connection connection=DbConnection.connect();
        String qry1 = "update livre set status = 'disponible', quantity = quantity + 1  where id = ?";
        String qry="delete from empruntelivre where id = ? ";
    }
}
