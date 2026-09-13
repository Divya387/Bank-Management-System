package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionDAO {

    public void showTransactions(int accountNumber) {

        String sql = "SELECT * FROM transactions WHERE account_number = ? " + "ORDER BY transaction_date DESC";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== Transaction History =====");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Transaction ID: " + rs.getInt("transaction_id") +
                                " | Type: " + rs.getString("type") +
                                " | Amount: ₹" + rs.getDouble("amount") +
                                " | Date: " + rs.getTimestamp("transaction_date")
                );
            }

            if (!found) {
                System.out.println("No transactions found!");
            }

        } catch (Exception e) {
            System.out.println("Error: Unable to show transaction history.");
        }
    }
}