package Pattern;

public class AbstractState {

    protected AbstractState(){

    }

    public void entry(StateMachineImpl context) {}

    public void exit(StateMachineImpl context) {}

    protected void goBack(StateMachineImpl context) {
        context.goBack();
    }


    // Cambio stato
    protected void goNext(StateMachineImpl context, AbstractState nextState) {
        context.transition(nextState);
        nextState.entry(context);  // chiama il metodo entry del nuovo stato
        nextState.action(context); // esegue l’azione del nuovo stato
    }

    public abstract void action(StateMachineImpl context);

    public void mostraSchermata() {}

    // Metodo per stampare il messaggio di benvenuto
    public void stampaBenvenuto() {}


}

}
