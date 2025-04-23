package Controller;

import Dao.UserDao;
import Bean.Utenteloggatobean;
import Bean.CredenzialiBean;
import Model.UtenteloggatoModel;
import Model.CredenzialiModel;
import Exceptions.CredenzialisbagliateException;
import Exceptions.UtentenonpresenteException;
import Other.FactoryDao;

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


            utenteloggatobean.setNome(utenteloggatoModel.getNome());
            utenteloggatobean.setCognome(utenteloggatoModel.getCognome());
            utenteloggatobean.setEmail(utenteloggatoModel.getCredenziali().getEmail());
            utenteloggatobean.setRuolo(utenteloggatoModel.isIstructor());

            // prende dalla Dao le credenziali dell'utente
            return utenteloggatobean;

        } catch (UtentenonpresenteException un) {
           return null;
        } catch (CredenzialisbagliateException cl){
            return null;
        }

    }
}
