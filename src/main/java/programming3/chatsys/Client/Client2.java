package programming3.chatsys.Client;

import programming3.chatsys.data.UserInformation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Scanner;

public class Client2 {

    private static String lastChat = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //create Client server socket
        //connect server and login
        Socket socket = new Socket("localhost",10000);
        UserInformation user = login(socket);

        getThread(socket);
        sendThread(socket,user);

    }

    private static void sendMessage(Socket socket, UserInformation user) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String send = message + "," + user.getName() + "," + time.toString();

        // record the message which will be sent
        lastChat = time.toString() + ", " +
                user.getName() + ": " +
                message;

        DataOutputStream outChat = null;
        outChat = new DataOutputStream(socket.getOutputStream());
        outChat.writeUTF(send);
        outChat.flush();

    }

    private static void sendThread(final Socket socket, final UserInformation user) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // send message to server
                try {
                    do {
                        sendMessage(socket,user);
                    }while (true);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private static void getMessage(Socket socket) throws IOException {
        DataInputStream inputMessageSize = null;
        inputMessageSize = new DataInputStream(socket.getInputStream());
        int size = 0;
        size = inputMessageSize.readInt();
        if (size == -1){// get new message which have not seen
            DataInputStream inputChat = new DataInputStream(socket.getInputStream());
            String m = inputChat.readUTF();
            if (!m.equals(lastChat)){
                //error
                System.out.println(m);
                lastChat = m;
            }
        }
        else {//get all chat message from server from database
            for (int j = 0; j < size; j++) {
                DataInputStream inputMessage = null;
                inputMessage = new DataInputStream(socket.getInputStream());
                System.out.println(inputMessage.readUTF());
            }
        }
    }

    private static void getThread(final Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // get chat message from server
                try {
                    do {
                        getMessage(socket);
                    }while (true);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //inputMessage.close();


            }
        }).start();
    }

    private static UserInformation login(Socket socket) throws IOException {
        DataInputStream inputAskID = new DataInputStream(socket.getInputStream());
        System.out.println(inputAskID.readUTF());

        Scanner scannerID = new Scanner(System.in);
        DataOutputStream outputID = new DataOutputStream(socket.getOutputStream());
        int id = Integer.parseInt(scannerID.nextLine());
        outputID.writeUTF(String.valueOf(id));

        DataInputStream inputAskPassword = new DataInputStream(socket.getInputStream());
        System.out.println(inputAskID.readUTF());

        Scanner scannerPassword = new Scanner(System.in);
        DataOutputStream outputPassword = new DataOutputStream(socket.getOutputStream());
        String password = scannerPassword.nextLine();
        outputPassword.writeUTF(password);

        DataInputStream inputName = new DataInputStream(socket.getInputStream());
        String name = inputName.readUTF();
        UserInformation user = new UserInformation(id, name, password);
        return user;
    }
}
