package CLI;

import Controller.SchedaNuotoController;
import Model.SchedaNuotoModel;
import Model.EsercizioModel;
import Other.Stampa;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

import java.util.List;
import java.util.Scanner;

public class VisualizzaSchedeNuotoUtenteCLI extends AbstractState {

    private final SchedaNuotoController controller;

    public VisualizzaSchedeNuotoUtenteCLI() {
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

        Stampa.printlnBlu("----- SCHEDE NUOTO DISPONIBILI -----");

        List<SchedaNuotoModel> schede = controller.getAllSchede();

        if (schede.isEmpty()) {
            Stampa.println("Nessuna scheda disponibile al momento.");
        } else {
            for (SchedaNuotoModel s : schede) {
                Stampa.println("ID Scheda: " + s.getIdScheda());
                Stampa.println("Livello: " + s.getLivello());
                Stampa.println("Durata: " + s.getDurata() + " minuti");
                Stampa.println("Distanza totale: " + s.getDistanzaTotale() + " metri");

                if (!s.getEsercizi().isEmpty()) {
                    Stampa.println("Esercizi:");
                    for (EsercizioModel e : s.getEsercizi()) {
                        Stampa.println("  - " + e.getNome() + " | Stile: " + e.getStile() +
                                " | Distanza: " + e.getDistanza() + "m | Info: " + e.getInfo());
                    }
                }
                Stampa.println("-------------------------------");
            }
        }

        Stampa.println("\nPremi INVIO per tornare al menu...");
        scan.nextLine();
        goNext(context, null); // qui puoi mettere il menu principale dell'utente
    }

    @Override
    public void stampaBenvenuto() {
        Stampa.println("👀 Visualizzazione schede nuoto per utente");
    }
}
