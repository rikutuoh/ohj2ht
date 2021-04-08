package rtk.test;
// Generated by ComTest BEGIN
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import rtk.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.08 14:57:30 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class OsallistujatTest {



  // Generated by ComTest BEGIN
  /** testOsallistujat16 */
  @Test
  public void testOsallistujat16() {    // Osallistujat: 16
    Osallistujat osallistujat = new Osallistujat(); 
    Osallistuja osall1 = new Osallistuja(1, "Osal1", "Listuja1"); 
    Osallistuja osall2 = new Osallistuja(2, "Osal2", "Listuja2"); 
    Osallistuja osall3 = new Osallistuja(3, "Osal3", "Listuja3"); 
    Osallistuja osall4 = new Osallistuja(4, "Osal4", "Listuja4"); 
    Osallistuja osall5 = new Osallistuja(5, "Osal5", "Listuja5"); 
    osallistujat.lisaa(osall1); osallistujat.lisaa(osall2); osallistujat.lisaa(osall3); osallistujat.lisaa(osall4); osallistujat.lisaa(osall5); 
    assertEquals("From: Osallistujat line: 25", "[Osal1 Listuja1, Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]", Arrays.toString(osallistujat.getLista().toArray())); 
    osallistujat.muokkaa(osall1, new Osallistuja(0, "Osal1Uus", "Listuja1Uus")); 
    assertEquals("From: Osallistujat line: 27", "[Osal1Uus Listuja1Uus, Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]", Arrays.toString(osallistujat.getLista().toArray())); 
    osallistujat.muokkaa(osall1, null); 
    assertEquals("From: Osallistujat line: 29", "[Osal1Uus Listuja1Uus, Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]", Arrays.toString(osallistujat.getLista().toArray())); 
    osallistujat.poista(osall1); 
    assertEquals("From: Osallistujat line: 31", "[Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]", Arrays.toString(osallistujat.getLista().toArray())); 
  } // Generated by ComTest END
}