package View;

import Other.Stampa;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;
import java.util.Scanner;
import Controller.Prenotazionecontroller;
import Bean.Utenteloggatobean;
import Bean.Userbean;

public class PrenotaLezioneCLI extends AbstractState {
    private float prezzo;
    private String giorno;
    private String info;
    private float ora;

    @Override
    public void action(StateMachineImpl context) {
        Scanner scanner = new Scanner(System.in);

        Stampa.println("Scegli un'opzione: ");
        int scelta ;
        while((scelta = scanner.nextInt()) != 0) {
            switch (scelta) {
                case (1):
                    Stampa.println("hai scelto di prenotare la Lezione");
                    // Aggiungi logica per mostrare il calendario
                    Inseriscivalori(context, scanner);
                    break;
                case (0):
                    goBack(context);
                    break;

                default:
                    Stampa.println("❌ Scelta non valida.");
                    break;
            }
        }
        // Rimane nello stesso stato e ripete l'azione
        context.goNext();
    }
    public void Inseriscivalori(StateMachineImpl context, Scanner scanner) {
        Stampa.println("📅 Inserisci il giorno della lezione (es: 10-05-2024): rispettando giorno-mese-anno ");
        String giorno = scanner.nextLine();
        if (!validaData(giorno)) {
            Stampa.println("❌ Data non valida.");
            return;
        }
        Stampa.print("🕒 Inserisci l'orario (es: 14.30): ");
        float ora = Float.parseFloat(scanner.nextLine());

        Stampa.print("💬 Inserisci info aggiuntive: ");
        String info = scanner.nextLine();

        Stampa.print("💰 Inserisci il prezzo (€): ");
        float prezzo = Float.parseFloat(scanner.nextLine());

        Stampa.print("👤 Inserisci nome dell'istruttore: ");
        String nomeIstruttore = scanner.nextLine();

        Stampa.println("Inserisci il cognome dell'istruttore:");
        String cognomeIstruttore = scanner.nextLine();

        Stampa.print("📧 Inserisci email dell'istruttore: ");
        String emailIstruttore = scanner.nextLine();
    }

    public void mostraSchermata() {
        Stampa.println("📚 --- Benvenuto in prenota lezione di nuoto ---");
        Stampa.println("1. Prenota lezione");
        Stampa.println("0. Torna indietro");

    }

    public static boolean validaData(String dataInput) {
        if (dataInput == null || !dataInput.matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
            return false;
        }

        String[] parts = dataInput.split("-");
        try {
            int giorno = Integer.parseInt(parts[0]);
            int mese = Integer.parseInt(parts[1]);
            int anno = Integer.parseInt(parts[2]);

            return (giorno >= 1 && giorno <= 31) &&
                    (mese >= 1 && mese <= 12) &&
                    (anno == 2025);
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
