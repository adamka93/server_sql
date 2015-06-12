package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private final int port;
	private ServerSocket serverSocket;
	private Thread runningThread;
	private boolean isStopped = false;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() {
		System.out.println("Server start.");
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}

		try {
			openSocket();
			
			while( !isStopped ) {
				Socket clientSocket  = null;
				try {
					clientSocket = serverSocket.accept();
					System.out.println("Clinet connected.");
				} catch(IOException ex) {
					if( isStopped() ) {
						System.out.println("Server Stopped.") ;
	                    return;
					}
					ex.printStackTrace();
				}
				
				new Thread( new WorkerRunnable(clientSocket) ).start();
			}
			System.out.println("Server stopped.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private synchronized boolean isStopped() {
		return isStopped;
	}
	
	private void openSocket() throws Exception {
		this.serverSocket = new ServerSocket(port);
	}
	
	public static void main(String[] args) {
		new Server(6655).start();
	}

}
