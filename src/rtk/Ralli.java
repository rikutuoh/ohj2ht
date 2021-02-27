package rtk;

import java.time.LocalDate;

public class Ralli {
	
	private String nimi, paikka;
	private LocalDate alkuPvm, loppuPvm;
	
	public Ralli(String nam, String paik) {
		this.nimi = nam;
		paikka = paik;
	}
	
	public String getNimi() {
		return nimi;
	}
	
	public String getPaikka() {
		return paikka;
	}
	
	public String toString() {
		return getNimi();
	}
	
	public String getDate() {
		return alkuPvm.toString() + " - " + loppuPvm.toString();
	}

}
