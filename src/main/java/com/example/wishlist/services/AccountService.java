package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.repositories.DBHandler;

import java.util.ArrayList;
import java.util.Objects;

public class AccountService
{
    private DBHandler dbh = new DBHandler();

    public boolean checkLoginCredentials(String username, String password) {
        //Can now check against the db
        //I think this can be done with a single sql query
        ArrayList<String> names = dbh.getAllAccountNames();
        ArrayList<String> passwords = dbh.getAllAccountPasswords();
        int index = names.indexOf(username);
        boolean match;
        if (index >= 0) {
            match = (Objects.equals(password, passwords.get(index)));
        }
        else {
            match = false;
        }
        return match;
    }

    public void addAccountToDb(Account account) {
        dbh.insertAccountToDB(account);
    }

    public Account getAccountFromUsername(String user)
    {
        return null; // todo: method in DBHandler
    }

    public ArrayList<String> getAllUserNames()
    {
        return dbh.getAllAccountNames();
    }
}
