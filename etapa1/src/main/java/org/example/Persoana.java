package org.example;
import java.util.Scanner;
public class Persoana {
    private String nume;
    private String cnp; // Cod Numeric Personal
    private String adresa;
    private String telefon;


    public Persoana(String nume, String cnp, String adresa, String telefon) {
        this.nume = nume;
        this.cnp = cnp;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    // Metode de acces pentru atributele private
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public static Persoana citestePersoanaDeLaTastatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceți detaliile persoanei:");

        // Citire nume
        System.out.print("Nume: ");
        String nume = scanner.nextLine();

        // Citire CNP
        System.out.print("CNP: ");
        String cnp = scanner.nextLine();

        // Citire adresa
        System.out.print("Adresă: ");
        String adresa = scanner.nextLine();

        // Citire telefon
        System.out.print("Telefon: ");
        String telefon = scanner.nextLine();

        // Închiderea scannerului


        return new Persoana(nume, cnp, adresa, telefon);
    }

    public static Persoana citestePersoanaDeLaTastaturaPtCont(Scanner scanner) {

        System.out.println("Introduceți detaliile persoanei:");


        System.out.print("Nume: ");
        String nume = scanner.nextLine();


        System.out.print("CNP: ");
        String cnp = scanner.nextLine();


        System.out.print("Adresă: ");
        String adresa = scanner.nextLine();


        System.out.print("Telefon: ");
        String telefon = scanner.nextLine();





        return new Persoana(nume, cnp, adresa, telefon);
    }
    @Override
    public String toString() {
        return  "nume='" + nume + '\'' +
                ", cnp='" + cnp + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\''+
                '}';
    }
}
