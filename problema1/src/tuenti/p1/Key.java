package tuenti.p1;

import java.util.HashMap;

public class Key {

	HashMap<Key, Integer> distances = new HashMap<Key, Integer>();
	char[] chars;


	private Key(char[] chars) {
		this.chars = chars;
	}

	public int numberPulses(char c){
		if(chars == null){ /* Truco pal shift */
			return 1;
		}

		for(int i = 0 ; i < chars.length; i++){
			if(chars[i] == c){
				return 1 + i;
			}
		}

		throw new RuntimeException("Caracter" + c + " no esta en esta tecla");
	}

	final static Key ONE = new Key(new char[]{' ','1'});
	final static Key TWO = new Key(new char[]{'A','B','C','2'});
	final static Key THREE = new Key(new char[]{'D','E','F','3'});
	final static Key FOUR = new Key(new char[]{'G','H','I','4'});
	final static Key FIVE = new Key(new char[]{'J','K','L','5'});
	final static Key SIX = new Key(new char[]{'M','N','O','6'});
	final static Key SEVEN = new Key(new char[]{'P','Q','R','S','7'});
	final static Key EIGHT = new Key(new char[]{'T','U','V','8'});
	final static Key NINE = new Key(new char[]{'W','X','Y','Z','9'});
	final static Key ZERO = new Key(new char[]{'0'});
	final static Key CASE = new Key(null);

	final static int UP = 300;
	final static int DOWN = 300;
	final static int LEFT = 200;
	final static int RIGHT = 200;
	final static int DIAG = 350;

