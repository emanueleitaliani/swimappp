package Query;

import Exceptions.UtentenonpresenteException;
import Model.RichiestaSchedaNuotoModel;
import Other.Stampa;
import Other.StatoRichiestaScheda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class QueryRichiesteSchedaNuoto {

    private QueryRichiesteSchedaNuoto() { }

    // --- Inserisce una nuova richiesta nel DB ---
    public static void inserisciRichiesta(Statement stmt, RichiestaSchedaNuotoModel richiesta) {
        try {
            int idRichiesta = richiesta.getIdRichiesta();
            String nome = richiesta.getNome();
            String cognome = richiesta.getCognome();
            String livelloUtente = richiesta.getLivelloUtente();
            String emailIstruttore = richiesta.getEmailIstruttore();
            String emailUser = richiesta.getEmailUser();
            String info = richiesta.getInfo();
            String dataRichiesta = richiesta.getDataRichiesta().toString();
            String status = richiesta.getStatus().name();

            String sql = String.format(Locale.US, Query2.INSERISCI_RICHIESTA_SCHEDA,
                    idRichiesta, nome, cognome, livelloUtente, emailIstruttore, emailUser, info, dataRichiesta, status);

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            handleException(e);
        }
    }

    // --- Cerca richieste per utente ---
    public static ResultSet cercaRichiesteByEmailUser(Statement stmt, String emailUser)
            throws SQLException, UtentenonpresenteException {

        String sql = String.format(Query2.CERCA_RICHIESTE_USER, emailUser);
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.isBeforeFirst()) { // nessuna riga trovata
            throw new UtentenonpresenteException();
        }

        return rs;
    }

    // --- Cerca richieste per istruttore ---
    public static ResultSet cercaRichiesteByEmailIstruttore(Statement stmt, String emailIstruttore)
            throws SQLException, UtentenonpresenteException {

        String sql = String.format(Query2.CERCA_RICHIESTE_ISTRUTTORE, emailIstruttore);
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.isBeforeFirst()) {
            throw new UtentenonpresenteException();
        }

        return rs;
    }

    // --- Cancella richiesta ---
    public static void cancellaRichiestaSchedaNuoto(Statement stmt, int idRichiesta, String emailUser)
            throws SQLException, UtentenonpresenteException {

        String sql = String.format(Query2.CANCELLA_RICHIESTA_SCHEDA, idRichiesta, emailUser);
        stmt.executeUpdate(sql);
    }

    // --- Aggiorna stato della richiesta ---
    public static void aggiornaStatoRichiesta(Statement stmt, int idRichiesta, StatoRichiestaScheda nuovoStato)
            throws SQLException {

        String sql = String.format(Query2.AGGIORNA_STATO_RICHIESTA, nuovoStato.name(), idRichiesta);
        stmt.executeUpdate(sql);
    }

    // --- Gestione eccezioni centralizzata ---
    private static void handleException(Exception e) {
        Stampa.errorPrint(String.format("QueryRichiesteSchedaNuoto: %s", e.getMessage()));
    }
}
