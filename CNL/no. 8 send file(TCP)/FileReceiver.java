//Write a program using TCP socket for wired network for following 
//File transfer
import java.io.*;
import java.net.*;

public class FileReceiver {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

            String fileName = dis.readUTF();
            long fileSize = dis.readLong();

            File receivedFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(receivedFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;

            while (totalBytesRead < fileSize && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesRead))) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }

            fos.close();
            dis.close();

            System.out.println("File received: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
