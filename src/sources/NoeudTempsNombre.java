package sources;

/**
 * Classe modelisant un objet associant un nombre de segments et une duree de
 * realisation d'un algorithme donne suivant un cas donnee
 * 
 * @author ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO
 */
public class NoeudTempsNombre {

	// attributs
	private int nombreDeSegments;
	private long temps;

	/**
	 * Constructeur permetant de creer un objet associant associant un nombre de
	 * segments et une duree
	 * 
	 * @param nombreDeSegments
	 * @param temps
	 */
	public NoeudTempsNombre(int nombreDeSegments, long temps) {
		this.nombreDeSegments = nombreDeSegments;
		this.temps = temps;
	}

	// getteur
	public int getNombreDeSegments() {
		return nombreDeSegments;
	}

	// setteur
	public void setNombreDeSegments(int nombreDeSegments) {
		this.nombreDeSegments = nombreDeSegments;
	}

	// getteur
	public long getTemps() {
		return temps;
	}

	// setteur
	public void setTemps(long temps) {
		this.temps = temps;
	}
}
