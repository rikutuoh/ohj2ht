package rtk;

import java.time.LocalDate;

/**
 * Erikoiskoe-luokka
 * @author Riku Tuohimetsä
 * @version 23.3.2021
 */

public class Erikoiskoe {
	
	private String nimi;
	private LocalDate pvm;
	private int id, ralliId;
	private double pituus;
	
	/**
	 * Muodostaja erikoiskokeelle, missä annetaan vain nimi
	 * asettaa päivämääräksi tämän päivän
	 * @param nimi Nimi
	 */
	public Erikoiskoe(String nimi) {
		this.nimi = nimi; this.pvm = LocalDate.now();
	}
	
	/**
	 * Muodostaja erikoiskokeelle, missä annetaan kaikki tiedot
	 * @param id Erikoiskokeen id
	 * @param ralliId Rallin id
	 * @param nimi Erikoiskokeen nimi
	 * @param pituus Erikoiskokeen pituus km
	 * @param pvm Tapahtuman päivämäärä
	 */
	public Erikoiskoe(int id, int ralliId, String nimi, double pituus, LocalDate pvm) {
		this.id = id; this.ralliId = ralliId; this.nimi = nimi; this.pituus = pituus; this.pvm = pvm;
	}
	
	/**
	 * Palauttaa olion id:n
	 * @return olion id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Erikoiskokeen sisältäneen rallin id
	 * @return rallin id
	 */
	public int getRalliId() {
		return ralliId;
	}
	
	/**
	 * Palauttaa erikoiskokeen nimen
	 * @return nimi
	 */
	public String getNimi() {
		return nimi;
	}
	
	/**
	 * Palauttaa erikoiskokeen päivämäärän
	 * @return
	 */
	public LocalDate getPvm() {
		return pvm;
	}
	
	/**
	 * Asettaa id:n
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Palauttaa erikoiskokeen tiedot luettavassa muodossa
	 */
	public String toString() {
		if (pvm != null) return getNimi() + ", " + pituus + "km, " + pvm.toString();
		else return getNimi() + ", " + pituus + "km";
	}
	
	/**
	 * Palauttaa olion tiedot tiedostoon tallennettavassa muodossa
	 * @return olion tiedot tiedostoon tallennettavassa muodossa
	 */
	public String tiedostomuotoon() {
		return String.format("%-4s", id) + "|" + String.format("%-11s", ralliId) + "|" + String.format("%-15s", nimi) + "|" + String.format("%-11s", pituus) + "|" + String.format("%-11s", pvm.toString()) + "|";
	}
	
	/**
	 * Muuttaa tiedostosta saatavan merkkijonon erikoiskoe-olioksi
	 * @param s tiedostosta saatu merkkijono
	 * @return erikoiskoe-olio
	 */
	public static Erikoiskoe parseToErikoiskoe(String s) {
		
		String[] sArr = s.split("\\|");
		for (int i = 0; i < sArr.length; i++) sArr[i] = sArr[i].trim();
		
		Erikoiskoe osall = new Erikoiskoe(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]), sArr[2], Double.parseDouble(sArr[3]), LocalDate.parse(sArr[4]));
		
		return osall;
	}

}
