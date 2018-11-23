

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	
	private ServerSocket clientConnect;
	private GameLogic game;
	private int n;
	
	public Server(int port) throws IOException {
		clientConnect = new ServerSocket(port);
		game = new GameLogic();
		this.n = 1;
	}
	
	public void run() {
		System.out.println("Server listening on " + clientConnect.getLocalPort());
		game.start();
		try {
			while(true) {
				Socket s = clientConnect.accept();
				//System.out.println("Group " + n + " Has Connected");
				game.addNewPlayer(s, n);
				this.n++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				clientConnect.close();
			} catch (IOException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server(14002).start();
	}
}
