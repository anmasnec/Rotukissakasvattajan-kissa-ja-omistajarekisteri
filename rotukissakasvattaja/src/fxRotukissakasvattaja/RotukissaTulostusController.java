package fxRotukissakasvattaja;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;

/**
* Tulostuksen hoitava luokka
* 
* @author annik
* @version 24.4.2020
*/

public class RotukissaTulostusController implements ModalControllerInterface<String>{
    
 @FXML TextArea tulostusAlue;
    
     /**
      * Sulkee tulostuksen
      */
    @FXML private void handleOK() {
        ModalController.closeStage(tulostusAlue);
    }

    
    /**
     * Avaa tulostusikkunan
     */
    @FXML private void handleTulosta() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if ( job != null && job.showPrintDialog(null) ) {
            WebEngine webEngine = new WebEngine();
            webEngine.loadContent("<pre>" + tulostusAlue.getText() + "</pre>");
            webEngine.print(job);
            job.endJob();
        }
    }

    
    /**
     * Käsittelee tulostettavat 
     */
    @Override
    public String getResult() {
        return null;
    } 

    
    /**
     * Käsittelee tulostettavat 
     */
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

