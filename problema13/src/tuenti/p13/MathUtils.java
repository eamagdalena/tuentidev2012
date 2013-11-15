package tuenti.p13;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MathUtils {

	public static BigInteger mcm(int[] l){

		List<Integer> list = new ArrayList<Integer>();
		list.add(l[0]);

		if(l[0] != l[1]){
			list.add(l[1]);
		}

		BigInteger mcm = mcm(l[0], new BigInteger("" + l[1]));

		for(int i = 2; i < l.length - 1; i++){

			if(list.contains(l[i])){
				continue;
			}

			list.add(l[i]);

			mcm = mcm(l[i],mcm);
		}

		return mcm;
	}

	public static BigInteger mcm(int _a, BigInteger mcm) {

		if(_a == 0){
			return mcm;
		}

		BigInteger a = new BigInteger(String.valueOf(_a));

		return a.divide(mcm.gcd(a)).multiply(mcm);

	}
}
