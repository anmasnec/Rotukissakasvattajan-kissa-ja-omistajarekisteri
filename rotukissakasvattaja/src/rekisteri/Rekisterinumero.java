package rekisteri;



/** Luokka kissan rekisterinumeron arpomiseksi
 * @author annik
 * @version 3.3.2020
 *
 */
public class Rekisterinumero {

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

}
