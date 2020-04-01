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
 * @version 1.4.2020
 *
 */
public class Rekisteri {
    private Kissat kissat = new Kissat();
    private Omistajat omistajat = new Omistajat();


    /**
     * Palautaa rekisterin kissamäärän
     * @return kissamäärä
     */
    public int getKissoja() {
        return kissat.getLkm();
    }
    
    
    /**
     * Palautaa rekisterin omistajamäärän
     * @return omistajamäärä
     */
    public int getOmistajia() {
        return omistajat.getLkm();
    }
 

    /**
     * Poistaa kissoista ja omistajista ne joilla on nro. Kesken.
     * @param nro viitenumero, jonka mukaan poistetaan
     * @return montako kissaa poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
    }
    

    
    /**
     * Lisätään uusi kissa rekisteriin
     * @param kissa lisättävä kissa
     */
    public void lisaa(Kissa kissa) {
        kissat.lisaa(kissa);
    }

    
    
    /**
     * Lisää rekisteriin uuden omistajan
     * @param omistaja lisättävä omistaja
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
    public void lisaa(Omistaja omistaja) {
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
     * @example
     * <pre name="test">
     * #import java.util.*;
     *  Rekisteri rekisteri = new Rekisteri();
     *  Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(), sadepilvinen3 = new Omistaja();
     *  sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); sadepilvinen3.rekisteroi();
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
     * </pre> 
     */
     
    public List<Kissa> annaKissat(Omistaja omistaja) {
        return kissat.annaKissat(omistaja.getOmistajanTunnusNro());
    }

    /**
     * Haetaan kaikki kissan omistajat
     * @param kissa kissa jolle omistajia haetaan
     * @return tietorakenne jossa viiteet löydettyihin omistajiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Rekisteri rekisteri = new Rekisteri();
     *  Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa(), sadepilvi3 = new Kissa();
     *  sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi(); sadepilvi3.rekisteroi();
     *  int id1 = sadepilvi1.getKissanTunnusNro();
     *  int id2 = sadepilvi2.getKissanTunnusNro();
     *  Omistaja sadepilvinen11 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen11);
     *  Omistaja sadepilvinen12 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen12);
     *  Omistaja sadepilvinen21 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen21);
     *  Omistaja sadepilvinen22 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen22);
     *  Omistaja sadepilvinen23 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen23);
     *  
     *  List<Omistaja> loytyneetOmistajat;
     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi3);
     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi1);
     *  loytyneetOmistajat.get(0) == sadepilvinen11 === true;
     *  loytyneetOmistajat.get(1) == sadepilvinen12 === true;
     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi2);
     *  loytyneetOmistajat.size() === 5; 
     *  </pre>
     */
    public List<Omistaja> annaOmistajat(Kissa kissa) {
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
     * 
     *  Rekisteri rekisteri = new Rekisteri();
     *  
     *  Kissa sadepilvi1 = new Kissa(); sadepilvi1.taytaKissaTiedoilla(); sadepilvi1.rekisteroi();
     *  Kissa sadepilvi2 = new Kissa(); sadepilvi2.taytaKissaTiedoilla(); sadepilvi2.rekisteroi();
     *  Omistaja sadepilvinen21 = new Omistaja(); sadepilvinen21.taytaKissanOmistaja(sadepilvi2.getKissanTunnusNro());
     *  Omistaja sadepilvinen11 = new Omistaja(); sadepilvinen11.taytaKissanOmistaja(sadepilvi1.getKissanTunnusNro());
     *   
     *  String hakemisto = "testiKissakaveri";
     *  File dir = new File(hakemisto);
     *  File ftied  = new File(hakemisto+"/kissat.dat");
     *  File fhtied = new File(hakemisto+"/omistajat.dat");
     *  dir.mkdir();  
     *  rekisteri.lueTiedostosta(hakemisto); 
     *  rekisteri.lisaa(sadepilvi1);
     *  rekisteri.lisaa(sadepilvi2);
     *  rekisteri.lisaa(sadepilvinen21);
     *  rekisteri.lisaa(sadepilvinen11);
     *  rekisteri.tallenna();
     *  rekisteri = new Rekisteri();
     *  rekisteri.lueTiedostosta(hakemisto);
     *  Collection<Kissa> kaikki = rekisteri.etsiKissat("",-1); 
     *  Iterator<Kissa> it = kaikki.iterator();
     *  it.next().getNimi() === sadepilvi1.getNimi();
     *  List<Omistaja> loytyneet = rekisteri.annaOmistajat(sadepilvi1);
     *  Iterator<Omistaja> ih = loytyneet.iterator();
     *  loytyneet = rekisteri.annaOmistajat(sadepilvi2);
     *  ih = loytyneet.iterator();
     *  rekisteri.lisaa(sadepilvi2);
     *  rekisteri.lisaa(sadepilvinen11);
     *  rekisteri.tallenna();
     *  ftied.delete()  === true;
     *  fhtied.delete() === true;
     *  File fbak = new File(hakemisto+"/kissat.bak");
     *  File fhbak = new File(hakemisto+"/omistajat.bak");
     *  fhbak.delete() === true;
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
     * Vaikka kissojen tallettamien epäonistuisi, niin yritetään silti tallentaa
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

            int id1 = sadepilvi1.getKissanTunnusNro();
            int id2 = sadepilvi2.getKissanTunnusNro();
            Omistaja sadepilvinen11 = new Omistaja(id1); sadepilvinen11.taytaKissanOmistaja(id1); rekisteri.lisaa(sadepilvinen11);
            Omistaja sadepilvinen12 = new Omistaja(id1); sadepilvinen11.taytaKissanOmistaja(id1); rekisteri.lisaa(sadepilvinen12);
            Omistaja sadepilvinen21 = new Omistaja(id2); sadepilvinen21.taytaKissanOmistaja(id2); rekisteri.lisaa(sadepilvinen21);
            Omistaja sadepilvinen22 = new Omistaja(id2); sadepilvinen22.taytaKissanOmistaja(id2); rekisteri.lisaa(sadepilvinen22);
            Omistaja sadepilvinen23 = new Omistaja(id2); sadepilvinen23.taytaKissanOmistaja(id2); rekisteri.lisaa(sadepilvinen23);
            
        
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
            int i = 0;
            for (Kissa kissa: kissat) {
             
                kissa.tulosta(System.out);
                List<Omistaja> loytyneet = rekisteri.annaOmistajat(kissa);
                for (Omistaja omistaja : loytyneet)
                    omistaja.tulosta(System.out);
                i++;
            }
            
            
            for (int k = 0; k < rekisteri.getOmistajia(); k++) {
                Omistaja omistaja = rekisteri.annaOmistaja(k);
              
                omistaja.tulosta(System.out);
               
                List<Omistaja> loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi1);
                for (Omistaja omistaja2 : loytyneetOmistajat)
                    omistaja2.tulosta(System.out);
           }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
        
   }        




