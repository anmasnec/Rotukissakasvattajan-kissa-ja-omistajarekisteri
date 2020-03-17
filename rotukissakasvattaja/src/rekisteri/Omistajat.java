package rekisteri;


/**
 * Omistajat, joka osaa mm. lisätä uuden omistajan.
 * Vastuualueet:
 * - Pitää yllä varsinaista kasvattajan rekisteriä omistajien osalta (lisää ja poistaa omistajan)
 * - Lukee ja kirjoittaa omistajat tiedostoon 
 * - Etsii ja lajittelee 
 * Avustajaluokka: Omistaja
 * @author annik
 * @version 16.3.2020
 * 
 *
 */                    
public class Omistajat {
    
    private static final int MAX_OMISTAJIA  = 10;
    private int lkm = 0;
    private Omistaja Omistajaalkiot[] = new Omistaja[MAX_OMISTAJIA];
    private String tiedostonNimi = "";

    /** Lista omistajista */
 // private final Collection<Omistaja> alkiotOmistajat = new ArrayList<Omistaja>();
 //  private final List<Omistaja> alkiotOmistajat = new LinkedList<>();
  // private Omistaja[] alkiotOmistajat = new Omistaja [10];
    /**
     * Oletusmuodostaja: Omistajien alustaminen
     */
    public Omistajat() {
        // toistaiseksi ei tarvitse tehdä mitään
    }


//    /**
//     * Lisää uuden omistajan tietorakenteeseen.  Ottaa omistajan omistukseensa.
//     * @param omistaja lisättävä omistaja.  Huom tietorakenne muuttuu omistajan omistajaksi
//     */
//    public void lisaa(Omistaja omistaja) {
//        alkiotOmistajat.add(omistaja);
//    }

    /**
     * Lisää uuden omistajan tietorakenteeseen. 
     * @param omistaja lisättävän omistajan viite.  
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Omistajat omistajat = new Omistajat();
     * Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja();
     * omistajat.getLkm() === 0;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 1;
     * omistajat.lisaa(sadepilvinen2); omistajat.getLkm() === 2;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 3;
     * omistajat.annaOmistaja(0) === sadepilvinen1;
     * omistajat.annaOmistaja(1) === sadepilvinen2;
     * omistajat.annaOmistaja(2) === sadepilvinen1;
     * omistajat.annaOmistaja(1) == sadepilvinen1 === false;
     * omistajat.annaOmistaja(1) == sadepilvinen2 === true;
     * omistajat.annaOmistaja(3) === sadepilvinen1; #THROWS IndexOutOfBoundsException 
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 4;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 5;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 6;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 7;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 8;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 9;
     * omistajat.lisaa(sadepilvinen1); omistajat.getLkm() === 10;
     * omistajat.lisaa(sadepilvinen1);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Omistaja omistaja) throws SailoException {
        if (lkm >= Omistajaalkiot.length) throw new SailoException("Liikaa alkioita");
        Omistajaalkiot[lkm] = omistaja;
        lkm++;
    }

    
    /**
     * Palauttaa viitteen i:teen omistajaan.
     * @param i monennenko omistajan viite halutaan
     * @return viite omistajaan, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella  
     */
    public Omistaja annaOmistaja(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return Omistajaalkiot[i];
    }

    /**
     * Lukee omistajat tiedostosta. Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + ".omistaja"; // "/omistajat.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }


    /**
     * Tallentaa omistajat tiedostoon. Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        throw new SailoException("Tiedoston tallennus ei onnistu vielä " + tiedostonNimi);
    }


//    /**
//     * Palauttaa rekisterin omistajien lukumäärän
//     * @return omistajien lukumäärä
//     */
//    public int getLkm() {
//        return alkiotOmistajat.size();
//    }
    
