package rtk;

/**
 * Osallistuja-luokka
 * @author rikut
 * @version 29.3.2021
 */
public class Osallistuja {
	
	private String etunimi, sukunimi, kans, talli, auto, kartetunimi, kartsukunimi, kartKans;
	private int id;
	private Aika aika;
	
	/**
	 * Muodostaja osallistujalle, missä annetaan kuskin ja kartturin nimi, talli ja auto
	 * @param etunimi Kuskin etunimi
	 * @param sukunimi Kuskin sukunimi
	 * @param tal Osallistujan talli
	 * @param aut Osallistujan auto
	 * @param kartet Kartturin etunimi
	 * @param kartsuk Kartturin sukunimi
	 */
	public Osallistuja(String etunimi, String sukunimi, String talli, String auto, String kartet, String kartsuk) {
		this.etunimi = etunimi; this.sukunimi = sukunimi; this.talli = talli; this.auto = auto; this.kartetunimi = kartet; this.kartsukunimi = kartsuk;
	}
	
	/**
	 * Muodostaja osallistujalle, missä annetaan kaikki osallistujan tiedot
	 * @param id Osallistujan id
	 * @param sukunimi Kuskin sukunimi 
	 * @param etunimi Kuskin etunimi
	 * @param kans kuskin kansallisuus
	 * @param kartsukunimi kartturin sukunimi
	 * @param kartetunimi kartturin etunimi
	 * @param kartKans kartturin kansallisuus
	 * @param auto auto
	 * @param talli talli
	 */
	public Osallistuja(int id, String sukunimi, String etunimi, String kans, String kartsukunimi, String kartetunimi, String kartKans, String auto, String talli) {
		this.id = id; this.sukunimi = sukunimi; this.etunimi = etunimi; this.kans = kans; 
		this.kartsukunimi = kartsukunimi; this.kartetunimi = kartetunimi; this.kartKans = kartKans; 
		this.auto = auto; this.talli = talli;
	}
	
	/**
	 * Palauttaa osallistujan id:n
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Palauttaa kuljettajan nimen
	 * @return kuskin nimi
	 */
	public String getNimi() {
		if (kans.equals("")) return etunimi + " " + sukunimi;
		return sukunimi + " " + etunimi + ", " + kans;
	}
	
	/**
	 * palauttaa kuskin etunimen
	 * @return kuskin etunimi
	 */
	public String getKuskiEtu() {
		return this.etunimi;
	}
	
	/**
	 * palauttaa kuskin sukunimen
	 * @return kuskin sukunimi
	 */
	public String getKuskiSuku() {
		return this.sukunimi;
	}
	
	/**
	 * Palauttaa kuljettajan kansallisuuden
	 * @return kuljettajan kansallisuus
	 */
	public String getKuskiKans() {
		return this.kans;
	}
	
	/**
	 * Palauttaa kartturin etunimen
	 * @return kartturin etunimi
 	 */
	public String getKartEtu() {
		return this.kartetunimi;
	}
	
	/**
	 * Palauttaa kartturin sukunimen
	 * @return kartturin sukunimi
	 */
	public String getKartSuku() {
		return this.kartsukunimi;
	}
	
	/**
	 * Palauttaa kartanlukijan nimen
	 * @return kartturin nimi
	 */
	public String getKartturi() {
		if (kartKans.equals("")) return kartetunimi + " " + kartsukunimi;
		return kartetunimi + " " + kartsukunimi + ", " + kartKans;
	}
	
	/**
	 * Palauttaa kartanlukijan kansallisuuden
	 * @return kartanlukijan kansallisuus
	 */
	public String getKartturiKans() {
		return this.kartKans;
	}
	
	/**
	 * Palauttaa osallistujan tallin
	 * @return osallistujan talli
	 */
	public String getTalli() {
		return talli;
	}
	
	/**
	 * Palauttaa osallistujan auton
	 * @return osallistujan auto
	 */
	public String getAuto() {
		return auto;
	}
	
	/**
	 * Palauttaa osallistujalle väliaikaisen aika-olion
	 * Tämä tarvitaan TableViewin toimimiseen
	 * Jos aika on null, palauttaa ajan 0
	 * @return osallistujan asetettu aika
	 */
	public Aika getAika() {
		if (aika != null) return aika;
		return new Aika("00,00.00");
 	}
	
	/**
	 * Asettaa osallistujalle ajan väliaikaista käyttöä
	 * varten. Tarvitaan TableViewin toimintaa varten
	 * @param aika Asetettava aika
	 */
	public void setAika(Aika aika) {
		this.aika = aika;
	}
	
	/**
	 * Asettaa osallistujalle id:n
	 * @param id asetettava
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Muodostaa ja palauttaa tiedostosta saadun merkkijonon mukaisen
	 * osallistuja olion
	 * @param s merkkijono
	 * @return olio
	 */
	public static Osallistuja parseToOsallistuja(String s) {
		
		String[] sArr = s.split("\\|");
		for (int i = 0; i < sArr.length; i++) sArr[i] = sArr[i].trim();
		
		Osallistuja osall = new Osallistuja(Integer.parseInt(sArr[0]), sArr[1], sArr[2], sArr[3], sArr[4], sArr[5], sArr[6], sArr[7], sArr[8]);
		
		return osall;
	}
	
	/**
	 * Palauttaa olion tiedot tiedostoon tallennettavassa muodossa
	 * @return olion tiedot tiedostoon tallennettavassa muodossa
	 */
	public String tiedostomuotoon() {
		return String.format("%-4d", id) + "|" + String.format("%-15s", sukunimi) + "|" + String.format("%-11s", etunimi) + "|" + String.format("%-7s", kans) +
			"|" + String.format("%-11s", kartsukunimi) + "|" + String.format("%-11s", kartetunimi) + "|" + String.format("%-11s", kartKans) + "|" + String.format("%-11s", auto)
			+ "|" + String.format("%-15s", talli) + "|";
	}
	
	/**
	 * Palauttaa kuljettajan suku- ja etunimen
	 */
	public String toString() {
		return this.sukunimi + " " + this.etunimi;
	}

}
