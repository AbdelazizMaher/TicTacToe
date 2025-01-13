/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOGameBoard;

import java.util.Random;

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

    public String[] assignXOToPlayer() {
        String[] ret = new String[2];

        Random random = new Random();
        if (random.nextBoolean()) {
            ret[0] = "X";
            ret[1] = "O";
        } else {
            ret[0] = "O";
            ret[1] = "X";
        }
        return ret;
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean isDraw() {
        return false;
    }

    public boolean isWinningMove(int row, int col) {
        return (checkRow(row) || checkCol(col) || checkDiagonal());
    }

    private boolean checkRow(int row) {
        if (board[row][0] != null && board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2])) {
            setWinningLine(row, 0, row, 1, row, 2);
            return true;
        }
        return false;
    }

    private boolean checkCol(int col) {
        if (board[0][col] != null && board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
            setWinningLine(0, col, 1, col, 2, col);
            return true;
        }
        return false;
    }
    
    private boolean checkDiagonal() {
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            setWinningLine(0, 0, 1, 1, 2, 2);
            return true;
        } else if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            setWinningLine(0, 2, 1, 1, 2, 0);
            return true;
        }
        return false;
    }




}
