package programming3.chatsys.data;

import java.sql.Timestamp;

public class ChatMessage {

    private String message, user;

    private int id;

    private Timestamp creationTime;

    public ChatMessage(String message, String user, int id, Timestamp creationTime) {
        this.message = message;
        this.user = user;
        this.id = id;
        this.creationTime = creationTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public String getMessage() {
        return message;
    }

    public String format(){
        return id + "," + user + "," + creationTime + "," + message;
    }

}
