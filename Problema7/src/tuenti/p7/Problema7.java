package tuenti.p7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Problema7 {
	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;
		ArrayList<String> lista = new ArrayList<String>();
		while(null != (s = br.readLine())){
			if(DEBUG && s.equals(".")){
				break;
			}

			lista.add(s);
		}

		br.close();

		try{
			new Problema7().solve(lista);
		}catch(Exception e){
			System.out.println("ERROR");
			if(DEBUG){
				e.printStackTrace();
			}
		}

	}

	private List<StringBuffer> solutions = new ArrayList<StringBuffer>();

	private ArrayList<Character> columns[];

	private void solve(ArrayList<String> input) {

		calculateColumns(input);

		solveProblem(columns, new StringBuffer());

		paintSolutions();

	}

	private void solveProblem( ArrayList<Character> columns[], StringBuffer partial){
		List<Character> chars = new ArrayList<Character>();

		a: for(Character c : columns[0]){
			if(chars.contains(c)){
				continue;
			}

			for(int i = 1 ; i < columns.length; i++){
				if(columns[i].contains(c)){
					continue a;
				}
			}

			chars.add(c);
		}

		if(chars.size() == 0){
			solutions.add(partial);
			return;
		}

		if(chars.size() == 1){
			partial.append(chars.get(0));
			removeCharacter(columns, chars.get(0));
			solveProblem(columns, partial);
			return;
		}

		for(Character c : chars){
			StringBuffer partialX = new StringBuffer(partial);

			ArrayList<Character> columnsX[] = cloneProblemColumnsStatus(columns);

			partialX.append(c);
			removeCharacter(columnsX, c);
			solveProblem(columnsX, partialX);
		}

	}

	@SuppressWarnings("unchecked")
	private ArrayList<Character>[] cloneProblemColumnsStatus(ArrayList<Character> columns[]){
		ArrayList<Character> columnsX[] = new ArrayList[columns.length];

		int i = 0;
		for(ArrayList<Character> al : columns){
			ArrayList<Character> alclone = new ArrayList<Character>();
			alclone.addAll(al);
			columnsX[i++] = alclone;
		}

		return columnsX;
	}

	private void removeCharacter(ArrayList<Character> columns[], Character c){

		int ix;

		while((ix =columns[0].indexOf(c)) != -1){

			columns[0].remove(ix);

			for(int i = 1 ; i < columns.length; i++){
				columns[i-1].add(columns[i].remove(ix));
			}

			columns[columns.length - 1].add(null);

		}
	}


	@SuppressWarnings("unchecked")
	private void calculateColumns(ArrayList<String> input){
		columns = new ArrayList[input.get(0).length()];

		for(int i = 0 ; i < columns.length; i++){
			columns[i] = new ArrayList<Character>();

			for(String s : input){
				columns[i].add(s.charAt(i));
			}
		}
	}

	private void paintSolutions(){
		Collections.sort(solutions, new Comparator<StringBuffer>() {
			@Override
			public int compare(StringBuffer o1, StringBuffer o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});

		for(StringBuffer s : solutions){
			System.out.println(s);
		}
	}

}
