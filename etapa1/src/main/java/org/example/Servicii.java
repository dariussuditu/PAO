package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Servicii {
    private TreeSet<contBancar> listaConturi;

    public Servicii() {
        listaConturi = new TreeSet<>();
    }
    public void adaugaCont(){
        Scanner s=new Scanner(System.in);
        System.out.println("Pt cont bancar introduceti 1, pt cont economii 2");
        int ans=s.nextInt();
        s.nextLine();
        if(ans==1){
            contBancar cb=contBancar.citesteContBancarDeLaTastatura();
            listaConturi.add(cb);

        }
        if (ans==2){
            contEconomii ce=contEconomii.citesteContEconomiiDeLaTastatura(s);
            listaConturi.add(ce);
        }

    }

    public void adaugaCont(contBancar cb){

            listaConturi.add(cb);

    }

    public void stergeCont(contBancar cb){
        listaConturi.remove(cb);
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



}
