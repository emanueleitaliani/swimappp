package Bean;

public class Utenteloggatobean{
    private CredenzialiBean credenzialiBean;
    private String Nome;
    private String Cognome;
    private String Email;
    protected boolean isIstructor;

    public Utenteloggatobean(CredenzialiBean credenzialiBean,String Email, String Nome, String Cognome, boolean ruolo){
        this.credenzialiBean=credenzialiBean;
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.Email = Email;
        this.isIstructor = ruolo;
    }

    public Utenteloggatobean(CredenzialiBean credenzialiBean,String Email, String Nome, String Cognome){
        this.credenzialiBean=credenzialiBean;
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.Email = Email;
    }
    public CredenzialiBean getCredenziali(){
        return credenzialiBean;
    }
    public void setCredenziali(CredenzialiBean credenzialiBean){
        this.credenzialiBean = credenzialiBean;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String Cognome) {
        this.Cognome = Cognome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void setRuolo(boolean role) {
        this.isIstructor = role;
    }
    public boolean getRuolo() {
        return this.isIstructor;
    }

    public boolean isIstructor() {
        return this.isIstructor;
    }


}
