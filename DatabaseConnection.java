package bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/bank_management";
        String username = "root";
        String password = "Divya@123";

        try {
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Database connected successfully!");

            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}