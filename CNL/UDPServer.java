//Socket Programming using C/C++/Java.
//UDP Client, UDP Server


import java.io.IOException;     // java.io.*
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;   //java.net.*

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(12345)) {
            System.out.println("Server is listening on port 12345...");

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Client (" + clientAddress + ":" + clientPort + "): " + message);

                // You can process the message here and send a response back to the client if needed.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
