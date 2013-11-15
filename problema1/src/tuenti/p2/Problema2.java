package tuenti.p2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problema2 {

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
				System.out.println("ERROR"); //Si falla este caso hay que ser capaz de seguir con el siguiente
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}

	private static int caso = 1;

	private static void solve(String input){
		long numero = Long.parseLong(input);
		long numeroPerfecto = dameNumeroPerfecto(numero);
		long resto = numero - numeroPerfecto;

		System.out.println("Case #" + (caso++) + ": " + (cuentaUnos(numeroPerfecto) + cuentaUnos(resto)));
	}

	private static long dameNumeroPerfecto(long numero){
		boolean buscando = true;
		long res = 0L;

		for(int i = 63; i >= 0 ;i--){
			if(buscando && (numero & (1L << i)) > 0){
				buscando = false;
			}else if(!buscando){
				res+= (1L << i);
			}

		}

		return res;

	}

	private static int cuentaUnos(long numero){
		int cuenta = 0;

		for(int i = 1; i <= 64 ;i++){

			if( (numero & (1L << i-1)) > 0){
				cuenta++;
			}

		}

		return cuenta;

	}

}
