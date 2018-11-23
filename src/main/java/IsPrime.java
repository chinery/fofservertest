

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IsPrime extends Problem{

	private static List<Integer> primes = new ArrayList<>(100);
	private static List<Integer> nonprimes;

	public IsPrime(){
		super(1, 2, "ISPRIME");
	}

	private static void generatePrimes() {
		int num = 2;
		while(primes.size() < 100) {
			primes.add(num++);
			while(!isPrime(num)) {
				num++;
			}
		}
		nonprimes = IntStream.range(1, primes.get(primes.size()-1))
				.filter(in -> !primes.contains(in))
				.boxed()
				.collect(Collectors.toList());
	}

	private static boolean isPrime(int in) {
		for(int prime : primes) {
			if(in % prime == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void init() {
		if(nonprimes == null) {
			generatePrimes();
		}
	}

	@Override
	protected String getQuestion() {
		if(rng.nextBoolean()) {
			return "" + primes.get(rng.nextInt(primes.size()));
		} else {
			return "" + nonprimes.get(rng.nextInt(nonprimes.size()));
		}
	}

	@Override
	protected String getAnswer(String question) {
		if(primes.contains(Integer.parseInt(question))) {
			return "TRUE";
		} else {
			return "FALSE";
		}
	}




}