package RTKfx;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import rtk.*;

/**
 * Käyttöliittymän ohjainluokka
 * @author rikut
 * @version 29.3.2021
 */
public class RallitietokantaGUIController {

    @FXML private ListView<Ralli> listViewRalli;
    @FXML private ListView<Erikoiskoe> listViewEk;
    @FXML private TableView<Osallistuja> tableviewkuski; 
    @FXML private TableColumn<Osallistuja, Integer> tableCKuskiId;
    @FXML private TableColumn<Osallistuja, String> tableCKuskiNimi;
    @FXML private TableColumn<Osallistuja, String> tableCKuskiAika;
    @FXML private TableColumn<Osallistuja, String> tableCKuskiTalli;
    @FXML private TextField kuskiNimiField;
    @FXML private TextField kartturiNimiField;
    @FXML private TextField kuskiTalliField;
    @FXML private TextField kuskiAutoField;
    @FXML private ToggleButton kaikkiNappi;
    @FXML private TextField hakukentta;
    
    private Rekisteri rekisteri;
    
    /**
     * Kutsutaan, kun osallistujien listaa klikataan
     */
    @FXML void kuskiValittu() {
    	naytaOsallTiedot();
    }
    
    /**
     * Kutsutaan, kun rallilistaa klikataan
     */
    @FXML void ralliValittu() {
    	naytaErikoiskokeet();
    }

    /**
     * Kutsutaan, kun erikoiskoelistaa klikataan
     */
    @FXML void ekValittu() {
    	naytaOsallistujat();
    }

    /**
     * Kutsutaan, kun Erikoiskokeen "lisää" nappia painetaan
     */
    @FXML void lisaaEk() {
    	if (listViewRalli.getSelectionModel().getSelectedItem() != null) {
    		Erikoiskoe ek = ModalController.<Erikoiskoe, MuokkaaEkController>showModal(this.getClass().getResource("MuokkaaErikoiskoeView.fxml"), 
    																				  "Lisää Erikoiskoe", null, null,
    																				   ctrl -> ctrl.setRalli(listViewRalli.getSelectionModel().getSelectedItem()));
    		if (ek != null) rekisteri.lisaaErikoiskoe(ek);
    		naytaErikoiskokeet();
    	}
    }
    
    /**
     * Kutsutaan, kun osallistujalistan "lisää" nappia painetaan
     */
    @FXML void lisaaKuski() {	
    	if (listViewEk.getSelectionModel().getSelectedItem() != null) {
    		Aika aika = ModalController.<Aika, LisaaOsallController>showModal(this.getClass().getResource("LisaaOsallistujaView.fxml"), 
    																		"Lisää Osallistuja", null, null, 
    																		ctrl -> ctrl.setRekisteriJaEk(rekisteri, listViewEk.getSelectionModel().getSelectedItem()));
    		if (aika != null) rekisteri.lisaaAika(aika);
    		naytaOsallistujat();
    	}
    }
    
    /**
     * Kutsutaan, kun osallistujalistan "uusi" nappia painetaan
     */
    @FXML void lisaaUusiKuski() {
    	Osallistuja uusi = ModalController.<Osallistuja>showModal(this.getClass().getResource("MuokkaaOsallistujaaView.fxml"), "Lisää Uusi Osallistuja", null, null);
    	if (uusi != null) rekisteri.lisaaOsallistuja(uusi);
    	naytaOsallistujat();
    }
    
    /**
     * Kutsutaan, kun rallilistan "lisää" nappia painetaan
     */
    @FXML void lisaaRalli() {
    	Ralli ralli = ModalController.<Ralli>showModal(this.getClass().getResource("MuokkaaRalliaView.fxml"), "Lisää Ralli", null, null);
    	if (ralli != null) rekisteri.lisaaRalli(ralli);
    	naytaRallit();
    }
    
