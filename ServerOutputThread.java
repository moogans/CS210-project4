import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Thread;
import java.net.*;
import java.io.*;
public class ServerOutputThread extends Thread{

    public void run(){
	PrintWriter pw;
	try{
	    //Continually loop through all messages
		while(true){
	
			//If queue is not empty then pop message off the queue
				if (!ChatServer.messages.isEmpty()){
					String popMessage = ChatServer.messages.pop();
					
					//Loop through all connections and send popped message to each connection
					for (Socket s: ChatServer.connections){
						try{
							pw = new PrintWriter(s.getOutputStream(), true);
							//System.out.println(popMessage);
							pw.println(popMessage);
						}catch(Exception e){
							System.out.println("ServerOutputThread.java: " + e);
						}
					}
				}			
			else{
			//Sleep for 100 miliseconds if the queue is currently empty
			ServerOutputThread.sleep(100);
			}
			
		}
	}catch(Exception e){
	    System.out.println("ServerOutputThread (run): " + e);
		}
    }
}