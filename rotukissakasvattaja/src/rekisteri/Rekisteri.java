package rekisteri;

import java.util.List;

/**
 * Rekisteri-luokka, joka huolehtii kissoista. Pääosin kaikki metodit
 * ovat vain "välittäjämetodeja" kissoihin.
 * Vastuualueet:
 * - Huolehtii Kissat- ja Omistajat-luokkien välisestä yhteistyöstä ja välittää näitä tietoja pyydettäessä
 * - Lukee ja kirjoittaa rekisterin tiedostoon pyytämällä apua avustajilta
 * Avustajaluokat: Kissa, Omistaja, Kissat, Omistajat
 * @author annik
 * @version 10.3.2020
 *
 */
public class Rekisteri {
    private final Kissat kissat = new Kissat();
    private final Omistajat omistajat = new Omistajat();


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
    


//    /**
//     * Lisää rekisteriin uuden kissan
//     * @param kissa lisättävä kissa
//     * @throws SailoException jos lisäystä ei voida tehdä
//     * @example
//     * <pre name="test">
//     * #THROWS SailoException
//     * Rekisteri rekisteri = new Rekisteri();
//     * Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa();
//     * sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi();
//     * rekisteri.getKissoja() === 0;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 1;
//     * rekisteri.lisaa(sadepilvi2); rekisteri.getKissoja() === 2;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 3;
//     * rekisteri.getKissoja() === 3;
//     * rekisteri.annaKissa(0) === sadepilvi1;
//     * rekisteri.annaKissa(1) === sadepilvi2;
//     * rekisteri.annaKissa(2) === sadepilvi1;
//     * rekisteri.annaKissa(3) === sadepilvi1; #THROWS IndexOutOfBoundsException 
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 4;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 5;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 6;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 7;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 8;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 9;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 10;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 11;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 12;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 13;
//     * rekisteri.lisaa(sadepilvi1); rekisteri.getKissoja() === 14;
//     * rekisteri.lisaa(sadepilvi1);            #THROWS SailoException
//     * </pre>
//     */
//    public void lisaa(Kissa kissa) throws SailoException {
//        kissat.lisaa(kissa);
//    }
    
