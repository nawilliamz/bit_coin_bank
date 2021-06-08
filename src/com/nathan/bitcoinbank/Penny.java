package com.nathan.bitcoinbank;

import java.util.Random;

public class Penny extends Coin {
        double value;

    public Penny(String customerName, String coinType) {
        super(customerName, "penny");
        this.value = value = .01;
//        Random random = new Random();
//        serialNumber = 10000 + random.nextInt(90000);
    }
}
