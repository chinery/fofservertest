

import java.util.Random;

public class ProblemFactory {
	
	private Random r;
	
	public ProblemFactory() {
		r = new Random();
	}
	
	public Problem getNewProblem() {
		int i = r.nextInt(10);
		switch(i) {
			case 0 : return new ReverseString();
			case 1 : return new IsPrime();
			case 2 : return new IsEven();
			case 3 : return new Factorial();
			case 4 : return new Fib();
			case 5 : return new LeftTwo();
			case 6 : return new HasBath();
			case 7 : return new StringMult();
			case 8 : return new MiddleTwo();
			case 9 : return new EvenFibToN();
			default : return new IsPrime();
		}
	}
}
