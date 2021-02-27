package rtk;

public class Osallistuja {
	
	private String etuNimi, sukuNimi, talli, auto, kartetuNimi, kartsukuNimi;
	private Aika aika;
	
	
	public Osallistuja(String etunimi, String sukunimi, String tal, String aut, String kartet, String kartsuk) {
		
		etuNimi = etunimi;
		sukuNimi = sukunimi;
		talli = tal;
		auto = aut;
		kartetuNimi = kartet;
		kartsukuNimi = kartsuk;
		
		
	}
	
	public String getNimi() {
		return sukuNimi + " " + etuNimi;
	}
	
	public String getKartturi() {
		return kartetuNimi + " " + kartsukuNimi;
	}
	
	public String getTalli() {
		return talli;
	}
	
	public String getAuto() {
		return auto;
	}
	
	public Aika getAika() {
		return aika;
	}
	
	public void setAika(Aika aika) {
		this.aika = aika;
	}

}
