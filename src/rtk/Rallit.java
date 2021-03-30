package rtk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Rallit-luokka
 * @author rikut
 * @version 23.3.2021
 */
public class Rallit {
	
	private static final String FILEPATH = ".\\src\\data\\rallit.dat";
	private Ralli[] rallit;
	private int lkm, maxLkm, viimId;
	
	/**
	 * Muodostaja, alustaa
	 * taulukon lkm ja maxLkm
	 */
	public Rallit() {
		lkm = 0;
		maxLkm = 10;
		rallit = new Ralli[maxLkm];
	}
	
	/**
	 * Muokkaa rallille uudet tiedot,
	 * käytännössä antaa uudelle vanhan id:n
	 * ja siirtää uuden sen tilalle
	 * @param muokattava muokattava
	 * @param uusi uusi
	 */
	public void muokkaa(Ralli muokattava, Ralli uusi) {
		uusi.setId(muokattava.getId());
		for (int i = 0; i < lkm; i++) {
			if (rallit[i].getId() == muokattava.getId()) rallit[i] = uusi;
		}
	}
	
	/**
	 * Lisää rallin taulukkoon
	 * @param ralli lisättävä
	 */
	public void lisaa(Ralli ralli) {
		if ( lkm + 2 > maxLkm ) lisaaTilaa();
		rallit[lkm] = ralli;
		viimId = ralli.getId();
		lkm++;
	}
	
	/**
	 * Lisää uuden rallin
	 * @param ralli uusi
	 */
	public void lisaaUusi(Ralli ralli) {
		ralli.setId(viimId + 1);
		lisaa(ralli);
	}
	
	public void poista(Ralli ralli) {
		for (int i = 0; i < lkm; i++) {
			if (rallit[i] != null && rallit[i].getId() == ralli.getId()) {
				rallit[i] = null;
				return;
			}
		}
	}
	
	
	/**
	 * Palauttaa taulukon kaikista ralleista
	 * @return taulukko
	 */
	public Ralli[] getKaikki() {
		return rallit;
	}
	
	/**
	 * Palauttaa rallien lukumäärän
	 * @return lkms
	 */
	public int getLkm() {
		return lkm;
	}
	
	/**
	 * Lisää tilaa taulukkoon
	 */
	private void lisaaTilaa() {
		Ralli[] vanha = rallit;
		rallit = new Ralli[maxLkm + 10];
		maxLkm += 10; 
		for (int i = 0; i < lkm; i++) {
			rallit[i] = vanha[i];
		}
	}
	
	/**
	 * Palauttaa listan kaikista ralleista
	 * @return Lista
	 */
	public ObservableList<Ralli> getLista() {
		
		ObservableList<Ralli> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < lkm; i++) {
			if (rallit[i] != null) {
				lista.add(rallit[i]);
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
			fw.write(";id|Nimi                    |Paikka         |Alkupvm    |Loppupvm   |\n");
			for (int i = 0; i < lkm; i++) {
				if (rallit[i] != null) fw.write(rallit[i].tiedostomuotoon() + "\n");
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
					this.lisaa(Ralli.parseToRalli(rivi));
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
	
