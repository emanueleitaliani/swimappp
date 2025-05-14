package Query;

public class Query {

    private Query() {
        // Costruttore privato per evitare istanziazione
    }

    // Query per cercare se un'email esiste (usata sia nel login che nella registrazione)
    public static final String RICERCA_EMAIL =
            "SELECT email FROM utente WHERE email = '%s'";

    // Query per verificare email e password per il login
    public static final String VERIFICA_USER =
            "SELECT * FROM utente WHERE email = '%s' AND password = '%s'";

    // Query per registrare un nuovo utente
    public static final String REGISTRAZIONE =
            "INSERT INTO utente (nome, cognome,email, isIstructor ,password) VALUES ('%s', '%s', '%s', %b,'%s')";

    public static final String INSERISCIPRENOTAZIONE =
            "INSERT INTO prenotazione(id prenotazione,nomeIstruttore,cognomeIstruttore,emailIstruttore,emailUtente,prezzo,giorno,info,ora) VALUES ('%d','%s','%s','%s','%s','%lf','%s','%s','%lf')";

}


