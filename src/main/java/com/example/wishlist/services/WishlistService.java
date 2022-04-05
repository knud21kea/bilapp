package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.DBHandler;

import javax.servlet.http.HttpSession;

public class WishlistService {
    DBHandler dbh = new DBHandler();

   public void createWishList(Account account, String wishlistName) {
        int accountID = account.getAccountID();
        dbh.createWishList(accountID, wishlistName);
    }

    public void createWish(String wishName, String wishDescription, double wishPrice, String wishURL, WishList wishList) {
       Wish wish = new Wish(wishName,wishDescription,wishURL,wishPrice);
       dbh.insertWishToDB(wish,wishList);

    }
}
