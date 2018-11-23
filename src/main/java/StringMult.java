

public class StringMult extends Problem {
	
	private static String[] shortWords = {"HI", "HEY", "OI", "HO"};
	
	public StringMult(){
		super(1, 4, "STRINGMULT");
	}

	@Override
	protected String getQuestion() {
		return shortWords[rng.nextInt(shortWords.length)] + " " + (rng.nextInt(3)+1);
	}

	@Override
	protected String getAnswer(String question) {
		String[] parts = question.split(" ");
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(parts[1]);
		for(int i = 0; i < n; i++) {
			sb.append(parts[0]);
		}
		return sb.toString();
	}
	

}