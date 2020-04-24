package kanta.test;
// Generated by ComTest BEGIN
import kanta.*;
import static org.junit.Assert.*;
import org.junit.*;
import static kanta.RekisterinumeroTarkistus.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.20 23:18:43 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class RekisterinumeroTarkistusTest {



  // Generated by ComTest BEGIN
  /** testTarkista48 */
  @Test
  public void testTarkista48() {    // RekisterinumeroTarkistus: 48
    RekisterinumeroTarkistus reknro = new RekisterinumeroTarkistus(); 
    assertEquals("From: RekisterinumeroTarkistus line: 51", "Rekisterinumero liian lyhyt", reknro.tarkista("FI KS NO 123456")); 
    assertEquals("From: RekisterinumeroTarkistus line: 52", "Rekisterinumero liian lyhyt", reknro.tarkista("KS")); 
    assertEquals("From: RekisterinumeroTarkistus line: 53", "Alkuosassa saa olla vain kirjaimia F,I,K,S,N,O", reknro.tarkista("12112121 1234566")); 
    assertEquals("From: RekisterinumeroTarkistus line: 54", "Rekisterinumero liian pitkä", reknro.tarkista("FI KS NO 12345678")); 
    assertEquals("From: RekisterinumeroTarkistus line: 55", "Loppuosassa saa olla vain numeroita", reknro.tarkista("FI KS NO A234566")); 
    assertEquals("From: RekisterinumeroTarkistus line: 56", "Alkuosassa saa olla vain kirjaimia F,I,K,S,N,O", reknro.tarkista("FI AS NO 1234567")); 
    assertEquals("From: RekisterinumeroTarkistus line: 57", null, reknro.tarkista("FI KS NO 1234567")); 
    assertEquals("From: RekisterinumeroTarkistus line: 58", null, reknro.tarkista("FI KS NO 9267435")); 
    assertEquals("From: RekisterinumeroTarkistus line: 59", null, reknro.tarkista("FI KS NO 6547988")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testOnkoVain79 */
  @Test
  public void testOnkoVain79() {    // RekisterinumeroTarkistus: 79
    assertEquals("From: RekisterinumeroTarkistus line: 80", false, onkoVain("123","12")); 
    assertEquals("From: RekisterinumeroTarkistus line: 81", true, onkoVain("123","1234")); 
    assertEquals("From: RekisterinumeroTarkistus line: 82", true, onkoVain("","1234")); 
  } // Generated by ComTest END
}