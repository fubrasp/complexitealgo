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
 * Classe permettant de faire les graphes sur les segments secants
 * @author ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO
 * il s'agit d'une librairie: jfreechart (la documentation est payante!)
 */
public class XYLineChart_AWT extends ApplicationFrame 
{
	//pour enlever la legende on passer un booleen dans le constructeur sur demande qui se met au bon etat en fonction du nb de segments
   public XYLineChart_AWT( String applicationTitle, String chartTitle, ArrayList<sources.Segment> arrSegs )
   {
      super(applicationTitle);
      //on creer un graphe en renseignant les axes, l'orientation, en y incorporant les donnees, plusieurs options sont disponible avec les booleens
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "X" ,
         "Y" ,
         createDataset(arrSegs) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
      
      //on l'incorpore dans un panel
      ChartPanel chartPanel = new ChartPanel(xylineChart);
      
      //on attribue une dimension par defaut
      chartPanel.setPreferredSize( new java.awt.Dimension(800 , 800));
      //ontenir la courbe courante
      final XYPlot plot = xylineChart.getXYPlot( );
      //couleur blanche de l'arriere plan
      plot.setBackgroundPaint(Color.WHITE);
      //mettre le quadrtiage en rouge
      plot.setRangeGridlinePaint(Color.RED);
      plot.setDomainGridlinePaint(Color.RED);;
      //pas besoin c'est par defaut
      //plot.setRangeGridlinesVisible(true);
      //creation d'un renderer 
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      /*renderer.setSeriesPaint(0 , Color.RED );
      renderer.setSeriesStroke( 0 , new BasicStroke( 3.0f ) );*/
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
    * @param arrSegs structure permettant de representer le graphe
    * @return collection que le peut parcourir aisement et ainsi representer le graphe
    */
   private XYDataset createDataset(ArrayList<sources.Segment> arrSegs){
	   //on instancie la structure de donnees
	   XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
       
       XYDataset result = null;
       //utilise dans le parcours
       sources.Segment courant;
       for (int x = 0; x < arrSegs.size(); x++) {
    	    //on obtient l'element courant
    	    courant=arrSegs.get(x);
    	    //on a une nouvelle serie de points
    	    XYSeries test = new XYSeries("S"+(x+1));
    	    //on les ajoute a la structure..
    	    test.add(courant.getPointG().getX(), courant.getPointG().getY());
   		    test.add(courant.getPointD().getX(), courant.getPointD().getY());
    	    //on ajoute a la structure encapsulant toutes les XYSeries (ici les segments!) 
   		    xySeriesCollection.addSeries(test);
       }
       //on retourne la bonne structure de donnees contenant toutes les informations utiles pour le trace
       return xySeriesCollection;
	}

}