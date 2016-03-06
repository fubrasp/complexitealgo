
public class Segment {
	private Point pointD;
	private Point pointG;
	
	public Segment(Point p1, Point p2){
		this.setPointD(p1);
		this.setPointG(p2);
	}

	public Point getPointD() {
		return pointD;
	}

	public void setPointD(Point pointD) {
		this.pointD = pointD;
	}

	public Point getPointG() {
		return pointG;
	}

	public void setPointG(Point pointG) {
		this.pointG = pointG;
	}
	
}