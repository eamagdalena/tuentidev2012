package tuenti.p4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problema4 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s,s2;

		int i = Integer.parseInt(br.readLine());

		for(int j = 0 ; j < i ; j++){
			s = br.readLine();
			s2 = br.readLine();
			try{
				solve(s,s2);
			}catch(Exception e){
				System.out.println("ERROR"); //Si falla este caso hay que ser capaz de seguir con el siguiente
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}

	private static void solve(String s, String s2) {
		String[] sp = s.split(" ");
		String[] sp2 = s2.split(" ");

		int[] grupos = new int[Integer.parseInt(sp[2])];

		int i = 0;

		for(String sg : sp2){
			grupos[i++] = Integer.parseInt(sg);
		}

		solve(Integer.parseInt(sp[0]),
				Integer.parseInt(sp[1]),
				grupos);

	}

	private static void solve(int races, int karts, int[] groups){

		int currentRacers = 0;
		int kartsUsed = 0;

		int raceFirstGroup =0, raceNextGroup = 0;

		for(int i = 0; i < races; i++){

			raceFirstGroup = raceNextGroup;

			/* Elegimos corredores */
			while (karts - currentRacers >= groups[raceNextGroup]){
				currentRacers+=groups[raceNextGroup];
				raceNextGroup++;

				if(raceNextGroup >= groups.length){
					raceNextGroup = 0;
				}

				if(raceNextGroup == raceFirstGroup){
					break;
				}

			}

			/* Corremos */
			kartsUsed+=currentRacers;

			/* Limpiamos */
			currentRacers = 0;

		}

		System.out.println(kartsUsed);

	}

}
