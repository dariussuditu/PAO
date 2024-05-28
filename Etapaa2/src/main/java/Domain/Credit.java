package Domain;

import java.time.LocalDate;

public class Credit {

    private contBancar contCreditat;

    private Persoana persoanaCreditata;
    private double sumaCreditata;
    private double rataDobanda;
    private int perioadaCreditareInLuni;

    public Credit(contBancar contCreditat, Persoana persoanaCreditata, double sumaCreditata, double rataDobanda, int perioadaCreditareInLuni) {

        this.contCreditat=contCreditat;
        this.persoanaCreditata=persoanaCreditata;
        this.sumaCreditata = sumaCreditata;
        this.rataDobanda = rataDobanda;
        this.perioadaCreditareInLuni = perioadaCreditareInLuni;
    }

    public double getSumaCreditata() {
        return sumaCreditata;
    }

    public void setSumaCreditata(double sumaCreditata) {
        this.sumaCreditata = sumaCreditata;
    }

    public double getRataDobanda() {
        return rataDobanda;
    }

    public void setRataDobanda(double rataDobanda) {
        this.rataDobanda = rataDobanda;
    }

    public int getPerioadaCreditare() {
        return perioadaCreditareInLuni;
    }

public void setPerioadaCreditareInLuni(int perioadaCreditareInLuni) {
        this.perioadaCreditareInLuni = perioadaCreditareInLuni;
    }





    @Override
    public String toString() {
        return "Credit{" +
                "Cont creditat: " + contCreditat+
                "sumaCreditata=" + sumaCreditata +
                ", rataDobanda=" + rataDobanda +
                ", dataScadentei=" + perioadaCreditareInLuni+
                '}';
    }
}