    /**
     * Lisätään uusi kissa rekisteriin
     * @param kissa lisättävä kissa
     */
    public void lisaa(Kissa kissa) {
        kissat.lisaa(kissa);
    }

    
//    /**
//     * Lisätään uusi omistaja rekisteriin
//     * @param omistaja lisättävä omistaja
//     */
//    public void lisaa(Omistaja omistaja) {
//        omistajat.lisaa(omistaja);
//    }
    
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
     * rekisteri.lisaa(sadepilvinen1);            #THROWS SailoException
     * </pre>
     */
    public void lisaa(Omistaja omistaja) throws SailoException {
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
     * Haetaan kaikki omistajan kissat
     * @param omistaja omistaja jonka kissoja haetaan
     * @return tietorakenne jossa viiteet löydettyihin kissoihin
     * * @example
     * <pre name="test">
     * #import java.util.*;
     * 
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

//    /**
//     * Haetaan kaikki kissan omistajat
//     * @param kissa kissa jolle omistajia haetaan
//     * @return tietorakenne jossa viiteet löydettyihin omistajiin
//     * @example
//     * <pre name="test">
//     * #import java.util.*;
//     * 
//     *  Rekisteri rekisteri = new Rekisteri();
//     *  Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa(), sadepilvi3 = new Kissa();
//     *  sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi(); sadepilvi3.rekisteroi();
//     *  int id1 = sadepilvi1.getKissanTunnusNro();
//     *  int id2 = sadepilvi2.getKissanTunnusNro();
//     *  Omistaja sadepilvinen11 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen11);
//     *  Omistaja sadepilvinen12 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen12);
//     *  Omistaja sadepilvinen21 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen21);
//     *  Omistaja sadepilvinen22 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen22);
//     *  Omistaja sadepilvinen23 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen23);
//     *  
//     *  List<Omistaja> loytyneetOmistajat;
//     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi3);
//     *  loytyneetOmistajat.size() === 0; 
//     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi1);
//     *  loytyneetOmistajat.size() === 2; 
//     *  loytyneetOmistajat.get(0) == sadepilvinen11 === true;
//     *  loytyneetOmistajat.get(1) == sadepilvinen12 === true;
//     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi2);
//     *  loytyneetOmistajat.size() === 3; 
//     *  loytyneetOmistajat.get(0) == sadepilvinen21 === true;
//     *  </pre>
//     */
//    public List<Omistaja> annaOmistajat(Kissa kissa) {
//        return omistajat.annaOmistajat(kissa.getKissanTunnusNro());
//    }
    
    
    
    /**
     * Haetaan kaikki kissan omistajat
     * @param kissa kissa jolle omistajia haetaan
     * @return tietorakenne jossa viiteet löydettyihin omistajiin
     * @example
     * <pre name="test">
     * #THROWS SailoException     
     * Rekisteri rekisteri = new Rekisteri();
     * Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa(), sadepilvi3 = new Kissa();
     *  sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi(); sadepilvi3.rekisteroi();
     *  int id1 = sadepilvi1.getKissanTunnusNro();
     *  int id2 = sadepilvi2.getKissanTunnusNro();
     *  Omistaja sadepilvinen11 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen11);
     *  Omistaja sadepilvinen12 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen12);
     *  Omistaja sadepilvinen21 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen21);
     *  Omistaja sadepilvinen22 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen22);
     *  Omistaja sadepilvinen23 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen23);
     *  
     *  Omistaja[] loytyneetOmistajat = new Omistaja[10];
     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi1);
     *  loytyneetOmistajat.length === 2; 
     *  loytyneetOmistajat[0] == sadepilvinen11 === true;
     *  loytyneetOmistajat[1] == sadepilvinen12 === true;
     *  loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi2);
     *  loytyneetOmistajat.length === 3; 
     *  loytyneetOmistajat[0] == sadepilvinen21 === true;
     *  </pre>
     */
     public Omistaja[] annaOmistajat (Kissa kissa) {
        return omistajat.annaOmistajat(kissa.getKissanTunnusNro());
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
     * Lukee rekisterin tiedot tiedostosta
     * @param nimi jota käytetään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kissat.lueTiedostosta(nimi);
    }


    /**
     * Tallentaa rekisterin tiedot tiedostoon
     * @throws SailoException jos tallentamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        kissat.tallenna();
        omistajat.tallenna();
        // TODO: yritä tallentaa toinen vaikka toinen epäonnistuisi
    }


    /**
     * Testiohjelma rekisteristä
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Rekisteri rekisteri = new Rekisteri();

        try {
            // rekisteri.lueTiedostosta("kissat");
            // rekisteri.lueTiedostosta("omistajat");

            Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa();
            sadepilvi1.rekisteroi();
           // int id1 = sadepilvi1.getOmistajanTunnusNro();
            sadepilvi1.taytaKissaTiedoilla();
            sadepilvi2.rekisteroi();
           // int id2 = sadepilvi2.getOmistajanTunnusNro();
            sadepilvi2.taytaKissaTiedoilla();
 
            rekisteri.lisaa(sadepilvi1);
            rekisteri.lisaa(sadepilvi2);
            int id3 = sadepilvi1.getKissanTunnusNro();
            int id4 = sadepilvi2.getKissanTunnusNro();
            Omistaja sadepilvinen11 = new Omistaja(id3); sadepilvinen11.taytaKissanOmistaja(id3); rekisteri.lisaa(sadepilvinen11);
            Omistaja sadepilvinen12 = new Omistaja(id3); sadepilvinen11.taytaKissanOmistaja(id3); rekisteri.lisaa(sadepilvinen12);
            Omistaja sadepilvinen21 = new Omistaja(id4); sadepilvinen21.taytaKissanOmistaja(id4); rekisteri.lisaa(sadepilvinen21);
            Omistaja sadepilvinen22 = new Omistaja(id4); sadepilvinen22.taytaKissanOmistaja(id4); rekisteri.lisaa(sadepilvinen22);
            Omistaja sadepilvinen23 = new Omistaja(id4); sadepilvinen23.taytaKissanOmistaja(id4); rekisteri.lisaa(sadepilvinen23);
            
          rekisteri.lisaa(sadepilvinen11);
          int id5 = sadepilvinen11.getOmistajanTunnusNro();
          Kissa sadepilvi3 = new Kissa(id5); sadepilvi3.taytaKissaTiedoilla(); rekisteri.lisaa(sadepilvi3);
    

            System.out.println("============= Rekisterin testi =================");

            for (int i = 0; i < rekisteri.getKissoja(); i++) {
                Kissa kissa = rekisteri.annaKissa(i);
               // System.out.println("Kissa paikassa: " + i);
                kissa.tulosta(System.out);
                List<Kissa> loytyneetKissat = rekisteri.annaKissat(sadepilvinen23);
                for (Kissa katti : loytyneetKissat)
                    katti.tulosta(System.out);
            }    
            

            for (int i = 0; i < rekisteri.getOmistajia(); i++) {
                Omistaja omistaja = rekisteri.annaOmistaja(i);
                //System.out.println("Omistaja paikassa: " + i);
                omistaja.tulosta(System.out);
//                Kissa kissa = null;
//                List<Omistaja> loytyneetOmistajat = rekisteri.annaOmistajat(kissa);
//                for (Omistaja omistaja : loytyneetOmistajat)
//                    omistaja.tulosta(System.out);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
        
   }        




