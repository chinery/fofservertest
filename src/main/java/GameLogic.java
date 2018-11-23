

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GameLogic extends Thread{
	private Map map;
	private GameLogicView gameView;
	private ProblemFactory problemFactory;
	private ArrayList<Player> players;
	private int nextTurn;
	private Random random;
	private int tickTime;
	private final int MAX_TURNS = 10;
	private final int TICK_LIMIT = 30;
	
	public GameLogic() {
		map = new Map();
		problemFactory = new ProblemFactory();
		players = new ArrayList<>();
		random = new Random();
		nextTurn = 0;
		tickTime = 0;
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                gameView = new GameLogicView();
                gameView.setVisible(true);
            }
        });
	}
	
	public void run() {
		updateGameView();
		while(true) {
			if(!players.isEmpty()) {
				getPlayer(nextTurn).setTurns(MAX_TURNS);
				getPlayer(nextTurn).sendMessage("START " + MAX_TURNS);
				int startTime = getTickTime();
				boolean canPlay = true;
				updateGameView();
				while(canPlay) {
					try {
						updateGameView();
						Thread.sleep(1000);
						String cmd = getPlayer(nextTurn).getInput();
						String result = processCommand(cmd, getPlayer(nextTurn));
						updateGameView();
						getPlayer(nextTurn).sendMessage(result);
						getPlayer(nextTurn).takeTurn(1);
						tick();
						canPlay = (((getTickTime()-startTime)!=TICK_LIMIT) && (getPlayer(nextTurn).getTurns()!=0));
					}
					catch(SocketTimeoutException e) {
						tick();
						canPlay = (((getTickTime()-startTime)!=TICK_LIMIT) && (getPlayer(nextTurn).getTurns()!=0));
					} 
					catch (IOException e) {
						e.printStackTrace();
						setNextPlayer();
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
				getPlayer(nextTurn).sendMessage("END");
				getPlayer(nextTurn).displayInformation();
				setNextPlayer();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private synchronized void updateGameView() {
		String[][] m = map.getCopyOfMap();
		if(gameView != null) {
			for(Player p : players) {
				int x = p.getX();
				int y = p.getY();
				m[y][x] = p.getPlayer();
			}
			gameView.updateMap(m, nextTurn+1);
		}
	}
	
	private synchronized Player getPlayer(int index) {
		return players.get(index);
	}
	
	private synchronized int getTickTime() {
		return tickTime;
	}
	
	private synchronized void tick() {
		tickTime++;
	}
	
	private synchronized void setNextPlayer() {
		int n = nextTurn+1;
		if(n>=getNumberOfPlayers()) {
			nextTurn = 0;
		}
		else {
			nextTurn = n;
		}
	}
	
	private synchronized int getNumberOfPlayers(){
		return players.size();
	}
	
	public synchronized void addNewPlayer(Socket s, int n) throws IOException {
		String name = ""+n;
		int x = random.nextInt(Map.MAP_X);
		int y = random.nextInt(Map.MAP_Y);
		players.add(new Player(s,name,x,y));
	}
	
	public synchronized String processCommand(String command, Player p) {
		command = command.toUpperCase();
		String[] commands = command.split(" ");
		switch (commands[0]) {
			case "PICKUP" : return pickUp(p); 
			case "LOOK" : return look(p);
			case "MOVE" : return move(command, p);
			case "PROBLEM" : return newProblem(p);
			case "ANSWER" : return isCorrect(command, p);
			default : return "FAIL";
		}
	}
	
	private synchronized String move(String command, Player p) {
		String[] commands = command.split(" ");
		if(commands.length == 2) {
			switch (commands[1]) {
				case "N" :  p.setY(-1); return "SUCCESS";
				case "S" :  p.setY(1); return "SUCCESS";
				case "E" :  p.setX(1); return "SUCCESS";
				case "W" :  p.setX(-1); return "SUCCESS";
				default : return "FAIL";
			}
		}
		return "FAIL";
	}
	
	private synchronized String pickUp(Player p) {
		Element e = map.pickUp(p.getX(), p.getY());
		if(e!=null) {
			p.addSwag(e);
			return "PICKED UP " + e.getFullName();
		}
		return "PICKED UP NOTHING";
	}
	
	private synchronized String look(Player p) {
		return map.getSubMap(p.getX(),p.getY(), p.getPlayer());
	}
	
	private synchronized String newProblem(Player p) {
		Problem prob = problemFactory.getNewProblem();
		p.addProblem(prob);
		return prob.toString();
	}
	
	private synchronized String isCorrect(String answer, Player p) {
		if(p.getProblem().checkAnswer(answer)) {
			p.addBonusTurns(p.getProblem().getBonusTurns());
			p.addBonusPoints(p.getProblem().getBonusPoints());
			return "SUCCESS" + " BONUSTURNS " + p.getProblem().getBonusTurns() + " BONUSPOINTS " + p.getProblem().getBonusPoints();
		}
		else {
			return "FAIL";
		}
	}
}
