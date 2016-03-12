import java.util.ArrayList;
import java.util.List;

public class Matrice {
	public int n, m;
	public int mat[][];
	private boolean error;

	/**
	 * Constructeur Matrice vide
	 */
	public Matrice() {
	}

	public static Matrice[] genererMatrices(Segment s1, Segment s2) {
		Matrice[] mats = new Matrice[2];
		int p1X = s1.getPointG().getX();
		int p1Y = s1.getPointG().getY();

		int p2X = s1.getPointD().getX();
		int p2Y = s1.getPointD().getY();

		int p3X = s2.getPointG().getX();
		int p3Y = s2.getPointG().getY();

		int p4X = s2.getPointD().getX();
		int p4Y = s2.getPointD().getY();

		String[] mat = new String[] { "3", "3", Integer.toString(p1X), Integer.toString(p1Y), "1",
				Integer.toString(p2X), Integer.toString(p2Y), "1", Integer.toString(p3X), Integer.toString(p3Y), "1" };
		String[] mat2 = new String[] { "3", "3", Integer.toString(p1X), Integer.toString(p1Y), "1",
				Integer.toString(p2X), Integer.toString(p2Y), "1", Integer.toString(p4X), Integer.toString(p4Y), "1" };

		Matrice matrice1 = new Matrice(mat);
		Matrice matrice2 = new Matrice(mat2);

		mats[0] = matrice1;
		mats[1] = matrice2;

		return mats;
	}

	
	public static ArrayList<Segment> genererSegmentsAleatoire(int nombreSegments, int numJeuTest) {
		ArrayList<Segment> arrSegmentsAleatoires = new ArrayList<Segment>();
		Segment segmentCourant;
		Point pG, pD;
		int VARATION_INTERVAL = nombreSegments+1;
		int x1int, y1int, x2int, y2int;
		Double x1, x2, y1, y2;
		Point p1, p2;

		switch (numJeuTest) {
		case 1:
			System.out.println("OK_je suis dans1");
			// Il s'agit de tirer au hasard des segments avec des sommets
			// compris dans 0 n (nombre de segments)
			// phrase incompréhensible au sens strict --> on daube l'aletoire la
			// (la région du plan dans lequel se répartissent les n segments
			// grandit avec le nombre n de segments).
			
			for (int i = 0; i < nombreSegments; i++) {
				
				x1 = Math.random() * VARATION_INTERVAL;
				y1 = Math.random() * VARATION_INTERVAL;
				x2 = Math.random() * VARATION_INTERVAL;
				y2 = Math.random() * VARATION_INTERVAL;
				
				x1int = x1.intValue();
				y1int = y1.intValue();
				x2int = x2.intValue();
				y2int = y2.intValue();
				p1 = new Point(x1int, y1int);
				p2 = new Point(x2int, y2int);

				arrSegmentsAleatoires.add(new Segment(p1, p2));
			}
			break;
		case 2:
			System.out.println("OK_je suis dans2");
			// Il s'agit de tirer au hasard des segments avec des sommets
			// compris dans 0 n (nombre de segments)
			// phrase incomprehensible au sens strict --> on daube l'aletoire la
			// (la région du plan dans lequel se répartissent les n segments
			// grandit avec le nombre n de segments).
			for (int i = 0; i < nombreSegments; i++) {

				x1 = Math.random() * VARATION_INTERVAL;
				y1 = Math.random() * VARATION_INTERVAL;
				// si on veut des segments de largeur 1 et de hauteur 1 on ne
				// peut pas toucher a la pente de manière aleatoire puisque
				// celle ci influe sur la huteur et la largeur
				// avoir hauteur et largeur a 1 on a le choix entre 4 oordonnees
				Double alea = Math.random();
				int switchVal = alea.intValue();
				// on decoupe en 4
				// on fait au moins une foit la boucle pour eviter le cas ou les
				// coordonnees de x2 seraient negatives
				//do {
					if (switchVal < 0.25) {
						x2 = x1 - 1.0;
						y2 = y1 + 1.0;
					} else {
						if ((switchVal > 0.25) && (switchVal < 0.5)) {
							x2 = x1 + 1.0;
							y2 = y1 + 1.0;
						} else {
							if ((switchVal > 0.5) && (switchVal < 0.75)) {
								x2 = x1 + 1.0;
								y2 = y1 - 1.0;
							} else {
								x2 = x1 - 1.0;
								y2 = y1 - 1.0;
							}
						}
					}
				//} while ((x2 <= 0));
				x1int = x1.intValue();
				y1int = y1.intValue();
				x2int = x2.intValue();
				y2int = y2.intValue();
				p1 = new Point(x1int, y1int);
				p2 = new Point(x2int, y2int);

				arrSegmentsAleatoires.add(new Segment(p1, p2));
			}
			break;
		case 3:
			System.out.println("OK_je suis dans3");
			// On reduit l'interval a racine de n
			double nbSegsDb = (double) nombreSegments;
			System.out.println(nbSegsDb);
			double sqrtResN = Math.sqrt(nbSegsDb);
			System.out.println(sqrtResN);
			VARATION_INTERVAL = (int) Math.round(sqrtResN);
			System.out.println(VARATION_INTERVAL);

			for (int i = 0; i < nombreSegments; i++) {

			// Il s'agit de tirer au hasard des segments avec des sommets
			// compris dans 1 racine n
			// la heuteur et la largeur sont aleatoire mais la pente est proche
			// de 1
			// on commence par obtenir une variation de pente dans l'interval
			// -1,1 pour la pente
			// Que veut dire plus ou moins 1!
			double pente = 0;
			double alea2 = Math.random();
			if (alea2 < 0.33) {
				pente = -1.0;
			} else {
				if ((alea2 > 0.33) && (alea2 < 0.66)) {
					pente = 0.0;
				} else {
					pente = 1.0;
				}
			}
			// le sens de la question est tres difficile a comprendre: les deux
			// points doivent forcement etre lies a la pente!!
			// On choisit donc d'utiliser la pente comme une fonction lineaire
			// meme remarque concernant l'aleatoire
			// a partir de la pente On peut determiner les coordonnees des deux
			// points qu'on choisit de maniere aleatoire sur la pente (droite)
			// tel que f(valeur)=coeffPente*valeur

			//les do while sont la pour eviter les depassement d'intervale
			//do {
				x1 = Math.random() * VARATION_INTERVAL;
				y1 = x1 * pente;
			//} while ((x1 <= 1));

			//do {
				x2 = Math.random() * VARATION_INTERVAL;
				y2 = x2 * pente;
			//} while ((x2 <= 1));

			x1int = x1.intValue();
			y1int = y1.intValue();
			x2int = x2.intValue();
			y2int = y2.intValue();
			p1 = new Point(x1int, y1int);
			p2 = new Point(x2int, y2int);

			arrSegmentsAleatoires.add(new Segment(p1, p2));
			}
			break;
		}
		// ArrayList contenant des segements tires au hasard suivant le jeu
		// desire i ou ii ou iii
		return arrSegmentsAleatoires;
	}
	
