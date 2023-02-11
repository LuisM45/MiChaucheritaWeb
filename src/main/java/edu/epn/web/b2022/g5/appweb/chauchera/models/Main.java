package edu.epn.web.b2022.g5.appweb.chauchera.models;

public class Main {
    public static void main(String[] args) {
        
        Account a = new Account("Nomina",Account.Type.INCOME);
        Account b = new Account("Banco",Account.Type.INCOME, Account.Type.SPENDING);
        Account u = new Account("Universidad",Account.Type.SPENDING);
        
        a.generate(1000,"2022-01-01");
        a.generate(2000,"2023-01-01");
        a.generate(3000,"2024-01-01");
        
        b.transferFrom(a, 500,"2022-03-03");
        b.transferFrom(a, 200,"2022-03-04");
        
        u.transferFrom(b, 1000,"2022-07-04");
        u.transferFrom(b, 2000,"2022-07-08");
        
        
        Person p = new Person(); //Placeholder for future needs
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(u);
        System.out.println(a.getTransactions("2022-01-01","2023-12-12"));
        System.out.println(b.getTransactions("2022-01-01","2023-12-12"));
        System.out.println(u.getTransactions("2022-01-01","2023-12-12"));
               
        
    }
}
