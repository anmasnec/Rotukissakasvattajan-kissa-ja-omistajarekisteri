package fxRotukissakasvattaja;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * Luokka Käyttöliittymän käsittelyyn rotukissakasvattajan ohjelmalle.
 * @author annik
 * @version 12.2.2020
 *
 */
public class RotukissakasvattajaGUIController {
	
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;
    
    private String kasvattajanimi = "Kissakaveri";
    
    
   // @Override
   // public void initialize(URL url, ResourceBundle bundle) {
        //      
   // }

    
    @FXML private void handleHakuehto() {
        String hakukentta = cbKentat.getSelectedText();
        String ehto = hakuehto.getText(); 
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Vielä ei voi hakea " + hakukentta + ": " + ehto);
    }
    
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    
    @FXML private void handleTulosta() {
        RotukissaTulostusController.tulosta(null);
    } 

    
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    } 

    
    @FXML private void handleLisaaUusiKissa() {
       // Dialogs.showMessageDialog("Vielä ei osata lisätä kissaa");
        ModalController.showModal(RotukissakasvattajaGUIController.class.getResource("LisaaKissaDialogView.fxml"), "Kissa", null, "");
    }
    
    @FXML private void handleLisaaUusiOmistaja() {
        //Dialogs.showMessageDialog("Vielä ei osata lisätä omistajaa");
        ModalController.showModal(RotukissakasvattajaGUIController.class.getResource("LisaaOmistajaDialogView.fxml"), "Omistaja", null, "");
    }
  
    
    @FXML private void handleMuokkaaKissaa() {
        ModalController.showModal(RotukissakasvattajaGUIController.class.getResource("MuokkaaKissaDialogView.fxml"), "Kissa", null, "");
    }
    
    @FXML private void handleMuokkaaOmistajaa() {
        ModalController.showModal(RotukissakasvattajaGUIController.class.getResource("MuokkaaOmistajaDialogView.fxml"), "Omistaja", null, "");
    }
      

    @FXML private void handlePoistaKissa() {
        Dialogs.showMessageDialog("Vielä ei ole mahdollista poistaa kissaa.");
    }
    
    @FXML private void handlePoistaOmistaja() {
        Dialogs.showMessageDialog("Vielä ei ole mahdollista poistaa omistajaa.");
    }
    

    @FXML private void handleApua() {
        avustus();
    }
    


//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
    
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * Alustaa kasvattajarekisterin lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kasvattajanrekisterin tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        kasvattajanimi = nimi;
        setTitle("Kasvattaja - " + kasvattajanimi);
        String virhe = "Tiedostoja ei lueta vielä.";  // TODO: tähän oikea tiedoston lukeminen
         if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }

    
    /**
     * Kysytään tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
   public boolean avaa() {
       String uusinimi = KaynnistysController.kysyNimi(null, kasvattajanimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }

    
    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennus ei toimi vielä.");
    }


    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    

    /**
     * Näytetään ohjelman suunnitelma erillisessä selaimessa.
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/anmasnec");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
}

