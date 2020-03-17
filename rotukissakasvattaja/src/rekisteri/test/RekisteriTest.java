package rekisteri.test;
// Generated by ComTest BEGIN
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.16 20:43:54 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class RekisteriTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa109 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa109() throws SailoException {    // Rekisteri: 109
    Rekisteri rekisteri = new Rekisteri(); 
    Omistaja sadepilvinen1 = new Omistaja(), sadepilvinen2 = new Omistaja(); 
    sadepilvinen1.rekisteroi(); sadepilvinen2.rekisteroi(); 
    assertEquals("From: Rekisteri line: 114", 0, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 115", 1, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen2); assertEquals("From: Rekisteri line: 116", 2, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 117", 3, rekisteri.getOmistajia()); 
    assertEquals("From: Rekisteri line: 118", 3, rekisteri.getOmistajia()); 
    assertEquals("From: Rekisteri line: 119", sadepilvinen1, rekisteri.annaOmistaja(0)); 
    assertEquals("From: Rekisteri line: 120", sadepilvinen2, rekisteri.annaOmistaja(1)); 
    assertEquals("From: Rekisteri line: 121", sadepilvinen1, rekisteri.annaOmistaja(2)); 
    try {
    assertEquals("From: Rekisteri line: 122", sadepilvinen1, rekisteri.annaOmistaja(3)); 
    fail("Rekisteri: 122 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 123", 4, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 124", 5, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 125", 6, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 126", 7, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 127", 8, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 128", 9, rekisteri.getOmistajia()); 
    rekisteri.lisaa(sadepilvinen1); assertEquals("From: Rekisteri line: 129", 10, rekisteri.getOmistajia()); 
    try {
    rekisteri.lisaa(sadepilvinen1); 
    fail("Rekisteri: 130 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaKissat153 */
  @Test
  public void testAnnaKissat153() {    // Rekisteri: 153
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
    assertEquals("From: Rekisteri line: 169", 0, loytyneetKissat.size()); 
    loytyneetKissat = rekisteri.annaKissat(sadepilvinen1); 
    assertEquals("From: Rekisteri line: 171", 2, loytyneetKissat.size()); 
    assertEquals("From: Rekisteri line: 172", true, loytyneetKissat.get(0) == sadepilvi11); 
    assertEquals("From: Rekisteri line: 173", true, loytyneetKissat.get(1) == sadepilvi12); 
    loytyneetKissat = rekisteri.annaKissat(sadepilvinen2); 
    assertEquals("From: Rekisteri line: 175", 3, loytyneetKissat.size()); 
    assertEquals("From: Rekisteri line: 176", true, loytyneetKissat.get(0) == sadepilvi21); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnnaOmistajat226 
   * @throws SailoException when error
   */
  @Test
  public void testAnnaOmistajat226() throws SailoException {    // Rekisteri: 226
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
    Omistaja[] loytyneetOmistajat = new Omistaja[10]; 
    loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi1); 
    assertEquals("From: Rekisteri line: 241", 2, loytyneetOmistajat.length); 
    assertEquals("From: Rekisteri line: 242", true, loytyneetOmistajat[0] == sadepilvinen11); 
    assertEquals("From: Rekisteri line: 243", true, loytyneetOmistajat[1] == sadepilvinen12); 
    loytyneetOmistajat = rekisteri.annaOmistajat(sadepilvi2); 
    assertEquals("From: Rekisteri line: 245", 3, loytyneetOmistajat.length); 
    assertEquals("From: Rekisteri line: 246", true, loytyneetOmistajat[0] == sadepilvinen21); 
  } // Generated by ComTest END
}