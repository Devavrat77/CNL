import java.io.*;
import java.net.*;

public class FileReceiver {
    public static void main(String[] args) {
        int port = 12345;

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");
            
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(packet);
            
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            
            String fileName = (String) objectInputStream.readObject();
            long fileSize = objectInputStream.readLong();

            File receivedFile = new File(fileName);
            FileOutputStream fos = new FileOutputStream(receivedFile);

            int bytesRead;
            long totalBytesRead = 0;

            while (totalBytesRead < fileSize) {
                serverSocket.receive(packet);
                fos.write(packet.getData(), 0, packet.getLength());
                totalBytesRead += packet.getLength();
            }

            fos.close();
            objectInputStream.close();
            byteArrayInputStream.close();

            System.out.println("File received: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
