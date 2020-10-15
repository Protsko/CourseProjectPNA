package server.controller;

import java.io.*;
import java.net.*;

public class MainController {
    public void processRequest(Socket clientSocket) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream())
        );
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );

    }
}
