package rtk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Osallistujat-luokka
 * @author Riku Tuohimetsä
 * @version 23.3.2021
 * <pre name="test">
 * #import java.util.Arrays;
 * Osallistujat osallistujat = new Osallistujat();
 *  Osallistuja osall1 = new Osallistuja(1, "Osal1", "Listuja1");
 *  Osallistuja osall2 = new Osallistuja(2, "Osal2", "Listuja2");
 *  Osallistuja osall3 = new Osallistuja(3, "Osal3", "Listuja3");
 *  Osallistuja osall4 = new Osallistuja(4, "Osal4", "Listuja4");
 *  Osallistuja osall5 = new Osallistuja(5, "Osal5", "Listuja5");
 * osallistujat.lisaa(osall1); osallistujat.lisaa(osall2); osallistujat.lisaa(osall3); osallistujat.lisaa(osall4); osallistujat.lisaa(osall5);
 * Arrays.toString(osallistujat.getLista().toArray()) === "[Osal1 Listuja1, Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]";
 * osallistujat.muokkaa(osall1, new Osallistuja(0, "Osal1Uus", "Listuja1Uus"));
 * Arrays.toString(osallistujat.getLista().toArray()) === "[Osal1Uus Listuja1Uus, Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]";
 * osallistujat.muokkaa(osall1, null);
 * Arrays.toString(osallistujat.getLista().toArray()) === "[Osal1Uus Listuja1Uus, Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]";
 * osallistujat.poista(osall1);
 * Arrays.toString(osallistujat.getLista().toArray()) === "[Osal2 Listuja2, Osal3 Listuja3, Osal4 Listuja4, Osal5 Listuja5]";
 * </pre>
 */
public class Osallistujat {
	private static final String FILEPATH = ".\\src\\data\\osallistujat.dat";
	private Osallistuja[] osallistujat;
	private int lkm, maxLkm, viimId;
	
	/**
	 * Muodostaja osallistujat luokalle
	 * alustaa taulukon, lkm ja maxLkm
	 */
	public Osallistujat() {
		lkm = 0;
		maxLkm = 10;
		osallistujat = new Osallistuja[maxLkm];
		viimId = 0;
	}
	
	/**
	 * Lisää osallistujan taulukkoon
	 * @param Osallistuja lisättävä
	 */
	public void lisaa(Osallistuja osallistuja) {
		for (int i = 0; i < lkm; i++) {
			if (osallistujat[i] != null && osallistujat[i].getId() == osallistuja.getId()) return;
		}
		if ( lkm + 2 > maxLkm ) lisaaTilaa();
		osallistujat[lkm] = osallistuja;
		viimId = osallistuja.getId();
		lkm++;
	}
	
	/**
	 * Lisää uuden osallistujan
	 * @param osallistuja
	 */
	public void lisaaUusi(Osallistuja osallistuja) {
		osallistuja.setId(viimId + 1);
		lisaa(osallistuja);
	}
	
	/**
	 * Muokkaa osallistujalle uudet tiedot,
	 * käytännössä antaa uudelle vanhan id:n
	 * ja siirtää uuden sen tilalle
	 * @param muokattava muokattava
	 * @param uusi uusi
	 */
	public void muokkaa(Osallistuja muokattava, Osallistuja uusi) {
		if (muokattava == null || uusi == null) return;
		uusi.setId(muokattava.getId());
		for (int i = 0; i < lkm; i++) {
			if (osallistujat[i].getId() == muokattava.getId()) osallistujat[i] = uusi;
		}
	}
	
	/**
	 * Poistaa osallistujan
	 * @param osall osallistuja
	 */
	public void poista(Osallistuja osall) {
		for (int i = 0; i < lkm; i++) {
			if (osallistujat[i] != null && osallistujat[i].getId() == osall.getId()) {
				osallistujat[i] = null;
				return;		
			}
		}
	}
	
	/**
	 * Palauttaa osallistujien lukumäärän
	 * @return osallistujine lkm
	 */
	public int getLkm() {
		return lkm;
	}
	
	/**
	 * Palauttaa osallistujan jolla on annettu id
	 * @param osId id
	 * @return osallistuja
	 */
	public Osallistuja get(int osId) {
		
		for (int i = 0; i < lkm; i++) {
			if (osallistujat[i] != null && osId == osallistujat[i].getId()) return osallistujat[i];
		}
	
		return null;
	}
	
	/**
	 * Lisää taulukkoon tilaa
	 */
	private void lisaaTilaa() {
		Osallistuja[] vanha = osallistujat;
		osallistujat = new Osallistuja[maxLkm + 10];
		maxLkm += 10; 
		for (int i = 0; i < lkm; i++) {
			osallistujat[i] = vanha[i];
		}
	}
	
	/**
	 * Palauttaa listan, jossa on kaikki osallistujat
	 * @return lista
	 */
	public ObservableList<Osallistuja> getLista() {
		
		ObservableList<Osallistuja> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < lkm; i++) {
			if (osallistujat[i] != null) {
				osallistujat[i].setAika(new Aika(0));
				lista.add(osallistujat[i]);
			}
		}
		
		return lista;
	}
	
	/**
	 * Palauttaa kaikki tiettyyn erikoiskokeeseen osallistuneet osallistujat
	 * @param ekId Erikoiskokeen id
	 * @return Lista erikoiskokeeseen osallistuneista
	 */
	public ObservableList<Osallistuja> getLista(int ekId) {
		
		ObservableList<Osallistuja> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < lkm; i++) {
			if (osallistujat[i] != null) {
				lista.add(osallistujat[i]);
			}
		}
		
		return lista;
	}
	
	/**
	 * Tallentaa tiedot tiedostoon
	 */
	public void tallenna() {
		
		try {
			FileWriter fw = new FileWriter(FILEPATH + "_temp.dat");
			fw.write(";id |Sukunimi       |Etunimi	|Kans.  |Kart.Sukun.|Kart.Etun.	|Kart.kans. |Auto       |Talli          |\n");
			for (int i = 0; i < lkm; i++) {
				if (osallistujat[i] != null) fw.write(osallistujat[i].tiedostomuotoon() + "\n");
			}
			fw.close();
			fw = new FileWriter(FILEPATH);
			BufferedReader br = new BufferedReader(new FileReader(FILEPATH + "_temp.dat"));
			String rivi;
			while ((rivi = br.readLine()) != null) {
				fw.write(rivi + "\n");
			}
			br.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Lukee ja luo oliot tiedostosta
	 */
	public void lueTiedostosta() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
			String rivi;
			while ((rivi = br.readLine()) != null) {
				if(!rivi.startsWith(";")) {
					this.lisaa(Osallistuja.parseToOsallistuja(rivi));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("Virhe: "+e.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println("Virhe: "+e.getLocalizedMessage());
		} 
	}
}
