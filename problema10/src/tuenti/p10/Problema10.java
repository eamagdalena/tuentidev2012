package tuenti.p10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problema10 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;

		while(null != (s = br.readLine())){
			if(DEBUG && s.equals(".")){
				break;
			}

			try{
				solve(s);
			}catch(Exception e){
				System.out.println("ERROR");
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}

	private final static String MIRROR = "mirror";
	private final static String SUBSTRACT = "$";
	private final static String ADD = "@";
	private final static String FIRE = "fire";
	private final static String CONQUER = "conquer";
	private final static String CR = ".";
	private final static String CLONE = "breadandfish";
	private final static String MULT = "#";
	private final static String DIV = "&";
	private final static String DANCE = "dance";

	private static void solve(String sx) {
		String split[] = sx.split(" ");

		Stack<Integer> args = new Stack<Integer>();

		Integer a1, a2;

		for(String s : split){
			/* Numeros */
			try{
				args.push(Integer.parseInt(s));
				continue;
			}catch(NumberFormatException e){

			}

			/* Operadores */
			if(s.equals(CR)){
				System.out.println(args.pop());
				return;
			}

			if(s.equals(MIRROR)){
				args.push(args.pop() * -1);
			}else if(s.equals(SUBSTRACT)){
				args.push(args.pop() * -1 + args.pop());
			}else if(s.equals(DANCE)){
				a1 = args.pop();
				a2 = args.pop();
				args.push(a1);
				args.push(a2);
			}else if(s.equals(CONQUER)){
				a2 = args.pop();
				args.push(args.pop() % a2);
			}else if(s.equals(FIRE)){
				args.pop();
			}else if(s.equals(CLONE)){
				args.push(args.peek());
			}else if(s.equals(MULT)){
				args.push(args.pop() * args.pop());
			}else if(s.equals(ADD)){
				args.push(args.pop() + args.pop());
			}else if(s.equals(DIV)){
				a2 = args.pop();
				args.push(args.pop() / a2);
			}

		}
	}

}
