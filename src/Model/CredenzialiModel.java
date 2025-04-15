package Model;

public class CredenzialiModel {
    private String email;
    private String password;
    private boolean ruolo;

    public CredenzialiModel(String email, String password, boolean ruolo){
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }
    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;

    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isRuolo() {
        return ruolo;

    }
    public void setRuolo(boolean ruolo) {
        this.ruolo = ruolo;
    }
}
