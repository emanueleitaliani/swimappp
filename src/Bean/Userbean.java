package Bean;

public class Userbean extends Utenteloggatobean {
    private int age;
    private String info;
    private String level;

    public Userbean(private int age,private String info,private String level){
       this.age = age;
       this.info = info;
       this.level=level;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age){
        this.age =age;
    }
    public String getInfo(){
        return info;
    }
    public void setInfo(String info){
        this.info=info;
    }
    public String getLevel(){
        return level;
    }
    public void SetLevel(String level){
        this.level=level;
    }
}
