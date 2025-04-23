package View;

import Pattern.AbstractState;
import Pattern.StateMachineImpl;
import java.util.Scanner;
import Bean.Prenotazionebean;
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

        System.out.println("📚 --- Prenotazione Lezione ---");
        System.out.println("1. Visualizza calendario");
        System.out.println("2. Prenota una lezione");
        System.out.println("3. Torna indietro");

        System.out.print("Scegli un'opzione: ");
        String scelta = scanner.nextLine();

        switch (scelta) {
            case "1":
                System.out.println("Caricamento calendario...");
                // Aggiungi logica per mostrare il calendario
                break;
            case "2":
                System.out.println("Inserisci data e orario della lezione:");
                // Aggiungi logica per prenotare
                break;
            case "3":
                goBack(context); // Torna allo stato precedente
                return;
            default:
                System.out.println("❌ Scelta non valida.");
                break;
        }

        // Rimane nello stesso stato e ripete l'azione
        context.goNext();
    }

}
