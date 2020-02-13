package es.udc.redes.tutorial.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//java es.udc.redes.tutorial.copy.Copy <fichero origen> <fichero destino>
public class Copy {

    public static void main(String[] argv) throws IOException {
        
        if (argv.length != 2) {
            System.err.println("Format: UdpClient <server_address> <port_number> <message>");
            System.exit(-1);
        }
        
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(argv[0]);
            out = new FileOutputStream(argv[1]);
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
    
}
