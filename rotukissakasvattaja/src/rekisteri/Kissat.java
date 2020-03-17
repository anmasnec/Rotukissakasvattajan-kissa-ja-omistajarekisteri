
package rekisteri;

import java.util.*;

/**
 * Rekisterin kissat joka osaa mm. lisätä uuden kissan.
 * Vastuualueet:                                                       
* - Pitää yllä varsinaista kasvattajan rekisteriä kissojen osalta (lisää ja poistaa kissan)                            
* - Lukee ja kirjoittaa kissan tiedostoon                               
* - Etsii ja lajittelee
* Avustajaluokka: Kissa
 * @author annik
 * @version 16.3.2020
 * 
 */
public class Kissat implements Iterable <Kissa> {
    
    private String           tiedostonNimi = "";
  
    
    /** Lista kissoista */
    private final Collection<Kissa> alkiot = new ArrayList<Kissa>();
    
    /**
     * Oletusmuodostaja; kissojen alustaminen
     */
    public Kissat() {
    }

    
    /**
     * Lisää uuden kissan tietorakenteeseen. Ottaa kissan omistukseensa.
     * @param kissa lisättävä kissa
     */
    public void lisaa(Kissa kissa) {
        alkiot.add(kissa);
    }



    /**
     * Palauttaa viitteen i:teen kissaan.
     * @param i monennenko kissan viite halutaan
     * @return viite kissaan, jonka indeksi on i 
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella 
     */
    public Kissa anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || getLkm() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
       // return alkiot[i];
        return ((ArrayList<Kissa>) alkiot).get(i);
    }


    /**
     * Lukee kissat tiedostosta.  Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + "/kissat.dat"; //+ ".kissa";
        throw new SailoException("Tiedoston luku ei vielä onnistu " + tiedostonNimi);
    }


    /**
     * Tallentaa kissat tiedostoon.  Kesken.
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna() throws SailoException {
        throw new SailoException("Tiedoston tallennus ei vielä onnistu " + tiedostonNimi);
    }


    /**
     * Palauttaa rekisterin kissojen lukumäärän
     * @return kissojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * Iteraattori kaikkien kissojen läpikäymiseen
     * @return kissaiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Kissat maukujat = new Kissat();
     *  Kissa sadepilvi21 = new Kissa(2); maukujat.lisaa(sadepilvi21);
     *  Kissa sadepilvi11 = new Kissa(1); maukujat.lisaa(sadepilvi11);
     *  Kissa sadepilvi22 = new Kissa(2); maukujat.lisaa(sadepilvi22);
     *  Kissa sadepilvi12 = new Kissa(1); maukujat.lisaa(sadepilvi12);
     *  Kissa sadepilvi23 = new Kissa(2); maukujat.lisaa(sadepilvi23);
     * 
     *  Iterator<Kissa> i2=maukujat.iterator();
     *  i2.next() === sadepilvi21;
     *  i2.next() === sadepilvi11;
     *  i2.next() === sadepilvi22;
     *  i2.next() === sadepilvi12;
     *  i2.next() === sadepilvi23;
     *  i2.next() === sadepilvi12;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int omistajanrot[] = {2,1,2,1,2};
     *  
     *  for ( Kissa kissa:maukujat ) { 
     *    kissa.getOmistajanTunnusNro() === omistajanrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    @Override
    public Iterator<Kissa> iterator() {
        return alkiot.iterator();
    }
    
    /**
     * Haetaan kaikki omistajan kissat
     * @param omistajantunnusnro omistajan tunnusnumero jolle kissoja haetaan
     * @return tietorakenne jossa viitteet löydettyihin kissoihin
     */
    public List<Kissa> annaKissat(int omistajantunnusnro) {
        List<Kissa> loydetyt = new ArrayList<Kissa>();
        for (Kissa kissa : alkiot)
            if (kissa.getOmistajanTunnusNro() == omistajantunnusnro) loydetyt.add(kissa);
        return loydetyt;
    }

    /**
     * Testiohjelma kissoille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Kissat kissat = new Kissat();

        Kissa sadepilvi = new Kissa();
        Kissa sadepilvi2 = new Kissa();
        sadepilvi.rekisteroi();
        sadepilvi.taytaKissaTiedoilla();
        sadepilvi2.rekisteroi();
        sadepilvi2.taytaKissaTiedoilla();

      
        kissat.lisaa(sadepilvi);
        kissat.lisaa(sadepilvi2);

        System.out.println("============= Kissat testi =================");

        List<Kissa> kissat1 = kissat.annaKissat(2);

        for (Kissa kissa : kissat1) {
            System.out.print(kissa.getOmistajanTunnusNro() + " ");
            kissa.tulosta(System.out);
        }     
            
        for (int i = 0; i < kissat.getLkm(); i++) {
            Kissa kissa = kissat.anna(i);
           // System.out.println("Kissa nro: " + i);
            kissa.tulosta(System.out);
        }
       
    }

}
