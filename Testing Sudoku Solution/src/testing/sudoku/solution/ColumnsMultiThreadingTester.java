/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.sudoku.solution;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abbas
 */
public class ColumnsMultiThreadingTester extends Thread {

    int columnNumber = 0;

    public ColumnsMultiThreadingTester(int receivedColumnNumber) {
        this.columnNumber = receivedColumnNumber;
    }

    JTable t = testing.sudoku.solution.ApplicationScreen.jTable1;
    DefaultTableModel model = (DefaultTableModel) t.getModel();

    int currentCellValue = 0;
    int anotherCellValue = 0;
    String invalidSolution = "";

    @Override
    public void run() {

        for (int row = 0; row <= 7; row++) {
            try {
                currentCellValue = Integer.valueOf(String.valueOf(model.getValueAt(row, columnNumber)));

                for (int i = row + 1; i <= 8; i++) {
                    try {
                        anotherCellValue = Integer.valueOf(String.valueOf(model.getValueAt(i, columnNumber)));
                        if ((currentCellValue >= 1 && currentCellValue <= 9) && (anotherCellValue >= 1 && anotherCellValue <= 9)) {
                            if (currentCellValue == anotherCellValue) {
                                invalidSolution = "Invalid Sudoku Solution";
                                testing.sudoku.solution.ApplicationScreen.jLabel4.setText(invalidSolution + " D:");
                                testing.sudoku.solution.ApplicationScreen.jLabel4.setForeground(Color.red);
                                break;
                            }
                        } else {
                            testing.sudoku.solution.ApplicationScreen.jLabel4.setText("Allowe numbers (1-9)");
                            testing.sudoku.solution.ApplicationScreen.jLabel4.setForeground(Color.red);
                            break;
                        }
                    } catch (Exception e) {
                        testing.sudoku.solution.ApplicationScreen.jLabel4.setText("Allowed numbers (1-9)");
                        testing.sudoku.solution.ApplicationScreen.jLabel4.setForeground(Color.red);
                    }
                }
                if ("Invalid Sudoku Solution".equals(invalidSolution) && "Allowe numbers (1-9)".equals(testing.sudoku.solution.ApplicationScreen.jLabel4.getText())) {
                    break;
                }
            } catch (Exception e) {
                testing.sudoku.solution.ApplicationScreen.jLabel4.setText("can't accept letters");
                testing.sudoku.solution.ApplicationScreen.jLabel4.setForeground(Color.red);
                break;
            }
        }

    }
}
