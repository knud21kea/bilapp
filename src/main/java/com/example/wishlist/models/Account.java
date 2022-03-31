package com.example.wishlist.models;

public class Account {

    private String userName;
    private String password;
    private String email;
    private boolean hasAccount;

    public Account(String userName, String password, String email, boolean hasAccount) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.hasAccount = hasAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isHasAccount() {
        return hasAccount;
    }

    public void setHasAccount(boolean hasAccount) {
        this.hasAccount = hasAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
               "userName='" + userName + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", hasAccount=" + hasAccount +
               '}';
    }
}
