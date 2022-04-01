package com.example.wishlist.repositories.utility;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.DBHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

    static Connection con;

    public Connection connectDB() {
        try {
            String url = "jdbc:mysql://dbwish.mysql.database.azure.com:3306/dbwish";
            con = DriverManager.getConnection(url, "Andromeda@dbwish", "TestMinHest4");
            System.out.println("Det virker");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // TESTDATA
    public static void main(String[] args) {

        DBHandler dbHandler = new DBHandler();

        Account account = new Account("Torben", "kodeord", "email@torben.com");
       // dbHandler.insertAccountToDB(account);

        // WishList wishList = new WishList("FørsteWishList");
        // dbHandler.insertWishListToDB(wishList);
    }
}


