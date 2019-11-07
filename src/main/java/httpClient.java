import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class httpClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(InetAddress.getByName(args[0]), Integer.parseInt(args[1]));
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.print("GET / HTTP/1.1\r\n");
        pw.print("Host: "+ args[0] + "\r\n\r\n");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String t;

        char[] buffer = new char[10000];
        int length = 0;
        length = br.read(buffer, 0, buffer.length);
        t= new String(buffer, 0, length);

        t = t.substring(t.indexOf("<!doctype html>"), t.length());

        Transceiver transceiver = new Transceiver(2222,"localhost");
        ReaderPrinter readerPrinter = new ReaderPrinter(transceiver);
        readerPrinter.start();
        readerPrinter.getReader().setStrIn(t);
        br.close();
    }
}
