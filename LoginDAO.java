package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public boolean login(int accountNumber, String pin) {

        String sql = "SELECT * FROM accounts WHERE account_number = ? AND pin = ?";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountNumber);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid account number or PIN!");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error: Unable to login.");
            return false;
        }
    }
}