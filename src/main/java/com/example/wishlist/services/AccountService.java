package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.DBHandler;

import java.util.ArrayList;

public class AccountService {
    private DBHandler dbh = new DBHandler();

    public boolean checkLoginCredentials(String username, String password) {
        return dbh.validateCredentials(username, password);
    }

    public void addAccountToDb(Account account) {
        dbh.insertAccountToDB(account);
    }

    public Account getAccountFromUsername(String user) {
        return dbh.getAccountFromAccountName(user);
    }

    public ArrayList<String> getAllUserNames() {
        return dbh.getAllAccountNames();
    }

    public WishList getWishlistFromId(int id) {
        return dbh.getWishlistFromID(id);
    }

    public WishList getWishesFromWishlist(WishList wl) {
        return dbh.getWishesFromWishlist(wl);
    }

    public void reserveWish(int list, int wish) {
        dbh.reserveWish(list, wish);
    }

    public ArrayList<Wish> getUnreservedWishesFromWishlistID(int id) {
        ArrayList<Wish> al;
        al = dbh.getUnreservedWishesFromWishlistID(id);
        return al;
    }
}
