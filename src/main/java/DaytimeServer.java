public class DaytimeServer {

    public static void main(String[] args) throws Exception {

        //Server
        //$ java Netcat -l 2222
        int port = Integer.parseInt(args[1]);
        System.out.println("Server started!");

        ReaderPrinter readerPrinter= new ReaderPrinter();
        Transceiver transceiver = new Transceiver(port, readerPrinter);
        transceiver.start();

        while(true) {

            Thread.sleep(100);

            if(readerPrinter.getPrinter().getStrOut() !=null) {
                String time = java.time.LocalTime.now().toString().substring(0,8);
                readerPrinter.getReader().setStrIn(time);
                readerPrinter.getReader().run();


            }

        }

    }

}
