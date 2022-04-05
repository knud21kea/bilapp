package com.example.wishlist.models;

import java.util.ArrayList;

public class Account {

    private int accountID;
    private String accountName;
    private String password;
    private String email;
    private ArrayList<WishList> wishListArrayList;

    public Account(String userName, String email, String password){
        this.accountName = userName;
        this.password = password;
        this.email = email;
    }

    public Account(int accountID,String userName, String email, String password) {
        this.accountID = accountID;
        this.accountName = userName;
        this.password = password;
        this.email = email;
    }

    public ArrayList<WishList> getWishListArrayList() {
        return wishListArrayList;
    }

    public void setWishListArrayList(ArrayList<WishList> wishListArrayList) {
        this.wishListArrayList = wishListArrayList;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return accountID + " " + accountName + " " +email;
    }
}
