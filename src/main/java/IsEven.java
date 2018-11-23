

public class IsEven extends Problem{
	
	public IsEven(){
		super(1, 1, "ISEVEN");
	}

	@Override
	protected String getQuestion() {
		return "" + rng.nextInt(100000);
	}

	@Override
	protected String getAnswer(String question) {
		if(Integer.parseInt(question) % 2 == 0) {
			return "TRUE";
		} else {
			return "FALSE";
		}
	}

}