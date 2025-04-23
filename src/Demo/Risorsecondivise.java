package Demo;
import Model.UtenteloggatoModel;
import Model.CredenzialiModel;
import Model.IstruttoreModel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class Risorsecondivise {
    private final Map<String, UtenteloggatoModel> utentiRegistrati = new ConcurrentHashMap<>();

    // Mappa email → credenziali (email + password)
    private final Map<String, CredenzialiModel> credenzialiUtenti = new ConcurrentHashMap<>();

    private final Map<String, IstruttoreModel>  istruttoreMap= new ConcurrentHashMap<>();
    // Singleton
    private static Risorsecondivise instance = null;

    private Risorsecondivise() {}

    public static synchronized Risorsecondivise getInstance() {
        if (instance == null) {
            instance = new Risorsecondivise();
        }
        return instance;
    }

    public Map<String, UtenteloggatoModel> getUtentiRegistrati() {
        return utentiRegistrati;
    }

    public Map<String, CredenzialiModel> getCredenzialiUtenti() {
        return credenzialiUtenti;
    }

    public Map<String, IstruttoreModel> getIstruttori() {
        return istruttoreMap;
    }
}

