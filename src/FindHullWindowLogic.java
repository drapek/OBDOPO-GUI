
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
public class FindHullWindowLogic {
    static private Point2D [] importedPointsFromPointsReader;
    static private boolean isConvexHullFinded = false;
    
    static public void setIsConvexHullFinded(boolean newVal) {
        isConvexHullFinded =  newVal;
    }
    
    
    static void setImportedPointsFromReader( Point2D [] imported) {
        importedPointsFromPointsReader = imported;
    }
    
    static Point2D [] getImportedPointsFromReader() {
        return importedPointsFromPointsReader;
    }
    
    static void drawPointsOnChart(JPanel panelWhenInside, ArrayList <Point2D> convexHull) {
        panelWhenInside.removeAll();
        panelWhenInside.setLayout(new java.awt.BorderLayout());
        XYSeries seriersAllPoints = new XYSeries("All points");
        addPointsToSeries(seriersAllPoints);
        
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
        dataset.addSeries(seriersAllPoints);
        
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
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShape(0, ShapeUtilities.createDiamond(3));
        
        for( int i = 1; i <= covnexHullDivideOnPiars.length; i++) {
            renderer.setSeriesPaint(i, Color.red);
            renderer.setSeriesLinesVisible(i, true);
            renderer.setSeriesStroke( i , new BasicStroke( 1.0f ) );
        }

        plot.setRenderer(renderer);

        panelWhenInside.add(chartPanel, BorderLayout.CENTER);
        panelWhenInside.validate();
    }
    
    static private void addPointsToSeries(XYSeries seriesDestination) {
        for(Point2D onePoint : importedPointsFromPointsReader) {
            seriesDestination.add( onePoint.getX() , onePoint.getY());
        }
    }
    
    static private void divideOnPairsAndConvertConvexHullIntoSeries(XYSeries [] seriesDestination, ArrayList <Point2D> convexHull) {
        for(int i = 1; i < convexHull.size(); i ++) {
            seriesDestination[i - 1].add(convexHull.get(i-1).getX(), convexHull.get(i-1).getY());
            seriesDestination[i - 1].add(convexHull.get(i).getX(), convexHull.get(i).getY());
        }
    }
    
    static public void fullfilTableWithConvexHull( JTable jTableDest, ArrayList <Point2D> convexHull) {
        DefaultTableModel jTableDestModel = (DefaultTableModel) jTableDest.getModel();
        jTableDestModel.setRowCount(0);
        
        int i = 1;
        for(Point2D onePoint : convexHull ) {
            if( i != 1 && areTheSamePoints(onePoint, convexHull.get(0)))
                break;
            jTableDestModel.addRow(new Object[] { i + ".", onePoint.getX(), onePoint.getY()  });
            i++;
        }
        
    }
    
    static private boolean areTheSamePoints(Point2D p1, Point2D p2) {
        if( p1.getX() == p2.getX() && p1.getY() == p2.getY())
            return true;
        else
            return false;
    }
}
