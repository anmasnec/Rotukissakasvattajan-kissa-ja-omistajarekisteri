package rekisteri;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Rekisteri-luokka, joka huolehtii kissoista. Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" kissoihin.
 * Vastuualueet:
 * - Huolehtii Kissat- ja Omistajat-luokkien välisestä yhteistyöstä ja välittää näitä tietoja pyydettäessä
 * - Lukee ja kirjoittaa rekisterin tiedostoon pyytämällä apua avustajilta
 * Avustajaluokat: Kissa, Omistaja, Kissat, Omistajat
 * @author annik
 * @version 22.4.2020
 *
 */
public class Rekisteri {
    private Kissat kissat = new Kissat();
    private Omistajat omistajat = new Omistajat();
    
    /** Hakee kaikki kissat
     * @return Kaikki kissat
     */
    public Kissat getKissat() {
        return kissat;
    }


    /**
     * Palauttaa rekisterin kissamäärän
     * @return kissamäärä
     */
    public int getKissoja() {
        return kissat.getLkm();
    }
    
    
    /**
     * Palauttaa rekisterin omistajamäärän
     * @return omistajamäärä
     */
    public int getOmistajia() {
        return omistajat.getLkm();
    }
    
    /**
     * Hakee kaikki omistajat
     * @return omistajat
     */
    public Omistajat getOmistajat() {
        return omistajat;
    }
    
    /**
     * Hakee omistajan id-numeron perusteella
     * @param id omistajan id
     * @return omistajan id-numeron mukaan
     */
    public Omistaja getOmistajaById(int id) {
        return omistajat.annaId(id);
    }
 
    
    /**
     * Poistaa rekisteristä kissan tiedot 
     * @param kissa kissa joka poistetaan
     * @return montako kissaa poistettiin
     * @example
     * <pre name="test">
     * #THROWS Exception
     *  Rekisteri rekisteri = new Rekisteri();
     *  Kissa sadepilvi1 = new Kissa();
     *  Kissa sadepilvi2 = new Kissa();
     *  Kissa sadepilvi3 = new Kissa();
     *  sadepilvi1.setKissanTunnusNro(1);
     *  sadepilvi2.setKissanTunnusNro(2);
     *  sadepilvi3.setKissanTunnusNro(3);
     *  rekisteri.lisaa(sadepilvi1);
     *  rekisteri.lisaa(sadepilvi2);
     *  rekisteri.lisaa(sadepilvi3);
     *  rekisteri.etsiKissat("*",0).size() === 3;
     *  rekisteri.poista(sadepilvi1) === 1;
     *  rekisteri.etsiKissat("*",0).size() === 2;
     *  rekisteri.poista(sadepilvi2) === 1;
     *  rekisteri.etsiKissat("*",0).size() === 1;
     * </pre>
     */
    public int poista(Kissa kissa) {
        if ( kissa == null ) return 0;
        int ret = kissat.poista(kissa.getKissanTunnusNro()); 
        return ret; 
    }

   
    /**
     * Poistaa rekisteristä omistajan tiedot 
     * @param omistaja omistaja joka poistetaan
     * @return montako omistajaa poistettiin
     * * @example
     * <pre name="test">
     * #THROWS Exception
     *   Rekisteri rekisteri = new Rekisteri();
     *   Kissa sadepilvi = new Kissa();
     *   Omistaja sadepilvinen = new Omistaja();
     *   sadepilvi.setOmistajanTunnusNro(1);
     *   sadepilvi.setKissanTunnusNro(1);
     *   sadepilvinen.setOmistajanTunnusNro(1);
     *   rekisteri.lisaa(sadepilvi);
     *   rekisteri.lisaa(sadepilvinen);
     *   rekisteri.annaOmistajat(sadepilvi).size() === 1;
     *   rekisteri.poista(sadepilvinen) === 1;
     *   rekisteri.annaOmistajat(sadepilvi).size() === 0;
     * </pre>
     */ 
    public int poista(Omistaja omistaja) { 
        if ( omistaja == null ) return 0;
        int ret = omistajat.poista(omistaja.getOmistajanTunnusNro()); 
        return ret; 
    
    }
    
    
    /**
     * Lisätään uusi kissa rekisteriin
     * @param kissa lisättävä kissa
     * @throws SailoException jos tulee ongelmia
     */
    public void lisaa(Kissa kissa) throws SailoException  {
        kissat.lisaa(kissa);
    }

    
    /** 
     * Korvaa kissan tietorakenteessa.  Ottaa kissan omistukseensa. 
     * Etsitään samalla tunnusnumerolla oleva kissa.  Jos ei löydy, 
     * niin lisätään uutena kissana. 
     * @param kissa lisättävän kissan viite.  Huom tietorakenne muuttuu omistajaksi 
     * @throws SailoException jos tietorakenne on jo täynnä 
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  Rekisteri rekisteri = new Rekisteri();
     *  Kissa sadepilvi1 = new Kissa();
     *  Kissa sadepilvi2 = new Kissa();
     *  Kissa sadepilvi3 = new Kissa();
     *  sadepilvi1.setKissanTunnusNro(1);
     *  sadepilvi2.setKissanTunnusNro(2);
     *  sadepilvi3.setKissanTunnusNro(3);
     *  rekisteri.lisaa(sadepilvi1);
     *  rekisteri.lisaa(sadepilvi2);
     *  rekisteri.lisaa(sadepilvi3);
     *  rekisteri.etsiKissat("*",0).size() === 3;
     *  rekisteri.korvaaTaiLisaa(sadepilvi1);
     *  rekisteri.etsiKissat("*",0).size() === 3;
     * </pre> 
     */ 
    public void korvaaTaiLisaa(Kissa kissa) throws SailoException { 
        kissat.korvaaTaiLisaa(kissa); 
    } 

    
    /** 
     * Korvaa omistajan tietorakenteessa.  Ottaa omistajan omistukseensa. 
     * Etsitään samalla tunnusnumerolla oleva omistaja.  Jos ei löydy, 
     * niin lisätään uutena omistajana. 
     * @param omistaja lisättävän omistajan viite.  Huom tietorakenne muuttuu omistajaksi 
     * @throws SailoException jos tietorakenne on jo täynnä 
     * * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  Rekisteri rekisteri = new Rekisteri();
     *  Kissa sadepilvi = new Kissa();
     *  Omistaja sadepilvinen = new Omistaja();
     *  sadepilvi.setOmistajanTunnusNro(1);
     *  sadepilvi.setKissanTunnusNro(1);
     *  sadepilvinen.setOmistajanTunnusNro(1);
     *  rekisteri.lisaa(sadepilvi);
     *  rekisteri.lisaa(sadepilvinen);
     *  rekisteri.annaOmistajat(sadepilvi).size() === 1;
     *  rekisteri.korvaaTaiLisaa(sadepilvinen);
     *  rekisteri.annaOmistajat(sadepilvi).size() === 1;
     * </pre> 
     */ 
    public void korvaaTaiLisaa(Omistaja omistaja) throws SailoException { 
        omistajat.korvaaTaiLisaa(omistaja); 
    } 
    
