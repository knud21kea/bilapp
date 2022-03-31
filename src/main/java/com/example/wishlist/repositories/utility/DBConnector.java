package com.example.wishlist.repositories.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

    static Connection connection;

    public static Connection connectDB() {
        try {
            String url = "jdbc:mysql://dbwish.mysql.database.azure.com:3306/dbwish";
            connection = DriverManager.getConnection(url, "Andromeda@dbwish", "TestMinHest4");
            System.out.println("Det virker");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        connectDB();
    }
}


