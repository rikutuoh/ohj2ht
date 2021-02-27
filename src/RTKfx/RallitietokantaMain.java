package RTKfx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author rikut
 * @version 2.2.2021
 *
 */
public class RallitietokantaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("RallitietokantaGUIView.fxml"));
            final Pane root = ldr.load();
            // final RallitietokantaGUIController rallitietokantaCtrl = (RallitietokantaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("rallitietokanta.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Rallitietokanta");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}