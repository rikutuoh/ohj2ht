package RTKfx;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rtk.Osallistuja;

/**
 * Osallistujan muokkausikkunan ohjainluokka
 * @author Riku Tuohimetsä
 * @version 30.3.2021
 */
public class MuokkaaOsallController implements ModalControllerInterface<Osallistuja> {

    @FXML private TextField kuskiEtuField;
    @FXML private TextField kartturiEtuField;
    @FXML private TextField talliField;
    @FXML private TextField autoField;
    @FXML private TextField kartturiSukuField;
    @FXML private TextField kuskiSukuField;
    @FXML private TextField kuskiKansField;
    @FXML private TextField kartturiKansField;
    
    private Osallistuja palau;

    /**
     * Kutsutaan kun "peruuta" nappia painetaan
     */
    @FXML void handelPeruuta() {
    	palau = null;
    	ModalController.closeStage(kuskiEtuField);
    }

    /**
     * Kutsutaan kun ok nappia painetaan
     */
    @FXML void handleOk() {
    	palau = new Osallistuja(0, kuskiSukuField.getText(), kuskiEtuField.getText(), kuskiKansField.getText(), 
    			kartturiSukuField.getText(), kartturiEtuField.getText(), kartturiKansField.getText(), 
    			autoField.getText(), talliField.getText());
    	ModalController.closeStage(kuskiEtuField);
    }

    /**
     * Kutsutaan, kun ikkuna näytetään
     * ei käytössä
     */
	@Override
	public void handleShown() {	}

	/**
	 * Palauttaa ikkunan tuloksen
	 */
	@Override
	public Osallistuja getResult() {
		return palau;
	}

	/**
	 * Asettaa oletustiedot ikkunaan
	 */
	@Override
	public void setDefault(Osallistuja oletus) {
		if (oletus != null) {
			kuskiEtuField.setText(oletus.getKuskiEtu());
			kuskiSukuField.setText(oletus.getKuskiSuku());
			kuskiKansField.setText(oletus.getKuskiKans());
			kartturiEtuField.setText(oletus.getKartEtu());
			kartturiSukuField.setText(oletus.getKartSuku());
			kartturiKansField.setText(oletus.getKartturiKans());
			talliField.setText(oletus.getTalli());
			autoField.setText(oletus.getAuto());
		}
	}

}
