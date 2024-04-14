package org.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Tranzactie {
    private contBancar contEmitator;
    private contBancar contReceptor;
    private double suma;
    private LocalDate data;

    // Constructor
    public Tranzactie(contBancar contEmitator,contBancar contReceptor, double suma) {
        this.contEmitator = contEmitator;
        this.contReceptor=contReceptor;
        this.suma = suma;
        this.data = LocalDate.now();
    }


    public contBancar getCont() {
        return contEmitator;
    }

    public void setCont(contBancar cont) {
        this.contEmitator = cont;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }


    public static Tranzactie citesteTranzactieDeLaTastatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți detaliile contului bancar al emitatorului:");
        contBancar emitator=contBancar.citesteContBancarDeLaTastatura();
        System.out.println("Introduceți detaliile contului bancar al receptorului:");
        contBancar receptor=contBancar.citesteContBancarDeLaTastatura();




        System.out.print("Suma tranzactionata: ");
        double suma = scanner.nextDouble();
        scanner.nextLine();


        LocalDate data = LocalDate.now();



        return new Tranzactie(emitator,receptor, suma);
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "cont emitator=" + contEmitator +
                "cont receptor=" + contReceptor +
                ", suma=" + suma +
                ", data=" + data +
                '}';
    }
}
