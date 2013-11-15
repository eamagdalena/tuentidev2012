package tuenti.p17;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ingredient {

	Point center = new Point();

	List<Segment> segments = new ArrayList<Segment>();

	public Ingredient(BufferedReader br, int edges) throws IOException{
		String split[] = br.readLine().split(" ");

		center.x = Double.parseDouble(split[0]);
		center.y = Double.parseDouble(split[1]);

		Segment segment = new Segment();
		segments.add(segment);

		segment.x1 = center.x;
		segment.y1 = center.y;
		segment.x2 = Double.parseDouble(split[2]);
		segment.y2 = Double.parseDouble(split[3]);

		//TODO: calcular todos los vertices del poligono
		MathUtils.getVertexSegments(segments, center, edges  + 1);

	}

	public boolean validate(Rect r) {
		/* Comprobar si la recta de la pizza se corta con cada uno de los segmentos del poligono */
		for(Segment	s : segments){

			Point p = MathUtils.cutPoint(s, r);

			if(p == null){
				continue;
			}

			if(MathUtils.isInside(p, s)){
				return false;
			}

		}

		return true;
	}

}
