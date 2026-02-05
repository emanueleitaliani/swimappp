package Query;

import Model.SchedaNuotoModel;
import Other.Stampa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class QuerySchedaNuoto {

    // Costruttore privato per impedire istanze
    private QuerySchedaNuoto() { }

    // --- Inserisce una nuova scheda nel DB ---
    public static void inserisciScheda(Statement stmt, SchedaNuotoModel scheda) {
        try {
            String sql = String.format(Locale.US, Query2.INSERISCI_SCHEDA,
                    scheda.getIdScheda(),
                    scheda.getDistanzaTotale(),
                    scheda.getDurata(),
                    scheda.getLivello());

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            handleException(e);
        }
    }

    // --- Recupera una scheda tramite ID ---
    public static ResultSet cercaSchedaById(Statement stmt, String idScheda) throws SQLException {
        String sql = String.format(Query2.CERCA_SCHEDA_BY_ID, idScheda);
        return stmt.executeQuery(sql);
    }

    // --- Recupera tutte le schede ---
    public static ResultSet getAllSchede(Statement stmt) throws SQLException {
        String sql = Query2.CERCA_TUTTE_LE_SCHEDE;
        return stmt.executeQuery(sql);
    }

    // --- Aggiorna una scheda ---
    public static void aggiornaScheda(Statement stmt, SchedaNuotoModel scheda) throws SQLException {
        String sql = String.format(Locale.US, Query2.AGGIORNA_SCHEDA,
                scheda.getDistanzaTotale(),
                scheda.getDurata(),
                scheda.getLivello(),
                scheda.getIdScheda());
        stmt.executeUpdate(sql);
    }

    // --- Cancella una scheda ---
    public static void cancellaScheda(Statement stmt, String idScheda) throws SQLException {
        String sql = String.format(Query2.CANCELLA_SCHEDA, idScheda);
        stmt.executeUpdate(sql);
    }

    // --- Gestione eccezioni centralizzata ---
    private static void handleException(Exception e) {
        Stampa.errorPrint(String.format("SchedaNuotoQuery: %s", e.getMessage()));
    }
}
