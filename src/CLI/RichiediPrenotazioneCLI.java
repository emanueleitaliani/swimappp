package CLI;

import Bean.LezioneBean;
import Controller.Prenotazionecontroller;
import Exceptions.UtentenonpresenteException;
import Other.Stampa;
import Pattern.AbstractState;
import Pattern.StateMachineImpl;
import java.util.Scanner;
import Bean.Utenteloggatobean;
import Bean.Prenotazionebean;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.SQLException;
public class RichiediPrenotazioneCLI extends AbstractState {
    private float prezzo;
    private String giorno;
    private String info;
    private float ora;
    Utenteloggatobean utente;
    private LezioneBean lezioneSelezionata;

    public RichiediPrenotazioneCLI(Utenteloggatobean utente,LezioneBean lezioneSelezionata){
        this.utente = utente;
        this.lezioneSelezionata = lezioneSelezionata;
    }
    @Override
    public void action(StateMachineImpl context) {
        Scanner scanner = new Scanner(System.in);
        int scelta=-1;

        System.out.println("DEBUG: action() avviato");
        // Stampa le opzioni una volta all’inizio

        while (scelta!=0) {
            mostraSchermata();
            try {
                Stampa.print("Scegli un'opzione: ");
                String input = scanner.nextLine();
                scelta = Integer.parseInt(input);

                System.out.println("DEBUG: input letto -> " + input);

                switch (scelta){

                    case 1:
                        Stampa.println("Hai scelto di prenotare la Lezione");
                        try {
                            Inseriscivalori(context, scanner);
                        } catch (SQLException e) {
                            Stampa.println("❌ Errore durante la prenotazione: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("DEBUG: caso 0 scelto, ritorno");
                        goBack(context);
                        return;
                    default:
                        Stampa.println("❌ Scelta non valida.");
                        break;
                }
            } catch (NumberFormatException e) {
                Stampa.println("❌ Input non valido. Inserisci un numero intero.");
            }
        }

        // Se necessario tornare alla schermata iniziale
        Stampa.println("DEBUG: uscita dal ciclo while");
        goBack(context);
    }





    public void Inseriscivalori(StateMachineImpl context, Scanner scanner) throws SQLException{
        Prenotazionebean prenotazionebean=new Prenotazionebean();
        int idRandom = ThreadLocalRandom.current().nextInt(0, 100); //
        prenotazionebean.setIdPrenotazione(idRandom);

        Stampa.println("📅 Inserisci il giorno della lezione ");
        String giorno = lezioneSelezionata.getGiorni();
        prenotazionebean.setGiorno(giorno);

        try {
            Stampa.print("🕒 Inserisci l'orario (es: 14.30): ");
            float ora = Float.parseFloat(scanner.nextLine());
            prenotazionebean.setHour(ora);


            String info = lezioneSelezionata.getNoteAggiuntive();
            prenotazionebean.setInfo(info);


            float prezzo= lezioneSelezionata.getTariffa();
            prenotazionebean.setPrezzo(prezzo);


            String nomeIstruttore = lezioneSelezionata.getNomeIstruttore();
            prenotazionebean.setNome(nomeIstruttore);

            String cognomeIstruttore = lezioneSelezionata.getCognomeIstruttore();
            prenotazionebean.setCognome(cognomeIstruttore);


            String emailIstruttore = lezioneSelezionata.getEmailIstruttore();
            prenotazionebean.setEmailIstruttore(emailIstruttore);

            Prenotazionecontroller prenotazionecontroller = new Prenotazionecontroller();
            try {
                prenotazionecontroller.controllaEmail(nomeIstruttore, cognomeIstruttore, emailIstruttore);
            } catch (UtentenonpresenteException e) {
                Stampa.println("❌ Errore: " + e.getMessage());
                return; // o ripeti l'inserimento
            }




            prenotazionebean.setEmailIstruttore(emailIstruttore);

            prenotazionebean.setEmailUser(utente.getCredenziali().getEmail());


            Stampa.println("\n✅ Richiesta di prenotazione completata con successo!");
            Stampa.println("📅 Giorno: " + giorno);
            Stampa.println("🕒 Ora: " + ora);
            Stampa.println("💰 Prezzo: " + prezzo + "€");
            Stampa.println("👤 Istruttore: " + nomeIstruttore + " " + cognomeIstruttore);
            Stampa.println("📧 Email: " + emailIstruttore);
            Stampa.println("📝 Info: " + info);
            try {
                prenotazionecontroller.richiediprenotazione(prenotazionebean);
            } catch (SQLException e) {
                Stampa.println("❌ Errore durante la prenotazione: ");
                return;  // opzionale
            }




        }catch (NumberFormatException e) {
            Stampa.println("❌ Errore nell'inserimento di un valore numerico.");
        }

    }

    @Override
    public void entry(StateMachineImpl context){
        stampaBenvenuto();
        action(context);
    }

    @Override
    public void exit(StateMachineImpl context){
        Stampa.println("riportato alla home");
    }


    @Override
    public void stampaBenvenuto() {
        Stampa.println("📚 --- Benvenuto in prenota lezione di nuoto ---");
        Stampa.println("Ciao " + this.utente.getNome() + ", scegli un'opzione:");
    }


    @Override
    public void mostraSchermata() {
        Stampa.println("1. Prenota lezione");
        Stampa.println("0. Torna indietro");
        Stampa.println("opzione scelta");

    }




}
