package rtk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Erikoiskokeet-luokka
 * @author Riku Tuohimetsä
 * @version 23.3.2021
 * 
 * <pre name="test">
 * #import java.time.LocalDate;
 * #import java.util.Arrays;
 * Erikoiskokeet ekt = new Erikoiskokeet();
 * Erikoiskoe ek1 = new Erikoiskoe(1, 1, "Ek1", 1, LocalDate.of(2020, 1, 1));
 * Erikoiskoe ek2 = new Erikoiskoe(2, 1, "Ek2", 2, LocalDate.of(2020, 1, 1));
 * Erikoiskoe ek3 = new Erikoiskoe(3, 2, "Ek3", 3, LocalDate.of(2020, 1, 1));
 * Erikoiskoe ek4 = new Erikoiskoe(4, 3, "Ek4", 4, LocalDate.of(2020, 1, 1));
 * Erikoiskoe ek5 = new Erikoiskoe(5, 2, "Ek5", 5, LocalDate.of(2020, 1, 1));
 * Erikoiskoe ek6 = new Erikoiskoe(99, 2, "Ek6", 6, LocalDate.of(2020, 1, 1));
 * ekt.lisaa(ek1);
 * ekt.lisaa(ek2);
 * ekt.lisaa(ek3);
 * ekt.lisaa(ek4);
 * ekt.lisaa(ek5);
 * Arrays.toString(ekt.getKaikki()) === "[Ek1, 1.0km, 2020-01-01, Ek2, 2.0km, 2020-01-01, Ek3, 3.0km, 2020-01-01, Ek4, 4.0km, 2020-01-01, Ek5, 5.0km, 2020-01-01, null, null, null, null, null]";
 * ekt.lisaa(ek1);
 * Arrays.toString(ekt.getKaikki()) === "[Ek1, 1.0km, 2020-01-01, Ek2, 2.0km, 2020-01-01, Ek3, 3.0km, 2020-01-01, Ek4, 4.0km, 2020-01-01, Ek5, 5.0km, 2020-01-01, null, null, null, null, null]";
 * ekt.muokkaa(ek1, new Erikoiskoe(0, 1, "Ek1Muokattu", 1.5, LocalDate.of(2020, 1, 1)));
 * Arrays.toString(ekt.getKaikki()) === "[Ek1Muokattu, 1.5km, 2020-01-01, Ek2, 2.0km, 2020-01-01, Ek3, 3.0km, 2020-01-01, Ek4, 4.0km, 2020-01-01, Ek5, 5.0km, 2020-01-01, null, null, null, null, null]";
 * ekt.muokkaa(ek1, null);
 * Arrays.toString(ekt.getKaikki()) === "[Ek1Muokattu, 1.5km, 2020-01-01, Ek2, 2.0km, 2020-01-01, Ek3, 3.0km, 2020-01-01, Ek4, 4.0km, 2020-01-01, Ek5, 5.0km, 2020-01-01, null, null, null, null, null]";
 * ekt.lisaaUusi(ek6);
 * Arrays.toString(ekt.getKaikki()) === "[Ek1Muokattu, 1.5km, 2020-01-01, Ek2, 2.0km, 2020-01-01, Ek3, 3.0km, 2020-01-01, Ek4, 4.0km, 2020-01-01, Ek5, 5.0km, 2020-01-01, Ek6, 6.0km, 2020-01-01, null, null, null, null]";
 * ekt.poista(ek1);
 * Arrays.toString(ekt.getKaikki()) === "[null, Ek2, 2.0km, 2020-01-01, Ek3, 3.0km, 2020-01-01, Ek4, 4.0km, 2020-01-01, Ek5, 5.0km, 2020-01-01, Ek6, 6.0km, 2020-01-01, null, null, null, null]";
 * </pre>
 */
public class Erikoiskokeet {
	
	private static final String FILEPATH = ".\\src\\data\\ek.dat";
	private Erikoiskoe[] erikoiskokeet;
	private int lkm, maxLkm, viimId;
	
	/**
	 * Muodostaja erikoiskokeet-oliolle
	 * alustaa taulukon, lkm ja maxLkm
	 */
	public Erikoiskokeet() {
		lkm = 0;
		maxLkm = 10;
		erikoiskokeet = new Erikoiskoe[maxLkm];
		viimId = 0;
	}
	
