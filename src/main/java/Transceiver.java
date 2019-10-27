public class Transceiver<A> implements Actor<A> {

	private Actor transmitter;
	private Receiver receiver;
	private TCPSocket TCPSocket;


	/**
	 * Konstruktor fuer Server
	 * @param port
	 * @param readerPrinter
	 */
	public Transceiver(int port, ReaderPrinter readerPrinter) {
        TCPSocket = new TCPSocket(port);
		receiver = new Receiver(TCPSocket, readerPrinter,this);
		transmitter = new Transmitter(TCPSocket);
	}

	public void start(){
		receiver.start();
	}


	/**
	 * Konstruktor fuer Client
	 * @param port
	 * @param host
	 */
	public Transceiver(int port, String host)  {
        TCPSocket = new TCPSocket(port, host);
		transmitter = new Transmitter(TCPSocket);
	}


	@Override
	public void tell(String message, Actor sender) throws Exception {
		transmitter.tell(message, sender);

		if(receiver == null) {
			receiver = new Receiver(TCPSocket,sender,this);
			receiver.start();
		}
	}

	@Override
	public void shutdown() {

	}


}
