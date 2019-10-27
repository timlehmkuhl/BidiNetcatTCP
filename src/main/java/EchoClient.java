public class EchoClient {

    public static void main(String[] args) throws Exception {

        String host = args[0];

        System.out.println("Client started!");

        int port = Integer.parseInt(args[1]);
        String nachricht = args[2];

        Transceiver transceiver = new Transceiver(port, host);
        ReaderPrinter readerPrinter = new ReaderPrinter(transceiver);
        readerPrinter.start();
        readerPrinter.getReader().setStrIn(nachricht);

    }
}
