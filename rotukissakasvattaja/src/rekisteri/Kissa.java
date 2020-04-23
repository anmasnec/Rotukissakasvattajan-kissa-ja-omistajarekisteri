package rekisteri;

import static kanta.RekisterinumeroTarkistus.arvoRekNro;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;
import fi.jyu.mit.ohj2.Mjonot;
import kanta.RekisterinumeroTarkistus;
import kanta.Tietue;

/**
 * Rekisterin kissa, joka mm. osaa huolehtia tunnusnumeroistaan.
 * Vastuualueet:
 * - Tietää kissan kentät (nimi, rek.nro, väri ym)
 * - Osaa muuttaa 1|Kissakaveri Sadepilvi|-merkkijonon| kissan tiedoiksi                               
 * - Osaa antaa merkkijonona i:n kentän tiedot       
 * - Osaa laittaa merkkijonon i:neksi kentäksi      
 * - Osaa ilmoittaa kissasta, jonka seuraava rokotus on pian ajankohtainen  
 * @author annik
 * @version 22.4.2020
 * 
 */
public class Kissa implements Cloneable, Tietue {
    
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
    private static int seuraavaNroOmistajalle = 1;
    
    
    /** 
     * Kissojen vertailija 
     */ 
    public static class Vertailija implements Comparator<Kissa> { 
        private int k;
         
        @SuppressWarnings("javadoc") 
        public Vertailija(int k) { 
            this.k = k; 
        } 
         
        @Override 
        public int compare(Kissa kissa1, Kissa kissa2) {
            return kissa1.getAvain(k).compareToIgnoreCase(kissa2.getAvain(k));
         
        }  
    } 
    

    /** 
     * Antaa k:n kentän sisällön merkkijonona 
     * @param k monennenko kentän sisältö palautetaan 
     * @return kentän sisältö merkkijonona 
     */ 
    public String getAvain(int k) { 
        switch ( k ) { 
        case 0: return "" + kissantunnusNro; 
        case 1: return "" + omistajantunnusNro;
        case 2: return "" + nimi.toUpperCase(); 
        case 3: return "" + rotu;  
        case 4: return "" + emsKoodi; 
        case 5: return "" + vari; 
        case 6: return "" + rekisteriNro; 
        case 7: return "" + sukupuoli; 
        case 8: return "" + syntymaAika; 
        case 9: return "" + emonNimi; 
        case 10: return "" + emonRekisteriNro; 
        case 11: return "" + isanNimi; 
        case 12: return "" + isanRekisteriNro; 
        case 13: return "" + myyntipaiva;
        case 14: return "" + viimeisinRokotus; 
        case 15: return "" + seuraavaRokotus; 
        case 16: return "" + lisatiedot;
        default: return "Tyhjä"; 
        } 
    } 
    
    
    /**
     * Palauttaa kissan kenttien lukumäärän
     * @return kenttien lukumäärä
     */
    @Override
    public int getKenttia() {
        return 17;
    }


    /**
     * Eka kenttä joka on mielekäs kysyttäväksi eli nimi
     * @return ekan kentän indeksi
     */
    @Override
    public int ekaKentta() {
        return 2;
    }
    
    
    /**
     * Alustetaan kissan merkkijono-attribuutti tyhjiksi jonoiksi ja tunnusnro = 0.
     */
    public Kissa() {
    }
    
    
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
  * @return Kissan viimeisin rokotus
  */
 public String getViimeisinRokotus() {
     return viimeisinRokotus;
 }
 
 
 /**
 * @return Kissan seuraava rokotus
 */
public String getSeuraavaRokotus() {
     return seuraavaRokotus;
 }


