package com.nathan.bitcoinbank;

public class Dime extends Coin {
    double value;

    public Dime(String customerName, String coinType) {
        super(customerName, "dime");
        this.value = .10;
    }
}
