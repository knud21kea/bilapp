package com.example.wishlist.models;

import java.util.ArrayList;
import java.util.List;

public class WishList {

    private ArrayList<Wish> wishList;
    private String wishListName;

    public ArrayList<Wish> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Wish> wishList) {
        this.wishList = wishList;
    }

    public String getWishListName() {
        return wishListName;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public WishList(String wishListName) {
        this.wishListName =  wishListName;
    }
}
