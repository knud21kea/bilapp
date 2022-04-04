package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.repositories.DBHandler;

import javax.servlet.http.HttpSession;

public class WishlistService {
    DBHandler dbh = new DBHandler();

    public void createWishList(Account account, String wishlistName) {

        //Get wishlist name from user

        String username = account.getAccountName();
        int accountID = dbh.getAccountIDFromAccountName(username);



        dbh.createWishList(accountID, wishlistName);


    }
}
