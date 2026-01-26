package Gui;
import Other.Stampa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Bean.CredenzialiBean;
import Bean.Utenteloggatobean;
import Controller.Logincontroller;
import Exceptions.CredenzialisbagliateException;
import Exceptions.UtentenonpresenteException;

import java.net.URL;
import java.util.logging.Logger;
import java.io.IOException;

public class LoginGui {
    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label credenzialiError;

    @FXML
    private Label credenzialiSbagliate;

    protected Utenteloggatobean utenteloggatobean;

    private static final Logger logger = Logger.getLogger(LoginGui.class.getName());


    @FXML
    void login() {

        String userEmail;
        String userPassword;

        if (!this.emailField.getText().isEmpty() && !this.passwordField.getText().isEmpty()) {
            userEmail = this.emailField.getText().trim();
            userPassword = this.passwordField.getText().trim();
        } else {
            credenzialiError.setText("Campi obbligatori.");
            return;
        }
        Stampa.println("EMAIL INSERITA: [" + userEmail + "]");
        Stampa.println("PASSWORD INSERITA: [" + userPassword + "]");
        try {
            CredenzialiBean credenzialiBean = new CredenzialiBean(userEmail,userPassword);
            credenzialiBean.setEmail(userEmail);
            credenzialiBean.setPassword(userPassword);

            Logincontroller loginController = new Logincontroller();
            this.utenteloggatobean = loginController.login(credenzialiBean);

            // carica la home in base al ruolo
            caricaHome(utenteloggatobean.getRuolo());

        } catch (CredenzialisbagliateException e) {
            credenzialiError.setVisible(false);
            credenzialiSbagliate.setText("❌ Credenziali sbagliate");
        } catch (UtentenonpresenteException u) {
            credenzialiError.setVisible(false);
            credenzialiSbagliate.setText("❌ Utente inesistente");
        }
    }

    public void caricaRegistrazione() {
        try {
            URL fxmlUrl = LoginGui.class.getResource("/Fxml/registrazione.fxml");
            if (fxmlUrl == null) {
                logger.severe("File registrazione.fxml non trovato!");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            loader.setControllerFactory(c -> new RegistrazioneGui(this.utenteloggatobean));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) credenzialiError.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            logger.severe("Errore nel caricamento della registrazione: " + e.getMessage());
        }
    }

    public void caricaHome(boolean isIstructor) {

        try {
            FXMLLoader loader;

            if (isIstructor) {
                loader = new FXMLLoader(LoginGui.class.getResource("/Fxml/homeistruttore.fxml"));
                loader.setControllerFactory(c -> new HomeIstruttoreGui(utenteloggatobean));
            } else {
                loader = new FXMLLoader(LoginGui.class.getResource("/Fxml/homeutente.fxml"));
                loader.setControllerFactory(c -> new HomeUtenteGui(utenteloggatobean));
            }
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) credenzialiError.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            logger.severe("errore in LoginGuiController " + e.getMessage());
        }
    }

}
