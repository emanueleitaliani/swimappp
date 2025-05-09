package Controller;
import Bean.Utenteloggatobean;
import Dao.PrenotazioneDao;
import Model.PrenotazioneModel;
import Other.Stampa;

public class Prenotazionecontroller {
    public PrenotazioneModel prenotaLezione(Utenteloggatobean utente, int idPrenotazione, String emailUtente, String emailIstruttore, float prezzo, String giorno, String info, float ora) {

        // Crea l'oggetto del Model
        PrenotazioneModel prenotazione = new PrenotazioneModel(utente, idPrenotazione, emailUtente, prezzo, giorno, info, ora);

        // Salva la prenotazione nel database tramite la DAO


        return prenotazione;
    }
}
