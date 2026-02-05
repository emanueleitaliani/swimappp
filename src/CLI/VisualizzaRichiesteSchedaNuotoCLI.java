package CLI;

import java.util.List;

import Bean.RichiestaSchedaNuotoBean;
import Bean.Utenteloggatobean;
import Controller.RichiestaSchedaNuotoController;
import Other.Stampa;
import Other.StatoRichiestaScheda;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

public class VisualizzaRichiesteSchedaNuotoCLI extends AbstractState {

    private final Utenteloggatobean user;

    public VisualizzaRichiesteSchedaNuotoCLI(Utenteloggatobean user) {
        this.user = user;
    }

    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
        action(context);
    }

    @Override
    public void action(StateMachineImpl context) {
        try {
            RichiestaSchedaNuotoController controller = new RichiestaSchedaNuotoController();
            List<RichiestaSchedaNuotoBean> richieste =
                    controller.getRichiesteByEmailUser(user.getCredenziali().getEmail());

            if (richieste == null || richieste.isEmpty()) {
                Stampa.println("🔎 Nessuna richiesta di scheda nuoto trovata.");
            } else {
                Stampa.println("📋 Le tue richieste di scheda nuoto:");

                for (RichiestaSchedaNuotoBean r : richieste) {
                    Stampa.println("-----------------------------");
                    Stampa.println("📌 ID Richiesta: " + r.getIdRichiesta());

                    // --- VISUALIZZAZIONE STATO ---
                    StatoRichiestaScheda stato = r.getStatus();
                    if (stato == StatoRichiestaScheda.ACCETTATA) {
                        Stampa.println("✅ STATO: " + stato);
                    } else if (stato == StatoRichiestaScheda.RIFIUTATA) {
                        Stampa.println("❌ STATO: " + stato);
                    } else {
                        Stampa.println("⏳ STATO: " + stato + " (In attesa)");
                    }
                    // ----------------------------

                    Stampa.println("👤 Nome: " + r.getNome() + " " + r.getCognome());
                    Stampa.println("🏊 Livello: " + r.getLivelloUtente());
                    Stampa.println("👨‍🏫 Email Istruttore: " + r.getEmailIstruttore());
                    Stampa.println("📝 Info: " + r.getInfo());
                    Stampa.println("📅 Data richiesta: " + r.getDataRichiesta());
                    Stampa.println("📧 Email Utente: " + r.getEmailUser());
                }
            }

        } catch (Exception e) {
            Stampa.errorPrint("❌ Errore durante la visualizzazione: " + e.getMessage());
        }

        Stampa.println("\nPremi INVIO per tornare indietro...");
        new java.util.Scanner(System.in).nextLine();
        goBack(context);
    }

    @Override
    public void stampaBenvenuto() {
        Stampa.println("🔍 Visualizzazione Richieste Scheda Nuoto");
    }
}
