package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransferDAO {

    public void transfer(int fromAccount, int toAccount, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount! Amount must be greater than 0.");
            return;
        }

        if (fromAccount == toAccount) {
            System.out.println("Sender and receiver accounts cannot be the same!");
            return;
        }

        String senderSql =
                "UPDATE accounts SET balance = balance - ? " +
                        "WHERE account_number = ? AND balance >= ?";

        String receiverSql =
                "UPDATE accounts SET balance = balance + ? " +
                        "WHERE account_number = ?";

        String transactionSql =
                "INSERT INTO transactions (account_number, type, amount) " +
                        "VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement senderPs = con.prepareStatement(senderSql);
             PreparedStatement receiverPs = con.prepareStatement(receiverSql);
             PreparedStatement senderTransactionPs = con.prepareStatement(transactionSql);
             PreparedStatement receiverTransactionPs = con.prepareStatement(transactionSql)) {

            con.setAutoCommit(false);

            // 1. Deduct money from sender
            senderPs.setDouble(1, amount);
            senderPs.setInt(2, fromAccount);
            senderPs.setDouble(3, amount);

            int senderRowsAffected = senderPs.executeUpdate();

            if (senderRowsAffected == 0) {
                con.rollback();

                System.out.println(
                        "Transfer failed: Account not found or insufficient balance."
                );
                return;
            }

            // 2. Add money to receiver
            receiverPs.setDouble(1, amount);
            receiverPs.setInt(2, toAccount);

            int receiverRowsAffected = receiverPs.executeUpdate();

            if (receiverRowsAffected == 0) {
                con.rollback();

                System.out.println("Receiver account not found!");
                return;
            }

            // 3. Record sender transaction
            senderTransactionPs.setInt(1, fromAccount);
            senderTransactionPs.setString(2, "TRANSFER_SENT");
            senderTransactionPs.setDouble(3, amount);

            senderTransactionPs.executeUpdate();

            // 4. Record receiver transaction
            receiverTransactionPs.setInt(1, toAccount);
            receiverTransactionPs.setString(2, "TRANSFER_RECEIVED");
            receiverTransactionPs.setDouble(3, amount);

            receiverTransactionPs.executeUpdate();

            // 5. Save everything
            con.commit();

            System.out.println("Money transferred successfully!");

        } catch (Exception e) {

            System.out.println("Error: Unable to transfer money.");
        }
    }
}