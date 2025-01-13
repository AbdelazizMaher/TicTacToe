/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOGameBoard;

/**
 *
 * @author Abdel
 */
public class TicTacToe {

    private String[][] board;
    private String currentPlayer;
    private final int SIZE = 3;
    private int[] winningLine;

    public TicTacToe() {
        board = new String[SIZE][SIZE];
        currentPlayer = "X";
        winningLine = new int[6];
    }

    private void getStartingPlayer() {

    }
    
    public boolean makeMove(int row, int col) {
        return true;
    }

    public boolean isDraw() {
        return true;
    }

    public boolean isWinningMove() {
        return true;
    }

    private boolean checkRow(int row) {
        return true;
    }

    private boolean checkCol(int col) {
        return true;
    }

    private boolean checkDiagonals() {
        return true;
    }

    private void setWinningLine(int r1, int c1, int r2, int c2, int r3, int c3) {

    }

    public int[] getWinningLine() {
        return winningLine;
    }

    public void switchPlayer() {

    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void resetBoard() {
    
    }

}
