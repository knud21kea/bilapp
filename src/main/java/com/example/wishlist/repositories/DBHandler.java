package com.example.wishlist.repositories;

import com.example.wishlist.models.Account;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.WishList;
import com.example.wishlist.repositories.utility.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler
{

    private DBConnector dbc = new DBConnector();
    private Connection con;

    public void insertWishToDB(Wish wish, int currentWishlistID)
    {
        con = dbc.connectDB();
        int wishlistID = currentWishlistID;
        String wishName = wish.getName();
        String wishDescription = wish.getDescription();
        double wishPrice = wish.getPrice();
        String wishURL = wish.getURL();
        int reservationStatus = 0;
        if (wish.isReservationStatus())
        {
            reservationStatus = 1;
        }
        String wishNote = wish.getWishNote();

        try
        {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("INSERT INTO `dbwish`.`wish` (`wishlist_id` , `wish_name` , `wish_description` , `wish_price`, `wish_url`, `reservation_status`,`wish_note`) VALUES (?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, wishlistID);
            preparedStatement.setString(2, wishName);
            preparedStatement.setString(3, wishDescription);
            preparedStatement.setDouble(4, wishPrice);
            preparedStatement.setString(5, wishURL);
            preparedStatement.setInt(6, reservationStatus);
            preparedStatement.setString(7, wishNote);
            preparedStatement.executeUpdate();
            con.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void insertAccountToDB(Account account)
    {
        con = dbc.connectDB();
        String accountName = account.getAccountName();
        String email = account.getEmail();
        String password = account.getPassword();
        try
        {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("INSERT INTO account (`account_name`, `account_email`, `account_password`) VALUES (?,?,?);");
            preparedStatement.setString(1, accountName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            con.close();
        } catch (Exception ignored)
        {
        }
    }

    public void reserveWish(int wishlistID, int wishID)
    {
        con = dbc.connectDB();
        try
        {
            String sqlString = "UPDATE wish SET reservation_status = 1 WHERE wishlist_id = '" + wishlistID + "'  AND wish_id = '" + wishID + "';";
            PreparedStatement preparedStatement = con.prepareStatement(sqlString);
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllAccountNames()
    {
        con = dbc.connectDB();
        ArrayList<String> names = new ArrayList<>();
        try
        {
            ResultSet rs;
            Statement stmt;
            String sqlString = "SELECT account_name from account ORDER BY account_id;"; //Why not accounts??

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlString);
            while (rs.next())
            {
                names.add(rs.getString("account_name"));
            }
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return names;
    }

    public boolean validateCredentials(String n, String p)
    {
        con = dbc.connectDB();
        int count = 0;
        try
        {
            ResultSet rs;
            Statement stmt;
            String sqlString = "SELECT * FROM account WHERE `account_name`= '" + n + "' AND `account_password`='" + p + "';";
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sqlString);
            while (rs.next())
            {
                count++;
            }
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (count == 1);
    }

    public int insertWishListToDB(int accountID, String name)
    {
        con = dbc.connectDB();
        try
        {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO wishlist (`account_id`, `wishlist_name`) VALUES (?,?);");
            preparedStatement.setInt(1, accountID);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return getLastWishlistID(accountID);
    }

    //MUST ONLY BE USED RIGHT AFTER A WISHLIST HAS BEEN ADDED
    public int getLastWishlistID(int accountID)
    {
        int wishlistID = 0;
        ResultSet rs;
        try
        {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT MAX(wishlist_id) FROM wishlist WHERE account_id = '" + accountID + "';";
            rs = stmt.executeQuery(sqlString);
            rs.next();
            wishlistID = rs.getInt(1);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return wishlistID;
    }

    public Account getAccountFromAccountName(String name)
    {
        con = dbc.connectDB();
        ResultSet rs;
        Account account = null;
        try
        {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT * FROM `account` WHERE account_name = '" + name + "' ORDER BY `account_id`;";
            rs = stmt.executeQuery(sqlString);
            rs.next();
            account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            System.out.println(account);
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return account;
    }

    // Den får et wishlist objekt,
    // tilføjer alle wishes fra DB til det objekts arrayliste og returnerer objektet.
    public WishList getWishesFromWishlist(WishList wishlist)
    {
        con = dbc.connectDB();
        int wishlistID = wishlist.getWishlistID();

        ResultSet rs;
        try
        {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT * FROM `wish` WHERE wishlist_id = '" + wishlistID + "';";
            rs = stmt.executeQuery(sqlString);
            while (rs.next())
            {
                int wishID = rs.getInt(1);
                wishlistID = rs.getInt(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                double price = rs.getDouble(5);
                String url = rs.getString(6);
                boolean reservationStatus = (rs.getInt(7) == 1);
                String wishNote = rs.getString(8);

                wishlist.getWishList().add(new Wish(wishID, wishlistID, name, description, price, url, reservationStatus, wishNote));
            }
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return wishlist;
    }

    public WishList getWishlistFromID(int id)
    {
        con = dbc.connectDB();
        ResultSet rs;
        WishList wl = null;
        try
        {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT * FROM `wishlist` WHERE wishlist_id = '" + id + "';";
            rs = stmt.executeQuery(sqlString);
            while (rs.next())
            {
                int accountId = rs.getInt(2);
                String name = rs.getString(3);
                wl = new WishList(id, accountId, name);
            }
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return wl;
    }

    public ArrayList<Wish> getUnreservedWishesFromWishlistID(int id)
    {
        con = dbc.connectDB();
        ResultSet rs;
        ArrayList<Wish> wl = new ArrayList<>();
        try
        {
            Statement stmt = con.createStatement();
            String sqlString = "SELECT * FROM wish WHERE reservation_status = 0 AND wishlist_id = '" + id + "';";
            rs = stmt.executeQuery(sqlString);
            while (rs.next())
            {
                int wishID = rs.getInt(1);
                String name = rs.getString(3);
                String description = rs.getString(4);
                double price = rs.getDouble(5);
                String url = rs.getString(6);
                boolean reservationStatus = (rs.getInt(7) == 1);
                String wishNote = rs.getString(8);

                wl.add(new Wish(wishID, id, name, description, price, url, reservationStatus, wishNote));
            }
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return wl;
    }public ArrayList<WishList> getWishlistsFromAccountID(int id)
{
    con = dbc.connectDB();
    ResultSet rs;
    ArrayList<WishList> wl = new ArrayList<>();
    try
    {
        Statement stmt = con.createStatement();
        String sqlString = "SELECT * FROM wishlist WHERE account_id = '" + id + "';";
        rs = stmt.executeQuery(sqlString);
        while (rs.next())
        {
            int w_id = rs.getInt(1);
            int a_id = rs.getInt(2);
            String wl_n = rs.getString(3);
            wl.add(new WishList(w_id, a_id, wl_n));
        }
        con.close();
    } catch (SQLException e)
    {
        e.printStackTrace();
    }
    return wl;
}
}

