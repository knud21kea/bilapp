package com.example.wishlist.services;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.DBHandler;

import javax.servlet.http.HttpSession;

public class WishlistService {
    DBHandler dbh = new DBHandler();

   public int createWishList(Account account, String wishlistName) {
        int accountID = account.getAccountID();
        int wishlistID = dbh.createWishList(accountID, wishlistName);
        return wishlistID;
    }

    public void createWish(String wishName, String wishDescription, double wishPrice, String wishURL, int currentWishlistID) {
       Wish wish = new Wish(wishName,wishDescription,wishURL,wishPrice);
       dbh.insertWishToDB(wish,currentWishlistID);

    }

    public void addArrayListOfWishlistsToAccount(Account account){
       int accountID = account.getAccountID();
        for (WishList wl : dbh.getWishlistsFromAccountID(accountID)) {
            account.getWishListArrayList().add(wl);
        }
    }

    public void addWishesToWishlist(){

    }
}
