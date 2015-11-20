import ProjectExceptions.ToFewPointsToFindConvexHullException;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by drapek on 29.10.15.
 */
public class GrahamScan {
    private ArrayList <Point2D> pointsCollection;
    private ArrayList <Point2D> convexHull;
    private Point2D minimalYPoint;
    private int actualStepInGrahamScan;

    public GrahamScan(Point2D [] inputArr) throws ToFewPointsToFindConvexHullException {
        if (inputArr.length > 2 ) {
            pointsCollection = new ArrayList<Point2D>(Arrays.asList(inputArr));
        }
        else {
            throw new ToFewPointsToFindConvexHullException();
        }
    }
    
    public ArrayList <Point2D> getConvexHull() {
        return convexHull;
    }
    
    
    public void initGrahamScanAlgorithm() {
        //prepere data for algorithm
        minimalYPoint = pullOutMinimalYXpoint();
        sortPointsByPolarAngle(pointsCollection);
        convexHull = new ArrayList<>();
        
        //prepere enivorment for algorimt
        convexHull.add(minimalYPoint);
        convexHull.add(pointsCollection.get(0));
        convexHull.add(pointsCollection.get(1));
        actualStepInGrahamScan = 2;
    }
    
    /**
     * 
     * @param stepNmb step number in finding hull algorithm
     * @return false if finding is done 
     */
    public boolean makeFewStepsInGrahamScan(int stepNmb) {
        for(int i = 0; i < stepNmb; i++) {
            if( !makeOneStepInGrahamScanAlgoritm() ) {
                convexHull.add(convexHull.get(0)); //to make nice loop on chart while drawning
                return false;
                
            }
        }
        return true;
        
    }
    
    public void goToEndOfFindingHull() {
        while(true) {
            if( !makeOneStepInGrahamScanAlgoritm() ) {
                convexHull.add(convexHull.get(0)); //to make nice loop on chart while drawning
                break;
                
            }
        }
    }
    
    private boolean makeOneStepInGrahamScanAlgoritm() {
        while( calculateDetBySarrusMethod(convexHull.get(convexHull.size() - 2),
                                          convexHull.get(convexHull.size() - 1),
                                          pointsCollection.get(actualStepInGrahamScan))
                    < 0){
                //so if the third element is at the right side of vector [first, second]
                convexHull.remove(convexHull.size() - 1);
            }
            convexHull.add(pointsCollection.get(actualStepInGrahamScan));
        
        actualStepInGrahamScan++;
        
        
            //if hull is found inform about that
         if( actualStepInGrahamScan == pointsCollection.size() )
             return false;
         else 
             return true;
        
    }

    private double calculateDetBySarrusMethod(Point2D a, Point2D b, Point2D c) {
        return a.getX() * b.getY() + a.getY() * c.getX() + b.getX() * c.getY()
                - b.getY() * c.getX() - a.getX() * c.getY() - a.getY() * b.getX();
    }

    /**
     * It's find minimal point and delete it from points collection
     * @return the minimal point on Y axis, and if there are more than 1 on this axis than
     * it takes this with smaller X parameter.
     */
    private Point2D pullOutMinimalYXpoint() {
        Point2D minimalXYpoint = pointsCollection.get(0);


        for(int i = 1; i < pointsCollection.size(); i++ ) {

            Point2D actualProcessedPoint = pointsCollection.get(i);

            if(actualProcessedPoint.getY() < minimalXYpoint.getY()) {
                minimalXYpoint = actualProcessedPoint;
            }
            else if(actualProcessedPoint.getY() == minimalXYpoint.getY()) {
                if(actualProcessedPoint.getX() < minimalXYpoint.getX())
                    minimalXYpoint = actualProcessedPoint;
            }
        }
        pointsCollection.remove(minimalXYpoint);
        return minimalXYpoint;
    }

    /**
     *  Sort pointsCollection by polar angle in counterclockwise order with respect to inReferenceTo parameter.
     */
    private void sortPointsByPolarAngle(ArrayList <Point2D> listToSort) {

        ArrayList <PointSlopeFactor> listToSortBySlopeFactor = new ArrayList <>();
        for( Point2D each : listToSort) {
            listToSortBySlopeFactor.add(new PointSlopeFactor(each, minimalYPoint));
        }

        Collections.sort(listToSortBySlopeFactor);

        pointsCollection.clear();
        for( PointSlopeFactor each : listToSortBySlopeFactor)
            pointsCollection.add(each.getPoint());


    }


    private void printPointCollection() {
        System.out.println("Aktualny zestaw punktów w GrahmScan: ");
        for(Point2D elem: pointsCollection)
            System.out.println("    X:" + elem.getX() + "   Y:" + elem.getY());
    }

}
