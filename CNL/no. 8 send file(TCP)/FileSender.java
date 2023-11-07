//Write a program using TCP socket for wired network for following 
//File transfer
import java.io.*;
import java.net.*;

public class FileSender {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;
        String filePath = "path/to/your/file.txt"; // Replace with the path to the file you want to send

        try (Socket socket = new Socket(serverAddress, serverPort);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

            File fileToSend = new File(filePath);
            dos.writeUTF(fileToSend.getName());
            dos.writeLong(fileToSend.length());

            FileInputStream fis = new FileInputStream(fileToSend);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            fis.close();
            dos.close();

            System.out.println("File sent: " + fileToSend.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
