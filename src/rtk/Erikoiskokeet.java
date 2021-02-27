package rtk;

public class Erikoiskokeet {
	
	private Erikoiskoe[] erikoiskokeet;
	private int lkm, maxLkm;
	
	public Erikoiskokeet() {
		lkm = 0;
		maxLkm = 10;
		erikoiskokeet = new Erikoiskoe[maxLkm];
	}
	
	public void lisaa(Erikoiskoe ek) {
		if ( lkm + 2 > maxLkm ) lisaaTilaa();
		erikoiskokeet[lkm] = ek;
		lkm++;
	}
	
	public Erikoiskoe[] getKaikki() {
		return erikoiskokeet;
	}
	
	public int getLkm() {
		return lkm;
	}
	
	private void lisaaTilaa() {
		Erikoiskoe[] vanha = erikoiskokeet;
		erikoiskokeet = new Erikoiskoe[maxLkm + 10];
		maxLkm += 10; 
		for (int i = 0; i < lkm; i++) {
			erikoiskokeet[i] = vanha[i];
		}
	}

}
