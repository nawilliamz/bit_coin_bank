package com.nathan.bitcoinbank;

import java.io.RandomAccessFile;
import java.sql.SQLOutput;
import java.util.*;

public class Bank {

    private String bankType;
    private String customerName;
    private Coin[] coins;
    private List<Coin> coinList;
    HashMap<String, Double> coinCustomerValue;
    HashMap<String, Integer> coinCustomerSerialNumber;
    HashSet<Coin> coinSet;
    private HashSet<Transaction> transactionSet;



    public Bank() {
        this.bankType = bankType;
        this.coins = new Coin[4];
        this.customerName = customerName;
        this.coinList = new ArrayList<>();
        this.coinCustomerValue = new HashMap<>();
        this.coinSet = new HashSet<>();
        this.coinCustomerSerialNumber = new HashMap<String, Integer>();
        this.transactionSet = new HashSet<>();
    }



    public void addCoin (String coinType, String customerName) {


        if (coinType.equals("penny")) {
            Penny coin = new Penny(customerName, "penny");
            coin.count++;
            coinList.add(coin);
            coinCustomerValue.put(customerName, coin.getValue());
            coinCustomerSerialNumber.put(customerName, coin.getSerialNumber());
        } else if (coinType.equals("nickel")) {
            Nickel coin = new Nickel(customerName, "nickel");
            coin.count++;
            coinList.add(coin);
            coinCustomerValue.put(customerName, coin.getValue());
            coinCustomerSerialNumber.put(customerName, coin.getSerialNumber());
        } else if (coinType.equals("dime")) {
            Dime coin = new Dime (customerName, "dime");
            coin.count++;
            coinList.add(coin);
            coinCustomerValue.put(customerName, coin.getValue());
            coinCustomerSerialNumber.put(customerName, coin.getSerialNumber());
        } else if (coinType.equals("quarter")) {
            Quarter coin = new Quarter(customerName, "quarter");
            coin.count++;
            coinList.add(coin);
            coinCustomerValue.put(customerName, coin.getValue());
            coinCustomerSerialNumber.put(customerName, coin.getSerialNumber());
        }
    }

//    public void addCoin (String coinType, String customerName) {
//        Coin penny = new Penny(customerName);
//        Coin nickel = new Nickel(customerName);
//        Coin dime = new Dime(customerName);
//        Coin quarter = new Quarter(customerName);
//
//
//        if (coinType.equals(penny.coinType)) {
//            coinList.add(penny);
//            coinCustomerValue.put(customerName, penny.getValue());
//            coinCustomerSerialNumber.put(customerName, penny.getSerialNumber());
//        } else if (coinType.equals(nickel.coinType)) {
//            coinList.add(nickel);
//            coinCustomerValue.put(customerName, nickel.getValue());
//            coinCustomerSerialNumber.put(customerName, nickel.getSerialNumber());
//        } else if (coinType.equals(dime.coinType)) {
//            coinList.add(dime);
//            coinCustomerValue.put(customerName, dime.getValue());
//            coinCustomerSerialNumber.put(customerName, dime.getSerialNumber());
//        } else if (coinType.equals(quarter.coinType)) {
//            coinList.add(quarter);
//            coinCustomerValue.put(customerName, quarter.getValue());
//            coinCustomerSerialNumber.put(customerName, quarter.getSerialNumber());
//        }
//    }

    public void withdrawCoin (int serialNumber) {
        for (int i = 0; i < coinList.size(); i++) {
            if (coinList.get(i).getSerialNumber() == serialNumber) {
                coinList.remove(coinList.get(i));
                System.out.println("You have removed coin with serial number " + serialNumber);
            }

        }
    }
//        coinList.remove(coinList.get())
//        Coin penny = new Penny(customerName);
//        Coin nickel = new Nickel(customerName);
//        Coin dime = new Dime(customerName);
//        Coin quarter = new Quarter(customerName);

//        if (coin.coinType.equals("penny")) {
//            coinList.remove(coin.serialNumber);
//            coinCustomerValue.remove(coin.customerName, coin.getValue());
//            coinCustomerSerialNumber.remove(customerName, penny.getSerialNumber());
//        } else if (coinType.equals(nickel.coinType)) {
//            coinList.remove(nickel);
//            coinCustomerValue.remove(customerName, nickel.getValue());
//            coinCustomerSerialNumber.remove(customerName, nickel.getSerialNumber());
//        } else if (coinType.equals(dime.coinType)) {
//            coinList.remove(dime);
//            coinCustomerValue.remove(customerName, dime.getValue());
//            coinCustomerSerialNumber.remove(customerName, dime.getSerialNumber());
//        } else if (coinType.equals(quarter.coinType)) {
//            coinList.remove(quarter);
//            coinCustomerValue.remove(customerName, quarter.getValue());
//            coinCustomerSerialNumber.remove(customerName, quarter.getSerialNumber());
//        }


    public HashSet<Transaction> getTransactionSet() {
        return new HashSet<Transaction>(transactionSet);
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void printCoinList() {
//        for (int i = 0; i < coinList.size(); i++) {
//            System.out.println(coinList.get(i).getSerialNumber());
//        }
        System.out.println(coinList);
    }

    public void sortCoinList() {

        Collections.sort(coinList);
        System.out.println(coinList);

    }
    public void sortCoinListReverse(){
        Collections.reverse(coinList);
        System.out.println(coinList);
    }

    public void customerCoinProfile(String customerName) {

        int countPennies = 0;
        int countNickels = 0;
        int countDimes = 0;
        int countQuarters = 0;

        //Step 1: Put all Coins belonging to customerName into a new ArrayList
        List<Coin> coinListByCustomerName = new ArrayList<>();
        for (int i = 0; i < coinList.size(); i++) {
            if (coinList.get(i) != null && coinList.get(i).customerName.equals(customerName)) {
                coinListByCustomerName.add(coinList.get(i));
            }
        }

        //Step 2: Sum Coin count for each type of Coin; save sum to a unique variable
        for (int i = 0; i < coinListByCustomerName.size(); i++) {
            if (coinListByCustomerName.get(i).coinType.equals("penny")) {
                countPennies += coinListByCustomerName.get(i).getCount();
            } else if (coinListByCustomerName.get(i).coinType.equals("nickel")) {
                countNickels += coinListByCustomerName.get(i).getCount();
            } else if (coinListByCustomerName.get(i).coinType.equals("dime")) {
                countDimes += coinListByCustomerName.get(i).getCount();
            } else if (coinListByCustomerName.get(i).coinType.equals("quarter")) {
                countQuarters += coinListByCustomerName.get(i).getCount();
            }
        }
            System.out.println("Customer: " + customerName);
            System.out.println("\t Penny Count: " + countPennies);
            System.out.println("\t Nickel Count: " + countNickels);
            System.out.println("\t Dimes Count: " + countDimes);
            System.out.println("\t Quarters Count: " + countQuarters);
            System.out.println("**********************************");
            System.out.println("\t Penny $$: " + countPennies * .01);
            System.out.println("\t Nickel $$: " + countNickels * .05);
            System.out.println("\t Dime $$: " + countDimes * .10);
            System.out.println("\t Quarter $$: " + countQuarters * .25);
            System.out.println("\t\t TOTAL $$: " + (countPennies*.01 + countNickels*.05 + countDimes*.10 + countQuarters*.25));
        }



//        public void deposit ()
    }
//next method is to sort through a list and add and subtract objects from the map
    //use Comparable  and compareTo()   //in next method, do the same thing but use a Comparator


