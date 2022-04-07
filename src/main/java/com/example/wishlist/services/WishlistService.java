package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.repositories.DBHandler;

public class WishlistService {
    private DBHandler dbh = new DBHandler();

    public int createWishList(Account account, String wishlistName) {
        int accountID = account.getAccountID();
        int wishlistID = dbh.insertWishListToDB(accountID, wishlistName);
        return wishlistID;
    }

    public void createWish(String wishName, String wishDescription, double wishPrice, String wishURL, int currentWishlistID) {
        Wish wish = new Wish(wishName, wishDescription, wishURL, wishPrice);
        dbh.insertWishToDB(wish, currentWishlistID);

    }
}
