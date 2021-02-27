package RTKfx;

import java.util.Arrays;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import rtk.*;

public class RallitietokantaGUIController {

    @FXML private ListView<Ralli> listViewRalli;
    @FXML private ListView<Erikoiskoe> listViewEk;
    @FXML private TableView<Osallistuja> tableviewkuski; 
    @FXML private TableColumn<Osallistuja, String> tableCKuskiNimi;
    @FXML private TableColumn<Osallistuja, String> tableCKuskiAika;
    @FXML private TableColumn<Osallistuja, String> tableCKuskiTalli;
    @FXML private TextField kuskiNimiField;
    @FXML private TextField kartturiNimiField;
    @FXML private TextField kuskiTalliField;
    @FXML private TextField kuskiAutoField;
    
    private Rallit rallit;
    private Osallistujat osallistujat;
    private Ajat ajat;
    private Erikoiskokeet erikoiskokeet;
    
    @FXML
    void kuskiValittu() {
    	Osallistuja osall = tableviewkuski.getSelectionModel().getSelectedItem(); 
    	
    	if (osall != null) {
    		kuskiNimiField.setText(osall.getNimi());
        	kartturiNimiField.setText(osall.getKartturi());
        	kuskiTalliField.setText(osall.getTalli());
        	kuskiAutoField.setText(osall.getAuto());
    	}
    }
    
    @FXML
    void lisaaEk() {
    	ModalController.showModal(this.getClass().getResource("MuokkaaErikoiskoeView.fxml"), "Muokkaa", null, "");
    }
    
    @FXML
    void lisaaKuski() {
    	ModalController.showModal(this.getClass().getResource("LisaaOsallistujaView.fxml"), "Muokkaa", null, "");
    }
    
    @FXML
    void lisaaUusiKuski() {
    	ModalController.showModal(this.getClass().getResource("MuokkaaOsallistujaaView.fxml"), "Muokkaa", null, "");
    }
    
    @FXML
    void lisaaRalli() {
    	ModalController.showModal(this.getClass().getResource("MuokkaaRalliaView.fxml"), "Muokkaa", null, "");
    }
    
    @FXML
    void muokkaaKuskia() {
    	ModalController.showModal(this.getClass().getResource("MuokkaaOsallistujaaView.fxml"), "Muokkaa", null, "");
    }

    @FXML
    void muokkaaRalli() {
    	ModalController.showModal(this.getClass().getResource("MuokkaaRalliaView.fxml"), "Muokkaa", null, "");
    }
    
    @FXML
    void muokkaaEk() {
    	ModalController.showModal(this.getClass().getResource("MuokkaaErikoiskoeView.fxml"), "Muokkaa", null, "");
    }

    @FXML
    void poistaKuski() {
    	if (tableviewkuski.getSelectionModel().getSelectedItem() != null) Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti poistaa osallistujan " + tableviewkuski.getSelectionModel().getSelectedItem().getNimi(), "Kyllä", "Ei");
    }

    @FXML
    void poistaRalli() {
    	if (listViewRalli.getSelectionModel().getSelectedItem() != null) Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti poistaa rallin " + listViewRalli.getSelectionModel().getSelectedItem().getNimi(), "Kyllä", "Ei");
    }
    
    @FXML
    void poistaEk() {
    	if (listViewEk.getSelectionModel().getSelectedItem() != null) Dialogs.showQuestionDialog("Oletko varma?", "Haluatko varmasti poistaa erikoiskokeen " + listViewEk.getSelectionModel().getSelectedItem().getNimi(), "Kyllä", "Ei");
    }

    @FXML 
    void initialize() {
        assert tableviewkuski != null : "fx:id=\"tableviewkuski\" was not injected: check your FXML file 'RallitietokantaGUIView.fxml'.";
        
        rallit = new Rallit();
        erikoiskokeet = new Erikoiskokeet();
        
        tableCKuskiNimi.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        tableCKuskiTalli.setCellValueFactory(new PropertyValueFactory<>("talli"));
        tableCKuskiAika.setCellValueFactory(new PropertyValueFactory<>("aika"));

        
         // KOKEILUA
        
        
        for (int i = 0; i < 30; i++) {
        	double rand = Math.random();
        	
        	Osallistuja kuskil;
        	
        	if(rand > 0.5) {
        		kuskil = new Osallistuja("Erkki", "Esimerkki", "VRCF", "Volvo 240", "Kari", "Kartturi");
        	} else {
        		kuskil = new Osallistuja("Matti", "Meikäpoika", "Lekaharkko Per.CC Racing", "Saab 96", "Veikko", "Lukija");
        	} 
        	kuskil.setAika(new Aika((int)(Math.random()*6000000)));
        	
        	tableviewkuski.getItems().add(kuskil);
        }
        
        for (int i = 0; i < 50; i++) {
        	Erikoiskoe ek = new Erikoiskoe("Ek " + i);
        	erikoiskokeet.lisaa(ek);
        }
        
        for (int i = 0; i < 15; i++) {
        	Ralli ralli = new Ralli("Ralli " + i, "");
        	rallit.lisaa(ralli);
        }
        
        Arrays.asList(rallit.getKaikki()).forEach(r -> listViewRalli.getItems().add(r));
        Arrays.asList(erikoiskokeet.getKaikki()).forEach(ek -> listViewEk.getItems().add(ek));
    }
}
