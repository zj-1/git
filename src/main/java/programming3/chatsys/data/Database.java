package programming3.chatsys.data;

import java.io.*;
import java.util.ArrayList;

public class Database {

    private final ArrayList<String> chatMessages = new ArrayList<String>();

    public ArrayList<String> readMessages() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("messages.csv"));
        String str;
        while ((str = in.readLine()) != null) {
            chatMessages.add(str);
        }
        in.close();
        return chatMessages;
    }

    public void addMessages(int id, String messages) throws Exception {
        ChatMessage chatMessage = new ChatMessage(messages, id);

        chatMessage.save(new File("messages.csv"), chatMessage.format());

    }
}
