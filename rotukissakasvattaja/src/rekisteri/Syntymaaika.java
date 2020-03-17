package rekisteri;

/**
 * Luokka omistajan syntymäajan arpomiseksi
 * @author annik
 * @version 9.3.2020
 *
 */
public class Syntymaaika {
    
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
     * Arvotaan satunnainen syntymäaika   
     * @return satunnainen syntymäaika
     */
    public static String arvoSyntymaAika() {
        String arvottusyntymaAika = String.format("%02d",rand(1,28))   + "." +
        String.format("%02d",rand(1,12))   + "." +
        String.format("%04d",rand(1950,2001));
        return arvottusyntymaAika;        
    }

}
