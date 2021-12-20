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

    int blockNumber = 0;

    int currentColumn = 0;
    int currentRow = 0;
    int previousRow = 0;
    int nextRow = 0;

    public BlocksMultiThreadingTester(int receivedBlockNumber) {
        this.blockNumber = receivedBlockNumber;
    }

    JTable t = testing.sudoku.solution.ApplicationScreen.jTable1;
    DefaultTableModel model = (DefaultTableModel) t.getModel();

    String invalidSolution = "";

    int invalidEntry = 0;

    @Override
    public void run() {
        

        if (blockNumber == 1) {
            currentColumn = 2;
            currentRow = 1;
            previousRow = 0;
            nextRow = 2;
        }else if (blockNumber == 2) {
            currentColumn = 5;
            currentRow = 1;
            previousRow = 0;
            nextRow = 2;
        }else if (blockNumber == 3) {
            currentColumn = 8;
            currentRow = 1;
            previousRow = 0;
            nextRow = 2;
        }else if (blockNumber == 4) {
            currentColumn = 2;
            currentRow = 4;
            previousRow = 3;
            nextRow = 5;
        }else if (blockNumber == 5) {
            currentColumn = 5;
            currentRow = 4;
            previousRow = 3;
            nextRow = 5;
        }else if (blockNumber == 6) {
            currentColumn = 8;
            currentRow = 4;
            previousRow = 3;
            nextRow = 5;
        }else if (blockNumber == 7) {
            currentColumn = 2;
            currentRow = 7;
            previousRow = 6;
            nextRow = 8;
        }else if (blockNumber == 8) {
            currentColumn = 5;
            currentRow = 7;
            previousRow = 6;
            nextRow = 8;
        }else{
            currentColumn = 8;
            currentRow = 7;
            previousRow = 6;
            nextRow = 8;
        }
        
        String blockElements[] = {
            String.valueOf(model.getValueAt(currentRow, currentColumn)),
            String.valueOf(model.getValueAt(previousRow, currentColumn - 1)),
            String.valueOf(model.getValueAt(previousRow, currentColumn)),
            String.valueOf(model.getValueAt(previousRow, currentColumn + 1)),
            String.valueOf(model.getValueAt(currentRow, currentColumn - 1)),
            String.valueOf(model.getValueAt(currentRow, currentColumn + 1)),
            String.valueOf(model.getValueAt(nextRow, currentColumn - 1)),
            String.valueOf(model.getValueAt(nextRow, currentColumn)),
            String.valueOf(model.getValueAt(nextRow, currentColumn + 1)),};

        for (int e = 0; e <= 8; e++) {
            try {
                Integer.valueOf(blockElements[e]);
                invalidEntry = 0;
            } catch (Exception ex) {
                invalidEntry = 1;
                testing.sudoku.solution.ApplicationScreen.jLabel4.setText("Only integers are allowed");
                testing.sudoku.solution.ApplicationScreen.jLabel4.setForeground(Color.red);
                break;
            }
        }

        if (invalidEntry != 1) {
            for (int i = 0; i < 8; i++) {

                for (int j = i + 1; j < 9; j++) {

                    if ((Integer.valueOf(blockElements[j]) >= 1 && Integer.valueOf(blockElements[j]) <= 9) && (Integer.valueOf(blockElements[i]) >= 1 && Integer.valueOf(blockElements[j]) <= 9)) {
                        if (blockElements[i].equals(blockElements[j])) {
                            invalidSolution = "Invalid Sudoku Solution";
                            testing.sudoku.solution.ApplicationScreen.jLabel4.setText(invalidSolution + " D:");
                            testing.sudoku.solution.ApplicationScreen.jLabel4.setForeground(Color.red);
                            break;
                        }
                    } else {
                        testing.sudoku.solution.ApplicationScreen.jLabel4.setText("Allowed integers (1-9)");
                        testing.sudoku.solution.ApplicationScreen.jLabel4.setForeground(Color.red);
                        break;
                    }
                }
                if ("Invalid Sudoku Solution".equals(invalidSolution) && "Allowed integers (1-9)".equals(testing.sudoku.solution.ApplicationScreen.jLabel4.getText())) {
                    break;
                }

            }
        }

    }
}
