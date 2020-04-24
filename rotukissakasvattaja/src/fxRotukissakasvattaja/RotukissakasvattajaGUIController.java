package fxRotukissakasvattaja;

import static fxRotukissakasvattaja.TietueDialogController.getFieldId;
import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Collection;
import java.util.List;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
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
 * @version 24.4.2020
 *
 */
public class RotukissakasvattajaGUIController implements Initializable {
	
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;
    @FXML private ScrollPane panelKissa;
    @FXML private GridPane gridKissa;
    @FXML private ListChooser<Kissa> chooserKissat;
    @FXML private StringGrid<Omistaja> stringGridOmistaja;
    @FXML private GridPane gridOmistaja;
    
    @FXML private TextArea Huomautukset;
    

    
    /**
     * alustaa rekisterin
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();    
    }

    
    /**
     * Hakee kissan
     */
    @FXML private void handleHakuehto() {
        haeKissa(0);
    }
   
    
    /**
     * Tallentaa muutokset
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Avaa rekisterin tietyllä kasvattajanimellä
     */
    @FXML private void handleAvaa() {
        avaa();
    }
    
    
    /**
     * Tulostaa. Tulostusalueeseen tulee kaikki, voidaan muokata tulostusaluetta poistamalla tarpeettomat
     */
    @FXML private void handleTulosta() {
        RotukissaTulostusController tulostusCtrl = RotukissaTulostusController.tulosta(null);
        tulostaValitut(tulostusCtrl.getTextArea());
    } 

    
    /**
     * Poistuu ohjelmasta
     */
    @FXML private void handleLopeta() {
       // tallenna();
        Platform.exit();
    } 

    
    /**
     * Lisää kissan
     */
    @FXML private void handleLisaaUusiKissa() {
        uusiKissa();
    }
    
    
    /**
     * Lisää omistajan kissalle
     */
    @FXML private void handleLisaaUusiOmistaja() {
        uusiOmistaja();
    }
  
    /**
     * Muokkaa valittua kissaa
     */
    @FXML private void handleMuokkaaKissaa() {
        muokkaaKissa(kentta);
    }
    
    /**
     * Muokkaa valittua omistajaa
     */
    @FXML private void handleMuokkaaOmistajaa() {
        muokkaaOmistaja();
    }
     
    
    /**
     * Poistaa valitun kissan
     */
    @FXML private void handlePoistaKissa() {
        poistaKissa();
    }
    
    /**
     * Poistaa valitun omistajan
     */
    @FXML private void handlePoistaOmistaja() {
        poistaOmistaja();
    }
    

    /**
     * Avaa ohjelman suunnittelu- ja käyttöohjesivuston TIM-oppimisympäristöstä
     */
    @FXML private void handleApua() {
        avustus();
    }
    

//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
    
    private String kasvattajanimi = "Kissakaveri";
    private Rekisteri rekisteri;
    private Kissa kissaKohdalla;
    private TextField edits[];
    private int kentta = 0; 
    private static Omistaja apuomistaja = new Omistaja(); 
    private static Kissa apukissa = new Kissa();
    
