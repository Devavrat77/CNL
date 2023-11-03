/*Write a program using UDP Sockets to enable file transfer (Script, Text, Audio and
Video one file each) between two machines. */


import java.io.*;
import java.net.*;

public class UDPmyClient {
    public static void main(String args[]) throws Exception {
        byte b[] = new byte[1024];
        FileInputStream f = new FileInputStream("C:\\Users\\dell\\OneDrive\\Desktop\\CNL\\7\\1.text.txt");
        DatagramSocket dsoc = new DatagramSocket(2000);
        int i = 0;

        while (f.available() != 0) {
            b[i] = (byte) f.read();
            i++;
        }

        f.close();
        dsoc.send(new DatagramPacket(b, i, InetAddress.getLocalHost(), 1000));
    }
}
