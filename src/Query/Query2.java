package Query;

public class Query2 {
    private Query2(){
        // Costruttore privato per evitare istanziazione
    }

    // --- Inserisci richiesta scheda nuoto ---
    public static final String INSERISCI_RICHIESTA_SCHEDA =
            "INSERT INTO richiesteschedanuoto (idRichiesta, nome, cognome, livelloUtente, emailIstruttore, emailUser, info, dataRichiesta, status) " +
                    "VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";

    // --- Cerca richieste per utente ---
    public static final String CERCA_RICHIESTE_USER =
            "SELECT * FROM richiesteschedanuoto WHERE emailUser = '%s'";

    // --- Cerca richieste per istruttore ---
    public static final String CERCA_RICHIESTE_ISTRUTTORE =
            "SELECT * FROM richiesteschedanuoto WHERE emailIstruttore = '%s'";

    // --- Cancella richiesta ---
    public static final String CANCELLA_RICHIESTA_SCHEDA =
            "DELETE FROM richiesteschedanuoto WHERE idRichiesta = %d AND emailUser = '%s'";

    // --- Aggiorna stato richiesta ---
    public static final String AGGIORNA_STATO_RICHIESTA =
            "UPDATE richiesteschedanuoto SET status = '%s' WHERE idRichiesta = %d";

    // Inserisce una nuova scheda
    public static final String INSERISCI_SCHEDA =
            "INSERT INTO scheda_nuoto (idScheda, distanzaTotale, durata, livello) VALUES ('%s', %d, %d, '%s')";

    // Cerca scheda tramite ID
    public static final String CERCA_SCHEDA_BY_ID =
            "SELECT * FROM scheda_nuoto WHERE idScheda = '%s'";

    // Recupera tutte le schede
    public static final String CERCA_TUTTE_LE_SCHEDE =
            "SELECT * FROM scheda_nuoto";

    // Aggiorna una scheda
    public static final String AGGIORNA_SCHEDA =
            "UPDATE scheda_nuoto SET distanzaTotale = %d, durata = %d, livello = '%s' WHERE idScheda = '%s'";

    // Cancella una scheda
    public static final String CANCELLA_SCHEDA =
            "DELETE FROM scheda_nuoto WHERE idScheda = '%s'";


    public static final String INSERISCI_ASSEGNAZIONE =
            "INSERT INTO schede_nuoto_assegnate (emailUser, emailIstruttore, idScheda, dataAssegnazione) " +
                    "VALUES ('%s', '%s', '%s', '%s')";

    public static final String CERCA_SCHEDE_USER =
            "SELECT * FROM schede_nuoto_assegnate WHERE emailUser = '%s'";

    public static final String CERCA_SCHEDE_ISTRUTTORE =
            "SELECT * FROM schede_nuoto_assegnate WHERE emailIstruttore = '%s'";

    // -------- SCHEDA NUOTO ASSEGNATA --------

    // assegna scheda a utente
    public static final String ASSEGNA_SCHEDA =
            "INSERT INTO schede_nuoto_assegnate (idScheda, emailUser, emailIstruttore) " +
                    "VALUES ('%s', '%s', '%s')";

    // cerca scheda assegnata a un utente
    public static final String CERCA_SCHEDA_ASSEGNATA_USER =
            "SELECT * FROM schede_nuoto_assegnate WHERE emailUser = '%s'";

    // tutte le schede assegnate
    public static final String CERCA_TUTTE_SCHEDE_ASSEGNATE =
            "SELECT * FROM schede_nuoto_assegnate";



}
