package CLI;

import Bean.EsercizioBean;
import Bean.Utenteloggatobean;
import Controller.SchedaNuotoController;
import Model.EsercizioModel;
import Model.SchedaNuotoModel;
import Other.Stampa;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

import java.util.Scanner;

/**
 * CLI per creare una nuova scheda nuoto, con inserimento esercizi.
 */
public class CreaSchedaNuotoCLI extends AbstractState {

    private final Utenteloggatobean istruttore;
    private final SchedaNuotoController controller;

    public CreaSchedaNuotoCLI(Utenteloggatobean istruttore) {
        this.istruttore = istruttore;
        this.controller = new SchedaNuotoController();
    }

    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
        action(context);
    }

    @Override
    public void action(StateMachineImpl context) {
        Scanner scan = new Scanner(System.in);

        Stampa.printlnBlu("----- CREAZIONE SCHEDA NUOTO -----");

        // --- Dati base scheda ---
        Stampa.print("ID Scheda: ");
        String idScheda = scan.nextLine();

        Stampa.print("Livello (BASE / INTERMEDIO / AVANZATO): ");
        String livello = scan.nextLine();

        Stampa.print("Durata (minuti): ");
        int durata = scan.nextInt();

        Stampa.print("Distanza totale (metri): ");
        int distanza = scan.nextInt();
        scan.nextLine(); // pulizia buffer

        // --- Creazione MODEL scheda ---
        SchedaNuotoModel scheda = new SchedaNuotoModel(
                idScheda,
                distanza,
                durata,
                livello
        );

        // --- Aggiunta esercizi ---
        Stampa.println("✅ Ora inserisci gli esercizi della scheda.");
        boolean continua = true;
        while (continua) {
            Stampa.print("Nome esercizio: ");
            String nome = scan.nextLine();

            Stampa.print("Stile: ");
            String stile = scan.nextLine();

            Stampa.print("Distanza (metri): ");
            int distEsercizio = scan.nextInt();

            scan.nextLine(); // pulizia buffer

            Stampa.print("Info aggiuntive: ");
            String info = scan.nextLine();

            // Creazione modello esercizio
            EsercizioModel esercizio = new EsercizioModel(nome, stile, distEsercizio, info);

            // Aggiunta alla scheda
            scheda.getEsercizi().add(esercizio);

            Stampa.print("Vuoi inserire un altro esercizio? (S/N): ");
            String risposta = scan.nextLine();
            if (!risposta.equalsIgnoreCase("S")) {
                continua = false;
            }
        }

        // --- Salvataggio scheda tramite controller ---
        controller.insertScheda(scheda);
        Stampa.println("✅ Scheda nuoto creata con successo!");

        // --- Fine e ritorno al menu istruttore ---
        Stampa.println("\nPremi INVIO per tornare al menu...");
        scan.nextLine();

        goNext(context, new IstructorCLI(istruttore));
    }

    @Override
    public void stampaBenvenuto() {
        Stampa.println("👨‍🏫 Istruttore: " + istruttore.getNome());
    }
}
