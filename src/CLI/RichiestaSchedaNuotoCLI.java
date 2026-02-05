package CLI;

import Bean.RichiestaSchedaNuotoBean;
import Bean.Utenteloggatobean;
import Controller.RichiestaSchedaNuotoController;
import Other.Stampa;
import Other.StatoRichiestaScheda;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class RichiestaSchedaNuotoCLI extends AbstractState {

    private final Utenteloggatobean utente;

    public RichiestaSchedaNuotoCLI(Utenteloggatobean utente) {
        this.utente = utente;
    }

    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
        action(context);
    }

    @Override
    public void action(StateMachineImpl context) {
        Scanner scanner = new Scanner(System.in);
        int scelta = -1;

        while (scelta != 0) {
            mostraSchermata();
            Stampa.print("Scegli un'opzione: ");
            String input = scanner.nextLine();

            try {
                scelta = Integer.parseInt(input);

                switch (scelta) {
                    case 1:
                        inserisciRichiesta(scanner);
                        break;
                    case 0:
                        goBack(context);
                        return;
                    default:
                        Stampa.println("❌ Scelta non valida.");
                }

            } catch (NumberFormatException e) {
                Stampa.println("❌ Input non valido. Inserisci un numero intero.");
            }
        }
    }

    private void inserisciRichiesta(Scanner scanner) {
        RichiestaSchedaNuotoController controller = new RichiestaSchedaNuotoController();
        RichiestaSchedaNuotoBean bean = new RichiestaSchedaNuotoBean();

        try {
            int idRandom = ThreadLocalRandom.current().nextInt(0, 1000);
            bean.setIdRichiesta(idRandom);

            Stampa.print("📧 Inserisci l'email dell'istruttore: ");
            String emailIstruttore = scanner.nextLine();
            bean.setEmailIstruttore(emailIstruttore);

            Stampa.print("📄 Inserisci informazioni aggiuntive: ");
            String info = scanner.nextLine();
            bean.setInfo(info);

            Stampa.print("💪 Inserisci il tuo livello: ");
            String livello = scanner.nextLine();
            bean.setLivelloUtente(livello);

            bean.setNome(utente.getNome());
            bean.setCognome(utente.getCognome());
            bean.setEmailUser(utente.getCredenziali().getEmail());
            bean.setDataRichiesta(LocalDate.now());
            bean.setStatus(StatoRichiestaScheda.INCORSO);

            controller.inserisciRichiesta(bean);

            Stampa.println("\n✅ Richiesta di scheda di nuoto inviata con successo!");
            Stampa.println("👤 Istruttore: " + emailIstruttore);
            Stampa.println("💪 Livello: " + bean.getLivelloUtente());
            Stampa.println("📝 Info: " + info);

        } catch (Exception e) {
            Stampa.println("❌ Errore durante l'invio della richiesta: " + e.getMessage());
        }
    }

    @Override
    public void exit(StateMachineImpl context) {
        Stampa.println("🔙 Tornato al menu principale");
    }

    @Override
    public void stampaBenvenuto() {
        Stampa.println("📚 --- Benvenuto nella richiesta della scheda di nuoto ---");
        Stampa.println("Ciao " + utente.getNome() + ", scegli un'opzione:");
    }

    @Override
    public void mostraSchermata() {
        Stampa.println("1. Invia richiesta scheda di nuoto");
        Stampa.println("0. Torna indietro");
    }
}
