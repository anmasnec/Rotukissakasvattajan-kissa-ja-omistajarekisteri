package rekisteri;

import java.io.*;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Tietue;
import static kanta.Syntymaaika.*;

/**
 * Omistaja, joka osaa mm. huolehtia tunnusnumerostaan. 
 * Vastuualueet: 
 * - Tietää omistajien kentät (nimi, osoite ym) 
 * - Osaa muuttaa 1|Sade Pilvinen|Pilvitie|-merkkijonon omistajan tiedoiksi
 * - Osaa antaa merkkijonona i:n kentän tiedoiksi
 * - Osaa laittaa merkkijonon i:neksi kentäksi
 * @author annik
 * @version 20.4.2020
 * 
 */
public class Omistaja implements Cloneable, Tietue {
    
    private int omistajantunnusNro;
    private String omistajanNimi = "";
    private String katuosoite = "";
    private String postinumero = "";
    private String paikkakunta = "";
    private String puhelinnumero = "";
    private String omistajanSyntymaAika = "";
    private String omistajanLisatiedot = "";
    
    private static int seuraavaNro = 1;
    
    /**
     * Alustetaan omistaja
     */
    public Omistaja() {
    }

   
    
    /** 
     * Omistajien vertailija 
     */ 
    public static class Vertailija implements Comparator<Omistaja> { 
        private int k;  
         
        @SuppressWarnings("javadoc") 
        public Vertailija(int k) { 
            this.k = k; 
        } 
         
        @Override 
        public int compare(Omistaja omistaja1, Omistaja omistaja2) { 
            return omistaja1.getAvain(k).compareToIgnoreCase(omistaja2.getAvain(k)); 
        } 
    } 
     
    
    /** 
     * Antaa k:n kentän sisällön merkkijonona 
     * @param k monenenko kentän sisältö palautetaan 
     * @return kentän sisältö merkkijonona 
     */ 
    public String getAvain(int k) { 
        switch ( k ) { 
        case 0: return "" + omistajanNimi.toUpperCase(); 
        case 1: return "" + katuosoite; 
        case 2: return "" + postinumero; 
        case 3: return "" + paikkakunta; 
        case 4: return "" + puhelinnumero; 
        case 5: return "" + omistajanSyntymaAika; 
        case 6: return "" + omistajanLisatiedot; 
        default: return "Tyhjä"; 
        } 
    } 
       
    
    /**
     * Palauttaa omistajan kenttien lukumäärän
     * @return kenttien lukumäärä
     */
    @Override
    public int getKenttia() {
        return 8;
    }


