import Other.Connect;
import Other.Stampa;
import Pattern.StateMachineImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            new Main().start();  // Avvia manualmente il programma
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        // Inizializza i mapper

        testDatabaseConnection();
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            try {
                mostraMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consuma newline
                switch (choice) {
                    case 1:
                        Stampa.println("⚠️ Interfaccia grafica non disponibile al momento.");
                        break;
                    case 2:
                        interfaceCLI();
                        validInput = true;
                        break;
                    default:
                        Stampa.println("Scelta non valida");
                }
            } catch (Exception e) {
                Stampa.errorPrint("Errore: " + e.getMessage());
                scanner.nextLine();  // Pulisce input in caso di errore
            }
        }
    }


    public  void interfaceCLI(){
        StateMachineImpl context= new StateMachineImpl();
        while(context.getState()!=null) {
            context.goNext();

        }
        Stampa.println("Arrivederci");
    }
    public void mostraMenu() {
        Stampa.println(" ");
        Stampa.println("-------------- Swimapp --------------");
        Stampa.println("Scegli l'interfaccia da utilizzare:");
        Stampa.println("2. Interfaccia a riga di comando");

    }
    private void testDatabaseConnection() {
        try {
            Connection conn = Connect.getInstance().getDBConnection();
            if (conn != null && !conn.isClosed()) {
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT 1")) {
                    if (rs.next()) {
                        Stampa.println("✅ Connessione al database verificata con successo.");
                    }
                }
            } else {
                Stampa.errorPrint("❌ Connessione al database non disponibile.");
            }
        } catch (Exception e) {
            Stampa.errorPrint("❌ Errore durante il test di connessione: " + e.getMessage());
        }
    }


}
