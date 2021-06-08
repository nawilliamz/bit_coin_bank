package com.nathan.bitcoinbank;

public class Nickel extends Coin {
    double value;

    public Nickel(String customerName, String coinType) {
        super(customerName, "nickel");
        this.value = .05;
    }
}
