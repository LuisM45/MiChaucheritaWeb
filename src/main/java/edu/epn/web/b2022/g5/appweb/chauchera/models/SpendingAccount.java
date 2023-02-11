package edu.epn.web.b2022.g5.appweb.chauchera.models;


public interface SpendingAccount extends Account{
    public default void transferFrom(IncomeAccount incomeAccount,double ammount){
        incomeAccount.setBalance(incomeAccount.getBalance()-ammount);
        setBalance(getBalance()+ammount);
    }
}
