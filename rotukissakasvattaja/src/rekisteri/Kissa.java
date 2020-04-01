/**
 * 
 */
package rekisteri;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

import static rekisteri.Rekisterinumero.*;
import static rekisteri.Omistajanumero.*;

/**
 * Rekisterin kissa, joka mm. osaa huolehtia tunnusnumeroistaan.
 * Vastuualueet:
 * - Tietää kissan kentät (nimi, rek.nro, väri ym)
 * - Osaa muuttaa 1|Kissakaveri Sadepilvi|-merkkijonon| kissan tiedoiksi                               
 * - Osaa antaa merkkijonona i:n kentän tiedot       
 * - Osaa laittaa merkkijonon i:neksi kentäksi      
 * - Osaa ilmoittaa kissasta, jonka seuraava rokotus on pian ajankohtainen  //TODO: tee tämä 
 * @author annik
 * @version 1.4.2020
 * 
 */
public class Kissa {
    
    private int        kissantunnusNro;
    private int        omistajantunnusNro;
    private String     nimi          = "";
    private String     rotu          = "";
    private String     emsKoodi      = "";
    private String     vari          = "";
    private String     rekisteriNro  = "";
    private String     sukupuoli     = "";
    private String     syntymaAika   = "";
    private String     emonNimi      = "";
    private String     emonRekisteriNro = "";
    private String     isanNimi      = "";
    private String     isanRekisteriNro = "";
    private String     myyntipaiva   = "";
    private String     viimeisinRokotus = "";
    private String     seuraavaRokotus = "";
    private String     lisatiedot    = "";

    private static int seuraavaNro    = 1;
    
    /**
     * @return kissan nimi
     * @example
     * <pre name="test">
     *   Kissa sadepilvi = new Kissa();
     *   sadepilvi.taytaKissaTiedoilla();
     *   sadepilvi.getNimi() =R= "Kissakaveri Sadepilvi";
     * </pre>
     */
    public String getNimi() {
        return nimi;
    }
    
    /**
     * Alustetaan kissa. Toistaiseksi ei muuta.
     */
    public Kissa() {
        // Vielä ei mitään
    }
    

    /**
     * Alustetaan tietyn kissan omistaja.  
     * @param omistajantunnusNro omistajan viitenumero 
     */
    public Kissa(int omistajantunnusNro) {
        this.omistajantunnusNro = omistajantunnusNro;
    }


    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot kissalle. 
     * @param omistajanNro viite omistajaan, jonka kissasta on kyse
     * @param arvottuRekNro rekisterinumero joka arvotaan kissalle
     */
    public void taytaKissaTiedoilla (int omistajanNro, String arvottuRekNro) {
        omistajantunnusNro = omistajanNro;
        nimi = "Kissakaveri Sadepilvi"; 
        rekisteriNro = "FI KS NO "+ arvottuRekNro;  
        rotu = "Ragdoll";
        emsKoodi = "RAG n 03";
        vari = "Ruskeanaamio bicolour";
        sukupuoli = "naaras";
        syntymaAika  = "23.05.2019";
        emonNimi = "Kissakaveri Poutapilvi";
        emonRekisteriNro = "FI KS NO 4537249";
        isanNimi = "Kamukisu Rahkasammal";
        isanRekisteriNro = "FI KS NO 7845012";
        myyntipaiva = "31.08.2019";
        viimeisinRokotus = "20.08.2019";
        seuraavaRokotus = "";
        lisatiedot = "Myyntiin";
        
    }
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot kissalle.
     * Rekisterinumero arvotaan, jotta kahdella kissalla ei olisi
     * samoja tietoja.
     */
    public void taytaKissaTiedoilla() {
        String arvottuRekNro = arvoRekNro();
        int omistajanNro = arvoOmistajaNro(); 
        taytaKissaTiedoilla(omistajanNro, arvottuRekNro);
        
    }

    
    
    //TODO: Tee huomautukset-ikkuna, johon tulee teksti tietyn kissan rokotetarpeesta
    //TODO: Tähän liittyen tee kuluvan päiväyksen haku 
    
