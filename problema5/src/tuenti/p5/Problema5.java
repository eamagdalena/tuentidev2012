package tuenti.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Problema5 {

	private static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;

		while(null != (s = br.readLine())){

			try{
				solve(s);
			}catch(Exception e){
				System.out.println("ERROR");
				if(DEBUG) e.printStackTrace();
			}

		}

		br.close();

	}

	private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private static void solve(String input) throws Exception{
		String s[] = input.split(" - ");
		solve(SDF.parse(s[0].trim()), SDF.parse(s[1].trim()));
	}

	private static void solve(Date from, Date to){
		int transcurso = (int) (to.getTime() / 1000 - from.getTime() / 1000);
		TheClock theClock = new TheClock();
		TheOtherClock theOtherClock = new TheOtherClock();
		int tviejo = theClock.getTotalLeds(transcurso);
		int tnuevo = theOtherClock.getTotalLedsOn(transcurso);
		System.out.println((tviejo - tnuevo));

	}

}
