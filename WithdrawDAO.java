package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class WithdrawDAO {

    public void withdraw(int accountNumber, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount! Amount must be greater than 0.");
            return;
        }

        String updateSql =
                "UPDATE accounts SET balance = balance - ? " +
                        "WHERE account_number = ? AND balance >= ?";

        String transactionSql =
                "INSERT INTO transactions (account_number, type, amount) " +
                        "VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql);
             PreparedStatement transactionPs = con.prepareStatement(transactionSql)) {

            // Start transaction
            con.setAutoCommit(false);

            // 1. Deduct money from account
            ps.setDouble(1, amount);
            ps.setInt(2, accountNumber);
            ps.setDouble(3, amount);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {

                // 2. Record transaction
                transactionPs.setInt(1, accountNumber);
                transactionPs.setString(2, "WITHDRAW");
                transactionPs.setDouble(3, amount);

                transactionPs.executeUpdate();

                // 3. Save both operations
                con.commit();

                System.out.println("Amount withdrawn successfully!");

            } else {

                // Account doesn't exist OR insufficient balance
                con.rollback();

                System.out.println(
                        "Account not found or insufficient balance!"
                );
            }

        } catch (Exception e) {

            System.out.println("Error: Unable to withdraw money.");
        }
    }
}