
import java.io.File;
import ProjectExceptions.*;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drapek
 */
public class MainWindowLogic {
    
    static void isTextFile ( File fil_hnd) throws IncorrectFileTypeException, FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(fil_hnd);
        int size = in.available();
        if(size > 1000)
            size = 1000;
        byte[] data = new byte[size];
        in.read(data);
        in.close();
        String s = new String(data, "ISO-8859-1");
        String s2 = s.replaceAll(
                "[a-zA-Z0-9ßöäü\\.\\*!\"§\\$\\%&/()=\\?@~'#:,;\\"+
                "+><\\|\\[\\]\\{\\}\\^°²³\\\\ \\n\\r\\t_\\-`´âêîô"+
                "ÂÊÔÎáéíóàèìòÁÉÍÓÀÈÌÒ©‰¢£¥€±¿»«¼½¾™ª]", "");
        // will delete all text signs

        double d = (double)(s.length() - s2.length()) / (double)(s.length());
        // percentage of text signs in the text
        if (!(d > 0.950))
            throw new IncorrectFileTypeException();
        
        }
    
    static void addRowToTable(JTable where) {
        DefaultTableModel tmp = (DefaultTableModel) where.getModel();
        int acutalsize = tmp.getRowCount();
        acutalsize++;
        tmp.addRow(new Object[]{acutalsize + ".", 0.0, 0.0, false});
    }
    
    static void deleteSelectedRow(JTable where) {
        DefaultTableModel tmp = (DefaultTableModel) where.getModel();
        System.out.println("Wielkość tabeli przed usunięciem: " + tmp.getRowCount());
        for(int i = 0; i < tmp.getRowCount(); i++) {
            boolean result = (boolean) tmp.getValueAt(i, 3);
            if( result ) {
                tmp.removeRow(i);
            }
            
        }
        System.out.println("Wielkość tabeli po usunięciu: " + tmp.getRowCount() );
        //TODO naprawić numeracje tabeli
        ponumerujWiersze(where);
    }
    
    static private void ponumerujWiersze(JTable where) {
        DefaultTableModel tmp = (DefaultTableModel) where.getModel();
        for(int i = 0; i < tmp.getRowCount(); i++) {
            String val = (i + 1) + ".";
            tmp.setValueAt(val, i, 0);
            
            
        }
    }
    
    static void readPointsFile(File fil_hndl, JTable tabela) throws IOException, InvalidFormatInFileException {
        ReadPointsFromFile pointsReader = new ReadPointsFromFile(fil_hndl.getPath());
        System.out.println("Ścieżka wcztytywanego pliku: " + fil_hndl.getPath());
        Point2D [] readed = pointsReader.readPoints();
        
        DefaultTableModel defaultModelTabeli = (DefaultTableModel) tabela.getModel(); 
        
        for (Point2D point : readed ) {
            defaultModelTabeli.addRow(new Object [] { defaultModelTabeli.getRowCount() + 1 + ".", point.getX(), point.getY(), false });
        }
       
       
    }
    
    static Point2D[] rewritePointsTableWitoutDuplicats(JTable jTable1) {
        deleteDuplicatsFromTable(jTable1);
        DefaultTableModel tmp = (DefaultTableModel) jTable1.getModel();
        
        Point2D[] rewritedTableFromTable = new Point2D[tmp.getRowCount()];
        for( int i = 0; i < rewritedTableFromTable.length; i++) {
            rewritedTableFromTable[i] = new Point2D.Double((double) tmp.getValueAt(i, 1), (double) tmp.getValueAt(i, 2));
        }
       
        return rewritedTableFromTable;
    }
    
    private static void deleteDuplicatsFromTable(JTable jTable) {
        DefaultTableModel tmp = (DefaultTableModel) jTable.getModel();
        boolean somethingChange = false;
        for( int i = 0; i < tmp.getRowCount(); i++) {
            for(int j = i + 1; j < tmp.getRowCount(); j++) {
         
                if(( (double) tmp.getValueAt(i, 1) == (double) tmp.getValueAt(j, 1)) &&
                   ( (double) tmp.getValueAt(i, 2) == (double) tmp.getValueAt(j, 2))) {
                    tmp.removeRow(j);
                    j--;
                    somethingChange = true;
                    
                }
            }
        }
        if( somethingChange ) {
            ponumerujWiersze(jTable);
        }
    }
    
    static void inputValuesIntoTableFromDataForwarder(Point2D [] pointsCollection, JTable jTabela) {
        DefaultTableModel tmp = (DefaultTableModel) jTabela.getModel();
        for( Point2D point : pointsCollection) {
            tmp.addRow(new Object[]{(tmp.getRowCount() + 1) + ".", point.getX(), point.getY(), false});
        }
    }
}
