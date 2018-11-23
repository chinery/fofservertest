


public class Fib extends Problem{
	
	private static long[] fibseq;
	
	public Fib(){
		super(1, 2, "FIB");
	}

	private static void generateFib() {
		fibseq = new long[100];
		fibseq[0] = 1;
		fibseq[1] = 2;
		for(int i = 2; i < fibseq.length; i++) {
			fibseq[i] = fibseq[i-1] + fibseq[i-2];
		}
	}
	
	@Override
	protected void init() {
		if(fibseq == null) {
			generateFib();
		}
	}

	@Override
	protected String getQuestion() {
		return "" + (rng.nextInt(50)+1);
	}

	@Override
	protected String getAnswer(String question) {
		return "" + fibseq[Integer.parseInt(question)-1];
	}

}