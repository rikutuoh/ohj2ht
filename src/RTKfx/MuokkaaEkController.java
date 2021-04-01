package RTKfx;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import rtk.Erikoiskoe;
import rtk.Ralli;

/**
 * Erikoiskokeen muokkausikkunan ohjainluokka
 * @author rikut
 * @version 30.3.2021
 */
public class MuokkaaEkController implements ModalControllerInterface<Erikoiskoe> {

    @FXML private DatePicker pPicker;
    @FXML private TextField nimiField;
    @FXML private TextField ralliField;
    @FXML private TextField pituusField;
    
    private Erikoiskoe ek;
    private Ralli ralli;

    /**
     * Kutsukaan, kun ok nappia painetaan
     */
    @FXML void handleOk() {
    	if (!nimiField.getText().contains("|")) {
    		if (pPicker.getValue() != null) {
    			try {
    				ek = new Erikoiskoe(0, ralli.getId(), nimiField.getText(), Double.parseDouble(pituusField.getText()), pPicker.getValue());
    				ModalController.closeStage(nimiField);
    			} catch(NumberFormatException excp) {
    				Dialogs.showMessageDialog("Kirjoita pituus muotoon 0.00");
    			}
    		} else {
    			Dialogs.showMessageDialog("Anna päivämäärä");
    		}
    	} else {
    		Dialogs.showMessageDialog("Älä käytä \"|\"-merkkiä");
    	}
    }

    /**
     * Kutsutaan, kun peruuta nappia painetaan
     */
    @FXML void handlePeruuta() {
    	ek = null;
    	ModalController.closeStage(nimiField);
    }
    
    /**
     * Asettaa ikkunaan rallin, johon erikoiskoe lisätään
     * @param ralli
     */
    public void setRalli(Ralli ralli) {
    	this.ralli = ralli;
    	ralliField.setText(ralli.getNimi());
    }

    /**
     * Palauttaa ikkunan tuloksen
     */
	@Override
	public Erikoiskoe getResult() {
		return ek;
	}

	/**
	 * Kutsutaan kun ikkuna näytetään
	 * Ei käytössä
	 */
	@Override
	public void handleShown() { }

	/**
	 * Asettaa ikkunaan oletustiedot
	 */
	@Override
	public void setDefault(Erikoiskoe oletus) {
		if (oletus != null) {
			nimiField.setText(oletus.getNimi());
			pPicker.setValue(oletus.getPvm());
		}
	}

}
