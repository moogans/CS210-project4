import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Thread;
import java.net.*;
import java.io.*;
public class ClientReceiver extends Thread{
    BufferedReader in;
    public void run(){
		while(true){
			try{
			//Read in message and print if not null
				String msg = in.readLine();
				if (msg != null){ 
					System.out.println(msg);
				}
				
			//Sleep for 100 miliseconds if message is null
			else{
				Thread.sleep(100);
			}
			
		}catch(Exception e){
			System.out.println("ClientReceiver: " + e);
			System.exit(1);
		}
	//}
	}
	
}
    

    public ClientReceiver(BufferedReader in){
	//takes the input stream from the server socket connected to client instance
	this.in = in;
    }
}
