package sources;

/**
 * Classe modelisant un segment constitue de deux extremites qui sont des point
 * @author ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO
 */
public class Segment {
	
	//attributs
	//point droit
	private Point pointD;
	//point gauche
	private Point pointG;
	
	/**
	 * Constructeur de Segment, effectue aussi la verification point gauche, point droit
	 * @param p1 premier point 
	 * @param p2 deuxieme point
	 */
	public Segment(Point p1, Point p2){
		//on ordonne en fonction de la coordonee x..
		if (p1.getX() > p2.getX()){
			this.setPointG(p2);
			this.setPointD(p1);
		}else{
			this.setPointG(p1);
			this.setPointD(p2);
		}
	}

	//getteur
	public Point getPointD() {
		return pointD;
	}

	//setteur
	public void setPointD(Point pointD) {
		this.pointD = pointD;
	}

	//getteur
	public Point getPointG() {
		return pointG;
	}

	//setteur
	public void setPointG(Point pointG) {
		this.pointG = pointG;
	}
	
	//affichage droit
	public String coordonnees_PointD(){
		return "D => X : " + pointD.getX() + " Y : " + pointD.getY();
	}
	
	//affichage point gauche
	public String coordonnees_PointG(){
		return "G => X : " + pointG.getX() + " Y : " + pointG.getY();
	}
	
	//affichage segment
	public String print(){
		return "Segment[" + this.coordonnees_PointG() + ", " + this.coordonnees_PointD() + "]";
	}
	
	//methode de comparaison
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