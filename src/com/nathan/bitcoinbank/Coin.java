package com.nathan.bitcoinbank;

import java.text.SimpleDateFormat;
import java.util.Random;

public class Coin implements Comparable<Coin> {
    String customerName;
    String coinType;
    double value =0;
    int serialNumber;
    int count=0;
    String color;
    String datePattern = "yyyy-MM-dd";

    public Coin(String customerName, String coinType) {
        this.customerName = customerName;
        this.coinType = coinType;
        this.value = value;
        this.serialNumber = serialNumber;
        this.color = color;
        this.datePattern = datePattern;


        //generate randon 5-digit serialNumber
        Random random = new Random();
        this.serialNumber = 10000 + random.nextInt(90000);
    }

    public String getCoinType() {
        return coinType;
    }

    public double getValue() {
        return value;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCount() {
        return count;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getColor() {
        return color;
    }

    public SimpleDateFormat getDateOfDeposit() {
        return new SimpleDateFormat(datePattern);
    }

    @Override
    public String toString() {
//        return "Coin Type: " + this.coinType +  " Serial Number: " + this.serialNumber;
        return Integer.toString(this.serialNumber);
    }

    //overriding equals() to keep 1:1 relationship for sort() method
    @Override
    public final boolean equals(Object obj) {
        if(this == obj) {
                return true;
        } else if (obj instanceof Coin) {
            Coin theObject = (Coin) obj;
            if (Integer.toString(this.serialNumber).equals(Integer.toString(theObject.getSerialNumber()))){
                return Integer.toString(this.serialNumber).equals(Integer.toString(theObject.getSerialNumber()));
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(this.serialNumber) + 31 + this.coinType.hashCode();
    }

    //Since I want to sort the collection in Bank by serialNumber, I will need to set the compareTo
    //method here to compare serialNumbers (if I want to sort by customerName, I would set the
    //compareTo method to compare customerNames; however, I would need to add a customerName field
    //in Coin class to do this
    @Override
    public int compareTo(Coin coin) {
        return Integer.toString(this.serialNumber).compareTo(Integer.toString(coin.serialNumber));
    }
}


