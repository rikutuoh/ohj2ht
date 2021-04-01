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
	private ObservableList<Ralli> rallit;
	private int viimId;
	
	/**
	 * Muodostaja, alustaa
	 * taulukon lkm ja maxLkm
	 */
	public Rallit() {
		rallit = FXCollections.observableArrayList();
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
		if(rallit.remove(muokattava))
			rallit.add(uusi);
	}
	
	/**
	 * Lisää rallin taulukkoon
	 * @param ralli lisättävä
	 */
	public void lisaa(Ralli ralli) {
		rallit.add(ralli);
		viimId = ralli.getId();
	}
	
	/**
	 * Lisää uuden rallin
	 * @param ralli uusi
	 */
	public void lisaaUusi(Ralli ralli) {
		ralli.setId(viimId + 1);
		lisaa(ralli);
	}
	
	/**
	 * Poistaa rallin
	 * @param ralli poistettava
	 */
	public void poista(Ralli ralli) {
		rallit.remove(ralli);
	}
	
	/**
	 * Palauttaa listan kaikista ralleista
	 * @return Lista
	 */
	public ObservableList<Ralli> getLista() {
		return rallit;
	}
	
	/**
	 * Tallentaa tiedot tiedostoon
	 */
	public void tallenna() {
		rallit.sort((r1, r2) -> r1.getId() - r2.getId());
		try {
			FileWriter fw = new FileWriter(FILEPATH + "_temp.dat");
			fw.write(";id|Nimi                    |Paikka         |Alkupvm    |Loppupvm   |\n");
			for (Ralli ralli : rallit) {
				if (ralli != null) fw.write(ralli.tiedostomuotoon() + "\n");
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
	
