package sources;

/**
 * Classe modelisant un Point
 * 
 * @author ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO
 */
public class Point {

	// attributs
	private int x, y;

	/**
	 * constructeur
	 * 
	 * @param x
	 *            coordonee en x
	 * @param y
	 *            coordonee en y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// getteur
	public int getX() {
		return x;
	}

	// setteur
	public void setX(int x) {
		this.x = x;
	}

	// getteur
	public int getY() {
		return y;
	}

	// setteur
	public void setY(int y) {
		this.y = y;
	}

	// methode pour l'egalite
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	// affichage
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}
