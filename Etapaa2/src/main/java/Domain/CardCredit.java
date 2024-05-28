package Domain;

import java.time.LocalDate;

public class CardCredit extends Card {
    private double valoareImprumut;

    public CardCredit(String numarCard, Persoana titular, String codCVV, contBancar contAsociat) {
        super(numarCard, titular, codCVV, contAsociat);
        this.valoareImprumut = contAsociat.getSold() * 3;
    }

    public double getValoareImprumut() {
        return valoareImprumut;
    }

    public void setValoareImprumut(double valoareImprumut) {
        this.valoareImprumut = valoareImprumut;
    }

    @Override
    public String toString() {
        return "CardCredit{" +
                "numarCard='" + numarCard + '\'' +
                ", titular=" + titular +
                ", dataExpirare=" + dataExpirare +
                ", codCVV='" + codCVV + '\'' +
                ", contAsociat=" + contAsociat +
                ", valoareImprumut=" + valoareImprumut +
                '}';
    }
}
