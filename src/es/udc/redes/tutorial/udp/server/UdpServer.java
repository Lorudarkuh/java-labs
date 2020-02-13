package es.udc.redes.tutorial.udp.server;

import java.net.*;

/**
 * Implements an UDP Echo Server.
 */
public class UdpServer {

    public static void main(String argv[]) {
        if (argv.length != 1) {
            System.err.println("Format: UdpServer <port_number>");
            System.exit(-1);
        }
        try {
            // Create a server socket
            int serverPort = Integer.parseInt(argv[0]);
            DatagramSocket serverSocket = new DatagramSocket(serverPort);           
            // Set max. timeout to 300 secs
            serverSocket.setSoTimeout(300000);
            while (true) {
                // Prepare datagram for reception
                byte[] array = new byte[1024];
                DatagramPacket dgramRec = new DatagramPacket(array, array.length);
                // Receive the message
                serverSocket.receive(dgramRec);
                String message = new String( dgramRec.getData());
                System.out.println("SERVER: Received "
                    + message
                    + " from " + dgramRec.getAddress().toString() + ":"
                    + dgramRec.getPort());
                // Prepare datagram to send responseInetAddress IPAddress = receivePacket.getAddress();
                int port = dgramRec.getPort();
                InetAddress IPAddress = dgramRec.getAddress();
                array = message.getBytes();
                DatagramPacket dgramSent = new DatagramPacket(array, array.length,
                    IPAddress, port);
                // Send response
                serverSocket.send(dgramSent);
            }
          
        // Uncomment next catch clause after implementing the logic
        } catch (SocketTimeoutException e) {
            System.err.println("No requests received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
// Close the socket
        }
    }
}
