

import java.util.Base64;

public class ReverseString extends Problem{

	public ReverseString() {
		super(1, 1, "REVERSESTRING");
	}

	@Override
	protected String getQuestion() {
		byte[] bytes = new byte[12];
		rng.nextBytes(bytes);
		String randomString = Base64.getUrlEncoder().encodeToString(bytes).toUpperCase();
		return randomString.substring(2, rng.nextInt(6)+5);
	}

	@Override
	protected String getAnswer(String question) {
		return (new StringBuilder(question)).reverse().toString();
	}
	
	

}
