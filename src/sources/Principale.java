package sources;

import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;

public class Principale {
	/**
	 * 
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		ArrayList<Object> tasks = new ArrayList<Object>();
		Scanner sc = new Scanner(System.in);
		String result = "";
		boolean petitsExemples = false, tousLesJeuxDeTests = false, pairesMoyennesDeSegments = false;

		System.out.println(
				"Donnez votre choix:\n tout (T),\n petitsExemples (Q3) (Question 3),\n tous les jeux de tests (Q4) (question 4),\n nombre de paires moyennes de segments se coupant (Q5) (question 5)");
		result = sc.nextLine();

		// while(!result.equals("QUITTER")){
		switch (result) {
		case "T":
			petitsExemples = true;
			tousLesJeuxDeTests = true;
			pairesMoyennesDeSegments = true;
			break;

		case "Q3":
			petitsExemples = true;
			break;

		case "Q4":
			System.out.println("Ceci peut prendre un certain temps..");
			tousLesJeuxDeTests = true;
			break;

		case "Q5":
			pairesMoyennesDeSegments = true;
			break;

		default:
			System.out.println("AUCUNE INSTRUCTION TROUVEE: \"Tout\" est choisi");
			break;
		}

		/*
		 * ------ Test des deux fonctions ------
		 */
		if (petitsExemples) {
			System.out.println("\n*** Test fonction : Balayage() et ToutesLesPaires()");
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

			System.out.println("NOMBRE DE SEGMENTS DANS LA LISTE: " + lesS.size());
			System.out.println("BALAYAGE nombre: " + Matrice.Balayage(lesS) + " intersections");
			System.out.println("ToutesLesPaires nombre : " + Matrice.ToutesLesPaires(lesS) + " intersections\n");

			XYLineChart_AWT confirmationIntersectionsSegments = new XYLineChart_AWT(
					"Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO", "Visualisation des segments ", lesS);
			confirmationIntersectionsSegments.pack();
			RefineryUtilities.centerFrameOnScreen(confirmationIntersectionsSegments);
			confirmationIntersectionsSegments.setVisible(true);
		}

		/*
		 * ------ Calcul des moyennes de paires secantes pour un jeu j donee j
		 * variant dans i, ii et iii ------
		 */
		if (pairesMoyennesDeSegments) {
			int[] resultatJeuMoyen2 = Matrice.evaluerNombreDePaireDeSegmentsSeCoupantM2(100);
			for (int i = 0; i < resultatJeuMoyen2.length; i++) {
				System.out.println("JEU DE TEST: " + (i + 1) + " moyenne: " + resultatJeuMoyen2[i]);
			}
		}

		/*
		 * ------ Utilisation des jeux de tests i, ii et iii pour illustrer le
		 * nombre de segments en fonctions du temps et comparer les algorithmes
		 * balayage et toutesLesPaires ------
		 */

		// on le laisse en dernier a cause de la consommation des ressources
		if (tousLesJeuxDeTests) {
			ArrayList<ArrayList<Segment>> listeI = new ArrayList<ArrayList<Segment>>();

			listeI.add(Matrice.genererSegmentsAleatoire(10, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(20, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(30, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(40, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(50, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(60, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(80, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(90, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(100, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(120, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(140, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(160, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(180, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(200, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(250, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(300, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(350, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(400, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(450, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(500, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(600, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(700, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(800, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(900, 1));
			listeI.add(Matrice.genererSegmentsAleatoire(1000, 1));
			/*
			 * listeI.add(Matrice.genererSegmentsAleatoire(1200, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(1400, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(1600, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(1800, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(2000, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(2500, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(3000, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(3500, 1));
			 * listeI.add(Matrice.genererSegmentsAleatoire(4000, 1));
			 */

			ArrayList<ArrayList<NoeudTempsNombre>> retourDonneesI = Matrice.estimerTemps(listeI);
			XYLineTIMEChart_AWT grapheNTI = new XYLineTIMEChart_AWT(
					"Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO",
					"Visualisation de la performance des algorithmes (jeu i)", retourDonneesI);
			grapheNTI.pack();
			RefineryUtilities.centerFrameOnScreen(grapheNTI);
			grapheNTI.setVisible(true);

			ArrayList<ArrayList<Segment>> listeII = new ArrayList<ArrayList<Segment>>();

			listeII.add(Matrice.genererSegmentsAleatoire(10, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(20, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(30, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(40, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(50, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(60, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(80, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(90, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(100, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(120, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(140, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(160, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(180, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(200, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(250, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(300, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(350, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(400, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(450, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(500, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(600, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(700, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(800, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(900, 2));
			listeII.add(Matrice.genererSegmentsAleatoire(1000, 2));
			/*
			 * listeII.add(Matrice.genererSegmentsAleatoire(1200, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(1400, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(1600, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(1800, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(2000, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(2500, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(3000, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(3500, 2));
			 * listeII.add(Matrice.genererSegmentsAleatoire(4000, 2));
			 */

			ArrayList<ArrayList<NoeudTempsNombre>> retourDonneesII = Matrice.estimerTemps(listeII);
			XYLineTIMEChart_AWT grapheNTII = new XYLineTIMEChart_AWT(
					"Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO",
					"Visualisation de la performance des algorithmes (jeu ii)", retourDonneesII);
			grapheNTII.pack();
			RefineryUtilities.centerFrameOnScreen(grapheNTII);
			grapheNTII.setVisible(true);

			ArrayList<ArrayList<Segment>> listeIII = new ArrayList<ArrayList<Segment>>();

			listeIII.add(Matrice.genererSegmentsAleatoire(10, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(20, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(30, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(40, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(50, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(60, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(80, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(90, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(100, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(120, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(140, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(160, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(180, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(200, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(250, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(300, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(350, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(400, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(450, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(500, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(600, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(700, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(800, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(900, 3));
			listeIII.add(Matrice.genererSegmentsAleatoire(1000, 3));
			/*
			 * listeIII.add(Matrice.genererSegmentsAleatoire(1200, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(1400, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(1600, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(1800, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(2000, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(2500, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(3000, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(3500, 3));
			 * listeIII.add(Matrice.genererSegmentsAleatoire(4000, 3));
			 */

			ArrayList<ArrayList<NoeudTempsNombre>> retourDonneesIII = Matrice.estimerTemps(listeIII);
			XYLineTIMEChart_AWT grapheNTIII = new XYLineTIMEChart_AWT(
					"Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO",
					"Visualisation de la performance des algorithmes (jeu iii)", retourDonneesIII);
			grapheNTIII.pack();
			RefineryUtilities.centerFrameOnScreen(grapheNTIII);
			grapheNTIII.setVisible(true);
		}
	}
}
