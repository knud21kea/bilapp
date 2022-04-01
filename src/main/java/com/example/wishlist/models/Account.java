package com.example.wishlist.models;

public class Account {

    private String accountName;
    private String password;
    private String email;

    public Account(String userName, String password, String email) {
        this.accountName = userName;
        this.password = password;
        this.email = email;
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
        return "Account{" +
               "userName='" + accountName + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
