package edu.epn.web.b2022.g5.appweb.chauchera.models;

public class Main {
    public static void main(String[] args) {
        
        Account a = new Account(Account.Type.INCOME);
        a.generate(200);
        
        System.out.println(a);
               
        
    }
}
