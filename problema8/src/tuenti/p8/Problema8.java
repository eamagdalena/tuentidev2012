package tuenti.p8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Problema8 {

	public static boolean DEBUG = true;

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
			new Problema8().solve(lista);
		}catch(Exception e){
			System.out.println("ERROR");
			if(DEBUG){
				e.printStackTrace();
			}
		}

	}

	void solve(List<String> lista){

		try{

			BufferedReader reader = new BufferedReader(new StringReader(lista.remove(0)));

			int i = 0;

			for(String s : lista){

				String[] rules = new String[100];

				for(String sx : s.split(",")){
					String[] sxy = sx.split("=>");

					rules[sxy[0].charAt(0) - 65] = sxy[1];
				}

				if(i != 0){
					reader = new BufferedReader(new FileReader("tmp" + i));
				}

				i++;

				BufferedWriter writer = new BufferedWriter(new FileWriter("tmp" + i));

				solve(reader, writer, rules);

			}

			try {
				System.out.println(MD5Checksum.getMD5Checksum("tmp" + i));
			} catch (NoSuchAlgorithmException e) {
				System.out.println(e.getMessage());
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	void solve(BufferedReader br, BufferedWriter bw, String[] rules) throws IOException{
		String aux;

		char[] buf = new char[50000];

		int rd;

		while((rd = br.read(buf)) != -1){

			for(int i = 0; i < rd; i++){
				aux = rules[buf[i] - 65];
				if(aux != null){
					bw.append(aux);
				} else{
					bw.append(buf[i]);
				}
			}
		}

		br.close();
		bw.close();
	}
}