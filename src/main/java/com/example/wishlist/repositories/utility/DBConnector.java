package com.example.wishlist.repositories.utility;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
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
        } catch (Exception ignored) {
        }
        return con;
    }

    // TESTDATA
    public static void main(String[] args) {

        DBHandler dbHandler = new DBHandler();

        Account account = new Account("søren", "password", "email@søren.com");
        WishList wishList = new WishList(12,5,"wishlistname");
        Wish wish = new Wish(1,2,"name","description",3.5,"url",true,"wishnote test");
        //dbHandler.insertWishToDB(wish,dbHandler.getWishlistsFromAccountID(3).get(3));

        System.out.println(dbHandler.getLastWishlistID(3));

        //ArrayList<Wish> arrayList = new ArrayList<>();
        //arrayList.add(wish);
        //wishList.setWishList(arrayList);
        //dbHandler.insertWishToDB(wish,wishList);
        //dbHandler.insertAccountToDB(account);
        //System.out.println(dbHandler.getAllAccountNames());
        //System.out.println(dbHandler.getAccountFromAccountName("søren"));
        //System.out.println(dbHandler.getWishesFromWishlist(wishList));
        //System.out.println(dbHandler.getAllAccountPasswords());
        //System.out.println(dbHandler.validateCredentials("torben", "kodeord"));
    }
}


