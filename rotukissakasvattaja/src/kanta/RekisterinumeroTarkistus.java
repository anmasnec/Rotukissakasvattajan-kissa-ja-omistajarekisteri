package kanta;

/**
 * Luokka kissan rekisterinumeron oikeellisuuden tarkistamiseksi.
 * (Aiemmin Luokka kissan rekisterinumeron arpomiseksi).
 * @author annik
 * @version 20.4.2020
 *
 */
public class RekisterinumeroTarkistus {
    
      /**
         * Arvotaan satunnainen kokonaisluku välille [ala,yla]
         * @param ala arvonnan alaraja
         * @param yla arvonnan yläraja
         * @return satunnainen luku väliltä [ala,yla]
         */
        public static int rand(int ala, int yla) {
          double n = (yla-ala)*Math.random() + ala;
          return (int)Math.round(n);
        }

        
       /**
        * Arvotaan satunnainen rekisterinumero
     * @return arvotun rekisterinumeron
     */
    public static String arvoRekNro() {
          String arvottuRekNro =  String.format("%07d",rand(1000000, 9999999)); 
           return arvottuRekNro;
       }

    
   
    
    /**Numeroita */
    public static final String NUMEROT = "0123456789";
    
    /**Kirjaimet jotka sallitaan rekisterinumerossa */
    public static final String KIRJAIMET = "FIKSNO ";
    
    /**
     * Tarkistetaan rekisterinumero.
     * @param rekisteriNro joka tutkitaan.
     * @return null jos oikein, muuten virhettä kuvaava teksti
     * TODO tarkistukset kuntoon myös karkausvuodesta.
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * RekisterinumeroTarkistus reknro = new RekisterinumeroTarkistus();
     * reknro.tarkista("FI KS NO 123456") === "Rekisterinumero liian lyhyt";
     * reknro.tarkista("KS") === "Rekisterinumero liian lyhyt";
     * reknro.tarkista("12112121 1234566") === "Alkuosassa saa olla vain kirjaimia F,I,K,S,N,O";
     * reknro.tarkista("FI KS NO 12345678")=== "Rekisterinumero liian pitkä";
     * reknro.tarkista("FI KS NO A234566") === "Loppuosassa saa olla vain numeroita";
     * reknro.tarkista("FI AS NO 1234567") === "Alkuosassa saa olla vain kirjaimia F,I,K,S,N,O";
     * reknro.tarkista("FI KS NO 1234567") === null;
     * reknro.tarkista("FI KS NO 9267435") === null;
     * reknro.tarkista("FI KS NO 6547988") === null;
     * </pre>
     */  
    public String tarkista(String rekisteriNro) {
        int pituus = rekisteriNro.length();
        if ( pituus < 16 ) return "Rekisterinumero liian lyhyt";
        if ( pituus > 16 ) return "Rekisterinumero liian pitkä";
        String kirjaimet = rekisteriNro.substring(0,8);
        if ( !onkoVain(kirjaimet,KIRJAIMET)) return "Alkuosassa saa olla vain kirjaimia F,I,K,S,N,O"; 
        String numerot =rekisteriNro.substring(9,15);
        if ( !onkoVain(numerot,NUMEROT)) return "Loppuosassa saa olla vain numeroita";
        return null;
    }
    
    /**
     * Onko jonossa vain sallittuja merkkejä
     * @param jono      tutkittava jono
     * @param sallitut  merkit joita sallitaan
     * @return true jos vain sallittuja, false muuten
     * @example
     * <pre name="test">
     *   onkoVain("123","12")   === false;
     *   onkoVain("123","1234") === true;
     *   onkoVain("","1234") === true;
     * </pre> 
     */
    public static boolean onkoVain(String jono, String sallitut) {
        for (int i=0; i<jono.length(); i++)
            if ( sallitut.indexOf(jono.charAt(i)) < 0 ) return false;
        return true;
    }
    
    
}

