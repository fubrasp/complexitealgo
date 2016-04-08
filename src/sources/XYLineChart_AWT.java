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

public class XYLineChart_AWT extends ApplicationFrame 
{
	//pour enlever la legende on passer un booleen dans le constructeur sur demande qui se met au bon etat en fonction du nb de segments
   public XYLineChart_AWT( String applicationTitle, String chartTitle, ArrayList<sources.Segment> arrSegs )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "X" ,
         "Y" ,
         createDataset(arrSegs) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 800 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      //En gris ça rend aussi?
      //plot.setBackgroundPaint(Color.GRAY);
      plot.setBackgroundPaint(Color.WHITE);
      plot.setRangeGridlinePaint(Color.RED);
      plot.setDomainGridlinePaint(Color.RED);;
      //pas besoin c'est par defaut
      //plot.setRangeGridlinesVisible(true);
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      /*renderer.setSeriesPaint(0 , Color.RED );
      renderer.setSeriesStroke( 0 , new BasicStroke( 3.0f ) );*/
      Stroke solid = new BasicStroke(3.5f);
      //depreciated mais le setBasicStroke ne passe pas
      renderer.setStroke(solid);
      //même remarque
      // A TESTER QUE PREFEREZ VOUS
      //renderer.setShape(ShapeUtilities.createRegularCross(3.0f, 5.0f));
      //renderer.setShape(ShapeUtilities.createRegularCross(6.0f, 0.5f));
      renderer.setShape(ShapeUtilities.createDiamond(4.0f));
      //Line2D line = new Line2D.Float(2, 2, -1, -1);
      //renderer.setShape(ShapeUtilities.createLineRegion(line, 5.0f));
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset(ArrayList<sources.Segment> arrSegs){
	   XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
       
       XYDataset result = null;
       sources.Segment courant;
       for (int x = 0; x < arrSegs.size(); x++) {
    	    courant=arrSegs.get(x);
    	    XYSeries test = new XYSeries("S"+(x+1));
    	    test.add(courant.getPointG().getX(), courant.getPointG().getY());
   		    test.add(courant.getPointD().getX(), courant.getPointD().getY());
    	    xySeriesCollection.addSeries(test);
       }
       return xySeriesCollection;
	}

}