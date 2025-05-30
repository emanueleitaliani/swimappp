package Pattern;
import Other.Stampa;
import View.LoginCLI;
import View.RegistrazioneCLI;
import Other.Stampa;
import java.util.Scanner;

public class Initialstate extends AbstractState {

    public void action(StateMachineImpl context) {
        AbstractState nextState;
        mostraSchermata();

        Scanner scanner = new Scanner(System.in);
        Stampa.println("Scegli un'opzione: [1] Login | [2] Registrazione | [0] Esci: ");
        String scelta = scanner.nextLine();

        switch (scelta) {
            case "1":
                nextState = new LoginCLI();
                goNext(context,nextState);
                break;
            case "2":
                nextState = new RegistrazioneCLI();
                goNext(context,nextState);
                break;
            case "0":
                Stampa.println("Uscita dall'applicazione...");
                System.exit(0);
                break;
            default:
                Stampa.println("Scelta non valida. Riprova.");
                  // Ricomincia dallo stesso stato
                break;
        }
    }

    @Override
    public void mostraSchermata() {
        Stampa.println("\n--- Benvenuto nell'applicazione SwimApp! ---");
        Stampa.println("1. Effettua il Login");
        Stampa.println("2. Registrati");
        Stampa.println("0. Esci");
    }

    @Override
    public void stampaBenvenuto() {
        System.out.println("Ciao! Benvenuto nella schermata iniziale!");
    }

    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
    }

    @Override
    public void exit(StateMachineImpl context) {
        System.out.println("Uscita dalla schermata iniziale...");
    }
}

