import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String [] args){

        try{

            ServerSocket server = new ServerSocket(2000);

            System.out.println("Server is running at port: " + server.getLocalPort());

            Socket socket = server.accept(); // Wait for the client

            System.out.println("Client connected.");

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));     
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String message = "";

            while(!message.equals("!stop")){

                try{
                    message = in.readUTF();

                    if(message != null){
                        out.writeUTF("Server: " + message);
                    }

                }catch(Exception e){
                    continue;
                }
    
            }

            in.close();
            out.close();
            socket.close();
            server.close();

            System.out.println("Stopping server...");
            System.out.println("Exited");

        }catch(IOException ie){
            System.out.println(ie);
        }
    }
}
