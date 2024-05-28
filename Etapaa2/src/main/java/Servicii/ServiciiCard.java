package Servicii;

import Domain.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class ServiciiCard {

    private HashSet<Card> listaCarduri;
    private int nr_cifre=12;
    private int nr=3;

    private ServiciiCont serviciiCont;  // Add this field

    public ServiciiCard(ServiciiCont serviciiCont) {  // Update constructor
        this.listaCarduri = new HashSet<>();
        this.serviciiCont = serviciiCont;  // Initialize it
    }


    public String generareNrCard(){
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<nr_cifre;i++)
        {
            int digit = random.nextInt(10); // generates a digit between 0 and 9
            sb.append(digit);
        }
        return sb.toString();
    }

    public String generareCVV(){
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<nr;i++)
        {
            int digit = random.nextInt(10); // generates a digit between 0 and 9
            sb.append(digit);
        }
        return sb.toString();
    }


    public Card adaugaCard(){
        Scanner s=new Scanner(System.in);

        String nc=generareNrCard();
        System.out.println("Numarul contului asociat cardului: ");
        String numar=s.nextLine();
        Persoana pers=serviciiCont.GasestePersoanaDupaNrCont(numar);
        String cvv=generareCVV();
        contBancar cb=serviciiCont.GasesteContDupaNumar(numar);
        System.out.println("Pt card normal de debit apasa 1, pt card de credit apasa 2.");
        String tip=s.nextLine();

        Card card=null;
        if(tip.equals("1")){
            card=new Card(nc,pers,cvv,cb);
            listaCarduri.add(card);}
        else{

            card=new CardCredit(nc,pers,cvv,cb);
            listaCarduri.add(card);}

        return card;


    }

    public Card efectueazaORetragere(Card c) {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti suma de retras: ");
        double sumaDeRetras = s.nextDouble();
        s.nextLine();

        if (c instanceof CardCredit) {
            CardCredit cardCredit = (CardCredit) c;
            double valoareImprumut = cardCredit.getValoareImprumut();
            if (sumaDeRetras <= valoareImprumut) {
                cardCredit.getContAsociat().setSold(cardCredit.getContAsociat().getSold() - sumaDeRetras);
                cardCredit.setValoareImprumut(valoareImprumut - sumaDeRetras);
                System.out.println("Retragere de " + sumaDeRetras + " efectuata cu succes.");
            } else {
                System.out.println("Fonduri insuficiente. Puteti retrage maxim " + valoareImprumut);
            }
        } else {
            contBancar contBancar = c.getContAsociat();
            double sold = contBancar.getSold();
            if (sumaDeRetras <= sold) {
                contBancar.setSold(sold - sumaDeRetras);
                System.out.println("Retragere de " + sumaDeRetras + " efectuata cu succes.");
            } else {
                System.out.println("Fonduri insuficiente. Puteti retrage maxim " + sold);
            }
        }

        return c;
    }

    public void adaugaCard(Card cb){

        listaCarduri.add(cb);

    }


    public void afiseazaCarduri() {
        if (listaCarduri.isEmpty()) {
            System.out.println("Nu există carduri de afișat.");
        } else {
            System.out.println("Lista cardurilor:");
            for (Card cont : listaCarduri) {
                System.out.println(cont);
            }
        }
    }



}
