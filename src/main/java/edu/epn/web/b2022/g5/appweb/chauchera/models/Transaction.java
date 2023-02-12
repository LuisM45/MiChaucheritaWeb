package edu.epn.web.b2022.g5.appweb.chauchera.models;

import java.time.Instant;

public class Transaction {
    private static int LAST_ID = 0;
    
    private final int id;
    private final double ammount;
    private final PaymentType paymentType;
    private final Instant instant;
    private final Account sender;
    private final Account recipient;

    public Transaction(double ammount, PaymentType paymentType, Instant instant, Account sender, Account recipient) {
        this.id = LAST_ID++;
        this.ammount = ammount;
        this.paymentType = paymentType;
        this.instant = instant;
        this.sender = sender;
        this.recipient = recipient;
    }

    


    
    public int id() {
        return id;
    }

    public double ammount() {
        return ammount;
    }

    public Instant instant() {
        return instant;
    }

    public Account sender() {
        return sender;
    }

    public PaymentType paymentType() {
        return paymentType;
    }

    
    
    public Account recipient() {
        return recipient;
    }
    
    @Override
    public String toString() {
        return "Transaction[" + id + ","+ ammount + "," + instant + ']';
    }

    
}
