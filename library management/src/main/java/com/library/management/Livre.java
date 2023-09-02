package com.library.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Livre {
    int id,annee;
    String isbn,titre,auteur,category,langage,status;

    public Livre() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLangage() {
        return langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Livre(int id, int annee, String isbn, String titre, String auteur, String category, String langage, String status) {
        this.id = id;
        this.annee = annee;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.category = category;
        this.langage = langage;
        this.status = status;
    }
    public int addLivre(){
        try{
            Connection con=DbConnection.connect();
            PreparedStatement statement=con.prepareStatement("insert into livre  values (null,?,?,?,?,?,?,?)");
            statement.setString(1,getIsbn());
            statement.setString(2,getTitre());
            statement.setString(3,getAuteur());
            statement.setInt(4,getAnnee());
            statement.setString(5,getLangage());
            statement.setString(6,getCategory());
            statement.setString(7,getStatus());
            if( statement.execute()){
                return 1;
            }else
                return 3;
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            return 2;
        }

    }
}
