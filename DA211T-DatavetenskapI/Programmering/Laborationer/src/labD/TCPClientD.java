package labD;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.InetAddress;
import java.net.Socket;

public class TCPClientD { 
    private String serverIP; 
    private int serverPort; 
     
    public TCPClientD( String serverIP, int serverPort ) { 
        this.serverIP = serverIP; 
        this.serverPort = serverPort; 
        Thread thread = new Thread( new ConnectAndListenToServer() ); 
        thread.start(); 
    } 
     
    private class ConnectAndListenToServer implements Runnable { 
        public void run() { 
            try { 
                Socket socket = new Socket(  
                                     InetAddress.getByName(serverIP),  
                                     serverPort ); 
                ObjectInputStream input = new ObjectInputStream(  
                                               socket.getInputStream() ); 
                String message; 
                while( true ) { 
                    message = (String)input.readObject();
                    //message = input.readUTF(); 
                    System.out.println( message ); 
                } 
            }
            catch(IOException e) { 
                System.out.println( e ); 
            } 
            catch(ClassNotFoundException e) { 
                System.out.println( e ); 
            } 
        } 
    } 
 
    public static void main( String[] args ) { 
        new TCPClientD( "127.0.0.1", 5555 ); 
    } 
}
