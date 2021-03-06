
import ProjectExceptions.IncorrectFileTypeException;
import ProjectExceptions.InvalidFormatInFileException;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drapek
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        jTableValueChangedInitListener();
    }
    
    public MainWindow(ForwardingDataPackage forwData) {
        initComponents();
        jTableValueChangedInitListener();
        MainWindowLogic.inputValuesIntoTableFromDataForwarder(forwData.getPointsCollection(), jTable1);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanelPointChart = new javax.swing.JPanel();
        jPanelGlobalButtons = new javax.swing.JPanel();
        jPanelAddPoints = new javax.swing.JPanel();
        jButtonNextWindow = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelTitlePointsPanel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAddPoint = new javax.swing.JButton();
        jButtonReadFromFile = new javax.swing.JButton();
        jButtonDeletePoints = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPointChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelPointChartLayout = new javax.swing.GroupLayout(jPanelPointChart);
        jPanelPointChart.setLayout(jPanelPointChartLayout);
        jPanelPointChartLayout.setHorizontalGroup(
            jPanelPointChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        jPanelPointChartLayout.setVerticalGroup(
            jPanelPointChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAddPointsLayout = new javax.swing.GroupLayout(jPanelAddPoints);
        jPanelAddPoints.setLayout(jPanelAddPointsLayout);
        jPanelAddPointsLayout.setHorizontalGroup(
            jPanelAddPointsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        jPanelAddPointsLayout.setVerticalGroup(
            jPanelAddPointsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButtonNextWindow.setText("Przejdź dalej");
        jButtonNextWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextWindowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGlobalButtonsLayout = new javax.swing.GroupLayout(jPanelGlobalButtons);
        jPanelGlobalButtons.setLayout(jPanelGlobalButtonsLayout);
        jPanelGlobalButtonsLayout.setHorizontalGroup(
            jPanelGlobalButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGlobalButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelGlobalButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGlobalButtonsLayout.createSequentialGroup()
                        .addComponent(jPanelAddPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGlobalButtonsLayout.createSequentialGroup()
                        .addComponent(jButtonNextWindow)
                        .addGap(43, 43, 43))))
        );
        jPanelGlobalButtonsLayout.setVerticalGroup(
            jPanelGlobalButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGlobalButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAddPoints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addComponent(jButtonNextWindow)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelTitlePointsPanel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelTitlePointsPanel.setText("Wczytane punkty");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lp.", "X", "Y", "delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTable1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jTable1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jTable1VetoableChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonAddPoint.setText("Dodaj Punkt");
        jButtonAddPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPointActionPerformed(evt);
            }
        });

        jButtonReadFromFile.setText("Wczytaj z pliku");
        jButtonReadFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadFromFileActionPerformed(evt);
            }
        });

        jButtonDeletePoints.setText("Usuń wybrane punkty");
        jButtonDeletePoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletePointsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabelTitlePointsPanel))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonReadFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonAddPoint)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonDeletePoints)))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitlePointsPanel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddPoint)
                    .addComponent(jButtonDeletePoints))
                .addGap(28, 28, 28)
                .addComponent(jButtonReadFromFile)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGlobalButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelPointChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelPointChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelGlobalButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPointActionPerformed

        MainWindowLogic.addRowToTable(jTable1);
    }//GEN-LAST:event_jButtonAddPointActionPerformed
    
    
    //nie jest używane chwilowo, bo sprawiało ze ise nakładały
    private void jTableValueChangedInitListener() {
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            synchronized public void tableChanged(TableModelEvent e) {
                MainWindowLogic.drawXYChart(jPanelPointChart, jTable1);
            }

  
        });
    }
    private void jButtonReadFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadFromFileActionPerformed

        try {
            File file = null;
            int returnVal = jFileChooser1.showOpenDialog(this);
            if (returnVal == jFileChooser1.APPROVE_OPTION) {
                file = jFileChooser1.getSelectedFile();
                MainWindowLogic.isTextFile(file);
                MainWindowLogic.readPointsFile( file, jTable1);

            } else {
                System.out.println("File access cancelled by user.");
            }
            
            
            
        } catch (IncorrectFileTypeException incExcp) {
            System.out.println(incExcp);
            JOptionPane.showMessageDialog(this, "Wybrany plik nie jest plikiem tekstowym!", "Błąd wczytywania pliku", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Nie można znaleźć pliku pod danym adresem!", "Błąd wczytywania pliku", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Nie mogę wczytać pliku! Sprawdź uprawnienia!", "Błąd wczytywania pliku", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidFormatInFileException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Podany plik ma niepoprawny format wewnętrzny. Powinien on wyglądać następująco \n 5.0 3.2\n 2.1 2.2\n itd.", "Błąd wczytywania pliku", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButtonReadFromFileActionPerformed

    private void jButtonDeletePointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletePointsActionPerformed

        MainWindowLogic.deleteSelectedRow(jTable1);
    }//GEN-LAST:event_jButtonDeletePointsActionPerformed

    private void jButtonNextWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextWindowActionPerformed

        Point2D [] forwardThisPoints = MainWindowLogic.rewritePointsTableWitoutDuplicats(jTable1);
        
        if( forwardThisPoints.length < 3) {
            JOptionPane.showMessageDialog(this, "Aby móc znaleźć otoczkę potrzebne są conjamiej 3 punkty!", "Błąd - zbyt mało punktów!", JOptionPane.WARNING_MESSAGE);
        } 
        else {
            ForwardingDataPackage dataForwarding = new ForwardingDataPackage();
            dataForwarding.setPointsCollection(forwardThisPoints);
            
            FindHullWindow newWindow = new FindHullWindow(dataForwarding);
            newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newWindow.setVisible(true);
            this.setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_jButtonNextWindowActionPerformed

    private void jTable1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTable1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1InputMethodTextChanged

    private void jTable1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorMoved
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jTable1AncestorMoved

    private void jTable1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jTable1VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1VetoableChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddPoint;
    private javax.swing.JButton jButtonDeletePoints;
    private javax.swing.JButton jButtonNextWindow;
    private javax.swing.JButton jButtonReadFromFile;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabelTitlePointsPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAddPoints;
    private javax.swing.JPanel jPanelGlobalButtons;
    private javax.swing.JPanel jPanelPointChart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
