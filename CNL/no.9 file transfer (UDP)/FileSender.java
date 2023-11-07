//Write a program using UDP Sockets to enable file transfer.



import java.io.*;
import java.net.*;

public class FileSender {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;
        String filePath = "path/to/your/file.txt"; // Replace with the path to the file you want to send

        try (DatagramSocket socket = new DatagramSocket();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            File fileToSend = new File(filePath);
            objectOutputStream.writeObject(fileToSend.getName());
            objectOutputStream.writeLong(fileToSend.length());
            objectOutputStream.flush();

            byte[] objectBytes = byteArrayOutputStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(objectBytes, objectBytes.length, InetAddress.getByName(serverAddress), serverPort);
            socket.send(packet);

            FileInputStream fis = new FileInputStream(fileToSend);
            byte[] buffer = new byte[1024];

            while (fis.read(buffer) != -1) {
                packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(serverAddress), serverPort);
                socket.send(packet);
            }

            fis.close();

            System.out.println("File sent: " + fileToSend.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
