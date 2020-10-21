package programming3.chatsys.data;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ChatMessage {

    private String message;

    public ChatMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String format(){
        return null;
    }

    public void parse(String formatted){

    }

    public void save(File filename) throws Exception{

        FileWriter fileWriter = new FileWriter(filename, true);

        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(this.message);
        printWriter.close();
    }

}
