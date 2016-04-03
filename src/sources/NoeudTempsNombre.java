package sources;

public class NoeudTempsNombre {
	private int nombreDeSegments;
	private long temps;
	
	
	
	public NoeudTempsNombre(int nombreDeSegments, long temps) {
		this.nombreDeSegments = nombreDeSegments;
		this.temps = temps;
	}
	public int getNombreDeSegments() {
		return nombreDeSegments;
	}
	public void setNombreDeSegments(int nombreDeSegments) {
		this.nombreDeSegments = nombreDeSegments;
	}
	public long getTemps() {
		return temps;
	}
	public void setTemps(long temps) {
		this.temps = temps;
	}
	
	
}
