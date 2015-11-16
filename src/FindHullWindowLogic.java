
import java.awt.geom.Point2D;

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
}