	public static int ToutesLesPaires(ArrayList<Segment> lesSegments) {
		int rangSegmentCourant = 0;
		int intersections = 0;
		Matrice[] matrices = new Matrice[2];

		while (rangSegmentCourant < lesSegments.size()) {
			for (int i = rangSegmentCourant + 1; i < lesSegments.size(); i++) {
				matrices = genererMatrices(lesSegments.get(rangSegmentCourant), lesSegments.get(i));
				if ((matrices[0].determinant() < 0 && matrices[1].determinant() < 0)
						|| (matrices[0].determinant() > 0 && matrices[1].determinant() > 0)) {
					System.out.println("Non sécantes");
				} else {
					System.out.println("Sécantes");
					intersections++;
				}			
			}
			rangSegmentCourant++;
		}

		return intersections;
	}

	public static Segment trouverSegAvecXGaucheMinimum(ArrayList<Segment> s){
		Segment tmp = s.get(0);
		int min = s.get(0).getPointG().getX();
		
		for (int i = 1; i < s.size(); i++){
			if (min > s.get(i).getPointG().getX()){
				min = s.get(i).getPointG().getX();
				tmp = s.get(i);
			}
		}
		
		return tmp;
	}
	
	public static Segment[] Balayage(ArrayList<Segment> s) {
		Segment[] tableauTrie = new Segment[s.size()];
		int i = 0;
		
		while (!s.isEmpty()){
			Segment segmentMin = trouverSegAvecXGaucheMinimum(s);
			tableauTrie[i] = segmentMin;
			s.remove(segmentMin);
			i++;
		}
		
		return tableauTrie;
	}

