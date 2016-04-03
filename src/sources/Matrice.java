package sources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jfree.ui.RefineryUtilities;


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

	public static double retournerSignePositifOuNegatif(){
		double res = Math.random();
		if(res<0.5){
			return -1.0;
		}else{
			return 1.0;
		}
	}
	
	public static boolean calculPenteOK(double x1, double y1, double x2, double y2){
		double result = (y2-y1)/(x2-x1);
		if((result<=1.5)&&(result>=-1.5)){
			return true;
		}else{
			return false;
		}
	}
	
	public static ArrayList<Segment> genererSegmentsAleatoire(int nombreSegments, int numJeuTest) {
		ArrayList<Segment> arrSegmentsAleatoires = new ArrayList<Segment>();
		Segment segmentCourant;
		Point pG, pD;
		int VARATION_INTERVAL = nombreSegments+1;
		int MAX_INTERVAL_Y = 2000000000;
		int x1int, y1int, x2int, y2int;
		Double x1, x2, y1, y2;
		Point p1, p2;

		switch (numJeuTest) {
		case 1:
			// Il s'agit de tirer au hasard des segments avec des sommets
			// compris dans 0 n (nombre de segments)
			// phrase incompréhensible au sens strict --> on daube l'aletoire la
			// (la région du plan dans lequel se répartissent les n segments
			// grandit avec le nombre n de segments).
			
			for (int i = 0; i < nombreSegments; i++) {
				
				x1 = Math.random() * VARATION_INTERVAL;
				//y1 = Math.random() * MAX_INTERVAL_Y * Matrice.retournerSignePositifOuNegatif();
				y1 = Math.random() * VARATION_INTERVAL;
				x2 = Math.random() * VARATION_INTERVAL;
				//y2 = Math.random() * MAX_INTERVAL_Y * Matrice.retournerSignePositifOuNegatif();
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
				Double switchVal = Math.random();
				System.out.println(switchVal);
				// on decoupe en 4
				// on fait au moins une foit la boucle pour eviter le cas ou les
				// coordonnees de x2 seraient negatives
				//do {
				boolean res= false;
					if ((switchVal < 0.25)&&!res) {
						x2 = x1 - 1.0;
						y2 = y1 + 1.0;
						res=true;
					} else {
						if (((switchVal >= 0.25) && (switchVal < 0.5))&&!res) {
							x2 = x1 + 1.0;
							y2 = y1 + 1.0;
							res=true;
						} else {
							if (((switchVal >= 0.5) && (switchVal < 0.75))&&!res) {
								x2 = x1 + 1.0;
								y2 = y1 - 1.0;
								res=true;
							} else {
								if((switchVal>=0.75)&&!res){
								}
								x2 = x1 - 1.0;
								y2 = y1 - 1.0;
								res=true;
							}
						}
					}
			
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
			// On reduit l'interval a racine de n
			double nbSegsDb = (double) nombreSegments;
			double sqrtResN = Math.sqrt(nbSegsDb);
			System.out.println("RACINE DE N "+sqrtResN);
			VARATION_INTERVAL = (int) Math.round(sqrtResN);

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
			/*if (alea2 < 0.33) {
				pente = 0.0;
			} else {
				if ((alea2 > 0.33) && (alea2 < 0.66)) {
					pente = 0.5;
				} else {
					pente = 1.0;
				}
			}*/
			// le sens de la question est tres difficile a comprendre: les deux
			// points doivent forcement etre lies a la pente!!
			// On choisit donc d'utiliser la pente comme une fonction lineaire
			// meme remarque concernant l'aleatoire
			// a partir de la pente On peut determiner les coordonnees des deux
			// points qu'on choisit de maniere aleatoire sur la pente (droite)
			// tel que f(valeur)=coeffPente*valeur

			//les do while sont la pour eviter les depassement d'intervale
				
				x1 = Math.random() * VARATION_INTERVAL;
				//y1 = x1 * pente;
				y1= Math.random() * VARATION_INTERVAL;

				
				//x2 = Math.random() * VARATION_INTERVAL;
				x2 = x1 + Math.random() * sqrtResN * Matrice.retournerSignePositifOuNegatif();
				y2 = y1 + Math.random() * sqrtResN * Matrice.retournerSignePositifOuNegatif();
				//y2 = x2 * pente;
				
				//test de la pente plus ou moins un

				while(!Matrice.calculPenteOK(x1, y1, x2, y2)){
					x2 = x1 + Math.random() * sqrtResN * Matrice.retournerSignePositifOuNegatif();
					y2 = y1 + Math.random() * sqrtResN * Matrice.retournerSignePositifOuNegatif();
				}
				
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
				System.out.println("NOMBRE DE SEGMENTS POUR LE JEU _"+numJeuTest+"_ DE TEST: "+nombreSegments);
				return arrSegmentsAleatoires;

	}

	/** 
	 * Methode dont le but est de permettre d'evaluer
	 * le temps de calcul des algorithmes
	 * renvoie des coordonnes pondere avec les 3 jeux de test
	 * on a la fin deux courbes avec des points moyen, on considère chaque jeu de tests separemment
	 * On renvoie un nombre donne de segments, avec le temps mis pour faire le balayage ou toutesLesPaires   
	 * @param ArrayList<Segment> jeuDeTest
	 * @return
	 */
	public static ArrayList<ArrayList<NoeudTempsNombre>> estimerTemps(ArrayList<ArrayList<Segment>> liste){
		long startTime;
		long elapsedTimeNs;
		int nombre_de_segments;
		ArrayList<ArrayList<NoeudTempsNombre>> result = new ArrayList<ArrayList<NoeudTempsNombre>>();
		ArrayList<NoeudTempsNombre> balayage=new ArrayList<NoeudTempsNombre>();
		ArrayList<NoeudTempsNombre> toutesLesPaires=new ArrayList<NoeudTempsNombre>();
		for (ArrayList<Segment> arrayList : liste) {
			nombre_de_segments=arrayList.size();
			startTime = System.nanoTime();
			Matrice.Balayage(arrayList);
			elapsedTimeNs = System.nanoTime() - startTime;
			balayage.add(new NoeudTempsNombre(nombre_de_segments, elapsedTimeNs));
			
			startTime = System.nanoTime();
			Matrice.ToutesLesPaires(arrayList);
			elapsedTimeNs = System.nanoTime() - startTime;
			toutesLesPaires.add(new NoeudTempsNombre(nombre_de_segments, elapsedTimeNs));
		}
		result.add(balayage);
		result.add(toutesLesPaires);
		return result;
	}

	
	public static int ToutesLesPaires(List<Segment> s) {
		int rangSegmentCourant = 0; 
		int intersections = 0; 
		Matrice[] m = new Matrice[2]; 
		Matrice[] m2 = new Matrice[2];
		
		while (rangSegmentCourant < s.size()) {
			for (int i = rangSegmentCourant + 1; i < s.size(); i++) {
				m = genererMatrices(s.get(rangSegmentCourant), s.get(i)); 
				m2 = genererMatrices(s.get(i), s.get(rangSegmentCourant));
				if ((m[0].determinant() * m[1].determinant() < 0) && (m2[0].determinant() * m2[1].determinant() < 0)) intersections++;
			}
			rangSegmentCourant++;
		}

		return intersections; 
	}

	
	public static int Balayage(List<Segment> s) {		
		Collections.sort(s, new Comparator<Segment>(){
			public int compare(Segment s1, Segment s2){
				 Integer xGauche_1 = (Integer)s1.getPointG().getX(); 
		         Integer xGauche_2 = (Integer)s2.getPointG().getX(); 
		         int result = xGauche_1.compareTo(xGauche_2); 
		         
		         if (result == 0) return s1.compareTo(s2); 
		         return result;
			}
		});
		
		int k = 0;
		Matrice[] m = new Matrice[2];
		Matrice[] m2 = new Matrice[2];
		
		for (int i = 0; i < s.size(); i++){
			for (int j = i+1; j < s.size(); j++){
				if (s.get(i).getPointD().getX() >= s.get(j).getPointG().getX()){
					m = genererMatrices(s.get(i), s.get(j)); 
					m2 = genererMatrices(s.get(j), s.get(i));
					if ((m[0].determinant() * m[1].determinant() < 0) && (m2[0].determinant() * m2[1].determinant() < 0)) k++; 
				}
			}
		}
		return k;
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
		/*
		 * ------ Test des deux fonctions ------
		 */
		
		System.out.println("*** Test fonction : Balayage() et ToutesLesPaires");
		ArrayList<Segment> lesS = new ArrayList<Segment>();
		lesS.add(new Segment(new Point(1, 1), new Point(2, 5)));
		lesS.add(new Segment(new Point(1, -2), new Point(4, 1)));
		lesS.add(new Segment(new Point(1, 4), new Point(2, 4)));
		lesS.add(new Segment(new Point(-1, 4), new Point(0, 8)));
		lesS.add(new Segment(new Point(3, 4), new Point(5, 8)));
		lesS.add(new Segment(new Point(2, 3), new Point(-2, 3)));
		lesS.add(new Segment(new Point(-2, 4), new Point(0, 10)));
		lesS.add(new Segment(new Point(6, 4), new Point(1, 8)));
		lesS.add(new Segment(new Point(4, 4), new Point(6, 10)));
		lesS.add(new Segment(new Point(2, 6), new Point(-6, 8)));
		lesS.add(new Segment(new Point(-2, 4), new Point(0, 12)));
		lesS.add(new Segment(new Point(5, 4), new Point(1, 9)));
		
		System.out.println("NOMBRE DE SEGMENTS: "+lesS.size());
		System.out.println("BALAYAGE nombre: "+Balayage(lesS)+" intersections");
		System.out.println("ToutesLesPaires nombre : "+ToutesLesPaires(lesS)+" intersections");
		 
		  //XYLineChart_AWT chart = new XYLineChart_AWT("Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO", "Visualisation des segments ", lesS);
		  ArrayList<Segment> tesT= Matrice.genererSegmentsAleatoire(200, 3);	
		  XYLineChart_AWT chart = new XYLineChart_AWT("Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO", "Visualisation des segments ", tesT);
		  for (Segment segment : tesT) {
			  System.out.println("PT_G(X): "+segment.getPointG().getX()+"PT_G(Y): "+segment.getPointG().getY());
			  System.out.println("PT_D(X): "+segment.getPointD().getX()+"PT_D(Y): "+segment.getPointD().getY());
			  System.out.println("");
		  }
		  chart.pack( );          
	      RefineryUtilities.centerFrameOnScreen( chart );          
	      chart.setVisible( true ); 
	      
	      ArrayList<ArrayList<Segment>> liste=new ArrayList<ArrayList<Segment>>();
			liste.add(Matrice.genererSegmentsAleatoire(10, 1));
			liste.add(Matrice.genererSegmentsAleatoire(20, 1));
			liste.add(Matrice.genererSegmentsAleatoire(30, 1));
			liste.add(Matrice.genererSegmentsAleatoire(40, 1));
			liste.add(Matrice.genererSegmentsAleatoire(50, 1));
			liste.add(Matrice.genererSegmentsAleatoire(60, 1));
			liste.add(Matrice.genererSegmentsAleatoire(80, 1));
			liste.add(Matrice.genererSegmentsAleatoire(90, 1));
			liste.add(Matrice.genererSegmentsAleatoire(100, 1));
			liste.add(Matrice.genererSegmentsAleatoire(120, 1));
			liste.add(Matrice.genererSegmentsAleatoire(140, 1));
			liste.add(Matrice.genererSegmentsAleatoire(160, 1));
			liste.add(Matrice.genererSegmentsAleatoire(180, 1));
			liste.add(Matrice.genererSegmentsAleatoire(200, 1));
			liste.add(Matrice.genererSegmentsAleatoire(250, 1));
			liste.add(Matrice.genererSegmentsAleatoire(300, 1));
			liste.add(Matrice.genererSegmentsAleatoire(350, 1));
			liste.add(Matrice.genererSegmentsAleatoire(400, 1));
			liste.add(Matrice.genererSegmentsAleatoire(450, 1));
			liste.add(Matrice.genererSegmentsAleatoire(500, 1));
			/*
			liste.add(Matrice.genererSegmentsAleatoire(600, 1));
			liste.add(Matrice.genererSegmentsAleatoire(700, 1));
			liste.add(Matrice.genererSegmentsAleatoire(800, 1));
			liste.add(Matrice.genererSegmentsAleatoire(900, 1));
			liste.add(Matrice.genererSegmentsAleatoire(1000, 1));
			liste.add(Matrice.genererSegmentsAleatoire(1200, 1));
			liste.add(Matrice.genererSegmentsAleatoire(1400, 1));
			liste.add(Matrice.genererSegmentsAleatoire(1600, 1));
			liste.add(Matrice.genererSegmentsAleatoire(1800, 1));
			liste.add(Matrice.genererSegmentsAleatoire(2000, 1));
			*/
	      
	  	ArrayList<ArrayList<NoeudTempsNombre>> retourDonnees= Matrice.estimerTemps(liste);
		  XYLineTIMEChart_AWT chart2 = new XYLineTIMEChart_AWT("Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO", "Visualisation de la performance des algorithmes", retourDonnees);
		  chart2.pack( );          
	      RefineryUtilities.centerFrameOnScreen(chart2);          
	      chart2.setVisible( true );
	      
	}

}