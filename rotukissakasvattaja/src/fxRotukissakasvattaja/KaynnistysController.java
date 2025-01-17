package fxRotukissakasvattaja;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Käynnistetään ohjelma. Käyttäjä kirjautuu ohjelmaan syöttämällä kasvattajanimensä.
 * 
 * @author annik
 * @version 24.04.2020
 */

public class KaynnistysController implements ModalControllerInterface<String> {

    @FXML private TextField textKasvattajanimi;
    private String vastaus = null;

    
    /**
     * Avataan rekisteri, kun syötetty alkunäytössä kasvattajanimi
     */
    @FXML private void handleOK() {
        vastaus = textKasvattajanimi.getText();
        ModalController.closeStage(textKasvattajanimi);
    }

    
    /**
     * Alkunäytössä poistutaan ohjelmasta
     */
    @FXML private void handleCancel() {
        ModalController.closeStage(textKasvattajanimi);
    }


    /**
     * Käsittelee annetun kasvattajanimen
     */
    @Override
    public String getResult() {
        return vastaus;
    }

    /**
     * Käsittelee annetun kasvattajanimen
     */
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
