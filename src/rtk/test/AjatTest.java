package rtk.test;
// Generated by ComTest BEGIN
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import rtk.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.07 23:14:52 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class AjatTest {



  // Generated by ComTest BEGIN
  /** testAjat16 */
  @Test
  public void testAjat16() {    // Ajat: 16
    Ajat ajat = new Ajat(); 
    Aika aika1 = new Aika(1, 1, 1, 10); 
    Aika aika2 = new Aika(2, 1, 2, 41241); 
    Aika aika3 = new Aika(3, 1, 3, 544353); 
    Aika aika4 = new Aika(4, 2, 1, 10000); 
    Aika aika5 = new Aika(5, 2, 2, 60000); 
    Aika aika6 = new Aika(6, 3, 1, 0); 
    Aika aika7 = new Aika(7, 3, 2, -1); 
    ajat.lisaa(aika1); ajat.lisaa(aika2); ajat.lisaa(aika3); ajat.lisaa(aika4); ajat.lisaa(aika5); ajat.lisaa(aika6); ajat.lisaa(aika7); 
    assertEquals("From: Ajat line: 28", "[00.00,01, 00.41,24, 09.04,35, 00.10,00, 01.00,00, 00.00,00, 00.00,00, null, null, null]", Arrays.toString(ajat.getKaikki())); 
    ajat.poista(new Osallistuja(1, "", "", "", "", "", "", "", ""), new Erikoiskoe(1, 1, "", 0, null)); 
    assertEquals("From: Ajat line: 30", "[null, 00.41,24, 09.04,35, 00.10,00, 01.00,00, 00.00,00, 00.00,00, null, null, null]", Arrays.toString(ajat.getKaikki())); 
    ajat.poistaKaikki(new Osallistuja(1, "", "", "", "", "", "", "", "")); 
    assertEquals("From: Ajat line: 32", "[null, 00.41,24, 09.04,35, null, 01.00,00, null, 00.00,00, null, null, null]", Arrays.toString(ajat.getKaikki())); 
    ajat.poistaKaikki(new Erikoiskoe(1, 1, "", 0, null)); 
    assertEquals("From: Ajat line: 34", "[null, null, null, null, 01.00,00, null, 00.00,00, null, null, null]", Arrays.toString(ajat.getKaikki())); 
  } // Generated by ComTest END
}