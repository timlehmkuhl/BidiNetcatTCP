import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class TCPSocket {

    private Socket socket;

    /**
     * Konstruktor fuer Client
     * @param ip
     * @param port
     * @throws SocketException
     */
    public TCPSocket(int port, String ip) {

        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Konstruktor fuer Server
     * @param port
     * @throws SocketException
     */
    public TCPSocket(int port) {
        try {
            socket = new ServerSocket(port).accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ein String wird vor dem Senden in ein Byte-Array gewandelt und
     * in ein DatagramPacket gelegt, welches dem Socket uebergeben wird
     * @param message
     * @throws IOException
     */
    public void send(String message)  {
        try {
            socket.getOutputStream().write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * In der send- und receive-Methode
     * Ein Byte-Array aus dem empfangenden DatagramPacket wird in ein String gewandelt
     * @param maxBytes
     * @return String
     * @throws IOException
     */
    public String receive(int maxBytes) {
        String ret = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            char[] buffer = new char[maxBytes];
            bufferedReader.read(buffer, 0, buffer.length);

            ret = new String(buffer, 0, buffer.length);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void shutdown() {
    }



}
