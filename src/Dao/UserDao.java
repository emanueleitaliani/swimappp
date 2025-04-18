package Dao;
import Exceptions.EmailgiainusoException;
import Exceptions.UtentenonpresenteException;
import Exceptions.CredenzialisbagliateException;
import Model.UtenteloggatoModel;
import Model.CredenzialiModel;

// uso interfaccia perchè devo solo chiamare i metodi
public interface UserDao {
  public UtenteloggatoModel login(CredenzialiModel credenzialiModel)throws UtentenonpresenteException,CredenzialisbagliateException;
}

