package edu.epn.web.b2022.g5.appweb.chauchera;

import edu.epn.web.b2022.g5.appweb.chauchera.models.*;

public class Main {
    public static void main(String[] args) {
        
        Account a = new Account("Nomina",Account.Type.INCOME);
        Account b = new Account("Banco",Account.Type.INCOME, Account.Type.SPENDING);
        Account u = new Account("Universidad",Account.Type.SPENDING);
        
        a.receiveFrom(null,1000,"2022-01-01",null);
        a.receiveFrom(null,2000,"2023-01-01",null);
        a.receiveFrom(null,3000,"2024-01-01",null);
        
        b.transferTo(a, 500,"2022-03-03",null);
        b.transferTo(a, 200,"2022-03-04",null);
        
        u.transferTo(b, 1000,"2022-07-04",null);
        u.transferTo(b, 2000,"2022-07-08",null);
        
        
        Person p = new Person("Pedro","Pico"); //Placeholder for future needs
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(u);
        System.out.println(a.getTransactions("2022-01-01","2023-12-12"));
        System.out.println(b.getTransactions("2022-01-01","2023-12-12"));
        System.out.println(u.getTransactions("2022-01-01","2023-12-12"));
               
        
    }
}
