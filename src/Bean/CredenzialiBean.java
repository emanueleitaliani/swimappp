package Bean;

public class CredenzialiBean {
    //classe che gestisce le credenziali per il login
    private String Email;
    private String Password;

    public void setEmail(String Email) {

        this.Email = Email;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }

    public String getEmail(){
      return  Email;
    }
    public String getPassword(){
      return Password;
    }
}
