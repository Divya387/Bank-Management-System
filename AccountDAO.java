package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {

    public void createAccount(int accountNumber, String name, String pin, double balance) {

        if (accountNumber <= 0) {
            System.out.println("Invalid account number!");
            return;
        }

        if (balance < 0) {
            System.out.println("Initial balance cannot be negative!");
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        if (pin == null || !pin.matches("\\d{4}")) {
            System.out.println("PIN must be exactly 4 digits!");
            return;
        }

        String sql = "INSERT INTO accounts (account_number, name, pin, balance) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountNumber);
            ps.setString(2, name);
            ps.setString(3, pin);
            ps.setDouble(4, balance);

            ps.executeUpdate();

            System.out.println("Account created successfully!");

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                System.out.println("Account number already exists!");
            } else {
                System.out.println("Error: Unable to create account.");
            }
        }
        }
    }
