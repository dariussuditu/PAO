package Domain;

public class contPremium extends contBancar{

    private double taxaDeschidereContPremium;
    private boolean premium;
    public contPremium(String numarCont, Persoana detinatorCont, double sold, double taxaDeschidereContPremium, boolean premium) {
        super(numarCont, detinatorCont, sold);
        this.taxaDeschidereContPremium=taxaDeschidereContPremium;
        this.premium = true;
    }

    public boolean getPremium(){
        return premium;
    }

    public contPremium(String numarCont, Persoana detinatorCont, double sold) {
        super(numarCont, detinatorCont, sold);
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getTaxaDeschidereContPremium(

    ) {
        return taxaDeschidereContPremium;
    }

    public void setTaxaDeschidereContPremium(int taxaDeschidereContPremium) {
        this.taxaDeschidereContPremium = taxaDeschidereContPremium;
    }


    @Override
    public String toString() {
        return super.toString() + ", Contul este premium=" + premium;
    }
}