    /**
     * Kutsutaan, kun osallistujalistan "muokkaa" nappia painetaan
     */
    @FXML void muokkaaKuskia() {
    	Osallistuja muokattava = tableviewkuski.getSelectionModel().getSelectedItem();
    	if (muokattava != null) {
    		Osallistuja uusi = ModalController.<Osallistuja>showModal(this.getClass().getResource("MuokkaaOsallistujaaView.fxml"), "Muokkaa Osallistujaa", null, muokattava);
        	if (uusi != null) rekisteri.muokkaaOsallistuja(muokattava, uusi);
        	naytaOsallistujat();
    	}
    	
    }

    /**
     * Kutsutaan, kun rallilistan "muokkaa" nappia painetaan
     */
    @FXML void muokkaaRalli() {
    	Ralli muokattava = listViewRalli.getSelectionModel().getSelectedItem();
    	if (muokattava != null) {
    		Ralli ralli = ModalController.<Ralli>showModal(this.getClass().getResource("MuokkaaRalliaView.fxml"), "Muokkaa Rallia", null, muokattava);
        	if (ralli != null) rekisteri.muokkaaRalli(muokattava, ralli);
        	naytaRallit();
    	}
    }
    
    /**
     * Kutsutaan, kun erikoiskoelistan "muokkaa" nappia painetaan
     */
    @FXML void muokkaaEk() {
    	Erikoiskoe muokattava = listViewEk.getSelectionModel().getSelectedItem();
    	if (muokattava != null) {
    		Erikoiskoe ek = ModalController.<Erikoiskoe, MuokkaaEkController>showModal(this.getClass().getResource("MuokkaaErikoiskoeView.fxml"), 
    																				  "Muokkaa Erikoiskoetta", null, muokattava,
    																				   ctrl -> ctrl.setRalli(listViewRalli.getSelectionModel().getSelectedItem()));
    		if (ek != null) rekisteri.muokkaaErikoiskoe(muokattava, ek);
    		naytaErikoiskokeet();
    	}
    }

