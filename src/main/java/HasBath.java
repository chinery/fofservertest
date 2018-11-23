

public class HasBath extends Problem{

	public HasBath(){
		super(1, 3, "HASBATH");
		throw new RuntimeException();
	}

	@Override
	protected String getQuestion() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < rng.nextInt(4); i++) {
			sb.append('X');
		}
		sb.append("BATH");
		for(int i = 0; i < rng.nextInt(4); i++) {
			sb.append('X');
		}
		return sb.toString();
	}

	@Override
	protected String getAnswer(String question) {
		if(question.matches(".?BATH.*")) {
			return "TRUE";
		} else {
			return "FALSE";
		}
	}
}
