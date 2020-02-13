package fxRotukissakasvattaja;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * Käynnistetään ohjelma. Käyttäjä kirjautuu ohjelmaan syöttämällä kasvattajanimensä.
 * 
 * @author annik
 * @version 12.02.2020
 */

public class KaynnistysController implements ModalControllerInterface<String> {

    @FXML private TextField textKasvattajanimi;
    private String vastaus = null;

    
    @FXML private void handleOK() {
        vastaus = textKasvattajanimi.getText();
        ModalController.closeStage(textKasvattajanimi);
    }

    
    @FXML private void handleCancel() {
        ModalController.closeStage(textKasvattajanimi);
    }


    @Override
    public String getResult() {
        return vastaus;
    }

    
    @Override
    public void setDefault(String oletus) {
        textKasvattajanimi.setText(oletus);
    }

    
    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        textKasvattajanimi.requestFocus();
    }
    
    
    /**
     * Luodaan kasvattajan nimenkysymisdialogi ja palautetaan siihen kirjoitettu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                KaynnistysController.class.getResource("Kaynnistys.fxml"),
                "Kasvattaja",
                modalityStage, oletus);
    }
}
