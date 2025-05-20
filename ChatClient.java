import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Thread;
import java.net.*;
import java.io.*;
public class ChatClient{
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println(
			       "Usage: java ChatClient <host name> <port number>");
            System.exit(1);
        }
 
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
   
        try{
        //Connect to the server by instantiating a Socket object
        Socket clientSocket = new Socket(hostName, portNumber);
	    //Set the Socket output stream to a PrintWriter object
        OutputStream outputStream = clientSocket.getOutputStream();
        PrintWriter socketOut = new PrintWriter(outputStream, true);
	    //Set the Socket input stream to a Buffered Reader   
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       
        //Read in username from terminal
        System.out.print("Enter a username: ");
        String username = stdIn.readLine();
        socketOut.println(username);
	    //Fire up a ClientReceiver thread !!
        ClientReceiver clientReceiver  = new ClientReceiver(serverIn);
        clientReceiver.start();
      
	    //Chatting below
        //originally was  String userInput = stdIn.readLine()
	    String userInput;
       
	    while (true){
            if ((userInput = stdIn.readLine()) != null){
                socketOut.println(userInput);
                //System.out.println("This is the line: " + userInput);
            }
        }
		    
        
        } catch (Exception e) {
            System.err.println("ChatClient: " + e);
            System.exit(1);
        }
    }


}