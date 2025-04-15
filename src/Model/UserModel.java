package Model;
import Model.CredenzialiModel;
public class UserModel extends UtenteloggatoModel {
   private int age;
   private String info;
   private String level;

    public UserModel(CredenzialiModel credenziali, String Nome, String Cognome, boolean isIstructor,int age, String info, String level) {
        super(credenziali,Nome,Cognome,isIstructor);
        this.age = age;
        this.info = info;
        this.level = level;
    }
    public int getAge() {
       return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
}
