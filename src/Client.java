import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        try{

            Socket socket = new Socket("127.0.0.1", 2000); //Connect to the server

            System.out.println("Connected!");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); //Output stream for sending a data to the server.

            BufferedInputStream bIn = new BufferedInputStream(socket.getInputStream());
            DataInputStream in = new DataInputStream(bIn);

            String input = "";

            while(!input.equals("!stop")){

                input = sc.nextLine();

                if(input != null){
                    out.writeUTF(input);
                }

                String message = in.readUTF();

                System.out.println(message);

            }

            sc.close();
            in.close();
            out.close();
            socket.close();

            System.out.println("Exited");

        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
}
