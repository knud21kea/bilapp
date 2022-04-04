package com.example.wishlist.repositories;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.utility.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    private DBConnector dbc = new DBConnector();
    //TODO Spørg Nicklas om vi skal connecte hver gang vi kalder.
    //TODO Vi connecter 2 gange
    private Connection con = dbc.connectDB();


    //TODO Virker ikke før vi kan hente wishlist_ID
    public void insertWishToDB(Wish wish) {
        String wishName = wish.getName();
        String wishDescription = wish.getDescription();
        String wishURL = wish.getURL();
        double wishPrice = wish.getPrice();
        try {                           // Måske skal der være reservationStatus også?
            PreparedStatement preparedStatement = con.prepareStatement
                    ("INSERT INTO wish (`wish_name`, `wish_description`, `wish_price`, `wish_url`) VALUES (?,?,?,?);");
            preparedStatement.setString(1, wishName);
            preparedStatement.setString(2, wishDescription);
            preparedStatement.setDouble(3, wishPrice);
            preparedStatement.setString(4, wishURL);
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
            PreparedStatement preparedStatement = con.prepareStatement
                    ("INSERT INTO account (`account_name`, `account_email`, `account_password`) VALUES (?,?,?);");
            preparedStatement.setString(1, accountName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (Exception ignored) {
        }
    }

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

    public int getAccountIDFromAccountName(String name) {
        ResultSet rs;
        int accountId = 0;
        try {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT account_id FROM `account` WHERE account_name = '" + name + "';";
            rs = stmt.executeQuery(sqlString);
            rs.next();
            accountId = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountId;

    }

    public Account getAccountFromAccountID(int accountID){
        ResultSet rs;
        Account account = null;
        try{
            Statement stmt = con.createStatement();
            String sqlString = "SELECT * FROM `account` WHERE account_id = '" + accountID + "';";
            rs = stmt.executeQuery(sqlString);
            rs.next();
            account = new Account(rs.getString(1), rs.getString(2), rs.getString(3));


        } catch (SQLException e){
            e.printStackTrace();
        } return account;
    }

}

