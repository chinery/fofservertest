

public class MiddleTwo extends Problem{
	
	private static String[] words = {"SOME", "EVEN", "LENGTH", "THINGS"};
	
	public MiddleTwo(){
		super(1, 5, "MIDDLETWO");
	}

	@Override
	protected String getQuestion() {
		return words[rng.nextInt(words.length)];
	}

	@Override
	protected String getAnswer(String question) {
		int midleft = question.length()/2 - 1; 
		return question.substring(midleft, midleft+2);
	}

}