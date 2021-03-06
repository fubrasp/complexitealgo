package sources;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.data.function.Function2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * Classe permettant de faire les graphes sur lenombre de segments et la duree selon les algorithmes
 * @author ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO
 * il s'agit d'une librairie: jfreechart (la documentation est payante!)
 */
public class XYLineSECChart extends ApplicationFrame 
{
	//pour enlever la legende on passer un booleen dans le constructeur sur demande qui se met au bon etat en fonction du nb de segments
   public XYLineSECChart(String applicationTitle, String chartTitle, ArrayList<ArrayList<NoeudINTINT>> data)
   {
      super(applicationTitle);
      //on creer un graphe en renseignant les axes, l'orientation, en y incorporant les donnees, plusieurs options sont disponible avec les booleens
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Paires de segments secants" ,
         "Nombre segments" ,
         createDataset(data) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
      
      //on l'incorpore dans un panel
      ChartPanel chartPanel = new ChartPanel(xylineChart);
      //on attribue une dimension par defaut
      chartPanel.setPreferredSize(new java.awt.Dimension(800 , 800));
      //obtenir la courbe courante
      final XYPlot plot = xylineChart.getXYPlot( );
    //couleur blanche de l'arriere plan
      plot.setBackgroundPaint(Color.WHITE);
      //mettre le quadrtiage en rouge
      plot.setRangeGridlinePaint(Color.RED);
      plot.setDomainGridlinePaint(Color.RED);
      //pas besoin c'est par defaut
      //plot.setRangeGridlinesVisible(true);
      //creation d'un renderer 
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      //epaisseur des traits
      Stroke solid = new BasicStroke(3.5f);
      //depreciated mais le setBasicStroke ne passe pas
      //c'est le plus pratique vu qu'on souhaite de l'uniformite
      renderer.setStroke(solid);
      //meme remarque
      renderer.setShape(ShapeUtilities.createDiamond(4.0f));
      plot.setRenderer(renderer); 
      //on finit en affectant le panel au frame
      setContentPane(chartPanel); 
   }
   
   /**
    * Methode construisant l'objet destine a la creation des courbes etc.
    * @param data structure permettant de representer le graphe
    * @return collection que l'on peut parcourir aisement et ainsi representer le graphe
    */
      
   private XYDataset createDataset(ArrayList<ArrayList<NoeudINTINT>> data){
	   //on instancie la structure de donnees
	   XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
	   XYDataset result = null;
       //utilise dans le parcours
	   ArrayList<NoeudTempsNombre> methodeCourante;
	   
	   ArrayList<NoeudINTINT> arr1 = data.get(0);
	   ArrayList<NoeudINTINT> arr2 = data.get(1);
	   ArrayList<NoeudINTINT> arr3 = data.get(2);
	   
	   XYSeries testI = new XYSeries("cas I");
	   XYSeries testII = new XYSeries("cas II");
	   XYSeries testIII = new XYSeries("cas III");
	   
		for (NoeudINTINT nb : arr1) {
			testI.add(nb.getMoyenne(), nb.getNombreSegments());
		}
		
		for (NoeudINTINT nb : arr2) {
			testII.add(nb.getMoyenne(), nb.getNombreSegments());
		}
		
		for (NoeudINTINT nb : arr3) {
			testIII.add(nb.getMoyenne(), nb.getNombreSegments());
		}
			xySeriesCollection.addSeries(testI);
			xySeriesCollection.addSeries(testII);
			xySeriesCollection.addSeries(testIII);
	   //on retourne l'objet encapsulant tout
	    return xySeriesCollection;
	}
}
