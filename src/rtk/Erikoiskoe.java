package rtk;

import java.time.LocalDate;

public class Erikoiskoe {
	
	private String nimi;
	private LocalDate pvm;
	
	public Erikoiskoe(String nimi) {
		this.nimi = nimi;
		this.pvm = LocalDate.now();
	}
	
	public String getNimi() {
		return nimi;
	}
	
	public LocalDate getPvm() {
		return pvm;
	}
	
	public String toString() {
		return getNimi();
	}

}