    /**
     * Tekee tarvittavat muut alustukset. Vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon tulostetaan kissojen tiedot.
     * Alustetaan myös kissalistan kuuntelija.
     */
    protected void alusta() {
        chooserKissat.clear();
        chooserKissat.addSelectionListener(e -> naytaKissa());
        edits = TietueDialogController.luoKentat(gridKissa, apukissa); 
        for (TextField edit: edits)  
            if ( edit != null ) {  
                edit.setEditable(false);  
                edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaaKissa(getFieldId(e.getSource(),0)); });  
                edit.focusedProperty().addListener((a,o,n) -> kentta = getFieldId(edit,kentta));
                edit.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.F2 ) muokkaaKissa(kentta);});
                
                // alustetaan omistajataulukon otsikot 
                int eka = apuomistaja.ekaKentta(); 
                int lkm = apuomistaja.getKenttia(); 
                String[] headings = new String[lkm-eka]; 
                for (int i=0, k=eka; k<lkm; i++, k++) headings[i] = apuomistaja.getKysymys(k); 
                stringGridOmistaja.initTable(headings); 
                stringGridOmistaja.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
                stringGridOmistaja.setEditable(false); 
                stringGridOmistaja.setPlaceholder(new Label("Ei vielä omistajia")); 
                stringGridOmistaja.setColumnWidth(-1,95); 
                 

                
                stringGridOmistaja.setOnMouseClicked( e -> { if ( e.getClickCount() > 1 ) muokkaaOmistaja(); } );
                stringGridOmistaja.setOnKeyPressed( e -> {if ( e.getCode() == KeyCode.F2 ) muokkaaOmistaja();}); 
            }     
    }
    
    /**
     * Toiminnon epäonnistuessa näyttää virheen
     */
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
    
    
    /**
     * Hakee tiedoston nimen kun luetaan tiedosto
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
//    
    /**
     * Hakee kuluvan päivämäärän ja vertaa kissan seuraavan rokotuksen ajankohtaa tähän.
     * Huomauttaa jos jollakin kissalla/kissoilla on seuraavaan rokotukseen alle 2 kk aikaa.
     */
    public void onkoRokotettava() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate nyt = LocalDate.now();
        LocalDate raja = nyt.plusMonths(2);
        Huomautukset.setFont(new Font("Courier New", 12));
        
        StringBuilder huom = new StringBuilder();
       
       List<Kissa> kissat = rekisteri.getKissat().getKissatAlkiot();
       
       for (Kissa k: kissat) {
           if(k.getSeuraavaRokotus() != null && !k.getSeuraavaRokotus().isEmpty()) {
               System.out.println(k.getSeuraavaRokotus());
           LocalDate rokotus = LocalDate.parse(k.getSeuraavaRokotus(), formatter);
           if(rokotus.isBefore(raja)) {
             huom.append("Kissalla " + k.getNimi() + " uusittava rokotus viimeistään " 
           + rokotus.format(formatter) + System.lineSeparator());
           }
          }
       }
       Huomautukset.setText(nyt.format(formatter) +" Huomautukset:" + System.lineSeparator() +System.lineSeparator() + huom.toString());       
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
            onkoRokotettava();
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
     * Näyttää listasta valitun kissan tiedot tekstikenttiin.
     */
    protected void naytaKissa() {
        kissaKohdalla = chooserKissat.getSelectedObject();

        if (kissaKohdalla == null) return;
        
        TietueDialogController.naytaTietue(edits, kissaKohdalla); 
        naytaOmistajat(kissaKohdalla);
    }
    
    
    /**
     * Hakee kissojen tiedot listaan
     * @param kissanro kissan numero, joka aktivoidaan haun jälkeen
     */
    protected void haeKissa(int kissanro) {

        int kissanumero = kissanro; // kissanumero kissan numero, joka aktivoidaan haun jälkeen 
        if ( kissanumero <= 0 ) { 
            Kissa kohdalla = kissaKohdalla; 
            if ( kohdalla != null ) kissanumero = kohdalla.getKissanTunnusNro(); 
        }
        
        int k = cbKentat.getSelectionModel().getSelectedIndex() + apukissa.ekaKentta();
        String ehto = hakuehto.getText(); 

        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 
        
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
        chooserKissat.setSelectedIndex(index); 
    }
    
    
    /**
     * Luo uuden kissan jota aletaan editoimaan 
     */
    protected void uusiKissa() {
        try {
        Kissa uusi = new Kissa();
        uusi = TietueDialogController.kysyTietue(null, uusi, 1);
        if ( uusi == null ) return;
        uusi.rekisteroi();
        rekisteri.lisaa(uusi);
        haeKissa(uusi.getKissanTunnusNro());
        } catch (SailoException e) { 
            Dialogs.showMessageDialog("Ongelmia uuden kissan luomisessa " + e.getMessage());
            return;
        }
        
    }
    
    
    /**
     * Hakee omistajat listaan
     */
    private void naytaOmistajat(Kissa kissa) {
        stringGridOmistaja.clear();
        if ( kissa == null ) return;
        
        try {
            List<Omistaja> omistajat = rekisteri.annaOmistajat(kissa);
            if ( omistajat.size() == 0 ) return;
            for (Omistaja omi: omistajat)
                naytaOmistaja(omi);
        } catch (SailoException e) { 
             naytaVirhe(e.getMessage());
        } 
    }

    
    /**
     * Hakee yksittäisen omistajan
     */
    private void naytaOmistaja(Omistaja omi) {
        int kenttia = omi.getKenttia(); 
        String[] rivi = new String[kenttia-omi.ekaKentta()]; 
        for (int i=0, k=omi.ekaKentta(); k < kenttia; i++, k++) 
            rivi[i] = omi.anna(k); 
        stringGridOmistaja.add(omi,rivi);
    }
    
 
    /**
     * Tekee uuden tyhjän omistajan editointia varten. Jos käytetään jo olemassaolevaa omistajaa, riittää kirjoittaa 
     * omistajan nimi ja sitten tallennetaan. Sen jälkeen ohjelma hakee loput tiedot ja ne tulevat näkyviin 
     * omistajan tietoihin.
     */
    private void uusiOmistaja(){
        if ( kissaKohdalla == null ) return;
        try {
            Omistaja uusi = new Omistaja(); 
            uusi = TietueDialogController.kysyTietue(null, uusi, 0);
            if ( uusi == null ) return;
            boolean onkoOmistajaOlemassa = false;
            int omistajatLkm = rekisteri.getOmistajat().getLkm();
            Omistaja[] omistajat = rekisteri.getOmistajat().getOmistajaAlkiot();
            for (int i = 0; i < omistajatLkm; i++) {
                if (omistajat[i].getNimi().equals(uusi.getNimi())){
                    onkoOmistajaOlemassa = true;
                    uusi = omistajat[i];
                    break;
                }
            }
            if(!onkoOmistajaOlemassa) {
                uusi.rekisteroi();
                rekisteri.lisaa(uusi);
            }

            rekisteri.annaKissat(uusi);
            kissaKohdalla.setOmistajanTunnusNro(uusi.getOmistajanTunnusNro());
            rekisteri.korvaaTaiLisaa(kissaKohdalla);
            naytaOmistajat(kissaKohdalla); 
            stringGridOmistaja.selectRow(1000);  // järjestetään viimeinen rivi valituksi
        } catch (SailoException e) { 
            Dialogs.showMessageDialog("Lisääminen epäonnistui: " + e.getMessage());
        }
    }
    
    
    /**
     * Muokataan valittua omistajaa
     */
    private void muokkaaOmistaja() {
        int r = stringGridOmistaja.getRowNr();
        if ( r < 0 ) return; 
        Omistaja har = stringGridOmistaja.getObject();
        if ( har == null ) return;
        int k = stringGridOmistaja.getColumnNr()+har.ekaKentta();
        try {
            har = TietueDialogController.kysyTietue(null, har.clone(), k);
            if ( har == null ) return;
            rekisteri.korvaaTaiLisaa(har); 
            naytaOmistajat(kissaKohdalla); 
            stringGridOmistaja.selectRow(r);  // järjestetään sama rivi takaisin valituksi
        } catch (CloneNotSupportedException  e) { /* clone on tehty */  
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia lisäämisessä: " + e.getMessage());
        }
    }

    
    /**
     * Muokataan valittua kissaa
     */
    private void muokkaaKissa(int k) { 
        if ( kissaKohdalla == null ) return; 
        try { 
            Kissa kissa; 
            kissa = TietueDialogController.kysyTietue(null, kissaKohdalla.clone(), k);   
            if ( kissa == null ) return; 
            rekisteri.korvaaTaiLisaa(kissa); 
            haeKissa(kissa.getKissanTunnusNro()); 
        } catch (CloneNotSupportedException e) { 
            // 
        } catch (SailoException e) { 
            Dialogs.showMessageDialog(e.getMessage()); 
        } 
    }   
    
    
    /**
     * @param rekisteri Kissankasvattajan rekisteri jota käytetään tässä käyttöliittymässä
     */
    public void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaKissa();
    }
    
    
    /**
     * Poistetaan omistajataulukosta valitulla kohdalla oleva omistaja. 
     */
    private void poistaOmistaja() {
        int rivi = stringGridOmistaja.getRowNr();
        if ( rivi < 0 ) return;
        Omistaja omistaja = stringGridOmistaja.getObject();
        if ( omistaja == null ) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko omistaja: " + omistaja.getNimi(), "Kyllä", "Ei") )
            return;
        rekisteri.poista(omistaja);
        naytaOmistajat(kissaKohdalla);
        int omistajia = stringGridOmistaja.getItems().size(); 
        if ( rivi >= omistajia ) rivi = omistajia -1;
        stringGridOmistaja.getFocusModel().focus(rivi);
        stringGridOmistaja.getSelectionModel().select(rivi);
    }


    /**
     * Poistetaan listalta valittu kissa
     */
    private void poistaKissa() {
        Kissa kissa = kissaKohdalla;
        if ( kissa == null ) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko kissa: " + kissa.getNimi(), "Kyllä", "Ei") )
            return;
        rekisteri.poista(kissa);
        int index = chooserKissat.getSelectedIndex();
        haeKissa(0);
        chooserKissat.setSelectedIndex(index);
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
 
       } catch (SailoException ex) {
            Dialogs.showMessageDialog("Omistajan hakemisessa ongelmia! " + ex.getMessage());
        }      
 
  }

    /**
     * Tulostaa listassa olevat kissat tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki kissat");
               
            
            for (Kissa kissa: chooserKissat.getObjects()) { 
                tulosta(os, kissa);
                os.println("\n\n");
            }   
            
        }
            
    }

}   
    


