

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Player {
	private String player;
	private int x, y, turns, bonusPoints;
	private ArrayList<Element> swag;
	private Problem problem;
	private PrintWriter out;
	private BufferedReader in;
	private PlayerView playerView;
	
	public Player(Socket s, String player, int x, int y) throws IOException {
		s.setSoTimeout(1000);
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		swag = new ArrayList<>();
		this.player = player;
		this.x = x;
		this.y = y;
		turns = 0;
		problem = null;
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                playerView = new PlayerView(player);
                playerView.setVisible(true);
            }
        });
	}
	
	public void displayInformation() {
		playerView.updatePlayerView(this.toString());
	}
	
	public String getInput() throws SocketTimeoutException, IOException {
		return in.readLine();
	}
	
	public void sendMessage(String msg) {
		out.println(msg);
	}
	
	public void addProblem(Problem p) {
		this.problem = p;
	}
	
	public Problem getProblem() {
		return problem;
	}

	public String getPlayer() {
		return player;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		int new_x = this.x+x;
		if(new_x < 0) {
			this.x = (Map.MAP_X-1) + x;
		}
		else if(new_x >= Map.MAP_X) {
			this.x = 0+x;
		}
		else {
			this.x += x;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		int new_y = this.y+y;
		if(new_y < 0) {
			this.y = (Map.MAP_Y-1) + y;
		}
		else if(new_y >= Map.MAP_Y) {
			this.y = 0+y;
		}
		else {
			this.y += y;
		}
	}

	public int getTurns() {
		return turns;
	}
	
	public void setTurns(int turns) {
		this.turns = turns;
	}

	public void takeTurn(int turns) {
		this.turns -= turns;
	}
	
	public void addBonusTurns(int bonus) {
		this.turns += bonus;
	}
	
	public void addBonusPoints(int bonus) {
		this.bonusPoints += bonus;
	}
	
	public void addSwag(Element e) {
		if(e!=null) {
			swag.add(e);
		}
	}
	
	public String toString() {
		return player + ":\t" + getSwagAsString();
	}
	
	private String getSwagAsString() {
		int gold = 0, copper = 0, aluminum = 0, magnesium = 0, nickel = 0, silicon = 0;
		int goldNo = 0, copperNo = 0, aluminumNo = 0, magnesiumNo = 0, nickelNo = 0, siliconNo = 0;
		for(Element e : swag) {
			switch (e.getName()) {
				case Element.GOLD : gold+=e.getPoints(); goldNo++; break;
				case Element.COPPER : copper+=e.getPoints(); copperNo++; break;
				case Element.ALUMINUM : aluminum+=e.getPoints(); aluminumNo++; break;
				case Element.MAGNESIUM : magnesium+=e.getPoints(); magnesiumNo++; break;
				case Element.NICKEL : nickel+=e.getPoints(); nickelNo++; break;
				case Element.SILICON : silicon+=e.getPoints(); siliconNo++; break;
				default : break;
			}
		}
		return "" + goldNo + "\t" + copperNo + "\t" + aluminumNo + "\t" + magnesiumNo + "\t" + nickelNo + "\t" + siliconNo + "\t" + bonusPoints + "\t\t" + (gold+copper+aluminum+magnesium+nickel+silicon+bonusPoints);
	}
}
