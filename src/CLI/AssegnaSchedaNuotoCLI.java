package CLI;

import Bean.Utenteloggatobean;
import Controller.SchedaNuotoAssegnataController;
import Controller.SchedaNuotoController;
import Model.SchedaNuotoAssegnataModel;
import Model.SchedaNuotoModel;
import Other.Stampa;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

import java.util.List;
import java.util.Scanner;

public class AssegnaSchedaNuotoCLI extends AbstractState {

    private final Utenteloggatobean istruttore;
    private final SchedaNuotoController schedaController;
    private final SchedaNuotoAssegnataController assegnataController;

    public AssegnaSchedaNuotoCLI(Utenteloggatobean istruttore) {
        this.istruttore = istruttore;
        this.schedaController = new SchedaNuotoController();
        this.assegnataController = new SchedaNuotoAssegnataController();
    }

    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
        action(context);
    }

    @Override
    public void action(StateMachineImpl context) {
        Scanner scan = new Scanner(System.in);

        Stampa.printlnBlu("----- ASSEGNA SCHEDA NUOTO -----");

        // Mostra tutte le schede disponibili
        List<SchedaNuotoModel> schede = schedaController.getAllSchede();
        if (schede.isEmpty()) {
            Stampa.println("❌ Nessuna scheda disponibile!");
            goNext(context, new IstructorCLI(istruttore));
            return;
        }

        Stampa.println("Schede disponibili:");
        for (int i = 0; i < schede.size(); i++) {
            SchedaNuotoModel s = schede.get(i);
            Stampa.println((i + 1) + ". ID: " + s.getIdScheda() +
                    ", Livello: " + s.getLivello() +
                    ", Durata: " + s.getDurata() +
                    " min, Distanza: " + s.getDistanzaTotale() + " m");
        }

        Stampa.print("Seleziona il numero della scheda da assegnare: ");
        int scelta = scan.nextInt();
        scan.nextLine(); // pulizia buffer

        if (scelta < 1 || scelta > schede.size()) {
            Stampa.errorPrint("Scelta non valida!");
            goNext(context, new IstructorCLI(istruttore));
            return;
        }

        SchedaNuotoModel schedaSelezionata = schede.get(scelta - 1);

        Stampa.print("Inserisci l'email dell'utente a cui assegnare la scheda: ");
        String emailUser = scan.nextLine();

        // Creazione model per assegnazione
        SchedaNuotoAssegnataModel assegnata = new SchedaNuotoAssegnataModel(
                schedaSelezionata.getIdScheda(),
                emailUser,
                schedaSelezionata.getDistanzaTotale(),
                schedaSelezionata.getDurata()
        );

        // Salvataggio tramite controller
        assegnataController.assegnaScheda(assegnata);

        Stampa.println("✅ Scheda assegnata con successo a " + emailUser + "!");

        Stampa.println("\nPremi INVIO per tornare al menu...");
        scan.nextLine();

        goNext(context, new IstructorCLI(istruttore));
    }

    @Override
    public void stampaBenvenuto() {
        Stampa.printlnBlu("👨‍🏫 Istruttore: " + istruttore.getNome());
        Stampa.println("Seleziona la scheda di nuoto da assegnare a un utente:");
    }
}
