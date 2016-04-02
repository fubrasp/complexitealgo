package sources;


import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Repere extends JPanel{
	private double graduationBaseX = 1;
	private double graduationBaseY = 1;

	private double graduationMaxX = 10;
	private double graduationMaxY = 10;
	
	private ArrayList<Segment> segments = new ArrayList<Segment>();

	public void initialisation(){
		//tracer les 2 reperes et les garduer
	}
	@Override 
    public void paintComponent(Graphics g){ 
		Point p1 = new Point(2, 2);
		Point p2 = new Point(3, 3);
		
		Segment s1 = new Segment(p1, p2);
        for (Segment current : this.segments) { 
            g.drawLine(s1.getPointG().getX(), s1.getPointG().getY(), s1.getPointD().getX(), s1.getPointD().getY());
        } 
    } 
}