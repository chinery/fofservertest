

public class LeftTwo extends Problem{
	
	private static String[] greetings = {"HELLO", "HEY", "GDAYMATE"};
	
	public LeftTwo(){
		super(1, 3, "LEFTTWO");
	}

	@Override
	protected String getQuestion() {
		return greetings[rng.nextInt(greetings.length)];
	}

	@Override
	protected String getAnswer(String question) {
		return question.substring(2) + question.substring(0, 2);
	}

}