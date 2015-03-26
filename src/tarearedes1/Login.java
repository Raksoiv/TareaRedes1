package tarearedes1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author soldieriv
 */
public class Login implements HttpHandler{
    @Override
    public void handle(HttpExchange he) throws IOException {
        String response = "Login";
        he.sendResponseHeaders(200, response.length());
        OutputStream out = he.getResponseBody();
        out.write(response.getBytes());
        out.close();
    }
}
