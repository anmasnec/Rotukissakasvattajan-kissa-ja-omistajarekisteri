package fxRotukissakasvattaja;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * Rotukissakasvattajalle tarkoitettu ohjelma, johon hän syöttää kasvattamansa kissanpentujen tiedot 
 * sekä kissojen uusien omistajien tiedot. Ohjelman tarkoitus
 * myös pitää kasvattaja ajantasalla omistamiensa kissojen rokotuksista.
 * Tarvitaan RotukissakasvattajaGUIView.fxml ja RotukissakasvattajaGUIController.java.
 * @author annik
 * @version 30.1.2020
 *
 */
public class RotukissakasvattajaMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("RotukissakasvattajaGUIView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("rotukissakasvattaja.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Käynnistetään rotukissakasvattajan ohjelma.
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
