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
 * @author rikut
 * @version 23.3.2021
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
