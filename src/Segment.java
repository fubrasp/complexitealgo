
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
	
	public void coordonnees_PointD(){
		System.out.println("Abscisse : " + pointD.getX() + "Ordonnée : " + pointD.getY());
	}
	
	public void coordonnees_PointG(){
		System.out.println("Abscisse : " + pointG.getX() + "Ordonnée : " + pointG.getY());
	}
}