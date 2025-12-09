package main;

import dao.BankDAO;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        BankDAO bank = new BankDAO();

        while (true) {
            System.out.println("\n--- BANK MANAGEMENT ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter Name: ");
                    sc.nextLine(); 
                    String name = sc.nextLine();
                    System.out.print("Initial Amount: ");
                    double amt = sc.nextDouble();
                    bank.createAccount(name, amt);
                    break;

                case 2:
                    System.out.print("Account No: ");
                    int acc1 = sc.nextInt();
                    System.out.print("Deposit Amount: ");
                    double d = sc.nextDouble();
                    bank.deposit(acc1, d);
                    break;

                case 3:
                    System.out.print("Account No: ");
                    int acc2 = sc.nextInt();
                    System.out.print("Withdraw Amount: ");
                    double w = sc.nextDouble();
                    bank.withdraw(acc2, w);
                    break;

                case 4:
                    System.out.print("Account No: ");
                    int acc3 = sc.nextInt();
                    double bal = bank.checkBalance(acc3);

                    if (bal >= 0)
                        System.out.println("Balance: " + bal);
                    else
                        System.out.println("Account not found.");

                    break;

                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);
            }
        }
    }
}
