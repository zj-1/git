package programming3.chatsys.data;

import java.sql.Timestamp;

public class ChatMessage {

    private String message, user;

    private Timestamp creationTime;

    public ChatMessage(String message, String user, Timestamp creationTime) {
        this.message = message;
        this.user = user;
        this.creationTime = creationTime;
    }

    public ChatMessage(String message){
        String[] chatData = message.split(",");
        Timestamp time = Timestamp.valueOf(chatData[2]);
        this.message = chatData[0];
        this.user = chatData[1];
        this.creationTime = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getUser() {
        return user;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public String getMessage() {
        return message;
    }

    public String format(){
        return user + "," + creationTime + "," + message;
    }

}
