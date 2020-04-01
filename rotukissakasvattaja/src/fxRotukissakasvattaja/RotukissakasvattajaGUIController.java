package fxRotukissakasvattaja;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Collection;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import fi.jyu.mit.fxgui.ListChooser;
import rekisteri.Kissa;
import rekisteri.Omistaja;
import rekisteri.Omistajat;
import rekisteri.Rekisteri;
import rekisteri.SailoException;


/**
 * Luokka Käyttöliittymän käsittelyyn rotukissakasvattajan ohjelmalle.
 * @author annik
 * @version 1.4.2020
 *
 */
public class RotukissakasvattajaGUIController implements Initializable {
	
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;
    @FXML private ScrollPane panelKissa;
    @FXML private ListChooser<Kissa> chooserKissat;
    
   // private String kasvattajanimi = "Kissakaveri";
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();     
    }

    
    @FXML private void handleHakuehto() {
//        String hakukentta = cbKentat.getSelectedText();
//        String ehto = hakuehto.getText(); 
//        if ( ehto.isEmpty() )
//            naytaVirhe(null);
//        else
//            naytaVirhe("Vielä ei voi hakea " + hakukentta + ": " + ehto);
        if ( kissaKohdalla != null )
            haeKissa(kissaKohdalla.getKissanTunnusNro()); 
    }
    
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    @FXML private void handleAvaa() {
        avaa();
    }
    
    
    @FXML private void handleTulosta() {
        RotukissaTulostusController tulostusCtrl = RotukissaTulostusController.tulosta(null);
        tulostaValitut(tulostusCtrl.getTextArea());
    } 

    
    @FXML private void handleLopeta() {
       // tallenna();
        Platform.exit();
    } 

    
    @FXML private void handleLisaaUusiKissa() {
        uusiKissa();
    }
    
    @FXML private void handleLisaaUusiOmistaja() {
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
    
    private String kasvattajanimi = "Kissakaveri";
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
            labelVirhe = new Label();
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
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        kasvattajanimi = nimi;
        setTitle("Kasvattaja - " + kasvattajanimi);

        try {
            rekisteri.lueTiedostosta(nimi);
            haeKissa(0);
            return null;
        } catch (SailoException e) {
            haeKissa(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
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
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        
        try {
            rekisteri.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
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

        if (kissaKohdalla == null) {
            areaKissa.clear();
            return;
        }

        areaKissa.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKissa)) {
          
            tulosta(os, kissaKohdalla);
        }
    }
    
    /**
     * Hakee kissojen tiedot listaan
     * @param kissanro kissan numero, joka aktivoidaan haun jälkeen
     */
    protected void haeKissa(int kissanro) {

        int k = cbKentat.getSelectionModel().getSelectedIndex();
        String ehto = hakuehto.getText(); 
        if (k > 0 || ehto.length() > 0)
            naytaVirhe(String.format("Ei osata hakea (kenttä: %d, ehto: %s)", k, ehto));
        else
            naytaVirhe("");
        
        chooserKissat.clear();

        int index = 0;
        Collection<Kissa> kissat;
        try {
            kissat = rekisteri.etsiKissat(ehto, k);
            int i = 0;
            for (Kissa kissa:kissat) {
                if (kissa.getKissanTunnusNro() == kissanro) index = i;
                chooserKissat.add(kissa.getNimi(), kissa);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Kissan hakemisessa ongelmia! " + ex.getMessage());
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
        } catch (Exception e) { 
            Dialogs.showMessageDialog("Ongelmia uuden kissan luomisessa " + e.getMessage());
            return;
        }
        haeKissa(uusi.getKissanTunnusNro());
    }
    
    /** 
     * Lisätään omistaja jo valmiina olevalla kissalle
     * (tekee uuden tyhjän omistajan editointia varten). 
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
            
            
            
            Collection<Kissa> kissat = rekisteri.etsiKissat("", -1); 
            for (Kissa kissa:kissat) { 
                tulosta(os, kissa);
                os.println("\n\n");
            }
        } catch (SailoException ex) { 
            Dialogs.showMessageDialog("Kissan hakemisessa ongelmia! " + ex.getMessage()); 
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
        try {
        List<Omistaja> omistajat = rekisteri.annaOmistajat(kissa);   
     
        for (Omistaja omistaja:omistajat)
            omistaja.tulosta(os);
 
       } catch (Exception ex) {
            Dialogs.showMessageDialog("Omistajan hakemisessa ongelmia! " + ex.getMessage());
        }      
 
  }

   
}   
    


