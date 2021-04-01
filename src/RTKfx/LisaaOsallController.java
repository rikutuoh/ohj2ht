package RTKfx;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import rtk.Aika;
import rtk.Erikoiskoe;
import rtk.Osallistuja;
import rtk.Rekisteri;

/**
 * Osallistujanlisäysikkunan ohjainluokka
 * @author Riku Tuohimetsä
 * @version 30.3.2021
 */
public class LisaaOsallController implements ModalControllerInterface<Aika> {

    @FXML private ChoiceBox<Osallistuja> osBox;
    @FXML private TextField nimiField;
    @FXML private TextField aikaField;
    
    private Rekisteri rekisteri;
    private Erikoiskoe ek;
    private Aika aika;

    /**
     * Kutsutaan kun ok nappia painetaan
     */
    @FXML void handleOk() {
    	try {
    		aika = new Aika(0, ek.getId(), osBox.getSelectionModel().getSelectedItem().getId(), Aika.parseToInt(aikaField.getText()));
    		ModalController.closeStage(nimiField);
    	} catch (Exception excp) {
    		Dialogs.showMessageDialog("Kirjoita aika muotoon 00.00,00");
    	}
    	
    }

    /**
     * Kutsutaan kun peruuta nappia painetaan
     */
    @FXML void handlePeruuta() {
    	aika = null;
    	ModalController.closeStage(nimiField);
    }
    
    /**
     * Asettaa ikkunalle käytettäväksi rekisterin ja 
     * erikoiskokeen johon osallistujaa lisätään
     * @param rekisteri Rekisteri
     * @param ek Erikoiskoe
     */
    public void setRekisteriJaEk(Rekisteri rekisteri, Erikoiskoe ek) {
    	this.ek = ek;
    	this.rekisteri = rekisteri;
    }

    /**
     * Palauttaa ikkunan tuloksen
     */
	@Override
	public Aika getResult() {
		return aika;
	}

	/**
	 * Kutsutaan kun ikkuna näytetään
	 */
	@Override
	public void handleShown() {
		nimiField.setText(ek.getNimi());
		ObservableList<Osallistuja> lista = rekisteri.getOsallistujat();
		lista.removeIf(o -> rekisteri.getOsallistujat(ek).contains(o));
		osBox.setItems(lista);
		
	}

	/**
	 * Asettaa oletustiedot ikkunaan
	 */
	@Override
	public void setDefault(Aika oletus) {
		oletus = new Aika("00,00.00");
		aikaField.setText(oletus.toString());
	}
	
}