    /**
     * Lisää rekisteriin uuden omistajan
     * @param omistaja lisättävä omistaja
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Rekisteri rekisteri = new Rekisteri();
     * Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja();
     * sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi();
     * rekisteri.getOmistajia() === 0;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 1;
     * rekisteri.lisaa(sadepilvinen2); rekisteri.getOmistajia() === 2;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 3;
     * rekisteri.getOmistajia() === 3;
     * rekisteri.annaOmistaja(0) === sadepilvinen1;
     * rekisteri.annaOmistaja(1) === sadepilvinen2;
     * rekisteri.annaOmistaja(2) === sadepilvinen1;
     * rekisteri.annaOmistaja(3) === sadepilvinen1; #THROWS IndexOutOfBoundsException 
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 4;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 5;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 6;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 7;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 8;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 9;
     * rekisteri.lisaa(sadepilvinen1); rekisteri.getOmistajia() === 10;
     * rekisteri.lisaa(sadepilvinen1);           
     * </pre>
     */
    public void lisaa(Omistaja omistaja) throws SailoException  {
        omistajat.lisaa(omistaja);
    }

    
    /**
     * Palauttaa i:n kissan
     * @param i monesko kissa palautetaan
     * @return viite i:teen kissaan
     */
    public Kissa annaKissa(int i) {
        return kissat.anna(i);
    }

    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien kissojen viitteet 
     * @param hakuehto hakuehto  
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä kissoista
     * @throws SailoException Jos jotakin menee väärin
     */ 
    public Collection<Kissa> etsiKissat(String hakuehto, int k) throws SailoException { 
        return kissat.etsi(hakuehto, k); 
    } 
        
     
    /**
     * Haetaan kaikki omistajan kissat
     * @param omistaja omistaja jonka kissoja haetaan
     * @return tietorakenne jossa viiteet löydettyihin kissoihin
     * @throws SailoException jos tulee ongelmia
     * @example
     * <pre name="test">
     * #import java.util.*;
     * #THROWS SailoException
     * try {
     *  Rekisteri rekisteri = new Rekisteri();
     *  Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(), sadepilvinen3 = new Omistaja();
     *  sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); sadepilvinen3.rekisteroi();
     *  rekisteri.lisaa(sadepilvinen1); rekisteri.lisaa(sadepilvinen2); rekisteri.lisaa(sadepilvinen3);
     *  int id1 = sadepilvinen1.getOmistajanTunnusNro();
     *  int id2 = sadepilvinen2.getOmistajanTunnusNro();
     *  Kissa sadepilvi11 = new Kissa(id1); rekisteri.lisaa(sadepilvi11);
     *  Kissa sadepilvi12 = new Kissa(id1); rekisteri.lisaa(sadepilvi12);
     *  Kissa sadepilvi21 = new Kissa(id2); rekisteri.lisaa(sadepilvi21);
     *  Kissa sadepilvi22 = new Kissa(id2); rekisteri.lisaa(sadepilvi22);
     *  Kissa sadepilvi23 = new Kissa(id2); rekisteri.lisaa(sadepilvi23);
     *  
     *  List<Kissa> loytyneetKissat;
     *  loytyneetKissat = rekisteri.annaKissat(sadepilvinen3);
     *  loytyneetKissat.size() === 0; 
     *  loytyneetKissat = rekisteri.annaKissat(sadepilvinen1);
     *  loytyneetKissat.size() === 2; 
     *  loytyneetKissat.get(0) == sadepilvi11 === true;
     *  loytyneetKissat.get(1) == sadepilvi12 === true;
     *  loytyneetKissat = rekisteri.annaKissat(sadepilvinen2);
     *  loytyneetKissat.size() === 3; 
     *  loytyneetKissat.get(0) == sadepilvi21 === true;
     *  } catch ( Exception e) {
 *       System.err.println(e.getMessage());
 *    }
     * </pre> 
     */
     
