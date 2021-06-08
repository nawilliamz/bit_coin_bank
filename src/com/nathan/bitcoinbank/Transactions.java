package com.nathan.bitcoinbank;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


//Do not need a Transactions class at this time. Not going to try to set up Random Access for
//two reasons:
//1. It makes sense to keep the hashSet static. It wouldn't make sense to maintain more than
//one set of Transactions (i.e. bitcoinbank only needs one overall set of transactions). However,
//a static hashSet in Transactions would have to be made public in order to access it in
//Main() which will violate principles of encapsulation.
//2. Will wait for the database lessons. It makes more sense to store bank transactions in
//a database rather than on a text file or in memory due to the volume of transactions.


public class Transactions implements Set {

    private static Transactions instance = new Transactions();
    private Set<Transaction> transactionSet;
    private DateTimeFormatter df;

    private Transactions() {
        transactionSet = new HashSet<>();
    }

    public static void main(String[] args) {
        try (ObjectOutputStream transFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("transactions.dat")))) {
            for (Transaction transaction: Transactions.getInstance().getTransactionSet()) {
                transFile.writeObject(transaction);
            }

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    static{
        try (ObjectInputStream transFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("transactions.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Transaction transaction = (Transaction) transFile.readObject();
                    System.out.println("******Below are Transactions read in from the file*******");
                    System.out.println("Read transaction name: " + transaction.getName());
                    System.out.println("Read transaction type: " + transaction.getType());
                    System.out.println("Read transaction amount: " + transaction.getAmount());
                    System.out.println("Read transaction ID: " + transaction.getID());
                    System.out.println("Read transaction date: " + transaction.getDate());

                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (InvalidClassException e) {
            System.out.println("Invalid class exception" + e.getMessage());
        } catch (IOException io) {
            System.out.println("IO Exception" + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception" + e.getMessage());
        }


    }

    public static Transactions getInstance() {
        return instance;
    }

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(HashSet<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    public void writeTransactions() {
        try (ObjectOutputStream transFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("transactions.dat")))) {
            for (Transaction transaction: Transactions.getInstance().getTransactionSet()) {
                transFile.writeObject(transaction);
            }

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }
}
