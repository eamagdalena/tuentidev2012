package tuenti.p12;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problema12 {
	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		solve(br.readLine());

		br.close();

	}

	private final static String KEY1 = "ed8ce15da9b7b5e2ee70634cc235e363"; //QR
	private final static String KEY2 = "a541714a17804ac281e6ddda5b707952"; //Comment
	private final static String KEY3 = "62cd275989e78ee56a81f0265a87562e"; // Stego LSB

	private static void solve(String s) {

		String res = "";

		for(int i = 0; i < KEY1.length(); i++){
			res+= String.format("%h", (Integer.parseInt(KEY1.substring(i,i+1),16) +
					Integer.parseInt(KEY2.substring(i,i+1),16) +
					Integer.parseInt(KEY3.substring(i,i+1),16) +
					Integer.parseInt(s.substring(i,i+1),16)) % 16);
		}

		System.out.println(res);

	}
}
