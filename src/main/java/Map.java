

import java.util.Random;

public class Map {
	public static final int MAP_X = 200, MAP_Y = 50;
	private Element[][] map;
	private Random random;
	// gold worth 1 point; copper 1 point; aluminum 2 point; magnesium 2 points; nickel 3 points; silicon 5 points
	private int gold, copper, aluminum, magnesium, nickel, silicon;
	
	public Map() {
		this.gold = 100;
		this.copper = 100;
		this.aluminum = 75;
		this.magnesium = 75;
		this.nickel = 50;
		this.silicon = 25;
		random = new Random();
		map = new Element[MAP_Y][MAP_X];
		generateMap();
	}
	
	public Map(int gold, int copper, int aluminum, int magnesium, int nickel, int silicon) {
		this.gold = gold;
		this.copper = copper;
		this.aluminum = aluminum;
		this.magnesium = magnesium;
		this.nickel = nickel;
		this.silicon = silicon;
		random = new Random();
		map = new Element[MAP_Y][MAP_X];
		generateMap();
	}
	
	public Element pickUp(int x, int y) {
		Element e = map[y][x];
		map[y][x] = null;
		addElement(e);
		return e;
	}
	
	public String getSubMap(int x, int y, String name) {
		int left = x-3;
		int right = x + 3;
		int top = y - 3;
		int bottom = y + 3;
		String look = "";
		for(int i=top; i<=bottom; i++) {
			for(int j=left; j<=right; j++) {
				if(i<0 || i>=map.length) {
					look += "#";
				}
				else if (j<0 || j>=map[0].length) {
					look += "#";
				}
				else if(i==y && j==x) {
					look += name;
				}
				else if(map[i][j] != null) {
					look += map[i][j].toString();
				}
				else {
					look += ".";
				}
			}
			look += ";";
		}
		return look;
	}
	
	private void generateMap() {
		addElements(gold,Element.GOLD);
		addElements(copper,Element.COPPER);
		addElements(aluminum,Element.ALUMINUM);
		addElements(magnesium,Element.MAGNESIUM);
		addElements(nickel,Element.NICKEL);
		addElements(silicon,Element.SILICON);
	}
	
	private void addElements(int number, String type) {
		for(int i=1; i<=number; i++) {
			addElement(new Element(type));
		}
	}
	
	private void addElement(Element e) {
		int x = random.nextInt(MAP_X-1);
		int y = random.nextInt(MAP_Y-1);
		while(map[y][x] != null) {
			x = random.nextInt(MAP_X-1);
			y = random.nextInt(MAP_Y-1);
		}
		map[y][x] = e;
	}
	
	public String[][] getCopyOfMap() {
		String[][] m = new String[MAP_Y][MAP_X];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j] == null) {
					m[i][j] = (".");
				}
				else {
					m[i][j] = (map[i][j].toString());
				}
			}
		}
		return m;
	}
	
	public void printMap() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j] == null) {
					System.out.print(".");
				}
				else {
					System.out.print(map[i][j].toString());
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Map m = new Map();
		// m.printMap();
		System.out.println(m.getSubMap(99, 99, "1"));
		System.out.println(m.getSubMap(0, 99, "1"));
		System.out.println(m.getSubMap(0, 0, "1"));
		System.out.println(m.getSubMap(99, 0, "1"));
	}
}
