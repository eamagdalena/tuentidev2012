package tuenti.p17;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngredientType {

	public IngredientType(BufferedReader br) throws IOException{
		String split[] = br.readLine().split(" ");
		id = split[0];
		edges = Integer.parseInt(split[1]);
		quantity = Integer.parseInt(split[2]);

		if(quantity % 2 != 0){
			throw new InvalidPizzaException("Non pair number of ingredients");
		}

		for(int i = 0 ; i < quantity; i++){
			instances.add(new Ingredient(br, edges));
		}

	}

	boolean validate(Rect rect){

		int left= 0, right = 0;

		for(Ingredient ing : instances){
			if(MathUtils.isLeft(ing.center, rect)){
				left++;
			}else{
				right++;
			}
		}

		if(left != right){
			return false;
		}

		for(Ingredient ing : instances){

			if(!ing.validate(rect)){
				return false;
			}
		}

		return true;
	}

	List<Ingredient> instances = new ArrayList<Ingredient>();

	String id;

	int edges;

	int quantity;

}