    /**
     * Kutsutaan, kun osallistujalistan "poista" nappia painetaan
     */
    @FXML void poistaKuski() {
    	Osallistuja osall = tableviewkuski.getSelectionModel().getSelectedItem();
    	if (osall != null) {
    		if (kaikkiNappi.isSelected()) {
    			if (Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti poistaa osallistujan " + osall.getNimi(), "Kyllä", "Ei")) {
    				rekisteri.poistaOsallistuja(osall);
    				
    			}
    		} else {
    			Erikoiskoe ek = listViewEk.getSelectionModel().getSelectedItem();
    			if (ek != null && Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti poistaa osallistujan " + osall.getNimi() +
    											" ajan erikoiskokeella " + ek.getNimi(), "Kyllä", "Ei")) {
        			rekisteri.poistaAika(osall, ek);
    			}
    		}
    		naytaOsallistujat();
    	}
    }

    /**
     * Kutsutaan, kun rallilistan "poista" nappia painetaan
     */
    @FXML void poistaRalli() {
    	Ralli poistettava = listViewRalli.getSelectionModel().getSelectedItem();
    	if (poistettava != null) {
    		if (Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti poistaa rallin " + poistettava.getNimi(), "Kyllä", "Ei")) {
    			rekisteri.poistaRalli(poistettava);
    			naytaRallit();
    			naytaErikoiskokeet();
    		}
    	}
    }
    
    /**
     * Kutsutaan kun erikoiskoelistan "poista" nappia painetaan
     */
    @FXML void poistaEk() {
    	Erikoiskoe ek = listViewEk.getSelectionModel().getSelectedItem();
    	if (ek != null) {
    		if (Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti poistaa erikoiskokeen " + ek.getNimi(), "Kyllä", "Ei")) {
    			rekisteri.poistaEk(ek);
    		}
    		naytaErikoiskokeet();
    	}
    }
    
    /**
     * Kutsutaan, kun "kaikki" nappia painetaan
     */
    @FXML void kaikkiNappiPainettu() {
    	naytaOsallistujat();
    }
    
    /**
     * Kutsutaan kun "lataa uudelleen" nappia painetaan
     */
    @FXML void handleReload() {
    	if (Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti ladata tiedot uudelleen? Menetät kaikki muutokset.", "Kyllä", "Ei")) {
    		rekisteri = new Rekisteri();
    		rekisteri.lueTiedostosta();
        	naytaRallit();
        	naytaErikoiskokeet();
        	naytaOsallistujat();
    	}
    }

    /**
     * Kutsutaan kun "tallenna" nappia painetaan
     */
    @FXML void handleTallenna() {
    	if (Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti tallentaa?", "Kyllä", "Ei")) {
    		rekisteri.tallenna();
    	}
    }
    
    /**
     * Kutsutaan kun hakukenttää klikataan
     */
    @FXML void kenttaklikattu() {
    	if (hakukentta.getText().equals("Hae...")) hakukentta.setText("");
    }
    
    /**
     * Hakee rekisteristä hakukenttään kirjoitettua
     * tekstiä vastaavat osallistujat ja 
     * asettaa ne listaan
     * @param hakusana hakukenttään kirjoitettu teksti
     */
    private void osallHaku(String hakusana) {
    	if (kaikkiNappi.isSelected()) tableviewkuski.setItems(rekisteri.haeOsall(hakusana, null));
    	else if (listViewEk.getSelectionModel().getSelectedItem() != null) tableviewkuski.setItems(rekisteri.haeOsall(hakusana, listViewEk.getSelectionModel().getSelectedItem()));
    }
    
    /**
     * Asettaa rallilistaan rallit
     */
    private void naytaRallit() {
    	listViewRalli.setItems(rekisteri.getRallit());
    }
    
    /**
     * Asettaa Erikoiskoelistaan erikoiskokeet
     */
    private void naytaErikoiskokeet() {
    	Ralli ralli = listViewRalli.getSelectionModel().getSelectedItem();
    	if (ralli != null) {
    		listViewEk.setItems(rekisteri.getErikoiskokeet(ralli));
    	} else {
    		listViewEk.setItems(null);
    	}
    	
    }
    
    /**
     * Asettaa osallistujalistaan osallistujat
     */
    private void naytaOsallistujat() {
    	if (kaikkiNappi.isSelected()) {
    		tableviewkuski.setItems(rekisteri.getOsallistujat());
    	} else {
    		Erikoiskoe ek = listViewEk.getSelectionModel().getSelectedItem();
    		if (ek != null) tableviewkuski.setItems(rekisteri.getOsallistujat(ek));
    		else tableviewkuski.setItems(null);
    	}
    }
    
    /**
     * Asettaa osallistujan tietokenttiin osallistujan tiedot
     */
    private void naytaOsallTiedot() {
    	
    	kuskiNimiField.setText("");
        kartturiNimiField.setText("");
        kuskiTalliField.setText("");
        kuskiAutoField.setText("");
    	
    	Osallistuja osall = tableviewkuski.getSelectionModel().getSelectedItem(); 
    	
    	if (osall != null) {
    		kuskiNimiField.setText(osall.getNimi());
        	kartturiNimiField.setText(osall.getKartturi());
        	kuskiTalliField.setText(osall.getTalli());
        	kuskiAutoField.setText(osall.getAuto());
    	} 
    }

    /**
     * Kutsutaan, kun sovellus käynnistyy
     */
    @FXML void initialize() {
        
        rekisteri = new Rekisteri();
        rekisteri.lueTiedostosta();
        
        tableCKuskiId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCKuskiNimi.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        tableCKuskiTalli.setCellValueFactory(new PropertyValueFactory<>("talli"));
        tableCKuskiAika.setCellValueFactory(new PropertyValueFactory<>("aika"));
        
        hakukentta.textProperty().addListener(tp -> osallHaku(((StringProperty)tp).get()));
        
        naytaRallit();
        naytaOsallistujat();
    }
}
