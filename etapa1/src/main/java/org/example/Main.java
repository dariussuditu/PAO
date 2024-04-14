package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Servicii servicii = new Servicii();


        contBancar cb1 = new contBancar("123456", new Persoana("John Doe", "1234567890", "Some Address", "123456789"), 1000.0);
        servicii.adaugaCont(cb1);


        contEconomii ce1 = new contEconomii("789012", new Persoana("Jane Smith", "0987654321", "Another Address", "987654321"), 2000.0, 0.05);
        servicii.adaugaCont(ce1);


        servicii.adaugaCont();


        servicii.afiseazaConturi();

        Tranzactie t=new Tranzactie(cb1,ce1,1);
        System.out.println(t);

    }
}