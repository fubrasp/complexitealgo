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

public class XYLineTIMEChart_AWT extends ApplicationFrame 
{
   public XYLineTIMEChart_AWT(String applicationTitle, String chartTitle, ArrayList<ArrayList<NoeudTempsNombre>> data)
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Temps" ,
         "Nombre segments" ,
         createDataset(data) ,
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
   
   private XYDataset createDataset(ArrayList<ArrayList<NoeudTempsNombre>> data){
	   XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
	   XYDataset result = null;
	   ArrayList<NoeudTempsNombre> methodeCourante;
	   for (int x = 0; x < data.size(); x++) {
   	    methodeCourante=data.get(x);
   	    //init???
   	    XYSeries test = null;
   	    if(x==0){
   	    	test= new XYSeries("BALAYAGE");
   	    }
   	    
   	    if(x==1){
   	    	test= new XYSeries("TOUTESLESPAIRES");
   	    }else{
   	    	if((x!=0)&&(x!=1)){
   	   	    	test= new XYSeries("BOG"+x);
   	    	}
   	    }
   	    
   	    for (NoeudTempsNombre noeudTempsNombre : methodeCourante) {
   	    	test.add(noeudTempsNombre.getTemps(), noeudTempsNombre.getNombreDeSegments());
		}
   	    xySeriesCollection.addSeries(test);
      }
	   //on vas faire deux courbes, une pour balayage, une autre pour 
	   
       return xySeriesCollection;
     
       /*
        * private TimeSeriesCollection createRandomDataset(final String name) {
        final TimeSeries series = new TimeSeries(name);
        double value = 100.0;
        RegularTimePeriod t = new Day();
        for (int i = 0; i < 50; i++) {
            series.add(t, value);
            t = t.next();
            value = value * (1.0 + Math.random() / 100);
        }
        return new TimeSeriesCollection(series);
    }
        */
	}
   
   /*private XYDataset createDataset( )
   {
      final XYSeries firefox = new XYSeries( "Firefox" );          
      firefox.add( 1.0 , 1.0 );          
      firefox.add( 3.50 , 4.0 );          
      
      final XYSeries chrome = new XYSeries( "Chrome" );          
      chrome.add( 1.0 , 4.0 );          
      chrome.add( 2.0 , 5.0 );
      
      final XYSeries test = new XYSeries( "test" );          
      test.add( 10.0 , 4.0 );          
      test.add( 4.0 , 11.0 ); 
      
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( firefox );          
      dataset.addSeries( chrome ); 
      dataset.addSeries( test ); 
      return dataset;
   }*/

   /*
   public static void main( String[ ] args ) 
   {
	  
	  ArrayList<sources.Segment> arrSegs = new ArrayList<sources.Segment>();
	  arrSegs.add(new sources.Segment(new sources.Point(1, 2), new sources.Point(3, 4)));
	  arrSegs.add(new sources.Segment(new sources.Point(3, 7), new sources.Point(5, 6)));	  
	  arrSegs.add(new sources.Segment(new sources.Point(26, 89), new sources.Point(4, 14)));
	  arrSegs.add(new sources.Segment(new sources.Point(23, 56), new sources.Point(9, 15)));	  
     
	  XYLineChart_AWT chart = new XYLineChart_AWT("Projet Complexite ARIF_BERTRAND_BOUGUETTOUCHA_GADEAU_SANCHO", "Segments", arrSegs);
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }*/
}