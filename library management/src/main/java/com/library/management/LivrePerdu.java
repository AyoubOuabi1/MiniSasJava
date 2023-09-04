package com.library.management;

import java.util.Date;

public class LivrePerdu {
    int id,livreId;
    Date datePrd;

    public LivrePerdu() {
    }

    public LivrePerdu(int id, int livreId, Date datePrd) {
        this.id = id;
        this.livreId = livreId;
        this.datePrd = datePrd;
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

    public Date getDatePrd() {
        return datePrd;
    }

    public void setDatePrd(Date datePrd) {
        this.datePrd = datePrd;
    }
}
