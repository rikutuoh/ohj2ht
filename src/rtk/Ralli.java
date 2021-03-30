package rtk;

import java.time.LocalDate;

/**
 * Ralli-luokka
 * @author rikut
 * @version 23.3.2021
 */
public class Ralli {
	
	private String nimi, paikka;
	private LocalDate alkuPvm, loppuPvm;
	private int id;
	
	/**
	 * Muodostaja, jolle annetaan nimi ja paikka
	 * @param nimi Nimi 
	 * @param paik Paikka
	 */
	public Ralli(String nimi, String paikka) {
		this.nimi = nimi; this.paikka = paikka;
	}
	
	/**
	 * Muodostaja jolle annetaan kaikki tiedot
	 * @param id id
	 * @param nimi nimi
	 * @param paikka paikka 
	 * @param alkuPvm alkupvm
	 * @param loppuPvm loppupvm
	 */
	public Ralli(int id, String nimi, String paikka, LocalDate alkuPvm, LocalDate loppuPvm) {
		this.id = id; this.nimi = nimi; this.paikka = paikka; this.alkuPvm = alkuPvm; this.loppuPvm = loppuPvm;
	}
	
	/**
	 * Palauttaa rallin nimen
	 * @return nimi
	 */
	public String getNimi() {
		return nimi;
	}
	
	/**
	 * Palauttaa rallin id:n
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Palauttaa rallin paikan
	 * @return paikka
	 */
	public String getPaikka() {
		return paikka;
	}
	
	/**
	 * Palauttaa tapahtuman alkamispäivän
	 * @return alkupäivä
	 */
	public LocalDate getAlkuP() {
		return this.alkuPvm;
	}
	
	/**
	 * Palauttaa tapahtuman loppupäivän
	 * @return loppupäivä
	 */
	public LocalDate getLoppuP() {
		return this.loppuPvm;
	}
	
	/**
	 * Palauttaa rallin tiedot luettavana
	 */
	public String toString() {
		if (alkuPvm != null && loppuPvm != null) return getNimi() + ", " + getDate();
		else return getNimi();
	}
	
	/**
	 * Palauttaa rallien alku- ja loppupäivämäärät
	 * @return alkupvm - loppupvm
	 */
	public String getDate() {
		return alkuPvm.toString() + " - " + loppuPvm.toString();
	}
	
	/**
	 * Asettaa id:n
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Palauttaa olion tiedot tiedostoon tallennettavassa muodossa
	 * @return olion tiedot tiedostoon tallennettavassa muodossa
	 */
	public String tiedostomuotoon() {
		return String.format("%-3d", id) + "|" + String.format("%-24s", nimi) + "|" + String.format("%-15s", paikka) + "|" + String.format("%-11s", alkuPvm.toString()) + "|" + String.format("%-11s", loppuPvm.toString()) + "|";
	}
	
	/**
	 * Luo olion tiedostosta saadun merkkkijonon avulla
	 * @param s merkkijono
	 * @return olio
	 */
	public static Ralli parseToRalli(String s) {
		String[] sArr = s.split("\\|");
		for (int i = 0; i < sArr.length; i++) sArr[i] = sArr[i].trim();
		
		Ralli osall = new Ralli(Integer.parseInt(sArr[0]), sArr[1], sArr[2], LocalDate.parse(sArr[3]), LocalDate.parse(sArr[4]));
		
		return osall;
	}

}