	/**
	 * Lisää erikoiskokeen taulukkoon
	 * @param ek lisättävä
	 */
	public void lisaa(Erikoiskoe ek) {
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i] != null && erikoiskokeet[i].getId() == ek.getId()) return;
		}
		if ( lkm + 2 > maxLkm ) lisaaTilaa();
		erikoiskokeet[lkm] = ek;
		viimId = ek.getId();
		lkm++;
	}
	
	/**
	 * Lisää uuden erikoiskokeen
	 * @param ek uusi
	 */
	public void lisaaUusi(Erikoiskoe ek) {
		ek.setId(viimId + 1);
		lisaa(ek);
	}
	
	/**
	 * Muokkaa erikoiskokeen tiedot,
	 * eli antaa uudelle vanhan id:n
	 * ja asettaa vanhan tilalle uuden
	 * @param muokattava muokattava
	 * @param uusi uusi
	 */
	public void muokkaa(Erikoiskoe muokattava, Erikoiskoe uusi) {
		if (muokattava == null || uusi == null) return;
		uusi.setId(muokattava.getId());
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i].getId() == muokattava.getId()) erikoiskokeet[i] = uusi;
		}
	}
	
	/**
	 * Poistaa erikoiskokeen
	 * @param ek poistettava
	 */
	public void poista(Erikoiskoe ek) {
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i] != null && erikoiskokeet[i].getId() == ek.getId()) {
				erikoiskokeet[i] = null;
				return;
			}
		}
	}
	
	/**
	 * Poistaa kaikki tiettyyn ralliin kuuluvat erikoiskokeet
	 * @param poistettava ralli jonka poistetaan
	 */
	public void poistaKaikki(Ralli poistettava) {
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i] != null && erikoiskokeet[i].getRalliId() == poistettava.getId()) erikoiskokeet[i] = null; 
		}	
	}

	/**
	 * palauttaa kaikki erikoiskokeet
	 * @return taulukko kaikista erikoiskokeista
	 */
	public Erikoiskoe[] getKaikki() {
		return erikoiskokeet;
	}
	
	/**
	 * palauttaa erikoiskokeiden lukumäärän
	 * @return erikoiskokeiden lkm
	 */
	public int getLkm() {
		return lkm;
	}
	
	/**
	 * lisää tilaa taulukkoon
	 */
	private void lisaaTilaa() {
		Erikoiskoe[] vanha = erikoiskokeet;
		erikoiskokeet = new Erikoiskoe[maxLkm + 10];
		maxLkm += 10; 
		for (int i = 0; i < lkm; i++) {
			erikoiskokeet[i] = vanha[i];
		}
	}
	
	/**
	 * Palauttaa listan kaikista erikoiskokeista
	 * @return Lista
	 */
	public ObservableList<Erikoiskoe> getLista() {
		
		ObservableList<Erikoiskoe> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i] != null) {
				lista.add(erikoiskokeet[i]);
			}
		}
		
		return lista;
	}
	
	/**
	 * Palauttaa listan kaikista tietylle rallille kuuluvista erikoiskokeista
	 * @param ralliId rallin id
	 * @return Lista
	 */
	public ObservableList<Erikoiskoe> getLista(int ralliId) {
		
		ObservableList<Erikoiskoe> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i] != null && erikoiskokeet[i].getRalliId() == ralliId) {
				lista.add(erikoiskokeet[i]);
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
			fw.write(";id |Ralli id   |Nimi           |Pituus(km) |Pvm        |\n");
			for (int i = 0; i < lkm; i++) {
				if (erikoiskokeet[i] != null) fw.write(erikoiskokeet[i].tiedostomuotoon() + "\n");
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
	 * Lukee ja lisää oliot tiedostosta
	 */
	public void lueTiedostosta() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
			String rivi;
			
			while ((rivi = br.readLine()) != null) {
				if(!rivi.startsWith(";")) {
					this.lisaa(Erikoiskoe.parseToErikoiskoe(rivi));
				}
			}
			br.close();
		
		} catch (Exception e) {
			System.err.println("Virhe: "+e.getLocalizedMessage());
		} 
	}
}
