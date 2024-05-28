package org.example;

import Domain.Persoana;
import Domain.contBancar;
import Domain.contEconomii;
//import Repository.CardRepository;
import Repository.CardRepository;
import Repository.ContBancarRepository;
import Repository.PersoanaRepository;
import Repository.TranzactieRepository;
import Servicii.ServiciiCard;
import Servicii.ServiciiCont;
import Servicii.ServiciiCredit;
import Servicii.ServiciiTranzactie;

public class Main {
    public static void main(String[] args) {
        ServiciiCont serviciiCont = new ServiciiCont();

        ServiciiTranzactie serviciiTranzactie = new ServiciiTranzactie(serviciiCont);  // Pass the instance
        ServiciiCard serviciiCard=new ServiciiCard(serviciiCont);
        ServiciiCredit serviciiCredit=new ServiciiCredit(serviciiCont);
        PersoanaRepository persoanaRepository = new PersoanaRepository();
        ContBancarRepository contBancarRepository=new ContBancarRepository();
        CardRepository cardRepository=new CardRepository();
        TranzactieRepository tranzactieRepository=new TranzactieRepository();

        contBancar cb1 = new contBancar("123456", new Persoana("John Doe", "1234567890", "Some Address", "123456789"), 1000.0);
        serviciiCont.adaugaCont(cb1);

        contEconomii ce1 = new contEconomii("789012", new Persoana("Jane Smith", "0987654321", "Another Address", "987654321"), 2000.0, 0.05);
        serviciiCont.adaugaCont(ce1);


        Persoana persoana1 = new Persoana("John Doe", "1234567890123", "123 Main St", "555-1234");
        Persoana persoana2 = new Persoana("Jane Doe", "9876543210987", "456 Elm St", "555-5678");

        //serviciiCont.adaugaCont();

        // Insert the persons into the database
        //System.out.println("Inserting persons into the database...");

         //persoanaRepository.insert(serviciiCont.adaugaPersoana());
        //persoanaRepository.update(1,persoana2);
        //persoanaRepository.delete(2);
        //serviciiCont.afiseazaConturi();


        persoanaRepository.insert(serviciiCont.adaugaPersoana(),serviciiCont);

        //contBancar cbtest=serviciiCont.adaugaCont();
         contBancarRepository.insert(serviciiCont.adaugaCont(),serviciiCont);
        //persoanaRepository.insert(serviciiCont.adaugaPersoana(),serviciiCont);

        //tranzactieRepository.insert(serviciiTranzactie.efectueazaTranzactie(serviciiCont.adaugaCont()));

        serviciiCont.afiseazaConturi();
        //contBancarRepository.delete(cb1.getNumarCont());
        //contBancarRepository.insert(cbtest);
        /*serviciiTranzactie.efectueazaTranzactie(cb1);
        serviciiCard.adaugaCard();
        //serviciiCard.efectueazaORetragere(cb1);
        serviciiCont.afiseazaConturi();
        serviciiCont.extrasDeCont("789012");

        serviciiTranzactie.afiseazaTranzactii();
        serviciiCard.afiseazaCarduri();
        serviciiCont.upgradeazaInContPremium(cb1);
        //serviciiCont.afiseazaUtilizatoriiPremium();
        serviciiCredit.obtinereCredit("123456");
        serviciiCredit.afiseazaCredite();*/
        cardRepository.insert(serviciiCard.adaugaCard());



    }
}
