
package rekisteri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import fi.jyu.mit.ohj2.WildChars;


/**
 * Rekisterin kissat joka osaa mm. lisätä uuden kissan.
 * Vastuualueet:                                                       
* - Pitää yllä varsinaista kasvattajan rekisteriä kissojen osalta (lisää ja poistaa kissan)                            
* - Lukee ja kirjoittaa kissan tiedostoon                               
* - Etsii ja lajittelee
* Avustajaluokka: Kissa
 * @author annik
 * @version 24.4.2020
 * 
 */
public class Kissat implements Iterable <Kissa> {
    
    private boolean muutettu = false;
    private String kasvattajaNimi = "";
    private String tiedostonPerusNimi = "kissat";
   
    /** Lista kissoista */
    private List<Kissa> alkiot = new ArrayList<Kissa>();
    
    /**
     * Oletusmuodostaja; kissojen alustaminen
     */
    public Kissat() {
    }
    
    /**
     * @return listan kissoista
     */
    public List<Kissa> getKissatAlkiot() {
        return alkiot;
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
     * Korvaa kissan tietorakenteessa.  Ottaa kissan omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kissa.  Jos ei löydy,
     * niin lisätään uutena kissana.
     * @param kissa lisättävän kissan viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Kissat kissat = new Kissat();
     * Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa();
     * sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi();
     * kissat.getLkm() === 0;
     * kissat.korvaaTaiLisaa(sadepilvi1); kissat.getLkm() === 1;
     * kissat.korvaaTaiLisaa(sadepilvi2); kissat.getLkm() === 2;
     * Kissa sadepilvi3 = sadepilvi1.clone();
     * sadepilvi3.aseta(3,"kkk");
     * Iterator<Kissa> it = kissat.iterator();
     * it.next() == sadepilvi1 === true;
     * kissat.korvaaTaiLisaa(sadepilvi3); kissat.getLkm() === 2;
     * it = kissat.iterator();
     * Kissa k0 = it.next();
     * k0 === sadepilvi3;
     * k0 == sadepilvi3 === true;
     * k0 == sadepilvi1 === false;
     * </pre>
     */
    public void korvaaTaiLisaa(Kissa kissa) throws SailoException  {
        int id = kissa.getKissanTunnusNro();
        for (int i = 0; i < getLkm(); i++) {
            if (alkiot.get(i).getKissanTunnusNro() == id) {
                alkiot.set(i, kissa);
                muutettu = true;
                return;
            }
        }
        lisaa(kissa);
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
     * Poistaa kissan jolla on valittu tunnusnumero  
     * @param id poistettavan kissan tunnusnumero 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     * @example 
     * <pre name="test"> 
     * Kissat kissat = new Kissat(); 
     * Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa(), sadepilvi3 = new Kissa(); 
     * sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi(); sadepilvi3.rekisteroi(); 
     * int id1 = sadepilvi1.getKissanTunnusNro(); 
     * kissat.lisaa(sadepilvi1); kissat.lisaa(sadepilvi2); kissat.lisaa(sadepilvi3); 
     * kissat.poista(id1+1) === 1; 
     * kissat.annaId(id1+1) === null; kissat.getLkm() === 2; 
     * kissat.poista(id1) === 1; kissat.getLkm() === 1; 
     * </pre> 
     *  
     */ 
    public int poista(int id) { 
         
        Kissa ind = etsiId(id); 
       
        if (ind == null) return 0;
 
        alkiot.removeIf(e -> e.equals(ind));
        muutettu =true;
        return 1;
 
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
     *  ftied.delete();
     *  kissat.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  kissat.lisaa(sadepilvi1);
     *  kissat.lisaa(sadepilvi2);
     *  kissat.tallenna();
     *  kissat = new Kissat();     // Poistetaan vanhat luomalla uusi
     *  kissat.lueTiedostosta(tiedNimi); // johon ladataan tiedot tiedostosta.
     *  Iterator<Kissa> i = kissat.iterator();
     *  i.next() === sadepilvi1;
     *  i.next() === sadepilvi2;
     *  i.hasNext() === false;
     *  kissat.lisaa(sadepilvi2);
     *  kissat.tallenna();
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
        fbak.delete(); 
        ftied.renameTo(fbak); 

        try ( PrintWriter fo = new PrintWriter(new FileWriter(new File(getTiedostonNimi()).getCanonicalPath())) ) {
           
            for (Kissa kissa : alkiot) {
                fo.println(kissa.toString());
            }
     
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
     * </pre> 
     */ 
   
    public Collection<Kissa> etsi(String hakuehto, int k) { 
        String ehto = "*"; 
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
        int hk = k; 
        if ( hk < 0 ) hk = 0; // jotta etsii id:n mukaan 

        List<Kissa> loytyneet = new ArrayList<Kissa>(); 
        for (Kissa kissa : this) { 
            if (WildChars.onkoSamat(kissa.anna(hk), ehto)) loytyneet.add(kissa);   
        } 
        Collections.sort(loytyneet, new Kissa.Vertailija(hk)); 
        return loytyneet; 
    }
    
    
    /** 
     * Etsii kissan id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return kissa jolla etsittävä id tai null 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kissat kissat = new Kissat(); 
     * Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa(), sadepilvi3 = new Kissa(); 
     * sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi(); sadepilvi3.rekisteroi(); 
     * int id1 = sadepilvi1.getKissanTunnusNro(); 
     * kissat.lisaa(sadepilvi1); kissat.lisaa(sadepilvi2); kissat.lisaa(sadepilvi3); 
     * kissat.annaId(id1  ) == sadepilvi1 === true; 
     * kissat.annaId(id1+1) == sadepilvi2 === true; 
     * kissat.annaId(id1+2) == sadepilvi3 === true; 
     * </pre> 
     */ 
    public Kissa annaId(int id) { 
        for (Kissa kissa : this) { 
            if (id == kissa.getKissanTunnusNro()) return kissa; 
        } 
        return null; 
    } 


    /** 
     * Etsii kissan id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen kissan indeksi tai virheen
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kissat kissat = new Kissat(); 
     * Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa(), sadepilvi3 = new Kissa(); 
     * sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi(); sadepilvi3.rekisteroi(); 
     * int id1 = sadepilvi1.getKissanTunnusNro(); 
     * kissat.lisaa(sadepilvi1); kissat.lisaa(sadepilvi2); kissat.lisaa(sadepilvi3); 
     * kissat.etsiId(id1) === sadepilvi1;  
     * </pre> 
     */ 
    public Kissa etsiId(int id) { 
 
        for (Kissa kissa : alkiot) {
            if (kissa.getKissanTunnusNro() == id)
                return kissa;
        }
        throw new NoSuchElementException("Ei ole id mukaista kissaa");
        
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
