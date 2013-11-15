package tuenti.p18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problema18 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;

		int i = Integer.parseInt(br.readLine());

		for(int j = 0 ; j < i ; j++){
			s = br.readLine();
			try{
				new Problema18().solve(s);
			}catch(Exception e){
				System.out.println("ERROR"); //Si falla este caso hay que ser capaz de seguir con el siguiente
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}

	private void solve(String s) throws IOException{
		long l = Long.parseLong(s);
		long res = 1 + ( l + 1)* l / 2;
		System.out.println("Case #" + (n++) + ": " + res);
	}

	static int n = 1;

}
