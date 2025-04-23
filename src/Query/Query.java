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
            "INSERT INTO utente (email, nome, cognome, password, isTutor) VALUES ('%s', '%s', '%s', '%s', %b)";

}


