package labD;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerD { 
    private int port; 
    private String[] messages; 

    public TCPServerD( int port, String[] messages ) { 
        this.port = port; 
        this.messages = messages; 
        Thread connectThread = new Thread( new Connect() ); 
        connectThread.start(); // starta tråd som lyssnar på klienter som vill ansluta sig
    } 

    private class Connect implements Runnable { 
        public void run() { 
            ServerSocket serverSocket = null; 
            Socket socket; 
            Thread clientThread; 
            try { 
                serverSocket = new ServerSocket( port ); 
                while( true ) { 
                    socket = serverSocket.accept(); 
                    // Här ska kommunikationen med klienten startas 
                    clientThread = new Thread( new TalkToClient( socket ) ); 
                    clientThread.start(); 
                } 
            } catch( IOException e1 ) { 
                System.out.println( e1 ); 
            } 
            try { 
                serverSocket.close(); 
            } catch( Exception e ) {} 
        } 
    } 
 
    private class TalkToClient implements Runnable { 
        private Socket socket; 
         
        public TalkToClient( Socket socket ) { 
            this.socket = socket; 
        } 
         
        public void run() { 
            try { 
                ObjectOutputStream oos = new ObjectOutputStream(  
                                                socket.getOutputStream() ); 
                for( int i = 0; i < messages.length; i++ ) { 
                    oos.writeObject( messages[ i ] ); 
                    //oos.writeUTF( messages[ i ] ); 
                    oos.flush(); 
                    Thread.sleep( 1000 ); 
                } 
            } catch(Exception e1 ) { 
                System.out.println( e1 ); 
            } 
            try { 
                socket.close(); 
            } catch( IOException e ) { 
                System.out.println( e ); 
            } 
        } 
    } 

    public static void main( String[] args ) { 
        String[] meddelanden = { "Veni, vidi, vici",  
            "Jag kom, jag såg, jag segrade", 
            "Alea iacta est",  
            "Tärningen är kastad", 
            "Et tu Brute",  
            "Även du, min käre Brutus" }; 
        new TCPServerD( 5555, meddelanden ); 
    } 
} 