	/**
	 * Constructeur Matrice n*m (int*int)
	 */
	public Matrice(int a, int b) {
		n = a;
		m = b;
		mat = new int[n][m];
		initialiser();
	}

	/**
	 * Constructeur Matrice n*m + valeurs (args)
	 */
	public Matrice(String[] args) {
		n = Integer.parseInt(args[0]);
		m = Integer.parseInt(args[1]);
		mat = new int[n][m];
		if (args.length == (n * m) + 2) {
			int k = 0;
			int i = 0;
			while (i != n) {
				int j = 0;
				while (j != m) {
					mat[i][j] = Integer.parseInt(args[k + 2]);
					k++;
					j++;
				}
				i++;
			}
		} else { // si le nombre de parametre n'est pas egal a la taille de la
					// matrice on remplit de 0
			initialiser();
		}
	}

	/**
	 * Initialise la matrice avec des 0
	 */
	public void initialiser() {
		int i = 0;
		while (i != n) {
			int j = 0;
			while (j != m) {
				mat[i][j] = 0;
				j++;
			}
			i++;
		}
	}

	/**
	 * Affiche la matrice
	 */
	public void afficher() {
		int i = 0;
		while (i != n) {
			int j = 0;
			while (j != m) {
				System.out.print(mat[i][j] + "\t");
				j++;
			}
			System.out.println("");
			i++;
		}
	}

	/**
	 * Calcul le determinant de la matrice si elle est carr??e.
	 */
	public int determinant() {
		if (estCarree()) {
			return determinant(this);
		} else {
			error = true;
			return 0;
		}
	}

	/**
	 * renvoi le determinant d'une matrice
	 */
	public int determinant(Matrice matrice) {
		if (matrice.n == 2 && matrice.m == 2) {
			return (matrice.mat[0][0] * matrice.mat[1][1] - matrice.mat[0][1] * matrice.mat[1][0]);
		} else {
			int det = 0;
			int i = 0;
			while (i != matrice.n) {
				String[] args = new String[2 + ((matrice.n - 1) * (matrice.m - 1))];
				args[0] = "" + (matrice.n - 1);
				args[1] = "" + (matrice.m - 1);
				int j = 0;
				int l = 2;
				while (j != matrice.n) {
					int k = 1;
					while (k != matrice.m) {
						if (j != i) {
							args[l] = "" + matrice.mat[j][k];
							l++;
						}
						k++;
					}
					j++;
				}
				Matrice newMatrice = new Matrice(args);
				if (i % 2 != 0) {
					det += -1 * matrice.mat[i][0] * determinant(newMatrice);
				} else {
					det += matrice.mat[i][0] * determinant(newMatrice);
				}
				i++;
			}
			return det;
		}
	}

	/**
	 * Calcul la trace de la matrice si elle est carr??e.
	 */
	public int trace() {
		if (estCarree()) {
			int tra = 0;
			int i = 0;
			while (i != n) {
				tra += mat[i][i];
				i++;
			}
			return tra;
		} else {
			error = true;
			return 0;
		}
	}

	/**
	 * Renvoi vraisi la matrice est carr??e n == m
	 */
	public boolean estCarree() {
		return n == m;
	}

