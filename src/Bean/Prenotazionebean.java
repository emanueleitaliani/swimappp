package Bean;

public class Prenotazionebean {
    private int idPrenotazione;
    private String nome;
    private String cognome;
    private String emailIstruttore;
    private String emailUser;
    private String giorno;
    private String info;
    private float prezzo;
    private float hour ;

    public Prenotazionebean(){}

    public Prenotazionebean(Utenteloggatobean istruttore,Integer idPrenotazione,String cognome,String Nome,String emailIstruttore,String emailUser,String day,String info,float prezzo,float hour) {
        this.idPrenotazione = idPrenotazione;
        this.nome= istruttore.getNome();
        this.cognome=istruttore.getCognome();
        this.emailIstruttore=istruttore.getEmail();
        this.emailUser=emailUser;
        this.giorno=giorno;
        this.info=info;
        this.prezzo=prezzo;
        this.hour=hour;
    }
    public int getIdPrenotazione() {
        return idPrenotazione;
    }
    public String getNome() {
      return nome;
    }

    public void setNome(String nome) {
      this.nome = nome;
    }

    public String getCognome(){
      return cognome;
    }

    public void setCognome(String cognome) {
      this.cognome = cognome;
    }


    public String getEmailIstruttore() {
      return emailIstruttore;
    }

    public void setEmailIstruttore(String emailIstruttore) {
      this.emailIstruttore = emailIstruttore;
    }

    public String getEmailUser() {
      return emailUser;
    }

    public void setEmailUser(String emailUser) {
      this.emailUser = emailUser;
    }
    public float getPrezzo() {
      return prezzo;
    }

    public void setPrezzo(float prezzo) {
      this.prezzo = prezzo;
    }

    public String getGiorno() {
      return giorno;
    }

    public void setGiorno(String giorno) {
      this.giorno = giorno;
    }

    public String getInfo() {
      return info;
    }

    public void setInfo(String info) {
      this.info = info;
    }
    public float getHour() {
      return hour;
    }

    public void setHour(float hour) {
      this.hour = hour;
    }

}

