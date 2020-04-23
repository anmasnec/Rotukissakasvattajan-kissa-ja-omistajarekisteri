package rekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.04.20 23:07:40 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class OmistajaTest {



  // Generated by ComTest BEGIN
  /** testGetNimi102 */
  @Test
  public void testGetNimi102() {    // Omistaja: 102
    Omistaja sadepilvinen = new Omistaja(); 
    sadepilvinen.taytaKissanOmistaja(); 
    { String _l_=sadepilvinen.getNimi(),_r_="Sade Pilvinen.*"; if ( !_l_.matches(_r_) ) fail("From: Omistaja line: 105" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAseta140 */
  @Test
  public void testAseta140() {    // Omistaja: 140
    Omistaja omistaja = new Omistaja(); 
    assertEquals("From: Omistaja line: 142", null, omistaja.aseta(1,"Sade Pilvinen")); 
    assertEquals("From: Omistaja line: 143", null, omistaja.aseta(4,"Pilvelä")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi262 */
  @Test
  public void testRekisteroi262() {    // Omistaja: 262
    Omistaja sadepilvinen = new Omistaja(); 
    assertEquals("From: Omistaja line: 264", 0, sadepilvinen.getOmistajanTunnusNro()); 
    sadepilvinen.rekisteroi(); 
    Omistaja sadepilvinen2 = new Omistaja(); 
    sadepilvinen2.rekisteroi(); 
    int n1 = sadepilvinen.getOmistajanTunnusNro(); 
    int n2 = sadepilvinen2.getOmistajanTunnusNro(); 
    assertEquals("From: Omistaja line: 270", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString305 */
  @Test
  public void testToString305() {    // Omistaja: 305
    Omistaja omistaja = new Omistaja(); 
    omistaja.parse("   1  |  Sade Pilvinen  | Pilvitie 2 B 16"); 
    assertEquals("From: Omistaja line: 308", true, omistaja.toString().startsWith("1|Sade Pilvinen|Pilvitie 2 B 16|"));  // on enemmänkin kuin 3 kenttää, siksi loppu |
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse331 */
  @Test
  public void testParse331() {    // Omistaja: 331
    Omistaja omistaja = new Omistaja(); 
    omistaja.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16"); 
    assertEquals("From: Omistaja line: 334", 1, omistaja.getOmistajanTunnusNro()); 
    assertEquals("From: Omistaja line: 335", true, omistaja.toString().startsWith("1|Sade Pilvinen|Pilvitie 2 B 16|"));  // on enemmänkin kuin 3 kenttää, siksi loppu |
    omistaja.rekisteroi(); 
    int n = omistaja.getOmistajanTunnusNro(); 
    omistaja.parse(""+(n+20));  // Otetaan merkkijonosta vain tunnusnumero
    omistaja.rekisteroi();  // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
    assertEquals("From: Omistaja line: 341", n+20+1, omistaja.getOmistajanTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone362 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone362() throws CloneNotSupportedException {    // Omistaja: 362
    Omistaja omistaja = new Omistaja(); 
    omistaja.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16"); 
    Omistaja kopio = omistaja.clone(); 
    assertEquals("From: Omistaja line: 367", omistaja.toString(), kopio.toString()); 
    omistaja.parse("   2  |  Kukka Metsälä  | Metsäpolku 8"); 
    assertEquals("From: Omistaja line: 369", false, kopio.toString().equals(omistaja.toString())); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEquals385 */
  @Test
  public void testEquals385() {    // Omistaja: 385
    Omistaja omistaja1 = new Omistaja(); 
    omistaja1.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16"); 
    Omistaja omistaja2 = new Omistaja(); 
    omistaja2.parse("   1  |  Sade Pilvinen   | Pilvitie 2 B 16"); 
    Omistaja omistaja3 = new Omistaja(); 
    omistaja3.parse("   2  |  Kukka Metsälä  | Metsäpolku 8"); 
    assertEquals("From: Omistaja line: 393", true, omistaja1.equals(omistaja2)); 
    assertEquals("From: Omistaja line: 394", true, omistaja2.equals(omistaja1)); 
    assertEquals("From: Omistaja line: 395", false, omistaja1.equals(omistaja3)); 
    assertEquals("From: Omistaja line: 396", false, omistaja3.equals(omistaja2)); 
  } // Generated by ComTest END
}