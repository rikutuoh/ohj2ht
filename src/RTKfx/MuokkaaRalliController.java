package RTKfx;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import rtk.Ralli;

/**
 * Rallin muokkausikkunan ohjainluokka
 * @author rikut
 * @version 30.3.2021
 */
public class MuokkaaRalliController implements ModalControllerInterface<Ralli> {

    @FXML private TextField nimiField;
    @FXML private TextField paikkaField;
    @FXML private DatePicker alkuPPicker;
    @FXML private DatePicker loppuPPicker;
    
    private Ralli ralli;

    /**
     * Kutsutaan, kun ok nappia painetaan
     */
    @FXML void handleOk() {
    	ralli = new Ralli(0, nimiField.getText(), paikkaField.getText(), alkuPPicker.getValue(), loppuPPicker.getValue());
    	ModalController.closeStage(nimiField);
    }

    /**
     * Kutsutaan kun peruuta nappia painetaan
     */
    @FXML void handlePeruuta() {
    	ralli = null;
    	ModalController.closeStage(nimiField);
    }

    /**
     * Palauttaa ikkunan tuloksen
     */
	@Override
	public Ralli getResult() {
		return ralli;
	}

	/**
	 * Kutsutaan, kun ikkuna näytetään
	 * Ei käytössä
	 */
	@Override
	public void handleShown() { }

	/**
	 * Asettaa ikkunaan oletustiedot
	 */
	@Override
	public void setDefault(Ralli oletus) {
		if (oletus != null) {
			this.nimiField.setText(oletus.getNimi());
			this.paikkaField.setText(oletus.getPaikka());
			this.alkuPPicker.setValue(oletus.getAlkuP());
			this.loppuPPicker.setValue(oletus.getLoppuP());
		}
	}


}
