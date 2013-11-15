package tuenti.p17;

import java.util.List;

public class MathUtils {

	public static Point cutPoint(Segment s, Rect r){
		Rect rs = s.toRect();

		if(rs.m == r.m){
			return null;
		}

		Point p = new Point();

		p.x = (rs.b - r.b) / (rs.m - r.m);
		p.y = rs.m * p.x + rs.b;

		return p;

	}

	public static boolean isInside(Point p, Segment s){
		return ( p.x >= s.x1 && p.x <= s.x2 || p.x <= s.x1 && p.x >= s.x2);
	}

	public static boolean isLeft(Point p, Rect r){

		double yp = r.m * p.x + r.b;

		return p.y < yp;

	}

	public static void getVertexSegments(List<Segment> segments, Point center, int N){
		Segment s0 = segments.get(0);

		double radio=Math.sqrt(Math.pow(s0.x2-center.x,2)+Math.pow(s0.y2-center.y,2));
		double angle = Math.atan2(s0.y2-center.y,s0.x2-center.x);

		for (int i=1;i<N;i++){
			double angleV=angle+2*i*Math.PI/N;

			Segment s = new Segment();
			s.x1 = center.x;
			s.y1 = center.y;
			s.x2 = radio * Math.cos(angleV) + s.x1;
			s.y2 = radio * Math.sin(angleV) + s.y1;

			segments.add(s);
		}

	}

}
