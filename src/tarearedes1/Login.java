package tarearedes1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author soldieriv
 */
public class Login implements HttpHandler{
    @Override
    public void handle(HttpExchange he) throws IOException {
    	//Obteniendo la pagina a enviar
    	String content = new Scanner(new File("HTMLPages/Login.html")).useDelimiter("\\Z").next();
    	//Obteniendo el tipo de Request (Post o Get)
    	String request_method = he.getRequestMethod();
    	//Enviando el header 200 OK
        he.sendResponseHeaders(200, content.length());
        //Mostrando la pagina al cliente
        OutputStream out = he.getResponseBody();
        out.write(content.getBytes());
        out.close();
    }
}
