/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.epn.web.b2022.g5.appweb.chauchera.models;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

class AccountImplementation implements Account{
    private static int LAST_ID = 0;
    
    private final int id;
    private double balance;
    private final Set<Class<? extends Account>> acountTypes;
    
    private AccountImplementation(Class<? extends Account>[] acountTypes) {
        id = LAST_ID++;
        balance = 0;
        this.acountTypes = new HashSet(Arrays.asList(acountTypes));
    }
    
    public static Account createAccount(Class<? extends Account>... types){
        Class[] allTypes = new Class[types.length+1];
        allTypes[0] = Account.class;
        System.out.println();
        System.arraycopy(types, 0, allTypes, 1, types.length);
        InvocationHandler handler = new MyInvocationHandler(new AccountImplementation(types));
        Account account = (Account) Proxy.newProxyInstance(AccountImplementation.class.getClassLoader(),
                allTypes,
                handler);
        
                
        return account;
    }
    
    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", balance=" + balance + '}';
    }
    
    private static class MyInvocationHandler implements InvocationHandler{
        private final AccountImplementation asociatedAccount;

        public MyInvocationHandler(AccountImplementation asociatedAccount) {
            this.asociatedAccount = asociatedAccount;
        }
        
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            method
            return method.invoke(asociatedAccount, args);
        }
    }
}
