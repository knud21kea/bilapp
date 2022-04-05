package com.example.wishlist.repositories;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.utility.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    private DBConnector dbc = new DBConnector();
    //TODO Vi connecter 3 gange
    private Connection con = dbc.connectDB();
    //TODO husk at lukke for connection til vores DB?


    //TODO HUSK AT NÅR NOGET BLIVER LAGT PÅ DATABASEN SÅ SKAL DET HENTES IGEN OG TILFØJES TIL OBJEKTERNES LISTER
    //Testet og virker
    public void insertWishToDB(Wish wish, WishList wishList) {
        int wishlistID = wishList.getWishlistID();
        String wishName = wish.getName();
        String wishDescription = wish.getDescription();
        double wishPrice = wish.getPrice();
        String wishURL = wish.getURL();
        int reservationStatus = 0;
        if (wish.isReservationStatus()) {
            reservationStatus = 1;
        }
        String wishNote = wish.getWishNote();

        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("INSERT INTO wish (`wishlist_id` , `wish_name` , `wish_description` , `wish_price`, `wish_url`, `reservation_status` , `wish_note`) VALUES (?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, wishlistID);
            preparedStatement.setString(2, wishName);
            preparedStatement.setString(3, wishDescription);
            preparedStatement.setDouble(4, wishPrice);
            preparedStatement.setString(5, wishURL);
            preparedStatement.setInt(6,reservationStatus);
            preparedStatement.setString(7,wishNote);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //Testet og virker
    public void insertAccountToDB(Account account) {
        String accountName = account.getAccountName();
        String password = account.getPassword();
        String email = account.getEmail();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("INSERT INTO account (`account_name`, `account_email`, `account_password`) VALUES (?,?,?);");
            preparedStatement.setString(1, accountName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (Exception ignored) {
        }
    }
    //Testet og virker
    public ArrayList<String> getAllAccountNames() {
        ArrayList<String> names = new ArrayList<>();
        try {
            ResultSet rs;
            Statement stmt;
            String sqlString = "SELECT account_name from account ORDER BY account_id;"; //Why not accounts??

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                names.add(rs.getString("account_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    //Is only called by WishlistService which sends an account, not an accountID.
    public void createWishList(int accountID, String name) {

        try {
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO wishlist (`account_id`, `wishlist_name`) VALUES (?,?);");
        preparedStatement.setInt(1,accountID);
        preparedStatement.setString(2,name);
        preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Testet og virker
    public Account getAccountFromAccountName(String name) {
        ResultSet rs;
        Account account = null;
        try {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT * FROM `account` WHERE account_name = '" + name + "';";
            rs = stmt.executeQuery(sqlString);
            rs.next();
            account = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
    //Testet og virker
    public WishList getWishesFromWishlist(WishList wishlist){
       int wishlistID = wishlist.getWishlistID();

        ResultSet rs;
        try {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT * FROM `wish` WHERE wishlist_id = '" + wishlistID + "';";
            rs = stmt.executeQuery(sqlString);
            while (rs.next()){
                int wishID = rs.getInt(1);
                wishlistID = rs.getInt(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                double price = rs.getDouble(5);
                String url = rs.getString(6);
                boolean reservationStatus = (rs.getInt(7) == 1);
                String wishNote = rs.getString(8);

                wishlist.getWishList().add(new Wish(wishID,wishlistID,name,description,price,url,reservationStatus,wishNote));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return wishlist;
    }
    //Testet og virker - men forstår ikke hvorfor String s skal printes
    public ArrayList<String> getAllAccountPasswords() {
        ArrayList<String> passwords = new ArrayList<>();
        try {
            ResultSet rs;
            Statement stmt;
            String sqlString = "SELECT account_password from account ORDER BY account_id;"; //Why not accounts??

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                passwords.add(rs.getString("account_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String n = "Pia";
        String p = "9876";
        String s = "SELECT * FROM account WHERE `account_name`= '" + n + "' AND `account_password`='" + p + "';";
        System.out.println(s);
        return passwords;
    }
    //Testet og virker
    public boolean validateCredentials(String n, String p) {
        int count = 0;
        try {
            ResultSet rs;
            Statement stmt;
            String sqlString = "SELECT * FROM account WHERE `account_name`= '" + n + "' AND `account_password`='" + p + "';";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (count == 1);
    }
}

