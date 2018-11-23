import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProblemTests {

	@Test
	void test() {
		ProblemFactory pf = new ProblemFactory();
		for(int i = 0; i < 100; i++) {
			Problem p = pf.getNewProblem();
			System.out.println(p.toString());
			System.out.println(p.answer);
			System.out.println();
			assertTrue(p.checkAnswer("ANSWER "+p.answer));
		}
	}

}
