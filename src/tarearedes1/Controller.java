package tarearedes1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller implements Runnable{
	private boolean available;
	private final ServerSocket server;
	private final int name;
	private	int bodyLength;
	private boolean login;

	Controller(ServerSocket server, int name){
		this.available = true;
		this.server = server;
		this.name = name;
		this.bodyLength = -1;
		this.login = false;
	}

        @Override
	public synchronized void run(){
		try{
			while(true){
				//Esperando conexiones
				Socket remote = server.accept();

		    //Get the headers and the body of the connection
		    BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));

		    //Reading the Header
		    boolean flag = false;
		    boolean flag2 = false;
		    String mainHeader[] = in.readLine().split(" ");
				String headerLine = ".";
				while(!headerLine.equals("")){
					headerLine = in.readLine();
					// System.out.println(headerLine);
					String parts[] = headerLine.split(": ");
					if(parts[0].equals("Content-Length")){
						this.bodyLength = Integer.parseInt(parts[1]);
						flag = true;
					}
					else if(parts[0].equals("Cookie") && parts[1].equals("login=true")){
						flag2 = true;
						this.login = true;
					}
				}

				if(!flag){
					this.bodyLength = -1;
				}

				if(!flag2){
					this.login = false;
				}

				// System.out.println(remote.getRemoteSocketAddress().toString()); // get IP
				// System.out.println(this.name);

				//Get the output stream
				PrintWriter out = new PrintWriter(remote.getOutputStream());

				//Verificando Get or POST
				switch (mainHeader[0]) {
					case "GET":
						switch (mainHeader[1]) {
							//HomePage
							case "/":
								homePage(out);
								break;
							//HomeOld
							case "/home_old":
								homeOld(out);
								break;
							//Login
							case "/login":
								login(out);
								break;
							//Secret
							case "/secret":
								if(this.login){
									secret(out);
								}
								else{
									forbidden(out);
								}
								break;
						}
						break;
					case "POST":
						//Login Post
						if(mainHeader[1].equals("/login")){
							loginPost(out, in);
						}
						break;
				}

		    //Send
		    out.flush();

		    //Closing the conection
				remote.close();

				//Reactivate the thread
				this.available = true;

				//Sleep the thread
				// Thread.sleep(5000);
				// System.out.println("Ending the thread");
			}
		}
		catch(IOException | NumberFormatException e){
			System.out.println(e.getMessage());
		}
	}

	public boolean isAvailable(){
		return this.available;
	}

	public void homePage(PrintWriter out){
		//Sending the Headers
    out.println("HTTP/1.1 200 OK");
    out.println("Content-Type: text/html");
    out.println("");
    try{
			for (String line: Files.readAllLines(Paths.get("HTMLPages/Home.html"))){
				out.println(line);
			}
		}
		catch(Exception e){
			System.out.println("File Error: " + e.getMessage());
		}
	}

	public void homeOld(PrintWriter out){
		//Sending the Headers
    out.println("HTTP/1.1 301 Moved Permanently");
    out.println("Content-Type: text/html");
    out.println("");
    try{
    	for(String line: Files.readAllLines(Paths.get("HTMLPages/Home_old.html"))){
    		out.println(line);
    	}
    }
    catch(Exception e){
			System.out.println("File Error: " + e.getMessage());
		}
	}

	public void login(PrintWriter out){
		//Sending the Headers
    out.println("HTTP/1.1 200 OK");
    out.println("Content-Type: text/html");
    out.println("");
    try{
    	for(String line: Files.readAllLines(Paths.get("HTMLPages/Login.html"))){
    		out.println(line);
    	}
    }
    catch(Exception e){
			System.out.println("File Error: " + e.getMessage());
		}
	}

	public void loginPost(PrintWriter out, BufferedReader in){
		//Sending the Headers
		out.println("HTTP/1.1 200 OK");
		out.println("Content-Type: text/html");
		String header = ".";
		try{
			char[] content = new char[this.bodyLength];
			in.read(content);
			String data[] = (new String(content)).split("&");
			if(data[0].equals("login=root") && data[1].equals("password=laboratorio1")){
				out.println("Set-Cookie: login=true");
				out.println("");
			}
		}
		catch(Exception e){
			System.out.println("Error Header: " + e.getMessage());
		}
	}

	public void secret(PrintWriter out){
		out.println("HTTP/1.1 200 OK");
		out.println("Content-Type: text/html");
		out.println("");
	}

	public void forbidden(PrintWriter out){
		//Sending the Headers
    out.println("HTTP/1.1 403 Forbidden");
    out.println("Content-Type: text/html");
    out.println("");
    try{
    	for(String line: Files.readAllLines(Paths.get("HTMLPages/Forbidden.html"))){
    		out.println(line);
    	}
    }
    catch(Exception e){
			System.out.println("File Error: " + e.getMessage());
		}
	}
}