public class Receiver extends Thread{

	private TCPSocket socket;
	private Actor readprinter;
	private Actor transceiver;
	private String nachricht;
    private static final int BUFSIZE = 508;


	public Receiver(TCPSocket socket, Actor readprinter, Actor transceiver){
		this.socket = socket;
		this.readprinter = readprinter;
		this.transceiver = transceiver;
	}


    /**
     * Nachrichten werden im Thread empfangen
     */
	public void run() {
        while (true){
            try {
                this.nachricht = socket.receive(BUFSIZE);
                this.readprinter.tell(nachricht, transceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(nachricht.equals("\u0004")){
                readprinter.shutdown();
                break;
            }
        }
	}

}
