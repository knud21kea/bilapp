package com.example.wishlist.models;

import java.util.ArrayList;

public class WishList {

    private int wishlistID;
    private int accountID;
    private ArrayList<Wish> wishList;
    private String wishListName;

    public WishList(int wishlistID, int accountID, String wishListName) {
        this.wishlistID = wishlistID;
        this.accountID = accountID;
        this.wishListName = wishListName;
        this.wishList = new ArrayList<>();
    }

    public ArrayList<Wish> getWishList() {
        return wishList;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishList(ArrayList<Wish> wishList) {
        this.wishList = wishList;
    }

    public String getWishListName() {
        return wishListName;
    }
}
