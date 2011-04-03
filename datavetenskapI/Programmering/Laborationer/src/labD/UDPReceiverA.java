package labD;

import java.net.*; 
import java.io.*; 
 
public class UDPReceiverA { 
    public static void receive( int port ) { 
        try { 
            DatagramSocket socket = new DatagramSocket( port ); 
            DatagramPacket packet; 
            String message; 
            byte[] data = new byte[ 256 ]; 
            while( true ) { 
                packet = new DatagramPacket( data, data.length ); 
                socket.receive( packet ); 
                message = new String( packet.getData(), 0, packet.getLength() ); 
                System.out.println( message ); // Skriver ut meddelandet 
            } 
        } catch(IOException e) { 
            System.out.println( e ); 
        } 
    } 


    public static void main( String[] args ) { 
        UDPReceiverA.receive( 4444 ); 
    } 
} 
