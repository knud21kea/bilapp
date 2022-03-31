package com.example.wishlist.repositories;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.utility.DBConnector;

import java.sql.PreparedStatement;

public class DBHandler {

    public void insertWishToDB(){

    }
    //TODO Virker ikke før vi kan hente Account_ID.
    public void insertWishListToDB(WishList wishList) {
        String wishListName = wishList.getWishListName();
        try {
            PreparedStatement preparedStatement = DBConnector.connectDB().prepareStatement("INSERT INTO wishlist (`wishlist_name`) VALUES (?);");

            preparedStatement.setString(1, wishListName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAccountToDB(Account account) {
        String accountName = account.getAccountName();
        String password = account.getPassword();
        String email = account.getEmail();
        try {
            PreparedStatement preparedStatement = DBConnector.connectDB().prepareStatement("INSERT INTO user (`account_name`, `user_email`, `user_password`) VALUES (?,?,?);");
            preparedStatement.setString(1,accountName);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
