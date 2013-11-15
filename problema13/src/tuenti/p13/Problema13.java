package tuenti.p13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Problema13 {

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
				n++;
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}
	private static int n = 1;

	private static void solve(String s) {
		String sp[] = s.split(" ");
		System.out.println("Case #" + (n++) + ": " + solve(Integer.parseInt(sp[0]), Integer.parseInt(sp[1])));
	}

	private static BigInteger solve(int N, int L){
		int dif = N - 2*L;

		if(L == 0 || L == 1 || L == N || N - 2*L == 0){
			return new BigInteger("2");
		}

		if(dif > 0){
			return solve1(N, L);
		}

		return solve2(N, L);
	}

	private static BigInteger solve1(int N, int L) {
		int[] sim = round( getArray(N), L);
		int[] target = getArray(N);
		int[] rules = computeRules(target, sim);
		return solve(rules);
	}

	private static BigInteger solve2(int N, int L){
		int[] sim = round2(getArray(N), L);
		int[] target = getArray(N);
		int[] rules = computeRules(target, sim);
		return solve(rules);
	}

	private static int[] getArray(int n){
		int[] res = new int[n];

		for(int i = 0; i < n; i++){
			res[i] =  i+1;
		}

		return res;
	}


	/* Calculamos el minimo comun multiplo de las iteraciones para conseguir que todas las cartas vuelvan a su sitio */
	private static BigInteger solve(int[] rules){
		int iters[] = new int[rules.length];

		int pos = 0;

		for(int i = 0; i < rules.length; i++){
			if(rules[i] == 0){
				iters[i] = 1;
				continue;
			}

			pos = i;

			do{
				iters[i]++;
				pos = pos + rules[pos];
			}while(pos != i);

		}

		return MathUtils.mcm(iters);
	}

	private static int[] computeRules(int[] initial, int[] sim){
		int rules[] = new int[sim.length];

		for(int i = 0; i < sim.length; i++){
			rules[i] = indexOf(initial[i], sim) - i;
		}

		return rules;
	}

	private static int indexOf(int n, int[] lista){
		for(int i = 0 ; i < lista.length; i++){
			if(lista[i] == n){
				return i;
			}
		}

		throw new RuntimeException("Number not found??");
	}

	private static int[] round(int[] sim, int L){
		int[] res = new int[sim.length];

		int ct = 0;
		for(int i = 1; i <= sim.length - L; i++){

			if(i <= L){
				res[ct++] = sim[L - i];
			}

			res[ct++] = sim[sim.length - i];
		}

		return res;
	}

	private static int[] round2(int[] sim, int L){
		int[] res = new int[sim.length];

		int M = sim.length - L;

		int ct = 0;
		for(int i = 1; i <= L; i++){
			res[ct++] = sim[L - i];

			if(i <= M){
				res[ct++] = sim[sim.length - i];
			}
		}
		return res;
	}

	//	private static boolean equals(int[] s1, int[] s2){
	//		for(int i = 0 ; i < s1.length; i++){
	//			if(s1[i] != s2[i]){
	//				return false;
	//			}
	//		}
	//
	//		return true;
	//	}
	//
	//	@SuppressWarnings("unused")
	//	private static int solveBruteForce(int N, int L) {
	//		int[] sim = getArray1(N);
	//		int[] target = getArray1(N);
	//
	//		int i = 0;
	//
	//		do{
	//			i++;
	//			sim = round(sim, L);
	//		}while(!equals(sim, target));
	//
	//		return i;
	//	}

	//	private static int solveBruteForce2(int N, int L) {
	//		int[] sim = getArray1(N);
	//		int[] target = getArray1(N);
	//
	//		int i = 0;
	//
	//		do{
	//			i++;
	//			sim = round2(sim, L);
	//		}while(!equals(sim, target));
	//
	//		return i;
	//	}

}
