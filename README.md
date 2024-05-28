# Aplicatie banking  
## Pentru modelarea scenariului am folosit clasele:   
 - Persoana, ce reprezinta clientii bancii
 - contBancar,contEconomii si contPremium ce reprezinta tipurile de conturi ce pot fi deschise.
 - Card, CardCredit ce reprezinta tipurile de carduri ce pot fi asociate de conturi.
 - Credit - pentru facilitarea imprumuturilor
 - Tranzactie- pentru tranzactii intre persoane
 
## Functionalitatile au fost definite in clasele Serviciu corespunzatoare si contin urmatoarele metode:  
### ServiciiCont
 - generareNrCont()  
 - adaugaPersoana()
 - GasestePersoanaDupaNume(String nume)  
 - adaugaCont()  
 - GasesteContDupaNumar(String numar)  
 - GasestePersoanaDupaNrCont(String numar)  
 - verificaTipCont(contBancar cont) 
 - upgradeazaInContPremium(contBancar cb)  
 - stergeCont(contBancar cb)  
 - extrasDeCont(String nrCont) 
 - afiseazaConturi()  
 - afiseazaUtilizatoriiPremium() 

### ServiciiCard
 - generareNrCard()
 - generareCVV() 
 - adaugaCard()
 - efectueazaORetragere(Card c)
 - afiseazaCarduri()

### ServiciiTranzactie
- efectueazaTranzactie(contBancar contEmitator)
- afiseazaTranzactii()

### ServiciiCredit
- obtinereCredit(String nrCont)
- afiseazaCredite()

## Serviciile care sa expuna operatiile de tip CRUD au fost create pentru clasele:
### PersoanaRepository ( CRUD pt clasa PERSOANA )
### ContBancarRepository ( CRUD pt clasa contBancar )
### CardRepository ( CRUD pt clasa Card )
### TranzactieRepository ( CRUD pt clasa Tranzactie )
 