    /**
     * Palauttaa rekisterin omistajien lukumäärän
     * @return omistajien lukumäärä
     */
    public int getLkm() {
        return lkm;
    }


//    /**
//     * Iteraattori kaikkien kissan omistajien läpikäymiseen
//     * @return omistajaiteraattori
//     * 
//     * @example
//     * <pre name="test">
//     * #PACKAGEIMPORT
//     * #import java.util.*;
//     *  Omistajat kissanomistajat = new Omistajat();
//     *  Omistaja sadepilvinen21 = new Omistaja(2); kissanomistajat.lisaa(sadepilvinen21);
//     *  Omistaja sadepilvinen11 = new Omistaja(1); kissanomistajat.lisaa(sadepilvinen11);
//     *  Omistaja sadepilvinen22 = new Omistaja(2); kissanomistajat.lisaa(sadepilvinen22);
//     *  Omistaja sadepilvinen12 = new Omistaja(1); kissanomistajat.lisaa(sadepilvinen12);
//     *  Omistaja sadepilvinen23 = new Omistaja(2); kissanomistajat.lisaa(sadepilvinen23);
//     * 
//     *  Iterator<Omistaja> i2=kissanomistajat.iterator();
//     *  i2.next() === sadepilvinen21;
//     *  i2.next() === sadepilvinen11;
//     *  i2.next() === sadepilvinen22;
//     *  i2.next() === sadepilvinen12;
//     *  i2.next() === sadepilvinen23;
//     *  i2.next() === sadepilvinen12;  #THROWS NoSuchElementException  
//     *  
//     *  int n = 0;
//     *  int kissanrot[] = {2,1,2,1,2};
//     *  
//     *  for ( Omistaja omistaja:kissanomistajat ) { 
//     *    omistaja.getKissanTunnusNro() === kissanrot[n]; n++;  
//     *  }
//     *  
//     *  n === 5;
//     *  
//     * </pre>
//     */
//    @Override
//    public Iterator<Omistaja> iterator() {
//        return alkiotOmistajat.iterator();
//    }


//    /**
//     * Haetaan kaikki kissan omistajat
//     * @param kissantunnusnro kissan tunnusnumero jolle omistajia haetaan
//     * @return tietorakenne jossa viiteet löydettyihin omistajiin
//     * @example
//     * <pre name="test">
//     * #import java.util.*;
//     * 
//     *  Omistajat kissafanit = new Omistajat();
//     *  Omistaja sadepilvinen21 = new Omistaja(2); kissafanit.lisaa(sadepilvinen21);
//     *  Omistaja sadepilvinen11 = new Omistaja(1); kissafanit.lisaa(sadepilvinen11);
//     *  Omistaja sadepilvinen22 = new Omistaja(2); kissafanit.lisaa(sadepilvinen22);
//     *  Omistaja sadepilvinen12 = new Omistaja(1); kissafanit.lisaa(sadepilvinen12);
//     *  Omistaja sadepilvinen23 = new Omistaja(2); kissafanit.lisaa(sadepilvinen23);
//     *  Omistaja sadepilvinen51 = new Omistaja(5); kissafanit.lisaa(sadepilvinen51);
//     *  
//     *  List<Omistaja> loytyneet;
//     *  loytyneet = kissafanit.annaOmistajat(3);
//     *  loytyneet.size() === 0; 
//     *  loytyneet = kissafanit.annaOmistajat(1);
//     *  loytyneet.size() === 2; 
//     *  loytyneet.get(0) == sadepilvinen11 === true;
//     *  loytyneet.get(1) == sadepilvinen12 === true;
//     *  loytyneet = kissafanit.annaOmistajat(5);
//     *  loytyneet.size() === 1; 
//     *  loytyneet.get(0) == sadepilvinen51 === true;
//     * </pre> 
//     */
//    public List<Omistaja> annaOmistajat(int kissantunnusnro) {
//        List<Omistaja> loydetyt = new ArrayList<Omistaja>();
//        for (Omistaja omistaja : alkiotOmistajat)
//            if (omistaja.getKissanTunnusNro() == kissantunnusnro) loydetyt.add(omistaja);
//        return loydetyt;
//
//    }
    
