package sources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;

/**
 * Classe modelisant une Matrice
 * 
 * @author ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO
 */
public class Matrice {

	// attributs

	// n ligne(s), m colone(s)
	public int n, m;
	// represente la matrice
	public int mat[][];
	// indicateur d'erreur
	private boolean error;

	/**
	 * Constructeur Matrice vide
	 */
	public Matrice() {
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
	 * Methode dont le but est de generer des matrices a partir de segments
	 * selon la methode decrite dans l'enonce
	 * 
	 * @param s1
	 *            premier segment
	 * @param s2
	 *            deuxieme segment
	 * @return matrices (tableau) a utiliser pour la comparaison de deux
	 *         segments (determiner ensuite s'ils sont secants)
	 */
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
	

	//utiliser pour le graphes des paires secantes
	public static ArrayList<ArrayList<NoeudINTINT>> genererStructureGrapheNBPAIRESSEC() throws Exception{
		int [] res = new int[3];
		ArrayList<NoeudINTINT> jeuI = new ArrayList<NoeudINTINT>();
		ArrayList<NoeudINTINT> jeuII = new ArrayList<NoeudINTINT>();
		ArrayList<NoeudINTINT> jeuIII = new ArrayList<NoeudINTINT>();
		
		for (int i = 200; i < 2000; i+=200) {
			res = Matrice.evaluerNombreDePaireDeSegmentsSeCoupantM2(i);
			jeuI.add(new NoeudINTINT(res[0], i));
			jeuII.add(new NoeudINTINT(res[1], i));
			jeuIII.add(new NoeudINTINT(res[2], i));
		}
		
		ArrayList<ArrayList<NoeudINTINT>> result = new ArrayList<ArrayList<NoeudINTINT>>();
		result.add(jeuI);
		result.add(jeuII);
		result.add(jeuIII);
		return result;
	}
	

	/**
	 * Methode donnant de maniere pseudo aleatoire soit -1.0 ou 1.0 est utilise
	 * dans les jeux de tests
	 * 
	 * @return nombre aleatoire soit -1.0 ou 1.0
	 */
	public static double retournerSignePositifOuNegatif() {
		double res = Math.random();
		if (res < 0.5) {
			return -1.0;
		} else {
			return 1.0;
		}
	}

	/**
	 * Methode pour determiner si la pente est valide
	 * 
	 * @param x1
	 *            coordonee x du premier point
	 * @param y1
	 *            coordonee y du premier point
	 * @param x2
	 *            coordonee x du deuxieme point
	 * @param y2
	 *            coordonee y du deuxieme point
	 * @return booleen indiquant si la pente du segment est plus ou moins 1
	 */
	public static boolean calculPenteOK(double x1, double y1, double x2, double y2) {
		double result = (y2 - y1) / (x2 - x1);
		if ((result <= 1) && (result >= -1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode generant des jeux de segments selons les indications de l'enonce
	 * (question 4)
	 * 
	 * @param nombreSegments
	 *            nombre de segments
	 * @param numJeuTest
	 *            les numeros correspondent respectivement aux cas i, ii et iii
	 * @return liste de segments
	 */
	public static ArrayList<Segment> genererSegmentsAleatoire(int nombreSegments, int numJeuTest) {
		// liste retournee
		ArrayList<Segment> arrSegmentsAleatoires = new ArrayList<Segment>();
		// le n de l'intervalle, constate par empirisme pour le +1
		int VARATION_INTERVAL = nombreSegments+1;
		// coordonnees des deux points
		int x1int, y1int, x2int, y2int;
		// coordonnees des deux points
		Double x1, x2, y1, y2;
		// les 2 points
		Point p1, p2;

		// on switch selon les cas
		switch (numJeuTest) {
		// cas i
		case 1:
			/*
			 * Il s'agit de tirer au hasard des segments avec des sommets
			 * compris dans 0 n (nombre de segments) (la région du plan dans
			 * lequel se répartissent les n segments grandit avec le nombre n de
			 * segments).
			 */

			// pour chaque segment on:
			for (int i = 0; i < nombreSegments; i++) {

				// on a au hasard les coordonnees dans 0, n
				x1 = Math.random() * VARATION_INTERVAL;
				y1 = Math.random() * VARATION_INTERVAL;
				x2 = Math.random() * VARATION_INTERVAL;
				y2 = Math.random() * VARATION_INTERVAL;

				// on convertit par mesure de simplicite
				x1int = x1.intValue();
				y1int = y1.intValue();
				x2int = x2.intValue();
				y2int = y2.intValue();

				// on creer les deux points
				p1 = new Point(x1int, y1int);
				p2 = new Point(x2int, y2int);

				// On ajoute un segment a partir des points
				arrSegmentsAleatoires.add(new Segment(p1, p2));
			}
			break;
		// cas ii
		case 2:
			/*
			 * Il s'agit de tirer au hasard des segments avec le sommet gauche
			 * compris dans 0 n (nombre de segments) (la région du plan dans
			 * lequel se répartissent les n segments grandit avec le nombre n de
			 * segments).
			 */

			// pour chaque segment on:
			for (int i = 0; i < nombreSegments; i++) {

				// on prend au hasard les coordonnees du point gauche
				x1 = Math.random() * VARATION_INTERVAL;
				y1 = Math.random() * VARATION_INTERVAL;

				/*
				 * si on veut des segments de largeur 1 et de hauteur 1 on ne
				 * peut pas toucher a la pente de manière aleatoire puisque
				 * celle ci influe sur la huteur et la largeur avoir hauteur et
				 * largeur a 1 on a le choix entre 4 oordonnees
				 * 
				 * on decoupe en 4 cas on fait au moins une foit la boucle pour
				 * eviter le cas ou les coordonnees de x2 seraient negatives
				 */
				Double switchVal = Math.random();
				boolean res = false;
				if ((switchVal < 0.25) && !res) {
					x2 = x1 - 1.0;
					y2 = y1 + 1.0;
					res = true;
				} else {
					if (((switchVal >= 0.25) && (switchVal < 0.5)) && !res) {
						x2 = x1 + 1.0;
						y2 = y1 + 1.0;
						res = true;
					} else {
						if (((switchVal >= 0.5) && (switchVal < 0.75)) && !res) {
							x2 = x1 + 1.0;
							y2 = y1 - 1.0;
							res = true;
						} else {
							if ((switchVal >= 0.75) && !res) {
							}
							x2 = x1 - 1.0;
							y2 = y1 - 1.0;
							res = true;
						}
					}
				}

				// on convertit par mesure de simplicite
				x1int = x1.intValue();
				y1int = y1.intValue();
				x2int = x2.intValue();
				y2int = y2.intValue();

				// on creer nos deux points
				p1 = new Point(x1int, y1int);
				p2 = new Point(x2int, y2int);

				// On ajoute un segment a partir des points
				arrSegmentsAleatoires.add(new Segment(p1, p2));
			}
			break;
		// cas iii
		case 3:
			// On reduit le deuxieme interval a racine de n
			double sqrtResN = Math.sqrt((double) (nombreSegments+1));
			int VARATION_INTERVAL1 = nombreSegments+1;
			VARATION_INTERVAL = (int) Math.round(sqrtResN);

			for (int i = 0; i < nombreSegments; i++) {

				/*
				 * Il s'agit de tirer au hasard des segments avec des sommets
				 * compris dans 1 racine n pour le point gauche et pour le point
				 * doit etre tel que le segment ait une hauteur et une largeur
				 * dans 1, racinde de n la heuteur et la largeur sont aleatoire
				 * mais la pente est dans l'interval -1.2, +1.2 pour la pente
				 */

				// on obtient les coordonnees du point gauche dans 0, n
				x1 = Math.random() * VARATION_INTERVAL1;
				y1 = Math.random() * VARATION_INTERVAL1;

				// notons que VARATION_INTERVAL correspond a racine de n
				x2 = x1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();
				y2 = y1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();

				/*
				 * en cas de pb On determine point Gauche, point Droit
				 */
				double temp, temp2;
				if (!(x1 > x2)) {
					temp = x2;
					temp2 = y2;
					x1 = temp;
					y1 = temp2;
					x2 = x1;
					y2 = y1;
				}

				/*
				 * il y a de la redondance.. certes on verifie qu'on a pas le
				 * cas de 0 et 1
				 */
				while (x1 == x2) {
					x2 = x1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();
				}

				while (y1 == y2) {
					y2 = y1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();
				}

				/*
				 * on doit verifier si la pente est de plus ou moins 1 sinon on
				 * regenere les points posant probleme on verifie aussi le cas
				 * di 0 a 1
				 */
				while ((!Matrice.calculPenteOK(x1, y1, x2, y2))) {

					// on obtient le deuxieme point
					x2 = x1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();
					y2 = y1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();

					/*
					 * vu qu'il s'agit d'entier on evite le cas du mauvais
					 * intervalle dans entre 0 et 1 du 1 racine de n
					 */
					while (x1 == x2) {
						x2 = x1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();
					}

					while (y1 == y2) {
						y2 = y1 + Math.random() * VARATION_INTERVAL * Matrice.retournerSignePositifOuNegatif();
					}
				}

				// on convertit par mesure de simplicite
				x1int = x1.intValue();
				y1int = y1.intValue();
				x2int = x2.intValue();
				y2int = y2.intValue();

				// on creer nos deux points
				p1 = new Point(x1int, y1int);
				p2 = new Point(x2int, y2int);

				// On ajoute un segment a partir des points
				arrSegmentsAleatoires.add(new Segment(p1, p2));
			}
			break;
		}
		/*
		 * ArrayList contenant des segements tires au hasard suivant le jeu
		 * desire i ou ii ou iii
		 */
		return arrSegmentsAleatoires;
	}

	/**
	 * Methode permettant de determiner un nombre moyen de segments se coupant
	 * 
	 * @param nombreDeSegments
	 *            nombre de segments
	 * @param pas
	 *            combien de graduation veut-on
	 * @return une ArrayList prete a l'emploi pour la partie graphique
	 * @throws Exception
	 *             leve une exception si on demande un pas abberant
	 */
	// A REMANIER
	public static int[] evaluerNombreDePaireDeSegmentsSeCoupant(int nombreDeSegments, int pas) throws Exception {
		if ((nombreDeSegments == pas) || (nombreDeSegments == 0)) {
			System.out.println("Cela n'a aucun sens de faire une moyenne d'un element");
			throw new Exception();
		}

		if (nombreDeSegments % pas != 0) {
			System.out.println("ATTENTION: " + pas + "n'est pas multiple de: " + nombreDeSegments);
		}

		ArrayList<NoeudNombreSegSecants> segCoupJeuTLP1 = new ArrayList<NoeudNombreSegSecants>();
		ArrayList<NoeudNombreSegSecants> segCoupJeuTLP2 = new ArrayList<NoeudNombreSegSecants>();
		ArrayList<NoeudNombreSegSecants> segCoupJeuTLP3 = new ArrayList<NoeudNombreSegSecants>();

		for (int i = pas; i < nombreDeSegments; i += pas) {
			System.out.println("NB SEGMENTS" + i);
			segCoupJeuTLP1
					.add(new NoeudNombreSegSecants(i, Matrice.ToutesLesPaires(Matrice.genererSegmentsAleatoire(i, 1))));
			segCoupJeuTLP2
					.add(new NoeudNombreSegSecants(i, Matrice.ToutesLesPaires(Matrice.genererSegmentsAleatoire(i, 2))));
			segCoupJeuTLP3
					.add(new NoeudNombreSegSecants(i, Matrice.ToutesLesPaires(Matrice.genererSegmentsAleatoire(i, 3))));
		}

		int moyenne1 = 0, moyenne2 = 0, moyenne3 = 0;
		int somme1 = 0, somme2 = 0, somme3 = 0;
		int taille1 = segCoupJeuTLP1.size(), taille2 = segCoupJeuTLP2.size(), taille3 = segCoupJeuTLP3.size();

		// on aurait pu mettre les 3 arraylist dans une sur structure, on
		// augmente les possibilites d'erreurs dans le code
		for (NoeudNombreSegSecants noeudNombreSegSecants : segCoupJeuTLP1) {
			somme1 += noeudNombreSegSecants.getNombreDePaireSecantes();
		}

		for (NoeudNombreSegSecants noeudNombreSegSecants : segCoupJeuTLP2) {
			somme2 += noeudNombreSegSecants.getNombreDePaireSecantes();
		}

		for (NoeudNombreSegSecants noeudNombreSegSecants : segCoupJeuTLP3) {
			somme3 += noeudNombreSegSecants.getNombreDePaireSecantes();
		}

		moyenne1 = somme1 / taille1;
		moyenne2 = somme2 / taille2;
		moyenne3 = somme3 / taille3;

		int[] retour = { moyenne1, moyenne2, moyenne3 };

		return retour;

	}

	/**
	 * Methode determinant la moyenne de paires de segments selon les cas i, ii
	 * et iii
	 * 
	 * @param nombreDeSegments
	 *            nombre de segments
	 * @return tableau donnant pour un n donne et un cas i la moyenne de paires
	 *         de segments qui se coupent
	 * @throws Exception
	 *             lance une exception si on donne 0 segments
	 */
	public static int[] evaluerNombreDePaireDeSegmentsSeCoupantM2(int nombreDeSegments) throws Exception {

		// test de l'erreur
		if (nombreDeSegments == 0) {
			System.out.println("Cela n'a aucun sens nombreDeSegments doit etre > 0");
			throw new Exception();
		}

		// on initialise 3 ArrayList, cela pourrait une structure plus simple
		ArrayList<NoeudNombreSegSecants> segCoupJeuTLP1 = new ArrayList<NoeudNombreSegSecants>();
		ArrayList<NoeudNombreSegSecants> segCoupJeuTLP2 = new ArrayList<NoeudNombreSegSecants>();
		ArrayList<NoeudNombreSegSecants> segCoupJeuTLP3 = new ArrayList<NoeudNombreSegSecants>();

		// on indique le nombre de segments
		System.out.println("NB SEGMENTS " + nombreDeSegments);

		// on veut 3 valeurs pour pouvoir effectuer une moyenne a avoir des
		// valeurs empiriques fiables
		for (int j = 1; j <= 3; j++) {
			segCoupJeuTLP1.add(new NoeudNombreSegSecants(nombreDeSegments,
					Matrice.ToutesLesPaires(Matrice.genererSegmentsAleatoire(nombreDeSegments, 1))));
			segCoupJeuTLP2.add(new NoeudNombreSegSecants(nombreDeSegments,
					Matrice.ToutesLesPaires(Matrice.genererSegmentsAleatoire(nombreDeSegments, 2))));
			segCoupJeuTLP3.add(new NoeudNombreSegSecants(nombreDeSegments,
					Matrice.ToutesLesPaires(Matrice.genererSegmentsAleatoire(nombreDeSegments, 3))));
		}

		// variables utilisees pour le calcul
		int moyenne1 = 0, moyenne2 = 0, moyenne3 = 0;
		int somme1 = 0, somme2 = 0, somme3 = 0;
		int taille1 = segCoupJeuTLP1.size(), taille2 = segCoupJeuTLP2.size(), taille3 = segCoupJeuTLP3.size();

		/*
		 * on aurait pu mettre les 3 arraylist dans une sur structure, on
		 * augmente les possibilites d'erreurs dans le code, on affectue les
		 * parcours et on calcul les valeurs cumulees
		 */
		for (NoeudNombreSegSecants noeudNombreSegSecants : segCoupJeuTLP1) {
			somme1 += noeudNombreSegSecants.getNombreDePaireSecantes();
		}

		for (NoeudNombreSegSecants noeudNombreSegSecants : segCoupJeuTLP2) {
			somme2 += noeudNombreSegSecants.getNombreDePaireSecantes();
		}

		for (NoeudNombreSegSecants noeudNombreSegSecants : segCoupJeuTLP3) {
			somme3 += noeudNombreSegSecants.getNombreDePaireSecantes();
		}

		// on calcule les moyennes, on les retournes
		moyenne1 = somme1 / taille1;
		moyenne2 = somme2 / taille2;
		moyenne3 = somme3 / taille3;

		// on affecte au tableau
		int[] retour = { moyenne1, moyenne2, moyenne3 };

		// on retourne le tableau
		return retour;
	}

	/**
	 * Methode dont le but est de permettre d'evaluer le temps de calcul des
	 * algorithmes renvoie des coordonnes pondere (calcul de 3 fois la meme
	 * chose sur le jeu de segment donne, evite certaines erreurs experimetales)
	 * avec les 3 jeux de test on a la fin deux courbes avec des points moyen,
	 * on considère chaque jeu de tests separemment On renvoie un nombre donne
	 * de segments, avec le temps mis pour faire le balayage ou toutesLesPaires
	 * 
	 * @param ArrayList<Segment>
	 *            jeuDeTest
	 * @return ArrayList<ArrayList<NoeudTempsNombre>> objet qui contient une
	 *         liste par courbe utilise par l'outil de generation de graphique
	 */
	public static ArrayList<ArrayList<NoeudTempsNombre>> estimerTemps(ArrayList<ArrayList<Segment>> liste) {

		// variables utilisees pour les calculs de durees
		long startTimeB = 0, startTimeTLP = 0, cumulTimeB = 0, cumulTimeTLP = 0, previousB = 0;
		long elapsedTimeNsB = 0, elapsedTimeNsTLP = 0;

		// variable stockant le nombre de segments
		int nombre_de_segments;

		// Objet contenant l'ensemble des resultats
		ArrayList<ArrayList<NoeudTempsNombre>> result = new ArrayList<ArrayList<NoeudTempsNombre>>();
		// liste concernat balayage
		ArrayList<NoeudTempsNombre> balayage = new ArrayList<NoeudTempsNombre>();
		// liste concernant touteLesPaires
		ArrayList<NoeudTempsNombre> toutesLesPaires = new ArrayList<NoeudTempsNombre>();

		// calcul du temps pour la liste passe en parametre
		for (ArrayList<Segment> arrayList : liste) {
			nombre_de_segments = arrayList.size();

			// triple iteration pour effectuer une moyenne
			for (int i = 1; i <= 3; i++) {
				startTimeB = System.nanoTime();
				Matrice.Balayage(arrayList);
				elapsedTimeNsB = System.nanoTime() - startTimeB;

				startTimeTLP = System.nanoTime();
				Matrice.ToutesLesPaires(arrayList);
				elapsedTimeNsTLP = System.nanoTime() - startTimeTLP;


				cumulTimeB += (elapsedTimeNsB);
				cumulTimeTLP += (elapsedTimeNsTLP);
				//cumulTimeB += (elapsedTimeNsB / 1000000000);
				//cumulTimeTLP += (elapsedTimeNsTLP / 1000000000);
			}

			// ajout des resultats
			balayage.add(new NoeudTempsNombre(nombre_de_segments, cumulTimeB / 3));
			toutesLesPaires.add(new NoeudTempsNombre(nombre_de_segments, cumulTimeTLP / 3));
		}

		// ajout a la structure globale
		result.add(balayage);
		result.add(toutesLesPaires);
		// retour du resultat
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
				if ((m[0].determinant() * m[1].determinant() <= 0) && (m2[0].determinant() * m2[1].determinant() <= 0)) intersections++;
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
		         //return result;
		         return -result;
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
					if ((m[0].determinant() * m[1].determinant() <= 0) && (m2[0].determinant() * m2[1].determinant() <= 0)) k++; 
				}
			}
		}
		return k;
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
	 * 
	 * @param matrice
	 *            dont on veut obtenir le determinant
	 */
	public int determinant(Matrice matrice) {
		// cas particulier
		if (matrice.n == 2 && matrice.m == 2) {
			return (matrice.mat[0][0] * matrice.mat[1][1] - matrice.mat[0][1] * matrice.mat[1][0]);
		} else {
			// cas general
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
	 * Renvoi vrai si la matrice est carree n == m
	 */
	public boolean estCarree() {
		return n == m;
	}

	/**
	 * Calcul la transposee de la matrice
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
}
