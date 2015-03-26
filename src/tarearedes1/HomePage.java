/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarearedes1;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author soldieriv
 */
public class HomePage implements HttpHandler{

    @Override
    public void handle(HttpExchange he) throws IOException {
        String request_method = he.getRequestMethod();
        Headers header1 = he.getRequestHeaders();
        InputStream ie = he.getRequestBody();
        Headers header2 = he.getResponseHeaders();
        he.sendResponseHeaders(200, 6);
        OutputStream os = he.getResponseBody();
        os.write(200);
        os.close();
    }
    
}
