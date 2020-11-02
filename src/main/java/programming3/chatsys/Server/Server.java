package programming3.chatsys.Server;
import programming3.chatsys.data.Database;
import programming3.chatsys.data.UserInformation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket server=new ServerSocket(10000);
        while(true)
        {
            //accept information of user
            Socket socket=server.accept();
            if (login(socket)){
                //using invoke function
                invoke(socket);
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

    private static void invoke(final Socket socket) throws IOException
    {
        //creat a new thread
        new Thread(new Runnable()
        {
            public void run()
            {
                //creat input Stream
                ObjectInputStream is=null;
                //creat output stream
                ObjectOutputStream os=null;
                try
                {
                    is=new ObjectInputStream(socket.getInputStream());
                    os=new ObjectOutputStream(socket.getOutputStream());
                    //read object
                    Object obj = is.readObject();
                    //turn object to UserInformation
                    UserInformation user=(UserInformation) obj;
                    //out user.name and user.password to terminal
                    System.out.println("user: "+user.getName()+"/"+user.getPassword());
                    //change name and password
                    user.setName(user.getName()+"_new");
                    user.setPassword(user.getPassword()+"_new");
                    //out to client server
                    os.writeObject(user);
                    os.flush();
                }
                catch(IOException|ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    try
                    {

                        is.close();

                        os.close();

                        socket.close();
                    }
                    catch(Exception ex){}
                }
            }
       }).start();

    }
}
