package programming3.chatsys.Server;
import programming3.chatsys.data.ChatMessage;
import programming3.chatsys.data.Database;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

    private static String lastMessage;

    public static void main(String[] args) throws IOException {

        ServerSocket server=new ServerSocket(10000);


            //accept information of user
        while (true) {

            Socket socket = server.accept();
            if (login(socket)) {
                sendAllChatMessage(socket);
                sendThread(socket);
                getThread(socket);

            }
        }
    }

    private static boolean login(final Socket socket) throws IOException {
        Database database = new Database();

        DataOutputStream outputAskID= new DataOutputStream(socket.getOutputStream());
        outputAskID.writeUTF("User ID: ");

        DataInputStream inputID = new DataInputStream(socket.getInputStream());
        int id = Integer.parseInt(inputID.readUTF());

        DataOutputStream outputAskPassword = new DataOutputStream(socket.getOutputStream());
        outputAskPassword.writeUTF("Password: ");

        DataInputStream inputPassword = new DataInputStream(socket.getInputStream());
        String password = inputPassword.readUTF();

        DataOutputStream outputName = new DataOutputStream(socket.getOutputStream());
        outputName.writeUTF(database.getUserByID(database.readUsers(), id).getName());

        return database.findUser(database.readUsers(), id, password);

    }

    private static void sendAllChatMessage(Socket socket) throws IOException {
        DataOutputStream outMessageSize = new DataOutputStream(socket.getOutputStream());
        Database database = new Database();
        ArrayList<ChatMessage> message = database.readMessages();
        outMessageSize.writeInt(message.size());
        for (ChatMessage chatMessage : message) {
            DataOutputStream outMessage = new DataOutputStream(socket.getOutputStream());
            String m = chatMessage.getCreationTime().toString() + ", "
                    + chatMessage.getUser() + ": "
                    + chatMessage.getMessage();
            outMessage.writeUTF(m);
            outMessage.flush();
        }

        //after send all history message, init lastMessage
        ChatMessage chatMessage = message.get(message.size() - 1);
        lastMessage = chatMessage.getCreationTime().toString() + ", "
                + chatMessage.getUser() + ": "
                + chatMessage.getMessage();
        outMessageSize.flush();
        //outMessageSize.close();
    }

    private static String getChatMessage(Socket socket) throws Exception {
        // save message from client
//                    ObjectInputStream inputChat = new ObjectInputStream(socket.getInputStream());
//                    Object obj = inputChat.readObject();
//                    ChatMessage chatMessage = (ChatMessage)obj;
        DataInputStream inputMessage = new DataInputStream(socket.getInputStream());
        String message = inputMessage.readUTF();

        System.out.println(message);

        ChatMessage chatMessage = new ChatMessage(message);
        Database database = new Database();
        database.addMessages(chatMessage);

        return chatMessage.getCreationTime().toString() + ", "
                + chatMessage.getUser() + ": "
                + chatMessage.getMessage();
        //inputMessage.close();
    }

    private static void sendChat(Socket socket) throws IOException {
        DataOutputStream outSize = new DataOutputStream(socket.getOutputStream());
        outSize.writeInt(-1);

        DataOutputStream outChat = new DataOutputStream(socket.getOutputStream());
        outChat.writeUTF(lastMessage);
        outChat.flush();
    }

    private static void sendThread(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // send chat message to client
                try {
                    do {
                        //sendAllChatMessage(socket);
                        sendChat(socket);

                    }while (true);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void getThread(final Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // get chat message from client and add into database
                try {
                    do {
                        lastMessage = getChatMessage(socket);
                        //sendAllChatMessage(socket);
                    }while (true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    private static void invoke(final Socket socket) {
//        //creat a new thread
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//       }).start();
//
//        new Thread(new Runnable()
//        {
//
//            @Override
//            public void run() {
//
//                try {
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
}
