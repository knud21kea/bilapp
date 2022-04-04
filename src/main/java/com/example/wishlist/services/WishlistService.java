package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.repositories.DBHandler;

import javax.servlet.http.HttpSession;

public class WishlistService {
    DBHandler dbh = new DBHandler();

    public void createWishList(HttpSession session) {
        //Get account from session
        //Get wishlist name from user

        Account account = (Account) session.getAttribute("sessionAccount");
        account.getAccountName();




        dbh.createWishList();


    }
}
