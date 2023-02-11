package edu.epn.web.b2022.g5.appweb.chauchera.models;

import java.time.Instant;

public class Transaction {
    private static int LAST_ID = 0;
    
    private final int id;
    private final double ammount;
    private final Instant instant;

    public Transaction(double ammount, Instant instant) {
        this.id = LAST_ID++;
        this.ammount = ammount;
        this.instant = instant;
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

    
    
}
