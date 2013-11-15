package tuenti.p16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;

public class Problema16 {
	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Problema16 p = new Problema16();

		p.R = Integer.parseInt(br.readLine());
		p.U = Integer.parseInt(br.readLine());
		p.N = Integer.parseInt(br.readLine());

		for(int i = 0; i < p.R; i++){
			p.all.add(new Report(br.readLine(), true));
		}

		for(int i = 0; i < p.U; i++){
			Report r = new Report(br.readLine(), false);
			p.suspicious.add(r);
		}

		p.solve();
		br.close();

	}

	private void solve() {

		double res = 0;

		NeuralNetwork net = new MultiLayerPerceptron(N, R, 1);

		TrainingSet<SupervisedTrainingElement> trainingSet = new TrainingSet<SupervisedTrainingElement>(N, 1);

		for(Report r : all){
			trainingSet.addElement(new SupervisedTrainingElement(r.vector, new double[]{r.malware?1.0D:0.0D}));
		}

		net.learn(trainingSet);

		for(Report r : suspicious){
			net.setInput(r.vector);
			net.calculate();

			if(net.getOutput()[0] >= 0.5D){
				res+=r.sum();
			}

		}

		System.out.println((long)res);

	}

	int R,U,N;

	List<Report> all = new ArrayList<Report>();
	List<Report> suspicious = new ArrayList<Report>();

}
