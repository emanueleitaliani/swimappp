package View;

import Pattern.AbstractState;
import Bean.Utenteloggatobean;
import Pattern.Initialstate;
import Pattern.StateMachineImpl;
import Other.Stampa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IstructorCLI extends AbstractState{
    private final Utenteloggatobean utente;
    public IstructorCLI(Utenteloggatobean utente) {
        this.utente = utente;
    }
    @Override
    public void action(StateMachineImpl context) {
        Scanner scan = new Scanner(System.in);
        int choice;
        while((choice = scan.nextInt()) != 0) {

            try{
                switch(choice){
                    case(1):
                        // Modificato per cercare una lezione di nuoto
                        goNext(context, new AssegnaschedaCLI());
                        break;
                    case(2):
                        // Modificato per gestire le creazioni di schede
                        // opzioni per prenotare scheda
                    default:
                        Stampa.errorPrint("Input invalido. Scegliere un'opzione tra quelle disponibili: ");
                        break;
                }
            } catch (InputMismatchException e){
                Stampa.errorPrint("Input non valido. Per favore, inserisci un numero intero: ");
                scan.nextLine(); // Consuma l'input non valido)
            }
        }

        goNext(context, new Initialstate());
    }
    public void mostraSchermata() {
        Stampa.println("1.assegna scheda");
        Stampa.println("0.Logout");
        Stampa.print("Opzione scelta:");
    }
    @Override
    public void stampaBenvenuto() {
        Stampa.println(" ");
        Stampa.println("-------------------Benvenuto nella home dell'istruttore-------------------");
        Stampa.println("Ciao" + " " + this.utente.getNome() + ",scegli cosa vuoi fare:");
    }
    @Override
    public void entry(StateMachineImpl context) {
        stampaBenvenuto();
        mostraSchermata();
    }


        // goBack(context); // oppure goNext(context, new AltraViewState(...));

}


