package com.nathan.bitcoinbank;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bitCoinBank = new Bank();



    public static void main(String[] args) {
        boolean quit = true;
        int choice = 0;
        printInstructions();
        while (quit) {
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            scanner.nextLine(); //to clear the input buffer
            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    addCoin();
                    break;
                case 2:
                    withdrawCoin();
                    break;
                case 3:
                    newTransaction();
                    break;
                case 4:
                    printTransactions();
                    break;
                case 5:
                    getCoinProfileCustomer();
                    break;
                case 6:
                    printCoinList();
                    break;
                case 7:
                    sortCoinList();
                    break;
                case 8:
                    saveTransactions();
                    break;
                case 9:
                    quit = false;
                    break;
            }

        }
    }

    public static void printInstructions() {
        System.out.println("\nPress: ");
        System.out.println("\t 0 - to print choice options");
        System.out.println("\t 1 - to add a coin to BitCoinBank");
        System.out.println("\t 2 - to withdraw a coin from BitCoinBank");
        System.out.println("\t 3 - new transaction");
        System.out.println("\t 4 - print transactions by ID number");
        System.out.println("\t 5 - to print a profile for each customer");
        System.out.println("\t 6 - to print a list of all coins in BitCoinBank by serial number");
        System.out.println("\t 7 - sortCoinList");
        System.out.println("\t 8 - save transactions");
        System.out.println("\t 9 - to quit");
    }

    public static void addCoin() {
        System.out.println("Enter coin type (penny, nickel, dime, quarter): ");
        String coinType = scanner.nextLine();
        if (coinType.equals("penny") || coinType.equals("nickel") || coinType.equals("dime") || coinType.equals("quarter")) {
            System.out.println("Enter customer owner of the coin: ");
            String customerName = scanner.nextLine();
            bitCoinBank.addCoin(coinType, customerName);
            System.out.println("You have successfully added your coin to the bank");
        } else {
            System.out.println("You have entered coin type incorrectly. Please try again");
        }

    }

    public static void withdrawCoin() {
        System.out.println("Enter the serial number for the coin to withdraw (serial numbers are 5-digit integers): ");
        int serialNumber = scanner.nextInt();
        scanner.nextLine();
//        System.out.println("Enter customer owner of the coin:");
//        String customerName = scanner.nextLine();
        bitCoinBank.withdrawCoin(serialNumber);
//        System.out.println("You have successfully withdrawn your coin from the bank");
    }

    public static void getCoinProfileCustomer() {
        System.out.println("Select customer from following list (type the name): ");
        for (String customerName : bitCoinBank.coinCustomerSerialNumber.keySet()) {
            System.out.println(customerName);
        }
        String customerName = scanner.nextLine();
        bitCoinBank.customerCoinProfile(customerName);
    }


    public static void printCoinList() {
        bitCoinBank.printCoinList();
    }

    public static void sortCoinList() {
        System.out.println("Sort: ");
        System.out.println("\t Ascending: Option 1");
        System.out.println("\t Descending: Option 2");

        int optionSelect = scanner.nextInt();
        scanner.nextLine();
        if (optionSelect==1) {
            bitCoinBank.sortCoinList();
        } else if (optionSelect==2) {
            bitCoinBank.sortCoinListReverse();
        }
    }

    public static void newTransaction() {
        double sum = 0;

        System.out.println("Please enter customer name: ");
        String customerName = scanner.nextLine();


        boolean quit = false;
        while(!quit) {
            System.out.println("Do you want to deposit or withdraw (D = deposit, W = withdraw)?");
            String type = scanner.nextLine().toUpperCase();
            if (type.equals("W")) {
                System.out.println("What is the serial number of the coin to withdraw");
                int sn = scanner.nextInt();
                scanner.hasNextLine();
                for (Coin coin : bitCoinBank.getCoinList()) {
                    if (coin.serialNumber == sn) {
                        double value = coin.getValue();
                        sum -= value;
                        Transaction transaction = new Transaction(customerName, type, value);
//                        bitCoinBank.getTransactionSet().remove(transaction);
                        Transactions.getInstance().getTransactionSet().remove(transaction);

                    }
                }
                bitCoinBank.withdrawCoin(sn);

            } else if (type.equals("D")) {
                double amount = 0;
                System.out.println("What type of coin do you want to add (penny, nickel, dime, quarter)?");
                String coinType = scanner.nextLine();
                if (coinType.equals("penny")) {
                    amount = .01;
                } else if (coinType.equals("nickel")) {
                    amount = .05;
                } else if (coinType.equals("dime")) {
                    amount = .10;
                } else if (coinType.equals("quarter")) {
                    amount = .25;
                } else {
                    System.out.println("Type not found. Please re-enter.");
                }


                Transaction transaction = new Transaction(customerName, type, amount);

                System.out.println(transaction.getName());
                System.out.println(transaction.getAmount());
                System.out.println(transaction.getID());
                System.out.println(transaction.getType());
                System.out.println(transaction.getDate());


//                bitCoinBank.getTransactionSet().add(transaction);
                Transactions.getInstance().getTransactionSet().add(transaction);
                bitCoinBank.addCoin(coinType,customerName);

            }
            quit = true;
        }
    }

    public static void saveTransactions() {
        Transactions.getInstance().writeTransactions();
        System.out.println("Transactions have been saved.");
    }

    public static void printTransactions () {
        System.out.println(Transactions.getInstance().getTransactionSet());
    }
}
