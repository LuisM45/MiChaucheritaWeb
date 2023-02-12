package edu.epn.web.b2022.g5.appweb.chauchera.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.Instant;

public class Person {
    private static int LAST_ID=0;
    
    private final int id;
    private String name;
    private String lastname;
    
    private final List<Account> accounts;
    private final List<Statement> statements;

    public Person(String name, String lastname) {
        this.id = LAST_ID++;
        this.name = name;
        this.lastname = lastname;
        this.accounts = new ArrayList<>();
        this.statements = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }
    
    public void addStatement(Instant startTime, Instant endTime){
        statements.add(new Statement(accounts, startTime, endTime));
    }

    public List<Account> getAccountsView() {
        return Collections.unmodifiableList(accounts);
    }

    public List<Statement> getStatementsView() {
        return Collections.unmodifiableList(statements);
    }
    
    
    
    
}
