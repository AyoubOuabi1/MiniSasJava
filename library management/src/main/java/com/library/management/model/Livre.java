package com.library.management.model;

public class Livre {
    private int id,annee,quantity;
    private String isbn,titre,auteur,category,langage,status;

    public Livre() {
    }

    public Livre(int annee, int quantity, String isbn, String titre, String auteur, String category, String langage) {
        this.annee = annee;
        this.quantity = quantity;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.category = category;
        this.langage = langage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
