package programming3.chatsys.data;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ChatMessage {

    private String message;

    private int id;

    public ChatMessage(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public String format(){
        return id + "," + message;
    }

    public void parse(String formatted){

    }

    public void save(File filename, String message) throws Exception{

        FileWriter fileWriter = new FileWriter(filename, true);

        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(message);
        printWriter.close();
    }

}
