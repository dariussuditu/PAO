package Servicii;

import Domain.Tranzactie;
import Domain.contBancar;

import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class ServiciiTranzactie {

    private HashSet<Tranzactie> listaTranzactii;
    private ServiciiCont serviciiCont;  // Add this field

    public ServiciiTranzactie(ServiciiCont serviciiCont) {  // Update constructor
        this.listaTranzactii = new HashSet<>();
        this.serviciiCont = serviciiCont;  // Initialize it
    }

    public Tranzactie efectueazaTranzactie(contBancar contEmitator) {
        Tranzactie t = null;
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti nr de cont in care doriti sa transferati: ");
        String nr = s.nextLine();
        for (contBancar cb : serviciiCont.getListaConturi()) {  // Use the passed instance
            if (cb.getNumarCont().equals(nr)) {
                System.out.println("Ce suma doriti sa tranzactionati? ");
                Double suma = s.nextDouble();
                s.nextLine();
                // Scade suma din contEmitator, adauga-o in cb
                if (contEmitator.getSold() >= suma) {
                    contEmitator.setSold(contEmitator.getSold() - suma);
                    cb.setSold(cb.getSold() + suma);
                    t = new Tranzactie(contEmitator, cb, suma);
                    listaTranzactii.add(t);
                } else {
                    System.out.println("Fonduri insuficiente pentru tranzactie.");
                }
                break;
            }
        }
        if (t == null) {
            System.out.println("Tranzactia nu a putut fi efectuata. Contul destinatar nu a fost gasit.");
        }
        return t;
    }

    public void afiseazaTranzactii() {
        if (listaTranzactii.isEmpty()) {
            System.out.println("Nu există tranzactii de afișat.");
        } else {
            System.out.println("Lista tranzactiilor:");
            for (Tranzactie cont : listaTranzactii) {
                System.out.println(cont);
            }
        }
    }

    public HashSet<Tranzactie> getListaTranzactii() {
        return listaTranzactii;
    }
}
