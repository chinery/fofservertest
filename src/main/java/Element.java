


public class Element {
	public static final String GOLD = "G", COPPER = "C", ALUMINUM = "A", MAGNESIUM = "M", NICKEL = "N", SILICON = "S";
	private final int gold = 1, copper = 1, aluminum = 2, magnesium = 2, nickel = 3, silicon = 5;
	
	private String name;
	private int points;
	
	public Element(String name) {
		this.name = name;
		setPoints(name);
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}
	
	public String getFullName() {
		switch(name){
			case Element.GOLD : return "GOLD";
			case Element.COPPER : return "COPPER";
			case Element.ALUMINUM : return "ALUMINUM";
			case Element.MAGNESIUM : return "MAGNESIUM";
			case Element.NICKEL : return "NICKEL";
			case Element.SILICON : return "SILICON";
			default : return "NOTHING";
		}
	}
	
	public String toString() {
		return name;
	}
	
	private void setPoints(String type) {
		switch(type){
			case Element.GOLD : points = gold; break;
			case Element.COPPER : points = copper; break;
			case Element.ALUMINUM : points = aluminum; break;
			case Element.MAGNESIUM : points = magnesium; break;
			case Element.NICKEL : points = nickel; break;
			case Element.SILICON : points = silicon; break;
			default : break;
		}
	}
	
}
