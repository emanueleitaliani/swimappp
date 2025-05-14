package Controller;

import Dao.UserDao;
import Bean.Utenteloggatobean;
import Bean.CredenzialiBean;
import Model.UtenteloggatoModel;
import Model.CredenzialiModel;
import Exceptions.CredenzialisbagliateException;
import Exceptions.UtentenonpresenteException;
import Other.FactoryDao;
import Other.Stampa;

public class Logincontroller {
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private boolean ruolo;

    public Utenteloggatobean login(CredenzialiBean credenzialiBean)throws CredenzialisbagliateException,UtentenonpresenteException{
        CredenzialiModel credenzialiModel= new CredenzialiModel(email,password);

        Utenteloggatobean utenteloggatobean = new Utenteloggatobean(credenzialiBean,nome,cognome,email,ruolo);


        try{
            credenzialiModel.setEmail(credenzialiBean.getEmail());
            credenzialiModel.setPassword(credenzialiBean.getPassword());

            // collegarsi al Dao per ottenere gli utenti

            UserDao userDAO = FactoryDao.getUserDAO();

            UtenteloggatoModel utenteloggatoModel = userDAO.loginMethod(credenzialiModel);

            if (utenteloggatoModel != null && utenteloggatoModel.getCredenziali() != null) {
                utenteloggatobean.setNome(utenteloggatoModel.getNome());
                utenteloggatobean.setCognome(utenteloggatoModel.getCognome());
                utenteloggatobean.setEmail(utenteloggatoModel.getCredenziali().getEmail());
                utenteloggatobean.setRuolo(utenteloggatoModel.isIstructor());
                return utenteloggatobean;
            }else {
                Stampa.errorPrint("❌ Credenziali mancanti o errate");
                return null;
            }
            // prende dalla Dao le credenziali dell'utente


        } catch (UtentenonpresenteException un) {
           return null;
        } catch (CredenzialisbagliateException cl){
            return null;
        }

    }
}
