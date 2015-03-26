package tarearedes1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Raksoiv
 */
public class TareaRedes1 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new HomePage());
        server.createContext("/home_old", new HomeOld());
        server.createContext("/secret", new Secret());
        server.createContext("/login", new Login());
        server.setExecutor(null);
        server.start();
    }
}
