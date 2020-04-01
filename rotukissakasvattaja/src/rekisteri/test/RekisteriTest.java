package rekisteri.test;
// Generated by ComTest BEGIN
import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.01 21:14:11 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class RekisteriTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa66 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa66() throws SailoException {    // Rekisteri: 66
    Rekisteri rekisteri = new Rekisteri(); 
    Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(); 
    sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); 
    assertEquals("From: Rekisteri line: 71", 0, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 72", 1, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen2); assertEquals("From: Rekisteri line: 73", 2, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 74", 3, rekisteri.getOmistajia()); 
    assertEquals("From: Rekisteri line: 75", 3, rekisteri.getOmistajia()); 
    assertEquals("From: Rekisteri line: 76", sadepilvinen1, rekisteri.annaOmistaja(0)); 
    assertEquals("From: Rekisteri line: 77", sadepilvinen2, rekisteri.annaOmistaja(1)); 
    assertEquals("From: Rekisteri line: 78", sadepilvinen1, rekisteri.annaOmistaja(2)); 
    try {
    assertEquals("From: Rekisteri line: 79", sadepilvinen1, rekisteri.annaOmistaja(3)); 
    fail("Rekisteri: 79 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 80", 4, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 81", 5, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 82", 6, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 83", 7, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 84", 8, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 85", 9, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 86", 10, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaKissat124 */
  @Test
  public void testAnnaKissat124() {    // Rekisteri: 124
    Rekisteri rekisteri = new Rekisteri(); 
    Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(), sadepilvinen3 = new Omistaja(); 
    sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); sadepilvinen3.rekisteroi(); 
    int id1 = sadepilvinen1.getOmistajanTunnusNro(); 
    int id2 = sadepilvinen2.getOmistajanTunnusNro(); 
    Kissa sadepilvi11 = new Kissa(id1); rekisteri.lisaa(sadepilvi11); 
    Kissa sadepilvi12 = new Kissa(id1); rekisteri.lisaa(sadepilvi12); 
    Kissa sadepilvi21 = new Kissa(id2); rekisteri.lisaa(sadepilvi21); 
    Kissa sadepilvi22 = new Kissa(id2); rekisteri.lisaa(sadepilvi22); 
    Kissa sadepilvi23 = new Kissa(id2); rekisteri.lisaa(sadepilvi23); 
    List<Kissa> loytyneetKissat; 
    loytyneetKissat = rekisteri.annaKissat(sadepilvinen3); 
    assertEquals("From: Rekisteri line: 139", 0, loytyneetKissat.size()); 
    loytyneetKissat = rekisteri.annaKissat(sadepilvinen1); 
    assertEquals("From: Rekisteri line: 141", 2, loytyneetKissat.size()); 
    assertEquals("From: Rekisteri line: 142", true, loytyneetKissat.get(0) == sadepilvi11); 
    assertEquals("From: Rekisteri line: 143", true, loytyneetKissat.get(1) == sadepilvi12); 
    loytyneetKissat = rekisteri.annaKissat(sadepilvinen2); 
    assertEquals("From: Rekisteri line: 145", 3, loytyneetKissat.size()); 
    assertEquals("From: Rekisteri line: 146", true, loytyneetKissat.get(0) == sadepilvi21); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaOmistajat159 */
  @Test
  public void testAnnaOmistajat159() {    // Rekisteri: 159
    Rekisteri rekisteri = new Rekisteri(); 
    Kissa sadepilvi1 = new Kissa(), sadepilvi2 = new Kissa(), sadepilvi3 = new Kissa(); 
    sadepilvi1.rekisteroi(); sadepilvi2.rekisteroi(); sadepilvi3.rekisteroi(); 
    int id1 = sadepilvi1.getKissanTunnusNro(); 
    int id2 = sadepilvi2.getKissanTunnusNro(); 
    Omistaja sadepilvinen11 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen11); 
    Omistaja sadepilvinen12 = new Omistaja(id1); rekisteri.lisaa(sadepilvinen12); 
    Omistaja sadepilvinen21 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen21); 
    Omistaja sadepilvinen22 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen22); 
    Omistaja sadepilvinen23 = new Omistaja(id2); rekisteri.lisaa(sadepilvinen23); 
    List<Omistaja> loytyneetOmistajat; 
    loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi3); 
    loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi1); 
    assertEquals("From: Rekisteri line: 176", true, loytyneetOmistajat.get(0) == sadepilvinen11); 
    assertEquals("From: Rekisteri line: 177", true, loytyneetOmistajat.get(1) == sadepilvinen12); 
    loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi2); 
    assertEquals("From: Rekisteri line: 179", 5, loytyneetOmistajat.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta220 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta220() throws SailoException {    // Rekisteri: 220
    Rekisteri rekisteri = new Rekisteri(); 
    Kissa sadepilvi1 = new Kissa(); sadepilvi1.taytaKissaTiedoilla(); sadepilvi1.rekisteroi(); 
    Kissa sadepilvi2 = new Kissa(); sadepilvi2.taytaKissaTiedoilla(); sadepilvi2.rekisteroi(); 
    Omistaja sadepilvinen21 = new Omistaja(); sadepilvinen21.taytaKissanOmistaja(sadepilvi2.getKissanTunnusNro()); 
    Omistaja sadepilvinen11 = new Omistaja(); sadepilvinen11.taytaKissanOmistaja(sadepilvi1.getKissanTunnusNro()); 
    String hakemisto = "testiKissakaveri"; 
    File dir = new File(hakemisto); 
    File ftied  = new File(hakemisto+"/kissat.dat"); 
    File fhtied = new File(hakemisto+"/omistajat.dat"); 
    dir.mkdir(); 
    rekisteri.lueTiedostosta(hakemisto); 
    rekisteri.lisaa(sadepilvi1); 
    rekisteri.lisaa(sadepilvi2); 
    rekisteri.lisaa(sadepilvinen21); 
    rekisteri.lisaa(sadepilvinen11); 
    rekisteri.tallenna(); 
    rekisteri = new Rekisteri(); 
    rekisteri.lueTiedostosta(hakemisto); 
    Collection<Kissa> kaikki = rekisteri.etsiKissat("",-1); 
    Iterator<Kissa> it = kaikki.iterator(); 
    assertEquals("From: Rekisteri line: 247", sadepilvi1.getNimi(), it.next().getNimi()); 
    List<Omistaja> loytyneet = rekisteri.annaOmistajat(sadepilvi1); 
    Iterator<Omistaja> ih = loytyneet.iterator(); 
    loytyneet = rekisteri.annaOmistajat(sadepilvi2); 
    ih = loytyneet.iterator(); 
    rekisteri.lisaa(sadepilvi2); 
    rekisteri.lisaa(sadepilvinen11); 
    rekisteri.tallenna(); 
    assertEquals("From: Rekisteri line: 255", true, ftied.delete()); 
    assertEquals("From: Rekisteri line: 256", true, fhtied.delete()); 
    File fbak = new File(hakemisto+"/kissat.bak"); 
    File fhbak = new File(hakemisto+"/omistajat.bak"); 
    assertEquals("From: Rekisteri line: 259", true, fhbak.delete()); 
  } // Generated by ComTest END
}