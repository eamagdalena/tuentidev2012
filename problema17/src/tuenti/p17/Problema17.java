package tuenti.p17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Problema17 {
	public static boolean DEBUG = false;
	private static int n = 1;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int i = Integer.parseInt(br.readLine());

		for(int j = 0 ; j < i ; j++){
			try{
				System.out.println("Case #" + (n++) + ": " + (new Problema17(br).solve()?"TRUE":"FALSE"));
			}catch(InvalidPizzaException e){
				System.out.println("Case #" + (n) + ": FALSE");
				if(DEBUG){
					e.printStackTrace();
				}
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

	Point center = new Point();

	double R;
	int N;

	List<IngredientType> ingredients = new ArrayList<IngredientType>();

	public Problema17(BufferedReader br) throws IOException{
		String split[] = br.readLine().split(" ");
		center.x = Double.parseDouble(split[0]);
		center.y = Double.parseDouble(split[1]);
		R = Double.parseDouble(split[2]);

		N = Integer.parseInt(br.readLine());

		for(int i = 0; i < N; i++){
			ingredients.add(new IngredientType(br));
		}

	}

	private final static double RESOLUTION = 1000;

	private boolean solve() {

		double iters = Math.PI * R * RESOLUTION;

		double base = 1 /  RESOLUTION;

		a: for(double d = 1 ; d < iters; d++){

			if(d%10 == 0){
				base *= 10;
			}

			Rect rect = new Rect(center, (1 + d % 10) * base);

			for(IngredientType ingredient : ingredients){
				if(!ingredient.validate(rect)){
					continue a;
				}
			}

			return true;
		}

		return false;

	}

}
