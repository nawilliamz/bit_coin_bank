package com.nathan.bitcoinbank;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Random;

public class Transaction implements Serializable  {

        private String name;
        private int ID;
        private String type;  // D = deposit, W = withdrawal
        private double amount;
        private LocalDate date;    //  MM/DD/YYYY format
        private LocalTime time;
//        private ZonedDateTime zonedTime;

        private long serialVersionUID = 1L;

        public Transaction(String name, String type, double amount) {
            this.name = name;
            this.type = type;
            this.amount = amount;
            this.date = LocalDate.now();  //this method returns LocalDate based on system clock w/ default time zone
            this.time = LocalTime.now();

            //generate randon 5-digit serialNumber
            Random random = new Random();
            this.ID = 10000 + random.nextInt(90000);

        }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
//        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
//        return df.format(this.date);
        ZonedDateTime zonedTime = ZonedDateTime.of(this.date, this.time, ZoneId.of("America/Chicago"));
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(zonedTime);

    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    @Override
    public int hashCode() {
        return Integer.hashCode(ID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Transaction) {
            Transaction theObject = (Transaction) obj;
            if (Integer.toString(this.ID).equals(Integer.toString(theObject.ID))) {
                return Integer.toString(this.ID).equals(Integer.toString(theObject.ID));
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(ID);
    }
}




