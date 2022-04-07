package com.example.wishlist.models;

public class Account {

    private int accountID;
    private String accountName;
    private String password;
    private String email;

    public Account(String userName, String email, String password) {
        this.accountName = userName;
        this.password = password;
        this.email = email;
        wishListArrayList = new ArrayList<>();
    }

    public Account(int accountID, String userName, String email, String password) {
        this.accountID = accountID;
        this.accountName = userName;
        this.password = password;
        this.email = email;
        wishListArrayList = new ArrayList<>();
    }

    public int getAccountID() {
        return accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return accountID + " " + accountName + " " + email;
    }
}
