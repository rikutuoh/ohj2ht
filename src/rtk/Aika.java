package rtk;

public class Aika {
	
	private int value;
	
	public Aika(String s) {
		this.value = parseToInt(s);
	}
	
	public Aika(int value) {
		this.value = value;
	}
	
	/**
	 * Palauttaa aika-olion annetun oikeanmuotoisen ajan perusteella
	 * @param s aika oikeassa muodossa
	 * @return aikaolio
	 */
	public static Aika parseToAika(String s) {

		return new Aika(parseToInt(s));
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
	
	
	public static String toString(int value) {
		
		int ms, s, m;
		
		ms = value%1000;
		s = value/1000;
		m = s/60;
		s -= m*60;
		
		return String.format("%02d", m) + "." + String.format("%02d", s) +","+ String.format("%02d", ms/10);
	}
	
	public String toString() {
		
		return toString(value);
	}

}
