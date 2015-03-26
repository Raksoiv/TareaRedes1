package tarearedes1;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author soldieriv
 */
public class HomePage implements HttpHandler{

    @Override
    public void handle(HttpExchange he) throws IOException {
        String content = new Scanner(new File("HTMLPages/Home.html")).useDelimiter("\\Z").next();
        // byte[] encoded = Files.readAllBytes(Paths.get("HTMLpages/Home.html"));
        // String page = new String(encoded, StandardCharsets.UTF_8);
        // System.out.println(page);
        String request_method = he.getRequestMethod();
        Headers header1 = he.getRequestHeaders();
        InputStream ie = he.getRequestBody();
        Headers header2 = he.getResponseHeaders();
        he.sendResponseHeaders(200, content.length());
        OutputStream os = he.getResponseBody();
        os.write(content.getBytes());
        os.close();
    }
    
}
