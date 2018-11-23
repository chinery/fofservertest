


public class Factorial extends Problem{
	
	public Factorial(){
		super(1, 1, "FACTORIAL");
	}

	@Override
	protected String getQuestion() {
		return "" + rng.nextInt(10);
	}

	@Override
	protected String getAnswer(String question) {
		return "" + factorial(Long.parseLong(question));
	}
	
	private long factorial(long in) {
		long ans = in--;
		while(in > 0) {
			ans *= in--;
		}
		return ans;
	}

}