package Query;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.PrenotazioneModel;
import Other.Stampa;

public class QueryPrenotazione {

    public static void richiediPrenotazione(Statement s,PrenotazioneModel pren){
        try{
            Integer idPrenotazione = pren.getIdPrenotazione();
            String nome=pren.getNome();
            String cognome=pren.getCognome();
            String emailIstruttore=pren.getEmailIstruttore();
            String emailUtente=pren.getEmailUtente();
            String giorno= pren.getInfo();
            float ora=pren.getOra();
            float prezzo=pren.getPrezzo();
            String info=pren.getInfo();

            String richiestapren = String.format(Query.PRENOTA,idPrenotazione,nome,cognome,emailIstruttore,emailUtente,giorno,ora,prezzo,info);
            s.executeUpdate(richiestapren);
        }
        catch(SQLException e){

        }

    }
    public static void recensione(Statement stmt, int idPrenotazione, int stelle){
        String sql;

        try{
            sql = String.format(Query.RECENSIONE,idPrenotazione,stelle);
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            handleException(e);
        }
    }
}
