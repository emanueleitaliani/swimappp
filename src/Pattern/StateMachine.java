package Pattern;

public interface StateMachine {

        /** Avvia la macchina nello stato iniziale */
        void start();

        /** Esegue l'azione dello stato corrente */
        void goNext();

        /** Torna allo stato precedente */
        void goBack();

        /** Effettua la transizione verso un nuovo stato */
        void transition(AbstractState nextState);

        /** Ottiene lo stato corrente */
        AbstractState getState();
}

