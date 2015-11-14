
import java.io.File;
import ProjectExceptions.*;
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
        tmp.addRow(new Object[]{acutalsize + ".", 0, 0, false});
    }
    
    static void deleteSelectedRow(JTable where) {
        DefaultTableModel tmp = (DefaultTableModel) where.getModel();
        System.out.println("Wlk tabeli: " + tmp.getRowCount());
        for(int i = 0; i < tmp.getRowCount(); i++) {
            boolean result = (boolean) tmp.getValueAt(i, 3);
            if( result ) {
                tmp.removeRow(i);
            }
            
        }
        
        //TODO naprawić numeracje tabeli
        ponumerujWiersze(where);
    }
    
    static private void ponumerujWiersze(JTable where) {
        DefaultTableModel tmp = (DefaultTableModel) where.getModel();
        for(int i = 0; i < tmp.getRowCount(); i++) {
            String val = (i++) + ".";
            tmp.setValueAt("1", i, 0);
            
            
        }
    }
}
