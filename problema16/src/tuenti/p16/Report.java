package tuenti.p16;

public class Report {

	double[] vector;

	boolean malware;

	public Report(String s, boolean scanType){
		String split[] = s.split(" ");

		if(scanType){
			malware = split[0].equals("M");
			vector = new double[split.length-1];
			for(int i = 0; i < vector.length; i++){
				vector[i] = Double.parseDouble(split[i+1]);
			}
		}else{
			vector = new double[split.length];
			for(int i = 0; i < vector.length; i++){
				vector[i] = Double.parseDouble(split[i]);
			}
		}


	}

	public double sum() {
		double res = 0.0D;

		for(int i=0; i<vector.length;i++){
			res+=vector[i];
		}

		return res;
	}

}