    /**
     * Eka kenttä joka on mielekäs kysyttäväksi
     * @return eknn kentän indeksi
     */
    @Override
    public int ekaKentta() {
        return 1;
    }
    
    
    /**
     * @return omistajan nimi
     * @example
     * <pre name="test">
     *   Omistaja sadepilvinen = new Omistaja();
     *   sadepilvinen.taytaKissanOmistaja();
     *   sadepilvinen.getNimi() =R= "Sade Pilvinen.*";
     * </pre>
     */
    public String getNimi() {
        return omistajanNimi;
    }
     
    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monennenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */
    @Override
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + omistajantunnusNro;
        case 1: return "" + omistajanNimi;
        case 2: return "" + katuosoite;
        case 3: return "" + postinumero;
        case 4: return "" + paikkakunta;
        case 5: return "" + puhelinnumero;
        case 6: return "" + omistajanSyntymaAika;
        case 7: return "" + omistajanLisatiedot;
        default: return "Tyhjä";
        }
    }
    
    
    /**
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     * @return null jos asettaminen onnistuu, muuten vastaava virheilmoitus.
     * @example
     * <pre name="test">
     *   Omistaja omistaja = new Omistaja();
     *   omistaja.aseta(1,"Sade Pilvinen") === null;
     *   omistaja.aseta(4,"Pilvelä") === null;
     * </pre>
     */
    @Override
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch ( k ) {
        case 0:
            setOmistajanTunnusNro(Mjonot.erota(sb, '§', getOmistajanTunnusNro()));
            return null;      
        case 1:
            omistajanNimi = tjono;
            return null;
        case 2:
            katuosoite = tjono;
            return null;
        case 3:
            postinumero = tjono;
            return null;
        case 4:
            paikkakunta = tjono;
            return null;
        case 5:
            puhelinnumero = tjono;
            return null;
        case 6:
            omistajanSyntymaAika = tjono;
            return null;
        case 7:
            omistajanLisatiedot = tjono;
            return null;  
        default:
            return "Tyhjä";
        }
    }
    
    
    /**
     * Palauttaa k:tta omistajan kenttää vastaavan kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
     * @return k:netta kenttää vastaava kysymys
     */
    @Override
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Omistajan Tunnusnro";
        case 1: return "Omistajan nimi";
        case 2: return "Katuosoite";
        case 3: return "Postinumero";
        case 4: return "Paikkakunta";
        case 5: return "Puhnumero";
        case 6: return "Syntymäaika";
        case 7: return "Lisätiedot";
        default: return "Tyhjä";
        }
    }



    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot omistajalle.
     * Omistajan syntymäaika arvotaan, jotta kahdella omistajalla ei olisi
     * samoja tietoja.
     * @param arvottusyntymaAika omistajalle arvottu syntymäaika
     */
    public void taytaKissanOmistaja(String arvottusyntymaAika ) {
        omistajanNimi = "Sade Pilvinen";
        katuosoite = "Pilvitie 2 B 16";
        postinumero = "97612";
        paikkakunta = "Pilvelä";
        puhelinnumero =  "0601456387";
        omistajanSyntymaAika = arvottusyntymaAika;
        omistajanLisatiedot = "";
    }

    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot omistajalle.
     * Syntymäaika arvotaan, jotta kahdella omistajalla ei olisi
     * samoja tietoja.
     */
    public void taytaKissanOmistaja() {
        String arvottusyntymaAika = arvoSyntymaAika();
        taytaKissanOmistaja(arvottusyntymaAika);
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
     * Asettaa omistajalle tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param omistajanr asetettava omistajan tunnusnumero
     */
    public void setOmistajanTunnusNro(int omistajanr) {
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
        
        StringBuffer sb = new StringBuffer("");
        String erotin = "";
        for (int k = 0; k < getKenttia(); k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
        return sb.toString();
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
    
    
    /**
     * Tehdään identtinen klooni omistajasta
     * @return Object kloonattu omistaja
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Omistaja omistaja = new Omistaja();
     *   omistaja.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16");
     *   Omistaja kopio = omistaja.clone();
     *   kopio.toString() === omistaja.toString();
     *   omistaja.parse("   2  |  Kukka Metsälä  | Metsäpolku 8");
     *   kopio.toString().equals(omistaja.toString()) === false;
     * </pre>
     */
    @Override
    public Omistaja clone() throws CloneNotSupportedException {
        Omistaja uusi;
        uusi = (Omistaja) super.clone();
        return uusi;
    }
    
    
    /**
     * Tutkii onko omistajan tiedot samat kuin parametrina tuodun omistajan tiedot
     * @param omistaja omistaja johon verrataan
     * @return true jos kaikki tiedot samat, false muuten
     * @example
     * <pre name="test">
     *   Omistaja omistaja1 = new Omistaja();
     *   omistaja1.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16");
     *   Omistaja omistaja2 = new Omistaja();
     *   omistaja2.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16");
     *   Omistaja omistaja3 = new Omistaja();
     *   omistaja3.parse("   2  |  Kukka Metsälä  | Metsäpolku 8");
     *   
     *   omistaja1.equals(omistaja2) === true;
     *   omistaja2.equals(omistaja1) === true;
     *   omistaja1.equals(omistaja3) === false;
     *   omistaja3.equals(omistaja2) === false;
     * </pre>
     */
    public boolean equals(Omistaja omistaja) {
        if ( omistaja == null ) return false;
        for (int k = 0; k < getKenttia(); k++)
            if ( !anna(k).equals(omistaja.anna(k)) ) return false;
        return true;
    }
    
    @Override
    public boolean equals(Object omistaja) {
          if ( omistaja instanceof Omistaja ) return equals((Omistaja)omistaja);
          return false;
    }


    @Override
    public int hashCode() {
        return omistajantunnusNro;
    }

    /**
     * Testiohjelma omistajalle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Omistaja sadepilvinen = new Omistaja();
        sadepilvinen.rekisteroi();
       
        sadepilvinen.taytaKissanOmistaja(); 
        sadepilvinen.tulosta(System.out);
        
        Omistaja sadepilvinen2 = new Omistaja();
        sadepilvinen2.rekisteroi();
       
        sadepilvinen2.taytaKissanOmistaja(); 
        sadepilvinen2.tulosta(System.out);
    }

}
