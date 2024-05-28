package Domain;

import java.time.LocalDate;

public class Card {
    protected String numarCard;
    protected Persoana titular;
    protected LocalDate dataExpirare;
    protected String codCVV;
    protected contBancar contAsociat;

    public Card(String numarCard, Persoana titular,  String codCVV, contBancar contAsociat) {
        this.numarCard = numarCard;
        this.titular = titular;
        this.dataExpirare = LocalDate.now().plusYears(5);
        this.codCVV = codCVV;
        this.contAsociat = contAsociat;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    public Persoana getTitular() {
        return titular;
    }

    public void setTitular(Persoana titular) {
        this.titular = titular;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public String getCodCVV() {
        return codCVV;
    }

    public void setCodCVV(String  codCVV) {
        this.codCVV = codCVV;
    }

    public contBancar getContAsociat() {
        return contAsociat;
    }

    public void setContAsociat(contBancar contAsociat) {
        this.contAsociat = contAsociat;
    }

    @Override
    public String toString() {
        return "Card{" +
                "numarCard='" + numarCard + '\'' +
                ", titular='" + titular + '\'' +
                ", dataExpirare=" + dataExpirare +
                ", codCVV=" + codCVV +
                ", contAsociat=" + contAsociat +
                '}';
    }
}
