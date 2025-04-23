package Pattern;
import Bean.Utenteloggatobean;
import java.util.ArrayDeque;
import java.util.Deque;
public class StateMachineImpl implements StateMachine{
    private Deque<AbstractState> cronologiaStati;
    private AbstractState currentState;
    private Utenteloggatobean utenteloggatobean;

    public StateMachineImpl(){
        this.cronologiaStati = new ArrayDeque<>();
        this.currentState = new InitialState();
    }

    public void start(){
        this.currentState = new InitialState();
        goNext();
    }
    public void goNext(){
        if(currentState!=null){
            this.currentState.action(this);
        }

    }
    public void goBack(){
       if(currentState!=null){
           this.currentState.exit(this);
           this.currentState=cronologiaStati.pop();
           this.currentState.entry(this);
       }
    }
    public Utenteloggatobean getUtenteloggatobean() {
        return utenteloggatobean;
    }
    public void setUtenteloggatobean(Utenteloggatobean utenteloggatobean) {
        this.utenteloggatobean = utenteloggatobean;
    }


    public AbstractState getState(){
        return currentState;
    }

    public void setState(){
        this.currentState = null;
    }

}