
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drapek
 */
public class MonteCarloWindowLogic {
    static ForwardingDataPackage forwardedData;
    
    static public void setForwardedData(ForwardingDataPackage newForwData) {
        forwardedData = newForwData;
    }
    
    static public ForwardingDataPackage getForwardedData() {
        return forwardedData;
    }
    
    static void drawPointsOnChart(JPanel panelWhenInside, ArrayList <Point2D> convexHull, ArrayList <Point2D> hits, ArrayList <Point2D> miss) {
        panelWhenInside.removeAll();
        panelWhenInside.setLayout(new java.awt.BorderLayout());
        
        XYSeries seriersHits = new XYSeries("Hits");
        convertArrayListToXYSeries(seriersHits, hits);
        
        XYSeries seriersMiss = new XYSeries("Miss");
        convertArrayListToXYSeries(seriersMiss, miss);
        //TODO refactor this, to handling hits, miss and than convex hull
        
        int pairsNumber = 0;
        if( convexHull != null) 
            pairsNumber = convexHull.size() - 1;
        XYSeries covnexHullDivideOnPiars[] = new XYSeries[pairsNumber];
        
        for( int i = 0; i < covnexHullDivideOnPiars.length; i++) {
            covnexHullDivideOnPiars[i] = new XYSeries("Convex hull pair " + i);
        }
        
        if( convexHull != null) {
            divideOnPairsAndConvertConvexHullIntoSeries(covnexHullDivideOnPiars, convexHull);
        }
        
        // Add the seriersAllPoints to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriersHits);
        dataset.addSeries(seriersMiss);
        
        for( int i = 0; i < covnexHullDivideOnPiars.length; i++) {
            dataset.addSeries(covnexHullDivideOnPiars[i]);
        }
        

        // Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
                null, // Title
                null, // x-axis Label
                null, // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                false, // Show Legend
                false, // Use tooltips
                false // Configure chart to generate URLs?
        );

        final XYPlot plot = chart.getXYPlot();
        ChartPanel chartPanel = new ChartPanel(chart);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShape(0, ShapeUtilities.createDiamond(3));
        
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShape(1, ShapeUtilities.createDiamond(3));
        
        for( int i = 2; i <= covnexHullDivideOnPiars.length + 1; i++) {
            renderer.setSeriesPaint(i, Color.black);
            renderer.setSeriesLinesVisible(i, true);
            renderer.setSeriesStroke( i , new BasicStroke( 1.0f ) );
        }

        plot.setRenderer(renderer);

        panelWhenInside.add(chartPanel, BorderLayout.CENTER);
        panelWhenInside.validate();
    }
    
    
    static private void divideOnPairsAndConvertConvexHullIntoSeries(XYSeries [] seriesDestination, ArrayList <Point2D> convexHull) {
        for(int i = 1; i < convexHull.size(); i ++) {
            seriesDestination[i - 1].add(convexHull.get(i-1).getX(), convexHull.get(i-1).getY());
            seriesDestination[i - 1].add(convexHull.get(i).getX(), convexHull.get(i).getY());
        }
    }
    
    static private void convertArrayListToXYSeries(XYSeries seriesDestination, ArrayList <Point2D> arrListFrom) {
        for(Point2D onePoint : arrListFrom) {
            seriesDestination.add(onePoint.getX(), onePoint.getY());
        }
    }
}
