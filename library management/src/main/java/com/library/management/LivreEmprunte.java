package com.library.management;

import java.util.Date;

public class LivreEmprunte {
    int id,livreId,userId;
    Date dateEmp;

    public LivreEmprunte() {
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
}
