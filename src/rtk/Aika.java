package rtk;

/**
 * Aika-luokka
 * @author rikut
 * @version 29.3.2021
 */
public class Aika {
	
	private int id, value, osId, ekId;
	
	/**
	 * Muodostaja aikaoliolle, missä annetaan oikeassa muodossa oleva aika
	 * @param s Aika muodossa mm,ss.cc, missä cc tarkoittaa sekunnin sadasosia
	 */
	public Aika(String s) {
		this.value = parseToInt(s);
	}
	
	
	/**
	 * Muodostaja aikaoliolle missä annetaan aika millisekunteina
	 * @param value Aika millisekunteina
	 */
	public Aika(int value) {
		this.value = value;
	}
	
	/**
	 * Muodostaja aikaoliolle, missä annetaan kaikki olion tiedot
	 * @param id Aikaolion id
	 * @param ekId Ajalle kuuluvan erikoiskokeen id
	 * @param osId Ajalle kuuluvan osallistujan id
	 * @param aika Aika millisekunteina
	 */
	public Aika(int id, int ekId, int osId, int aika) {
		this.id = id; this.ekId = ekId; this.osId = osId; this.value = aika;
	}
	
	/**
	 * Antaa ajalle kuuluvan erikoiskokeen id:n
	 * @return erikoiskokeen id
	 */
	public int getEkId() {
		return ekId;
	}
	
	/**
	 * Antaa ajalle kuuluvan osallistujan id:n
	 * @return osallistujan id
	 */
	public int getOsId() {
		return osId;
	}
	
	/**
	 * Palauttaa ajan id:n
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Asettaa ajalle id:n
	 * @param id asetettava id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Palauttaa aika-olion annetun oikeanmuotoisen ajan perusteella
	 * @param s aika oikeassa muodossa
	 * @return aikaolio
	 */
	public static Aika parseToAika(String s) {
		
		String[] sArr = s.split("\\|");
		for (int i = 0; i < sArr.length; i++) sArr[i] = sArr[i].trim();
		Aika aika = new Aika(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]), Integer.parseInt(sArr[3]));
		
		return aika;
	}
	
	/**
	 * Palauttaa olion tiedot tiedostoon tallennettavassa muodossa
	 * @return olion tiedot tiedostoon tallennettavassa muodossa
	 */
	public String tiedostomuotoon() {
		return String.format("%-4d", id) + "|" + String.format("%-7d", ekId) + "|" + String.format("%-11d", osId) + "|" + String.format("%-11d", value) + "|";
	}
	
	/**
	 * Palauttaa oikeanmuotoisen ajan millisekunteina
	 * @param s aika näytettävässä muodossa
	 * @return aika millisekunteina
	 * 
	 * <pre name="test">
	 * 		parseToInt("01.42,59") === 102590;
	 * 		parseToInt("02.05,34") === 125340;
	 * 		parseToInt("02.35,35") === 155350;
	 * 		parseToInt("05.40,28") === 340280;
	 * 		parseToInt("10.55,35") === 655350;
	 * </pre>
	 */
	public static int parseToInt(String s) {
		
		String[] sArr = s.split("[,.]");
		return Integer.parseInt(sArr[0]) * 60000 + Integer.parseInt(sArr[1])*1000 + Integer.parseInt(sArr[2])*10;
	}
	
	/**
	 * Palauttaa annetun ajan millisekunteina oikeassa 
	 * muodossa mm,ss.cc, missä cc tarkoittaa sekunnin sadasosia
	 * @param value Aika millisekunteina
	 * @return Aika muodossa mm,ss.cc, missä cc tarkoittaa sekunnin sadasosia
	 */
	public static String toString(int value) {
		
		int ms, s, m;
		
		ms = value%1000;
		s = value/1000;
		m = s/60;
		s -= m*60;
		
		return String.format("%02d", m) + "." + String.format("%02d", s) +","+ String.format("%02d", ms/10);
	}
	
	/**
	 * Palauttaa olion ajan millisekunteina oikeassa 
	 * muodossa mm,ss.cc, missä cc tarkoittaa sekunnin sadasosia
	 * @return Aika muodossa mm,ss.cc, missä cc tarkoittaa sekunnin sadasosia
	 */
	public String toString() {
		
		return toString(value);
	}

}
