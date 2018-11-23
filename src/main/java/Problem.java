

import java.util.Random;

public abstract class Problem {
	String question;
	String answer;
	protected static Random rng = new Random();
	private int bonusTurns;
	private int bonusPoints;
	private String name;
	
	public Problem(int bonusTurns, int bonusPoints, String name) {
		this.bonusTurns = bonusTurns;
		this.bonusPoints = bonusPoints;
		this.init();
		this.question = getQuestion();
		this.answer = getAnswer(this.question);
		this.name = name;
	}
	
	protected void init() {
		//by default do nothing, but can be overridden to initialise something
	}

	public String toString() {
		return name + " " + question;
	}
	
	public boolean checkAnswer(String s) {
		return ("ANSWER " + answer).equals(s.toUpperCase());
	}
	
	protected abstract String getQuestion();
	protected abstract String getAnswer(String question);
	
	public int getBonusTurns() {
		return bonusTurns;
	}
	
	public int getBonusPoints() {
		return bonusPoints;
	}
}
