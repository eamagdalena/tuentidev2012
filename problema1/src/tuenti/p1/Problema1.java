package tuenti.p1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problema1 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;

		int i = Integer.parseInt(br.readLine());

		for(int j = 0 ; j < i ; j++){
			s = br.readLine();
			try{
				solve(s);
			}catch(Exception e){
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}

	private final static int PRESS = 100;
	private final static int SAME_WAIT = 500;

	private static void solve(String input){

		boolean caps = false;
		Key nextKey = null, lastKeyPressed = Key.ZERO;
		long time = 0;
		char c;

		for(int i = 0; i < input.length(); i++){
			c = input.charAt(i);

			/* Comprobamos si hay que cambiar el caps */
			if(c != ' ' && !Character.isDigit(c)  && caps != Character.isUpperCase(c)){
				i--; /* hay que repetir esta iteracion */
				nextKey = Key.CASE;
				caps = !caps;
			}else{ /* Caso normal */
				/* Buscamos destino */
				c = Character.toUpperCase(c);
				nextKey = Key.KEY_LOCATIONS.get(c);
			}

			if(lastKeyPressed == nextKey){ /* Sumamos tiempo de espera */
				time+= SAME_WAIT;
			}else{ /* Sumamos tiempo de transito */
				time+=lastKeyPressed.distances.get(nextKey);
			}

			/* Sumamos pulsaciones */
			time+=nextKey.numberPulses(c) * PRESS;

			lastKeyPressed = nextKey;

		}

		System.out.println(time);

	}

}
