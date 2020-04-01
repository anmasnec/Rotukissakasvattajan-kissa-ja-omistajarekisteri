package rekisteri;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

import static rekisteri.Syntymaaika.*;

/**
 * Omistaja, joka osaa mm. huolehtia tunnusnumerostaan. 
 * Vastuualueet: 
 * - Tietää omistajien kentät (nimi, osoite ym) 
 * - Osaa muuttaa 1|Sade Pilvinen|Pilvitie|-merkkijonon omistajan tiedoiksi
 * - Osaa antaa merkkijonona i:n kentän tiedoiksi
 * - Osaa laittaa merkkijonon i:neksi kentäksi
 * @author annik
 * @version 1.4.2020
 * 
 */
public class Omistaja {
    
    private int omistajantunnusNro;
    private int kissantunnusNro;
    private String omistajanNimi = "";
    private String katuosoite = "";
    private int postinumero;
    private String paikkakunta = "";
    private String puhelinnumero = "";
    private String omistajanSyntymaAika = "";
    private String omistajanLisatiedot = "";
    
    private static int seuraavaNro = 1;
    
    /**
     * @return omistajan nimi
     * @example
     * <pre name="test">
     *   Omistaja sadepilvinen = new Omistaja();
     *   sadepilvinen.taytaKissanOmistaja(1);
     *   sadepilvinen.getNimi() =R= "Sade Pilvinen.*";
     * </pre>
     */
    public String getNimi() {
        return omistajanNimi;
    }
    
    /**
     * Alustetaan omistaja. Toistaiseksi ei muuta
     */
    public Omistaja() {
        // Vielä ei mitään
    }


    /**
     * Alustetaan tietyn omistajan kissa  
     * @param kissantunnusNro kissan viitenumero 
     */
    public Omistaja(int kissantunnusNro) {
        this.kissantunnusNro = kissantunnusNro;
    }


    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot Omistajalle.
     * Omistajan syntymäaika arvotaan, jotta kahdella omistajalla ei olisi
     * samoja tietoja.
     * @param kissanNro viite kissaan, jonka omistajasta on kyse
     * @param arvottusyntymaAika omistajalle arvottu syntymäaika
     */
    public void taytaKissanOmistaja(int kissanNro, String arvottusyntymaAika ) {
        kissantunnusNro = kissanNro;
        omistajanNimi = "Sade Pilvinen";
        katuosoite = "Pilvitie 2 B 16";
        postinumero = 97612;
        paikkakunta = "Pilvelä";
        puhelinnumero =  "0601456387";
        omistajanSyntymaAika = arvottusyntymaAika;
        omistajanLisatiedot = "";
    }

    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot omistajalle.
     * Syntymäaika arvotaan, jotta kahdella omistajalla ei olisi
     * samoja tietoja.
     * @param kissanNro viite kissaan, jonka omistajasta on kyse
     */
    public void taytaKissanOmistaja(int kissanNro) {
        String arvottusyntymaAika = arvoSyntymaAika();
        taytaKissanOmistaja(kissanNro, arvottusyntymaAika);
    }


    /**
     * Tulostetaan omistajan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Omistaja"); 
        out.println("Omistajan id: " + omistajantunnusNro); 
        out.println("Omistajan nimi: " + omistajanNimi);
        out.println("Katuosoite: " + katuosoite);
        out.println("Postinumero: " + postinumero);
        out.println("Paikkakunta: " + paikkakunta);
        out.println("Puhelinnumero: " + puhelinnumero);
        out.println("Omistajan syntymäaika: " + omistajanSyntymaAika);
        out.println("Lisätiedot: " + omistajanLisatiedot);
        out.println();
    }


    /**
     * Tulostetaan omistajan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * Antaa omistajalle seuraavan tunnusnumeron.
     * @return omistajan uusi tunnusnro
     * @example
     * <pre name="test">
     *   Omistaja sadepilvinen = new Omistaja();
     *   sadepilvinen.getOmistajanTunnusNro() === 0;
     *   sadepilvinen.rekisteroi();
     *   Omistaja sadepilvinen2 = new Omistaja();
     *   sadepilvinen2.rekisteroi();
     *   int n1 = sadepilvinen.getOmistajanTunnusNro();
     *   int n2 = sadepilvinen2.getOmistajanTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        omistajantunnusNro = seuraavaNro;
        seuraavaNro++;
        return omistajantunnusNro;
    }


    /**
     * Palautetaan omistajan oma id
     * @return omistajan id
     */
    public int getOmistajanTunnusNro() {
        return omistajantunnusNro;
    }
    
