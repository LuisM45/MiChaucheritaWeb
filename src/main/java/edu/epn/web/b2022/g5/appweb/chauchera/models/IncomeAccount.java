package edu.epn.web.b2022.g5.appweb.chauchera.models;

public interface IncomeAccount extends Account{
    public default void generate(double ammount){
        setBalance(getBalance()+ammount);
    }
}
