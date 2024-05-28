package Domain;

import Servicii.ServiciiCont;

import java.time.LocalDate;
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

    public LocalDate getData(){return data;}

    public void setSuma(double suma) {
        this.suma = suma;
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
