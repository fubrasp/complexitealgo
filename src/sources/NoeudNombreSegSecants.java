package sources;

public class NoeudNombreSegSecants {
	private int nombreDeSegments;
	private long nombreDePaireSecantes;
	
	
	public NoeudNombreSegSecants(int nombreDeSegments, long nombreDePaireSecantes) {
		this.nombreDeSegments = nombreDeSegments;
		this.nombreDePaireSecantes = nombreDePaireSecantes;
	}


	public int getNombreDeSegments() {
		return nombreDeSegments;
	}


	public void setNombreDeSegments(int nombreDeSegments) {
		this.nombreDeSegments = nombreDeSegments;
	}


	public long getNombreDePaireSecantes() {
		return nombreDePaireSecantes;
	}


	public void setNombreDePaireSecantes(long nombreDePaireSecantes) {
		this.nombreDePaireSecantes = nombreDePaireSecantes;
	}	
}