    /**
     * Tulostetaan kissan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Kissa");
        out.println("Kissan id: " + kissantunnusNro);
        out.println("Omistajan id: " + omistajantunnusNro);
        out.println("Nimi: " + nimi);
        out.println("Rekisterinumero: " + rekisteriNro);
        out.println("Rotu: " + rotu); 
        out.println("EMS-koodi: " + emsKoodi);
        out.println("Väri: " + vari);
        out.println("Sukupuoli: " + sukupuoli); 
        out.println("Syntymäaika: " + syntymaAika); 
        out.println("Emon nimi: " + emonNimi);
        out.println("Emon rekisterinumero: " + emonRekisteriNro);
        out.println("Isän nimi: " + isanNimi); 
        out.println("Isän rekisterinumero: "+ isanRekisteriNro);
        out.println("Myyntipäivä: " + myyntipaiva);
        out.println("Viimeisin rokotus: " + viimeisinRokotus); 
        out.println("Mahdollinen seuraava rokotus: " + seuraavaRokotus );
        out.println("Lisätiedot: " + lisatiedot);
        out.println();
        
    }
    
    /**
     * Tulostetaan kissan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * Antaa kissalle seuraavan tunnusnumeron.
     * @return kissan uusi tunnusNro
     * @example
     * <pre name="test">
     *   Kissa sadepilvi1 = new Kissa();
     *   sadepilvi1.getKissanTunnusNro() === 0;
     *   sadepilvi1.rekisteroi();
     *   Kissa sadepilvi2 = new Kissa();
     *   sadepilvi2.rekisteroi();
     *   int n1 = sadepilvi1.getKissanTunnusNro();
     *   int n2 = sadepilvi2.getKissanTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        kissantunnusNro = seuraavaNro;
        seuraavaNro++;
        return kissantunnusNro;
    }


    /**
     * Palauttaa kissan tunnusnumeron.
     * @return kissan id
     */
    public int getKissanTunnusNro() {
        return kissantunnusNro;
    }
    
    
    /**
     * Asettaa kissan tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param kissanr asetettava tunnusnumero
     */
    private void setKissanTunnusNro(int kissanr) {
        kissantunnusNro = kissanr;
        if (kissantunnusNro >= seuraavaNro) seuraavaNro = kissantunnusNro + 1;
    }
    

    /**
     * Palautetaan mille omistajalle kissa kuuluu
     * @return omistajan id
     */
    public int getOmistajanTunnusNro() {
        return omistajantunnusNro;
    }
    
    
    /**
     * Palauttaa kissan tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return kissa tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Kissa kissa = new Kissa();
     *   kissa.parse("   1  |  1  | Kissakaveri Sadepilvi");
     *   kissa.toString().startsWith("1|1|Kissakaveri Sadepilvi|") === true; // on enemmänkin kuin 3 kenttää, siksi loppu |
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getKissanTunnusNro() + "|" +
                getOmistajanTunnusNro() + "|" +
                nimi + "|" +
                rotu + "|" +
                emsKoodi + "|" +
                vari + "|" +
                rekisteriNro + "|" +
                sukupuoli + "|" +
                syntymaAika + "|" +
                emonNimi + "|" +
                emonRekisteriNro + "|" +
                isanNimi + "|" +
                isanRekisteriNro + "|" +
                myyntipaiva + "|" +
                viimeisinRokotus + "|" +
                seuraavaRokotus + "|" +
                lisatiedot;
    }
    
    
    /**
     * Selvittää kissan tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva kissantunnusNro.
     * @param rivi josta kissan tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Kissa kissa = new Kissa();
     *   kissa.parse("   1  |  1   | Kissakaveri Sadepilvi");
     *   kissa.getKissanTunnusNro() === 1;
     *   kissa.toString().startsWith("1|1|Kissakaveri Sadepilvi|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     *
     *   kissa.rekisteroi();
     *   int n = kissa.getKissanTunnusNro();
     *   kissa.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   kissa.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   kissa.getKissanTunnusNro() === n+20+1;    
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setKissanTunnusNro(Mjonot.erota(sb, '|', getKissanTunnusNro()));
        omistajantunnusNro = Mjonot.erota(sb, '|', getOmistajanTunnusNro());
        nimi = Mjonot.erota(sb, '|', nimi);
        rotu = Mjonot.erota(sb, '|', rotu);
        emsKoodi = Mjonot.erota(sb, '|', emsKoodi);
        vari = Mjonot.erota(sb, '|', vari);
        rekisteriNro = Mjonot.erota(sb, '|', rekisteriNro);
        sukupuoli = Mjonot.erota(sb, '|', sukupuoli);
        syntymaAika = Mjonot.erota(sb, '|', syntymaAika);
        emonNimi = Mjonot.erota(sb, '|', emonNimi);
        emonRekisteriNro = Mjonot.erota(sb, '|', emonRekisteriNro);
        isanNimi = Mjonot.erota(sb, '|', isanNimi);
        isanRekisteriNro = Mjonot.erota(sb, '|', isanRekisteriNro);
        myyntipaiva = Mjonot.erota(sb, '|', myyntipaiva);
        viimeisinRokotus = Mjonot.erota(sb, '|', viimeisinRokotus);
        seuraavaRokotus = Mjonot.erota(sb, '|', seuraavaRokotus);
        lisatiedot = Mjonot.erota(sb, '|', lisatiedot);
    }
    
    
    @Override
    public boolean equals(Object kissa) {
        if ( kissa == null ) return false;
        return this.toString().equals(kissa.toString());
    }


    @Override
    public int hashCode() {
        return kissantunnusNro;
    }


    /**
     * Testiohjelma kissalle
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kissa sadepilvi = new Kissa();
        Kissa sadepilvi2 = new Kissa();
        sadepilvi.rekisteroi();
        sadepilvi2.rekisteroi();
        sadepilvi.tulosta(System.out);
        sadepilvi.taytaKissaTiedoilla(); 
        sadepilvi.tulosta(System.out);

        sadepilvi2.taytaKissaTiedoilla();
        sadepilvi2.tulosta(System.out);
    
    }

}
