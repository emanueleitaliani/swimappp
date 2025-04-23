package View;

import Pattern.AbstractState;
import Bean.Utenteloggatobean;
import Pattern.StateMachineImpl;

public class IstructorCLI extends AbstractState{
    private final Utenteloggatobean utente;
    public IstructorCLI(Utenteloggatobean utente) {
        this.utente = utente;
    }
    @Override
    public void action(StateMachineImpl context) {
        // Qui puoi inserire il codice da eseguire quando questo stato è attivo
        System.out.println("Benvenuto, " + utente.getCredenziali().getEmail() + " (ISTRUTTORE)");

        // Esempio di menu:
        System.out.println("1. Visualizza studenti");
        System.out.println("2. Aggiungi esercizio");
        System.out.println("3. Logout");

        // Simulazione uscita dallo stato (puoi legarlo all'input utente se vuoi)
        // goBack(context); // oppure goNext(context, new AltraViewState(...));
    }
}


