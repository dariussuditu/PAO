package Servicii;

import Domain.Persoana;
import Domain.contBancar;
import Domain.contEconomii;
import Domain.contPremium;
import Repository.ContBancarRepository;
import Repository.PersoanaRepository;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class ServiciiCont {
    private TreeSet<contBancar> listaConturi;
    private String prefix="BNK";
    private int nr=7;
    private HashSet<Persoana> listaPersoane;
    public ServiciiCont() {
        listaConturi = new TreeSet<>();
        listaPersoane = new HashSet<>();

        // Fetch existing persons from the Persoana table in the database
        PersoanaRepository persoanaRepository = new PersoanaRepository();
        List<Persoana> existingPersons = persoanaRepository.getAllPersons(); // Assuming you have a method to get all persons
        listaPersoane.addAll(existingPersons);

        ContBancarRepository contBancarRepository= new ContBancarRepository();
        List<contBancar> existingContBancar = contBancarRepository.getAllCont(); // Assuming you have a method to get all persons

        // Add existing persons to listaPersoane
        listaConturi.addAll(existingContBancar);
    }



    public String generareNrCont(){
        StringBuilder sb=new StringBuilder(prefix);
        Random random=new Random();
        for(int i=0;i<nr;i++)
        {
            int digit = random.nextInt(10); // generates a digit between 0 and 9
            sb.append(digit);
        }
        return sb.toString();
    }


    public Persoana adaugaPersoana(){
        Scanner s=new Scanner(System.in);
        System.out.println("Introduceti numele persoanei: ");
        String nume=s.nextLine();
        System.out.println("Introduceti CNP-ul persoanei: ");
        String cnp=s.nextLine();
        System.out.println("Introduceti adresa persoanei: ");
        String adresa=s.nextLine();
        System.out.println("Introduceti nr. de telefon al persoanei: ");
        String nr_tel=s.nextLine();
        Persoana p=new Persoana(nume,cnp,adresa,nr_tel);
        listaPersoane.add(p);
        return p;
    }

    public Persoana GasestePersoanaDupaNume(String nume){
        Persoana persoana=null;
        for(Persoana p:listaPersoane)
        {
            if(p.getNume().equals(nume))
            {

                persoana=p;
                break;
            }


        }
        return persoana;

    }
    public contBancar adaugaCont() {
        contBancar cb = null;
        Scanner s = new Scanner(System.in);
        System.out.println("Pt cont bancar introduceti 1, pt cont economii 2");
        int ans = s.nextInt();
        s.nextLine();
        if (ans == 1) {

            System.out.println("Persoana careia ii va fi asociat noul cont este deja clienta? da/nu");
            String raspuns = s.nextLine();
            if (raspuns.equals("da")) {
                System.out.println("SUNT AICI");
                System.out.println("Introduceti numele complet al persoanei.");
                String nume = s.nextLine();
                for (Persoana p : listaPersoane) {
                    if (p.getNume().equals(nume)) {

                        String nrCont = generareNrCont();

                        cb = new contBancar(nrCont, p, 0);
                        listaConturi.add(cb);
                        return cb;

                    }
                }
            }
            if (raspuns.equals("nu")) {
                Persoana p = adaugaPersoana();
                String nrCont = generareNrCont();

                cb = new contBancar(nrCont, p, 0);
                listaConturi.add(cb);
                return cb;
            }

        }
        if (ans == 2) {

            System.out.println("Persoana careia ii va fi asociat noul cont este deja clienta? da/nu");
            String raspuns = s.nextLine();
            if (raspuns.equals("da")) {
                System.out.println("Introduceti numele complet al persoanei.");
                String nume = s.nextLine();
                for (Persoana p : listaPersoane) {
                    if (p.getNume().equals(nume)) {
                        String nrCont = generareNrCont();
                        System.out.println("rata: ");
                        Double rd = s.nextDouble();
                        s.nextLine();
                        cb = new contEconomii(nrCont, p, 0, rd);
                        listaConturi.add(cb);
                        return cb;
                    }
                }
            }
            if (raspuns.equals("nu")) {
                Persoana p = adaugaPersoana();
                String nrCont = generareNrCont();


                System.out.println("rata: ");
                Double rd = s.nextDouble();
                s.nextLine();
                cb = new contEconomii(nrCont, p, 0, rd);
                listaConturi.add(cb);
                return cb;
            }
        }
        return cb;

    }

    public contBancar adaugaCont(contBancar cb){

            listaConturi.add(cb);
            return cb;

    }

    public contBancar GasesteContDupaNumar(String numar){
        contBancar cont=null;
        for(contBancar c:listaConturi)
        {
            if(c.getNumarCont().equals(numar))
            {

                cont=c;
                break;
            }


        }
        return cont;

    }

    public Persoana GasestePersoanaDupaNrCont(String numar){
        contBancar cont=GasesteContDupaNumar(numar);
        Persoana pers=cont.getDetinatorCont();
        return pers;

    }

    public String verificaTipCont(contBancar cont) {
        if (cont instanceof contPremium) {
            return "premium";
        } else {
            return "normal";
        }
    }


    public contBancar upgradeazaInContPremium(contBancar cb){
        if(verificaTipCont(cb).equals("premium")){
            System.out.println("Contul este deja premium! ");
            return cb;
        }
        else{
            listaConturi.remove(cb);
            boolean premium=true;
            double taxaDeDeschidere=cb.getSold()/10;
            cb.setSold(cb.getSold()-taxaDeDeschidere);
            contPremium cp=new contPremium(cb.getNumarCont(),cb.getDetinatorCont(),cb.getSold(),taxaDeDeschidere,premium);
            System.out.println("Contul a fost upgradat cu succes! ");
            listaConturi.add(cp);
            return cp;

        }

    }

    public void afiseazaUtilizatoriiPremium(){
        for(contBancar cont:listaConturi){
            if(verificaTipCont(cont).equals("premium")){
                System.out.println(cont.getDetinatorCont());}
        }
    }


    public void stergeCont(contBancar cb){
        listaConturi.remove(cb);
    }

    public double extrasDeCont(String nrCont){
        contBancar cb=GasesteContDupaNumar(nrCont);
        System.out.println("Sold-ul contului tau este de " + cb.getSold());
        return cb.getSold();
    }
    public void afiseazaConturi() {
        if (listaConturi.isEmpty()) {
            System.out.println("Nu există conturi de afișat.");
        } else {
            System.out.println("Lista conturilor:");
            for (contBancar cont : listaConturi) {
                System.out.println(cont);
            }
        }
    }
    public TreeSet<contBancar> getListaConturi() {
        return listaConturi;
    }
    public HashSet<Persoana> getListaPersoane() {
        return listaPersoane;
    }

    public void setListaPersoane(HashSet<Persoana> listaPersoane) {
        this.listaPersoane = listaPersoane;
    }


}
