package programming3.chatsys.Client;
import programming3.chatsys.data.UserInformation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        for(int i=0;i<100;i++)
        {
            //create Client server socket
            Socket socket=null;
            //creat output and input stream
            ObjectOutputStream os=null;
            ObjectInputStream is=null;
            try
            {
                //connect server
                socket=new Socket("localhost",10000);
                //get information of output stream
                os=new ObjectOutputStream(socket.getOutputStream());
                //make User object and write into output stream
                UserInformation user=new UserInformation(i, "user_"+i,"password_"+i);
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
            }
            catch(IOException | ClassNotFoundException ex)
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
                catch(Exception ex) {}
            }
        }

    }
}
