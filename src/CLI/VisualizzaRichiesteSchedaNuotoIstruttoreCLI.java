package CLI;

import Bean.RichiestaSchedaNuotoBean;
import Bean.Utenteloggatobean;
import Controller.RichiestaSchedaNuotoController;
import Other.Stampa;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

import java.util.List;
import java.util.Scanner;

public class VisualizzaRichiesteSchedaNuotoIstruttoreCLI extends AbstractState {

    private final Utenteloggatobean istruttore;
    private final RichiestaSchedaNuotoController controller;

    public VisualizzaRichiesteSchedaNuotoIstruttoreCLI(Utenteloggatobean istruttore) {
        this.istruttore = istruttore;
        this.controller = new RichiestaSchedaNuotoController();
    }

    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
        action(context);
    }

    @Override
    public void action(StateMachineImpl context) {
        try {
            List<RichiestaSchedaNuotoBean> richieste =
                    controller.getRichiesteByEmailIstruttore(
                            istruttore.getCredenziali().getEmail()
                    );

            if (richieste == null || richieste.isEmpty()) {
                Stampa.println("📭 Nessuna richiesta di scheda nuoto trovata.");
            } else {
                Stampa.println("📋 Richieste di scheda nuoto ricevute:\n");

                for (RichiestaSchedaNuotoBean r : richieste) {
                    Stampa.println("----------------------------------");
                    Stampa.println("📌 ID Richiesta: " + r.getIdRichiesta());
                    Stampa.println("👤 Utente: " + r.getNome() + " " + r.getCognome());
                    Stampa.println("🏊 Livello: " + r.getLivelloUtente());
                    Stampa.println("📧 Email Utente: " + r.getEmailUser());
                    Stampa.println("📅 Data: " + r.getDataRichiesta());
                    Stampa.println("📄 Info: " + r.getInfo());
                    Stampa.println("⏳ Stato: " + r.getStatus());
                }
            }

        } catch (Exception e) {
            Stampa.errorPrint("❌ Errore durante la visualizzazione delle richieste.");
        }

        Stampa.println("\nPremi INVIO per tornare indietro...");
        new Scanner(System.in).nextLine();
        goBack(context);
    }

    @Override
    public void stampaBenvenuto() {
        Stampa.printlnBlu("--------- RICHIESTE SCHEDA NUOTO ---------");
        Stampa.println("Istruttore: " + istruttore.getNome());
    }
}
