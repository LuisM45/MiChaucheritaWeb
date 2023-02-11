/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g5.appweb.chauchera.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Account{
    private static int LAST_ID = 0;
    enum Type{INCOME,SPENDING};
    
    private final int id;
    private String name;
    private double balance;
    private final Set<Type> accountTypes;
    private final List<Transaction> transactionHistory;
    
    public Account(Type... accountTypes) {
        id = LAST_ID++;
        balance = 0;
        this.accountTypes = new HashSet(Arrays.asList(accountTypes));
        this.transactionHistory = new ArrayList<>();
    }
    
    private double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        boolean canBeNegative = isType(Type.INCOME) && isType(Type.SPENDING);
        
        if(!canBeNegative && balance<0){
            throw new RuntimeException("El balance resultante es negativo");
        }
            
        
        this.balance = balance;
    }
    
    public boolean isType(Account.Type type ){
        return accountTypes.contains(type);
    }
    
    public List<Transaction> getTransactionsView(){
        return Collections.unmodifiableList(transactionHistory);
    }
    
    public List<Transaction> getTransactions(Instant startTime, Instant endTime){
        List<Transaction> transactions = transactionHistory
                .stream()
                .filter(t->t.instant().isAfter(startTime)&&t.instant().isBefore(endTime))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(transactions);
    }
    
    public void generate(double ammount){
        generate(ammount,Instant.now());
    }
    
    public void generate(double ammount,Instant instant){
        boolean isValidTransaction = this.isType(Type.INCOME);
        
        if(!isValidTransaction){
            throw new RuntimeException("Tipo de cuenta no correcto. Solamente una cuenta de ingreso puede generar ingresos.");
        }
        
        transactionHistory.add(new Transaction(ammount,instant));
        
        setBalance(getBalance()+ammount);
    }
    
    public void transferFrom(Account incomeAccount,double ammount){
        transferFrom(incomeAccount, ammount, Instant.now());
    }
    
    public void transferFrom(Account incomeAccount,double ammount, Instant instant){
        boolean isValidTransaction = this.isType(Type.SPENDING) && incomeAccount.isType(Type.INCOME);
        
        if(!isValidTransaction){
            throw new RuntimeException("Tipos de cuenta no correctos. La transferencia se puede realizar desde una cuenta de ingresos a una cuenta de gastos.");
        }
        
        transactionHistory.add(new Transaction(ammount,instant));
        incomeAccount.transactionHistory.add(new Transaction(-ammount,instant));
        
        incomeAccount.setBalance(incomeAccount.getBalance()-ammount);
        setBalance(getBalance()+ammount);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", name=" + name + ", balance=" + balance + ", accountTypes=" + accountTypes + ", transactionHistory=" + transactionHistory + '}';
    }
    
}
