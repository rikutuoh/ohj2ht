package rtk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Ajat-luokka
 * @author Riku Tuohimetsä
 * @version 29.3.2021
 * <pre name="test">
 * #import java.util.Arrays;
 * Ajat ajat = new Ajat();
 *  // Aika aika = new Aika(int id, int ekId, int osId, int aika);
 * Aika aika1 = new Aika(1, 1, 1, 10);
 * Aika aika2 = new Aika(2, 1, 2, 41241);
 * Aika aika3 = new Aika(3, 1, 3, 544353);
 * Aika aika4 = new Aika(4, 2, 1, 10000);
 * Aika aika5 = new Aika(5, 2, 2, 60000);
 * Aika aika6 = new Aika(6, 3, 1, 0);
 * Aika aika7 = new Aika(7, 3, 2, -1);
 * ajat.lisaa(aika1); ajat.lisaa(aika2); ajat.lisaa(aika3); ajat.lisaa(aika4); ajat.lisaa(aika5); ajat.lisaa(aika6); ajat.lisaa(aika7);
 * Arrays.toString(ajat.getKaikki()) === "[00.00,01, 00.41,24, 09.04,35, 00.10,00, 01.00,00, 00.00,00, 00.00,00, null, null, null]";
 * ajat.poista(new Osallistuja(1, "", "", "", "", "", "", "", ""), new Erikoiskoe(1, 1, "", 0, null));
 * Arrays.toString(ajat.getKaikki()) === "[null, 00.41,24, 09.04,35, 00.10,00, 01.00,00, 00.00,00, 00.00,00, null, null, null]";
 * ajat.poistaKaikki(new Osallistuja(1, "", "", "", "", "", "", "", ""));
 * Arrays.toString(ajat.getKaikki()) === "[null, 00.41,24, 09.04,35, null, 01.00,00, null, 00.00,00, null, null, null]";
 * ajat.poistaKaikki(new Erikoiskoe(1, 1, "", 0, null));
 * Arrays.toString(ajat.getKaikki()) === "[null, null, null, null, 01.00,00, null, 00.00,00, null, null, null]";
 * </pre>
 */
public class Ajat {
	
	private static final String FILEPATH = ".\\src\\data\\ajat.dat";
	private Aika[] ajat;
	private int lkm, maxLkm, viimId;
	
	/**
	 * Muodostaja ajat-oliolle,
	 * alustaa taulukon, lkm ja maxLkm
	 */
	public Ajat() {
		lkm = 0;
		maxLkm = 10;
		ajat = new Aika[maxLkm];
		viimId = 0;
	}
	
	/**
	 * Lisää aikaolion taulukkoon
	 * @param aika lisättävä aikaolio
	 */
	public void lisaa(Aika aika) {
		for (int i = 0; i < lkm; i++) {
			if (ajat[i] != null && ajat[i].getId() == aika.getId()) return;
		}
		if ( lkm + 2 > maxLkm ) lisaaTilaa();
		ajat[lkm] = aika;
		viimId = aika.getId();
		lkm++;
	}
	
	/**
	 * Lisää uuden ajan
	 * @param aika lisättävä
	 */
	public void lisaaUusi(Aika aika) {
		aika.setId(viimId + 1);
		lisaa(aika);
	}
	
	/**
	 * palauttaa kaikki taulukossa olevat aikaoliot
	 * @return taulukko, jossa kaikki ajat
	 */
	public Aika[] getKaikki() {
		return ajat;
	}
	
	/**
	 * Palauttaa aikojen lukumäärän
	 * @return aikojen lukumäärä
	 */
	public int getLkm() {
		return lkm;
	}
	
	
	/**
	 * Lisää taulukkoon tilaa +10
	 */
	private void lisaaTilaa() {
		Aika[] vanha = ajat;
		ajat = new Aika[maxLkm + 10];
		maxLkm += 10; 
		for (int i = 0; i < lkm; i++) {
			ajat[i] = vanha[i];
		}
	}
	
	/**
	 * Poistaa ajan
	 * @param osall Osallistuja, jonka aika poistetaan
	 * @param ek Erikoiskoe, jolta aika poistetaan
	 */
	public void poista(Osallistuja osall, Erikoiskoe ek) {
		for (int i = 0; i < lkm; i++) {
			if (ajat[i] != null && ajat[i].getEkId() == ek.getId() && ajat[i].getOsId() == osall.getId()) {
				ajat[i] = null;
				return;
			}
		}
	}
	
	/**
	 * Poistaa kaikki osallistujan ajat
	 * @param osall osallistuja
	 */
	public void poistaKaikki(Osallistuja osall) {
		for (int i = 0; i < lkm; i++) {
			if (ajat[i] != null && ajat[i].getOsId() == osall.getId()) ajat[i] = null;
		}
	}
	
	/**
	 * Poistaa kaikki erikoiskokeen ajat
	 * @param ek erikoiskoe, jonka ajat poistetaan
	 */
	public void poistaKaikki(Erikoiskoe ek) {
		for (int i = 0; i < lkm; i++) {
			if (ajat[i] != null && ajat[i].getEkId() == ek.getId()) ajat[i] = null;
		}
	}
	
	/**
	 * Palauttaa tietyn osallistujan ajan tietyllä erikoiskokeella
	 * @param osId osallistujan id
	 * @param ekId erikoiskokeen id
	 * @return Aika
	 */
	public Aika get(int osId, int ekId) {
		for (Aika aika : ajat) {
			if (aika.getEkId() == ekId && aika.getOsId() == osId) {
				return aika;
			}
		}
		
		return null;
	}
	
	/**
	 * Palauttaa ObservableList listan kaikista aikaolioista
	 * @return Kaikki aikaoliot
	 */
	public ObservableList<Aika> getLista() {
		
		ObservableList<Aika> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < lkm; i++) {
			if (ajat[i] != null) {
				lista.add(ajat[i]);
			}
		}
		
		return lista;
	}
	
	/**
	 * Palauttaa ObservableList listan kaikista tietyn erikoiskokeen ajoista
	 * @param ekId erikoiskokeen id
	 * @return Lista erikoiskokeen ajoista
	 */
	public ObservableList<Aika> getLista(int ekId) {
		
		ObservableList<Aika> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < lkm; i++) {
			if (ajat[i] != null && ajat[i].getEkId() == ekId) {
				lista.add(ajat[i]);
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
			fw.write(";id |EK id  |Kuski id   |Aika(ms)   |\n");
			for (int i = 0; i < lkm; i++) {
				if (ajat[i] != null) fw.write(ajat[i].tiedostomuotoon() + "\n");
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
	 * Lukee ja lisää tiedostosta aikaoliot
	 */
	public void lueTiedostosta() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
			String rivi;
			
			while ((rivi = br.readLine()) != null) {
				if(!rivi.startsWith(";")) {
					this.lisaa(Aika.parseToAika(rivi));
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
