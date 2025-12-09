package dao;

import db.DBConnection;
import java.sql.*;

public class BankDAO {

    // Create Account
    public void createAccount(String name, double initialAmount) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO accounts(name, balance) VALUES(?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setDouble(2, initialAmount);
        ps.executeUpdate();
        System.out.println("Account created successfully!");
    }

    // Deposit
    public void deposit(int accNo, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, amount);
        ps.setInt(2, accNo);
        ps.executeUpdate();
        addTransaction(accNo, "DEPOSIT", amount);
        System.out.println("Amount deposited successfully!");
    }

    // Withdraw
    public void withdraw(int accNo, double amount) throws Exception {
        Connection con = DBConnection.getConnection();

        String check = "SELECT balance FROM accounts WHERE acc_no = ?";
        PreparedStatement cs = con.prepareStatement(check);
        cs.setInt(1, accNo);

        ResultSet rs = cs.executeQuery();

        if (rs.next()) {
            double bal = rs.getDouble("balance");

            if (bal >= amount) {
                String sql = "UPDATE accounts SET balance = balance - ? WHERE acc_no = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setInt(2, accNo);
                ps.executeUpdate();

                addTransaction(accNo, "WITHDRAW", amount);

                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    // Check Balance
    public double checkBalance(int accNo) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT balance FROM accounts WHERE acc_no = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accNo);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getDouble("balance");
        return -1;
    }

    // Add transaction entry
    private void addTransaction(int accNo, String type, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO transactions(acc_no, type, amount) VALUES(?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accNo);
        ps.setString(2, type);
        ps.setDouble(3, amount);
        ps.executeUpdate();
    }
}
