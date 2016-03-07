import java.util.ArrayList;

public class Matrice{
	public int n,m;	
	public int mat[][];
	private boolean error;

	/**
	 * Constructeur Matrice vide
	 */
	public Matrice(){

	}
	
	public static Matrice[] genererMatrices(Segment s1, Segment s2){
		Matrice[] mats = new Matrice[2];
		int p1X = s1.getPointG().getX();
		int p1Y = s1.getPointG().getY();
		
		int p2X = s1.getPointD().getX();
		int p2Y = s1.getPointD().getY();
		
		int p3X = s2.getPointG().getX();
		int p3Y = s2.getPointG().getY();
		
		int p4X = s2.getPointD().getX();
		int p4Y = s2.getPointD().getY();
		
		String[] mat = new String[]{"3","3",Integer.toString(p1X),Integer.toString(p1Y),"1",Integer.toString(p2X),Integer.toString(p2Y),"1",Integer.toString(p3X),Integer.toString(p3Y),"1"};
		String[] mat2 = new String[]{"3","3",Integer.toString(p1X),Integer.toString(p1Y),"1",Integer.toString(p2X),Integer.toString(p2Y),"1",Integer.toString(p4X),Integer.toString(p4Y),"1"};
		
		Matrice matrice1 = new Matrice(mat);
		Matrice matrice2 = new Matrice(mat2);
		
		mats[0] = matrice1;
		mats[1] = matrice2;
		
		return mats;
	}
	
	public Segment[] genererSegmentsAleatoire(int nombreSegments) {
		Segment tabSegmentsAleatoires[] = new Segment[nombreSegments];
		Segment segmentCourant;
		Point pG, pD;
		int VARATION_INTERVAL = 2000000000;
		Double variationSigne[] = new Double[4];
		int x1int, y1int, x2int, y2int;
		Double x1, x2, y1, y2;
		Point p1, p2;
		for (int i = 0; i < tabSegmentsAleatoires.length; i++) {

			for (int j = 0; j < variationSigne.length; j++)
				if (Math.random() >= 0.5) {
					variationSigne[j] = 1.0;
				} else {
					variationSigne[j] = -1.0;
				}
			x1 = Math.random() * (Math.random() * VARATION_INTERVAL) * variationSigne[0];
			y1 = Math.random() * (Math.random() * VARATION_INTERVAL) * variationSigne[1];
			x2 = Math.random() * (Math.random() * VARATION_INTERVAL) * variationSigne[2];
			y2 = Math.random() * (Math.random() * VARATION_INTERVAL) * variationSigne[3];

			x1int = x1.intValue();
			y1int = x1.intValue();
			x2int = x1.intValue();
			y2int = x1.intValue();
			p1 = new Point(x1int, y1int);
			p2 = new Point(x2int, y2int);

			tabSegmentsAleatoires[i] = new Segment(p1, p2);
		}
		return tabSegmentsAleatoires;

	}
	
    public static int ToutesLesPaires(ArrayList<Segment> lesSegments){
    	int rangSegmentCourant = 0;
    	int intersections = 0;
    	Segment s = lesSegments.get(rangSegmentCourant);
    	Matrice[] matrices = new Matrice[2];
    	for (int i = rangSegmentCourant+1; i < lesSegments.size(); i++){
    		matrices = genererMatrices(s, lesSegments.get(i));
    		if ((matrices[0].determinant() < 0 && matrices[1].determinant() < 0) ||
    				(matrices[0].determinant() > 0 && matrices[1].determinant() > 0)){
    			System.out.println("Non sécantes");
    		}else{
    			System.out.println("Sécantes");
    			intersections++;
    		}
    	}
    	
    	return intersections;
    }
    
	/**
	 * Constructeur Matrice n*m (int*int)
	 */
	public Matrice(int a, int b){
		n = a;
		m = b;
		mat = new int[n][m];
		initialiser();
	}

	/**
	 * Constructeur Matrice n*m + valeurs (args)
	 */
	public Matrice(String[] args){
		n = Integer.parseInt(args[0]);
		m = Integer.parseInt(args[1]);
		mat = new int[n][m];
		if(args.length == (n*m)+2){
			int k = 0;
			int i = 0;
			while(i != n){
				int j = 0;
				while(j != m){
					mat[i][j] = Integer.parseInt(args[k+2]);
					k++;					
					j++;
				}
				i++;
			}
		}
		else{ // si le nombre de parametre n'est pas egal a la taille de la matrice on remplit de 0
			initialiser();
		}
	}

	/**
	 * Initialise la matrice avec des 0
	 */
	public void initialiser(){
		int i = 0;
		while(i != n){
			int j = 0;
			while(j != m){
				mat[i][j] = 0;
				j++;
			}
			i++;
		}
	}


