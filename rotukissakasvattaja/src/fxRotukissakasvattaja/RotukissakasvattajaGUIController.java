package fxRotukissakasvattaja;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import fi.jyu.mit.fxgui.ListChooser;
import rekisteri.Kissa;
import rekisteri.Omistaja;
import rekisteri.Rekisteri;
import rekisteri.SailoException;


/**
 * Luokka Käyttöliittymän käsittelyyn rotukissakasvattajan ohjelmalle.
 * @author annik
 * @version 16.3.2020
 *
 */
public class RotukissakasvattajaGUIController implements Initializable {
	
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;
    @FXML private ScrollPane panelKissa;
    @FXML private ListChooser<Kissa> chooserKissat;
    
    private String kasvattajanimi = "Kissakaveri";
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();     
    }

    
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
        RotukissaTulostusController tulostusCtrl = RotukissaTulostusController.tulosta(null);
        tulostaValitut(tulostusCtrl.getTextArea());
    } 

    
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    } 

    
    @FXML private void handleLisaaUusiKissa() {
       // Dialogs.showMessageDialog("Vielä ei osata lisätä kissaa");
        //ModalController.showModal(RotukissakasvattajaGUIController.class.getResource("LisaaKissaDialogView.fxml"), "Kissa", null, "");
        uusiKissa();
    }
    
    @FXML private void handleLisaaUusiOmistaja() {
        //Dialogs.showMessageDialog("Vielä ei osata lisätä omistajaa");
       // ModalController.showModal(RotukissakasvattajaGUIController.class.getResource("LisaaOmistajaDialogView.fxml"), "Omistaja", null, "");
        uusiOmistaja();
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
    
    private Rekisteri rekisteri;
    private Kissa kissaKohdalla;
    private TextArea areaKissa = new TextArea();
    
    /**
     * Tekee tarvittavat muut alustukset. Vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon tulostetaan kissojen tiedot.
     * Alustetaan myös kissalistan kuuntelija.
     */
    protected void alusta() {
        panelKissa.setContent(areaKissa);
        areaKissa.setFont(new Font("Courier New", 12));
        panelKissa.setFitToHeight(true);
        
        chooserKissat.clear();
        chooserKissat.addSelectionListener(e -> naytaKissa());
    }
    
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
        // if (virhe != null) 
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
    
    /**
     * Näyttää listasta valitun kissan tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaKissa() {
        kissaKohdalla = chooserKissat.getSelectedObject();

        if (kissaKohdalla == null) return;

        areaKissa.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKissa)) {
           // kissaKohdalla.tulosta(os);
            tulosta(os, kissaKohdalla);
        }
    }
    
    /**
     * Hakee kissojen tiedot listaan
     * @param kissanro kissan numero, joka aktivoidaan haun jälkeen
     */
    protected void haeKissa(int kissanro) {
        chooserKissat.clear();

        int index = 0;
        for (int i = 0; i < rekisteri.getKissoja(); i++) {
            Kissa kissa = rekisteri.annaKissa(i);
            if (kissa.getKissanTunnusNro() == kissanro) index = i;
            chooserKissat.add(kissa.getNimi(), kissa);
        }
        chooserKissat.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää kissan
    }
    
    /**
     * Luo uuden kissan jota aletaan editoimaan 
     */
    protected void uusiKissa() {
        Kissa uusi = new Kissa();
        uusi.rekisteroi();
        uusi.taytaKissaTiedoilla();
        try {
            rekisteri.lisaa(uusi);
        } catch (Exception e) { //SailoException!! Herjaa
            Dialogs.showMessageDialog("Ongelmia uuden kissan luomisessa " + e.getMessage());
            return;
        }
        haeKissa(uusi.getKissanTunnusNro());
    }
    
    /** 
     * Valmiina olevalla kissalle
     * lisätään omistaja (tekee uuden tyhjän omistajan editointia varten). 
     */ 
    protected void uusiOmistaja() { 
        if ( kissaKohdalla == null ) return;  
        Omistaja omistaja = new Omistaja();  
        omistaja.rekisteroi();  
        omistaja.taytaKissanOmistaja(kissaKohdalla.getKissanTunnusNro());  
        try {
        rekisteri.lisaa(omistaja); 
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden omistajan luomisessa " + e.getMessage());
            return;
        }
        haeKissa(kissaKohdalla.getKissanTunnusNro());          
    } 
    
    
    /**
     * @param rekisteri Kissankasvattajan rekisteri jota käytetään tässä käyttöliittymässä
     */
    public void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaKissa();
    }
    
   
    /**
     * Tulostaa listassa olevat kissat tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki kissat");
            for (int i = 0; i < rekisteri.getKissoja(); i++) {
                Kissa kissa = rekisteri.annaKissa(i);
                tulosta(os, kissa);
                os.println("\n\n");
            }
//            List<Kissa> kissat = rekisteri.annaKissat(sadepilvinen);   
//                (Kissa kissa:kissat)
//                kissa.tulosta(os);
//        }
    }

  }
    
    /**
     * Tulostaa kissan tiedot
     * @param os tietovirta johon tulostetaan
     * @param kissa tulostettava kissa
     */
    public void tulosta(PrintStream os, final Kissa kissa) {
        os.println("----------------------------------------------");
        kissa.tulosta(os);
        os.println("----------------------------------------------");
//        List<Omistaja> omistajat = rekisteri.annaOmistajat(kissa);   
//        for (Omistaja omistaja:omistajat)
//            omistaja.tulosta(os);
        
//        for (int i = 0; i < rekisteri.getOmistajia(); i++) {
//            Omistaja[] omistaja = new Omistaja[10];
//            omistaja = rekisteri.annaOmistajat(kissa);
//            for (Omistaja omist:omistajat)
//                omistajat.tulosta(os);
//            tulosta(os, kissa);
//            os.println("\n\n");
        
        
            Omistaja [] omistajat = rekisteri.annaOmistajat(kissa);  
            if (omistajat != null) {
                for (Omistaja har:omistajat)
                    har.tulosta(os);   
            }

   
    }
    
}

