package rekisteri.test;
// Generated by ComTest BEGIN
import rekisteri.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.16 14:18:22 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class KissatTest {



  // Generated by ComTest BEGIN
  /** testIterator89 */
  @Test
  public void testIterator89() {    // Kissat: 89
    Kissat maukujat = new Kissat(); 
    Kissa sadepilvi21 = new Kissa(2); maukujat.lisaa(sadepilvi21); 
    Kissa sadepilvi11 = new Kissa(1); maukujat.lisaa(sadepilvi11); 
    Kissa sadepilvi22 = new Kissa(2); maukujat.lisaa(sadepilvi22); 
    Kissa sadepilvi12 = new Kissa(1); maukujat.lisaa(sadepilvi12); 
    Kissa sadepilvi23 = new Kissa(2); maukujat.lisaa(sadepilvi23); 
    Iterator<Kissa> i2=maukujat.iterator(); 
    assertEquals("From: Kissat line: 101", sadepilvi21, i2.next()); 
    assertEquals("From: Kissat line: 102", sadepilvi11, i2.next()); 
    assertEquals("From: Kissat line: 103", sadepilvi22, i2.next()); 
    assertEquals("From: Kissat line: 104", sadepilvi12, i2.next()); 
    assertEquals("From: Kissat line: 105", sadepilvi23, i2.next()); 
    try {
    assertEquals("From: Kissat line: 106", sadepilvi12, i2.next()); 
    fail("Kissat: 106 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int omistajanrot[] = { 2,1,2,1,2} ; 
    for ( Kissa kissa:maukujat ) {
    assertEquals("From: Kissat line: 112", omistajanrot[n], kissa.getOmistajanTunnusNro()); n++; 
    }
    assertEquals("From: Kissat line: 115", 5, n); 
  } // Generated by ComTest END
}