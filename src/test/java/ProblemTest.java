import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProblemTest {

	@Test
	public void test() {
		ProblemFactory pf = new ProblemFactory();
		for(int i = 0; i < 100; i++) {
			Problem p = pf.getNewProblem();
//			System.out.println(p.toString());
//			System.out.println(p.answer);
//			System.out.println();
			assertTrue(p.checkAnswer("ANSWER "+p.answer));
		}
	}

}
