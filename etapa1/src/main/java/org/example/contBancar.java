package org.example;

import java.util.Scanner;
import java.util.Comparator;
public class contBancar implements Comparable<contBancar> {
    protected String numarCont;
    protected Persoana detinatorCont;
    protected double sold;

    // Constructor
    public contBancar(String numarCont, Persoana detinatorCont, double sold) {
        this.numarCont = numarCont;
        this.detinatorCont = detinatorCont;
        this.sold = sold;
    }


    public String getNumarCont() {
        return numarCont;
    }

    public void setNumarCont(String numarCont) {
        this.numarCont = numarCont;
    }

    public Persoana getDetinatorCont() {
        return detinatorCont;
    }

    public void setDetinatorCont(Persoana detinatorCont) {
        this.detinatorCont = detinatorCont;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }


    @Override
    public int compareTo(contBancar other) {
        // Compare based on the numarCont attribute
        return this.numarCont.compareTo(other.numarCont);
    }


    public static Comparator<contBancar> numarContComparator = Comparator.comparing(contBancar::getNumarCont);



    public static contBancar citesteContBancarDeLaTastatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți detaliile contului bancar:");


        System.out.print("Număr cont: ");
        String numarCont = scanner.nextLine();


        Persoana detinatorCont = Persoana.citestePersoanaDeLaTastaturaPtCont(scanner);


        System.out.print("Sold cont: ");
        double sold = scanner.nextDouble();
        scanner.nextLine();


        return new contBancar(numarCont, detinatorCont, sold);
    }

    public static contBancar citesteContBancarDeLaTastaturaPtEconomii(Scanner scanner) {
        System.out.println("Introduceți detaliile contului bancar:");


        System.out.print("Număr cont: ");
        String numarCont = scanner.nextLine();


        Persoana detinatorCont = Persoana.citestePersoanaDeLaTastaturaPtCont(scanner);


        System.out.print("Sold cont: ");
        double sold = scanner.nextDouble();
        scanner.nextLine();


        return new contBancar(numarCont, detinatorCont, sold);
    }

    @Override
    public String toString() {
        return "numarCont='" + numarCont + '\'' +
                ", detinatorCont='" + detinatorCont + '\'' +
                ", sold=" + sold;
    }
}
