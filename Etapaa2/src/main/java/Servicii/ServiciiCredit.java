package Servicii;

import Domain.Card;
import Domain.Credit;
import Domain.contBancar;

import java.util.HashSet;
import java.util.Scanner;

public class ServiciiCredit {

    private HashSet<Credit> listaCredite;
    private ServiciiCont serviciiCont;

    public ServiciiCredit(ServiciiCont serviciiCont) {  // Update constructor
        this.listaCredite = new HashSet<>();
        this.serviciiCont = serviciiCont;  // Initialize it

    }

    public Credit obtinereCredit(String nrCont){
        //dobanda 20% pt conturile normale, 5% premium
        contBancar cb=serviciiCont.GasesteContDupaNumar(nrCont);

        double rataDobandaLunara=0;
        if(serviciiCont.verificaTipCont(cb).equals("premium"))
            rataDobandaLunara = (5.0/100)/12;
        else
            rataDobandaLunara = (20.0/100)/12;
        Scanner s=new Scanner(System.in);
        System.out.println("Introduceti suma pt care doriti finantare: ");
        double sumaCreditata=s.nextDouble();
        s.nextLine();
        System.out.println("Introduceti perioada de finantare(nr luni)");
        int numarLuni=s.nextInt();
        s.nextLine();
        double rataLunara=(sumaCreditata * rataDobandaLunara * Math.pow(1 + rataDobandaLunara, numarLuni)) / (Math.pow(1 + rataDobandaLunara, numarLuni) - 1);
        Credit c=new Credit(cb,cb.getDetinatorCont(),sumaCreditata,rataLunara,numarLuni);
        listaCredite.add(c);
        cb.setSold(cb.getSold()+sumaCreditata);
        serviciiCont.getListaConturi().removeIf(cont -> cont.getNumarCont().equals(cb.getNumarCont()));
        serviciiCont.getListaConturi().add(cb);
        return c;

    }

    public void afiseazaCredite() {
        if (listaCredite.isEmpty()) {
            System.out.println("Nu există credite de afișat.");
        } else {
            System.out.println("Lista creditelor:");
            for (Credit cont : listaCredite) {
                System.out.println(cont);
            }
        }
    }


}