	/**
	 * Calcul la transposer de la matrice
	 */
	public Matrice transposer() {
		String[] args = new String[2 + (n * m)];
		args[0] = "" + (m);
		args[1] = "" + (n);
		int j = 0;
		int l = 2;
		while (j != m) {
			int i = 0;
			while (i != n) {
				args[l] = "" + mat[i][j];
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
	public Matrice produit(Matrice matrice) {
		if (n == matrice.m) {
			String[] args = new String[2 + (n * matrice.m)];
			args[0] = "" + (n);
			args[1] = "" + (matrice.m);
			int i = 0;
			int a = 2;
			while (i != n) {
				int j = 0;
				while (j != matrice.m) {
					int b = 0;
					int k = 0;
					while (k != m) {
						b += mat[i][k] * matrice.mat[k][j];
						k++;
					}
					args[a] = "" + b;
					a++;
					j++;
				}
				i++;
			}
			return new Matrice(args);
		} else {
			error = true;
			return new Matrice();
		}
	}

	/**
	 * Main Exemple : java Matrice 3 3 1 2 3 4 5 6 7 8 9 Matrice 3 * 3 avec pour
	 * valeur 1 2 3 4 5 6 7 8 9
	 */
	public static void main(String args[]) {
		String[] mat = new String[] { "3", "3", "1", "1", "1", "2", "4", "1", "1", "-2", "1" };
		Matrice matrice = new Matrice(mat);

		System.out.println("-------------*** Test transposée, déterminant, matrice, etc. ***--------------");
		System.out.println("La matrice est : ");
		matrice.afficher();

		// determinant
		matrice.error = false;
		int a = matrice.determinant();
		if (matrice.error) {
			System.out.println("Determinant : La matrice n'est pas carr??e!!!");
		} else {
			System.out.println("Determinant : " + a);
		}

		// trace
		matrice.error = false;
		a = matrice.trace();
		if (matrice.error) {
			System.out.println("Trace : La matrice n'est pas carrée!!!");
		} else {
			System.out.println("Trace : " + a);
		}

		// Transposer
		Matrice tmatrice = matrice.transposer();
		System.out.println("La transposer de la matrice est : ");
		tmatrice.afficher();

		/*
		 * ------ Test de la fonction ToutesLesPaires(ArrayList<Segment>) ------
		 */
		
		System.out.println("-------------*** Test fonction : ToutesLesPaires() ***--------------");
		ArrayList<Segment> lesS = new ArrayList<Segment>();
		lesS.add(new Segment(new Point(1, 1), new Point(2, 4)));
		lesS.add(new Segment(new Point(1, -2), new Point(4, 1)));
		lesS.add(new Segment(new Point(1, 1), new Point(2, 4)));
		lesS.add(new Segment(new Point(-1, 4), new Point(0, 8)));
		
		System.out.println("k vaut " + ToutesLesPaires(lesS));
		
		/*System.out.println("-------------*** Test fonction : genererSegmentsAleatoire(100, 1) ***--------------");
		ArrayList<Segment> centSuiv1 = Matrice.genererSegmentsAleatoire(10, 1);
		
		System.out.println("-------------*** Test fonction : genererSegmentsAleatoire(100, 2) ***--------------");
		ArrayList<Segment> centSuiv2 = Matrice.genererSegmentsAleatoire(10, 2);
		
		
		System.out.println("-------------*** Test fonction : genererSegmentsAleatoire(100, 3) ***--------------");
		ArrayList<Segment> centSuiv3 = Matrice.genererSegmentsAleatoire(10, 3);
		
		System.out.println("Liste avec i");
		for(Segment s1:centSuiv1){
			System.out.println(s1.coordonneesCompletesSegment());
		}
		
		System.out.println("Liste avec ii");
		for(Segment s1:centSuiv2){
			System.out.println(s1.coordonneesCompletesSegment());
		}
		
		System.out.println("Liste avec iii");
		for(Segment s1:centSuiv3){
			System.out.println(s1.coordonneesCompletesSegment());
		}*/
		
	}

}