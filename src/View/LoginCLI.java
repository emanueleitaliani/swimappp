package View;

import Bean.CredenzialiBean;
import Bean.Utenteloggatobean;
import Controller.Logincontroller;
import Exceptions.CredenzialisbagliateException;
import Exceptions.UtentenonpresenteException;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;
import Other.Stampa;
import java.util.Scanner;

public class LoginCLI extends AbstractState {


Utenteloggatobean utenteloggatobean;

    public void action(StateMachineImpl context) {
        Scanner scanner = new Scanner(System.in);
        Logincontroller logincontroller = new Logincontroller();

        Stampa.println("=== LOGIN UTENTE ===");

        Stampa.println("Inserisci email: ");
        String email = scanner.nextLine();

        Stampa.println("Inserisci password: ");
        String password = scanner.nextLine();

        AbstractState homeCLI=null;
        try {
            CredenzialiBean credenzialiBean = new CredenzialiBean();
            credenzialiBean.setEmail(email);
            credenzialiBean.setPassword(password);

            Utenteloggatobean utente = logincontroller.login(credenzialiBean);


            if (utente != null) {
                Stampa.println("\nLogin effettuato con successo!");
                Stampa.println("Benvenuto/a, " + utente.getNome() + " " + utente.getCognome());
                Stampa.println("Email: " + utente.getEmail());
                Stampa.println("Ruolo: " + (utente.isIstructor() ? "Istruttore" : "Studente"));

                // Cambio stato in base al ruolo
                if (utente.isIstructor()) {
                    homeCLI=new UserCLI(utente);
                } else {
                    homeCLI=new IstructorCLI(utente);
                }
            }
            goNext(context,homeCLI);
        } catch (UtentenonpresenteException | CredenzialisbagliateException e) {
            Stampa.println("Errore durante il login: " + e.getMessage());
        }
    }
}