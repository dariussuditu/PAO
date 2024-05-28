package Domain;
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

    @Override
    public String toString() {
        return  "nume='" + nume + '\'' +
                ", cnp='" + cnp + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\''+
                '}';
    }
}
