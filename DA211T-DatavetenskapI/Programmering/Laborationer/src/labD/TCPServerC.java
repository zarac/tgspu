package labD;

import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerC { 
     
    public static void send( int port, String[] messages ) { 
        ServerSocket serverSocket = null; 
        Socket socket = null; 
        ObjectOutputStream output; 
        try { 
            serverSocket = new ServerSocket( port ); 
            socket = serverSocket.accept(); 
            output = new ObjectOutputStream( socket.getOutputStream() ); 
            for( int i = 0; i < messages.length; i++ ) { 
                output.writeUTF( messages[ i ] ); 
                output.flush(); 
                Thread.sleep( 3000 ); // paus 3 sekunder 
            } 
        } catch( Exception e1 ) { // IOException eller InterruptedException 
            System.out.println( e1 ); 
        }  
        try { 
            socket.close(); 
            serverSocket.close(); 
        } catch( Exception e ) {} 
    } 
     
    public static void main( String[] args ) { 
        String[] meddelanden = { "Veni, vidi, vici",  
                                 "Jag kom, jag såg, jag segrade", 
        "Alea iacta est", "Tärningen är kastad", 
        "Et tu Brute", "Även du, min käre Brutus" }; 
        TCPServerC.send( 5555, meddelanden ); 
    } 
}
