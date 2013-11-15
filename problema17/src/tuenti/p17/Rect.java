package tuenti.p17;

public class Rect {

	public Rect() {

	}

	// y = m * x + b

	public Rect(Point p, double m){
		this.m = m;
		b = p.y - m * p.x;
	}

	double m, b;
}
