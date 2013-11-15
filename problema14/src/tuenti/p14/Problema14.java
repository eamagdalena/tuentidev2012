package tuenti.p14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problema14 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Problema14 p = new Problema14();

		String s;
		while(null != (s = br.readLine())){
			if(DEBUG && s.equals(".")){
				break;
			}

			try{
				p.solve(s);
			}catch(Exception e){
				System.out.println("ERROR");
				if(DEBUG){
					e.printStackTrace();
				}
			}

		}

		br.close();

		//		System.out.println(HINT.length());
		//		p.solve(HINT);

	}

	private final static String HINT = "00001010001101100011011001011101110"
			+ "01110001101110111110001000100010000" +
			"10001101000100010011011101111100111" +
			"00100101100010010011101010001101000";

	private void solve(String s) {
		//String ss;
		String res = "";

		try{
			for(int i = 0; i < s.length() ; i+=14){
				res+= (char) Integer.parseInt(decode(s.substring(i,i+7)) + decode(s.substring(i+7,i+14)), 2);
			}

			checkLegible(res);

			System.out.println(res);

		}catch(Exception e){
			System.out.println("Error!");
		}
	}

	private void checkLegible(String res) {
		for(int i = 0; i < res.length(); i++){
			int c = res.charAt(i);

			if(c < 32 || c > 126){
				throw new RuntimeException("CHARACTER ILEGIBLE!");
			}
		}

	}

	private final static int P1 = 0;
	private final static int P2 = 1;
	private final static int P3 = 3;
	private final static int D1 = 2;
	private final static int D2 = 4;
	private final static int D3 = 5;
	private final static int D4 = 6;

	private static List<String> DIC = new ArrayList<String>();

	static{
		DIC.add("0000000");
		DIC.add("1110000");
		DIC.add("1001100");
		DIC.add("0111100");
		DIC.add("0101010");
		DIC.add("1011010");
		DIC.add("1100110");
		DIC.add("0010110");
		DIC.add("1101001");
		DIC.add("0011001");
		DIC.add("0100101");
		DIC.add("1010101");
		DIC.add("1000011");
		DIC.add("0110011");
		DIC.add("0001111");
		DIC.add("1111111");
	}

	private String decode(String ss) {
		int[] b = new int[7];

		for(int i = 0 ; i < 7; i++){
			b[i] = Integer.parseInt(ss.substring(i,i+1));
		}

		boolean ok1,ok2,ok3;
		ok1 = b[P1] == (b[D1] + b[D2] + b[D4]) % 2;
		ok2 = b[P2] == (b[D1] + b[D3] + b[D4]) % 2;
		ok3 = b[P3] == (b[D2] + b[D3] + b[D4]) % 2;

		if(!ok1 && !ok2){
			if(ok3){
				b[D1] = (b[D1] + 1) % 2;
			}else{
				b[D4] = (b[D4] + 1) % 2;
			}
		}else if(!ok1 && !ok3){
			b[D2] = (b[D2] + 1) % 2;
		}else if(!ok2 && !ok3){
			b[D3] = (b[D3] + 1) % 2;
		}else if(!ok1 && ok2 && ok3){
			b[P1] = (b[P1] + 1) % 2;
		}else if(ok1 && !ok2 && ok3){
			b[P2] = (b[P2] + 1) % 2;
		}else if(ok1 && ok2 && !ok3){
			b[P3] = (b[P3] + 1) % 2;
		}

		if(!DIC.contains("" + b[P1]+ b[P2] + b[D1] + b[P3] + b[D2] + b[D3] + b[D4])){
			throw new RuntimeException("Word not in dic!");
		}

		return "" + b[D1] + b[D2] + b[D3] + b[D4];
	}

}
