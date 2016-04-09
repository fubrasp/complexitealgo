package sources;

/**
 * Classe modelisant un objet associant un nombre de segments et un nombre de
 * paires de segments qui sont secants
 * 
 * @author ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO
 */
public class NoeudNombreSegSecants {

	// attributs
	private int nombreDeSegments;
	private long nombreDePaireSecantes;

	/**
	 * Constructeur permetant de creer un objet associant un nombre n de
	 * segments, a un nombre de paires de segments secants
	 * 
	 * @param nombreDeSegments
	 *            nombre de segments
	 * @param nombreDePaireSecantes
	 *            nombre de paires de segments secants
	 */
	public NoeudNombreSegSecants(int nombreDeSegments, long nombreDePaireSecantes) {
		this.nombreDeSegments = nombreDeSegments;
		this.nombreDePaireSecantes = nombreDePaireSecantes;
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
	public long getNombreDePaireSecantes() {
		return nombreDePaireSecantes;
	}

	// setteur
	public void setNombreDePaireSecantes(long nombreDePaireSecantes) {
		this.nombreDePaireSecantes = nombreDePaireSecantes;
	}
}
