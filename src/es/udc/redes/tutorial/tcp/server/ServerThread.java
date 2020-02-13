package es.udc.redes.tutorial.tcp.server;
import java.net.*;
import java.io.*;

/** Thread that processes an echo server connection. */

public class ServerThread extends Thread {
    
private Socket socket;

public ServerThread(Socket s) {
    // Store the socket s
    socket = s;
}

public void run() {
    try {
        // Set the input channel
        BufferedReader sInput = new BufferedReader(new InputStreamReader(
                                                            socket.getInputStream()));
        // Set the output channel
        PrintWriter sOutput = new PrintWriter(socket.getOutputStream(), true);
        // Receive the client message
        String received = sInput.readLine();
        // Send response to the client
        System.out.println("SERVER: Sending " + received);
                sOutput.println(received);
        // Close the streams
         System.err.println("Closing connection with client");
         sOutput.close();
         sInput.close();
         socket.close();
        } catch (SocketTimeoutException e) {
        System.err.println("Nothing received in 300 secs");
        } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
        } finally {
        // Close the socket
        try {
        socket.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
    }
}
