

public class EvenFibToN extends Problem {

	private static long[] fibseq;
	private static long[] evenfibton;
	
	public EvenFibToN(){
		super(1, 6, "EVENFIBTON");
	}
	
	private static void generateFib() {
		fibseq = new long[100];
		fibseq[0] = 1;
		fibseq[1] = 2;
		for(int i = 2; i < fibseq.length; i++) {
			fibseq[i] = fibseq[i-1] + fibseq[i-2];
		}
		
		evenfibton = new long[100];
		int sum = 0;
		for(int i = 0; i < evenfibton.length; i++) {
			if(fibseq[i] % 2 == 0) {
				sum += fibseq[i];
			}
			evenfibton[i] = sum;
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
		return "" + evenfibton[Integer.parseInt(question)-1];
	}

}