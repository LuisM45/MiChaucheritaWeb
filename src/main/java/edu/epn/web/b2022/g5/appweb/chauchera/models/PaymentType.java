package edu.epn.web.b2022.g5.appweb.chauchera.models;

public class PaymentType {
    private static int LAST_ID = 0;
    
    private final int id;
    private final String name;

    public PaymentType(String name) {
        this.id = LAST_ID++;
        this.name = name;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }
    
    
}
