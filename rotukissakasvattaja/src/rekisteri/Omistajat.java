package rekisteri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Omistajat, joka osaa mm. lisätä uuden omistajan.
 * Vastuualueet:
 * - Pitää yllä varsinaista kasvattajan rekisteriä omistajien osalta (lisää ja poistaa omistajan)
 * - Lukee ja kirjoittaa omistajat tiedostoon 
 * - Etsii ja lajittelee 
 * Avustajaluokka: Omistaja
 * @author annik
 * @version 7.4.2020
 * 
 *
 */                    
public class Omistajat implements Iterable <Omistaja> {
    
    private static final int MAX_OMISTAJIA  = 10;
    private boolean muutettu = false;
    private int lkm = 0;
    private String tiedostonPerusNimi = "omistajat";
    private Omistaja omistajaAlkiot[] = new Omistaja[MAX_OMISTAJIA];
    private String kasvattajaNimi = "";


    /**
     * Oletusmuodostaja: Omistajien alustaminen
     */
    public Omistajat() {
    }
    
    /**
     * @return omistajat
     */
    public Omistaja[] getOmistajaAlkiot() {
        return omistajaAlkiot;
    }



    /**
     * Lisää uuden omistajan tietorakenteeseen. 
     * @param omistaja lisättävän omistajan viite.  
     * @example
     * <pre name="test">
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
     * omistajat.lisaa(sadepilvinen1); 
     * </pre>
     */
    public void lisaa(Omistaja omistaja){
        if (lkm >= omistajaAlkiot.length) omistajaAlkiot = 
            Arrays.copyOf(omistajaAlkiot, lkm +10);
        omistajaAlkiot[lkm] = omistaja;
        lkm++;
        muutettu = true;
    }

    
    /**
     * Korvaa omistajan tietorakenteessa.  Ottaa omistajan omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva omistaja.  Jos ei löydy,
     * niin lisätään uutena omistajana.
     * @param omistaja lisättävän omistajan viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Omistajat omistajat = new Omistajat();
     * Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja();
     * sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi();
     * omistajat.getLkm() === 0;
     * omistajat.korvaaTaiLisaa(sadepilvinen1); omistajat.getLkm() === 1;
     * omistajat.korvaaTaiLisaa(sadepilvinen2); omistajat.getLkm() === 2;
     * Omistaja sadepilvinen3 = sadepilvinen1.clone();
     * sadepilvinen3.aseta(3,"kkk");
     * Iterator<Omistaja> it = omistajat.iterator();
     * it.next() == sadepilvinen1 === true;
     * omistajat.korvaaTaiLisaa(sadepilvinen3); omistajat.getLkm() === 2;
     * it = omistajat.iterator();
     * Omistaja o0 = it.next();
     * o0 === sadepilvinen3;
     * o0 == sadepilvinen3 === true;
     * o0 == sadepilvinen1 === false;
     * </pre>
     */
    public void korvaaTaiLisaa(Omistaja omistaja) throws SailoException {
        int id = omistaja.getOmistajanTunnusNro();
        for (int i = 0; i < lkm; i++) {
            if ( omistajaAlkiot[i].getOmistajanTunnusNro() == id ) {
                omistajaAlkiot[i] = omistaja;
                muutettu = true;
                return;
            }
        }
        lisaa(omistaja);
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
        return omistajaAlkiot[i];
    }


