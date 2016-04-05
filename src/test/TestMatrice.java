package test;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import sources.*;
public class TestMatrice extends Matrice {
	
	public void testBalayage(List<Segment> s){
		assertEquals(ToutesLesPaires(s), Balayage(s));
	}
	
	@Test
	public void test() {
		List<Segment> lesS = new ArrayList<Segment>();
		//lesS.add(new Segment(new Point(1, 1), new Point(2, 4)));
		//lesS.add(new Segment(new Point(1, -2), new Point(4, 1)));
		//lesS.add(new Segment(new Point(4, 4), new Point(2, 8)));
		
		lesS.add(new Segment(new Point(1, 1), new Point(2, 5)));
		lesS.add(new Segment(new Point(6, -2), new Point(4, 1)));
		lesS.add(new Segment(new Point(1, 9), new Point(6, 4)));
		lesS.add(new Segment(new Point(-10, 4), new Point(0, 8)));
		lesS.add(new Segment(new Point(3, 2), new Point(5, 8)));
		lesS.add(new Segment(new Point(9, 3), new Point(-2, 3)));
		lesS.add(new Segment(new Point(-3, 4), new Point(3, 10)));
		lesS.add(new Segment(new Point(7, 4), new Point(1, 12)));
		lesS.add(new Segment(new Point(7, 6), new Point(-6, 8)));
		lesS.add(new Segment(new Point(-2, 4), new Point(0, 10)));
		lesS.add(new Segment(new Point(5, 4), new Point(1, 2)));
		
		testBalayage(lesS);
	}

}

