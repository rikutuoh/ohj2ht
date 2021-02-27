package rtk;

public class Rallit {
	
	private Ralli[] rallit;
	private int lkm, maxLkm;
	
	public Rallit() {
		lkm = 0;
		maxLkm = 10;
		rallit = new Ralli[maxLkm];
	}
	
	public void lisaa(Ralli ralli) {
		if ( lkm + 2 > maxLkm ) lisaaTilaa();
		rallit[lkm] = ralli;
		lkm++;
	}
	
	public Ralli[] getKaikki() {
		return rallit;
	}
	
	public int getLkm() {
		return lkm;
	}
	
	private void lisaaTilaa() {
		Ralli[] vanha = rallit;
		rallit = new Ralli[maxLkm + 10];
		maxLkm += 10; 
		for (int i = 0; i < lkm; i++) {
			rallit[i] = vanha[i];
		}
	}
	
	
}
	
