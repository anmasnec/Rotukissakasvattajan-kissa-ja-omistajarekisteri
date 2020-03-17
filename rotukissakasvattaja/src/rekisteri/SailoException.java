package rekisteri;



/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille.
 * @author annik
 * @version 2.3.2020
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;


    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }

}
