
import java.awt.geom.Point2D;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drapek
 */
public class ForwardingDataPackage {
    private Point2D [] pointsCollection;
    private ArrayList <Point2D> ConvexHullPoints;
    
    public Point2D [] getPointsCollection() {
        return this.pointsCollection;
    }
    
    public void setPointsCollection( Point2D [] newPointsCollection ) {
        this.pointsCollection = newPointsCollection;
    }
    
    public ArrayList <Point2D> getConvexHullPoints() {
        return this.ConvexHullPoints;
    }
    
    public void setConvexHullPoints(ArrayList <Point2D> newConvexHull) {
        this.ConvexHullPoints = newConvexHull;
    }
}
