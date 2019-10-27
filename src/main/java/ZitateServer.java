import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ZitateServer {

    public static void main(String[] args) throws Exception{
        List<String> lines = new ArrayList<>();
        BufferedReader reader;

            reader = new BufferedReader(new FileReader(
                    "G:\\InfProjekte\\BidiNetcatTCP\\src\\main\\resources\\zitate.txt"));
            String line = reader.readLine();
            while (line != null) {

                lines.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();

        //Server
        //$ java Netcat -l 2222
        int port = Integer.parseInt(args[1]);
        System.out.println("Server started!");

        Random r = new Random();
        int random;
        while (true){
            random = r.nextInt(lines.size());
            if(random % 2 != 1) break;
        }

        ReaderPrinter readerPrinter= new ReaderPrinter();
        Transceiver transceiver = new Transceiver(port, readerPrinter);
        transceiver.start();

        while(true) {

            Thread.sleep(100);

            if(readerPrinter.getPrinter().getStrOut() !=null) {
                String s= lines.get(random) + "\n" + lines.get(random+1);
                readerPrinter.getReader().setStrIn(s);
                readerPrinter.getReader().run();

            }

        }

    }

}