    public List<Kissa> annaKissat(Omistaja omistaja) throws SailoException {
        return kissat.annaKissat(omistaja.getOmistajanTunnusNro());
    }

    
    /**
     * Haetaan kaikki omistajat. Parametrin kissalle annetaan omistajan id, jotta osaa lukea kissat.dat-tiedostosta omistajan id-numeron
     * @param kissa kissa jolle omistajia haetaan
     * @return tietorakenne jossa viitteet löydettyihin omistajiin
     * @throws SailoException jos tulee ongelmia
     */
    public List<Omistaja> annaOmistajat(Kissa kissa) throws SailoException {
        return omistajat.annaOmistajat(kissa.getOmistajanTunnusNro());
    }
    
    
    /**
     * Palauttaa i:n omistajan
     * @param i monesko omistaja palautetaan
     * @return viite i:teen omistajaan
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Omistaja annaOmistaja(int i) throws IndexOutOfBoundsException {
        return omistajat.annaOmistaja(i);
    }

    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        kissat.setTiedostonPerusNimi(hakemistonNimi + "kissat");
        omistajat.setTiedostonPerusNimi(hakemistonNimi + "omistajat");
    }
    
            
    /**
     * Lukee rekisterin tiedot tiedostosta
     * @param nimi jota käytetään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * #import rekisteri.SailoException;
     *  String hakemisto = "testiKissakaveri";
     *  File dir = new File(hakemisto);
     *  File ftied  = new File(hakemisto+"/kissat.dat");
     *  File fhtied = new File(hakemisto+"/omistajat.dat");
     *  dir.mkdir();
     *  ftied.delete();
     *  fhtied.delete();
     *  Rekisteri rekisteri = new Rekisteri(); // tiedostoja ei ole, tulee poikkeus
     *  rekisteri.lueTiedostosta(hakemisto); #THROWS SailoException
     *  rekisteri = new Rekisteri();
     *  Kissa sadepilvi1 = new Kissa();
     *  Kissa sadepilvi2 = new Kissa();
     *  Kissa sadepilvi3 = new Kissa();
     *  Omistaja sadepilvinen11 = new Omistaja();
     *  Omistaja sadepilvinen21 = new Omistaja();
     *  Omistaja sadepilvinen31 = new Omistaja();
     *  sadepilvi1.setOmistajanTunnusNro(1);
     *  sadepilvi2.setOmistajanTunnusNro(2);
     *  sadepilvi3.setOmistajanTunnusNro(3);
     *  sadepilvi1.setKissanTunnusNro(1);
     *  sadepilvi2.setKissanTunnusNro(2);
     *  sadepilvi3.setKissanTunnusNro(3);
     *  sadepilvinen11.setOmistajanTunnusNro(1);
     *  sadepilvinen21.setOmistajanTunnusNro(2);
     *  sadepilvinen31.setOmistajanTunnusNro(3);
     *  rekisteri.lisaa(sadepilvi1);
     *  rekisteri.lisaa(sadepilvi2);
     *  rekisteri.lisaa(sadepilvi3);
     *  rekisteri.lisaa(sadepilvinen11);
     *  rekisteri.lisaa(sadepilvinen21);
     *  rekisteri.lisaa(sadepilvinen31);
     *  rekisteri.setTiedosto(hakemisto); // nimi annettava koska uusi poisti sen
     *  rekisteri.tallenna(); 
     *  rekisteri = new Rekisteri();
     *  rekisteri.lueTiedostosta(hakemisto);
     *  Collection<Kissa> kaikki = rekisteri.etsiKissat("",-1); 
     *  Iterator<Kissa> it = kaikki.iterator();
     *  it.next() === sadepilvi1;
     *  it.next() === sadepilvi2;
     *  it.next() === sadepilvi3;
     *  it.hasNext() === false;
     *  List<Omistaja> loytyneet = rekisteri.annaOmistajat(sadepilvi1);
     *  Iterator<Omistaja> io = loytyneet.iterator();
     *  io.next() === sadepilvinen11;
     *  io.hasNext() === false;
     *  loytyneet = rekisteri.annaOmistajat(sadepilvi2);
     *  io = loytyneet.iterator();
     *  io.next() === sadepilvinen21;
     *  io.hasNext() === false;
     *  rekisteri.lisaa(sadepilvi3);
     *  rekisteri.lisaa(sadepilvinen31);
     *  rekisteri.tallenna(); // tekee molemmista .bak
     *  ftied.delete()  === true;
     *  fhtied.delete() === true;
     *  File fbak = new File(hakemisto+"/kissat.bak");
     *  File fhbak = new File(hakemisto+"/omistajat.bak");
     *  fbak.delete() === true;
     *  fhbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kissat = new Kissat(); 
        omistajat = new Omistajat();

        setTiedosto(nimi);
        kissat.lueTiedostosta();
        omistajat.lueTiedostosta();
    }

    
    /**
     * Tallentaa rekisterin tiedot tiedostoon  
     * Vaikka kissojen tallettaminen epäonistuisi, niin yritetään silti tallentaa
     * omistajia ennen poikkeuksen heittämistä.
     * @throws SailoException jos tallentamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            kissat.tallenna();
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }

        try {
            omistajat.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        if ( !"".equals(virhe) ) throw new SailoException(virhe);
    }


    /**
     * Testiohjelma rekisteristä
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Rekisteri rekisteri = new Rekisteri();

        try {
           
            Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa();
            sadepilvi1.rekisteroi();
           
            sadepilvi1.taytaKissaTiedoilla();
            sadepilvi2.rekisteroi();
           
            sadepilvi2.taytaKissaTiedoilla();
 
            rekisteri.lisaa(sadepilvi1);
            rekisteri.lisaa(sadepilvi2);

            Omistaja sadepilvinen11 = new Omistaja(); sadepilvinen11.taytaKissanOmistaja(); rekisteri.lisaa(sadepilvinen11); 
            Omistaja sadepilvinen12 = new Omistaja(); sadepilvinen11.taytaKissanOmistaja(); rekisteri.lisaa(sadepilvinen12); 
            Omistaja sadepilvinen21 = new Omistaja(); sadepilvinen21.taytaKissanOmistaja(); rekisteri.lisaa(sadepilvinen21);
            Omistaja sadepilvinen22 = new Omistaja(); sadepilvinen22.taytaKissanOmistaja(); rekisteri.lisaa(sadepilvinen22);
            Omistaja sadepilvinen23 = new Omistaja(); sadepilvinen23.taytaKissanOmistaja(); rekisteri.lisaa(sadepilvinen23);
            
        
          int id3 = sadepilvinen11.getOmistajanTunnusNro();
          Kissa sadepilvi3 = new Kissa(id3); sadepilvi3.taytaKissaTiedoilla(); rekisteri.lisaa(sadepilvi3);
    

            System.out.println("============= Rekisterin testi =================");

            for (int i = 0; i < rekisteri.getKissoja(); i++) {
                Kissa kissa = rekisteri.annaKissa(i);
              
                kissa.tulosta(System.out);
                List<Kissa> loytyneetKissat = rekisteri.annaKissat(sadepilvinen23);
                for (Kissa katti : loytyneetKissat)
                    katti.tulosta(System.out);
            }    
            
            Collection<Kissa> kissat = rekisteri.etsiKissat("", -1);
           
            for (Kissa kissa: kissat) {
                kissa.tulosta(System.out);
                List<Omistaja> loytyneet = rekisteri.annaOmistajat(kissa);
                for (Omistaja omistaja : loytyneet)
                    omistaja.tulosta(System.out);
            }
            

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
        
   }        




