package server.controller;

import server.controller.command.Command;
import server.controller.command.CommandProvider;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    public void processRequest(Socket clientSocket) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream())
        );
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );

        String request = bufferedReader.readLine();
        String[] splitRequest = request.split("&");
        Map<String, String> params = getRequestParams(splitRequest);
        String commandName = CommandProvider.getCommandName(splitRequest);
        Command command = CommandProvider.defineCommand(commandName);
        String response = command.execute(params);

        bufferedWriter.write(response + "\n");
        bufferedWriter.flush();

        bufferedReader.close();
        bufferedWriter.close();
        clientSocket.close();
    }

    private Map<String, String> getRequestParams(String[] splitRequest) {
        Map<String, String> params = new HashMap<>();
        for (String sr : splitRequest) {
            String[] s = sr.split("=");
            params.put(s[0], s[1]);
        }
        return params;
    }
}
