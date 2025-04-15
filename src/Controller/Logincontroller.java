package Controller;
import Dao.UserDao;
import Bean.Utenteloggatobean;
import Bean.CredenzialiBean;
import Model.UtenteloggatoModel;
import Model.CredenzialiModel;
public class Logincontroller {
    private String nome;
    private String cognome;

    public Utenteloggatobean login(CredenzialiBean){
        CredenzialiModel credenzialiModel= new CredenzialiModel();
        Utenteloggatobean utenteloggatobean = new Utenteloggatobean(nome,cognome,Credenziali);


        try{
            credenzialiModel.setEmail(CredenzialiBean.getEmail());
            credenzialiModel.setPassword(CredenzialiBean.getPassword());

            UserDao userDao = new UserDao();

            UtenteloggatoModel utenteloggatoModel = userDao.login(credenzialiModel);


            utenteloggatobean.setNome(utenteloggatoModel.getNome());
            utenteloggatobean.setCognome(utenteloggatoModel.getCognome());
            utenteloggatobean.setEmail(utenteloggatoModel.getCredenziali());
            utenteloggatobean.setRuolo(utenteloggatoModel.isIstructor());

            // prende dalla Dao le credenziali dell'utente

        }

    }
}
