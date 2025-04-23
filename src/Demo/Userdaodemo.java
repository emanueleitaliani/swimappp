package Demo;

import Dao.UserDao;
import Exceptions.CredenzialisbagliateException;
import Exceptions.EmailgiainusoException;
import Exceptions.UtentenonpresenteException;
import Model.CredenzialiModel;
import Model.UtenteloggatoModel;
import Model.IstruttoreModel;
import Other.Stampa;

import java.util.function.Consumer;


public class Userdaodemo implements UserDao {

    @Override
    public UtenteloggatoModel loginMethod(CredenzialiModel credenzialiModel)throws UtentenonpresenteException, CredenzialisbagliateException{
        String email = normalize(credenzialiModel.getEmail());
        CredenzialiModel stored = Risorsecondivise.getInstance().getCredenzialiUtenti().get(email);

        if (stored == null) {
            throw new UtentenonpresenteException();
        }

        if (!stored.getPassword().equals(credenzialiModel.getPassword())) {
            throw new CredenzialisbagliateException();
        }

        return Risorsecondivise.getInstance().getUtentiRegistrati().get(email);

    };

    @Override
    public void registrazioneMethod(UtenteloggatoModel registrazioneModel){
        String email = normalize(registrazioneModel.getCredenziali().getEmail());

        // Inserisci credenziali
        Risorsecondivise.getInstance().getCredenzialiUtenti().put(email, registrazioneModel.getCredenziali());
        // Inserisci utente
        Risorsecondivise.getInstance().getUtentiRegistrati().put(email, registrazioneModel);
    }

    @Override
    public void controllaEmailMethod(UtenteloggatoModel registrazioneModel) throws EmailgiainusoException{
        String email = normalize(registrazioneModel.getCredenziali().getEmail());
        if (Risorsecondivise.getInstance().getUtentiRegistrati().containsKey(email)) {
            throw new EmailgiainusoException();
        }
    }

    public void registraIstruttoreMethod(String email, String nome, String cognome){
        UtenteloggatoModel utenteBase = Risorsecondivise.getInstance().getUtentiRegistrati().get(email.trim().toLowerCase());

        if (utenteBase == null) {
            // Potresti anche lanciare un'eccezione qui se necessario
            Stampa.println("Errore: utente non trovato per la registrazione come istruttore.");
            return;
        }


        IstruttoreModel istruttore = new IstruttoreModel();
        istruttore.setCredenziali(utenteBase.getCredenziali());
        istruttore.setNome(nome);
        istruttore.setCognome(cognome);
        istruttore.setIstructor(true);


        Risorsecondivise.getInstance().getIstruttori().put(email.trim().toLowerCase(), istruttore);
    }

    private String normalize(String email) {
        return email.trim().toLowerCase();
    }
}
