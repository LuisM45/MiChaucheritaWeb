/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g5.appweb.chauchera.models;

/**
 *
 * @author luism
 */
public class Main {
    public static void main(String[] args) {
        
//        Person o = new Person();
//        IncomeAccount ic = new IncomeAccount("Nomina");
//        SpendingAccount sa = new SpendingAccount("Universidad");
//        AccountInterface bank = new AccountInterface("Banco");
//        o.addAccount(ic);
//        o.addAccount(sa);
//        o.addAccount(bank);
//        o.getAccountByName("Nomina").generate(2000);
//        o.getAccountByName("Banco").generate(2000);
        Account a = AccountImplementation.createAccount(IncomeAccount.class,SpendingAccount.class);
        a.setBalance(200);
        ((IncomeAccount)a).generate(2000);
        
        System.out.println(a);
        System.out.println((Account)a);
        System.out.println((IncomeAccount)a);
        System.out.println((SpendingAccount)a);
               
        
    }
}
