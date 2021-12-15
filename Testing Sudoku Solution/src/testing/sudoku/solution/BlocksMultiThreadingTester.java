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
public class BlocksMultiThreadingTester extends Thread {

    int currentColumn = 0;
    int currentRow = 0;
    int previousRow = 0;
    int nextRow = 0;

    public BlocksMultiThreadingTester(int receivedCurrentColumn, int receivedCurrentRow, int receivedPreviousRow, int receivedNextRow) {
        this.currentColumn = receivedCurrentColumn;
        this.currentRow = receivedCurrentRow;
        this.previousRow = receivedPreviousRow;
        this.nextRow = receivedNextRow;

    }

    JTable t = testing.sudoku.solution.ApplicationScreen.jTable1;
    DefaultTableModel model = (DefaultTableModel) t.getModel();

    String invalidSolution = "";

    @Override
    public void run() {

        int currentCellValue = 0;
        int prevRowCell1 = 0;
        int prevRowCell2 = 0;
        int prevRowCell3 = 0;
        int prevCell = 0;
        int nextCell = 0;
        int nextRowCell1 = 0;
        int nextRowCell2 = 0;
        int nextRowCell3 = 0;

        for (int i = 0; i < 8; i++) {
            try {
                currentCellValue = Integer.valueOf(String.valueOf(model.getValueAt(currentRow, currentColumn)));

                prevRowCell1 = Integer.valueOf(String.valueOf(model.getValueAt(previousRow, currentColumn - 1)));
                prevRowCell2 = Integer.valueOf(String.valueOf(model.getValueAt(previousRow, currentColumn)));
                prevRowCell3 = Integer.valueOf(String.valueOf(model.getValueAt(previousRow, currentColumn + 1)));
                prevCell = Integer.valueOf(String.valueOf(model.getValueAt(currentRow, currentColumn - 1)));
                nextCell = Integer.valueOf(String.valueOf(model.getValueAt(currentRow, currentColumn + 1)));
                nextRowCell1 = Integer.valueOf(String.valueOf(model.getValueAt(nextRow, currentColumn - 1)));
                nextRowCell2 = Integer.valueOf(String.valueOf(model.getValueAt(nextRow, currentColumn)));
                nextRowCell3 = Integer.valueOf(String.valueOf(model.getValueAt(nextRow, currentColumn + 1)));

                int blockElements[] = {currentCellValue, prevRowCell1, prevRowCell2, prevRowCell3, prevCell, nextCell, nextRowCell1, nextRowCell2, nextRowCell3};

                for (int j = i + 1; j < 9; j++) {

                    if ((blockElements[j] >= 1 && blockElements[j] <= 9) && (blockElements[i] >= 1 && blockElements[i] <= 9)) {
                        if (blockElements[i] == blockElements[j]) {
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
