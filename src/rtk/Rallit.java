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
 * @author Riku Tuohimetsä
 * @version 23.3.2021
 * <pre name="test">
 * #import java.time.LocalDate;
 * #import java.util.Arrays;
 * Rallit rallit = new Rallit();
 * Ralli ralli1 = new Ralli(1, "Ralli1", "Paikka1", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
 * Ralli ralli2 = new Ralli(2, "Ralli2", "Paikka2", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
 * Ralli ralli3 = new Ralli(3, "Ralli3", "Paikka3", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
 * Ralli ralli4 = new Ralli(4, "Ralli4", "Paikka4", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
 * Ralli ralli5 = new Ralli(5, "Ralli5", "Paikka5", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
 * Ralli ralli6 = new Ralli(6, "Ralli6", "Paikka6", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1));
 * rallit.lisaa(ralli1);
 * rallit.lisaa(ralli2);
 * rallit.lisaa(ralli3);
 * rallit.lisaa(ralli4);
 * rallit.lisaa(ralli5);
 * Arrays.toString(rallit.getLista().toArray()) === "[Ralli1, 2020-01-01 - 2020-01-01, Ralli2, 2020-01-01 - 2020-01-01, Ralli3, 2020-01-01 - 2020-01-01, Ralli4, 2020-01-01 - 2020-01-01, Ralli5, 2020-01-01 - 2020-01-01]";
 * rallit.lisaa(ralli1);
 * Arrays.toString(rallit.getLista().toArray()) === "[Ralli1, 2020-01-01 - 2020-01-01, Ralli2, 2020-01-01 - 2020-01-01, Ralli3, 2020-01-01 - 2020-01-01, Ralli4, 2020-01-01 - 2020-01-01, Ralli5, 2020-01-01 - 2020-01-01]";
 * rallit.muokkaa(ralli1, new Ralli(99, "Ralli1Muokattu", "Paikka1Muokattu", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1)));
 * Arrays.toString(rallit.getLista().toArray()) === "[Ralli1Muokattu, 2020-01-01 - 2020-01-01, Ralli2, 2020-01-01 - 2020-01-01, Ralli3, 2020-01-01 - 2020-01-01, Ralli4, 2020-01-01 - 2020-01-01, Ralli5, 2020-01-01 - 2020-01-01]";
 * rallit.muokkaa(ralli1, null);
 * Arrays.toString(rallit.getLista().toArray()) === "[Ralli1Muokattu, 2020-01-01 - 2020-01-01, Ralli2, 2020-01-01 - 2020-01-01, Ralli3, 2020-01-01 - 2020-01-01, Ralli4, 2020-01-01 - 2020-01-01, Ralli5, 2020-01-01 - 2020-01-01]";
 * rallit.poista(ralli2);
 * Arrays.toString(rallit.getLista().toArray()) === "[Ralli1Muokattu, 2020-01-01 - 2020-01-01, Ralli3, 2020-01-01 - 2020-01-01, Ralli4, 2020-01-01 - 2020-01-01, Ralli5, 2020-01-01 - 2020-01-01]";
 * </pre>
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
		if (muokattava == null || uusi == null) return;
		uusi.setId(muokattava.getId());
		if (rallit.contains(muokattava)) {
			rallit.add(rallit.indexOf(muokattava), uusi);
			rallit.remove(muokattava);
		}
	}
	
	/**
	 * Lisää rallin taulukkoon
	 * @param ralli lisättävä
	 */
	public void lisaa(Ralli ralli) {
		if (rallit.contains(ralli)) return;
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
	
