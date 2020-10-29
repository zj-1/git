package programming3.chatsys.data;

public class UserInformation {
    private int id;
    private String name, password;

    public UserInformation() {
    }

    public UserInformation(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String format(){
        return id + "," + name + "," + password;
    }
}
