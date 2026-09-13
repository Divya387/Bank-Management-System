package bank;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.println("5. Create Account");
            System.out.println("6. Login");
            System.out.println("7. Transfer Money");
            System.out.println("8. Transaction History");


            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter account number: ");
                    int depositAccountNumber = sc.nextInt();

                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();

                    DepositDAO depositDAO = new DepositDAO();

                    depositDAO.deposit(depositAccountNumber, depositAmount);

                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    int withdrawAccountNumber = sc.nextInt();

                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();

                    WithdrawDAO withdrawDAO = new WithdrawDAO();

                    withdrawDAO.withdraw(withdrawAccountNumber, withdrawAmount);

                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    int balanceAccountNumber = sc.nextInt();

                    BalanceDAO balanceDAO = new BalanceDAO();

                    double currentBalance = balanceDAO.getBalance(balanceAccountNumber);

                    if (currentBalance != -1) {
                        System.out.println("Current Balance: ₹" + currentBalance);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using our bank!");
                    sc.close();
                    return;

                case 5:
                    System.out.print("Enter account number: ");
                    int accountNumber = sc.nextInt();

                    System.out.print("Enter name: ");
                    String name = sc.next();

                    System.out.print("Enter PIN: ");
                    String pin = sc.next();

                    System.out.print("Enter initial balance: ");
                    double balance = sc.nextDouble();

                    AccountDAO dao = new AccountDAO();
                    dao.createAccount(accountNumber, name, pin, balance);
                    break;

                case 6:
                    System.out.print("Enter account number: ");
                    int loginAccountNumber = sc.nextInt();

                    System.out.print("Enter PIN: ");
                    String loginPin = sc.next();

                    LoginDAO loginDAO = new LoginDAO();

                    loginDAO.login(loginAccountNumber, loginPin);

                    break;

                case 7:
                    System.out.print("Enter sender account number: ");
                    int fromAccount = sc.nextInt();

                    System.out.print("Enter receiver account number: ");
                    int toAccount = sc.nextInt();

                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = sc.nextDouble();

                    TransferDAO transferDAO = new TransferDAO();

                    transferDAO.transfer(fromAccount, toAccount, transferAmount);

                    break;

                case 8:
                    System.out.print("Enter account number: ");
                    int transactionAccountNumber = sc.nextInt();

                    TransactionDAO transactionDAO = new TransactionDAO();

                    transactionDAO.showTransactions(transactionAccountNumber);

                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}