    /** 
     * Poistaa omistajan jolla on valittu tunnusnumero  
     * @param id poistettavan omistajan tunnusnumero 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Omistajat omistajat = new Omistajat();
     * Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(), sadepilvinen3 = new Omistaja(); 
     * sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); sadepilvinen3.rekisteroi(); 
     * int id1 = sadepilvinen1.getOmistajanTunnusNro(); 
     * omistajat.lisaa(sadepilvinen1); omistajat.lisaa(sadepilvinen2); omistajat.lisaa(sadepilvinen3); 
     * omistajat.poista(id1+1) === 1; 
     * omistajat.annaId(id1+1) === null; omistajat.getLkm() === 2; 
     * omistajat.poista(id1) === 1; omistajat.getLkm() === 1; 
     * omistajat.poista(id1+3) === 0; omistajat.getLkm() === 1; 
     * </pre> 
     *  
     */ 
    public int poista(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            omistajaAlkiot[i] = omistajaAlkiot[i + 1]; 
        omistajaAlkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    } 
    
  
    /**
     * Lukee omistajat tiedostosta. 
     * @param tied tiedoston perusnimi
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Omistajat omistajat = new Omistajat();
     *  Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja();
     *  sadepilvinen1.taytaKissanOmistaja();
     *  sadepilvinen2.taytaKissanOmistaja();
     *  String hakemisto = "testiKissakaveri";
     *  String tiedNimi = hakemisto+"/omistajat";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  omistajat.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  omistajat.lisaa(sadepilvinen1);
     *  omistajat.lisaa(sadepilvinen2);
     *  omistajat.tallenna();
     *  omistajat = new Omistajat();            // Poistetaan vanhat luomalla uusi
     *  omistajat.lueTiedostosta(tiedNimi);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Omistaja> i = omistajat.iterator();
     *  i.next() === sadepilvinen1;
     *  i.next() === sadepilvinen2;
     *  i.hasNext() === false;
     *  omistajat.lisaa(sadepilvinen2);
     *  omistajat.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            String rivi = "";


            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Omistaja omistaja = new Omistaja();
                omistaja.parse(rivi); 
                lisaa(omistaja);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }


    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    
    
    /**
     * Tallentaa omistajat tiedostoon.  
     * Tiedoston muoto:
     * <pre>
     *1|Sade Pilvinen|Pilvitie 2 B 16|97612|Pilvelä|0601456387|05.06.1991||
     *2|Kukka Metsälä|Metsäpolku 8|26100|Metsäkylä|0608123445|16.11.1984|Toimii itsekin kasvattajana|
     * </pre>
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); 
        ftied.renameTo(fbak); 

        try ( PrintWriter fo = new PrintWriter(new FileWriter(new File(getTiedostonNimi()).getCanonicalPath())) ) {

            Collection<Omistaja> kaikkiOmistajat = new ArrayList<Omistaja>();
            for(int i=0;i<lkm;i++) {
                kaikkiOmistajat.add(omistajaAlkiot[i]);
            }
            for (Omistaja omistaja : kaikkiOmistajat) {
                fo.println(omistaja.toString());
            }

        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }


    /**
     * Palauttaa kasvattajanimen
     * @return Kasvattajanimi merkkijonona
     */
    public String getKasvattajaNimi() {
        return kasvattajaNimi;
    }
    

    
    /**
     * Palauttaa rekisterin omistajien lukumäärän
     * @return omistajien lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }



    /**
     * Luokka omistajien iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Omistajat omistajat = new Omistajat();
     * Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja();
     * sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi();
     *
     * omistajat.lisaa(sadepilvinen1); 
     * omistajat.lisaa(sadepilvinen2); 
     * omistajat.lisaa(sadepilvinen1); 
     * 
     * StringBuffer ids = new StringBuffer(30);
     * for (Omistaja omistaja:omistajat)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+omistaja.getOmistajanTunnusNro());           
     * 
     * String tulos = " " + sadepilvinen1.getOmistajanTunnusNro() + " " + sadepilvinen2.getOmistajanTunnusNro() + " " + sadepilvinen1.getOmistajanTunnusNro();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Omistaja>  i=omistajat.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Omistaja omistaja = i.next();
     *   ids.append(" "+omistaja.getOmistajanTunnusNro());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Omistaja>  i=omistajat.iterator();
     * i.next() == sadepilvinen1  === true;
     * i.next() == sadepilvinen2  === true;
     * i.next() == sadepilvinen1  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     */
    public class OmistajatIterator implements Iterator<Omistaja> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa omistajaa
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä omistajia
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava omistaja
         * @return seuraava omistaja
         * @throws NoSuchElementException jos seuraavaa alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Omistaja next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei ole");
            return annaOmistaja(kohdalla++);
        }


        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("ei poisteta");
        }
    }

    
    
    /**
     * Palautetaan iteraattori omistajistaan.
     * @return omistaja iteraattori
     */
    @Override
    public Iterator<Omistaja> iterator() {
        return new OmistajatIterator();
    }


    /**
     * Haetaan kaikki omistajat
     * @param omistajaId omistajan tunnusnumero
     * @return tietorakenne jossa viiteet löydettyihin omistajiin
     */
    public List<Omistaja> annaOmistajat(int omistajaId) {
        List<Omistaja> loydetyt = new ArrayList<Omistaja>();

        for (int i=0;i<lkm;i++) {
            if (omistajaAlkiot[i].getOmistajanTunnusNro() == omistajaId) loydetyt.add(omistajaAlkiot[i]);
        }

        return loydetyt;

    }
    

    
    /** 
     * Etsii omistajan id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return omistaja jolla etsittävä id tai null 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Omistajat omistajat = new Omistajat(); 
     * Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(), sadepilvinen3 = new Omistaja(); 
     * sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); sadepilvinen3.rekisteroi(); 
     * int id1 = sadepilvinen1.getOmistajanTunnusNro();  
     * omistajat.lisaa(sadepilvinen1); omistajat.lisaa(sadepilvinen2); omistajat.lisaa(sadepilvinen3); 
     * omistajat.annaId(id1  ) == sadepilvinen1 === true; 
     * omistajat.annaId(id1+1) == sadepilvinen2 === true; 
     * omistajat.annaId(id1+2) == sadepilvinen3 === true; 
     * </pre> 
     */ 
    public Omistaja annaId(int id) { 
        for (Omistaja omistaja : this) { 
            if (id == omistaja.getOmistajanTunnusNro()) return omistaja; 
        } 
        return null; 
    } 
