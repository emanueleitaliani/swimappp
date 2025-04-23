package View;

import Bean.CredenzialiBean;
import Bean.Utenteloggatobean;
import Controller.Logincontroller;
import Exceptions.CredenzialisbagliateException;
import Exceptions.UtentenonpresenteException;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;

import java.util.Scanner;

public class LoginCLI extends AbstractState {


Utenteloggatobean utenteloggatobean;

    public void mostraSchermata(StateMachineImpl context) {
        Scanner scanner = new Scanner(System.in);
        Logincontroller logincontroller = new Logincontroller();

        System.out.println("=== LOGIN UTENTE ===");

        System.out.print("Inserisci email: ");
        String email = scanner.nextLine();

        System.out.print("Inserisci password: ");
        String password = scanner.nextLine();

        CredenzialiBean credenzialiBean = new CredenzialiBean();
        credenzialiBean.setEmail(email);
        credenzialiBean.setPassword(password);

        try {
            Utenteloggatobean utente = logincontroller.login(credenzialiBean);
            AbstractState homeCLI;

            if (utente != null) {
                System.out.println("\nLogin effettuato con successo!");
                System.out.println("Benvenuto/a, " + utente.getNome() + " " + utente.getCognome());
                System.out.println("Email: " + utente.getEmail());
                System.out.println("Ruolo: " + (utente.isIstructor() ? "Istruttore" : "Studente"));

                // Cambio stato in base al ruolo
                if (utente.isIstructor()) {
                    homeCLI=new UserCLI(utente);
                } else {
                    homeCLI=new IstructorCLI(utente);
                }
            }
        } catch (UtentenonpresenteException | CredenzialisbagliateException e) {
            System.out.println("Errore durante il login: " + e.getMessage());
        }
    }
}