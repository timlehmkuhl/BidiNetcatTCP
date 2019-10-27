public class EchoServer {



    public static void main(String[] args) throws Exception {


        //Server
        //$ java Netcat -l 2222
        int port = Integer.parseInt(args[1]);
        System.out.println("Server started!");

        ReaderPrinter readerPrinter= new ReaderPrinter();
        Transceiver transceiver = new Transceiver(port,readerPrinter);
        transceiver.start();

        while(true) {

            Thread.sleep(100);

            if(readerPrinter.getPrinter().getStrOut() !=null) {
                String s= readerPrinter.getPrinter().getStrOut();
                readerPrinter.getReader().setStrIn(s);
                readerPrinter.getReader().run();
            }

        }

    }
}
