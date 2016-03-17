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
		lesS.add(new Segment(new Point(1, 1), new Point(2, 4)));
		lesS.add(new Segment(new Point(1, -2), new Point(4, 1)));
		lesS.add(new Segment(new Point(4, 4), new Point(2, 8)));
		
		testBalayage(lesS);
	}

}

