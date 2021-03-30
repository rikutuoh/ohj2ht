package rtk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Erikoiskokeet-luokka
 * @author rikut
 * @version 23.3.2021
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
		uusi.setId(muokattava.getId());
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i].getId() == muokattava.getId()) erikoiskokeet[i] = uusi;
		}
	}
	
	public void poista(Erikoiskoe ek) {
		for (int i = 0; i < lkm; i++) {
			if (erikoiskokeet[i] != null && erikoiskokeet[i].getId() == ek.getId()) {
				erikoiskokeet[i] = null;
				return;
			}
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
		
		} catch (FileNotFoundException e) {
			System.err.println("Virhe: "+e.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println("Virhe: "+e.getLocalizedMessage());
		} 
	}


}
