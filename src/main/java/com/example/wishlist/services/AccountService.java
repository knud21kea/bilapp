package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.repositories.DBHandler;

import java.util.ArrayList;
import java.util.Objects;

public class AccountService
{
    private DBHandler dbh = new DBHandler();

    public boolean checkLoginCredentials(String username, String password) {
        //todo: use repositry to check username exists and hashed password matches.
        //If so, add the data to the session
        return true;
    }

    // Temporary overload
    public boolean checkLoginCredentials(String username, String password, ArrayList<String> n,ArrayList<String> p) {
        int index = n.indexOf(username);
        return  (Objects.equals(password, p.get(index)));
    }

    public void addAccountToDb(Account account) {
        dbh.insertAccountToDB(account);
    }

    public Account getAccountFromUsername(String user)
    {
        return null; // todo: method in DBHandler
    }
}