	static{
		ONE.distances.put(TWO, 200);TWO.distances.put(ONE, 200);
		ONE.distances.put(THREE, 400);THREE.distances.put(ONE, 400);
		ONE.distances.put(FOUR, 300);FOUR.distances.put(ONE, 300);
		ONE.distances.put(FIVE, 350);FIVE.distances.put(ONE, 350);
		ONE.distances.put(SIX, 550); SIX.distances.put(ONE, 550);
		ONE.distances.put(SEVEN, 600); SEVEN.distances.put(ONE, 600);
		ONE.distances.put(EIGHT, 650); EIGHT.distances.put(ONE, 650);
		ONE.distances.put(NINE, 700); NINE.distances.put(ONE, 700);
		ONE.distances.put(ZERO, 950); ZERO.distances.put(ONE, 950);
		ONE.distances.put(CASE, 1000); CASE.distances.put(ONE, 1000);

		TWO.distances.put(THREE, RIGHT); THREE.distances.put(TWO, LEFT);
		TWO.distances.put(FOUR, DIAG); FOUR.distances.put(TWO, DIAG);
		TWO.distances.put(FIVE, DOWN); FIVE.distances.put(TWO, UP);
		TWO.distances.put(SIX, DIAG); SIX.distances.put(TWO, DIAG);
		TWO.distances.put(SEVEN, DOWN + DIAG); SEVEN.distances.put(TWO, UP + DIAG);
		TWO.distances.put(EIGHT, 2*DOWN); EIGHT.distances.put(TWO, 2*UP);
		TWO.distances.put(NINE, DOWN + DIAG); NINE.distances.put(TWO, UP + DIAG);
		TWO.distances.put(ZERO, 3*DOWN); ZERO.distances.put(TWO, 3*UP);
		TWO.distances.put(CASE, 2*DOWN+DIAG); CASE.distances.put(TWO, 2*UP+DIAG);

		THREE.distances.put(FOUR, LEFT + DIAG); FOUR.distances.put(THREE, RIGHT + DIAG);
		THREE.distances.put(FIVE, DIAG); FIVE.distances.put(THREE, DIAG);
		THREE.distances.put(SIX, DOWN); SIX.distances.put(THREE, UP);
		THREE.distances.put(SEVEN, 2*DIAG); SEVEN.distances.put(THREE, 2*DIAG);
		THREE.distances.put(EIGHT, DIAG+DOWN); EIGHT.distances.put(THREE, DIAG+UP);
		THREE.distances.put(NINE, 2*DOWN); NINE.distances.put(THREE, UP*2);
		THREE.distances.put(ZERO, DIAG + 2*DOWN); ZERO.distances.put(THREE, DIAG + 2*UP);
		THREE.distances.put(CASE, 3*DOWN); CASE.distances.put(THREE, 3*UP);

		FOUR.distances.put(FIVE, RIGHT); FIVE.distances.put(FOUR, LEFT);
		FOUR.distances.put(SIX, RIGHT*2); SIX.distances.put(FOUR, LEFT*2);
		FOUR.distances.put(SEVEN, DOWN); SEVEN.distances.put(FOUR, UP);
		FOUR.distances.put(EIGHT, DIAG); EIGHT.distances.put(FOUR, DIAG);
		FOUR.distances.put(NINE, RIGHT+DIAG); NINE.distances.put(FOUR, LEFT+DIAG);
		FOUR.distances.put(ZERO, DIAG + DOWN); ZERO.distances.put(FOUR, DIAG + UP);
		FOUR.distances.put(CASE, 2*DIAG); CASE.distances.put(FOUR, 2*DIAG);

		FIVE.distances.put(SIX, RIGHT); SIX.distances.put(FIVE, LEFT);
		FIVE.distances.put(SEVEN, DIAG); SEVEN.distances.put(FIVE, DIAG);
		FIVE.distances.put(EIGHT, DOWN); EIGHT.distances.put(FIVE, UP);
		FIVE.distances.put(NINE, DIAG); NINE.distances.put(FIVE, DIAG);
		FIVE.distances.put(ZERO, 2*DOWN); ZERO.distances.put(FIVE, 2*UP);
		FIVE.distances.put(CASE, DOWN+DIAG); CASE.distances.put(FIVE, UP+DIAG);

		SIX.distances.put(SEVEN, LEFT+DIAG); SEVEN.distances.put(SIX, RIGHT+DIAG);
		SIX.distances.put(EIGHT, DIAG); EIGHT.distances.put(SIX, DIAG);
		SIX.distances.put(NINE, DOWN); NINE.distances.put(SIX, UP);
		SIX.distances.put(ZERO, DIAG+DOWN); ZERO.distances.put(SIX, DIAG+UP);
		SIX.distances.put(CASE, 2*DOWN); CASE.distances.put(SIX, 2*UP);

		SEVEN.distances.put(EIGHT, RIGHT); EIGHT.distances.put(SEVEN, LEFT);
		SEVEN.distances.put(NINE, 2*RIGHT); NINE.distances.put(SEVEN, 2*LEFT);
		SEVEN.distances.put(ZERO, DIAG); ZERO.distances.put(SEVEN, DIAG);
		SEVEN.distances.put(CASE, DIAG + RIGHT); CASE.distances.put(SEVEN, DIAG + LEFT);

		EIGHT.distances.put(NINE, RIGHT); NINE.distances.put(EIGHT, LEFT);
		EIGHT.distances.put(ZERO, DOWN); ZERO.distances.put(EIGHT, UP);
		EIGHT.distances.put(CASE, DIAG); CASE.distances.put(EIGHT, DIAG);

		NINE.distances.put(ZERO, DIAG); ZERO.distances.put(NINE, DIAG);
		NINE.distances.put(CASE, DOWN); CASE.distances.put(NINE, UP);

		ZERO.distances.put(CASE, RIGHT); CASE.distances.put(ZERO, LEFT);

	}

	private void storeKeyLocations(){
		for(char s : chars){
			KEY_LOCATIONS.put(s, this);
		}
	}

	static final HashMap<Character, Key> KEY_LOCATIONS = new HashMap<Character, Key>();

	static{
		ONE.storeKeyLocations();
		TWO.storeKeyLocations();
		THREE.storeKeyLocations();
		FOUR.storeKeyLocations();
		FIVE.storeKeyLocations();
		SIX.storeKeyLocations();
		SEVEN.storeKeyLocations();
		EIGHT.storeKeyLocations();
		NINE.storeKeyLocations();
		ZERO.storeKeyLocations();
	}

	@Override
	public String toString() {
		return "TECLA " + new String(chars);
	}

}
