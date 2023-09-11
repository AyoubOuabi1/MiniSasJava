package com.library.management.services;

import com.library.management.model.Livre;
import com.library.management.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivreEmprunteServiceTest {

    @Test
    void checkBookBorrowed() {
        User user=new User("ayoub","ayoub");
        User user1=new UserService(user).checkLogin();
        Livre livre = new Livre(24);
        assertEquals(false,LivreEmprunteService.checkBookBorrowed(user1,livre));
    }
    @Test
    void emprunteLivre() {
        User user=new User("mohamed","mohamed");
        User user1=new UserService(user).checkLogin();
        Livre livre = new Livre(24);
        assertNotEquals(true,LivreEmprunteService.emprunteLivre(user1,livre));
    }
}