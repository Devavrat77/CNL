//Write a program using TCP socket for wired network for following 
//Say Hello to Each other


import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;

        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server.");

            String message;
            while (true) {
                System.out.print("Client: ");
                message = reader.readLine();
                out.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String response = in.readLine();
                System.out.println(response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
