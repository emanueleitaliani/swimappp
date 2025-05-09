package Query;
import Model.PrenotazioneModel;
import Other.Stampa;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryLezioni {
    private QueryLezioni(){}

    public static void PrenotaLezione(Statement smt, PrenotazioneModel prenotazione){
        try{
            int idprenotazione= prenotazione.getIdPrenotazione();
            String nome= prenotazione.getNome();
            String cognome= prenotazione.getCognome();
            String emailIstruttore= prenotazione.getEmailIstruttore();
            String emailUtente= prenotazione.getEmailUtente();
            float prezzo= prenotazione.getPrezzo();
            String giorno=prenotazione.getGiorno();
            String info =prenotazione.getInfo();
            float ora=prenotazione.getOra();

            String richiesta=String.format(Query.INSERISCIPRENOTAZIONE,idprenotazione,nome,cognome,emailIstruttore,emailUtente,prezzo,giorno,info,ora);
            smt.executeUpdate(richiesta);

        } catch(SQLException e){
            handleException(e);
        }
    };



    private static void handleException(Exception e) {
        Stampa.errorPrint(String.format("QueryPrenotazione: %s", e.getMessage()));
    }


}


