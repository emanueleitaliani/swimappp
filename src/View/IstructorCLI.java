package View;

import Pattern.AbstractState;
import Bean.Utenteloggatobean;
import Pattern.StateMachineImpl;

public class IstructorCLI extends AbstractState {
    private final Utenteloggatobean utente;
    public IstructorCLI(Utenteloggatobean utente) {
        this.utente = utente;
    }
}
