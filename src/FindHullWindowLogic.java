
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
    
    static void setImportedPointsFromReader( Point2D [] imported) {
        importedPointsFromPointsReader = imported;
    }
    
    static Point2D [] getImportedPointsFromReader() {
        return importedPointsFromPointsReader;
    }
    
    static void drawPointsOnChart(JPanel panelWhenInside) {
        
        panelWhenInside.setLayout(new java.awt.BorderLayout());
        XYSeries seriersAllPoints = new XYSeries("All points");
        addPointsToSeries(seriersAllPoints);


        // Add the seriersAllPoints to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriersAllPoints);

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
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );

        plot.setRenderer(renderer);

        panelWhenInside.add(chartPanel, BorderLayout.CENTER);
        panelWhenInside.validate();
    }
    
    static private void addPointsToSeries(XYSeries seriesDestination) {
        for(Point2D onePoint : importedPointsFromPointsReader) {
            seriesDestination.add( onePoint.getX() , onePoint.getY());
        }
    }
}
