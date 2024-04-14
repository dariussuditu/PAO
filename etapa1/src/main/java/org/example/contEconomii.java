package org.example;

import java.util.Scanner;

public class contEconomii extends contBancar {
    private double rataDobanda;

    // Constructor
    public contEconomii(String numarCont, Persoana detinatorCont, double sold, double rataDobanda) {
        super(numarCont, detinatorCont, sold);
        this.rataDobanda = rataDobanda;
    }


    public double getRataDobanda() {
        return rataDobanda;
    }

    public void setRataDobanda(double rataDobanda) {
        this.rataDobanda = rataDobanda;
    }
    public static contEconomii citesteContEconomiiDeLaTastatura(Scanner scanner) {

        contBancar cb=citesteContBancarDeLaTastaturaPtEconomii(scanner);
        System.out.print("Rata Dobanda: ");
        double rdb = scanner.nextDouble();
        scanner.nextLine(); // Consumăm linia goală



        return new contEconomii(cb.numarCont,cb.detinatorCont,cb.sold,rdb);
    }

    @Override
    public String toString() {
        return super.toString() + ", rataDobanda=" + rataDobanda;
    }


    public double calculeazaDobanda(double suma) {

        return suma * rataDobanda;
    }
}
