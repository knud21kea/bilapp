package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.repositories.DBHandler;

import javax.servlet.http.HttpSession;

public class WishlistService {
    DBHandler dbh = new DBHandler();

   public void createWishList(Account account, String wishlistName) {
        int accountID = account.getAccountID();
        dbh.createWishList(accountID, wishlistName);
    }
}
