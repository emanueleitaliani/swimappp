package CLI;

import java.util.*;
import Bean.CredenzialiBean;
import Bean.LezioneBean;
import Bean.Utenteloggatobean;
import Controller.Prenotazionecontroller;
import Other.Stampa;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

public class CercaLezioneCLI extends AbstractState {



    private final Utenteloggatobean user;
    private LezioneBean lezioneInfoBean;
    private List<LezioneBean> risultatiBean = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public CercaLezioneCLI(Utenteloggatobean user) {
        this.user = user;
    }

    @Override
    public void action(StateMachineImpl context) {
        boolean ricercaInCorso = true;

        while (ricercaInCorso) {
            risultatiBean = cercalezione();

            // Se la lista è null o vuota, gestiamo l'assenza di risultati
            if (risultatiBean == null || risultatiBean.isEmpty()) {
                Stampa.errorPrint("\n--- Nessun risultato trovato con i filtri inseriti ---");
                Stampa.println("Cosa desideri fare?");
                Stampa.println("1. Modifica i filtri e riprova");
                Stampa.println("0. Torna alla Home");
                Stampa.print("Scelta: ");

                String scelta = scanner.nextLine();
                if (scelta.equals("0")) {
                    ricercaInCorso = false;
                    goNext(context, new UserCLI(user));
                    return; // Usciamo dal metodo per evitare di proseguire a RisultatiLezioniCLI
                }
                // Se sceglie "1", il ciclo continua e richiama cercalezione()
            } else {
                // Risultati trovati, usciamo dal ciclo e proseguiamo
                ricercaInCorso = false;
                Stampa.println("\nOttimo! Sono state trovate " + risultatiBean.size() + " lezioni.");
                goNext(context, new RisultatiLezioniCLI(user, risultatiBean, lezioneInfoBean));
            }
        }
    }

    public List<LezioneBean> cercalezione() {
        Stampa.println("\n--- Inserimento Filtri (Digita 'exit' in qualsiasi momento per annullare) ---");

        Stampa.print("Inserisci il tipo di lezione (privata, in gruppo): ");
        String tipo = scanner.nextLine();
        if(tipo.equalsIgnoreCase("exit")) return new ArrayList<>();

        Stampa.print("Inserisci il livello (principiante, intermedio, agonista): ");
        String livello = scanner.nextLine();

        Stampa.print("Inserisci la fascia oraria (es. 10-12, 14-16): ");
        String fasciaOraria = scanner.nextLine();

        Stampa.print("Inserisci il giorno (es. lunedi, martedi): ");
        String giorni = scanner.nextLine().replace("'", "").toLowerCase();

        Stampa.print("Inserisci note aggiuntive o premi Invio: ");
        String noteAggiuntive = scanner.nextLine();

        Float prezzoMassimo;
        Stampa.print("Prezzo massimo: ");
        try {
            prezzoMassimo = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            prezzoMassimo = null;
        }

        // Preparazione del Bean per la ricerca
        CredenzialiBean credenzialiFittizie = new CredenzialiBean("");
        Utenteloggatobean istruttoreVuoto = new Utenteloggatobean(credenzialiFittizie, "", "");
        lezioneInfoBean = new LezioneBean(tipo, giorni, prezzoMassimo, istruttoreVuoto, livello, fasciaOraria, noteAggiuntive);

        // Chiamata al Controller
        Prenotazionecontroller prenotazioneController = new Prenotazionecontroller();
        return prenotazioneController.ricercaLezione(lezioneInfoBean);
    }

    @Override
    public void stampaBenvenuto() {
        Stampa.println(" ");
        Stampa.printBlu("Home Utente -> ");
        Stampa.printBlu("PrenotaLezione -> ");
        Stampa.printlnBlu("Ricerca");
        Stampa.printlnBlu("--------------- CERCA LEZIONI DI NUOTO ------------------");
    }

    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
        action(context);
    }
}