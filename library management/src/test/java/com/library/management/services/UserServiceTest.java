package com.library.management.services;

import com.library.management.model.User;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void addUser() {
        User user=new User("fkjghdpiof","sdfsfsd","dfsdf","dsfsdf","df","ZYUE883");
        UserService userService=new UserService(user);
        assertEquals(userService.addUser(),true);
    }

    @Test
    void checkLogin() {
        User user=new User("dsfsd","dfsdf");
        User user1=new User();
        UserService userService=new UserService(user);
        assertNotEquals(user1,userService.checkLogin());

    }
}