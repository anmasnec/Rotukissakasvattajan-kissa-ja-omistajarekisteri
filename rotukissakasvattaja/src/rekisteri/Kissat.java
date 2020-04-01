
package rekisteri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    private boolean muutettu = false;
    private int lkm = 0;
    private String kasvattajaNimi = "";
    private String tiedostonPerusNimi = "kissat";
   
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
        muutettu = true;
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
        return ((ArrayList<Kissa>) alkiot).get(i);
    }

    


    /**
     * Lukee kissat tiedostosta. 
     * @param tied tiedoston perusnimi
     * @throws SailoException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Kissat kissat = new Kissat();
     *  Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa();
     *  sadepilvi1.taytaKissaTiedoilla();
     *  sadepilvi2.taytaKissaTiedoilla();
     *  String hakemisto = "testiKissakaveri";
     *  String tiedNimi = hakemisto+"/kissat";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  kissat.lueTiedostosta(tiedNimi); 
     *  kissat.lisaa(sadepilvi1);
     *  kissat.lisaa(sadepilvi2);
     *  kissat.tallenna();
     *  kissat = new Kissat();     // Poistetaan vanhat luomalla uusi
     *  kissat.lueTiedostosta(tiedNimi); // johon ladataan tiedot tiedostosta.
     *  Iterator<Kissa> i = kissat.iterator();
     *  i.next().getNimi() === sadepilvi1.getNimi();
     *  kissat.lisaa(sadepilvi2);
     *  kissat.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     * </pre>
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
//            kasvattajaNimi = fi.readLine();
//            if ( kasvattajaNimi == null ) throw new SailoException("Kasvattajan nimi puuttuu");
            String rivi = "";
        

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Kissa kissa = new Kissa();
                kissa.parse(rivi); 
                lisaa(kissa);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    

    
    /**
     * Palauttaa kasvattajanimen 
     * @return Kasvattajanimi merkkijonona
     */
    public String getKasvattajaNimi() {
        return kasvattajaNimi;
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
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    
    
    /**
     * Tallentaa kissat tiedostoon.  
     * Tiedoston muoto:
     * <pre>
     * Kissakaveri
     * 
     * 1|1|Kissakaveri Sadepilvi|Ragdoll|RAG n 03|Ruskeanaamio bicolour|FI KS NO 1234567|naaras|23.05.2019|Kissakaveri Poutapilvi|FI KS NO 4537249|Kamukisu Rahkasammal|FI KS NO 7845012|31.08.2019|20.08.2019  ||Myyntiin|
     * 2|2|Kissakaveri Saniainen|Ragdoll|RAG n 04|Ruskeanaamio mitted|FI KS NO 9267435|uros|23.05.2019|Kissakaveri Poutapilvi|FI KS NO 4537249|Kamukisu Rahkasammal|FI KS NO 7845012|30.08.2019|20.08.2019||Myyntiin, Näyttelykissa|
     * </pre>
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(new File(getTiedostonNimi()).getCanonicalPath())) ) {
//            fo.println(getKasvattajaNimi());
//            fo.println(alkiot);
            for (Kissa kissa : alkiot) {
                fo.println(kissa.toString());
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    
    
    
    /**
     * Palauttaa rekisterin kissojen lukumäärän
     * @return kissojen lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    
    /**
     * Luokka kissojen iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Kissat kissat = new Kissat();
     * Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa();
     * sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi();
     *
     * kissat.lisaa(sadepilvi1); 
     * kissat.lisaa(sadepilvi2); 
     * kissat.lisaa(sadepilvi1); 
     * 
     * StringBuffer ids = new StringBuffer(30);
     * for (Kissa kissa:kissat)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+kissa.getKissanTunnusNro());           
     * 
     * String tulos = " " + sadepilvi1.getKissanTunnusNro() + " " + sadepilvi2.getKissanTunnusNro() + " " + sadepilvi1.getKissanTunnusNro();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Kissa>  i=kissat.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Kissa kissa = i.next();
     *   ids.append(" "+kissa.getKissanTunnusNro());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Kissa>  i=kissat.iterator();
     * i.next() == sadepilvi1  === true;
     * i.next() == sadepilvi2  === true;
     * i.next() == sadepilvi1  === true;
     * 
     * i.next();  #THROWS NoSuchElementException
     *  
     * </pre>
     */
    public class KissatIterator implements Iterator<Kissa> {
        private int kohdalla = 0;


        /**
         * Onko olemassa vielä seuraavaa kissaa
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä kissoja
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }


        /**
         * Annetaan seuraava kissa
         * @return seuraava kissa
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Kissa next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei ole");
            return anna(kohdalla++);
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
     * Palautetaan iteraattori kissoistaan.
     * @return kissa iteraattori
     */
    @Override
    public Iterator<Kissa> iterator() {
        return new KissatIterator();
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
     * Palauttaa "taulukossa" hakuehtoon vastaavien kissojen viitteet 
     * @param hakuehto hakuehto 
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä jäsenistä 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Kissat kissat = new Kissat(); 
     *   Kissa kissa1 = new Kissa(); kissa1.parse("1|1|Kissakaveri Sadepilvi|Ragdoll|RAG n 03|Ruskeanaamio bicolour|"); 
     *   Kissa kissa2 = new Kissa(); kissa2.parse("2|2|Kissakaveri Saniainen|Ragdoll|RAG n 04|Ruskeanaamio mitted|"); 
     *   Kissa kissa3 = new Kissa(); kissa3.parse("3|3|Kissakaveri Sateenkaari|Ragdoll|RAG n 03|Ruskeanaamio bicolour|"); 
     *   Kissa kissa4 = new Kissa(); kissa4.parse("4|4|Kissakaveri April|Ragdoll|RAG a|Sininaamio colourpoint|"); 
     *   Kissa kissa5 = new Kissa(); kissa5.parse("5|5|Kissakaveri Peter Pan|Ragdoll|RAG a 04|Sininaamio mitted|"); 
     *   kissat.lisaa(kissa1); kissat.lisaa(kissa2); kissat.lisaa(kissa3); kissat.lisaa(kissa4); kissat.lisaa(kissa5);
     *   // TODO: toistaiseksi palauttaa kaikki jäsenet 
     * </pre> 
     */ 
    @SuppressWarnings("unused")
    public Collection<Kissa> etsi(String hakuehto, int k) { 
        Collection<Kissa> loytyneet = new ArrayList<Kissa>(); 
        for (Kissa kissa : this) { 
            loytyneet.add(kissa);  
        } 
        return loytyneet; 
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
            kissa.tulosta(System.out);
        }
       
    }

}