 /**
  * Antaa k:n kentän sisällön merkkijonona
  * @param k monenenko kentän sisältö palautetaan
  * @return kentän sisältö merkkijonona
  */
 @Override
public String anna(int k) {
     switch ( k ) {
     case 0: return "" + kissantunnusNro;
     case 1: return "" + omistajantunnusNro;
     case 2: return "" + nimi;
     case 3: return "" + rotu;
     case 4: return "" + emsKoodi;
     case 5: return "" + vari;
     case 6: return "" + rekisteriNro;
     case 7: return "" + sukupuoli;
     case 8: return "" + syntymaAika;
     case 9: return "" + emonNimi;
     case 10: return "" + emonRekisteriNro;
     case 11: return "" + isanNimi;
     case 12: return "" + isanRekisteriNro;
     case 13: return "" + myyntipaiva;
     case 14: return "" + viimeisinRokotus;
     case 15: return "" + seuraavaRokotus;
     case 16: return "" + lisatiedot;
     default: return "Tyhjä";
     }
 }
 
 
 /**
  * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
  * @param k kuinka monennen kentän arvo asetetaan
  * @param jono jonoa joka asetetaan kentän arvoksi
  * @return null jos asettaminen onnistuu.
  * @example
  * <pre name="test">
  *   Kissa kissa = new Kissa();
  *   kissa.aseta(1,"Sadepilvi") === null; 
  *   kissa.aseta(7,"naaras") === null;
  *   kissa.aseta(6, "FI KS NO 123456") === "Rekisterinumero liian lyhyt";
  *   kissa.aseta(6, "FI KS NO 1234567") === null;
  *   kissa.aseta(10, "FI KS NO 223456") === "Rekisterinumero liian lyhyt";
  *   kissa.aseta(12, "FI KS NO 323456") === "Rekisterinumero liian lyhyt";
  *   kissa.aseta(10, "FI KS NO 2234567") === null;
  *   kissa.aseta(12, "FI KS NO 3234567") === null;
  * </pre>
  */
 @Override
public String aseta(int k, String jono) {
     String tjono = jono.trim();
     StringBuffer sb = new StringBuffer(tjono);
     switch ( k ) {
     case 0:
         setKissanTunnusNro(Mjonot.erota(sb, '§', getKissanTunnusNro()));
         return null;
     case 1:
         omistajantunnusNro = (Mjonot.erota(sb, '§', getOmistajanTunnusNro()));
         return null;    
     case 2:
         nimi = tjono;
         return null;
     case 3:
         rotu = tjono;
         return null;
     case 4:
         emsKoodi = tjono;
         return null;
     case 5:
         vari = tjono;
         return null;
     case 6:
         RekisterinumeroTarkistus reknro = new RekisterinumeroTarkistus();
         String virhe = reknro.tarkista(jono);
         if ( virhe != null ) return virhe;
         rekisteriNro = tjono;
         return null;
     case 7:
         sukupuoli = tjono;
         return null;
     case 8:
         syntymaAika = tjono;
         return null;
     case 9:
         emonNimi = tjono;
         return null;
     case 10:
         RekisterinumeroTarkistus reknro2 = new RekisterinumeroTarkistus();
         String virhe2 = reknro2.tarkista(jono);
         if ( virhe2 != null ) return virhe2;
         emonRekisteriNro = tjono;
         return null;
     case 11:
         isanNimi = tjono;
         return null;
     case 12:
         RekisterinumeroTarkistus reknro3 = new RekisterinumeroTarkistus();
         String virhe3 = reknro3.tarkista(jono);
         if ( virhe3 != null ) return virhe3;
         isanRekisteriNro = tjono;
         return null;
     case 13:
         myyntipaiva = tjono;
         return null;
     case 14:
         viimeisinRokotus = tjono;
         return null;
     case 15:      
         seuraavaRokotus = tjono;
         return null;
     case 16:
         lisatiedot = Mjonot.erota(sb, '§', lisatiedot);
         return null;   
     default:
         return "Tyhjä";
     }
 }
 
 
 /**
  * Palauttaa k:tta kissan kenttää vastaavan kysymyksen
  * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
  * @return k:netta kenttää vastaava kysymys
  */
 @Override
public String getKysymys(int k) {
     switch ( k ) {
     case 0: return "Kissan Tunnusnro";
     case 1: return "Omistajan Tunnusnro";
     case 2: return "Kissan nimi";
     case 3: return "Rotu";
     case 4: return "EMS-koodi";
     case 5: return "Väri";
     case 6: return "Rekisterinro";
     case 7: return "Sukupuoli";
     case 8: return "Syntymäaika";
     case 9: return "Emon nimi";
     case 10: return "Emon rekisterinro";
     case 11: return "Isän nimi";
     case 12: return "Isän rekisterinro";
     case 13: return "Myyntipäivä";
     case 14: return "Viimeisin rokotus";
     case 15: return "Seuraava rokotus";
     case 16: return "Lisätiedot";
     default: return "Tyhjä";
     }
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
     * @param arvottuRekNro rekisterinumero joka arvotaan kissalle
     */
    public void taytaKissaTiedoilla (String arvottuRekNro) {
        omistajantunnusNro = getOmistajanTunnusNro();
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
        taytaKissaTiedoilla(arvottuRekNro);
        
    }

    
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
    public void setKissanTunnusNro(int kissanr) {
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
     * Asettaa omistajan tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param omistajanr asetettava tunnusnumero
     */
    public void setOmistajanTunnusNro(int omistajanr) {
        omistajantunnusNro = omistajanr;
        if (omistajantunnusNro >= seuraavaNroOmistajalle) seuraavaNroOmistajalle = omistajantunnusNro + 1;
    } 
    
    
    /**
     * Palauttaa kissan tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return kissa tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     Kissa kissa = new Kissa();
//     *   kissa.parse("   1  |  1  | Kissakaveri Sadepilvi");
//     *   kissa.toString().startsWith("1|1|Kissakaveri Sadepilvi|") === true; // on enemmänkin kuin 3 kenttää, siksi loppu |
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
        for (int k = 0; k < getKenttia(); k++)
            aseta(k, Mjonot.erota(sb, '|'));
    }
    
    
    /**
     * Tehdään identtinen klooni kissasta
     * @return Object kloonattu kissa
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Kissa kissa = new Kissa();
     *   kissa.parse("   3  | Kissakaveri Sadepilvi  | Ragdoll");
     *   Kissa kopio = kissa.clone();
     *   kopio.toString() === kissa.toString();
     *   kissa.parse("   4  | Kissakaveri April   | Ragdoll");
     *   kopio.toString().equals(kissa.toString()) === false;
     * </pre>
     */
    @Override
    public Kissa clone() throws CloneNotSupportedException {
        Kissa uusi;
        uusi = (Kissa) super.clone();
        return uusi;
    }
    
    
    /**
     * Tutkii onko kissan tiedot samat kuin parametrina tuodun kissan tiedot
     * @param kissa kissa johon verrataan
     * @return true jos kaikki tiedot samat, false muuten
     * @example
     * <pre name="test">
     *   Kissa kissa1 = new Kissa();
     *   kissa1.parse("   1  |  Kissakaveri Sadepilvi   | Ragdoll");
     *   Kissa kissa2 = new Kissa();
     *   kissa2.parse("   1  |  Kissakaveri Sadepilvi   | Ragdoll");
     *   Kissa kissa3 = new Kissa();
     *   kissa3.parse("   2  |  Kissakaveri Saniainen   | Ragdoll");
     *   
     *   kissa1.equals(kissa2) === true;
     *   kissa2.equals(kissa1) === true;
     *   kissa1.equals(kissa3) === false;
     *   kissa3.equals(kissa2) === false;
     * </pre>
     */
    public boolean equals(Kissa kissa) {
        if ( kissa == null ) return false;
        for (int k = 0; k < getKenttia(); k++)
            if ( !anna(k).equals(kissa.anna(k)) ) return false;
        return true;
    }
        
    
    @Override
    public boolean equals(Object kissa) {
        if ( kissa instanceof Kissa ) return equals((Kissa)kissa);
        return false;
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
