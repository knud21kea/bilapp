package com.example.wishlist.services;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest
{
    AccountService as = new AccountService();
    ArrayList<String> n = new ArrayList<>(Arrays.asList("", "a", "ab", "abc"));
    ArrayList<String> p = new ArrayList<>(Arrays.asList("0", "a1", "ab2", "abc3"));

    @Test
    void checkLoginCredentials()
    {
        //Arrange
        String n1 = "a";
        String p1 = "a1";
        String n2 = "ab";
        String p2 = "ab2";
        String n3 = "abc";
        String p3 = "admin";

        //Act
        boolean result1 = as.checkLoginCredentials(n1, p1, n, p);
        boolean result2 = as.checkLoginCredentials(n2, p2, n, p);
        boolean result3 = as.checkLoginCredentials(n3, p3, n, p);

        //Assert
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
    }
}