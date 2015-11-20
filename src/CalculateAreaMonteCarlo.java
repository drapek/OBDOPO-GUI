import ProjectExceptions.ToFewPointsToMakePlygon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by drapek on 01.11.15.
 */
public class CalculateAreaMonteCarlo {
    private ArrayList <Point2D> fieldBoundaryPoints;
    private ArrayList <Point2D> randomPointsHits;
    private ArrayList <Point2D> randomPointsMiss;
    
    
    Point2D minPointOuterRectangle;
    Point2D maxPointOuterRectangle;
    Random rnd = new Random();

    private int overallRandomSamplesNumber = 1000;
    private int oneStepRandomSamplesNumber = 200;
    private int summaryRandomSamplesNumberAtMoment = 0;
    private int hitsNumber = 0;
    private int missNumber = 0;


    public CalculateAreaMonteCarlo(ArrayList <Point2D> fieldBoundaryPoints) {
        
        this.fieldBoundaryPoints = fieldBoundaryPoints;
        randomPointsHits = new ArrayList<>();
        randomPointsMiss = new ArrayList<>();
        
        minPointOuterRectangle = findMinimalPointLimitingFigure();
        maxPointOuterRectangle = findMaximalPointLimitingFigure();
    }

    public void setOverallNumerOfSamples(int naturalNumber) {
            overallRandomSamplesNumber = naturalNumber;
    }
    
    public void setOneStepRandomSamplesNumber(int naturalNumber) {
            oneStepRandomSamplesNumber = naturalNumber;
    }
    
    public int getHitsNumber() {
        return hitsNumber;
    }
    
    public int getMissNumber() {
        return missNumber;
    }
    
    public ArrayList <Point2D> getHitsArrayList() {
        return randomPointsHits;
    }
    
    public ArrayList <Point2D> getMissArrayList() {
        return randomPointsMiss;
    }
    
    public double getAreaAtThisMoment() {
        double outerRectangleArea = (maxPointOuterRectangle.getX() - minPointOuterRectangle.getX()) * (maxPointOuterRectangle.getY() - minPointOuterRectangle.getY());
        return (double) hitsNumber / (double) summaryRandomSamplesNumberAtMoment * outerRectangleArea;
    }
    
    /**
     * 
     * @return false if algoritm make overallRandomSamplesNumber steps
     */
    public boolean oneStepInCountingArea() {
        for(int i = 0; i < oneStepRandomSamplesNumber; i++, summaryRandomSamplesNumberAtMoment++) {
            if(summaryRandomSamplesNumberAtMoment >= overallRandomSamplesNumber)
                return false;
            Point2D rndPnt = randomPoint();
            if(isInsideFigure(rndPnt)) {
                hitsNumber++;
                randomPointsHits.add(rndPnt);
            } else {
                missNumber++;
                randomPointsMiss.add(rndPnt);
            }
        }
        
        return true;
    }
    
    public void goToTheEndOfAlgoritm() {
        while(oneStepInCountingArea())
            ;
    }

    private boolean isInsideFigure(Point2D check) {
        for (int i = 0, j = fieldBoundaryPoints.size() - 1; i < fieldBoundaryPoints.size(); j = i++) {
            if ((fieldBoundaryPoints.get(i).getY() > check.getY()) != (fieldBoundaryPoints.get(j).getY() > check.getY()) &&
                    (check.getX() < (fieldBoundaryPoints.get(j).getX() - fieldBoundaryPoints.get(i).getX()) * (check.getY() - fieldBoundaryPoints.get(i).getY()) / (fieldBoundaryPoints.get(j).getY() - fieldBoundaryPoints.get(i).getY()) + fieldBoundaryPoints.get(i).getX())) {
                return true;
            }
        }
        return false;
    }

    private Point2D randomPoint() {
        double randX = rnd.nextDouble()*(maxPointOuterRectangle.getX() - minPointOuterRectangle.getX()) + minPointOuterRectangle.getX();
        double randY = rnd.nextDouble()*(maxPointOuterRectangle.getY() - minPointOuterRectangle.getY()) + minPointOuterRectangle.getY();

        return new Point2D.Double(randX, randY);

    }

    private Point2D findMinimalPointLimitingFigure() {
        double minX = fieldBoundaryPoints.get(0).getX();
        double minY = fieldBoundaryPoints.get(0).getY();
        for( Point2D each : fieldBoundaryPoints) {
            if( each.getX() < minX )
                minX = each.getX();
            if( each.getY() < minY )
                minY = each.getY();
        }

        return new Point2D.Double(minX, minY);
    }

    private Point2D findMaximalPointLimitingFigure() {
        double maxX = fieldBoundaryPoints.get(0).getX();
        double maxY = fieldBoundaryPoints.get(0).getY();;
        for( Point2D each : fieldBoundaryPoints) {
            if( each.getX() > maxX )
                maxX = each.getX();
            if( each.getY() > maxY )
                maxY = each.getY();
        }

        return new Point2D.Double(maxX, maxY);
    }

}
