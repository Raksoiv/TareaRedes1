package tarearedes1;

import java.net.ServerSocket;

public class TareaRedes1 {
  public static void main(String[] args){
    TareaRedes1 server = new TareaRedes1();
    server.initServer(8000);
  }

  public void initServer(int port){
    ServerSocket server;
    try{
      //Configure and open the server for listen the port
      server = new ServerSocket(port);
    }
    catch(Exception e){
      System.out.println("Socket Error: " + e.getMessage());
      return;
    }

    //Configure the thread Pool
    Thread threadPool[] = new Thread[5];
    Controller controllerPool[] = new Controller[5];
    for(int i = 0; i < 5; i++){
      controllerPool[i] = new Controller(server, i);
      threadPool[i] = new Thread(controllerPool[i]);
      threadPool[i].start();
    }
  }
}
