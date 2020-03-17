package fxRotukissakasvattaja;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
* Tulostuksen hoitava luokka
* 
* @author annik
* @version 11.3.2020
*/

public class RotukissaTulostusController implements ModalControllerInterface<String>{
    
 @FXML TextArea tulostusAlue;
    
    @FXML private void handleOK() {
        ModalController.closeStage(tulostusAlue);
    }

    
    @FXML private void handleTulosta() {
        Dialogs.showMessageDialog("Tulostus ei ole vielä mahdollista.");
    }

    
    @Override
    public String getResult() {
        return null;
    } 

    
    @Override
    public void setDefault(String oletus) {
        if ( oletus == null ) return;
        tulostusAlue.setText(oletus);
    }

    
    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        //
    }
    
    /**
     * @return Alue johon tulostetaan
     */
    public TextArea getTextArea() {
        return tulostusAlue;
    }
    
    
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostus tulostettava teksti
     * @return kontrolleri, jolta voidaan pyytää lisää tietoa
     */
    public static RotukissaTulostusController tulosta(String tulostus) {
        RotukissaTulostusController tulostusCtrl = 
        ModalController.showModeless(RotukissaTulostusController.class.getResource("RotukissaTulostusView.fxml"),
                "Tulostus", tulostus);
        return tulostusCtrl;
    }

}

