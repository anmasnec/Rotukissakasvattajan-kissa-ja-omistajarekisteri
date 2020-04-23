package kanta;


/**
 * Luokka omistajanumeron arpomiseksi
 * @author annik
 * @version 12.4.2020
 *
 */
public class Omistajanumero {
    
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
    * Arvotaan satunnainen omistajanumero
 * @return arvotun omistajanumeron
 */
public static int arvoOmistajaNro() {
      int arvottuOmistajaNro =  rand(1, 500); 
       return arvottuOmistajaNro;
   }

}
