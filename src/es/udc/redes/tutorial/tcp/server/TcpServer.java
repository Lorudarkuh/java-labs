package es.udc.redes.tutorial.tcp.server;

import java.net.*;
import java.io.*;

/**
 * Monothread TCP echo server.
 */
public class TcpServer {

    public static void main(String argv[]) {
        if (argv.length != 1) {
            System.err.println("Format: TcpServer <port>");
            System.exit(-1);
        }
        ServerSocket serverSocket = null;
        try {
            // Create a server socket
            int serverPort = Integer.parseInt(argv[0]);
            serverSocket = new ServerSocket(serverPort);
            // Set a timeout of 300 secs
            serverSocket.setSoTimeout(300000);
            while (true) {
                // Wait for connections
                Socket clientSocket = serverSocket.accept();
                System.err.println("Accepted connection from client");
                // Create a ServerThread object, with the new connection as parameter
                ServerThread st = new ServerThread(clientSocket);
                st.start();
            }
        // Uncomment next catch clause after implementing the logic            
        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
//Close the socket
        try {
        serverSocket.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
                
        }
    }
}
