//Socket Programming using C/C++/Java.
//UDP Client, UDP Server



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Message to be sent to the server
            String message = "Hello, UDP Server!";
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);

            System.out.println("Client sent a message to the server: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
