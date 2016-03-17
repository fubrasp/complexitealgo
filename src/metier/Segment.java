package metier;

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
	
	public String coordonnees_PointD(){
		return "Point D => X : " + pointD.getX() + " Y : " + pointD.getY();
	}
	
	public String coordonnees_PointG(){
		return "Point G => X : " + pointG.getX() + " Y : " + pointG.getY();
	}
	
	public String coordonneesCompletesSegment(){
		return this.coordonnees_PointG() + " | " + this.coordonnees_PointD();
	}
	
	public int compareTo(Object o) {
		   if(o.getClass().equals(Segment.class)){
		      Segment s = (Segment)o;
		      Integer xG_1 = (Integer)this.getPointG().getX();
		      Integer xG_2 = (Integer)s.getPointG().getX();
		      Integer xD_1 = (Integer)this.getPointD().getX();
		      Integer xD_2 = (Integer)s.getPointD().getX();
		      
		      if (xG_1.compareTo(xG_2) == 0) return xD_1.compareTo(xD_2);
		      return xG_1.compareTo(xG_2);
		   }
		   return -1;
	}
}