package tuenti.p17;

public class Segment {
	double x1,y1,x2,y2;

	public Rect toRect(){

		Rect r = new Rect();

		r.m = (y1 - y2) / (x1 - x2);

		r.b = y1 - r.m * x1;

		return r;
	}

	@Override
	public String toString() {

		return "("+x1+","+y1+") - ("+x2+","+y2+")";
	}

}
