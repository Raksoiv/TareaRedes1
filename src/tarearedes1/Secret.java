/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarearedes1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author soldieriv
 */
public class Secret implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        String response = "Secret";
        he.sendResponseHeaders(403, response.length());
        OutputStream out = he.getResponseBody();
        out.write(response.getBytes());
        out.close();
    }
}