package server.app;

import server.controller.MainController;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args){
        MainController controller = new MainController();
        try{
            ServerSocket serverSocket = new ServerSocket(8100);
            while (true){
                controller.processRequest(serverSocket.accept());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
