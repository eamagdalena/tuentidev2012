package tuenti.p9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problema9 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;

		int i = Integer.parseInt(br.readLine());

		Problema9 p9 = new Problema9();

		for(int j = 0 ; j < i ; j++){
			s = br.readLine();
			try{
				p9.push(s);
			}catch(Exception e){
				System.out.println("ERROR"); //Si falla este caso hay que ser capaz de seguir con el siguiente
				if(DEBUG){
					e.printStackTrace();
				}
			}
		}

		p9.index();
		p9.solve();

		br.close();

	}

	private class Search{
		Search(String word, int ocurrence) {
			this.word = word;
			this.ocurrence = ocurrence;
		}

		String word;
		int ocurrence;
	}

	private List<Search> search = new ArrayList<Search>();

	private void push(String s){

		String split[] = s.split(" ");
		diccionario.put(split[0].toLowerCase(), new DocumentInfo());
		search.add(new Search(split[0],  Integer.parseInt(split[1]) - 1));
	}

	private void solve() {

		for(Search entry: search){
			DocumentInfo di = diccionario.get(entry.word);

			if(di == null){
				System.out.println("ERROR");
			}

			System.out.println(di.occurrences.get(entry.ocurrence).toString());
		}
	}

	private HashMap<String, DocumentInfo> diccionario = new HashMap<String, DocumentInfo>();

	private void index() throws Exception{
		String line, word;
		DocumentInfo di;
		String split[];
		int nl;
		for(int i = 1 ; i <= 800; i++){
			BufferedReader br = new BufferedReader(new FileReader("/documents/" + String.format("%04d", i)));
			nl = 0;

			while(null!= (line = br.readLine())){
				nl++;
				split = line.split(" ");

				for(int j=0; j<split.length;j++){

					word = split[j].toLowerCase();

					di = diccionario.get(word);

					if(di == null){
						continue;
					}

					di.occurrences.add(new DocumentInfo.Ocurrence(i,nl,j+1));

				}

			}

		}
	}

}
