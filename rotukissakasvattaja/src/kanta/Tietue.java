package kanta;

/**
 * Rajapinta tietueelle johon voidaan taulukon avulla rakentaa 
 * "attribuutit".
 * @author annik
 * @version 7.4.2020
 *
 */
public interface Tietue {
    
    
    /**
     * @return tietueen kenttien lukumäärä
     * @example
     * <pre name="test">
     *   #import rekisteri.Omistaja;
     *   Omistaja omi = new Omistaja();
     *   omi.getKenttia() === 8;
     * </pre>
     */
    public abstract int getKenttia();


    /**
     * @return ensimmäinen käyttäjän syötettävän kentän indeksi
     * @example
     * <pre name="test">
     *   Omistaja omi = new Omistaja();
     *   omi.ekaKentta() === 1;
     * </pre>
     */
    public abstract int ekaKentta();


    /**
     * @param k minkä kentän kysymys halutaan
     * @return valitun kentän kysymysteksti
     * @example
     * <pre name="test">
     *   Omistaja omi = new Omistaja();
     *   omi.getKysymys(2) === "Katuosoite";
     * </pre>
     */
    public abstract String getKysymys(int k);


    /**
     * @param k Minkä kentän sisältö halutaan
     * @return valitun kentän sisältö
     * @example
     * <pre name="test">
     *   Omistaja omi = new Omistaja();
     *   omi.parse("   1   |  Sade Pilvinen  |   Pilvitie 2 B 16 | 97612 | Pilvelä ");
     *   omi.anna(0) === "1";   
     *   omi.anna(1) === "Sade Pilvinen";   
     *   omi.anna(2) === "Pilvitie 2 B 16";   
     *   omi.anna(3) === "97612";   
     *   omi.anna(4) === "Pilvelä";   
     * </pre>
     */
    public abstract String anna(int k);

    
    /**
     * Asetetaan valitun kentän sisältö.  Mikäli asettaminen onnistuu,
     * palautetaan null, muutoin virheteksti.
     * @param k minkä kentän sisältö asetetaan
     * @param s asetettava sisältö merkkijonona
     * @return null jos ok, muuten virheteksti
     * @example
     * <pre name="test">
     *   Omistaja omi = new Omistaja();
     *   omi.aseta(1,"Muru Metsälä") === null;
     *   omi.aseta(3,"96514")  === null;
     * </pre>
     */
    public abstract String aseta(int k, String s);


    /**
     * Tehdään identtinen klooni tietueesta
     * @return kloonattu tietue
     * @throws CloneNotSupportedException jos kloonausta ei tueta
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Omistaja omi = new Omistaja();
     *   omi.parse("   1   |  Sade Pilvinen  |   Pilvitie 2 B 16 | 97612 | Pilvelä ");
     *   Object kopio = omi.clone();
     *   kopio.toString() === omi.toString();
     *   omi.parse("   2   |  Kukka Metsälä  |   Metsäpolku 8  | 26100 | Metsäkylä ");
     *   kopio.toString().equals(omi.toString()) === false;
     *   kopio instanceof Omistaja === true;
     * </pre>
     */
    public abstract Tietue clone() throws CloneNotSupportedException;


    /**
     * Palauttaa tietueen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return tietue tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Omistaja omistaja = new Omistaja();
     *   omistaja.parse("   2   |  Kukka Metsälä  |   Metsäpolku 8  | 26100 | Metsäkylä ");
     *   omistaja.toString()    =R= "2\\|Kukka Metsälä\\|Metsäpolku 8\\|26100\\|Metsäkylä.*";
     * </pre>
     */
    @Override
    public abstract String toString();

}
