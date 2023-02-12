package edu.epn.web.b2022.g5.appweb.chauchera.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Statement {
    private static int LAST_ID = 0;
    
    private final int id;
    private final List<Account> accounts;
    private final Instant startTime;
    private final Instant endTime;
    private final Map<Account,List<Transaction>> transactions;

    public Statement(List<Account> accounts, Instant startTime, Instant endTime) {
        this.id = LAST_ID++;
        this.accounts = new ArrayList<>(accounts);
        this.startTime = startTime;
        this.endTime = endTime;
        this.transactions = new HashMap<>();
    }

    private void setTransactions(){
        for(Account ac: accounts)
            transactions.put(ac, ac.getTransactions(startTime, endTime));
    }
    
    public int getId() {
        return id;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Map<Account,List<Transaction>> getTransactions() {
        return Collections.unmodifiableMap(transactions);
    }
    
}
