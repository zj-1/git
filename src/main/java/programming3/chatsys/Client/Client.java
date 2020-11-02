package programming3.chatsys.Client;
import programming3.chatsys.data.UserInformation;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        for(int i=0;i<100;i++)
        {
            //create Client server socket
            Socket socket=null;
            //creat output and input stream
            ObjectOutputStream os=null;
            ObjectInputStream is=null;

            //connect server
            socket=new Socket("localhost",10000);
            UserInformation user = login(socket);
            //get information of output stream
            os=new ObjectOutputStream(socket.getOutputStream());
            //make User object and write into output stream
            os.writeObject(user);
            os.flush();
            //get information of input stream
            is=new ObjectInputStream(socket.getInputStream());
            //read information
            Object obj=is.readObject();
            //turn obj into user object data
            if(obj!=null)
            {
                user=(UserInformation) obj;
                System.out.println("user: "+user.getName()+"/"+user.getPassword());
            }




            is.close();

            os.close();

            socket.close();


        }

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