    /**
     * Haetaan kaikki kissan omistajat
     * @param kissantunnusnro kissan tunnusnumero jolle omistajia haetaan
     * @return tietorakenne jossa viitteet löydettyihin omistajiin
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Omistajat kissafanit = new Omistajat();
     *  Omistaja sadepilvinen21 = new Omistaja(2); kissafanit.lisaa(sadepilvinen21);
     *  Omistaja sadepilvinen11 = new Omistaja(1); kissafanit.lisaa(sadepilvinen11);
     *  Omistaja sadepilvinen22 = new Omistaja(2); kissafanit.lisaa(sadepilvinen22);
     *  Omistaja sadepilvinen12 = new Omistaja(1); kissafanit.lisaa(sadepilvinen12);
     *  Omistaja sadepilvinen23 = new Omistaja(2); kissafanit.lisaa(sadepilvinen23);
     *  Omistaja sadepilvinen51 = new Omistaja(5); kissafanit.lisaa(sadepilvinen51);
     *  
     *  Omistaja[] loytyneet = new Omistaja[10];
     *  loytyneet = kissafanit.annaOmistajat(1);
     *  loytyneet.length === 2; 
     *  loytyneet[0] == sadepilvinen11 === true;
     *  loytyneet[1] == sadepilvinen12 === true;
     *  loytyneet = kissafanit.annaOmistajat(5);
     *  loytyneet.length === 1; 
     *  loytyneet[0] == sadepilvinen51 === true; 
     * </pre> 
     */
    public Omistaja[] annaOmistajat (int kissantunnusnro) {
        int pituus = pituusIlmanNull(Omistajaalkiot);
       Omistaja [] loydetyt = new Omistaja[pituus];
       int laskin = 0;
       
       for (int i= 0; i < pituus; i++) {
           if (Omistajaalkiot[i].getKissanTunnusNro() == kissantunnusnro) {
               loydetyt [laskin] = Omistajaalkiot[i]; 
               laskin++;
           }           
       }
       if (laskin > 0) {
           Omistaja[] omistajat = new Omistaja[laskin]; 
           for (int i = 0; i < laskin; i++) {
               omistajat[i] = loydetyt[i];
           }
           return omistajat;
       } 
           return null;       
    }
    
    /**
     * Laskee taulukon määriteltyjen alkioiden lukumäärän
     * @param omistajaAlkiot Omistajataulukon alkiot
     * @return montako ei null alkiota
     */
    public static int pituusIlmanNull(Omistaja[] omistajaAlkiot){
        int count = 0;
        for(Omistaja alkio : omistajaAlkiot)
            if (alkio != null)
                ++count;
        return count;
    }
    
    
    

    /**
     * Testiohjelma omistajille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Omistajat kissanomistajat = new Omistajat();
        Omistaja sadepilvinen1 = new Omistaja();
        sadepilvinen1.rekisteroi();
        sadepilvinen1.taytaKissanOmistaja(2);
        Omistaja sadepilvinen2 = new Omistaja();
        sadepilvinen2.rekisteroi();
        sadepilvinen2.taytaKissanOmistaja(1);
        Omistaja sadepilvinen3 = new Omistaja();
        sadepilvinen3.rekisteroi();
        sadepilvinen3.taytaKissanOmistaja(3);
//        Omistaja sadepilvinen4 = new Omistaja();
//        sadepilvinen4.rekisteroi();
//        sadepilvinen4.taytaKissanOmistaja(2);
         

        try {
        kissanomistajat.lisaa(sadepilvinen1);
        kissanomistajat.lisaa(sadepilvinen2);
        kissanomistajat.lisaa(sadepilvinen3);
       // kissanomistajat.lisaa(sadepilvinen4);

        System.out.println("============= Omistajat testi =================");


        Omistaja omistajat1[] = new Omistaja[3];
        
           omistajat1[0] = sadepilvinen1;
           omistajat1[1] = sadepilvinen2;
           omistajat1[2] = sadepilvinen3;
           //omistajat1[3] = sadepilvinen4;
        
//        
        
        
     for   (Omistaja kissanomistaja : omistajat1) {
            System.out.print(kissanomistaja.getKissanTunnusNro() + " ");
            kissanomistaja.tulosta(System.out); 
      }
      
        for (int i = 0; i < kissanomistajat.getLkm(); i++) {
            Omistaja omistaja = kissanomistajat.annaOmistaja(i);
           // System.out.println("Omistaja nro: " + i);
            omistaja.tulosta(System.out);
            //System.out.print(omistaja.getKissanTunnusNro() + " ");
            //omistaja.tulosta(System.out);
        }
        
    }    

    catch (SailoException ex) {
        System.out.println(ex.getMessage());
        }
    

    }
  }

