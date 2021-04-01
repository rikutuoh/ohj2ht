package rtk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Rekisteri-luokka
 * @author Riku Tuohimetsä
 * @version 29.3.2021
 */
public class Rekisteri {
	
	private Ajat ajat;
	private Osallistujat osallistujat;
	private Rallit rallit;
	private Erikoiskokeet ekt;
	
	/**
	 * Muodostaja rekisterille 
	 * alustaa kaikki taulukot
	 */
	public Rekisteri() {
		osallistujat = new Osallistujat();
		ekt = new Erikoiskokeet();
		rallit = new Rallit();
		ajat = new Ajat();
	}
	
	/**
	 * Palauttaa listan osallistujista, jotka sisältävät
	 * annetun merkkijonon (case-insensitive)
	 * @param hakusana hakusana, jolla haetaan
	 * @param ekId erikoiskokeen id, jolta haetaan, 0 jos kaikista
	 * @return lista, jossa täsmäävät
	 */
	public ObservableList<Osallistuja> haeOsall(String hakusana, Erikoiskoe ek) {
		ObservableList<Osallistuja> lista;
		if (ek == null) lista = this.getOsallistujat();
		else lista = this.getOsallistujat(ek);
		
		lista.removeIf(osall -> !osall.getNimi().toUpperCase().matches(".*" + hakusana.toUpperCase() + ".*") &&
					  			!osall.getKartturi().toUpperCase().matches(".*" + hakusana.toUpperCase() + ".*") &&
					  			!osall.getTalli().toUpperCase().matches(".*" + hakusana.toUpperCase() + ".*") &&
					  			!osall.getAuto().toUpperCase().matches(".*" + hakusana.toUpperCase() + ".*"));
		
		return lista;
	}
	
	/**
	 * Lukee tietokannan
	 */
	public void lueTiedostosta() {
		osallistujat.lueTiedostosta();
		ekt.lueTiedostosta();
		rallit.lueTiedostosta();
		ajat.lueTiedostosta();
	}
	
	/**
	 * Tallenntaa koko rekisterin tiedostoihin
	 */
	public void tallenna() {
		osallistujat.tallenna();
		ekt.tallenna();
		rallit.tallenna();
		ajat.tallenna();
	}
	
	/**
	 * Lisää uuden ajan rekisteriin
	 * @param aika uusi aika
	 */
	public void lisaaAika(Aika aika) {
		ajat.lisaaUusi(aika);
	}
	
	/**
	 * Lisää uuden osallistujan rekisteriin
	 * @param osallistuja uusi osallistuja
	 */
	public void lisaaOsallistuja(Osallistuja osallistuja) {
		osallistujat.lisaaUusi(osallistuja);
	}
	
	/**
	 * Lisää uuden rallin rekisteriin
	 * @param ralli uusi
	 */
	public void lisaaRalli(Ralli ralli) {
		rallit.lisaaUusi(ralli);
	}
	
	/**
	 * Lisää uuden erikoiskokeen rekisteriin
	 * @param ek uusi
	 */
	public void lisaaErikoiskoe(Erikoiskoe ek) {
		ekt.lisaaUusi(ek);
	}
	
	/**
	 * Muokkaa osallistujan tiedot annetuiksi
	 * @param muokattava muokattava
	 * @param uusi uudet tiedot omaava olio
	 */
	public void muokkaaOsallistuja(Osallistuja muokattava, Osallistuja uusi) {
		osallistujat.muokkaa(muokattava, uusi);
	}
	
	/**
	 * Muokkaa rallin tiedot annetuiksi
	 * @param muokattava muokattava
	 * @param uusi uudet tiedot omaava olio
	 */
	public void muokkaaRalli(Ralli muokattava, Ralli uusi) {
		rallit.muokkaa(muokattava, uusi);
	}
	
	/**
	 * Muokkaa erikoiskokeen tiedot annetuiksi
	 * @param muokattava muokattava
	 * @param uusi uudet tiedot omaava olio
	 */
	public void muokkaaErikoiskoe(Erikoiskoe muokattava, Erikoiskoe uusi) {
		ekt.muokkaa(muokattava, uusi);
	}
	
	/**
	 * Poistaa rallin rekisteristä
	 * @param poistettava poistettava
	 */
	public void poistaRalli(Ralli poistettava) {
		ekt.poistaKaikki(poistettava);
		rallit.poista(poistettava);
	}
	
	/**
	 * Poistaa ajan rekisteristä
	 * @param osall osallistuja, jonka aika poistetaan
	 * @param ek ek, josta aika poistetaan
	 */
	public void poistaAika(Osallistuja osall, Erikoiskoe ek) {
		ajat.poista(osall, ek);
	}
	
	/**
	 * Poistaa osallistujan rekisteristä
	 * @param osall
	 */
	public void poistaOsallistuja(Osallistuja osall) {
		ajat.poistaKaikki(osall);
		osallistujat.poista(osall);
	}
	
	/**
	 * Poistaa erikoiskokeen rekisteristä
	 * @param ek poistettava
	 */
	public void poistaEk(Erikoiskoe ek) {
		ajat.poistaKaikki(ek);
		ekt.poista(ek);
	}
	
	/**
	 * Palauttaa kaikkien osallistujien listan
	 * @return lista
	 */
	public ObservableList<Osallistuja> getOsallistujat() {
		return osallistujat.getLista();
	}

	/**
	 * Palauttaa kaikkien erikoiskokeiden listan
	 * @return lista
	 */
	public ObservableList<Erikoiskoe> getErikoiskokeet() {
		return ekt.getLista();
	}

	/**
	 * Palauttaa listan kaikista rallesita
	 * @return lista
	 */
	public ObservableList<Ralli> getRallit() {
		return rallit.getLista();
	}

	/**
	 * Palauttaa tiettyyn ralliin kuuluvat erikoiskokeet
	 * @param ralliId rallin id
	 * @return erikoiskokeet
	 */
	public ObservableList<Erikoiskoe> getErikoiskokeet(Ralli ralli) {
		return ekt.getLista(ralli.getId());
	}

	/**
	 * Palauttaa listan kaikista tietylle erikoiskokeelle osallistuneista
	 * Samalla asettaa osallistujille ajan tälle erikoiskokeelle
	 * @param ekId erikoiskokeen id
	 * @return lista
	 */
	public ObservableList<Osallistuja> getOsallistujat(Erikoiskoe ek) {
		Aika[] kaikkiajat = ajat.getKaikki();
		ObservableList<Osallistuja> lista = FXCollections.observableArrayList();
		
		for (int i = 0; i < ajat.getLkm(); i++) {
			if (kaikkiajat[i] != null && kaikkiajat[i].getEkId() == ek.getId()) {
				Osallistuja os = osallistujat.get(kaikkiajat[i].getOsId());
				os.setAika(kaikkiajat[i]);
				lista.add(os);
			}
		}
		
		return lista;
	}
}
