package labD;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.InetAddress;
import java.net.Socket;

public class TCPClientC { 
    public static void receive( String serverIP, int serverPort ) { 
        Socket socket = null; 
        try { 
            InetAddress adress = InetAddress.getByName( serverIP ); 
            socket = new Socket( adress , serverPort ); // koppla upp 
            ObjectInputStream input = new ObjectInputStream(  
                                              socket.getInputStream() ); 
            String message; 
            while( true ) { 
                message = input.readUTF(); // här väntar tråden 
                System.out.println( message ); 
            } 
        } catch(IOException e) { 
            System.out.println( e ); 
        }        
        try { 
            socket.close(); // avsluta Socket-objektet 
        } catch( Exception e ) { 
            System.out.println( e ); 
        } 
    } 
     
    public static void main( String[] args ) { 
        TCPClientC.receive( "127.0.0.1", 5555 ); 
    } 
}
