package client.main;

import java.io.*;
import java.net.Socket;

public class RequestHandler {

    private RequestHandler() {
    }

    public static String doRequest(String query) {
        String response = "";
        try {
            Socket clientSocket = new Socket("127.0.0.1", 8020);
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream())
            );
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            bufferedWriter.write(query + "\n");
            bufferedWriter.flush();
            response = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
