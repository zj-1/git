package programming3.chatsys.data;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Database {

    private ArrayList<ChatMessage> chatMessages;
    private ArrayList<UserInformation> users;

    public ArrayList<ChatMessage> readMessages() throws IOException {
        chatMessages = new ArrayList<>();
        Timestamp time;
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/messages.csv"));
        String str;
        String[] chatData;

        while ((str = in.readLine()) != null) {
            chatData = str.split(",");
            time = Timestamp.valueOf(chatData[2]);
            ChatMessage chatMessage = new ChatMessage(chatData[3],chatData[1],Integer.parseInt(chatData[0]),time);
            chatMessages.add(chatMessage);
        }

        in.close();
        return chatMessages;
    }

    public void addMessages(ChatMessage chatMessage) throws Exception {

        FileWriter fileWriter = new FileWriter(new File("src/main/resources/messages.csv"), true);

        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(chatMessage.format());
        printWriter.close();
    }

    public ArrayList<UserInformation> readUsers() throws IOException {
        users = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/user.csv"));
        String str;
        String[] userData;
        while ((str = in.readLine()) != null) {
            userData = str.split(",");
            UserInformation userInformation = new UserInformation(Integer.parseInt(userData[0]),userData[1],userData[2]);
            users.add(userInformation);
        }

        in.close();
        return users;
    }

    public void addUser(UserInformation userInformation) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("src/main/resources/user.csv"), true);

        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(userInformation.format());
        printWriter.close();
    }
}
