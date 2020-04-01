package fxRotukissakasvattaja;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import rekisteri.Rekisteri;


/**
 * Rotukissakasvattajalle tarkoitettu ohjelma, johon hän syöttää kasvattamansa kissanpentujen tiedot 
 * sekä kissojen uusien omistajien tiedot. Ohjelman tarkoitus
 * myös pitää kasvattaja ajantasalla omistamiensa kissojen rokotuksista.
 * Tarvitaan RotukissakasvattajaGUIView.fxml ja RotukissakasvattajaGUIController.java.
 * @author annik
 * @version 1.4.2020
 * 
 * Pääohjelma Rotukissakasvattajarekisteri-ohjelman käynnistämiseksi
 *
 */
public class RotukissakasvattajaMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("RotukissakasvattajaGUIView.fxml"));
		    //final BorderPane root = (BorderPane)ldr.load();
		    final Pane root = (Pane)ldr.load();
		   // BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("RotukissakasvattajaGUIView.fxml"));
			//Pane root = (Pane)FXMLLoader.load(getClass().getResource("Kaynnistys.fxml"));
			//final RotukissakasvattajaGUIController rotukissakasvattajaCtrl = (RotukissakasvattajaGUIController) ldr.getController();
			
			final RotukissakasvattajaGUIController kasvattajaCtrl = (RotukissakasvattajaGUIController)ldr.getController();
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("rotukissakasvattaja.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.setOnCloseRequest((event) -> {
                if ( !kasvattajaCtrl.voikoSulkea() ) event.consume();
            });
			
			primaryStage.setTitle("Rekisteri");
			Rekisteri rekisteri = new Rekisteri();
			kasvattajaCtrl.setRekisteri(rekisteri);
			
			primaryStage.show();
			
			Application.Parameters params = getParameters(); 
            if ( params.getRaw().size() > 0 ) 
                kasvattajaCtrl.lueTiedosto(params.getRaw().get(0)); 
			
            else
			if ( !kasvattajaCtrl.avaa() ) Platform.exit();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Käynnistetään rotukissakasvattajan ohjelma.
	 * @param args komentorivin parametrit
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
