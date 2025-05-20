import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Thread;
import java.net.*;
import java.io.*;
public class ServerInputThread extends Thread{
	private Socket clientSocket;
    public void run(){
	try{
	    //Read in a line representing the username
		BufferedReader bf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    //Set the username field
		String username = bf.readLine();
	    //Add a new socket to the static connection list
		//ChatServer.connections.add(clientSocket);
	
		while(true) {
			//Continually read input from the client socket
			String message = bf.readLine();
			//Add that input to the messages list with the username infront "JR: lorum ipsum"
			String input = username + ": " + message;
			//System.out.println("The stored message is: " + input);
			//synchronized(ChatServer.messages){
				if (message !=null){
					ChatServer.messages.add(input);
				}			
		}
		
	}catch(Exception e){
	    System.out.println("ServerInputThread (run): " + e);
	    System.exit(1);
	} 
    }

    public ServerInputThread(Socket clientSocket){
	try{
	    //Set the appropriate fields 
		this.clientSocket = clientSocket;
	}catch(Exception e){
	    System.out.println("ServerInputThread (Constructor)" + e);
	    System.exit(1);
	}
    }
}