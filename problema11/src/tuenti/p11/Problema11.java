package tuenti.p11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problema11 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;

		Problema11 p11 = new Problema11();

		p11.index();

		int i = Integer.parseInt(br.readLine());

		for(int j = 0 ; j < i ; j++){
			s = br.readLine();
			try{
				p11.solve(s);
			}catch(Exception e){
				System.out.println("ERROR"); //Si falla este caso hay que ser capaz de seguir con el siguiente
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		br.close();

	}

	private HashMap<String, List<String>> index = new HashMap<String, List<String>>();

	private void index() throws IOException{

		BufferedReader br = new BufferedReader(new FileReader("/descrambler_wordlist.txt"));

		String s,ss;

		List<String> list;


		while(null != (s = br.readLine())){

			list = index.get(ss = s.substring(0, 2));

			if(list == null){
				list = new ArrayList<String>();
				index.put(ss, list);
			}

			list.add(s);

		}

		br.close();

	}

	class Word{
		String word = "";
		int score = 0;

		@Override
		public String toString() {
			return word + " " + score;
		}

	}

	private void solve(String s) {
		String split[] = s.split(" ");
		solve(split[1], split[0]);
	}

	private void solve(String board, String rack){
		Word bestWord  = new Word();

		a: for(int i = 0; i < board.length(); i++){

			for(int j = 0; j < i ; j++){
				if(board.charAt(i) == board.charAt(j)){
					continue a;
				}
			}

			bestWord = solve(board.charAt(i), rack, bestWord);

		}

		System.out.println(bestWord);
	}

	private Word solve(char boardChar, String rack, Word bestWord){

		rack+=boardChar;

		int rl = rack.length(), wl;
		String ss;
		List<String> list;
		int score;

		for(int i = 0; i < rl; i++){
			for(int j = 0; j < rl; j++){
				if(i == j){
					continue;
				}

				ss = rack.charAt(i) + "" + rack.charAt(j);

				list = index.get(ss);

				if(list != null){
					w: for(String word : list){

						/* Mnemotecnicos de descarte */
						/* 1 */
						if(word.indexOf(boardChar) == -1){
							continue;
						}

						wl = word.length();

						/* 2 */
						for(int k = 2; k < wl; k++){
							if(rack.indexOf(word.charAt(k)) == -1){
								continue w;
							}
						}

						/* Hacemos la exploracion definitiva por si hubiera requerimientos de letras repetidas */
						if(isReallyGoodWord(rack, word)){
							score = scoreWord(word);

							if(score > bestWord.score){
								bestWord = new Word();
								bestWord.score = score;
								bestWord.word = word;
							}else if(score == bestWord.score && bestWord.word.compareTo(word) > 0){
								bestWord.word = word;
							}

						}
					}
				}
			}
		}

		return bestWord;
	}

	private boolean isReallyGoodWord(String rack, String word){
		StringBuffer sb = new StringBuffer(rack);

		int ix;

		for(int i = 0; i < word.length(); i++){
			ix = sb.indexOf("" + word.charAt(i));

			if(ix == -1) return false;

			sb.deleteCharAt(ix);

		}

		return true;
	}

	private int scoreWord(String word){

		int score = 0;

		for (int i = 0; i < word.length(); i++){

			switch(word.charAt(i)){
			case 'A':
			case 'E':
			case 'I':
			case 'L':
			case 'N':
			case 'O':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
				score++;
				break;
			case 'D':
			case 'G':
				score+=2;
				break;
			case 'B':
			case 'C':
			case 'M':
			case 'P':
				score+=3;
				break;
			case 'F':
			case 'H':
			case 'V':
			case 'W':
			case 'Y':
				score+=4;
				break;
			case 'K':
				score+=5;
				break;
			case 'J':
			case 'X':
				score+=8;
				break;
			case 'Q':
			case 'Z':
				score+=10;
			}
		}

		return score;
	}
}