    /**
     * Palauttaa kissan tunnusnumeron.
     * @return kissan id
     */
    public int getKissanTunnusNro() {
        return kissantunnusNro;
    }

    
    /**
     * Asettaa omistajalle tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param omistajanr asetettava omistajan tunnusnumero
     */
    private void setOmistajanTunnusNro(int omistajanr) {
        omistajantunnusNro = omistajanr;
        if (omistajantunnusNro >= seuraavaNro) seuraavaNro = omistajantunnusNro + 1;
    }
    

    /**
     * Palauttaa omistajan tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return omistaja tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Omistaja omistaja = new Omistaja();
     *   omistaja.parse("   1  |  Sade Pilvinen  | Pilvitie 2 B 16");
     *   omistaja.toString().startsWith("1|Sade Pilvinen|Pilvitie 2 B 16|") === true; // on enemmänkin kuin 3 kenttää, siksi loppu |
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getOmistajanTunnusNro() + "|" +
                omistajanNimi + "|" +
                katuosoite + "|" +
                postinumero + "|" +
                paikkakunta + "|" +
                puhelinnumero + "|" +
                omistajanSyntymaAika + "|" +
                omistajanLisatiedot;
    }


    /**
     * Selvittää omistajan tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta omistajan tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Omistaja omistaja = new Omistaja();
     *   omistaja.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16");
     *   omistaja.getOmistajanTunnusNro() === 1;
     *   omistaja.toString().startsWith("1|Sade Pilvinen|Pilvitie 2 B 16|") === true; // on enemmänkin kuin 3 kenttää, siksi loppu |
     *
     *   omistaja.rekisteroi();
     *   int n = omistaja.getOmistajanTunnusNro();
     *   omistaja.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   omistaja.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   omistaja.getOmistajanTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setOmistajanTunnusNro(Mjonot.erota(sb, '|', getOmistajanTunnusNro()));
        omistajanNimi = Mjonot.erota(sb, '|', omistajanNimi);
        katuosoite = Mjonot.erota(sb, '|', katuosoite);
        postinumero = Mjonot.erota(sb, '|', postinumero);
        paikkakunta = Mjonot.erota(sb, '|', paikkakunta);
        puhelinnumero = Mjonot.erota(sb, '|', puhelinnumero);
        omistajanSyntymaAika = Mjonot.erota(sb, '|', omistajanSyntymaAika);
        omistajanLisatiedot = Mjonot.erota(sb, '|', omistajanLisatiedot);
    }
    
    
    @Override
    public boolean equals(Object omistaja) {
        if ( omistaja == null ) return false;
        return this.toString().equals(omistaja.toString());
    }


    @Override
    public int hashCode() {
        return omistajantunnusNro;
    }

    /**
     * Testiohjelma Omistajalle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Omistaja sadepilvinen = new Omistaja();
        sadepilvinen.rekisteroi();
       
        sadepilvinen.taytaKissanOmistaja(1);
        sadepilvinen.tulosta(System.out);
        
        Omistaja sadepilvinen2 = new Omistaja();
        sadepilvinen2.rekisteroi();
       
        sadepilvinen2.taytaKissanOmistaja(2);
        sadepilvinen2.tulosta(System.out);
    }

    

}
