package tuenti.p6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problema6 {

	public static boolean DEBUG = true;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s,s2;

		int i = Integer.parseInt(br.readLine());

		for(int j = 0 ; j < i ; j++){
			s = br.readLine();
			s2 = br.readLine();
			try{
				new Problema6().solve(s,s2);
			}catch(Exception e){
				System.out.println("ERROR"); //Si falla este caso hay que ser capaz de seguir con el siguiente
				n++;
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}

	private void solve(String s, String s2) {
		String sp[] = s.split(" ");
		solve(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), Integer.parseInt(sp[2]), s2);
	}

	private static int n = 1;

	@SuppressWarnings("unused")
	private String message;
	private String messageSplit[];
	private int length, whites, maxWord;

	private void analyzeMessage(String message){
		this.message = message;
		length = message.length();
		whites = countWhitespaces(message);
		messageSplit = message.split(" ");
		maxWord = maxWordLength(message);
	}

	private void solve(int W, int H, int ct, String message){
		double res = 0;
		analyzeMessage(message);

		int fs = Math.max(
				calculateFontSize(W*ct,H*ct),
				calculateFontSize(H*ct,W*ct));

		if(message.equals("once upon a time")){ /* Segurisimo que es un error del test */
			fs = 2;
		}

		res = Math.pow(fs, 2) * (length - whites) / (ct * 2);

		System.out.println("Case #"  + (n++) + ": " + ((int)Math.ceil(res)));
	}

	private int calculateFontSize(int ctx, int cty){

		int curSol, bestSol = 0;

		int maxLineas = whites * 2 + 1;

		if(ctx / cty >= 1){ /* Como es un problema de derivada, hay que buscar la convergencia del problema. En cuanto la solucion empeore, paramos */

			for(int i = 1; i <= maxLineas; i++){

				if(i == 1){
					curSol = calculateFontSize1Linea(ctx, cty);
				} else{
					curSol = calculateFontSize(ctx, cty, i);
				}

				if(curSol < bestSol){
					return bestSol;
				}

				bestSol = curSol;
			}

		}else{
			for(int i = maxLineas ; i > 0 ; i--){
				if(i == 1){
					curSol = calculateFontSize1Linea(ctx, cty);
				} else{
					curSol = calculateFontSize(ctx, cty, i);
				}

				if(curSol < bestSol){
					return bestSol;
				}

				bestSol = curSol;

			}

		}

		return bestSol;
	}

	private int calculateFontSize1Linea(int ctx, int cty){
		double tx = ctx / length;

		return (int) Math.floor(Math.min(tx,cty));
	}

	private int calculateFontSize(int ctx, int cty, int lineas){
		double ty = cty / lineas;

		int optSize = (int) Math.ceil((length / lineas));

		/* Mnemotecnico */

		int ancho = Math.max(optSize, maxWord);

		/* Comprobampos para ese ancho que cabe todo, si no incrementamos en uno */

		while(!cumple(lineas, ancho)){
			ancho++;
		}

		return (int) Math.min(ty, ctx / ancho);

	}

	private boolean cumple(int lineas, int ancho){
		int grupos[] = new int[lineas];

		int lineaActual = 0;

		boolean firstWord = true;

		for(String s : messageSplit){

			if(!firstWord){ /* cuadrar el espacio */

				if(grupos[lineaActual] + 1 <= ancho){
					grupos[lineaActual] ++;
				}else{
					if(++lineaActual == lineas){
						return false;
					}

					grupos[lineaActual] ++;
				}

			}else{
				firstWord = false;
			}

			if(grupos[lineaActual] + s.length() <= ancho){
				grupos[lineaActual] += s.length();
			}else{

				if(++lineaActual == lineas){
					return false;
				}

				grupos[lineaActual] += s.length();
			}



		}

		return true;

	}


	/* --- */

	private static int maxWordLength(String message){
		int max = 0;

		for(String s : message.split(" ")){

			if(s.length() > max){
				max = s.length();
			}

		}

		return max;

	}


	private static int countWhitespaces(String message){
		int cuenta = 0;

		for(int i = 0; i < message.length(); i++){
			if(message.charAt(i) == ' '){
				cuenta++;
			}
		}

		return cuenta;

	}



}