//
//
    /** 
     * Etsii omistajan id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen omistajan indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Omistajat omistajat = new Omistajat(); 
     * Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(), sadepilvinen3 = new Omistaja(); 
     * sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); sadepilvinen3.rekisteroi(); 
     * int id1 = sadepilvinen1.getOmistajanTunnusNro(); 
     * omistajat.lisaa(sadepilvinen1); omistajat.lisaa(sadepilvinen2); omistajat.lisaa(sadepilvinen3); 
     * omistajat.etsiId(id1+1) === 1; 
     * omistajat.etsiId(id1+2) === 2; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == omistajaAlkiot[i].getOmistajanTunnusNro()) return i; 
        return -1; 
    }  
    

    /**
     * Testiohjelma omistajille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Omistajat kissanomistajat = new Omistajat();
        Omistaja sadepilvinen1 = new Omistaja();
        sadepilvinen1.rekisteroi();
        sadepilvinen1.taytaKissanOmistaja(); 
        Omistaja sadepilvinen2 = new Omistaja();
        sadepilvinen2.rekisteroi();
        sadepilvinen2.taytaKissanOmistaja(); 
        Omistaja sadepilvinen3 = new Omistaja();
        sadepilvinen3.rekisteroi();
        sadepilvinen3.taytaKissanOmistaja(); 

         

        kissanomistajat.lisaa(sadepilvinen1);
        kissanomistajat.lisaa(sadepilvinen2);
        kissanomistajat.lisaa(sadepilvinen3);
      

        System.out.println("============= Omistajat testi =================");


        for (int i = 0; i < kissanomistajat.getLkm(); i++) {
            Omistaja omistaja = kissanomistajat.annaOmistaja(i);
         
            omistaja.tulosta(System.out);
           
        }
    
    }
  }

