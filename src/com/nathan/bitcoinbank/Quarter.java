package com.nathan.bitcoinbank;

public class Quarter extends Coin {
    double value;

    public Quarter(String customerName, String coinType) {
        super(customerName, "quarter");
        this.value = .25;
    }
}