	/**
	 * Affiche la matrice
	 */
	public void afficher(){
		int i = 0;
		while(i != n){
			int j = 0;
			while(j != m){
				System.out.print(mat[i][j]+"\t");
				j++;
			}
			System.out.println("");
			i++;
		}
	}
	

	/**
	 * Calcul le determinant de la matrice si elle est carr��e.
	 */
	public int determinant(){
		if(estCarree()){
			return determinant(this);
		}
		else{
			error = true;
			return 0;
		}
	}


	/**
	 * renvoi le determinant d'une matrice
	 */
	public int determinant(Matrice matrice){
		if(matrice.n == 2 && matrice.m == 2){
			return (matrice.mat[0][0] * matrice.mat[1][1] - matrice.mat[0][1] * matrice.mat[1][0]);
		}
		else{
			int det = 0;
			int i = 0;
			while(i != matrice.n){
				String[] args = new String[2+((matrice.n-1)*(matrice.m-1))];
				args[0] = ""+(matrice.n-1);
				args[1] = ""+(matrice.m-1);
				int j = 0;
				int l = 2;
				while(j != matrice.n){
					int k = 1;
					while(k != matrice.m){
						if(j != i){
							args[l] = ""+matrice.mat[j][k];
							l++;
						}
						k++;
					}
					j++;
				}
				Matrice newMatrice = new Matrice(args);
				if(i % 2 != 0){
					det += -1*matrice.mat[i][0]*determinant(newMatrice);
				}
				else{
					det += matrice.mat[i][0]*determinant(newMatrice);
				}
				i++;
			}
			return det;
		}
	}

	/**
	 * Calcul la trace de la matrice si elle est carr��e.
	 */
	public int trace(){
		if(estCarree()){
			int tra = 0;
			int i = 0;
			while(i != n){
				tra += mat[i][i];
				i++;
			}
			return tra;
		}
		else{
			error = true;
			return 0;
		}
	}


	/**
	 * Renvoi vraisi la matrice est carr��e n == m
	 */
	public boolean estCarree(){
		return n == m;
	}


	/**
	 * Calcul la transposer de la matrice
	 */
	public Matrice transposer(){
		String[] args = new String[2+(n*m)];
		args[0] = ""+(m);
		args[1] = ""+(n);
		int j = 0;
		int l = 2;
		while(j != m){
			int i = 0;
			while(i != n){
				args[l] = ""+mat[i][j];
				l++;
				i++;
			}
			j++;
		}
		return new Matrice(args);
	}

	/**
	 * Calcul la transposer de la matrice
	 */
	public Matrice produit(Matrice matrice){
		if(n == matrice.m){
			String[] args = new String[2+(n*matrice.m)];
			args[0] = ""+(n);
			args[1] = ""+(matrice.m);
			int i = 0;
			int a = 2;
			while(i != n){
				int j = 0;
				while(j != matrice.m){
					int b = 0;
					int k = 0;
					while(k != m){
						b += mat[i][k]*matrice.mat[k][j];
						k++;
					}
					args[a] = ""+b;
					a++;
					j++;
				}
				i++;
			}
			return new Matrice(args);
		}
		else{
			error = true;
			return new Matrice();
		}
	}


	/**
	 * Main
	 * Exemple : java Matrice 3 3 1 2 3 4 5 6 7 8 9
	 *	     Matrice 3 * 3 avec pour valeur 1 2 3
	 *					    4 5 6
	 *					    7 8 9
	 */
	public static void main(String args[]){
		String[] mat = new String[]{"3","3","1","1","1","2","4","1","1","-2","1"};
		Matrice matrice = new Matrice(mat);
		
		System.out.println("La matrice est : ");
		matrice.afficher();
	
		// determinant
		matrice.error = false;
		int a = matrice.determinant();
		if(matrice.error){
			System.out.println("Determinant : La matrice n'est pas carr��e!!!");
		}
		else{
			System.out.println("Determinant : "+a);
		}

		// trace
		matrice.error = false;
		a = matrice.trace();
		if(matrice.error){
			System.out.println("Trace : La matrice n'est pas carrée!!!");
		}
		else{
			System.out.println("Trace : "+a);
		}

		// Transposer
		Matrice tmatrice = matrice.transposer();
		System.out.println("La transposer de la matrice est : ");
		tmatrice.afficher();
		
		/* ------ Test de la fonction ToutesLesPaires(ArrayList<Segment>) ------ */
		ArrayList<Segment> lesS = new ArrayList<Segment>();
		lesS.add(new Segment(new Point(1, 1), new Point(2, 4)));
		lesS.add(new Segment(new Point(1, -2), new Point(4, 1)));
		lesS.add(new Segment(new Point(1, 1), new Point(2,4)));
		
		int i = ToutesLesPaires(lesS);
		System.out.println("Retour de la fonction => " + i);
	}

}